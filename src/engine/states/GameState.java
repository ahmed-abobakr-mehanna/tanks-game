package engine.states;

import engine.Handler;
import engine.entities.creature.Computer;
import engine.entities.creature.Tank;
import engine.gfx.Assests;
import engine.gfx.LoadingBar;

public class GameState extends State {
    private Tank playerTank;
    private Computer computerTank;
    public GameState(Handler handler) {
        super(handler);
    }

    @Override
    public void startState() {
        playerTank = new Tank(handler, handler.getWorld().getSpawnX(), handler.getWorld().getSpawnY());
        computerTank = new Computer(handler, 350, 350);
        handler.getGame().getRender().addObject(handler.getWorld());
        handler.getGame().getRender().addObject(playerTank);
        handler.getGame().getRender().addObject(computerTank);
        handler.getGame().getSoundEffect().getMainMusic().pause();
        handler.getGame().getRender().addObject(this);
        handler.getGame().getSoundEffect().getLoadingSound().play();
        handler.getGame().getSoundEffect().getLoadingSound().setRepeat(true);
        loadingBar = new LoadingBar(handler, Assests.loadingBar[1]);
        handler.getGame().getRender().addObject(loadingBar);
        loadingBar.start();
        try {
            Thread.currentThread().sleep(4250); // need 4250 milliseconds to complete loading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.getGame().getSoundEffect().getLoadingSound().stop();
        handler.getGame().getSoundEffect().getGameMusic().play();
        handler.getGame().getSoundEffect().getGameMusic().setRepeat(true);
        playerTank.start();
        computerTank.start();
    }
}