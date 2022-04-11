import asyncio
from aiohttp import web
import json

async def handler(request):
    if (request.method == 'GET' and request.path == '/api/healthcheck'):
        return web.Response(text=json.dumps({
            'status': 'OK'
        }))
    exception = web.HTTPException(text='Not Found')
    exception.status_code = 404

    return exception


app = web.Application()
app.router.add_get('/api/healthcheck', handler)
web.run_app(app, port=3000)

async def main():
    """
    server = web.Server(handler)
    runner = web.ServerRunner(server)
    await runner.setup()
    site = web.TCPSite(runner, '0.0.0.0', 3000)
    await site.start()
    """


    print('Serving on http://0.0.0.0:3000')



"""
loop = asyncio.get_event_loop()

try:
    loop.run_until_complete(main())
except KeyboardInterrupt:
    pass
loop.close()
"""