var fs = require('fs'),
    semFolder = './node_modules/semantic-ui-less';

function createSiteFolder() {
	try {
	  if (fs.lstatSync(semFolder + '/site').isDirectory()) {
	    //site folder exists, continuing
	  }
	}
	catch (e) {
		if(e.code == 'ENOENT') {
	    fs.renameSync(semFolder + '/_site', semFolder + '/site');
		} else {
		  console.error('An error during the Semantic UI initialization: ', e);
		}
	}
}

function copyConfig() {
	fs.createReadStream('src/theme.config').pipe(fs.createWriteStream(semFolder + '/theme.config'));
}

createSiteFolder();
copyConfig();