package rsbot;

import rsbot.view.MainWindowView;

public class RSBot {
    public void start() {
        MainWindowView view = new MainWindowView();
        view.initialize();
    }
}
