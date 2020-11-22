package networkUDP;

import java.io.Serializable;
import java.net.InetAddress;

public class ClientData implements Serializable {
    private InetAddress ipAddress;
    private int port;
    private boolean inGame;

    public ClientData(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        inGame = false;
    }

    public String getIpAddress() {
        return ipAddress.getHostAddress();
    }

    public int getPort() {
        return port;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
    }

}