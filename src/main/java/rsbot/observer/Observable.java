package rsbot.observer;

import java.util.LinkedList;
import java.util.List;

public interface Observable {
    List<Observer> observers = new LinkedList<Observer>();

    default void addObserver(Observer o) {
        observers.add(o);
    }

    default void removeObserver(Observer o) {
        observers.remove(o);
    }

    default void notifyObservers() {
        observers.forEach(o -> o.update(null));
    }
}
