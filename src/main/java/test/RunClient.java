package test;
import java.io.IOException;

public class RunClient {

    public static void main(String[] args) throws IOException {
        FlightMap flight_details = FlightMap.getInstance();
        BookingOperation booking = new BookingOperation();
        BookingOperation.csv_flight_parser(args[1], flight_details);
        System.out.println("Initial Seats Availability");
        System.out.println(flight_details.getFlight_available_seats());
        booking.csv_booking_parser(args[0],args[2], args[3]);
    }
}