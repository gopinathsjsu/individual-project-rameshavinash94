package test;

public class FlightNotExist implements  BookingState{
    @Override
    public String toString() {
        return "In FlightNotExist State";
    }

    @Override
    public void operation(BookingOperation state) {
        String error = "Please enter correct booking details for " + state.Name + ": invalid flight number";
        state.Error_messages.add(error);
    }
}
