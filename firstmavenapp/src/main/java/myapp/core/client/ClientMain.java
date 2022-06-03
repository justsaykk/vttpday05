package myapp.core.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args)
            throws IOException {
        // Connect to server
        System.out.println("Connecting to localhost at port: 3000");
        Socket sock = new Socket("localhost", 3000);
        System.out.println("Connected");

        // Get input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get outputstream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // Enable user input
        Console cons = System.console();
        String input = cons.readLine(">> Say Something to the server: \n");

        // Write to the server
        dos.writeUTF(input);
        dos.flush(); // To make sure it doesnt get stuck in the internal buffer

        // Read from the server
        String response = dis.readUTF();
        System.out.printf(">> %s \n", response);

        // Close streams
        is.close();
        os.close();

        // Close sockets
        sock.close();

    }
}
