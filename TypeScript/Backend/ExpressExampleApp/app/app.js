const express = require('express')
const SUCCESS_CODE = 200
const PORT = 80

express()
    .use((req, res, next) => {
        res.header('Access-Control-Allow-Origin', req.get('Origin') || '*')
        res.header('Access-Control-Allow-Credentials', 'true')
        res.header('Access-Control-Allow-Methods', 'GET,HEAD,PUT,PATCH,POST,DELETE')
        res.header('Access-Control-Expose-Headers', 'Content-Length')
        res.header(
            'Access-Control-Allow-Headers',
            'Accept, Authorization, Content-Type, X-Requested-With, Range'
        )

        if (req.method === 'OPTIONS') {
            return res.send(SUCCESS_CODE)
        }

        return next()
    })
    .use('/static', express.static('static'))
    .use('/', (req, res, next) => {
        res.send('Hello World!')
    })
    .listen(PORT, () => {
        console.log(`Server started on port: ${PORT}`)
    })