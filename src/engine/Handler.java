package engine;

import engine.worlds.World;

import java.io.Serializable;

public class Handler implements Serializable {
    private transient Game game;
    private transient World world;

    public Handler(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}