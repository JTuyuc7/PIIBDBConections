package ProyectoConcesionaria;

import java.sql.SQLException;
import java.util.List;

/*
    *Entrada principal de programa, lugar donde se maneja el menu principal y sus opciones
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        String PATH = "inventario.txt";
        String BACKUPPATH = "backupInventario.txt";
        int opcion, nuevo_registro, ver_de_nuevo = 0, nuevo_ingreso = 0, nueva_extraccion = 0, num_columns = 6;
        List<String> products;

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
        //? Utils
        Utils utils = new Utils();

        products = utils.openData(PATH);

        System.out.println("Bienvenido a la concesionaria DriveXport");

        do {
            //? Mostrar opciones menu
            opcion = menu.opcionesMenu();

            switch (opcion) {
                case 1: {
                    do {
                        registro.nuevoRegistro(PATH);
                        nuevo_registro = menu.nuevaOperacion("Desea agregar un nuevo producto");
                    } while (nuevo_registro == 1);
                    break;
                }
                case 2 : {
                    do {
                        leerInventario.mostrarProductosInventario(PATH, num_columns);
                        if(!products.isEmpty()){
                            ver_de_nuevo = menu.nuevaOperacion("Desea ver de nuevo los registros?");
                        }
                    } while (ver_de_nuevo == 1);
                    break;
                }
                case 3 : {
                    do {
                        ingresoProducto.ingresoProductoInventario(PATH);
                        if(!products.isEmpty()){
                            nuevo_ingreso = menu.nuevaOperacion("Desa Ingresar un nuevo producto?");
                        }
                    }while (nuevo_ingreso == 1);
                    break;
                }
                case 4 :{
                    do {
                        extraerProductoInventario.extraerProducto(PATH);
                        if(!products.isEmpty()){
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
