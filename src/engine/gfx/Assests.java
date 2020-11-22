package engine.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assests {
    public static final int width = 128, height = 128;
    public static transient BufferedImage grass, rock, dirt, tree;
    public static transient BufferedImage[] tankRotate, fire, fireSpeed, tankDrop;
    public static transient BufferedImage[] btn_start, btn_exit, btn_playAgain, btn_close;
    public static transient BufferedImage[] loadingBar, playerStatus;
    public static transient BufferedImage window, finishedGameWindow;

    public static void init() {
        window = ImageLoader.loadImage("res/textures/game menu/window.png");
        finishedGameWindow = ImageLoader.loadImage("res/textures/game finished window/window.png");

        loadingBar = new BufferedImage[3];
        loadingBar[0] = ImageLoader.loadImage("res/textures/loading bar/loading_bar_1.png");
        loadingBar[1] = ImageLoader.loadImage("res/textures/loading bar/loading_bar_2.png");
        loadingBar[2] = ImageLoader.loadImage("res/textures/loading bar/loading_bar_3.png");

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/game menu/start_btn.png"));
        btn_start = new BufferedImage[2];
        for (int i = 0; i < 2; i++)
            btn_start[i] = sheet.crop(i * 410, 0, 410, 121);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/game menu/exit_btn.png"));
        btn_exit = new BufferedImage[2];
        for (int i = 0; i < 2; i++)
            btn_exit[i] = sheet.crop(i * 410, 0, 410, 121);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/spritesheet.png"));
        dirt = sheet.crop(0, 0, width, height);
        grass = sheet.crop(width, 0, width, height);
        rock = sheet.crop(0, height, width, height);
        tree = sheet.crop(width, height, width, height);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/fire.png"));
        fire = new BufferedImage[36];
        int index = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                fire[index++] = sheet.crop(j * width, i * height, width, height);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/fireSpeed.png"));
        fireSpeed = new BufferedImage[36];
        index = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                fireSpeed[index++] = sheet.crop(j * width, i * height, width, height);

            
        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/rotate.png"));
        tankRotate = new BufferedImage[36];
        index = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                tankRotate[index++] = sheet.crop(j * width, i * height, width, height);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/drop.png"));
        tankDrop = new BufferedImage[4];
        index = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                    tankDrop[index++] = sheet.crop(j * width, i * height, width, height);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/player status/status.png"));
        playerStatus = new BufferedImage[2];
        playerStatus[0] = sheet.crop(0, 0, 453, 60);
        playerStatus[1] = sheet.crop(453, 0, 399, 59);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/game finished window/close_btns.png"));
        btn_close = new BufferedImage[2];
        for (int i = 0; i < 2; i++)
            btn_close[i] = sheet.crop(i * 210, 0, 210, 210);

        sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/game finished window/play_again_btns.png"));
        btn_playAgain = new BufferedImage[2];
        for (int i = 0; i < 2; i++)
            btn_playAgain[i] = sheet.crop(i * 210, 0, 210, 210);
    }
}