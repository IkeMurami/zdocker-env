package com.ikemurami.service.component

import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.*

@Component
class HttpClientComponent : InitializingBean, DisposableBean {
    private var backgroundTaskTimer: Timer? = null
    private var multiThreadedHttpConnectionManager: MultiThreadedHttpConnectionManager? = null

    override fun afterPropertiesSet() {
        backgroundTaskTimer = Timer(true)
        multiThreadedHttpConnectionManager = MultiThreadedHttpConnectionManager()
    }

    override fun destroy() {
        backgroundTaskTimer?.purge()
        backgroundTaskTimer?.cancel()
        multiThreadedHttpConnectionManager?.shutdown()
    }

    fun getHttpClient(): HttpClient {
        return HttpClient(this.multiThreadedHttpConnectionManager)
    }

    fun getTimer(): Timer? {
        return this.backgroundTaskTimer
    }
}