package networkTCP;

import engine.entities.creature.Tank;
import networkUDP.ClientData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class GameServer extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ArrayList<ClientData[]> playersIpAddress;
    private ArrayList<ClientData[]> playersInGame;
    private ArrayList<ClientData> waitingEnemy;
    private Random random;

    public GameServer() {
        try {
            serverSocket = new ServerSocket(1331);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Object object = receive();

//            if (object instanceof String) {
//                String message = (String) object;
//                System.out.println("CLIENT [ " + socket.getInetAddress().getHostAddress() + " : " + socket.getPort() + " ] >> " + message.trim());
//                if (message.equalsIgnoreCase("start"))
//                    sendObject("create game", socket.getInetAddress(), socket.getPort());
//                else if (message.equalsIgnoreCase("play online"))
//                    sendObject("create your tank", socket.getInetAddress(), socket.getPort());
//                else if (message.equalsIgnoreCase("get my ip and port")) {
//                    ClientData clientData = new ClientData(socket.getInetAddress(), socket.getPort());
//                    sendObject(clientData, socket.getInetAddress(), socket.getPort());
//                }
//                else if (message.equalsIgnoreCase("get enemy tank")) {
//                    ClientData clientData = new ClientData(socket.getInetAddress(), socket.getPort());
//                    waitingEnemy.add(clientData);
//                }
//                else if (message.equalsIgnoreCase("update enemy tank"))
//                    updateEnemyTank(socket.getInetAddress().getHostAddress());
//                else if (message.equalsIgnoreCase("fire"))
//                    updateGame(socket.getInetAddress().getHostAddress(), "enemy fire");
//                else if (message.equalsIgnoreCase("lose"))
//                    updateGame(socket.getInetAddress().getHostAddress(), "you are winner");
//            }
//            else if (object instanceof Tank) {
//                if (!isInGame(((Tank) object).getIpAddress()))
//                    newOnlineGame((Tank) object);
//                else
//                    updateGame((Tank) object);
//            }
//            chooseRandomPlayers();
            System.out.println(object);
            if (object.toString().equals("start"))
                sendObject("server", socket.getInetAddress(), socket.getPort());
        }
    }

    private void updateEnemyTank(String ipAddress) {
        for (int i = 0; i < playersInGame.size(); i++) {
            if (playersInGame.get(i)[0].getIpAddress().equals(ipAddress)) {
                try {
                    sendObject("send your tank", Inet4Address.getByName(playersInGame.get(i)[1].getIpAddress()), playersInGame.get(i)[1].getPort());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
            else if (playersInGame.get(i)[1].getIpAddress().equals(ipAddress)) {
                try {
                    sendObject("send your tank", Inet4Address.getByName(playersInGame.get(i)[0].getIpAddress()), playersInGame.get(i)[0].getPort());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private boolean isInGame(String ipAddress) {
        for (int i = 0; i < playersInGame.size(); i++)
            for (int j = 0; j < 2; j++)
                if (playersInGame.get(i)[j].getIpAddress().equals(ipAddress))
                    return true;
        return false;
    }

    private void updateGame(String ipAddress, String message) {
        for (int i = 0; i < playersInGame.size(); i++) {
            if (playersInGame.get(i)[0].getIpAddress().equals(ipAddress)) {
                try {
                    sendObject(message, Inet4Address.getByName(playersInGame.get(i)[1].getIpAddress()), playersInGame.get(i)[1].getPort());
                    if (message.equals("you are winner"))
                        playersInGame.remove(i);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
            else if (playersInGame.get(i)[1].getIpAddress().equals(ipAddress)) {
                try {
                    sendObject(message, Inet4Address.getByName(playersInGame.get(i)[0].getIpAddress()), playersInGame.get(i)[0].getPort());
                    if (message.equals("you are winner"))
                        playersInGame.remove(i);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private void updateGame(Tank tank) {
        for (int i = 0; i < playersInGame.size(); i++) {
            if (playersInGame.get(i)[0].getIpAddress().equals(tank.getIpAddress())) {
                try {
                    sendObject(tank, Inet4Address.getByName(playersInGame.get(i)[1].getIpAddress()), playersInGame.get(i)[1].getPort());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
            else if (playersInGame.get(i)[1].getIpAddress().equals(tank.getIpAddress())) {
                try {
                    sendObject(tank, Inet4Address.getByName(playersInGame.get(i)[0].getIpAddress()), playersInGame.get(i)[0].getPort());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private void newOnlineGame(Tank tank) {
        for (int i = 0; i < playersIpAddress.size(); i++) {
            if (playersIpAddress.get(i)[0].getIpAddress().equals(tank.getIpAddress()) && !playersIpAddress.get(i)[1].isInGame()) {
                try {
                    sendObject(tank, Inet4Address.getByName(playersIpAddress.get(i)[1].getIpAddress()), playersIpAddress.get(i)[1].getPort());
                    playersIpAddress.get(i)[1].setInGame(true);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
            else if (playersIpAddress.get(i)[1].getIpAddress().equals(tank.getIpAddress()) && !playersIpAddress.get(i)[0].isInGame()){
                try {
                    sendObject(tank, Inet4Address.getByName(playersIpAddress.get(i)[0].getIpAddress()), playersIpAddress.get(i)[0].getPort());
                    playersIpAddress.get(i)[0].setInGame(true);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
            if (playersIpAddress.get(i)[0].isInGame() && playersIpAddress.get(i)[1].isInGame()) {
                playersInGame.add(playersIpAddress.get(i));
                playersIpAddress.remove(i);
            }
        }
    }

    private void chooseRandomPlayers(){
        if (waitingEnemy.size() < 2)
            return;
        System.out.println("choose random player method");
        ClientData[] ips = new ClientData[2];

        int playerIndex = random.nextInt(waitingEnemy.size());
        ips[0] = waitingEnemy.get(playerIndex);
        try {
            sendObject("send your tank", Inet4Address.getByName(waitingEnemy.get(playerIndex).getIpAddress()), waitingEnemy.get(playerIndex).getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        waitingEnemy.remove(playerIndex);

        playerIndex = random.nextInt(waitingEnemy.size());
        ips[1] = waitingEnemy.get(playerIndex);
        try {
            sendObject("send your tank", Inet4Address.getByName(waitingEnemy.get(playerIndex).getIpAddress()), waitingEnemy.get(playerIndex).getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        waitingEnemy.remove(playerIndex);

        playersIpAddress.add(ips);
    }

    private Object receive() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendObject(Object object, InetAddress ipAddress, int port) {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(object);
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}