package test;

public interface CardValidation {
    void setNextChain(CardValidation nextChain);
    String validate(String CardNumber);
}
