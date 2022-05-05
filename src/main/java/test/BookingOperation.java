package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BookingOperation {
    static BookingState currentState;
    String Flight_number;
    String Category;
    String requested_seats;
    String CardNumber;
    String Name;
    FlightMap flight_details = FlightMap.getInstance();
    ArrayList<String> Error_messages = new ArrayList<>();
    ArrayList<String> Completed_Booking = new ArrayList<>();
    Integer Total;

    public static void set_state(BookingState state) {
        currentState = state;
    }

    public void operation() {
        currentState.operation(this);
    }

    public static void Flight_seats(String Flight_number, String Category, Integer AvailableSeats, FlightMap flight_details) {
        HashMap<String, Integer> inner = flight_details.Flight_available_seats.computeIfAbsent(Flight_number, k -> new HashMap<>());
        inner.put(Category, AvailableSeats);
    }

    public static void Flight_price(String Flight_number, String Category, Integer Price, FlightMap flight_details) {
        HashMap<String, Integer> inner1 = flight_details.Flight_price.computeIfAbsent(Flight_number, k -> new HashMap<>());
        inner1.put(Category, Price);
    }

    public static void csv_flight_parser(String file_name, FlightMap flight_details) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            Flight_seats(split[1], split[0], Integer.valueOf(split[2]), flight_details);
            Flight_price(split[1], split[0], Integer.valueOf(split[3]), flight_details);
        }
    }

    public static Boolean booking_Flight_exist(String flight_number, FlightMap flight_details) {
        return flight_details.Flight_available_seats.containsKey(flight_number);
    }

    public void csv_booking_parser(String file_name, String Output_csv, String Output_txt) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            Name=split[0];
            Flight_number = split[1];
            Category = split[2];
            requested_seats = split[3];
            CardNumber = split[4];
            boolean flight_exist = booking_Flight_exist(Flight_number, flight_details);
            if (flight_exist) {
                currentState = new FlightExist();
                currentState.operation(this);
            }
            else {
                currentState = new FlightNotExist();
                currentState.operation(this);
            }
        }
        System.out.println("Error Bookings");
        System.out.println(Error_messages);
        System.out.println("Completed Bookings");
        System.out.println(Completed_Booking);
        write_to_file(Error_messages, Completed_Booking, Output_csv, Output_txt);
    }

    public static void file_writer(ArrayList < String > data, String file_name) throws IOException {
        boolean file_type = file_name.endsWith(".csv");
        FileWriter writer = new FileWriter(file_name);
        if (file_type) {
            String heading = "Booking name, flight number, Category, number of seats booked, total price";
            writer.write(heading);
            writer.write(System.getProperty( "line.separator" ));
        }
            for (String str : data) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
    }

    public static void write_to_file(ArrayList < String > error_data, ArrayList < String > completed_data, String Output_csv, String Output_txt) throws IOException {
        if (completed_data.size()> 0) {
            file_writer(completed_data,Output_csv);
        }
        if (error_data.size() > 0 ) {
            file_writer(error_data,Output_txt);
            }
    }

    }
