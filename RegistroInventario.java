package ProyectoConcesionaria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
/*
    * Metodos para poder agregar un nuevo registro al inventario
 */
public class RegistroInventario {
    Scanner sc = new Scanner(System.in);
    int cantidad_producto = 0;
    String codigo_producto, nombre_producto, precio_unitario, regex = "\\d+";

    public void nuevoRegistro() throws SQLException {

        //? Utilidades
        Utils utils = new Utils();
        //? DB
        DbConection db = new DbConection();

        ResultSet product = null;
        String codigo_existente = "";
        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";

        System.out.println("INGRESA LOS DATOS PARA UN NUEVO REGISTRO \n");

        do {
            //? Codigo de producto
            System.out.print("Ingresa el codigo del producto: ");
            codigo_producto = sc.nextLine();
            product = db.singleProduct(query, codigo_producto);
            if(product != null){
                while (product.next()){
                    codigo_existente = product.getString(1);
                }
            }

            if(codigo_producto.equals(codigo_existente)){
                System.out.println("Codigo existente, por favor ingrese uno nuevo");
            }

            if(codigo_producto.isEmpty()){
                System.out.println("El codigo no puede estar vacio");
            }
        }while (codigo_producto.isEmpty() || codigo_producto.equals(codigo_existente));

        String code_with_no_spaces = "";
        if( codigo_producto.contains(" ")){
            code_with_no_spaces = codigo_producto.replace(" ", "_");
        }else {
            code_with_no_spaces = codigo_producto;
        }

        //? Nombre producto
        do {
            System.out.print("Ingresa el nombre del producto: ");
            nombre_producto = sc.nextLine();

            if(nombre_producto.length() <= 3){
                System.out.println("El nombre del producto debe ser mayor a 3 caracteres");
            }
        }while ( nombre_producto.length() <= 3);

        //? Precio Producto
        do {
            System.out.print("Ingresa el precio del producto: ");
            precio_unitario = sc.nextLine();

            //? Validar que el precio sea mayor a 0
            if( !precio_unitario.matches(regex) ){
                System.out.println("El precio debe ser un numero y no puede estar vacio");
            }else if ( Integer.parseInt(precio_unitario) == 0 ) {
                System.out.println("El precio no puede ser 0");
            }

        }while (precio_unitario.isEmpty() || !precio_unitario.matches(regex) || Integer.parseInt(precio_unitario) == 0);
        String date = utils.dateTimeInfo();
        String queryAddProduct = "INSERT INTO `producto` (`codigoproducto`, `nombreproducto`, `cantidadproducto`, `preciounitario`) VALUES (?, ?, ?, ?)";

        int inserted =  db.insertRecord(queryAddProduct, code_with_no_spaces, nombre_producto, cantidad_producto, Float.parseFloat(precio_unitario) );
        if(inserted == 1){
            utils.boxFormating("-", 65);
            utils.formatMsg("| Producto guardado cxitosamente", 65, true);
            utils.boxFormating("-", 65);
        }else {
            utils.boxFormating("-", 65);
            utils.formatMsg("| Oops, Algo sali mal :(", 65, true);
            utils.boxFormating("-", 65);
        }
    }
}
