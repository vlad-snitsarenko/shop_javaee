package by.epam.exceptions;

public class SourceException extends Exception {

    Exception e = null;

    public SourceException(Exception e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return e.getMessage();
    }


}
