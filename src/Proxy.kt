import java.net.MalformedURLException
import java.net.ServerSocket
import java.net.Socket
import java.net.URL
import java.nio.charset.Charset

class Proxy(port: Int = 8080) {
    private val serverSocker: ServerSocket = ServerSocket(port)

    fun start() {
        Thread {
            while (true) {
                val socket = serverSocker.accept()
                Thread{ handleSocket(socket) }.start()
            }
        }.start()
    }

    private fun handleSocket(clientSocket: Socket) {
        val lines = ArrayList<String>()
        val reader = clientSocket.getInputStream().bufferedReader()
        var line: String = reader.readLine()
        while (line.isNotEmpty()) {
            lines.add(line)
            line = reader.readLine()
        }

        val header = lines[0].split(" ")

        println(header)

        val requestType =    header[0]
        val requestUrl = try { URL(header[1]) } catch (e: MalformedURLException) { clientSocket.close() }
        val protocol =       header[2]


    }
}