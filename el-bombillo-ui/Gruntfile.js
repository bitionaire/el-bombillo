module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        copy: {
            main: {
                files: [
                    {expand: true, flatten: true, src: ['src/main/resources/webapp/*.html'], dest: 'build/resources/main/webapp/'}
                ]
            }
        },
        watch: {
            html: {
                files: "src/main/resources/webapp/**/*.html",
                tasks: ['copy']
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('default', ['copy']);
};