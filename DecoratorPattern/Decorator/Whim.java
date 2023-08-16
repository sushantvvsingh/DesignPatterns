package DecoratorPattern.Decorator;

import DecoratorPattern.Coffee;

public class Whim extends Coffee{
    Coffee coffee;

    public Whim(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " Add extra Whim.";
    }

    @Override
    public Integer getCost() {
        return this.coffee.getCost() + 10;
    }
}
