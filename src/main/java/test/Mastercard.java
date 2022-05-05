package test;

public class Mastercard implements CardValidation {
    @Override
    public String toString() {
        return "Mastercard";
    }

    private  CardValidation nextInChain;

    public void setNextChain(CardValidation nextChain) {
        nextInChain = nextChain;
    }

    public String validate(String CardNumber) {
        int length = CardNumber.replaceAll("\\s", "").length();
        String testing = "12345";
        if (  (length ==16) && (CardNumber.charAt(0)=='5') && (testing.indexOf(CardNumber.charAt(1)))!=-1)
        {
            return "Valid";
        }
        else {
            return nextInChain.validate(CardNumber);
        }
    }
}