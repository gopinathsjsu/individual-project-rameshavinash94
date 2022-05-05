package test;

public class FlightExist implements BookingState{
    @Override
    public String toString() {
        return "In FlightExist State";
    }

    public static Integer Payment(Integer Seats, Integer Cost_per_Seat) {
        return Seats * Cost_per_Seat;
    }

    public static Integer Price_per_Category(String flight_number, String Category, FlightMap flight_details) {
        return flight_details.Flight_price.get(flight_number).get(Category);
    }

    public static Boolean Flight_seat_availability(String flight_number, String Category, Integer requested_seats, FlightMap flight_details) {
        Integer available_seats = flight_details.Flight_available_seats.get(flight_number).get(Category);
        return available_seats != null && requested_seats <= available_seats;
    }

    @Override
    public void operation(BookingOperation state) {
        if (Flight_seat_availability(state.Flight_number, state.Category, Integer.valueOf(state.requested_seats), state.flight_details)){
            state.Total= Payment(Integer.valueOf(state.requested_seats), Price_per_Category(state.Flight_number, state.Category, state.flight_details));
            BookingOperation.set_state(new ValidBooking());
            state.operation();
        }
        else{
            String error = "Please enter correct booking details for " + state.Name + ": requested seats not available";
            state.Error_messages.add(error);
        }
    }
}
