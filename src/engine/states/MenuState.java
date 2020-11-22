package engine.states;

import engine.Handler;
import engine.gfx.Assests;
import engine.ui.ClickListener;
import engine.ui.UIImageButton;
import engine.ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends State {
    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager();
        handler.getGame().getMouseManager().setUiManager(uiManager);
    }

    @Override
    public void startState() {
        handler.getGame().getRender().addObject(uiManager);

        uiManager.addObject(new UIImageButton(0, 0, 1280, 832, new BufferedImage[]{Assests.window}, () -> {

        }));

        uiManager.addObject(new UIImageButton(435, 200, 410, 121, Assests.btn_start, () -> {
            handler.getGame().getMouseManager().setUiManager(null);
            State.setState(handler.getGame().getGameState());
            handler.getGame().getRender().removeObject(uiManager);
        }));

        uiManager.addObject(new UIImageButton(435, 350, 410, 121, Assests.btn_start, () -> {
            handler.getGame().getMouseManager().setUiManager(null);
            State.setState(handler.getGame().getMultiplayerState());
            handler.getGame().getRender().removeObject(uiManager);
        }));

        uiManager.addObject(new UIImageButton(435, 506, 410, 121, Assests.btn_exit, () -> {
            handler.getGame().exit();
            handler.getGame().getRender().removeObject(uiManager);
        }));

    }

    public UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void render(Graphics g) {

    }
}