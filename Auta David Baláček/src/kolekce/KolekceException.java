package kolekce;

public class KolekceException extends Exception {

    KolekceException(String message) {
        super(message);
    }

    KolekceException() {
       super();      
    }
}
