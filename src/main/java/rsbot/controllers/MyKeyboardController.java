package rsbot.controllers;

import rsbot.view.MainWindowView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyboardController implements KeyListener {

    private MainWindowView _view;

    public MyKeyboardController(MainWindowView view) {
        _view = view;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            _view.close();
    }
}
