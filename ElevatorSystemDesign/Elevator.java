package ElevatorSystemDesign;

public class Elevator{
    int elevatorID;
    
    private ElevatorController elevatorController;

    private Elevator(){
        elevatorController = new ElevatorController();
    }

    public int getElevatorID(){
        return elevatorID;
    }

    public void moveToFloor(int floorID){
        elevatorController.moveToFloor(floorID);
    }

    public void notifyUpdateFloor(int floorID){
        elevatorController.setCurrentFloor(floorID);
    }
    
    
}
