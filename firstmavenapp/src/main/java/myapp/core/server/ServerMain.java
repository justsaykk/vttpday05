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

        // Perform computation on request

        Boolean isOpen = true;
        while (isOpen) {
            // Incoming request
            String request = dis.readUTF().toLowerCase();
            // Check if request is "exit"
            if (request.equals("exit")) {
                // If true, break and close streams + socket
                is.close();
                os.close();
                sock.close();
                isOpen = false;
                break;
            } else {
                // If false, compute, keep streams and socket open
                request = request.toUpperCase();
                dos.writeUTF(request);
            }

        }
    }
}
