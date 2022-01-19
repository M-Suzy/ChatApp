package com.basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLoader {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = new Socket("127.0.0.1", 8080);
        Server server = new Server(serverSocket, clientSocket);
        server.process();
    }
}
