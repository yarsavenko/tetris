package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

public class Input implements KeyListener, MouseListener {
    private final Collection<KeyEvent> keyPressedEvents;
    private final Collection<KeyEvent> keyReleasedEvents;
    private final Collection<MouseEvent> mouseClickedEvents;
    public Input() {
        keyPressedEvents = new ArrayList<KeyEvent>();
        mouseClickedEvents = new ArrayList<MouseEvent>();
        keyReleasedEvents = new ArrayList<KeyEvent>();
    }
    @Override
    public void keyTyped(KeyEvent event) {
    }
    @Override
    public synchronized void keyPressed(KeyEvent event) {
        keyPressedEvents.add(event);
    }
    public synchronized Collection<KeyEvent> getKeyPressedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyPressedEvents);
        keyPressedEvents.clear();
        return events;
    }
    @Override
    public synchronized void keyReleased(KeyEvent event) {
        keyReleasedEvents.add(event);
    }
    public synchronized Collection<KeyEvent> getKeyReleasedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyReleasedEvents);
        keyReleasedEvents.clear();
        return events;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
