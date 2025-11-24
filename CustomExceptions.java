package clinic_logic;

public class CustomExceptions {
    
    // Thrown when an ID is searched but not found
    public static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }

    // Thrown when a doctor is already booked at the requested time
    public static class ConflictException extends Exception {
        public ConflictException(String message) {
            super(message);
        }
    }
}