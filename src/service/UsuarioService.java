package service;

import entities.Usuario;
import exception.ElementoNoEncontradoException;
import exception.ValidacionNegocioException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    
    private List<Usuario> usuarios = new ArrayList<>();
    private Long generadorId = 1L;

    public Usuario agregarUsuario(Usuario usuario) {
        if (existeEmail(usuario.getMail(), null)) {
            throw new ValidacionNegocioException("Error: El email '" + usuario.getMail() + "' ya se encuentra registrado.");
        }
        if (usuario.getMail() == null || usuario.getMail().trim().isEmpty()) {
            throw new ValidacionNegocioException("Error: El email no puede estar vacío.");
        }

        usuario.setId(generadorId++);
        usuario.setEliminado(false);
        usuarios.add(usuario);
        return usuario;
    }

    public void editarUsuario(Long id, Usuario datosNuevos) {
        Usuario existente = buscarPorId(id);
        
        if (existeEmail(datosNuevos.getMail(), id)) {
            throw new ValidacionNegocioException("Error: El email provisto ya pertenece a otro usuario.");
        }
        
        existente.setNombre(datosNuevos.getNombre());
        existente.setApellido(datosNuevos.getApellido());
        existente.setMail(datosNuevos.getMail());
        existente.setCelular(datosNuevos.getCelular());
        existente.setRol(datosNuevos.getRol());
    }

    private boolean existeEmail(String email, Long idExcluido) {
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(email) && !u.isEliminado()) {
                if (idExcluido != null && u.getId().equals(idExcluido)) {
                    continue;
                }
                return true; 
            }
        }
        return false;
    }

    public Usuario buscarPorId(Long id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new ElementoNoEncontradoException("Usuario no encontrado o fue eliminado.");
    }
    
    public void eliminarUsuario(Long id) {
        Usuario u = buscarPorId(id);
        u.setEliminado(true); // Baja lógica //
    }

    public List<Usuario> listarUsuariosActivos() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                activos.add(u);
            }
        }
        return activos;
    }
}