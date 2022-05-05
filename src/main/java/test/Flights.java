package test;

class Flights {
    private String Category;
    private String Flight_number;
    private Integer AvailableSeats;
    private Integer Price;
    private String Arrival;
    private String Departure;

    public Flights(String category, String flight_number, Integer availableSeats, Integer price, String arrival, String departure) {
        this.Category = category;
        this.Flight_number = flight_number;
        this.AvailableSeats = availableSeats;
        this.Price = price;
        this.Arrival = arrival;
        this.Departure = departure;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getFlight_number() {
        return Flight_number;
    }

    public void setFlight_number(String flight_number) {
        Flight_number = flight_number;
    }

    public Integer getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        AvailableSeats = availableSeats;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getArrival() {
        return Arrival;
    }

    public void setArrival(String arrival) {
        Arrival = arrival;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "Category='" + Category + '\'' +
                ", Flight_number='" + Flight_number + '\'' +
                ", AvailableSeats=" + AvailableSeats +
                ", Price=" + Price +
                ", Arrival='" + Arrival + '\'' +
                ", Departure='" + Departure + '\'' +
                '}';
    }
}