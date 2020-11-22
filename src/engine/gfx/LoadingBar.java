package engine.gfx;

import engine.Handler;
import engine.rendering.Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class LoadingBar extends Thread implements Rendering {
    private transient BufferedImage image;
    private Handler handler;
    private int width = 50;
    private boolean complete;

    public LoadingBar(Handler handler, BufferedImage image) {
        this.image = image;
        complete = false;
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, 200, 700, width, 20, null);
    }

    @Override
    public void run() {
        while (width < 900) {
            width += 50;
            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        complete = true;
        handler.getGame().getRender().removeObject(this);
    }

    public boolean isComplete() {
        return complete;
    }

}