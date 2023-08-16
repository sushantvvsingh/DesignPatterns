package DecoratorPattern;

public class Cappuccino extends Coffee{

    @Override
    public String description() {
        return "Better in taste!!!";
    }

    @Override
    public Integer getCost() {
        return 120;
    }
    
}
