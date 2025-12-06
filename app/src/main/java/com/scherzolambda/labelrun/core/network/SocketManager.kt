package com.scherzolambda.labelrun.network

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketManager {
    private var mSocket: Socket? = null
    // Lembre-se de substituir "SEU_ENDERECO_IP" pelo IP do seu servidor
    private const val SERVER_URL = "http://SEU_ENDERECO_IP:8080/"

    fun getSocket(): Socket? {
        if (mSocket == null) {
            try {
                mSocket = IO.socket(SERVER_URL)
            } catch (e: URISyntaxException) {
                e.printStackTrace()
                throw RuntimeException("Falha ao instanciar o socket", e)
            }
        }
        return mSocket
    }

    fun connect() {
        if (getSocket()?.connected() == false) {
            getSocket()?.connect()
        }
    }

    fun disconnect() {
        getSocket()?.disconnect()
    }
}
