var webpack = require('webpack');

module.exports = {
  entry: {
    'main': './src/js/main.js'
  },
  output: {
    filename: 'static/js/[name].js'
  },

  devtool: 'source-map',
  module: {
    exclude: /(node_modules)/,
    loaders: [ { test: /\.js$/, loader: 'babel' } ]
  },

  devServer: {
    contentBase: "./static",
    noInfo: true,
    hot: true,
    inline: false
  }

};