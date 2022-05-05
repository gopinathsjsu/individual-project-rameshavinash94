package test;

public class ConfirmBooking implements BookingState {
    @Override
    public String toString() {
        return "In ConfirmBooking State";
    }

    public static Integer Flight_seat_present(String flight_number, String Category, FlightMap flight_details) {
        return flight_details.Flight_available_seats.get(flight_number).get(Category);
    }

    @Override
    public void operation(BookingOperation state) {
        Integer Current_available_seat = Flight_seat_present(state.Flight_number, state.Category, state.flight_details);
        Integer Seats_available_post_booking = Current_available_seat - Integer.parseInt(state.requested_seats);
        BookingOperation.Flight_seats(state.Flight_number, state.Category,Seats_available_post_booking, state.flight_details);
        String Completed = state.Name+","+state.Flight_number+","+state.Category+","+state.requested_seats+","+state.Total;
        state.Completed_Booking.add(Completed);
        System.out.println("Available Seats after booking confirmation");
        System.out.println(state.flight_details.getFlight_available_seats());
    }
}
