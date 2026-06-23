package main;

import entities.*;
import enums.*;
import exception.*;
import service.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    
    // Instanciamos todos los servicios //
    private static final CategoriaService categoriaService = new CategoriaService();
    private static final ProductoService productoService = new ProductoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final PedidoService pedidoService = new PedidoService(usuarioService);

    public static void main(String[] args) {
        // DATOS DE PRUEBA para no arrancar el sistema vacío//
        categoriaService.crear("Bebidas", "Gaseosas y aguas");
        categoriaService.crear("Comidas", "Hamburguesas y pizzas");
        
        Usuario admin = new Usuario();
        admin.setNombre("Admin"); admin.setApellido("Sistema");
        admin.setMail("admin@mail.com"); admin.setRol(Rol.ADMIN);
        usuarioService.agregarUsuario(admin);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1: menuCategorias(); break;
                    case 2: menuProductos(); break;
                    case 3: menuUsuarios(); break;
                    case 4: menuPedidos(); break;
                    case 0:
                        salir = true;
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("⚠️ Opción inválida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número válido.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // ===================//
    // MODULO: "Categoria" //
    // ================== //
    private static void menuCategorias() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE CATEGORÍAS ---");
            System.out.println("1. Listar categorías");
            System.out.println("2. Crear categoría");
            System.out.println("3. Eliminar categoría");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcion) {
                    case 1: 
                        System.out.println("\n--- Lista de Categorías ---");
                        for(Categoria c : categoriaService.listar()) { System.out.println(c.toString()); }
                        break;
                    case 2: 
                        System.out.print("Nombre: "); String nombre = scanner.nextLine();
                        System.out.print("Descripción: "); String desc = scanner.nextLine();
                        categoriaService.crear(nombre, desc);
                        System.out.println("✅ Categoría creada.");
                        break;
                    case 3: 
                        System.out.print("ID a eliminar: "); long id = scanner.nextLong();
                        categoriaService.eliminar(id);
                        System.out.println("✅ Categoría eliminada.");
                        break;
                    case 0: volver = true; break;
                    default: System.out.println("⚠️ Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    // ================= //
    // MODULO: "Usuario" //
    // ================= //
    private static void menuUsuarios() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcion) {
                    case 1: 
                        System.out.println("\n--- Lista de Usuarios ---");
                        List<Usuario> activos = usuarioService.listarUsuariosActivos();
                        if(activos.isEmpty()) System.out.println("No hay usuarios.");
                        for(Usuario u : activos) { System.out.println(u.toString()); }
                        break;
                    case 2: 
                        System.out.print("Nombre: "); String nombre = scanner.nextLine();
                        System.out.print("Apellido: "); String apellido = scanner.nextLine();
                        System.out.print("Email: "); String email = scanner.nextLine();
                        System.out.print("Celular: "); String cel = scanner.nextLine();
                        System.out.print("Rol (1. ADMIN, 2. USUARIO): "); 
                        int rolOpt = scanner.nextInt(); scanner.nextLine();
                        
                        Usuario nuevo = new Usuario();
                        nuevo.setNombre(nombre); nuevo.setApellido(apellido);
                        nuevo.setMail(email); nuevo.setCelular(cel);
                        nuevo.setContraseña("1234"); // Clave genérica para evitar pasos
                        nuevo.setRol((rolOpt == 1) ? Rol.ADMIN : Rol.USUARIO);
                        
                        usuarioService.agregarUsuario(nuevo);
                        System.out.println("✅ Usuario creado.");
                        break;
                    case 3: 
                        System.out.print("ID a eliminar: "); long id = scanner.nextLong();
                        usuarioService.eliminarUsuario(id);
                        System.out.println("✅ Usuario eliminado.");
                        break;
                    case 0: volver = true; break;
                    default: System.out.println("⚠️ Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    // ===================//
    // MODULO: "Productos" //
    // ==================//
    private static void menuProductos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Crear producto");
            System.out.println("3. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1: listarProductos(); break;
                    case 2: crearProducto(); break;
                    case 3: eliminarProducto(); break;
                    case 0: volver = true; break;
                    default: System.out.println("⚠️ Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                scanner.nextLine(); 
            }
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- Lista de Productos Activos ---");
        List<Producto> activos = productoService.listarProductosActivos();
        if (activos.isEmpty()) {
            System.out.println("No hay productos cargados.");
        } else {
            for (Producto p : activos) { System.out.println(p.toString()); }
        }
    }

    private static void crearProducto() {
        System.out.println("\n--- Nuevo Producto ---");
        try {
            System.out.print("Nombre: "); String nombre = scanner.nextLine();
            System.out.print("Descripción: "); String descripcion = scanner.nextLine();
            System.out.print("Precio: $"); double precio = scanner.nextDouble();
            System.out.print("Stock inicial: "); int stock = scanner.nextInt();
            scanner.nextLine(); 

            System.out.println("Categorías disponibles:");
            for(Categoria c : categoriaService.listar()) { System.out.println(c.getId() + ". " + c.getNombre()); }
            System.out.print("ID de Categoría: ");
            long idCat = scanner.nextLong();
            scanner.nextLine();
            
            Categoria cat = categoriaService.buscarPorId(idCat);
            if(cat == null) {
                System.out.println("❌ Error: Categoría inexistente.");
                return;
            }
            
            Producto nuevoProd = new Producto();
            nuevoProd.setNombre(nombre); nuevoProd.setDescripcion(descripcion);
            nuevoProd.setPrecio(precio); nuevoProd.setStock(stock);
            nuevoProd.setDisponible(true); nuevoProd.setCategoria(cat);
            
            productoService.agregarProducto(nuevoProd);
            System.out.println("✅ Producto creado con éxito.");
        } catch (Exception e) {
            System.out.println("🚩 ERROR: " + e.getMessage());
            scanner.nextLine(); 
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        try {
            Long id = scanner.nextLong();
            productoService.eliminarProducto(id); 
            System.out.println("✅ Producto eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("🚩 ERROR: " + e.getMessage());
            scanner.nextLine(); 
        }
    }

    // ==================//
    // MODULO: "Pedidos"  //
    // =================//
    private static void menuPedidos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Crear nuevo pedido");
            System.out.println("3. Eliminar pedido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcion) {
                    case 1: 
                        System.out.println("\n--- Lista de Pedidos ---");
                        for(Pedido p : pedidoService.listar()) { System.out.println(p.toString()); }
                        break;
                    case 2: 
                        crearPedidoCompleto();
                        break;
                    case 3: 
                        System.out.print("ID a eliminar: "); long id = scanner.nextLong();
                        pedidoService.eliminar(id);
                        System.out.println("✅ Pedido eliminado.");
                        break;
                    case 0: volver = true; break;
                    default: System.out.println("⚠️ Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    private static void crearPedidoCompleto() {
        System.out.println("\n--- Creando Factura/Pedido ---");
        try {
            // 1. Elegir Usuario
            System.out.println("Usuarios disponibles:");
            for(Usuario u : usuarioService.listarUsuariosActivos()) { System.out.println(u.getId() + ". " + u.getNombre()); }
            System.out.print("ID del Usuario comprador: ");
            long idUsuario = scanner.nextLong();
            scanner.nextLine();

            // 2. Elegir Pago
            System.out.print("Forma de Pago (1. Tarjeta, 2. Transferencia, 3. Efectivo): ");
            int opcionPago = scanner.nextInt();
            scanner.nextLine();
            FormaPago pago = FormaPago.EFECTIVO;
            if (opcionPago == 1) pago = FormaPago.TARJETA;
            if (opcionPago == 2) pago = FormaPago.TRANSFERENCIA;

            // 3. Crear el pedido "Cabecera"
            Pedido nuevoPedido = pedidoService.crear(LocalDate.now(), Estado.PENDIENTE, pago, idUsuario);

            // 4. Bucle para agregar "Detalles" (Productos)
            boolean seguirAgregando = true;
            while (seguirAgregando) {
                System.out.println("\n--- Agregar Productos ---");
                listarProductos();
                System.out.print("Ingrese ID del producto: ");
                long idProd = scanner.nextLong();
                System.out.print("Ingrese Cantidad: ");
                int cant = scanner.nextInt();
                scanner.nextLine();

                try {
                    Producto prod = productoService.buscarPorId(idProd);
                    nuevoPedido.addDetallePedido(cant, prod.getPrecio(), prod);
                    System.out.println(">> ¡Producto agregado a la cuenta! Subtotal parcial: $" + nuevoPedido.getTotal());
                } catch (Exception e) {
                    System.out.println("⚠️ No se pudo agregar: " + e.getMessage());
                }

                System.out.print("\n¿Desea agregar otro producto al pedido? (S/N): ");
                String rta = scanner.nextLine();
                if (rta.equalsIgnoreCase("N")) {
                    seguirAgregando = false;
                }
            }
            System.out.println("✅ PEDIDO FINALIZADO. Total a cobrar: $" + nuevoPedido.getTotal());

        } catch (Exception e) {
            System.out.println("🚩 Error al crear el pedido: " + e.getMessage());
        }
    }
}