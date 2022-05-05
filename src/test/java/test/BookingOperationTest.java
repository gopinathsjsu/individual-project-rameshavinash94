package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookingOperationTest {
    static BookingOperation booking;
    static FlightMap flight_details;

    @BeforeAll
    public static void setup(){
        System.out.println("Create Setup and Run all Test cases");
        booking = new BookingOperation();
        flight_details = FlightMap.getInstance();
    }

    @DisplayName("Singleton Test")
    @Test
    void Singleton() {
        FlightMap flight_details1 = FlightMap.getInstance();
        FlightMap flight_details2 = FlightMap.getInstance();
        assertSame(flight_details1, flight_details2);
    }

    @DisplayName("Parse the Csv file and store in FlightMap Hashmap")
    @Test
    void csv_flight_parser() throws IOException {
        FlightMap flight_details = FlightMap.getInstance();
        String file_name="/Users/avinash/Downloads/flights.csv";
        BookingOperation.csv_flight_parser(file_name,flight_details);
        Assertions.assertFalse(flight_details.getFlight_available_seats().isEmpty());
        Assertions.assertFalse(flight_details.getFlight_price().isEmpty());
    }

    @DisplayName(" Testing Addition/Updates of seats to Flight_available_seats HashMap")
    @Test
    void flight_seats() {
        String Flight_number = "SJ456";
        String Category="Economy";
        Integer AvailableSeats=5;
        HashMap<String, Integer> inner = flight_details.Flight_available_seats.computeIfAbsent(Flight_number, k -> new HashMap<>());
        inner.put(Category, AvailableSeats);
        HashMap < String, HashMap < String, Integer >> Flight_available_seats_test;
        Flight_available_seats_test = flight_details.getFlight_available_seats();
        Assertions.assertNotNull(Flight_available_seats_test.get("SJ456").get("Economy"));
        assertSame(Flight_available_seats_test.get("SJ456").get("Economy"), AvailableSeats);
        Assertions.assertNull(Flight_available_seats_test.get("SJ46"));
        Assertions.assertNull(Flight_available_seats_test.get("SJ456").get("Premium Economy"));
        assertNotEquals(10, (int) Flight_available_seats_test.get("SJ456").get("Economy"));
    }

    @DisplayName("Testing Addition/Updates of pricing info to Flight_price Hashmap")
    @Test
    void flight_price() {
        String Flight_number = "SJ456";
        String Category="Economy";
        Integer Price=200;
        HashMap<String, Integer> inner = flight_details.Flight_price.computeIfAbsent(Flight_number, k -> new HashMap<>());
        inner.put(Category, Price);
        HashMap < String, HashMap < String, Integer >> Flight_price_test;
        Flight_price_test = flight_details.getFlight_price();
        Assertions.assertNotNull(Flight_price_test.get("SJ456").get("Economy"));
        assertSame(Flight_price_test.get("SJ456").get("Economy"), Price);
        Assertions.assertNull(Flight_price_test.get("SJ46"));
        Assertions.assertNull(Flight_price_test.get("SJ456").get("Premium Economy"));
        assertNotEquals(150, (int) Flight_price_test.get("SJ456").get("Economy"));
    }

    @DisplayName("Check Flight Exist Status- returns Boolean True or False")
    @Test
    void booking_Flight_exist() throws IOException {
        FlightMap flight_details = FlightMap.getInstance();
        String flightNumber1 = "SJ456";
        String flightNumber2 = "BY1110";
        csv_flight_parser();
        Assertions.assertTrue(flight_details.Flight_available_seats.containsKey(flightNumber1));
        Assertions.assertFalse(flight_details.Flight_available_seats.containsKey(flightNumber2));
    }

    @DisplayName("Set the Current State to:Flight Exist,FlightNotExist,ValidBooking,ConfirmBooking and check")
    @Test
    void set_state() {
        BookingOperation.currentState= new FlightExist();
        assertSame("In FlightExist State", BookingOperation.currentState.toString());
        BookingOperation.currentState= new ValidBooking();
        assertSame("In ValidBooking State", BookingOperation.currentState.toString());
        BookingOperation.currentState= new ConfirmBooking();
        assertSame("In ConfirmBooking State", BookingOperation.currentState.toString());
        BookingOperation.currentState= new FlightNotExist();
        assertSame("In FlightNotExist State", BookingOperation.currentState.toString());
    }

    @DisplayName("Card Validation - valid inputs")
    @ParameterizedTest
    @ValueSource(strings = {"4410000000000000","5520000000000000","5220000000000000","5210000000000000","6011000000000101","341100000000010","371100000000010"}) // testing 5 card operations
    void CardValidation(String CardNumber) {
        CardValidation VisaCard = new VisaCard();
        CardValidation Mastercard = new Mastercard();
        CardValidation Discover = new Discover();
        CardValidation Amex = new Amex();
        VisaCard.setNextChain(Mastercard);
        Mastercard.setNextChain(Discover);
        Discover.setNextChain(Amex);
        assertSame("Valid", VisaCard.validate(CardNumber));
    }

    @DisplayName("Card Validation - Invalid inputs")
    @ParameterizedTest
    @ValueSource(strings = {"","1234 78","371100000000010000","1111111111111111111111"}) // testing 5 card operations
    void CardInValid(String CardNumber) {
        CardValidation VisaCard = new VisaCard();
        CardValidation Mastercard = new Mastercard();
        CardValidation Discover = new Discover();
        CardValidation Amex = new Amex();
        VisaCard.setNextChain(Mastercard);
        Mastercard.setNextChain(Discover);
        Discover.setNextChain(Amex);
        assertSame("invalid", VisaCard.validate(CardNumber));
    }

    @DisplayName("Booking file Parser")
    @ParameterizedTest
    @ValueSource(strings = {"/Users/avinash/Downloads/flights.csv"}) // six numbers
    void csv_booking_parser(String Filename) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(Filename));
    }

    @DisplayName("Flight Seat Availability")
    @Test
    void Flight_seat_availability(){
        int requested_seats =3;
        flight_seats();
        Integer available_seats = flight_details.Flight_available_seats.get("SJ456").get("Economy");
        Assertions.assertTrue(available_seats != null && requested_seats <= available_seats);
    }
}