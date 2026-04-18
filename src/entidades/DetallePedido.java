package entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public class DetallePedido extends Base {

    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido() {
    }

    public DetallePedido(Long id, boolean eliminado, LocalDateTime createdAt,
                         int cantidad, Double subtotal, Producto producto) {
        super(id, eliminado, createdAt);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    // Getters y Setters
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    @Override
    public String toString() {
        String prodNombre = (producto != null) ? producto.getNombre() : "Producto desconocido";
        return cantidad + "x " + prodNombre
                + " | $" + producto.getPrecio() + " c/u"
                + " | Subtotal: $" + String.format("%.2f", subtotal);
    }

    // Identidad por producto: en un mismo pedido no puede haber dos detalles
    // del mismo producto (se acumula cantidad en uno solo).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}