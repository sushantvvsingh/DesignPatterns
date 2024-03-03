package StartegyPattern;

import StartegyPattern.Strategy.DriveStrategy;

class Vehicle{
    //composition (HAS-A)
    private DriveStrategy driveStrategy;
    
    //dependency injection
    Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }

    public void drive(){
        driveStrategy.drive();
    }
}