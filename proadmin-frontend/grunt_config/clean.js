/**
 * @name proadmin:app
 * @author Samir SAID-ALI
 * @requires ngRoute
 */

'use strict';

module.exports = function (options, grunt) {
    grunt.loadNpmTasks('grunt-contrib-clean');

    var paths = options.paths;

    return {
        dist: ['' + paths.dist + '/app', '' + paths.dist + 'vendorlib'],
        dev: paths.dev,
        annotated: '' + paths.dist + 'annotated',
        unusedJs: '' + paths.dist + '/app',
        unusedCss: '' + paths.dist + '/res/app.css'
    }
}