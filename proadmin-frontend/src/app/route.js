/**
 * @name proadmin:route
 * @author Samir SAID-ALI
 * @requires routeProvider
 */

'use strict';

angular.module('proadmin')
.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "components/home/views/home.html",
            controller: "homeCtr",
            resolve : {

            }
        })
        .otherwise({
            redirectTo: "/"
        });
});