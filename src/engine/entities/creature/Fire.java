package engine.entities.creature;

import engine.Handler;
import engine.gfx.Animation;
import engine.gfx.Assests;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fire extends Creature {
    private Animation fire;
    private long lastTime, timer;
    private boolean stopTimer;
    private int damage;
    private int animationIndex;
    private Tank tank;

    public Fire(Handler handler, int animationIndex, float x, float y, Tank tnak) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH / 2, Creature.DEFAULT_CREATURE_HEIGHT / 2);
        bounds.x = 18;
        bounds.y = 15; // 20
        bounds.width = 5;
        bounds.height = 10; //43
        speed = 4f; // 10
        this.animationIndex = animationIndex;
        BufferedImage[] animation = {Assests.fire[animationIndex], Assests.fireSpeed[animationIndex]};
        fire = new Animation(100, animation);
        timer = 0;
        lastTime = System.currentTimeMillis();
        stopTimer = false;
        damage = 1;
        this.tank = tnak;
        rightPosition();
    }

    @Override
    public void tick() {
        moveUp();
        move();
        if (!stopTimer)
            timer();
        if (timer >= 150) {
            stopTimer = true;
            timer = 0;
            fire.tick();
        }
    }

    protected void moveUp() {
        if (animationIndex == 0) { // Up
            yMove = -speed;
            xMove = 0;
        }
        else if (animationIndex == 1) {
            yMove = -speed;
            xMove = speed - 3.3f;
        }
        else if (animationIndex == 2) {
            yMove = -speed;
            xMove = speed - 2.7f;
        }
        else if (animationIndex == 3) {
            yMove = -speed;
            xMove = speed - 1.5f;
        }
        else if (animationIndex == 4) {
            yMove = -speed + 0.5f;
            xMove = speed - 0.5f;
        }
        else if (animationIndex == 5) {
            yMove = -speed + 1;
            xMove = speed;
        }
        else if (animationIndex == 6) {
            yMove = -speed + 2f;
            xMove = speed;
        }
        else if (animationIndex == 7) {
            yMove = -speed + 2.7f;
            xMove = speed;
        }
        else if (animationIndex == 8) {
            yMove = -speed + 3.3f;
            xMove = speed;
        }
        else if (animationIndex == 9) { // Right
            yMove = 0;
            xMove = speed;
        }
        else if (animationIndex == 10) {
            yMove = speed - 3.3f;
            xMove = speed;
        }
        else if (animationIndex == 11) {
            yMove = speed - 2.6f;
            xMove = speed;
        }
        else if (animationIndex == 12) {
            yMove = speed - 1.4f;
            xMove = speed;
        }
        else if (animationIndex == 13) {
            yMove = speed - 0.6f;
            xMove = speed;
        }
        else if (animationIndex == 14) {
            yMove = speed;
            xMove = speed - 0.7f;
        }
        else if (animationIndex == 15) {
            yMove = speed;
            xMove = speed - 1.4f;
        }
        else if (animationIndex == 16) {
            yMove = speed;
            xMove = speed - 2.7f;
        }
        else if (animationIndex == 17) {
            yMove = speed;
            xMove = speed - 3.3f;
        }
        if (animationIndex == 18) { // Down
            yMove = +speed;
            xMove = 0;
        }
        else if (animationIndex == 19) {
            yMove = speed;
            xMove = -speed + 3.3f;
        }
        else if (animationIndex == 20) {
            yMove = speed;
            xMove = -speed + 2.65f;
        }
        else if (animationIndex == 21) {
            yMove = speed;
            xMove = -speed + 1.5f;
        }
        else if (animationIndex == 22) {
            yMove = speed - 0.5f;
            xMove = -speed + 0.5f;
        }
        else if (animationIndex == 23) {
            yMove = speed - 1.2f;
            xMove = -speed;
        }
        else if (animationIndex == 24) {
            yMove = speed - 2f;
            xMove = -speed;
        }
        else if (animationIndex == 25) {
            yMove = speed - 2.7f;
            xMove = -speed;
        }
        else if (animationIndex == 26) {
            yMove = speed - 3.3f;
            xMove = -speed;
        }
        else if (animationIndex == 27) { // Left
            yMove = 0;
            xMove = -speed;
        }
        else if (animationIndex == 28) {
            yMove = -speed + 3.3f;
            xMove = -speed;
        }
        else if (animationIndex == 29) {
            yMove = -speed + 2.6f;
            xMove = -speed;
        }
        else if (animationIndex == 30) {
            yMove = -speed + 1.4f;
            xMove = -speed;
        }
        else if (animationIndex == 31) {
            yMove = -speed + 0.6f;
            xMove = -speed;
        }
        else if (animationIndex == 32) {
            yMove = -speed;
            xMove = -speed + 0.7f;
        }
        else if (animationIndex == 33) {
            yMove = -speed;
            xMove = -speed + 1.4f;
        }
        else if (animationIndex == 34) {
            yMove = -speed;
            xMove = -speed + 2.7f;
        }
        else if (animationIndex == 35) {
            yMove = -speed;
            xMove = -speed + 3.3f;
        }
    }

    protected void rightPosition() {
        y -= 10;
        if (animationIndex == 0) {
            x += tank.getBoundsWidth() / (float)2 + 3;
            y -= 5;
        }
        else if (animationIndex == 1) {
            x += tank.getBoundsWidth() / (float)2 + 8;
            y -= 5;
        }
        else if (animationIndex == 2) {
            x += tank.getBoundsWidth() / (float)2 + 16;
            y -= 5;
        }
        else if (animationIndex == 3){
            x += tank.getBoundsWidth() / (float)2 + 22;
            y -= 2;
        }
        else if (animationIndex == 4){
            x += tank.getBoundsWidth() / (float)2 + 27;
            y -= 0;
        }
        else if (animationIndex == 5){
            x += tank.getBoundsWidth() / (float)2 + 32;
            y += 9;
        }
        else if (animationIndex == 6){
            x += tank.getBoundsWidth() / (float)2 + 35;
            y += 14;
        }
        else if (animationIndex == 7){
            x += tank.getBoundsWidth() / (float)2 + 38;
            y += 19;
        }
        else if (animationIndex == 8){
            x += tank.getBoundsWidth() / (float)2 + 40;
            y += 24;
        }
        else if (animationIndex == 9) {
            x += tank.getBoundsWidth() + 22;
            y += tank.getBoundsHeight() / (float)2 + 14;
        }
        else if (animationIndex == 10) {
            x += tank.getBoundsWidth() + 22;
            y += tank.getBoundsHeight() / (float)2 + 18;
        }
        else if (animationIndex == 11) {
            x += tank.getBoundsWidth() + 21;
            y += tank.getBoundsHeight() / (float)2 + 24;
        }
        else if (animationIndex == 12) {
            x += tank.getBoundsWidth() + 20;
            y += tank.getBoundsHeight() / (float)2 + 34;
        }
        else if (animationIndex == 13) {
            x += tank.getBoundsWidth() + 17;
            y += tank.getBoundsHeight()  / (float)2 + 38;
        }
        else if (animationIndex == 14) {
            x += tank.getBoundsWidth() + 10;
            y += tank.getBoundsHeight() / (float)2 + 46;
        }
        else if (animationIndex == 15) {
            x += tank.getBoundsWidth() + 3;
            y += tank.getBoundsHeight() / (float)2 + 50;
        }
        else if (animationIndex == 16) {
            x += tank.getBoundsWidth() - 3;
            y += tank.getBoundsHeight() / (float)2 + 53;
        }
        else if (animationIndex == 17) {
            x += tank.getBoundsWidth() - 8;
            y += tank.getBoundsHeight() / (float)2 + 56;
        }
        else if (animationIndex == 18) {
            x += tank.getBoundsWidth() / (float)2 + 4;
            y += tank.getBoundsHeight() + 38;
        }

        else if (animationIndex == 19) {
            x += tank.getBoundsWidth() / (float)2 - 2;
            y += tank.getBoundsHeight() + tank.getBoundsY() + 15;
        }
        else if (animationIndex == 20) {
            x += tank.getBoundsWidth() / (float)2 - 11;
            y += tank.getBoundsHeight() + tank.getBoundsY() + 13;
        }
        else if (animationIndex == 21){
            x -= tank.getBoundsWidth() / (float)2 - 18;
            y += tank.getBoundsHeight() + tank.getBoundsY() + 10;
        }
        else if (animationIndex == 22){
            x -= tank.getBoundsWidth() / (float)2 - 10;
            y += tank.getBoundsHeight() + tank.getBoundsY() + 5;
        }
        else if (animationIndex == 23){
            x -= tank.getBoundsWidth() / (float)2 - 4;
            y += tank.getBoundsHeight() + tank.getBoundsY() - 2;
        }
        else if (animationIndex == 24){
            x -= tank.getBoundsWidth() / (float)2;
            y += tank.getBoundsHeight() + tank.getBoundsY() - 6;
        }
        else if (animationIndex == 25){
            x -= tank.getBoundsWidth() / (float)2 + 3;
            y += tank.getBoundsHeight() + tank.getBoundsY() - 13;
        }
        else if (animationIndex == 26){
            x -= tank.getBoundsWidth() / (float)2 + 5;
            y += tank.getBoundsHeight() + tank.getBoundsY() - 22;
        }
        else if (animationIndex == 27) {
            x -= tank.getBoundsWidth() - 13;
            y += tank.getBoundsHeight() / (float)2 + 14;
        }
        else if (animationIndex == 28) {
            x -= tank.getBoundsWidth() - 14;
            y += tank.getBoundsHeight() / (float)2 + 6;
        }
        else if (animationIndex == 29) {
            x -= tank.getBoundsWidth() - 15;
            y += tank.getBoundsHeight() / (float)2;
        }
        else if (animationIndex == 30) {
            x -= tank.getBoundsWidth() - 18;
            y += tank.getBoundsHeight() / (float)2 - 10;
        }
        else if (animationIndex == 31) {
            x -= tank.getBoundsWidth() - 21;
            y += tank.getBoundsHeight() / (float)2 - 17;
        }
        else if (animationIndex == 32) {
            x -= tank.getBoundsWidth() - 30;
            y += tank.getBoundsHeight() / (float)2 - 25;
        }
        else if (animationIndex == 33) {
            x -= tank.getBoundsWidth() - 35;
            y += tank.getBoundsHeight() / (float)2 - 28;
        }
        else if (animationIndex == 34) {
            x -= tank.getBoundsWidth() - 42;
            y += tank.getBoundsHeight() / (float)2 - 31;
        }
        else if (animationIndex == 35) {
            x -= tank.getBoundsWidth() - 51;
            y += tank.getBoundsHeight() / (float)2 - 34;
        }

    }

    private void timer() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void die() {
        active = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(fire.getCurrentFrame(), (int) (x), (int) (y), width, height, null);
    }

    public int getDamage() {
        return damage;
    }

}
