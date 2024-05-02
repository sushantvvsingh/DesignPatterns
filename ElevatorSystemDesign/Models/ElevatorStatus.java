package ElevatorSystemDesign.Models;

import ElevatorSystemDesign.Enum.ElevatorDirection;

public class ElevatorStatus {
    int currentFloor;
    ElevatorStatus status;
    ElevatorDirection direction;
    
    public int getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    public ElevatorStatus getStatus() {
        return status;
    }
    public void setStatus(ElevatorStatus status) {
        this.status = status;
    }
    public ElevatorDirection getDirection() {
        return direction;
    }
    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }
    
    
}
