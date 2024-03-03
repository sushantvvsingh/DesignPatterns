package ObserverPattern.Observable;

import java.util.ArrayList;
import java.util.List;

import ObserverPattern.Models.Weather;
import ObserverPattern.Observer.Observer;

public class WeatherObservable implements Observable{

    public List<Observer> observers = new ArrayList<>();
    public Weather weather = new Weather();

    @Override
    public void add(Observer obj) {
        observers.add(obj);
    }

    public Weather getValue() {
        return this.weather;
    }

    @Override
    public void notifyObservers() {
        for(Observer ob: observers){
            ob.update();
        }
    }

    @Override
    public void remove(Observer obj) {
        if(observers.contains(obj)){
            observers.remove(obj);
        }
    }

    public void setValue(Weather weather) {
        if(weather.getTemperature() != this.weather.getTemperature()
        || weather.getHumidity() != this.weather.getHumidity()){
            this.weather.setTemperature(weather.getTemperature());
            this.weather.setHumidity(weather.getHumidity());
            notifyObservers();
        }
    }
    
}
