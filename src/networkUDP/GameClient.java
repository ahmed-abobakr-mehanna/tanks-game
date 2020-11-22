package networkUDP;

import engine.Game;
import engine.entities.creature.Fire;
import engine.entities.creature.Tank;

import java.io.*;
import java.net.*;

public class GameClient extends Thread {
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] data;
    private final int bufferSize = 6400;
    private ByteArrayInputStream bais;
    private ObjectInputStream ois;
    private ByteArrayOutputStream baos;
    private ObjectOutputStream oos;
    private Game game;

    public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.ipAddress = Inet4Address.getByName(ipAddress);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        while (true) {
            data = new byte[bufferSize];
            packet = new DatagramPacket(data, data.length);
            Object object = receive();
            if (object instanceof String) {
                String message = (String)object;
                System.out.println("SERVER >> " + message);
                if (message.equalsIgnoreCase("create game")) {
                    game = new Game("Tankies", 1280, 832);
                    game.setClient(this);
                    game.start();
                    try {
                        sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if (message.equalsIgnoreCase("create your tank")) {
                    game.getMultiplayerState().setMyTank(new Tank(game.getHandler(), game.getHandler().getWorld().getSpawnX(), game.getHandler().getWorld().getSpawnY()));
                    game.getMultiplayerState().getMyTank().setUpdateEnemyTank(true);
                    game.getRender().addObject(game.getMultiplayerState().getMyTank());
                    game.getMultiplayerState().getMyTank().start();
                    sendObject("get my ip and port");
                }
                else if (message.equalsIgnoreCase("send your tank"))
                    sendObject(game.getMultiplayerState().getMyTank());
                else if (message.equalsIgnoreCase("enemy fire")) {
                    game.getHandler().getWorld().getEntityManager().getEntities().add(new Fire(game.getMultiplayerState().getPlayerTank().getHandler(), game.getMultiplayerState().getPlayerTank().getRotateAnimationIndex(), game.getMultiplayerState().getPlayerTank().getX(), game.getMultiplayerState().getPlayerTank().getY(), game.getMultiplayerState().getPlayerTank()));
                    game.getSoundEffect().playSound();
                }
                else if (message.equalsIgnoreCase("you are winner")) {
                    game.getMultiplayerState().getPlayerTank().die();
                    System.out.println("You Are Winner");
                    game.getMultiplayerState().getMyTank().setEndGame(true);
                }
            }
            else if (object instanceof ClientData) {
                game.getMultiplayerState().getMyTank().setIpAddress(((ClientData) object).getIpAddress());
                sendObject("get enemy tank");
            }
            else if (object instanceof Tank) {
                if (game.getMultiplayerState().getPlayerTank() == null) {
                    game.getMultiplayerState().setPlayerTank((Tank) object);
                    game.getMultiplayerState().getPlayerTank().setUpdateEnemyTank(false);
                    game.getMultiplayerState().getPlayerTank().setHandler(game.getHandler());
                    game.getMultiplayerState().getPlayerTank().updateAnimations();
                    game.getRender().addObject(game.getMultiplayerState().getPlayerTank());
                    game.getMultiplayerState().getPlayerTank().checkEnemyCollision();
                }
                else {
                    game.getMultiplayerState().getPlayerTank().updateOnlineTank((Tank) object);
                    game.getMultiplayerState().getPlayerTank().setHandler(game.getHandler());
                }
            }
        }
    }

    private Object receive() {
        bais = new ByteArrayInputStream(data);
        try {
            socket.receive(packet);
            ois = new ObjectInputStream(new BufferedInputStream(bais));
            return ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            this.socket.send(packet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) {
        baos = new ByteArrayOutputStream(bufferSize);
        oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        sendData(baos.toByteArray());
    }

    public Tank getEnemyTank() {
        return game.getMultiplayerState().getPlayerTank();
    }
}
