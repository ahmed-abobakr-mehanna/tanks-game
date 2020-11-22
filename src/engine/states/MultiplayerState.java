package engine.states;

import engine.Handler;
import engine.entities.creature.Fire;
import engine.entities.creature.Tank;
import engine.worlds.World;

import java.awt.*;

public class MultiplayerState extends State{
    private Tank myTank, playerTank;

    public MultiplayerState(Handler handler) {
        super(handler);
    }

    @Override
    public void startState() {
        handler.getGame().getRender().addObject(handler.getWorld());
        handler.getGame().getClient().sendObject("play online");
        myTank = null;
        playerTank = null;
        while (myTank == null) {
            System.out.println("Wait Game Will Start");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (playerTank == null) {
            System.out.println("Waiting For Another Player");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

}