/**
 * @author  Samir SAID-ALI samir_saidali@yahoo.fr
 * @desc    Grunt configuration file
 */

module.exports = function (grunt) {
    var options = require("package.json");
    
    /**
     * @desc loads modules with configuration from the grunt_config folder
     * @param {String} 'module name of the module which should be loads
     * @returns {function} object with the grunt module and all necessary configuration information
     */
    loadConfig = function (module) {
        return require("./grunt_config" + module)(options, grunt);
    }

    loadConfig("npm");
    loadConfig("libs");

    //load grunt modules which are needed
    grunt.initConfig({
        pkg: options,
        jshint: loadConfig("jshint"),
        clean: loadConfig("clean"),
        concat: loadConfig("concat"),
        connect: loadConfig("connect"),
        cssmin: loadConfig("cssmin"),
        htmlbuild: loadConfig("htmlbuild"),
        htmlmin: loadConfig("htmlmin"),
        karma: loadConfig("karma"),
        less: loadConfig("less"),
        ngAnnotate: loadConfig("annotate"),
        ngdocs: loadConfig("ngdocs"),
        protractor: loadConfig("protractor"),
        template: loadConfig("template"),
        sync: loadConfig("sync"),
        uglify: loadConfig("uglify"),
        watch: loadConfig("watch")
    });

    //available tasks for grunt
    grunt.registerTask("dependencies", ["npm-install"]);
    grunt.registerTask("sync_dev", ["sync:dev_js", "sync:dist_static", "sync:dev_libs", "sync:fonts_dev", "htmlbuild:dev"]);
    grunt.registerTask("sync_dist", ["sync:dist_js", "sync:dist_static", "sync:dist_libs", "sync:dist_fonts"]);
    grunt.registerTask("compile_dev", ["sync_dev", "less:dev", "htmlbuild:dev"]);
    grunt.registerTask("compile_dist", ["sync_dist", "less:dist", "cssmin", "htmlmin", "ngAnnotate", "uglify", "concat", "clean:annotated"]);
    grunt.registerTask("dev", ["clean:dev", "compile_dev", "template:dev", "jshint", "karma:dev", "configureProxies:server", "connect:server", "watch"]);
    grunt.registerTask("dist", ["dependencies", "clean:dist", "jshint", "karma:dist", "compile_dist", "ngdocs", "template:dist_min"]);
    grunt.registerTask("unittest", ["karma:dev"]);
    grunt.registerTask("e2etest", ["protractor:dev"]);
    grunt.registerTask("nhDocs", ["ngdocs"]);
}