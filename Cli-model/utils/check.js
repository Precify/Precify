const ora = require('ora');
const spinner = ora({ text: '' });
const meow = require('meow');
const chalk = require('chalk');
const sym = require('log-symbols');
const comma = require('comma-number');
const red = chalk.red;
const cyan = chalk.cyan;
const green = chalk.green;
const to = require('await-to-js').default;
const handleError = require('cli-handle-error');
const fetch = require('node-fetch');
const inquirer = require('./inquirer.js')
const figlet = require('figlet');
const dim = chalk.dim;
const figures = require('figures');
const cliProgress = require('cli-progress');



module.exports = async () => {
    console.log(
        chalk.yellow(
            figlet.textSync('Fake News Detection', {
                horizontalLayout: 'default',
            })
        )
    )
    console.log(`\n`)
    const info = await inquirer.ask();
        console.log(`\n`)
        spinner.start()
        let data = {
            url: info.url,
            source: info.source,
            text: info.content,
        };
        let url = "https://stark-anchorage-45962.herokuapp.com/addPost";
        fetch(url, {
            method: "POST",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" },
        })
        .then((raw) => raw.json())
        .then((res) => {
            spinner.stopAndPersist();
            if(res !== -1){
                const b1 = new cliProgress.SingleBar({
                    format: 'Fakeness Gradient |' + red('{bar}') + '| {percentage}%',
                    barCompleteChar: '\u2588',
                    barIncompleteChar: '\u2591',
                    hideCursor: true
                });
                b1.start(100, 0, {
                    speed: "N/A"
                });
                b1.update((res)*100, {speed : '10'});
                b1.stop();
            } 
            console.log(`\n`)
            if(res === -1){
                    console.log(sym.warning, `${cyan(` Oops!! Cannot classify !!`)}`)
            }
            else if(res < 0.5){
                    console.log(sym.success, `${green(` you can trust on this news :) !!`)}`)
            }
            else{
                    console.log(sym.error, `${red(` This news is most likely to be fake :( !!`)}`)
            } 
                           
            console.log(
                `\n\n${figures.star} ${dim(
                    ` For more info → https://github.com/Precify/Precify`
                )}\n`
            );
        })
        .catch((err) => {
            spinner.stopAndPersist();
            handleError(`API is down, try again later here.`, err, false);
        }); 
        
}