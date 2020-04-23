const inquirer = require('inquirer');

module.exports = {
    ask: () => {
        const questions = [
            {
                name: 'url',
                type: 'input',
                message: `❯ Enter the url of news`,
               
            },
            {
                name : 'source',
                type: 'input',
                message: `❯ Enter the source of news`,
                
            },
            {
                name: 'content',
                type: 'input',
                message: `❯ Enter the content`,
                validate : function(value){
                    if(value.length){
                        return true
                    }
                    else{
                        return 'Please enter the content'
                    }
                }
            }
        ];
        return inquirer.prompt(questions)
    }
}