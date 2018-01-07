/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-ng-annotate');

    var paths = options.paths;

    return {
        'proadmin': {
            files: [
                {
                    expand: true,
                    src: 'js/**/*.js',
                    dest: '' + path.dist + "/annotated",
                    cwd: paths.app
                }
            ]
        }
    }
}