package org.xstore.gestion;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.xstore.gestion.tablas.Carrito;
import org.xstore.gestion.tablas.auxiliares.Cliente;
import org.xstore.gestion.tablas.Pedido;
import org.xstore.gestion.tablas.auxiliares.Producto;

public class GestionDePedidosYCarritos {
    public static void main(String[] args) {
        // Generaremos el menu para interactuar
        int opcionSeleccionada;
        Scanner leerOpcion = new Scanner(System.in);
        int numeroDeOpciones = 4;

        do {
            System.out.println("\n Bienvenido a XDATA");
            System.out.println("-------------------------");
            mostrarMenu();
            opcionSeleccionada = leerOpcion.nextInt();

            switch (opcionSeleccionada) {

                case 1:
                    insertarDatosEnTabla();
                    break;
                case 2:
                    obtenerLosProductosEnPedidoSegunId(440);
                    break;
                case 3:
                    modificarClienteDeCarritoSegunId(500, 320);
                    break;
                case 4:
                    System.out.println("Hasta la proxima");
                    System.out.println("Este programa a sido creado por: AndresX");
                    System.exit(0);
                    break;
                default:

                    System.out.println("Opcion no encontrada. vuelva a intentarlo.");
            }

        } while (opcionSeleccionada != numeroDeOpciones);

    }

    /**
     * Muestra las opciones de menu que implemente la GestionDePedidosYCarritos
     */
    private static void mostrarMenu() {
        System.out.println("Bienvenid@ a XStore");
        System.out.println("-------------------------");
        System.out.println("Tabla: Carrito");
        System.out.println("-------------------------");
        System.out.println("Tabla: Pedido");
        System.out.println("-------------------------");
        System.out.println("Conectado y en marcha");
        System.out.println("-------------------------");
        System.out.println("Estas son las opciones: ");
        System.out.println("Op.1: Insertar los datos en tablas");
        System.out.println("Op.2: Mostrar los datos en tabla pedido");
        System.out.println("Op.3: Modificar cliente de carrito segun id");
        System.out.println("Op.4: Salir");
        System.out.println("-------------------------");
        System.out.println("Selecciona una opción" + "\n" + "-------------------------");
    }

    //A continuación el metodo de insertar datos en tablas.

    /** Este método se utiliza para insertar datos en las tablas de la base de datos.
     * Agrega una lista de carritos y pedidos a la base de datos. Los datos de la lista son predefinidos dentro del método.
     * @throws Exception si ocurre un error al conectarse a la base de datos o al insertar datos
     */
    private static void insertarDatosEnTabla() {
        // Configuramos la sesion.
        SessionFactory sesion = new Configuration().configure().buildSessionFactory();
        Session entrada = null;

        try {
            // Se crea la sesion
            entrada = sesion.openSession();

            //Iniciamos una transaccion.
            Transaction transaccion = entrada.beginTransaction();

            // Agregar los carritos a la tabla
            List<Carrito> carritos = new LinkedList<Carrito>();
            carritos.add(new Carrito(500, 340, 40));
            carritos.add(new Carrito(520, 320, 650));
            carritos.add(new Carrito(570, 300, 105));
            carritos.add(new Carrito(510, 310, 160));

            // Crear la lista de pedidos
            List<Pedido> pedidos = new LinkedList<Pedido>();
            pedidos.add(new Pedido(400, 2, 100, 500));
            pedidos.add(new Pedido(410, 1, 190, 520));
            pedidos.add(new Pedido(411, 1, 180, 520));
            pedidos.add(new Pedido(412, 1, 100, 520));
            pedidos.add(new Pedido(420, 1, 110, 570));
            pedidos.add(new Pedido(422, 1, 122, 570));
            pedidos.add(new Pedido(450, 3, 155, 510));
            pedidos.add(new Pedido(440, 1, 110, 510));
            pedidos.add(new Pedido(460, 1, 140, 510));

            // Guardar los datos en la transacción
            for (Carrito carrito : carritos) {
                entrada.save(carrito);
            }

            for (Pedido pedido : pedidos) {
                entrada.save(pedido);
            }

            // Comprobamos que se haga la transaccion
            transaccion.commit();

            // Mostramos todos los pedidos y carritos
            obtenerDatosDeTablaPedido();
            obtenerDatosDeTablaCarrito();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (entrada != null) {
                // Cerramos la sesion
                entrada.close();
            }
        }
    }

    //A continuación los metodos para obtener datos que relacionen nuestras tablas con otras y dar resultado.

    /**Este método se encarga de obtener el producto asociado a un pedido específico de la tabla "Pedido" en la base de datos, buscándolo por su id.
     *  El id del pedido es proporcionado como parámetro.
     *  La función configura la sesión, inicia la transacción, realiza la consulta correspondiente y muestra los datos relevantes del producto obtenido.
     *  Si ocurre un error, se realiza un rollback de la transacción.
     *  @param idPedido el id del pedido del cual se desea obtener el producto asociado
     */
    private static void obtenerLosProductosEnPedidoSegunId(int idPedido) {
        // Configurar la sesión
        SessionFactory sesion = new Configuration().configure().buildSessionFactory();
        Session entrada = null;

        try {
            // Crear la sesión
            entrada = sesion.openSession();

            // Iniciar transacción
            entrada.beginTransaction();

            // Obtener el pedido con el ID correspondiente
            Query consulta = entrada.createQuery("FROM Pedido WHERE id = :idPedido");
            consulta.setParameter("idPedido", idPedido);
            Pedido pedido = (Pedido) consulta.uniqueResult();

            // Obtener el producto asociado al pedido
            consulta = entrada.createQuery("FROM Producto WHERE id = :idProducto");
            consulta.setParameter("idProducto", pedido.getProducto());
            Producto producto = (Producto) consulta.uniqueResult();

            // Mostrar los datos relevantes del producto
            System.out.println("A continuacion se obtendrá el producto segun id"
                    + "\n -------------------------"
                    + "\n El id del pedido es:" + idPedido
                    + "\n El producto es: " + producto.getNombreDeProducto());

            // Confirmar transacción
            entrada.getTransaction().commit();
        } catch (Exception e) {
            // Si ocurre un error, hacer un rollback de la transacción
            assert entrada != null;
            entrada.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /** Este metodo modifica el cliente de un carrito según su ID en la base de datos.
     * @param idCarrito el ID del carrito que se desea modificar.
     * @param nuevoCliente el ID del nuevo cliente que se desea asignar al carrito.
     */
    private static void modificarClienteDeCarritoSegunId(int idCarrito, int nuevoCliente) {
        // Configuramos la sesión.
        SessionFactory sesion = new Configuration().configure().buildSessionFactory();
        Session entrada = null;

        try {
            // Se crea la sesión
            entrada = sesion.openSession();

            // Iniciar transacción
            entrada.beginTransaction();

            // Obtener el carrito con el id correspondiente
            Query consulta = entrada.createQuery("FROM Carrito WHERE id = :idCarrito");
            consulta.setParameter("idCarrito", idCarrito);
            Carrito carrito = (Carrito) consulta.uniqueResult();

            // Obtener el cliente con el id correspondiente
            consulta = entrada.createQuery("FROM Cliente WHERE idCliente = :nuevoCliente");
            consulta.setParameter("nuevoCliente", nuevoCliente);
            Cliente cliente = (Cliente) consulta.uniqueResult();

            // Imprimir el estado actual del carrito y del cliente
            System.out.println("El cliente actual es: " + carrito.getId()
                                + "\n El id del cliente es: " + carrito.getCliente());

            // Asignar el nuevo cliente al carrito
            carrito.setCliente(nuevoCliente);

            // Actualizar el carrito en la base de datos
            entrada.update(carrito);

            // Obtener el nuevo cliente con el id correspondiente
            consulta = entrada.createQuery("FROM Cliente WHERE idCliente = :nuevoCliente");
            consulta.setParameter("nuevoCliente", nuevoCliente);
            cliente = (Cliente) consulta.uniqueResult();

            // Confirmar transacción
            entrada.getTransaction().commit();

            // Imprimir el estado actualizado del carrito
            System.out.println("El id del carrito a modificar: " + carrito.getId());

            // Mostrar los datos de la tabla actualizada
            System.out.println("\n-------------------------"
                    + "\n El id del carrito a modificar: " + carrito.getId()
                    + "\n El id del cliente insertado es: " + cliente.getIdCliente()
                    + "\n El nombre del cliente es: " + cliente.getNombreDeCliente());
        } catch (Exception e) {
            // Si ocurre un error, hacer un rollback de la transacción
            assert entrada != null;
            entrada.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    //A continuación los metodos para ver todos los datos en las tablas.
    /**Este método obtiene los datos de la tabla "Pedido" en la base de datos y los imprime por consola.
     */
    private static void obtenerDatosDeTablaPedido() {
        // Configuramos la sesion de entrada
        SessionFactory sesion = new Configuration().configure().buildSessionFactory();
        Session entrada = sesion.openSession();

        // lanzamos una hql con consulta general
        Query<Pedido> query = entrada.createQuery("from Pedido");
        // Recorremos la lista de datos que nos devuelve
        List<Pedido> consulta = query.list();
        // mientras haya datos vaya recorriendo cada departamento encontrado.
        for (Pedido pedido : consulta) {
            System.out.println("-------------------------" + "\n" + "Este es el pedido "
                    + "\n El id del pedido es: " + pedido.getIdPedido()
                    + "\n La cantidad de productos es:  " + pedido.getCantidad()
                    + "\n El producto es: " + pedido.getProducto()
                    + "\n El carrito del pedido es: " + pedido.getCarrito()
            );
        }
        entrada.close();
    }

    /**Este método obtiene los datos de la tabla "Carrito" en la base de datos y los imprime por consola.
     */
    private static void obtenerDatosDeTablaCarrito() {

        // Configuramos la sesion de entrada
        SessionFactory sesion = new Configuration().configure().buildSessionFactory();
        Session entrada = sesion.openSession();

        // lanzamos una hql con consulta general
        Query<Carrito> query = entrada.createQuery("from Carrito");
        // Recorremos la lista de datos que nos devuelve
        List<Carrito> consulta = query.list();
        // mientras haya datos vaya recorriendo cada departamento encontrado.
        for (Carrito carrito : consulta) {
            System.out.println("-------------------------" + "\n" + "Este es el carrito "
                    + "\n El id del carrito es: " + carrito.getId()
                    + "\n El cliente del carrito es: " + carrito.getCliente()
                    + "\n El total a pagar es: " + carrito.getTotal() + "€.");
        }
        entrada.close();
    }
}


