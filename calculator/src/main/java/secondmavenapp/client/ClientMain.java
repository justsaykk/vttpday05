package secondmavenapp.client;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

import secondmavenapp.InputOutputStream;

public class ClientMain extends InputOutputStream {

    public ClientMain(Socket s) throws IOException {
        super(s);
    }

    public static void main(String[] args) throws IOException {
        // Connect to server
        System.out.println("Connecting to localhost at port: 3000");
        Socket sock = new Socket("localhost", 3000);
        System.out.println("Connected");

        // Construct input and output streams
        InputOutputStream io = new InputOutputStream(sock);

        // Get console
        Console cons = System.console();

        Boolean isOpen = true;
        while (isOpen) {
            // Prompt inputs
            String snum1 = cons.readLine("Input first number: ").trim();
            String snum2 = cons.readLine("Input second number: ").trim();
            String operator = cons.readLine("add, div, sub, mul: ").trim();
            //// If value is not provided
            if (snum1.equals(null) || snum2.equals(null) || operator.equals(null)) {
                System.out.println("Please provide the stated values");
                io.close();
                break;
            }
            String request = snum1 + " " + snum2 + " " + operator;
            io.write(request);

            // Read from server response
            String response = io.read();
            System.out.printf("> %s \n", response);
        }
    }
}
