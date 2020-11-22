package engine.entities.creature;

import engine.Handler;
import engine.entities.Entity;
import engine.tiles.Tile;

public abstract class Creature extends Entity {
    public static final float DEFAULT_SPEED = 6.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 84, DEFAULT_CREATURE_HEIGHT = 84;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move () {
        moveX();
        moveY();
    }

    public void moveX () {
        int newX;
        if (xMove > 0)
            newX = (int)(x + bounds.x + bounds.width + xMove) / Tile.TILE_WIDTH;
        else if (xMove < 0)
            newX = (int)(x + bounds.x + xMove) / Tile.TILE_WIDTH;
        else
            return;

        if (!collisionWithTile( newX, (int)(y + bounds.y)/Tile.TILE_HEIGHT))
            x += xMove;
        else if (this instanceof Fire)
            this.die();
    }

    public void moveY () {
        int newY;
        if (yMove < 0)
            newY = (int)(y + bounds.y + yMove) / Tile.TILE_HEIGHT;
        else if (yMove > 0)
            newY = (int)(y + bounds.y + yMove + bounds.height) / Tile.TILE_HEIGHT;
        else
            return;

        if (!collisionWithTile( (int)(x + bounds.x)/Tile.TILE_WIDTH, newY))
            y += yMove;
        else if (this instanceof Fire)
            this.die();
    }

    protected boolean collisionWithTile (int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

}