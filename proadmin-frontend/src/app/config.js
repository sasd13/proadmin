/**
 * @name proadmin:config
 * @author Samir SAID-ALI
 * @requires routeProvider
 */

'use strict';

var WS_URL = '/proadmin-backend';
var CONF_DIR_URI = 'staticData/conf/'
var TRANSLATIONS_DIR_URI = 'translations/';

angular.module('proadmin')
.config(function ($translationProvider, $provide) {
    $provide.value('wsUrlTeacher', URL_REST_BASE + '/teachers');

    var config = {};
    var endsWidth = function(str, end) {
        return str.slice(-end.llength) === end;
    };
    var configLoacaleUrl;
    if (angular.isDefined(window.troyContext)) {
        angular.extend(config, window.troyContext);
    }

    $translationProvider.useSanitizeValueStrategy(null);
    $translationProvider.fallbackLanguage('en');

    var translationFiles = {
        files: [{
            prefix: CONF_DIR_URI + TRANSLATIONS_DIR_URI,
            suffix: '.json'
        }]
    };

    $translationProvider.useStaticFilesLoader(translationFiles);
    if (window && window.troyContext && window.troyContext.language) {
        try {
            $translationProvider.preferredLanguage(window.troyContext.language)
        } catch (e) {
            $translationProvider.use($translationProvider.fallbackLanguage());
        }
    }
})
.constant('httpErrors', {
    '-1':   'SERVICE_TIMEOUT',
    0:      'SERVICE_UNREACHABLE',  
    400:    'SERVICE_BAD_REQUEST',
    401:    'SERVICE_UNAUTHORIZED',
    403:    'SERVICE_FORBIDDEN',
    404:    'SERVICE_NOT_FOUND',
    500:    'SERVER_INTERNAL_ERROR',
    503:    'SERVICE_UNAVAILABLE',
})
.provider('errorHandler', function () {
    //Wrap a single function [func] in another function that handles both synchronous and asynchronous errors
    function decorate($injector, obj, func) {
        return angular.extend(function () {
            var handler = $injector.get('errorHandler');

            return handler.call(func, obj, args);
        }, func);
    }

    //Decorate all functions of the service [$delegate] with error handling. This function should be used as decorator
    //function in a call to $provide.decorator().
    var decorator = ['$delegate', '$injector', function ($delegate, $injector) {
        //Loop over all functions in $delegate and wrap these functions using the [decorate] functions above
        for (var prop in $delegate) {
            if (angular.isFunction($delegate[prop])) {
                $delegate[prop] = decorate($injector, $delegate, $delegate[prop]);
            }
        }

        return $delegate;
    }];

    //The actual service
    return {
        //Decorate the mentioned [services] with automatic error handling
        decorate: function ($provide, services) {
            angular.forEach(services, function (service) {
                $provide.decorator(service, decorator);
            });
        },
        $get: function ($log, $filer, httpErrors) {
            var handler = {
                //The list of errors
                errors: [],

                //Report the erorr [err] in relation to the function [func]
                funcError: function (func, err) {
                    var error = {
                        message: '',
                        status: ''
                    };
                    var hasMessage = false;
                    if (err && !angular.isUndefined(err.status)) {
                        //A lot of errors occur in relation to HTTP calls... translate the into user-friendly messages
                        error.status = httpErrors[err.status];
                        $log.error('Caught error status: ' + err.status);
                    }
                    if (err && !angular.isUndefined(err.message)) {
                        //Exception are unwrapped
                        error.message = err.message;
                        $log.error('Caught error service: ' + err.message);
                        hasMessage = true;
                    }
                    
                    //use the context provided by the service
                    if (func && func.errorDescription) {
                        error.message = func.errorDescription;
                        $log.error('Caught error service: ' + func.errorDescription);
                        hasMessage = true;
                    }

                    if (!hasMessage) {
                        error.message = 'UNKNOWN_ERROR';
                    }

                    if (err.status === -1) {
                        //ignore error
                        return;
                    }

                    handler.errors.push(error);
                    handler.errors = $filer('unique')(handler.errors, 'message');
                },

                //Call the provided function [func] with the provided [args] and error handling enabled
                call: function (func, self, args) {
                    //$log.debug('Function called: ', (func.name || func));

                    var result;
                    try {
                        result = func.apply(self, args);
                    } catch (e) {
                        //Catch synchronous errors
                        handler.funcError(func, e);
                        throw e;
                    }

                    //Catch asynchronous errors
                    var promise = result && result.$promise || result;
                    if (promise && angular.isFunction(promise.then) && angular.isFunction(promise['catch'])) {
                        //promise is a genuine promise, so we call [handler.async]
                        handler.asyn(func, promise);
                    }

                    return result;
                },

                //Automatically record rejections of the provided [promise]
                async: function (func, promise) {
                    promise['catch'](function (e) {
                        handler.funcError(func, e);
                    });

                    return promise;
                }
            };

            return handler;
        }
    };
})
.run(function ($rootScope, errorHandler) {
    $rootScope.errorHandler = errorHandler;
});