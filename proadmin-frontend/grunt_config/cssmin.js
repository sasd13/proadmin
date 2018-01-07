/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-concat');

    var paths = options.paths, dist_files = [];

    dist_files['' + paths.dist + '/res/app.min.css'] = '' + paths.dist + '/res/app.css';

    return {
        dist: {
            files: dist_files,
            expand: true,
            dist: '' + paths.dist + '/res/css',
            cssmin: {
                options: {
                    shortandCompacting: false,
                    roundingPrecision: -1
                }
            }
        }
    }
}