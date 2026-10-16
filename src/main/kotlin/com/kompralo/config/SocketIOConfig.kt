package com.kompralo.config

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

@org.springframework.context.annotation.Configuration
class SocketIOConfig {

    @Value("\${socketio.host:0.0.0.0}")
    private lateinit var host: String

    // Cambiado el default a 3002 para que coincida con lo que busca tu frontend
    @Value("\${socketio.port:3002}")
    private var port: Int = 3002

    private var server: SocketIOServer? = null

    @Bean
    fun socketIOServer(): SocketIOServer {
        val config = Configuration().apply {
            hostname = host
            port = this@SocketIOConfig.port

            // IMPORTANTE: En netty-socketio no uses comas aquí.
            // Usa "*" (o null) para que permita localhost:5173, 5174 y 3000 sin fallar en el handshake
            origin = "*"

            isAllowCustomRequests = true
            upgradeTimeout = 10000
            pingTimeout = 60000
            pingInterval = 25000
        }

        return SocketIOServer(config).also { server = it }
    }

    @Bean
    fun springAnnotationScanner(socketServer: SocketIOServer): SpringAnnotationScanner {
        return SpringAnnotationScanner(socketServer)
    }

    @PreDestroy
    fun stopSocketIOServer() {
        server?.stop()
    }
}