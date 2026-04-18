package entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles = new HashSet<>();

    public Pedido() {
        this.total = 0.0;
    }

    public Pedido(Long id, boolean eliminado, LocalDateTime createdAt,
                  LocalDate fecha, Estado estado, FormaPago formaPago) {
        super(id, eliminado, createdAt);
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.total = 0.0;
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Set<DetallePedido> getDetalles() { return detalles; }

    @Override
    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        if (producto == null) {
            System.out.println(">> Error: el producto no puede ser null.");
            return;
        }
        if (!producto.getDisponible()) {
            System.out.println(">> Error: '" + producto.getNombre() + "' no esta disponible.");
            return;
        }
        if (producto.getStock() < cantidad) {
            System.out.println(">> Error: stock insuficiente para '" + producto.getNombre()
                    + "'. Disponible: " + producto.getStock() + ", pedido: " + cantidad);
            return;
        }
        Double subtotal = cantidad * producto.getPrecio();
        Long nuevoId = (long) (detalles.size() + 1);
        DetallePedido nuevoDetalle = new DetallePedido(nuevoId, false, LocalDateTime.now(), cantidad, subtotal, producto);
        detalles.add(nuevoDetalle);
        calcularTotal();
    }

    public Optional<DetallePedido> findDetallePedidoByProducto(Producto producto) {
        if (producto == null) return Optional.empty();
        return detalles.stream()
                .filter(d -> d.getProducto().equals(producto))
                .findFirst();
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        findDetallePedidoByProducto(producto).ifPresent(detalle -> {
            detalles.remove(detalle);
            calcularTotal();
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id)
          .append(" | Fecha: ").append(fecha)
          .append(" | Estado: ").append(estado)
          .append(" | Forma de pago: ").append(formaPago)
          .append(" | Total: $").append(String.format("%.2f", total))
          .append("\n   Detalles:");
        for (DetallePedido d : detalles) {
            sb.append("\n     - ").append(d);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        if (this.id == null || pedido.id == null) return false;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}