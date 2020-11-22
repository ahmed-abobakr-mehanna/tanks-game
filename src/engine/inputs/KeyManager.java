package engine.inputs;

import engine.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class KeyManager extends Thread implements KeyListener, Serializable {
    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean m;
    private final int KEYS_NUMBER = 256;

    private Handler handler;

    public KeyManager (Handler handler) {
        keys = new boolean[KEYS_NUMBER];
        this.handler = handler;
    }

    public void tick () {
        up = keys[KeyEvent.VK_W]; // move forward
        down = keys[KeyEvent.VK_S]; // move backward
        left = keys[KeyEvent.VK_A]; // rotate anti clockwise
        right = keys[KeyEvent.VK_D]; // rotate clockwise

        m = keys[KeyEvent.VK_M]; // fire
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        System.out.println("Released!");
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000 / fps;
        while(handler.getGame().isRunning()) {
            tick();
            try {
                sleep((long) timePerTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}