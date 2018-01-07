/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-concat');

    var paths = options.paths, dist_files = [];

    dist_files['' + paths.dist + '/vendorlib/proadmin/vendor.min.js'] = [
        '' + paths.dist + '/vendorlib/**/*.js'
    ];

    return {
        dist: {
            files: dist_files,
            expand: true,
            dist: '' + paths.dist + '/vendorlib/proadmin',
            concat: {
                options: {

                }
            }
        }
    }
}