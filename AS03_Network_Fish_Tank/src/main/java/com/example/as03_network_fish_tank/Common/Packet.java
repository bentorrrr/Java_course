package com.example.as03_network_fish_tank.Common;

public class Packet {
    public String type;
    public String dir;
    public String payload;

    public Packet() {}

    public Packet(String type, String dir, String payload) {
        this.type = type;
        this.dir = dir;
        this.payload = payload;
    }
}
