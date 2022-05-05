package test;

public class Amex implements CardValidation {
    @Override
    public String toString() {
        return "Amex";
    }

    private  CardValidation nextInChain;

    public void setNextChain(CardValidation nextChain) {
            nextInChain = nextChain;
    }

    public String validate(String CardNumber) {
        int length = CardNumber.replaceAll("\\s", "").length();
        String testing = "47";
        if (  (length ==15) && (CardNumber.charAt(0)=='3') && (testing.indexOf(CardNumber.charAt(1)))!=-1)
        {
            return "Valid";
        }
        else {
            return "invalid";
        }
    }
}