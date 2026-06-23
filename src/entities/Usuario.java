package entities;

import enums.Rol;
import java.time.LocalDateTime;

public class Usuario extends Base{
    
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;

    public Usuario() {
        super();
    }

    public Usuario(String nombre, String apellido, String mail, String celular, String contraseña, Rol rol, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id,eliminado, createdAt);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getMail() { return mail; }
    public String getCelular() { return celular; }
    public Rol getRol() { return rol; }
    public String getContraseña() { return contraseña; }

    public void setRol(Rol rol) { this.rol = rol; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setMail(String mail) { this.mail = mail; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    @Override
    public String toString() {
        return String.format(
            "USUARIO: %s %s | Id: %d | Mail: %s | Rol: %s",
             this.nombre, this.apellido, this.getId(), this.mail, this.rol
        );
    }
}