package test;

public class Discover implements CardValidation {
    private  CardValidation nextInChain;

    @Override
    public String toString() {
        return "Discover";
    }

    public void setNextChain(CardValidation nextChain) {
        nextInChain = nextChain;
    }

    public String validate(String CardNumber) {
        int length = CardNumber.replaceAll("\\s", "").length();
        if (  (length ==16) && (CardNumber.startsWith("6011")) )
        {
            return "Valid";
        }
        else {
            return nextInChain.validate(CardNumber);
        }
    }
}