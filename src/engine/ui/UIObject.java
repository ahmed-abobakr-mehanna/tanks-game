package engine.ui;

import engine.rendering.Rendering;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public abstract class UIObject implements Rendering, ClickListener, Serializable {
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;

    public UIObject (float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x, (int)y, width, height);
    }

    // There is render method because this class implements Rendering
    // There is onClick method because this class implements ClickListener

    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }

    public void onMouseRelease() {
        if (hovering)
            onClick();
    }

}