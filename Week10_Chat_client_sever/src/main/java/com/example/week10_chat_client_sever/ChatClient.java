package com.example.week10_chat_client_sever;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    private static final String HOST = "localhost";
    private static final int    PORT = 12345;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader netIn  = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter    netOut = new PrintWriter(
                     socket.getOutputStream(), true);
             BufferedReader kbIn   = new BufferedReader(
                     new InputStreamReader(System.in))) {

            Thread listener = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = netIn.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("[Client] connection closed");
                }
            });
            listener.setDaemon(true);
            listener.start();

            String line;
            while ((line = kbIn.readLine()) != null) {
                netOut.println(line);
                if (line.equalsIgnoreCase("/quit")) break;
            }
        }
    }
}
