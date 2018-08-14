package core;

import web.WebSocketServer;

public class AideMet {
    public static void main(String[] args) {
        WebSocketServer server = new WebSocketServer();
        server.startSync();
    }
}
