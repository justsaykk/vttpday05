package secondmavenapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import secondmavenapp.InputOutputStream;

public class ServerMain extends InputOutputStream {

    public ServerMain(Socket s) throws IOException {
        super(s);
    }

    public static void main(String[] args) throws IOException {
        // Create server and declare port
        int PORT = 3000;
        ServerSocket server = new ServerSocket(PORT);

        // Connect the server
        System.out.printf("Waiting for Connection on port %d \n", PORT);
        Socket sock = server.accept();
        System.out.println("Connection Accepted");

        // Get input and output streams
        InputOutputStream io = new InputOutputStream(sock);

        // Receive request and compute
        Boolean isOpen = true;
        while (isOpen) {
            String request = io.read();
            // Check if request is "<blank>"
            if (request.equals(null)) {
                isOpen = false;
                io.close();
                break;
            } else {
                String[] split = request.split(" ");
                // String to Int conversion
                int num1 = Integer.parseInt(split[0]);
                int num2 = Integer.parseInt(split[1]);
                String operator = split[2];

                // New variables
                float result = 0f;
                String soperator = "";

                // Evaluation
                if (operator.equals("add")) {
                    result = (float) (num1 + num2);
                    soperator = "+";

                } else if (operator.equals("div")) {
                    result = (float) num1 / num2;
                    soperator = "/";

                } else if (operator.equals("sub")) {
                    result = (float) (num1 - num2);
                    soperator = "-";

                } else if (operator.equals("mul")) {
                    result = (float) (num1 * num2);
                    soperator = "*";

                } else {
                    System.out.println("Problems detected");
                    isOpen = false;
                    io.close();
                    break;
                }

                // Constructing response
                String response = "The answer for " + split[0] + " " + soperator + " " + split[1] + " is "
                        + Float.toString(result);

                // Send to Client
                io.write(response);
            }
        }
    }
}
