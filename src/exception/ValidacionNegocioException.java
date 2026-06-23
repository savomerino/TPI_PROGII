package exception;

/**
 * Excepción personalizada para manejar exclusivamente las violaciones 
 * a las reglas de negocio estipuladas en la rúbrica (ej: stock < 0, email duplicado).
 * Extiende de RuntimeException para no obligar a declarar throws en cada firma.
 */
public class ValidacionNegocioException extends RuntimeException {
    
    public ValidacionNegocioException(String mensaje) {
        super(mensaje);
    }
}