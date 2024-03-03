package ObserverPattern.Observer;

import ObserverPattern.Models.Weather;
import ObserverPattern.Observable.WeatherObservable;

public class WeatherObserver implements Observer{

    WeatherObservable observable;
    Weather weather;

    public WeatherObserver(WeatherObservable observable){
        this.observable = observable;
    }

    @Override
    public void update() {
        this.weather = observable.getValue();
        System.out.println("Current temperature is " + String.valueOf(this.weather.getTemperature()) + 
        "\nHumidity is " + String.valueOf(this.weather.getHumidity()));
    }
    
}
