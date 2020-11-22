package engine.entities.creature;

import engine.Handler;
import engine.gfx.Animation;
import engine.gfx.Assests;
import engine.ui.UIImageButton;
import engine.ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Tank extends Creature {
    protected Random random;
    private static ArrayList<Tank> tanks = new ArrayList<>();
    protected Animation rotateAnimation, dropAnimation;
    private UIManager uiManager;
    protected int fireRate, rotateSpeed, dropAnimationSpeed;
    protected long lastTime, timer, timer2;
    protected String ipAddress;
    private boolean updateEnemyTank;
    protected boolean endGame;

    public Tank(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        ipAddress = null;
        uiManager= new UIManager();
        endGame = false;
        speed = 3;
        tanks.add(this);
        bounds.x = 23;
        bounds.y = 23; // 20
        bounds.width = 37;
        bounds.height = 37; //43
        rotateSpeed = 50;
        rotateAnimation = new Animation(rotateSpeed, Assests.tankRotate);
        random = new Random();
        rotateAnimation.setIndex(random.nextInt(36));
        dropAnimationSpeed = 150;
        dropAnimation = new Animation(dropAnimationSpeed, Assests.tankDrop);
        timer = 0;
        lastTime = System.currentTimeMillis();
        fireRate = 250;
        updateEnemyTank = false;
//        rotateAnimation.setIndex(0);
    }

    @Override
    public void tick() {
        timer();
        getInput();
        move();
        checkEntityCollision();
        if (updateEnemyTank && timer2 >= (1000/60) && handler.getGame().getClient().getEnemyTank() != null) {
            timer2 = 0;
            try {
                handler.getGame().getClient().sendObject(this);
            }
            catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }
    }

    protected void getInput () {
        xMove = 0;
        yMove = 0;
        if (handler.getGame().getKeyManager().up)
            moveUp();
        else if (handler.getGame().getKeyManager().down)
            moveDown();
        if (handler.getGame().getKeyManager().left)
            rotateAnimation.tick(false);
        else if (handler.getGame().getKeyManager().right)
            rotateAnimation.tick(true);
        if (handler.getGame().getKeyManager().m)
            fire();
    }

    protected void moveUp() {
        if (rotateAnimation.getIndex() == 0) { // Up
            yMove = -speed;
            xMove = 0;
        }
        else if (rotateAnimation.getIndex() == 1) {
            yMove = -speed;
            xMove = speed - 2.5f;
        }
        else if (rotateAnimation.getIndex() == 2) {
            yMove = -speed;
            xMove = speed - 2f;
        }
        else if (rotateAnimation.getIndex() == 3) {
            yMove = -speed;
            xMove = speed - 1;
        }
        else if (rotateAnimation.getIndex() == 4) {
            yMove = -speed;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 5) {
            yMove = -speed + 1;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 6) {
            yMove = -speed + 1.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 7) {
            yMove = -speed + 2;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 8) {
            yMove = -speed + 2.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 9) { // Right
            yMove = 0;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 10) {
            yMove = speed - 2.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 11) {
            yMove = speed - 2f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 12) {
            yMove = speed - 1;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 13) {
            yMove = speed - 0.4f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 14) {
            yMove = speed;
            xMove = speed - 0.6f;
        }
        else if (rotateAnimation.getIndex() == 15) {
            yMove = speed;
            xMove = speed - 1f;
        }
        else if (rotateAnimation.getIndex() == 16) {
            yMove = speed;
            xMove = speed - 2;
        }
        else if (rotateAnimation.getIndex() == 17) {
            yMove = speed;
            xMove = speed - 2.5f;
        }
        if (rotateAnimation.getIndex() == 18) { // Down
            yMove = +speed;
            xMove = 0;
        }
        else if (rotateAnimation.getIndex() == 19) {
            yMove = speed;
            xMove = -speed + 2.5f;
        }
        else if (rotateAnimation.getIndex() == 20) {
            yMove = +speed;
            xMove = -speed + 2f;
        }
        else if (rotateAnimation.getIndex() == 21) {
            yMove = speed;
            xMove = -speed + 1;
        }
        else if (rotateAnimation.getIndex() == 22) {
            yMove = speed;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 23) {
            yMove = speed - 1;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 24) {
            yMove = speed - 1.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 25) {
            yMove = speed - 2;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 26) {
            yMove = speed - 2.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 27) { // Left
            yMove = 0;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 28) {
            yMove = -speed + 2.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 29) {
            yMove = -speed + 2f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 30) {
            yMove = -speed + 1;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 31) {
            yMove = -speed + 0.4f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 32) {
            yMove = -speed;
            xMove = -speed + 0.6f;
        }
        else if (rotateAnimation.getIndex() == 33) {
            yMove = -speed;
            xMove = -speed + 1f;
        }
        else if (rotateAnimation.getIndex() == 34) {
            yMove = -speed;
            xMove = -speed + 2;
        }
        else if (rotateAnimation.getIndex() == 35) {
            yMove = -speed;
            xMove = -speed + 2.5f;
        }
    }

    protected void moveDown() {
        if (rotateAnimation.getIndex() == 0) { // Up
            yMove = speed;
            xMove = 0;
        }
        else if (rotateAnimation.getIndex() == 1) {
            yMove = speed;
            xMove = -speed + 2.5f;
        }
        else if (rotateAnimation.getIndex() == 2) {
            yMove = speed;
            xMove = -speed + 2f;
        }
        else if (rotateAnimation.getIndex() == 3) {
            yMove = speed;
            xMove = -speed + 1;
        }
        else if (rotateAnimation.getIndex() == 4) {
            yMove = speed;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 5) {
            yMove = speed - 1;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 6) {
            yMove = speed - 1.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 7) {
            yMove = speed - 2;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 8) {
            yMove = speed - 2.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 9) { // Right
            yMove = 0;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 10) {
            yMove = -speed + 2.5f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 11) {
            yMove = -speed + 2f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 12) {
            yMove = -speed + 1;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 13) {
            yMove = -speed + 0.4f;
            xMove = -speed;
        }
        else if (rotateAnimation.getIndex() == 14) {
            yMove = -speed;
            xMove = -speed + 0.6f;
        }
        else if (rotateAnimation.getIndex() == 15) {
            yMove = -speed;
            xMove = -speed + 1f;
        }
        else if (rotateAnimation.getIndex() == 16) {
            yMove = -speed;
            xMove = -speed + 2;
        }
        else if (rotateAnimation.getIndex() == 17) {
            yMove = -speed;
            xMove = -speed + 2.5f;
        }
        if (rotateAnimation.getIndex() == 18) { // Down
            yMove = -speed;
            xMove = 0;
        }
        else if (rotateAnimation.getIndex() == 19) {
            yMove = -speed;
            xMove = speed - 2.5f;
        }
        else if (rotateAnimation.getIndex() == 20) {
            yMove = -speed;
            xMove = speed - 2f;
        }
        else if (rotateAnimation.getIndex() == 21) {
            yMove = -speed;
            xMove = speed - 1;
        }
        else if (rotateAnimation.getIndex() == 22) {
            yMove = -speed;
            xMove = +speed;
        }
        else if (rotateAnimation.getIndex() == 23) {
            yMove = -speed + 1;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 24) {
            yMove = -speed + 1.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 25) {
            yMove = -speed + 2;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 26) {
            yMove = -speed + 2.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 27) { // Left
            yMove = 0;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 28) {
            yMove = speed - 2.5f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 29) {
            yMove = speed - 2f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 30) {
            yMove = speed - 1;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 31) {
            yMove = speed - 0.4f;
            xMove = speed;
        }
        else if (rotateAnimation.getIndex() == 32) {
            yMove = speed;
            xMove = speed - 0.6f;
        }
        else if (rotateAnimation.getIndex() == 33) {
            yMove = speed;
            xMove = speed - 1f;
        }
        else if (rotateAnimation.getIndex() == 34) {
            yMove = speed;
            xMove = speed - 2;
        }
        else if (rotateAnimation.getIndex() == 35) {
            yMove = speed;
            xMove = speed - 2.5f;
        }

    }

    protected void fire () {
        if (timer >= fireRate) {
            timer = 0;
            Fire fire = new Fire(handler, rotateAnimation.getIndex(), x, y, this);
            handler.getGame().getSoundEffect().playSound();
            handler.getWorld().getEntityManager().addEntity(fire);
            if (updateEnemyTank && handler.getGame().getClient().getEnemyTank() != null)
                handler.getGame().getClient().sendObject("fire");
        }
    }

    protected void timer() {
        timer += System.currentTimeMillis() - lastTime;
        timer2 += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimation(), (int) (x), (int) (y), width, height, null);
        if (!active && endGame)
            g.drawImage(Assests.playerStatus[0], 414, 386, null);
        else if (active && endGame)
            g.drawImage(Assests.playerStatus[1], 414, 386, null);
    }

    public BufferedImage getCurrentAnimation () {
        if (!active)
            return dropAnimation.getCurrentFrame();
        return rotateAnimation.getCurrentFrame();
    }

    @Override
    public void die() {
        active = false;
        while (dropAnimation.getIndex() < 3)
            dropAnimation.tick();
        if (updateEnemyTank)
            handler.getGame().getClient().sendObject("lose");
        endGame = true;
    }

    public void updateOnlineTank(Tank tank) {
        x = tank.getX();
        y = tank.getY();
        health = tank.getHealth();
        rotateAnimation.setIndex(tank.getRotateAnimationIndex());
        dropAnimation.setIndex(tank.getDropAnimationIndex());
        active = tank.isActive();
    }

    public void updateAnimations() {
        rotateAnimation = new Animation(rotateSpeed, Assests.tankRotate);
        dropAnimation = new Animation(dropAnimationSpeed, Assests.tankDrop);
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000 / fps;
        while(active && !endGame) {
            tick();
            try {
                sleep((long) timePerTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tanks.remove(this); // play again
        handler.getGame().getRender().removeObject(this);
        createAndDisplayUI();
    }

    private void createAndDisplayUI() {
        handler.getGame().getSoundEffect().getGameMusic().stop();
        handler.getGame().getSoundEffect().getMainMusic().play();
        handler.getGame().getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(400, 141, 479, 550, new BufferedImage[]{Assests.finishedGameWindow}, () -> {

        }));
        uiManager.addObject(new UIImageButton(491,  250, 128, 128, Assests.btn_close, () -> {
            handler.getGame().getRender().removeObject(handler.getWorld());
            handler.getGame().getRender().removeObject(uiManager);
            handler.getGame().getRender().addObject(handler.getGame().getMenuState().getUiManager());
            handler.getGame().getMouseManager().setUiManager(handler.getGame().getMenuState().getUiManager());
        }));
        uiManager.addObject(new UIImageButton(659,  250, 128, 128, Assests.btn_playAgain, () -> {
            handler.getGame().getRender().removeObject(handler.getWorld());
            handler.getGame().getRender().removeObject(uiManager);
            handler.getGame().getMouseManager().setUiManager(null);
            if (ipAddress == null)
                engine.states.State.setState(handler.getGame().getGameState());
            else
                engine.states.State.setState(handler.getGame().getMultiplayerState());
        }));
        handler.getGame().getRender().addObject(uiManager);
    }

    public void checkEnemyCollision() {
        new Thread(() -> {
            int fps = 60;
            double timePerTick = 1000 / fps;
            while(handler.getGame().getMultiplayerState().getPlayerTank() != null && handler.getGame().getMultiplayerState().getPlayerTank().isActive()) {
                handler.getGame().getMultiplayerState().getPlayerTank().checkEntityCollision();
                try {
                    sleep((long) timePerTick);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tanks.remove(handler.getGame().getMultiplayerState().getPlayerTank());
            handler.getGame().getRender().removeObject(handler.getGame().getMultiplayerState().getPlayerTank());
        }).start();
    }

    public static ArrayList<Tank> getTankes() {
        return tanks;
    }

    public int getRotateAnimationIndex() {
        return rotateAnimation.getIndex();
    }

    public int getDropAnimationIndex() {
        return dropAnimation.getIndex();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setUpdateEnemyTank (boolean updateEnemyTank) {
        this.updateEnemyTank = updateEnemyTank;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

}