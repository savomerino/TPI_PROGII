package exception;

public class EntradaVaciaException extends RuntimeException {
    public EntradaVaciaException(String mensaje){
        super(mensaje);
    }
}