package DecoratorPattern.Decorator;

import DecoratorPattern.Coffee;

public class Chocolate extends Toppings{

    Coffee coffee;

    public Chocolate(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " Add extra chocolate.";
    }

    @Override
    public Integer getCost() {
        return this.coffee.getCost() + 20;
    }
    
}
