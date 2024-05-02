package ElevatorSystemDesign.ElevatorControlStrategy;

import java.util.Queue;

import ElevatorSystemDesign.Enum.ElevatorDirection;

public class FirstComeFirstServeStrategy implements IElevatorControlStrategy{
    private Queue<Integer> upQueue, downQueue;

    @Override
    public int determineNextStop(int floorID, ElevatorDirection direction){
        if(direction == ElevatorDirection.DOWN){
            downQueue.offer(floorID);
            return downQueue.poll();
        }
        else {
            upQueue.offer(floorID);
            return upQueue.poll();
        }
    }
}
