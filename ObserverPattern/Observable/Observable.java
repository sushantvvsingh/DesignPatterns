package ObserverPattern.Observable;

import ObserverPattern.Models.Weather;
import ObserverPattern.Observer.Observer;

public interface Observable {
    void add(Observer obj);
    void remove(Observer obj);
    void setValue(Weather weather);
    void notifyObservers();
    Weather getValue();
}