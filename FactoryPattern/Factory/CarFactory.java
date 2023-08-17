package FactoryPattern.Factory;

public class CarFactory {
    public Car getCar(CarType carType){
        if(carType == CarType.AUDI){
            return new Audi();
        }
        else if(carType == CarType.BMW){
            return new BMW();
        }
        else if(carType == CarType.FERRARI){
            return new Ferrari();
        }
        return null;
    }
}
