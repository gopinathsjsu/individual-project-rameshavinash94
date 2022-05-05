package test;

import java.util.HashMap;

public class FlightMap {
    HashMap < String, HashMap < String, Integer >> Flight_available_seats = new HashMap<>();
    HashMap < String, HashMap < String, Integer >> Flight_price = new HashMap<>();
    private static volatile FlightMap single_instance;
    private static final Object lock = new Object();

    public HashMap < String, HashMap < String, Integer >> getFlight_available_seats() {
        return Flight_available_seats;
    }

    public HashMap < String, HashMap < String, Integer >> getFlight_price() {
        return Flight_price;
    }

    public static FlightMap getInstance() {
        FlightMap single_instance_local_ref = single_instance;
        if (single_instance_local_ref == null)
            synchronized (lock) {
                single_instance_local_ref = single_instance;
                if (single_instance_local_ref == null) {
                    single_instance_local_ref = new FlightMap();
                    single_instance=single_instance_local_ref;
                }
            }
        return  single_instance;
    }
}