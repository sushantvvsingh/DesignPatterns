package ElevatorSystemDesign.ElevatorControlStrategy;

import ElevatorSystemDesign.Enum.ElevatorDirection;

public interface IElevatorControlStrategy {
    int determineNextStop(int floorNum, ElevatorDirection direction);
}
