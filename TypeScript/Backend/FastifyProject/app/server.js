import Fastify from 'fastify'

class Server {
    serviceName = 'example'
    PORT = 3000
    HOST = '0.0.0.0'
    fastify = new Fastify()

    constructor() {
        this.fastify.get(`/healthcheck`, async (req, res) => {
            res.sent = true
            res.raw.writeHead(200, { 'Content-Type': 'application/json; charset=utf-8' })
            res.raw.end(JSON.stringify({
                status: 'ok',
                serviceName: this.serviceName
            }))
        })
        this.fastify.get(`/api/test`, async (req, res) => {return `test output`})
    }

    async start() {
        return this.fastify.listen(
            this.PORT,
            this.HOST
        )
    }
}

new Server().start()
  // eslint-disable-next-line no-console
  .then(address => console.log(`Service listening on ${address}`))
  .catch(err => {
    // eslint-disable-next-line no-console
    console.error('Error starting service:', err)
    // eslint-disable-next-line no-process-exit
    process.exit(1)
  })
