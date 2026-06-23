package entities;

import enums.Estado;
import enums.FormaPago;
import interfaces.Calculable;
import exception.ValidacionNegocioException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {
    
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario; // Relación obligatoria
    private List<DetallePedido> detalles;

    // 1. CONSTRUCTOR VACÍO//
    public Pedido() {
        super();
        this.detalles = new ArrayList<>();
        this.total = 0.0;
        this.estado = Estado.PENDIENTE;
        this.fecha = LocalDate.now();
    }

    // 2. CONSTRUCTOR COMPLETO //
    public Pedido(LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario, Long id, boolean eliminado, LocalDateTime createdAt) {
        super(id, eliminado, createdAt); // Le pasa el ID y fechas a la clase Base
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.total = 0.0; // Arranca en 0 hasta que le agregues detalles
        this.detalles = new ArrayList<>(); // Inicializamos la lista vacía
    }

    @Override
    public void calcularTotal() {
        double sumaParcial = 0.0;
        for (DetallePedido detalle : detalles) {
            if (!detalle.isEliminado()) {
                sumaParcial += detalle.getSubtotal();
            }
        }
        this.total = sumaParcial;
    }

    public void addDetallePedido(int cantidad, double precio, Producto producto) {
        if (cantidad <= 0) {
            throw new ValidacionNegocioException("Error: La cantidad en el detalle debe ser mayor a 0.");
        }
        if (producto.getStock() < cantidad) {
            throw new ValidacionNegocioException("Error: Stock insuficiente (" + producto.getStock() + " disp.) para el producto: " + producto.getNombre());
        }

        // Usamos el constructor completo de DetallePedido
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, cantidad * precio, producto);
        
        this.detalles.add(nuevoDetalle);
        this.calcularTotal(); 
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido dp : detalles) {
            if (dp.getProducto().getId().equals(producto.getId()) && !dp.isEliminado()) {
                return dp;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido dp = findeDetallePedidoByProducto(producto);
        if (dp != null) {
            dp.setEliminado(true); 
            this.calcularTotal();  
        } else {
            throw new ValidacionNegocioException("El producto no forma parte de este pedido.");
        }
    }

    // --- GETTERS Y SETTERS COMPLETOS ---
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }

    @Override
    public String toString() {
        return String.format("Pedido[ID=%d, Fecha=%s, Estado=%s, Total=%.2f, Usuario=%s]",
                getId(), fecha, estado, total, (usuario != null ? usuario.getNombre() : "N/A"));
    }
}