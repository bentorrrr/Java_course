package com.example.as03_network_fish_tank.Client;

import com.example.as03_network_fish_tank.Common.Packet;
import com.example.as03_network_fish_tank.Common.Fish;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FishClient extends Application {
    private static final String HOST = "localhost";
    private static final int PORT = 5555;
    private volatile int peerCount = 1;
    private DataOutputStream out;
    private final Gson gson = new GsonBuilder().create();

    @Override
    public void start(Stage stage) throws Exception {
        FishPane pane = new FishPane(this, this::sendTransfer);
        stage.setScene(new Scene(pane, 640, 480));
        stage.setTitle("Fish Tank");
        stage.show();
        Socket sock = new Socket(HOST, PORT);
        out = new DataOutputStream(sock.getOutputStream());
        new NetReceiver(new DataInputStream(sock.getInputStream()), pane).start();
    }

    int getPeerCount() {
        return peerCount;
    }

    private void sendTransfer(Fish f, String dir) {
        try {
            Packet pkt = new Packet("TRANSFER", dir, gson.toJson(f));
            synchronized (out) {
                out.writeUTF(gson.toJson(pkt));
                out.flush();
            }
        } catch (IOException ignored) {}
    }

    private class NetReceiver extends Thread {
        private final DataInputStream in;
        private final FishPane pane;
        NetReceiver(DataInputStream in, FishPane pane) {
            this.in = in;
            this.pane = pane;
            setDaemon(true);
        }
        @Override
        public void run() {
            try {
                while (true) {
                    String json = in.readUTF();
                    Packet pkt = gson.fromJson(json, Packet.class);
                    switch (pkt.type) {
                        case "STATE" -> peerCount = Integer.parseInt(pkt.payload);
                        case "TRANSFER" -> {
                            Fish f = gson.fromJson(pkt.payload, Fish.class);
                            Platform.runLater(() -> pane.spawnFromServer(f, pkt.dir));
                        }
                    }
                }
            } catch (IOException ignored) {}
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
