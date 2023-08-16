package DecoratorPattern.Decorator;

import DecoratorPattern.Coffee;

public class Sugar extends Coffee{
    Coffee coffee;

    public Sugar(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " Add extra Sugar.";
    }

    @Override
    public Integer getCost() {
        return this.coffee.getCost() + 50;
    }
}
