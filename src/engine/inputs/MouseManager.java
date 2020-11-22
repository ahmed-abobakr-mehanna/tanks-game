package engine.inputs;

import engine.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

public class MouseManager implements MouseListener, MouseMotionListener, Serializable {
    private transient UIManager uiManager;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (uiManager != null)
            uiManager.onMouseRelease();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (uiManager != null)
            uiManager.onMouseMove(e);
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
