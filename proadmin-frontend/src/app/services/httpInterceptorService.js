/**
 * @name proadmin:httpInterceptorService
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

angular.module('proadmin')
.factory('httpRequestInterceptor', function () {
    return {
        request: function (config) {
            config.headers['x-transactionID'] = 'PROADMIN-FRONTEND';

            return config;
        }
    }
});

angular.module('proadmin')
.config(function ($httpProvider) {
    //initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }

    //disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    //extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

    $httpProvider.interceptors.push('httpRequestInterceptor');
});