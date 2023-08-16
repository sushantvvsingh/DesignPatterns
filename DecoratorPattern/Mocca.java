package DecoratorPattern;

public class Mocca extends Coffee{

    @Override
    public String description() {
        return "Not good in taste!!!";
    }

    @Override
    public Integer getCost() {
        return 100;
    }
    
}
