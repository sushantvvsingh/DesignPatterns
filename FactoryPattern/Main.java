package FactoryPattern;

import FactoryPattern.Factory.Car;
import FactoryPattern.Factory.CarFactory;
import FactoryPattern.Factory.CarType;

public class Main {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Car bmw = carFactory.getCar(CarType.BMW);
        bmw.drive();
        Car audi = carFactory.getCar(CarType.AUDI);
        audi.drive();
        Car ferrari = carFactory.getCar(CarType.FERRARI);
        ferrari.drive();
    }
}
