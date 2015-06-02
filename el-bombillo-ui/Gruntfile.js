module.exports = function (grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        copy: {
            main: {
                files: [
                    {expand: true, flatten: true, src: ['src/main/resources/webapp/app/**/*.html'], dest: 'build/resources/main/webapp/', filter: 'isFile'},
                    {expand: true, flatten: true, src: ['src/main/resources/webapp/*.html'], dest: 'build/resources/main/webapp/'}
                ]
            }
        },
        tsd: {
            refresh: {
                options: {
                    command: 'reinstall',
                    latest: true,
                    overwrite: true,
                    config: 'src/main/resources/webapp/app/tsd.json',
                    opts: { }
                }
            }
        },
        ts: {
            default: {
                src: [ 'src/main/resources/webapp/app/**/*.ts' ],
                out: 'build/resources/main/webapp/js/el-bombillo-application.js',
                options: {
                    sourceMap: false
                }
            }
        },
        watch: {
            js: {
                files: "src/main/resources/webapp/app/**/*.ts",
                tasks: ['ts']
            },
            html: {
                files: "src/main/resources/webapp/**/*.html",
                tasks: ['copy']
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-tsd');
    grunt.loadNpmTasks("grunt-ts");

    grunt.registerTask('default', ['tsd', 'ts', 'copy']);
};