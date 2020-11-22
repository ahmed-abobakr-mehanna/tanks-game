package networkTCP;

import java.io.*;
import java.net.Socket;

public class GameClient extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public GameClient(String ipAddress) {
        try {
            socket = new Socket(ipAddress, 1331);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        while (true) {
            Object object = receive();
            if (object == null)
                continue;
            System.out.println(object.toString());
        }
    }

    private Object receive() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            Object object = ois.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        }
        return null;
    }

    public void sendObject(Object object) {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}