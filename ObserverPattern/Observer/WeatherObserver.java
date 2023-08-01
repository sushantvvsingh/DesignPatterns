package ObserverPattern.Observer;

import ObserverPattern.Weather;
import ObserverPattern.Observable.Observable;

public class WeatherObserver implements Observer{

    Observable observable;
    Weather weather;

    public WeatherObserver(Observable observable){
        this.observable = observable;
    }

    @Override
    public void update() {
        this.weather = observable.getValue();
        System.out.println("Current temperature is " + String.valueOf(this.weather.getTemperature()) + 
        "\nHumidity is " + String.valueOf(this.weather.getHumidity()));
    }
    
}
