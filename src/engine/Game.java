package engine;

import engine.display.Display;
import engine.gfx.Assests;
import engine.inputs.KeyManager;
import engine.inputs.MouseManager;
import engine.rendering.Render;
import engine.states.GameState;
import engine.states.MenuState;
import engine.states.MultiplayerState;
import engine.worlds.World;
import networkUDP.GameClient;

import java.io.Serializable;

public class Game extends Thread implements Serializable {
    private transient Display display;
    private String title;
    private int width, height;
    private boolean running = false;

    // Rendering
    private Render render;

    // States
    private engine.states.State gameState;
    private engine.states.State multiplayerState;
    private engine.states.State menuState;

    // Inputs
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Handler
    private Handler handler;

    // World
    private World world;

    // Music and Sound Effects
    private transient SoundEffect soundEffect;

    private transient GameClient client;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void init() {
        handler = new Handler(this);
        display = new Display(title, width, height);
        soundEffect = new SoundEffect();
        soundEffect.getMainMusic().play();
        soundEffect.getMainMusic().setRepeat(true);
        render = new Render(handler);
        keyManager = new KeyManager(handler);
        display.getFrame().addKeyListener(keyManager);
        mouseManager = new MouseManager();
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assests.init();
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
        gameState = new GameState(handler);
        multiplayerState = new MultiplayerState(handler);
        menuState = new MenuState(handler);
        engine.states.State.setState(menuState);
    }

    @Override
    public void run() {
        init();
        render.start();
        world.start();
        keyManager.start();
    }

    public void start() {
        if(running)
            return;
        running = true;
        super.start();
    }

    public void exit(){
        if(!running)
            return;
        running = false;
        soundEffect.getMainMusic().stop();
        display.getFrame().dispose();
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }

    public boolean isRunning () {
        return running;
    }

    public Display getDisplay() {
        return display;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public Render getRender() {
        return render;
    }

    public MultiplayerState getMultiplayerState() {
        return (MultiplayerState) multiplayerState;
    }

    public void setMultiplayerState(MultiplayerState multiplayerState) {
        this.multiplayerState = multiplayerState;
    }

    public GameState getGameState() {
        return (GameState)gameState;
    }

    public MenuState getMenuState() {
        return (MenuState)menuState;
    }

    public void setClient(GameClient client) {
        this.client = client;
    }

    public GameClient getClient() {
        return client;
    }

    public Handler getHandler() {
        return handler;
    }

    public SoundEffect getSoundEffect() {
        return soundEffect;
    }

}
