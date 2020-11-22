package engine;

import jaco.mp3.player.MP3Player;
import java.io.File;

public class SoundEffect {
    private MP3Player mainMusic;
    private MP3Player gameMusic;
    private MP3Player loadingSound;

    public SoundEffect() {
        mainMusic = new MP3Player(new File("res/music/main music.mp3"));
        gameMusic = new MP3Player(new File("res/music/game music.mp3"));
        // Fire Sound Effect Call Method playSound
        loadingSound = new MP3Player(new File("res/sound effect/loading sound effect2.mp3"));
    }

    public void playSound() {
        new Thread(() -> new MP3Player(new File("res/sound effect/fire sound effect.mp3")).play()).start();
    }

    public MP3Player getMainMusic() {
        return mainMusic;
    }

    public MP3Player getLoadingSound() {
        return loadingSound;
    }

    public MP3Player getGameMusic() {
        return gameMusic;
    }

}