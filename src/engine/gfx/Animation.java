package engine.gfx;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Animation implements Serializable {
    private int speed, index;
    private long lastTime, timer;
    private transient BufferedImage[] frames;

    public Animation (int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick (boolean right) {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            timer = 0;
            if (right && index + 1 < frames.length)
                index++;
            else if (!right && index - 1 >= 0)
                index--;
            else if ( right && index + 1 >= frames.length)
                this.index = 0;
            else if (!right && index - 1 < 0)
                index = frames.length - 1;
        }
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            timer = 0;
            if (++index >= frames.length)
                index = 0;
        }
    }

    public BufferedImage getCurrentFrame () {
        return frames[index];
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
