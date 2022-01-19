package com.basic;

import java.io.IOException;
import java.net.Socket;

public class ClientLoader {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8080);
        Client client = new Client(clientSocket);
        client.process();
    }
}
