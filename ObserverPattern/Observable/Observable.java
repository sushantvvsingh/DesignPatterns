package ObserverPattern.Observable;

import ObserverPattern.Observer.Observer;

public interface Observable {
    void add(Observer obj);
    void remove(Observer obj);
    void notifyObservers();
}