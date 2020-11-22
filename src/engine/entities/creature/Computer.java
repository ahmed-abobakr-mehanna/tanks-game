package engine.entities.creature;

import engine.Handler;
import engine.gfx.Assests;

import java.awt.*;

public class Computer extends Tank {
    private int animationIndex;
    private Tank trackingTank;

    public Computer(Handler handler, float x, float y) {
        super(handler, x, y);
        Tank.getTankes().remove(this);
//        animationIndex = rotateAnimation.getIndex();
        trackingTank = Tank.getTankes().get(0);
        rotateAnimation.setIndex(0);
    }

    @Override
    protected void getInput() {
        xMove = 0;
        yMove = 0;
        simpleAI();
        fire();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) (x), (int) (y), width, height, null);
        if (!active && trackingTank.isActive())
            g.drawImage(Assests.playerStatus[1], 414, 386, null);
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000 / fps;
        while(active && !endGame) {
            tick();
            if (trackingTank.endGame)
                endGame = true;
            try {
                sleep((long) timePerTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handler.getGame().getRender().removeObject(this);
        trackingTank.endGame  = true;
    }

    private void moveAI() {
        float tankStartX, tankEndX, tankStartY, tankEndY, compStartX, compEndX, compStartY, compEndY;

        tankStartX = trackingTank.getX() + trackingTank.getBoundsX();
        tankEndX = tankStartX + trackingTank.getBoundsWidth();
        tankStartY = trackingTank.getY() + trackingTank.getBoundsY();
        tankEndY = tankStartY + trackingTank.getBoundsHeight();

        compStartX = this.getX() + this.getBoundsX();
        compEndX = compStartX + this.getBoundsWidth();
        compStartY = this.getY() + this.getBoundsY();
        compEndY = compStartY + this.getBoundsHeight();

        int distanceBetweenTanks = 90;

        if (getX() > trackingTank.getX() && ((tankEndY >= compStartY && tankEndY <= compEndY) || (tankStartY >= compStartY && tankStartY <= compEndY)) && getX() - trackingTank.getX() <= distanceBetweenTanks)
            return;
        if (trackingTank.getX() > getX() && ((tankEndY >= compStartY && tankEndY <= compEndY) || (tankStartY >= compStartY && tankStartY <= compEndY)) && trackingTank.getX() - getX() <= distanceBetweenTanks)
            return;
        if (getY() > trackingTank.getY() && ((tankEndX >= compStartX && tankEndX <= compEndX) || (tankStartX >= compStartX && tankStartX <= compEndX)) && getY() - trackingTank.getY() <= distanceBetweenTanks)
            return;
        if (trackingTank.getY() > getY() && ((tankEndX >= compStartX && tankEndX <= compEndX) || (tankStartX >= compStartX && tankStartX <= compEndX)) && trackingTank.getY() - getY() <= distanceBetweenTanks)
            return;

        if (rotateAnimation.getIndex() == animationIndex)
            moveUp();

    }

    private void getRightAnimationIndex() {
        float tankStartX, tankEndX, tankStartY, tankEndY, compStartX, compEndX, compStartY, compEndY;

        tankStartX = trackingTank.getX() + trackingTank.getBoundsX();
        tankEndX = tankStartX + trackingTank.getBoundsWidth();
        tankStartY = trackingTank.getY() + trackingTank.getBoundsY();
        tankEndY = tankStartY + trackingTank.getBoundsHeight();

        compStartX = this.getX() + this.getBoundsX();
        compEndX = compStartX + this.getBoundsWidth();
        compStartY = this.getY() + this.getBoundsY();
        compEndY = compStartY + this.getBoundsHeight();

        float distance;

        if (((tankEndX >= compStartX && tankEndX <= compEndX) || (tankStartX >= compStartX && tankStartX <= compEndX)) && tankEndY < compStartY) {
            if (tankEndX >= compStartX && tankEndX <= compEndX) {
                distance = tankEndX - compStartX;
                System.out.println("Distance1: " + distance);
                if (distance <= 10)
                    animationIndex = 33;
                else if (distance > 10 && distance <= 20)
                    animationIndex = 34;
                else if (distance > 20 && distance <= 30)
                    animationIndex = 35;
                else if (distance > 30 && distance <= 40)
                    animationIndex = 0;
            }
            else {
                distance = compEndX - tankStartX;
                System.out.println("Distance2: " + distance);
                if (distance >= 20 && distance < 30)
                    animationIndex = 1;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 2;
                else if (distance >= 0 && distance < 10)
                    animationIndex = 3;
            }
        }
        else if (((tankEndY >= compStartY && tankEndY <= compEndY) || (tankStartY >= compStartY && tankStartY <= compEndY) ) && compEndX < tankStartX) {
            if (tankEndY >= compStartY && tankEndY <= compEndY) {
                distance = compEndY - tankEndY;
                System.out.println("Distance3: " + distance);
                if (distance >= 30 && distance < 40)
                    animationIndex = 4;
                else if (distance >= 20 && distance < 30)
                    animationIndex = 5;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 6;
                else if (distance >= 0 && distance < 10)
                    animationIndex = 7;
            }
            else {
                distance = tankStartY - compStartY;
                System.out.println("Distance4: " + distance);
                if (distance >= 0 && distance < 10)
                    animationIndex = 9;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 10;
                else if (distance >= 20 && distance < 30)
                    animationIndex = 11;
                else if (distance >= 30 && distance < 40)
                    animationIndex = 12;
            }
        }
        else if (((tankEndX >= compStartX && tankEndX <= compEndX) || (tankStartX >= compStartX && tankStartX <= compEndX)) && tankStartY > compEndY) {
            if (tankEndX >= compStartX && tankEndX <= compEndX) {
                distance = tankEndX - compStartX;
                System.out.println("Distance5: " + distance);
                if (distance <= 10)
                    animationIndex = 22;
                else if (distance > 10 && distance <= 20)
                    animationIndex = 21;
                else if (distance > 20 && distance <= 30)
                    animationIndex = 20;
                else if (distance > 30 && distance <= 40)
                    animationIndex = 19;
            }
            else {
                distance = compEndX - tankStartX;
                System.out.println("Distance6: " + distance);
                if (distance >= 30 && distance < 40)
                    animationIndex = 18;
                if (distance >= 20 && distance < 30)
                    animationIndex = 17;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 16;
                else if (distance >= 0 && distance < 10)
                    animationIndex = 15;
            }
        }
        else if (((tankEndY >= compStartY && tankEndY <= compEndY) || (tankStartY >= compStartY && tankStartY <= compEndY)) && tankEndX < compStartX) {
            if (tankEndY <= compEndY && tankEndY <= compEndY) {
                distance = compEndY - tankEndY;
                System.out.println("Distance7: " + distance);
                if (distance >= 30 && distance < 40)
                    animationIndex =31;
                else if (distance >= 20 && distance < 30)
                    animationIndex = 30;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 29;
                else if (distance >= 0 && distance < 10)
                    animationIndex = 28;
            }
            else {
                distance = tankStartY - compStartY;
                System.out.println("Distance8: " + distance);
                if (distance >= 0 && distance < 10)
                    animationIndex = 27;
                else if (distance >= 10 && distance < 20)
                    animationIndex = 26;
                else if (distance >= 20 && distance < 30)
                    animationIndex = 25;
                else if (distance >= 30 && distance < 40)
                    animationIndex = 24;
            }
        }
    }

    private void rotateTank() {
        if (rotateAnimation.getIndex() != animationIndex) {
            int stepsRight, stepsLeft;
            if (animationIndex > rotateAnimation.getIndex()) {
                stepsRight = animationIndex - rotateAnimation.getIndex();
                stepsLeft = 35 - animationIndex + rotateAnimation.getIndex() + 1;
            }
            else {
                stepsRight = 35 - rotateAnimation.getIndex() + animationIndex + 1;
                stepsLeft = rotateAnimation.getIndex() - animationIndex;
            }
            if (stepsRight < stepsLeft)
                rotateAnimation.tick(true);
            else
                rotateAnimation.tick(false);
        }
    }

    private void simpleAI() {
        getRightAnimationIndex();
        rotateTank();
        moveAI();
    }

}