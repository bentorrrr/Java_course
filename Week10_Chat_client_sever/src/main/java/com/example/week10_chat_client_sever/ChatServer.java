package com.example.week10_chat_client_sever;

import java.io.*;
import java.net.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private static final int PORT = 12345;

    private static final Set<PrintWriter> CLIENTS =
            ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("[Server] listening on port " + PORT);

            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private String       name = "anonymous";
        private PrintWriter  out;

        ClientHandler(Socket socket) { this.socket = socket; }

        @Override public void run() {
            try (socket) {
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                CLIENTS.add(out);

                out.println("Enter your name:");
                name = in.readLine();
                broadcast("★ " + name + " joined the chat ★");

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equalsIgnoreCase("/quit")) break;
                    broadcast(name + ": " + line);
                }
            } catch (IOException e) {
                System.err.println("[Server] " + e.getMessage());
            } finally {
                CLIENTS.remove(out);
                broadcast("✖ " + name + " left the chat");
            }
        }

        private static void broadcast(String msg) {
            for (PrintWriter w : CLIENTS) w.println(msg);
        }
    }
}
