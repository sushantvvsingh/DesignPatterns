package ElevatorSystemDesign.DispatcherFactory;

import ElevatorSystemDesign.Enum.DispatcherType;

public class DispatcherFactory {
    public IDispatcher getDispatcher(DispatcherType dispatcherType){
        IDispatcher dispatcher = null;
        if(dispatcherType == DispatcherType.EXTERNAL){
            return new ExternalDispatcher();
        }
        else if(dispatcherType == DispatcherType.INTERNAL){
            return  new InternalDispatcher();
        }
        return dispatcher;

    }
}
