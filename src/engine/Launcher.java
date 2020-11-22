package engine;

import networkUDP.GameClient;
import networkUDP.GameServer;

public class Launcher {
    private static Game game;
    public static void main(String[] args) throws InterruptedException {
//        game = new Game("title", 1280, 832);
//        game.start();

        new GameServer();
        new GameClient("127.0.1.1").sendObject("start");

//        new GameServer();
//        new GameClient("127.0.0.1").sendObject("start");
    }
}