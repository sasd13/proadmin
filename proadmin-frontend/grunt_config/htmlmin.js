/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-htmlmin');

    var paths = options.paths;

    return {
        htmlmin: {
            options: {
                removeComments: true,
                collapseWhitespace: true
            },
            files: [
                {
                    expand: true,
                    cwd: paths.dist,
                    src: ['app/**/*.html'],
                    dest: paths.dist
                }
            ]
        }
    }
}