package engine.gfx;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SpriteSheet implements Serializable {
    private transient BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}