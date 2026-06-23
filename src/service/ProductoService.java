package service;

import entities.Producto;
import exception.ElementoNoEncontradoException;
import exception.ValidacionNegocioException;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    
    private List<Producto> productos = new ArrayList<>();
    private Long generadorId = 1L;

    public Producto agregarProducto(Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new ValidacionNegocioException("Error: El precio del producto no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new ValidacionNegocioException("Error: El stock del producto no puede ser negativo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ValidacionNegocioException("Error: El nombre del producto no puede estar vacío.");
        }

        producto.setId(generadorId++);
        producto.setEliminado(false); 
        productos.add(producto);
        return producto;
    }

    public List<Producto> listarProductosActivos() {
        List<Producto> activos = new ArrayList<>();
        for (Producto p : productos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public Producto buscarPorId(Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new ElementoNoEncontradoException("Producto con ID " + id + " no encontrado o fue eliminado.");
    }

    public void eliminarProducto(Long id) {
        Producto p = buscarPorId(id);
        p.setEliminado(true); 
    }
}