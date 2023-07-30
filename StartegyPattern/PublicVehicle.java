package StartegyPattern;

import StartegyPattern.Strategy.DriveStrategy;

public class PublicVehicle extends Vehicle{

    PublicVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}
