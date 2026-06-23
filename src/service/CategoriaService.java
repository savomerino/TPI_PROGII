
package service;

import entities.Categoria;
import exception.ElementoEliminadoException;
import exception.ElementoNoEncontradoException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService {
    
    private List<Categoria> categorias = new ArrayList<>();
    private long contadorId = 1;
    
    // CREATE
    public Categoria crear(String nombre, String descripcion){
        Categoria c = new Categoria(nombre, descripcion, contadorId++, false, LocalDateTime.now());
        categorias.add(c);
        return c;
    }
    
    // READ (solo lista los no eliminados soft)
    public List<Categoria> listar(){
        
        List<Categoria> activas = new ArrayList<>();
        // Recorre la lista
        for(int i = 0; i < categorias.size(); i++){
            // guarda la categoria recorrrida en la variable c
            Categoria c = categorias.get(i);
            // Chequea si c no esta eliminado y lo guarda en la lista activas
            if(!c.isEliminado()){
                activas.add(c);
            }
             
        }
        return activas;
        
    }
    
    // READ usando id
    public Categoria buscarPorId(long id){
        for(int i = 0; i < categorias.size(); i++){
            Categoria c = categorias.get(i);
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    
    
    // UPDATE 
    public boolean actualizar(long id, String nuevoNombre,String nuevaDescripcion){
        Categoria c = buscarPorId(id);
        if(c == null || c.isEliminado()){
           throw new ElementoNoEncontradoException("La categoria con ID " + id + " no existe");
        }
        if(c.isEliminado()){
            throw new ElementoNoEncontradoException("La categoria con ID " + id + " fue eliminada");
        }
        c.setNombre(nuevoNombre);
        c.setDescripcion(nuevaDescripcion);
        return true;
    }
    
    // DELETE soft (no lo elimina completamente de la lista)
    public boolean eliminar(long id){
    Categoria c = buscarPorId(id);
        if(c == null){
           throw new ElementoNoEncontradoException("La categoria con ID " + id + " no existe");
        }
        if(c.isEliminado()){
            throw new ElementoEliminadoException("La categoria con ID " + id + " ya fue eliminada");
        }
        c.setEliminado(true);
        return true;
    }
    
}
