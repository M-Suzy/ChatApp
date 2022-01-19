package com.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    final ServerSocket serverSocket ;
    final Socket clientSocket ;
    final BufferedReader in;
    final PrintWriter out;
    final Scanner sc=new Scanner(System.in);


    public Server(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream());
    }
    public void process(){
        Thread sender = new Thread(()->{
            String msg;
            while(true){
                msg = sc.nextLine();
                out.println(msg);
                out.flush();
            }
        });
        sender.start();
        Thread receiver = new Thread(()->{
            String msg;
            try {
                msg = in.readLine();
                while(msg!=null){
                    System.out.println("Client : "+ msg);
                    msg = in.readLine();
                }

                System.out.println("Client left.");

                out.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("IO error "+ e.getMessage());
            }
        });
        receiver.start();
    }
}
