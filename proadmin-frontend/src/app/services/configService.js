/**
 * @name proadmin:configService
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

angular.module('proadmin')
.factory('configService', function ($http) {
})
//Decorate the service
.config(function (errorHandlerProvider, $provide) {
    errorHandlerProvider.decorate($provide, ['configService']);
});