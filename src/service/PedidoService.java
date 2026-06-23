
package service;

import entities.Pedido;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import exception.ElementoEliminadoException;
import exception.ElementoNoEncontradoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PedidoService {
    
    private List<Pedido> pedidos = new ArrayList<>();
    private long contadorId = 1;
    
    private UsuarioService usuarioService;
    
    public PedidoService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    
    //CREATE
    
    public Pedido crear(LocalDate fecha, Estado estado, FormaPago formaPago, long usuarioId){
    
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        
        if (usuario == null) {
            throw new ElementoNoEncontradoException("El usuario no existe");
        }

        if (usuario.isEliminado()) {
            throw new ElementoEliminadoException("El usuario está eliminado");
        }
        
        Pedido p = new Pedido(fecha, estado, formaPago, usuario, contadorId++, false, LocalDateTime.now());
        
        pedidos.add(p);
        return p;
    }
    
    // READ (solo lista los no eliminados soft) //
    public List<Pedido> listar(){
        
        List<Pedido> activos = new ArrayList<>();
        // Recorre la lista
        for(int i = 0; i < pedidos.size(); i++){
            // guarda la categoria recorrrida en la variable c//
            Pedido p = pedidos.get(i);
            // Chequea si c no esta eliminado y lo guarda en la lista activas//
            if(!p.isEliminado()){
                activos.add(p);
            }
             
        }
        return activos;
        
    } 
    
    // READ usando id //
    public Pedido buscarPorId(long id){
        for(int i = 0; i < pedidos.size(); i++){
            Pedido p = pedidos.get(i);
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    // UPDATE
    public boolean actualizar(long id, Estado estado, FormaPago formaPago, long usuarioId) {

        Pedido p = buscarPorId(id);

        if (p == null) {
            throw new ElementoNoEncontradoException("El pedido no existe");
        }

        if (p.isEliminado()) {
            throw new ElementoEliminadoException("El pedido está eliminado");
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (usuario == null) {
            throw new ElementoNoEncontradoException("El usuario no existe");
        }

        if (usuario.isEliminado()) {
            throw new ElementoEliminadoException("El usuario está eliminado");
        }

        p.setEstado(estado);
        p.setFormaPago(formaPago);
        p.setUsuario(usuario);

        return true;
    }
    
    // DELETE (soft)
    public boolean eliminar(long id) {
        Pedido p = buscarPorId(id);

        if (p == null) {
            throw new ElementoNoEncontradoException("El pedido no existe");
        }

        if (p.isEliminado()) {
            throw new ElementoEliminadoException("El pedido ya estaba eliminado");
        }

        p.setEliminado(true);
        return true;
    }
    
    // Metodo Listar pedido para validar los pedidos de un usuario //
    
    public List<Pedido> listarPorUsuario(long usuarioId) {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (!p.isEliminado() && p.getUsuario().getId() == usuarioId) {
                resultado.add(p);
            }
        }

        return resultado;
    }
}
