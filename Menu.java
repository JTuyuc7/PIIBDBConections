package ProyectoConcesionaria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Utils utils = new Utils();
    Scanner sc = new Scanner(System.in);
    int opcion, opcion_menu_principal;

    //* Menu Opciones
    public Integer opcionesMenu(){
        utils.boxFormating("*", 65);
        utils.formatMsg("| Selecciona una opcion para continuar", 65, true);
        utils.formatMsg("| 1 - Registrar producto en el inventario", 65, true);
        utils.formatMsg("| 2 - Mostrar productos del inventario", 65, true);
        utils.formatMsg("| 3 - Ingresar Producto al inventario", 65, true);
        utils.formatMsg("| 4 - Extraer producto del inventario", 65, true);
        utils.formatMsg("| 5 - Crear respaldo de datos", 65, true);
        utils.formatMsg("| 6 - Salir", 65, true);
        utils.boxFormating("*", 65);
        opcion_menu_principal = sc.nextInt();
        return  opcion_menu_principal;
    }

    //* Opcion invalida
    public void opcionInvalida(){
        System.out.println("Por favor selecciona una opcion correcta :)");
    }

    //* Salir del programa
    public void salirPrograma(){
        System.out.println("Gracias por usar nuestro software, todos los derechos reservados");
    }

    //* Nueva Operacions
    public Integer nuevaOperacion(String mensaje){
        //* Las opciones siempre sean 1 0 las difaults
        utils.boxFormating("*", 65);
        utils.formatMsg("| " + mensaje, 65, true);
        utils.formatMsg("| 1 - [SI] - 0 [NO] ", 65, true);
        utils.boxFormating("*", 65);
        opcion = sc.nextInt();

        return opcion;
    }
}
