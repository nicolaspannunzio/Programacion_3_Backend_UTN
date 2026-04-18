import entidades.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("    INICIANDO SISTEMA FOOD STORE");
        System.out.println("========================================\n");

        // ==========================================
        //         1. CREAR CATEGORIAS
        // ==========================================

        Categoria catFastFood  = new Categoria(1L, false, LocalDateTime.now(), "Fast Food",          "Comidas rapidas y al paso");
        Categoria catSaludable = new Categoria(2L, false, LocalDateTime.now(), "Fitness & Saludable", "Opciones ricas en proteinas y fermentos naturales");
        Categoria catBebidas   = new Categoria(3L, false, LocalDateTime.now(), "Bebidas",             "Bebidas frias y artesanales");

        // ==========================================
        //    2. CREAR PRODUCTOS y asignar categoria
        // ==========================================

        Producto p1  = new Producto(1L,  false, LocalDateTime.now(), "Hamburguesa Clasica",       8500.0,  "Carne, queso, tomate",                  47,  "h1.jpg",   true);
        Producto p2  = new Producto(2L,  false, LocalDateTime.now(), "Pizza Muzzarella",          12000.0, "Salsa, muzzarella, aceitunas",           31,  "p1.jpg",   true);
        Producto p3  = new Producto(3L,  false, LocalDateTime.now(), "Yogurt Griego Fermentado",  4500.0,  "Yogurt artesanal de larga fermentacion", 26,  "y1.jpg",   true);
        Producto p4  = new Producto(4L,  false, LocalDateTime.now(), "Leche de Soya",             3000.0,  "Bebida vegetal casera",                  19,  "l1.jpg",   true);
        Producto p5  = new Producto(5L,  false, LocalDateTime.now(), "Pan de Proteina Air Fryer", 5500.0,  "Con proteina de arveja y ricota",         12,  "pan.jpg",  true);
        Producto p6  = new Producto(6L,  false, LocalDateTime.now(), "Papas Fritas",              4000.0,  "Porcion grande",                         88,  "papas.jpg",true);
        Producto p7  = new Producto(7L,  false, LocalDateTime.now(), "Cerveza Artesanal IPA",     3500.0,  "Pinta de 500ml",                         70,  "ipa.jpg",  true);
        Producto p8  = new Producto(8L,  false, LocalDateTime.now(), "Gaseosa Cola Libre",        2000.0,  "Botella 500ml sin azucar",              144,  "cola.jpg", true);
        Producto p9  = new Producto(9L,  false, LocalDateTime.now(), "Ensalada Completa",         7000.0,  "Mix de verdes y pollo",                  17,  "ens.jpg",  true);
        Producto p10 = new Producto(10L, false, LocalDateTime.now(), "Postre Helado",             3500.0,  "Sabor vainilla",                         42,  "hel.jpg",  true);

        catFastFood.agregarProducto(p1);
        catFastFood.agregarProducto(p2);
        catFastFood.agregarProducto(p6);
        catFastFood.agregarProducto(p10);
        catSaludable.agregarProducto(p3);
        catSaludable.agregarProducto(p4);
        catSaludable.agregarProducto(p5);
        catSaludable.agregarProducto(p9);
        catBebidas.agregarProducto(p7);
        catBebidas.agregarProducto(p8);

        Set<Producto> catalogo = new HashSet<>();
        catalogo.add(p1);  catalogo.add(p2);  catalogo.add(p3);  catalogo.add(p4);  catalogo.add(p5);
        catalogo.add(p6);  catalogo.add(p7);  catalogo.add(p8);  catalogo.add(p9);  catalogo.add(p10);

        // ==========================================
        //         3. CREAR USUARIOS
        // ==========================================

        Usuario user1 = new Usuario(1L, false, LocalDateTime.now(), "Nicolas",   "Nickelhamp", "nico@mail.com",  "123456", "admin123", Rol.ADMIN);
        Usuario user2 = new Usuario(2L, false, LocalDateTime.now(), "Margarita", "Lecaldut",   "marga@mail.com", "987654", "guau123",  Rol.USUARIO);

        // ==========================================
        //         4. CREAR PEDIDOS y asociarlos
        // ==========================================

        Pedido pedido1 = new Pedido(1L, false, LocalDateTime.now(), LocalDate.now(), Estado.CONFIRMADO,   FormaPago.TARJETA);
        pedido1.addDetallePedido(2, p3);
        pedido1.addDetallePedido(1, p5);
        user1.agregarPedido(pedido1);

        Pedido pedido2 = new Pedido(2L, false, LocalDateTime.now(), LocalDate.now(), Estado.PENDIENTE,    FormaPago.EFECTIVO);
        pedido2.addDetallePedido(1, p2);
        pedido2.addDetallePedido(2, p7);
        user1.agregarPedido(pedido2);

        Pedido pedido3 = new Pedido(3L, false, LocalDateTime.now(), LocalDate.now(), Estado.TERMINADO,    FormaPago.TRANSFERENCIA);
        pedido3.addDetallePedido(3, p1);
        pedido3.addDetallePedido(1, p6);
        user2.agregarPedido(pedido3);

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(user1);
        usuarios.add(user2);

        // ==========================================
        //           5. REPORTES EN CONSOLA
        // ==========================================

        System.out.println("========================================");
        System.out.println(" REPORTES DEL SISTEMA");
        System.out.println("========================================");

        System.out.println("\n[ Categorias disponibles ]");
        System.out.println("  - " + catFastFood);
        System.out.println("  - " + catSaludable);
        System.out.println("  - " + catBebidas);

        System.out.println("\n[ Un producto individual ]");
        System.out.println("  " + p1);

        System.out.println("\n[ Catalogo completo de productos ]");
        for (Producto prod : catalogo) {
            System.out.println("  - " + prod);
        }

        System.out.println("\n[ Usuario con mas pedidos ]");
        Usuario usuarioConMasPedidos = usuarios.stream()
                .max(Comparator.comparingInt(Usuario::getCantidadPedidos))
                .orElse(null);

        if (usuarioConMasPedidos != null) {
            System.out.println("  " + usuarioConMasPedidos);
            System.out.println("  Sus pedidos:");
            for (Pedido p : usuarioConMasPedidos.getPedidos()) {
                System.out.println("\n  " + p);
            }
        }

        // ==========================================
        //       6. PRUEBA DE EQUALS CON DUPLICADO
        // ==========================================

        System.out.println("\n========================================");
        System.out.println(" PRUEBA DE EQUALS - DUPLICADO");
        System.out.println("========================================");

        Producto productoImpostor = new Producto(99L, false, LocalDateTime.now(),
                "Hamburguesa Clasica", 500.0, "Hamburguesa trucha", 5, "fake.jpg", true);

        System.out.println("\nProducto nuevo a comparar: " + productoImpostor);
        System.out.println("Comparando contra toda la coleccion...\n");

        boolean fueEncontrado = false;
        for (Producto prod : catalogo) {
            if (prod.equals(productoImpostor)) {
                fueEncontrado = true;
                System.out.println(">> COINCIDENCIA detectada con: " + prod);
                System.out.println(">> equals() retorna TRUE  -> mismo nombre, objeto diferente.");
                System.out.println(">> hashCode impostor: " + productoImpostor.hashCode()
                        + " | hashCode original: " + prod.hashCode());
                break;
            }
        }

        System.out.println(fueEncontrado
                ? "\n>> Prueba EXITOSA - el Set rechazaria al impostor (equals + hashCode consistentes)."
                : "\n>> FALLO - el sistema no detecto el duplicado.");

        int tamanoAntes = catalogo.size();
        catalogo.add(productoImpostor);
        int tamanoDespues = catalogo.size();
        System.out.println("\n>> Tamano del catalogo antes de add:  " + tamanoAntes);
        System.out.println(">> Tamano del catalogo despues de add: " + tamanoDespues);
        System.out.println(tamanoAntes == tamanoDespues
                ? ">> El HashSet ignoro el duplicado correctamente."
                : ">> ERROR: el duplicado fue agregado al Set.");

        System.out.println("\n--- FIN DE LA EJECUCION ---");
    }
}