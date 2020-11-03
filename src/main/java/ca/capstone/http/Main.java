package ca.capstone.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String... args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        int read;
        while ((read = inputStream.read()) != -1) {
            System.out.print((char)read);
        }
    }


}
