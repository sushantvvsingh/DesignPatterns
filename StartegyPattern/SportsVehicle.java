package StartegyPattern;

import StartegyPattern.Strategy.DriveStrategy;

public class SportsVehicle extends Vehicle {

    SportsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
    
}
