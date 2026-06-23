package entities;

import exception.EntradaVaciaException;
import java.time.LocalDateTime;

public class Categoria extends Base {
    
    private String nombre;
    private String descripcion;

    public Categoria() {
        super();
    }
    
    public Categoria(String nombre, String descripcion, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    
    public void setNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()){
            throw new EntradaVaciaException("El campo no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion == null || descripcion.isEmpty()){
            throw new EntradaVaciaException("El campo no puede estar vacio");
        }
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return String.format("Categoria{id=%d, nombre='%s', descripcion='%s'}",
                this.getId(), this.nombre, this.descripcion);
    }
}