package engine.ui;

import engine.rendering.Rendering;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class UIManager implements Rendering, Serializable {
    private ArrayList<UIObject> objects;

    public UIManager () {
        objects = new ArrayList<>();
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++)
            objects.get(i).render(g);
    }

    public void onMouseMove(MouseEvent e) {
        for (int i = 0; i < objects.size(); i++)
            objects.get(i).onMouseMove(e);
    }

    public void onMouseRelease() {
        for (int i = 0; i < objects.size(); i++)
            objects.get(i).onMouseRelease();
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }

}