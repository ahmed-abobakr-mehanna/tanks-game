package engine.entities;

import engine.Handler;
import engine.entities.creature.Computer;
import engine.entities.creature.Fire;
import engine.entities.creature.Tank;
import engine.rendering.Rendering;

import java.awt.*;
import java.io.Serializable;

public abstract class Entity extends Thread implements Rendering, Serializable {
    public static final int DEFAULT_HEALTH = 10;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    public boolean active = true;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();
    public abstract void die();

    public void hurt(Fire fire) {
        health -= fire.getDamage();
        fire.die();
        if (health <= 0)
            die();
    }

    public void checkEntityCollision () {
        for (int i = 0; i < handler.getWorld().getEntityManager().getEntities().size(); i++)
            if (handler.getWorld().getEntityManager().getEntities().get(i).getCollisionBounds().intersects(getCollisionBounds()))
                // 'this' is instanceof Tank
                this.hurt(((Fire) handler.getWorld().getEntityManager().getEntities().get(i)));

        for (int i = 0; i < handler.getGame().getRender().getObjects().size(); i++) {
            if (handler.getGame().getRender().getObjects().get(i).equals(this))
                continue;
            if (handler.getGame().getRender().getObjects().get(i) instanceof Tank || handler.getGame().getRender().getObjects().get(i) instanceof Computer)
                if (((Tank) handler.getGame().getRender().getObjects().get(i)).getCollisionBounds().intersects(getCollisionBounds()))
                    this.die();
        }

    }

    public Rectangle getCollisionBounds () {
        return new Rectangle((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public boolean isActive() {
        return active;
    }

    public int getBoundsX() {
        return bounds.x;
    }

    public int getBoundsY() {
        return bounds.y;
    }

    public int getBoundsWidth() {
        return bounds.width;
    }

    public int getBoundsHeight() {
        return bounds.height;
    }
}
