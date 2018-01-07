/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-jshint');

    return {
        options: {
            jshintrc: '.jshintrc'
        },
        sources: ['' + options.paths.app + '/**/*.js']
    }
}