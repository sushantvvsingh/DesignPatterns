package ObserverPattern;

import ObserverPattern.Models.Weather;
import ObserverPattern.Observable.Observable;
import ObserverPattern.Observable.WeatherObservable;
import ObserverPattern.Observer.Observer;
import ObserverPattern.Observer.WeatherObserver;

public class Main {
    public static void main(String[] args){
        Observable observable = new WeatherObservable();
        Observer observer1 = new WeatherObserver(observable);
        Observer observer2 = new WeatherObserver(observable);
        observable.add(observer1);
        observable.add(observer2);
        Weather weather = new Weather();
        weather.setTemperature(10);
        weather.setHumidity(20);
        observable.setValue(weather);
        observable.remove(observer1);
        observable.setValue(weather);
        weather.setTemperature(20);
        observable.setValue(weather);
    }
}
