package engine.states;

import engine.Handler;
import engine.gfx.Animation;
import engine.gfx.Assests;
import engine.gfx.LoadingBar;
import engine.rendering.Rendering;

import java.awt.*;
import java.io.Serializable;

public abstract class State implements Rendering, Serializable {
    private static State currentState;
    protected transient Handler handler;
    protected transient LoadingBar loadingBar;

    public State (Handler handler) {
        currentState = null;
        this.handler = handler;
    }
    public static void setState(State state) {
        currentState = state;
        currentState.startState();
    }

    @Override
    public void render(Graphics g) {
        if (!loadingBar.isComplete())
            g.drawImage(Assests.loadingBar[0], 150, 700, 50, 20, null);
        if (loadingBar.isComplete()) {
            g.drawImage(Assests.loadingBar[2], 900, 700, 50, 20, null);
            handler.getGame().getRender().removeObject(this);
        }
    }

    public abstract void startState();
}
