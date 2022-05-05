package test;

import java.util.Objects;

public class ValidBooking implements  BookingState{
    @Override
    public String toString() {
        return "In ValidBooking State";
    }

    public static String CardValidation(String CardNumber) {
        CardValidation VisaCard = new VisaCard();
        CardValidation Mastercard = new Mastercard();
        CardValidation Discover = new Discover();
        CardValidation Amex = new Amex();
        VisaCard.setNextChain(Mastercard);
        Mastercard.setNextChain(Discover);
        Discover.setNextChain(Amex);
        return VisaCard.validate(CardNumber);
    }

    @Override
    public void operation(BookingOperation state) {
        if (Objects.equals(CardValidation(state.CardNumber), "Valid")){
            BookingOperation.set_state(new ConfirmBooking());
            state.operation();
        }

        else{
            String error = "Please enter correct booking details for " + state.Name + ": invalid card number";
            state.Error_messages.add(error);
        }

    }
}
