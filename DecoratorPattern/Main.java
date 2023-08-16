package DecoratorPattern;

import DecoratorPattern.Decorator.Chocolate;
import DecoratorPattern.Decorator.Sugar;
import DecoratorPattern.Decorator.Whim;

public class Main {
    public static void main(String[] args) {
        Coffee mocca = new Sugar(new Mocca());
        System.out.println(mocca.description());
        System.out.println("price :" + String.valueOf(mocca.getCost()));
        Coffee cappuccino = new Chocolate(new Whim(new Sugar(new Cappuccino())));
        System.out.println(cappuccino.description());
        System.out.println("price :" + String.valueOf(cappuccino.getCost()));
    }
}
