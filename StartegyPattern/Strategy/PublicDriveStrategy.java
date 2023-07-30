package StartegyPattern.Strategy;

public class PublicDriveStrategy implements DriveStrategy {

    @Override
    public void Drive() {
        System.out.println("Public Drive Strategy....");
    }
    
}
