package Folkeregister.personmodel.exceptions;

public class InvalidPersonException extends IllegalArgumentException {
    public InvalidPersonException(String s) {
        super(s);
    }
}