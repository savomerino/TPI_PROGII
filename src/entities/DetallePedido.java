package entities;

public class DetallePedido extends Base {
    
    private int cantidad;
    // Se cambia de Double a double primitivo por los conflictos matemáticos en Pedido.java//
    private double subtotal; 
    
    private Producto producto; 

    // constructor vacío//
    public DetallePedido() {
        super();
    }

    // Constructor completo (el que espera Pedido.java en la línea 46)//
    public DetallePedido(int cantidad, double subtotal, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    // --- GETTERS Y SETTERS ---
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    //Este es setSubtotal que daba error.. ahora recibe double primitivo//
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // toString es obligatorio porque heredamos de Base //
    @Override
    public String toString() {
        return String.format("Detalle [Producto=%s, Cantidad=%d, Subtotal=$%.2f]",
                (producto != null ? producto.getNombre() : "N/A"), cantidad, subtotal);
    }
}