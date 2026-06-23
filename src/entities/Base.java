package entities;

import java.time.LocalDateTime;

public abstract class Base {
    
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
    
    // Soluciona el error "no suitable constructor"//
    public Base() {
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Base(Long id, boolean eliminado, LocalDateTime createdAt) {
        this.id = id;
        this.eliminado = eliminado;
        this.createdAt = createdAt;
    }
    
    public Base(boolean eliminado, LocalDateTime createdAt) {
        this(null, eliminado, createdAt); 
    }
    
    public Long getId() { return id; }
    
    // Necesario para que los Services asignen IDs autoincrementales//
    public void setId(Long id) { this.id = id; } 

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public abstract String toString(); 
}