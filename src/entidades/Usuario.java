package entidades;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasena;
    private Rol rol;
    private Set<Pedido> pedidos = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id, boolean eliminado, LocalDateTime createdAt,
                   String nombre, String apellido, String mail,
                   String celular, String contrasena, Rol rol) {
        super(id, eliminado, createdAt);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Set<Pedido> getPedidos() { return Collections.unmodifiableSet(pedidos); }

    public void agregarPedido(Pedido pedido) {
        if (pedido == null) return;
        this.pedidos.add(pedido);
    }

    public int getCantidadPedidos() { return pedidos.size(); }

    @Override
    public String toString() {
        return "Usuario [" + rol + "]: " + nombre + " " + apellido
                + " | Correo: " + mail
                + " | Pedidos: " + pedidos.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(mail, usuario.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}