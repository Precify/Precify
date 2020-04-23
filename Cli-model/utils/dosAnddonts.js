const meow = require('meow');
const chalk = require('chalk');
const green = chalk.green;
const yellow = chalk.yellow;
const blue = chalk.blue;
const cyan = chalk.cyan;
const sym = require('log-symbols');
const red = chalk.red;
const to = require('await-to-js').default;
const handleError = require('cli-handle-error');



module.exports = meow(
	`
	    ${yellow.bold(`Protect Yourself and Others !!`)}

          ${blue.italic.bold.underline(`Follow these Do's & Don'ts`)}

        

        ${green(sym.success, ` Practice Frequent hand washing`)}
        ${green(sym.success, ` Cover your nose and mouth with hankerchief/tissue while sneezing and coughing`)}
        ${green(sym.success, ` Throw used tissues into closed bins immediately`)}
        ${green(sym.success, ` See a doctor if you feel unwell, while visiting doctor wear a mask/cloth to cover your mouth and nose`)}
        

        ${red(sym.error, ` Don't go to crowded areas`)}
        ${red(sym.error, ` Don't touch your eyes, nose and mouth`)}
        ${red(sym.error, ` Don't split in public`)}
    `,
);
