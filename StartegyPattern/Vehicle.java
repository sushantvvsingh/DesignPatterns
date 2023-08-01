package StartegyPattern;

import StartegyPattern.Strategy.DriveStrategy;

class Vehicle{
    private DriveStrategy driveStrategy;
    
    Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }

    public void drive(){
        driveStrategy.drive();
    }
}