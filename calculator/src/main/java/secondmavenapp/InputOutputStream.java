package secondmavenapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class InputOutputStream {
    // Members
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    // Constructor
    public InputOutputStream(Socket s) throws IOException {
        is = s.getInputStream();
        dis = new DataInputStream(is);
        os = s.getOutputStream();
        dos = new DataOutputStream(os);
    }

    // Methods
    public void close() throws IOException {
        is.close();
        os.close();
    }

    public String read() throws IOException {
        return dis.readUTF();
    }

    public void write(String res) throws IOException {
        dos.writeUTF(res);
        dos.flush();
    }
}
