package myapp.core.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        // Create a server socket & listen to port
        int PORT = 3000;
        ServerSocket server = new ServerSocket(PORT);

        System.out.printf("Waiting for Connection on port %d \n", PORT);
        Socket sock = server.accept();
        System.out.println("Connection Accepted");

        // Get input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get outputstream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // Incoming request
        String request = dis.readUTF();

        // Perform computation on request
        request = request.toUpperCase();

        // Write back to client
        dos.writeUTF(request);

        // Close streams
        is.close();
        os.close();

        // Close sockets
        sock.close();

    }
}
