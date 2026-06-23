package entities;

import exception.EntradaVaciaException;
import java.time.LocalDateTime;

public class Producto extends Base {
    
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    // CONSTRUCTOR VACÍO
    public Producto() {
        super();
    }

    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public int getStock() { return stock; }
    public String getImagen() { return imagen; }
    public boolean isDisponible() { return disponible; }
    public Categoria getCategoria() { return categoria; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setStock(int stock) { this.stock = stock; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public void setCategoria(Categoria categoria) {
        if(categoria == null){
            throw new EntradaVaciaException("El campo categoria no puede estar vacio");
        }
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Producto[id=%d, Nombre='%s', Precio=%.2f, Stock=%d, Categoria='%s']",
                this.getId(), this.getNombre(), this.getPrecio(), this.getStock(), 
                (this.getCategoria() != null ? this.getCategoria().getNombre() : "N/A")
        );
    }
}