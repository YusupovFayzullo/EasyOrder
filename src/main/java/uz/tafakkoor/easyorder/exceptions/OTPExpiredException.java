package uz.tafakkoor.easyorder.exceptions;

public class OTPExpiredException extends RuntimeException {
    public OTPExpiredException(String message) {
        super(message);
    }
}
