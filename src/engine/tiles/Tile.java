package engine.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tile implements Serializable {
    public static Tile[] tiles = new Tile[256];

    static {
        new DirtTile(0);
        new GrassTile(1);
        new RockTile(2);
    }

    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
    protected transient BufferedImage image;
    protected final int id;

    public Tile (BufferedImage image, int id) {
        this.image = image;
        this.id = id;
        tiles[id] = this;
    }

    public void render (Graphics g, int x, int y) {
        g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid () {
        return false;
    }

}