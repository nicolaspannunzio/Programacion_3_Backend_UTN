package entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Base {

    protected Long id;
    protected boolean eliminado;
    protected LocalDateTime createdAt;

    public Base() {
    }

    public Base(Long id, boolean eliminado, LocalDateTime createdAt) {
        this.id = id;
        this.eliminado = eliminado;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // --- CONSIGNA: toString / equals / hashCode en cada clase ---

    // La identidad base se apoya en el id. Las subclases pueden (y deben)
    // sobreescribir si su identidad natural es otro campo (ej: mail en Usuario).
    @Override
    public String toString() {
        return "Base [id=" + id + ", eliminado=" + eliminado + ", createdAt=" + createdAt + "]";
    }

    // Dos entidades son iguales si tienen el mismo id no-nulo.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        if (this.id == null || base.id == null) return false;
        return Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}