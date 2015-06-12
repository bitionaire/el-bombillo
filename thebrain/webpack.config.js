var webpack = require('webpack');

module.exports = {
  devServer: {
      contentBase: "./dist",
      noInfo: true,
      hot: false,
      inline: false
  }
};
//   entry: {
//     'thebrain': './src/js/main.js'
//   },
//   output: {
//     filename: '[name].js'
//   },
//   devtool: 'source-map',
//   module: {
//     loaders: [
//       {test: /\.css$/, loader: 'style!css'},
//       {test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/}
//     ]
//   }
