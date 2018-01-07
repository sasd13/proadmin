/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-less');

    var paths = options.paths, dev_files = {}, dist_files = {};

    dev_file['' + paths.dev + 'res/app.css'] = '' + paths.app + '/app.less';
    dist_file['' + paths.dist + 'res/app.css'] = '' + paths.app + '/app.less';

    return {
        dev: {
            files: dev_files,
            expand: true,
            dist: '' + paths.dev + '/res/',
            options: {
                sourceMap: true,
                sourceMapBasepath: paths.dev,
                sourceMapRootpath: '/'
            }
        },
        ddist: {
            files: dist_files,
            expand: true,
            dist: '' + paths.dist + '/res/',
            options: {
                paths: [paths.node_modules, '' + paths.dev + '/res/'],
                ieCompat: false,
                relativeUrls: true
            }
        }
    }
}