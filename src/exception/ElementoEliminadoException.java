package exception;

public class ElementoEliminadoException extends RuntimeException {
    public ElementoEliminadoException(String mensaje){
        super(mensaje);
    }
}