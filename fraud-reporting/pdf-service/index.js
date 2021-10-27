const { generatePdf } = require('tea-school');
const path = require('path');
const express = require('express'),
    app = express(),
    bodyParser = require('body-parser');

// support parsing of application/json type post data
app.use(bodyParser.json());

app.post('/api/generate', (req, res) => {
    const options = {
        htmlTemplatePath: path.resolve(__dirname, 'template.pug'),

        // Here you put an object according to https://github.com/sass/node-sass#options
        styleOptions: {
            file: path.resolve(__dirname, 'template.scss')
        },

        // Here you put an object according to https://pugjs.org/api/reference.html#options
        // You can add any additional key to be used as a variable in the template.
        htmlTemplateOptions: req.body,

        // Her  e you put an object according to https://github.com/GoogleChrome/puppeteer/blob/v1.18.1/docs/api.md#pagepdfoptions
        pdfOptions: {
            format: 'A4',
            printBackground: true
        }
    }

    generatePdf(options).then((buffer) => {
        console.log('PDF generated!')
        res.send({ data: { file: buffer.toString('base64') } });
    }).catch(err => {
        console.error('Error generating PDF:', err);
        res.status(500).send({ err: 'Error generating report' });
    });
})

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`PDF service listing on port ${PORT}! 🚀`));
