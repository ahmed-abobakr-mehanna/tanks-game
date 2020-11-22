package engine.worlds;

import engine.Handler;
import engine.entities.EntityManager;
import engine.rendering.Rendering;
import engine.tiles.Tile;
import engine.utils.Utils;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

public class World extends Thread implements Rendering, Serializable {
    private Handler handler;
    private EntityManager entityManager;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler);
        loadWorld(path);
    }

    public void tick() {
        entityManager.tick();
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0;i < width; i++)
            for (int j = 0; j < height; j++)
                getTile(i, j).render(g, i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT);

        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        return t;
    }

    public void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);
        spawnX = Integer.parseInt(tokens[2]);
        spawnY = Integer.parseInt(tokens[3]);
        tiles = new int[width][height];

        int i = 0, j = 0;
        for (int index = 4; index < tokens.length; index++) {
            tiles[i++][j] = Integer.parseInt(tokens[index]);
            if (i == width) {
                i = 0;
                j++;
            }
        }
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000 / fps;
        while (handler.getGame().isRunning()) {
            tick();
            try {
                sleep((long) timePerTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}