var webpack = require('webpack');

module.exports = {
  entry: {
    'thebrain': './src/js/main.js'
  },
  output: {
    filename: 'static/js/[name].js'
  },

  devtool: 'source-map',
  module: {
    loaders: [
      // {test: /\.css$/, loader: 'style!css'},
      {test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/}
    ]
  },

  devServer: {
    contentBase: "./static",
    noInfo: true,
    hot: false,
    inline: false
  }

};