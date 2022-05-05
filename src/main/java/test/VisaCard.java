package test;

public class VisaCard implements CardValidation {
    private  CardValidation nextInChain;

    @Override
    public String toString() {
        return "VisaCard";
    }

    public void setNextChain(CardValidation nextChain) {
        nextInChain = nextChain;
    }

    public String validate(String CardNumber) {
        int length = CardNumber.replaceAll("\\s", "").length();
        if ((length == 13 || length ==16) && CardNumber.charAt(0)=='4')
        {
            return "Valid";
        }
        else {
            return nextInChain.validate(CardNumber);
        }
    }
}