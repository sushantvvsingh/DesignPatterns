package StartegyPattern.Strategy;

//IS-A
public class PublicDriveStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Public Drive Strategy...");
    }
    
}
