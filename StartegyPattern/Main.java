package StartegyPattern;

import StartegyPattern.Strategy.PublicDriveStrategy;
import StartegyPattern.Strategy.SportsDriveStrategy;

public class Main{
    public static void main(String[] args) {
        Vehicle publicVehicle = new PublicVehicle(new PublicDriveStrategy());
        publicVehicle.Drive();
        Vehicle sportsVehicle = new SportsVehicle(new SportsDriveStrategy());
        sportsVehicle.Drive();
    }
}