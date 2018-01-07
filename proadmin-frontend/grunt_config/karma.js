/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-karma');

    return {
        options: {
            configFiles: 'karma.conf.js'
        },
        dev: {
            reporters: ['story']
        },
        dist: {
            reporters: ['story', 'junit']
        }
    }
}