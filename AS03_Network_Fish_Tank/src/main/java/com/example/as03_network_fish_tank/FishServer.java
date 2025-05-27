package com.example.as03_network_fish_tank;

import com.example.as03_network_fish_tank.Common.Packet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FishServer {
    private static final int PORT = 5555;
    private final Map<Integer, DataOutputStream> clients = new ConcurrentHashMap<>();
    private final Gson gson = new GsonBuilder().create();
    private int nextId = 0;

    public static void main(String[] args) throws IOException {
        new FishServer().start();
    }

    private void start() throws IOException {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                Socket s = ss.accept();
                int id = nextId++;
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                clients.put(id, out);
                sendStateToAll();
                new ClientHandler(id, s).start();
            }
        }
    }

    private class ClientHandler extends Thread {
        private final int id;
        private final Socket socket;

        ClientHandler(int id, Socket socket) {
            this.id = id;
            this.socket = socket;
            setDaemon(true);
        }

        @Override
        public void run() {
            try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
                while (true) {
                    String json = in.readUTF();
                    Packet pkt = gson.fromJson(json, Packet.class);
                    if ("TRANSFER".equals(pkt.type)) relay(pkt, id);
                }
            } catch (IOException ignored) {
            } finally {
                clients.remove(id);
                sendStateToAll();
                try { socket.close(); } catch (IOException ignored) {}
            }
        }
    }

    private void relay(Packet pkt, int fromId) {
        if (clients.size() < 2) return;
        Integer[] ids = clients.keySet().toArray(Integer[]::new);
        java.util.Arrays.sort(ids);
        int idx = java.util.Arrays.binarySearch(ids, fromId);
        int destIdx = "LEFT".equals(pkt.dir) ? (idx - 1 + ids.length) % ids.length : (idx + 1) % ids.length;
        int destId = ids[destIdx];
        DataOutputStream out = clients.get(destId);
        if (out != null) {
            try { synchronized (out) { out.writeUTF(gson.toJson(pkt)); out.flush(); } }
            catch (IOException ignored) {}
        }
    }

    private void sendStateToAll() {
        Packet state = new Packet("STATE", null, String.valueOf(clients.size()));
        String js = gson.toJson(state);
        for (DataOutputStream o : clients.values()) {
            try { synchronized (o) { o.writeUTF(js); o.flush(); } }
            catch (IOException ignored) {}
        }
    }
}
