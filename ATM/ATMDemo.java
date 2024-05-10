package ATM;

class ATMMachine{
    private IATMState state;
    private int balance;

    ATMMachine(){
        this.state = new IdleState();
        this.balance = 0;
    }

    public IATMState getState() {
        return state;
    }

    public void setState(IATMState state) {
        this.state = state;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void insertCard(){
        this.state.enterCard(this);
    }
    public void rmeoveCard(){
        this.state.ejectCard(this);
    }
    public void enterPin(){
        this.state.enterPin(this);
    }
    public void cancel(){
        this.state.cancel(this);
    }
    public void withdraw(int amount){
        this.state.withdraw(this, amount);
    }
}

interface IATMState{
    void enterCard(ATMMachine state);
    void ejectCard(ATMMachine state);
    void enterPin(ATMMachine state);
    void withdraw(ATMMachine state, int balance);
    void cancel(ATMMachine state);
}

class IdleState implements IATMState{

    @Override
    public void cancel(ATMMachine atm) {
        System.out.println("Action Cancelled....");
        atm.setState(new IdleState());
    }

    @Override
    public void ejectCard(ATMMachine state) {
        System.out.println("Enter Card....");
    }

    @Override
    public void enterCard(ATMMachine state) {
        System.out.println("card inserted....");
        state.setState(new CardEnteredState());
        state.setBalance(100000);
    }

    @Override
    public void enterPin(ATMMachine state) {
        System.out.println("Enter Card....");
    }

    @Override
    public void withdraw(ATMMachine state, int balance) {
        System.out.println("Enter Card....");
    }

}
class CardEnteredState implements IATMState{

    @Override
    public void cancel(ATMMachine state) {
        System.out.println("Action Cancelled....");
        state.setState(new IdleState());
    }

    @Override
    public void ejectCard(ATMMachine state) {
        System.out.println("card removed");
        state.setState(new IdleState());
    }

    @Override
    public void enterCard(ATMMachine state) {
        System.out.println("card already inserted , Enter pin.....");
    }

    @Override
    public void enterPin(ATMMachine state) {
        //validation
        System.out.println("Pin correct...");
        state.setState(new PinEnteredState());
    }

    @Override
    public void withdraw(ATMMachine state, int balance) {
        System.out.println("Enter Pin...");
    }

}
class PinEnteredState implements IATMState{

    @Override
    public void cancel(ATMMachine state) {
        System.out.println("Action Cancelled....");
        state.setState(new IdleState());
    }

    @Override
    public void ejectCard(ATMMachine state) {
        System.out.println("card removed");
        state.setState(new IdleState());
    }

    @Override
    public void enterCard(ATMMachine state) {
        System.out.println("Not Supported...");
    }

    @Override
    public void enterPin(ATMMachine state) {
        System.out.println("Not Supported...");
    }

    @Override
    public void withdraw(ATMMachine state, int balance) {
        if(balance > state.getBalance()){
            System.out.println("Enter Valid amount");
        }
        else{
            state.setBalance(state.getBalance() - balance);
            System.out.println("Please collect cash and card");
        }
        state.setState(new IdleState());
    }

}

public class ATMDemo {
    public static void main(String[] args){
        ATMMachine atmMachine = new ATMMachine();
        atmMachine.enterPin();
        atmMachine.insertCard();
        atmMachine.enterPin();
        atmMachine.cancel();
        atmMachine.insertCard();;
        atmMachine.enterPin();
        atmMachine.withdraw(10);
    }
}
