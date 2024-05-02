package ElevatorSystemDesign;

import ElevatorSystemDesign.ElevatorControlStrategy.FirstComeFirstServeStrategy;
import ElevatorSystemDesign.ElevatorControlStrategy.IElevatorControlStrategy;
import ElevatorSystemDesign.Enum.ElevatorDirection;
import ElevatorSystemDesign.Models.ElevatorStatus;

public class ElevatorController {
    private ElevatorStatus status;
    private IElevatorControlStrategy elevatorController;

    void moveToFloor(int floorID){
       elevatorController = new FirstComeFirstServeStrategy();
       int nextFloor = elevatorController.determineNextStop(floorID, status.getDirection());
       if(nextFloor < status.getCurrentFloor()){
        status.setDirection(ElevatorDirection.DOWN);
       }
       else if(nextFloor > status.getCurrentFloor()){
        status.setDirection(ElevatorDirection.UP);
       }
       else if(nextFloor != status.getCurrentFloor()){
        status.setDirection(ElevatorDirection.MOVING);
       }

    }
    
    void setCurrentFloor(int floorID){
        status.setCurrentFloor(floorID);
    }

    public IElevatorControlStrategy getElevatorController() {
        return elevatorController;
    }

    
}
