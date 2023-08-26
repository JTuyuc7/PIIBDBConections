package ProyectoConcesionaria;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
    *Entrada principal de programa, lugar donde se maneja el menu principal y sus opciones
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        String PATH = "inventario.txt";
        String BACKUPPATH = "backupInventario.txt";
        int opcion, nuevo_registro, ver_de_nuevo = 0, nuevo_ingreso = 0, nueva_extraccion = 0;
        // ? Menu opciones
        Menu menu = new Menu();
        //? Nuevo Registro
        RegistroInventario registro = new RegistroInventario();
        //? Mostrar registros
        LeerInventario leerInventario = new LeerInventario();
        //? Registro Inventario
        IngresoProducto ingresoProducto = new IngresoProducto();
        //? Extraer producto Inventario
        ExtraerProductoInventario extraerProductoInventario = new ExtraerProductoInventario();
        //? Crear Respaldo de datos
        CopiaRespaldo respaldo = new CopiaRespaldo();

        //? DB
        DbConection db = new DbConection();
        ResultSet inventario = null;

        try {
            inventario = db.readFromDb("SELECT codigoproducto, nombreproducto, cantidadproducto, preciounitario, CONVERT_TZ(fecha,'+00:00','-06:00') fecha FROM `producto`");
        } catch (SQLException e) {
            System.out.println("Oops, no pudimos obtener los productos");
        }
        System.out.println("Bienvenido a la concesionaria DriveXport");

        do {
            //? Mostrar opciones menu
            opcion = menu.opcionesMenu();

            // TODO: mover el archivo jar de java a otra locacion

            switch (opcion) {
                case 1: {
                    do {
                        registro.nuevoRegistro();
                        nuevo_registro = menu.nuevaOperacion("Desea agregar un nuevo producto");
                    } while (nuevo_registro == 1);
                    break;
                }
                case 2 : {
                    do {
                        leerInventario.mostrarProductosInventario();
                        if(inventario != null){
                            ver_de_nuevo = menu.nuevaOperacion("Desea ver de nuevo los registros?");
                        }
                    } while (ver_de_nuevo == 1);
                    break;
                }
                case 3 : {
                    do {
                        ingresoProducto.ingresoProductoInventario();
                        if(inventario != null){
                            nuevo_ingreso = menu.nuevaOperacion("Desa Ingresar un nuevo producto?");
                        }
                    }while (nuevo_ingreso == 1);
                    break;
                }
                case 4 :{
                    do {
                        extraerProductoInventario.extraerProducto();
                        if(inventario != null){
                            nueva_extraccion = menu.nuevaOperacion("Desea extraer un nuevo producto");
                        }
                    }while (nueva_extraccion == 1);
                    break;
                }
                case 5 :{
                    respaldo.CrearCopiaRespaldo(PATH, BACKUPPATH);
                    break;
                }
                case 6 :{
                    menu.salirPrograma();
                    break;
                }
                default : {
                    menu.opcionInvalida();
                    break;
                }
            }
        }while (opcion != 6);
    }
}
