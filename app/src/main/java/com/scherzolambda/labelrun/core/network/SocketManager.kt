package com.scherzolambda.labelrun.core.network

import android.util.Log
import com.scherzolambda.labelrun.core.config.EnvConfig
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

object SocketManager {
    private var mSocket: Socket? = null
    private const val TAG = "SocketManager"

    // O Socket.IO usará a mesma URL base da API
    private var SERVER_URL = EnvConfig.get("API_BASE_URL")

    fun getSocket(): Socket? {
        if (mSocket == null) {
            try {
                val opts = IO.Options().apply {
                    reconnection = true
                    reconnectionAttempts = 5
                    reconnectionDelay = 1000
                    timeout = 10000
                }
                mSocket = IO.socket(SERVER_URL, opts)
                setupSocketListeners()
            } catch (e: URISyntaxException) {
                Log.e(TAG, "Erro ao criar socket: URL inválida", e)
                throw RuntimeException("Falha ao instanciar o socket - URL inválida: $SERVER_URL", e)
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao criar socket", e)
                throw RuntimeException("Falha ao instanciar o socket", e)
            }
        }
        return mSocket
    }

    private fun setupSocketListeners() {
        mSocket?.apply {
            on(Socket.EVENT_CONNECT, onConnect)
            on(Socket.EVENT_DISCONNECT, onDisconnect)
            on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        }
    }

    private val onConnect = Emitter.Listener {
        Log.d(TAG, "Socket conectado com sucesso")
    }

    private val onDisconnect = Emitter.Listener {
        Log.d(TAG, "Socket desconectado")
    }

    private val onConnectError = Emitter.Listener { args ->
        Log.e(TAG, "Erro ao conectar socket: ${args.joinToString()}")
    }

    fun connect() {
        try {
            if (getSocket()?.connected() == false) {
                Log.d(TAG, "Conectando ao servidor...")
                getSocket()?.connect()
            } else {
                Log.d(TAG, "Socket já está conectado")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao conectar", e)
        }
    }

    fun disconnect() {
        try {
            mSocket?.disconnect()
            Log.d(TAG, "Socket desconectado manualmente")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao desconectar", e)
        }
    }

    fun isConnected(): Boolean = mSocket?.connected() ?: false
}
