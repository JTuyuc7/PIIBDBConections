package ProyectoConcesionaria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class IngresoProducto {

    //? Utils
    Utils utils = new Utils();
    public void ingresoProductoInventario() throws SQLException {

        //? DB
        DbConection db = new DbConection();
        ResultSet inventario = null;
        String codigo_producto_busqueda;
        int nueva_existencia;
        Scanner sc = new Scanner(System.in);

        try {
            inventario = db.readFromDb("SELECT * FROM " + "producto");
        } catch (SQLException e) {
            System.out.println("Oops, no pudimos obtener los productos");
        }finally {
            db.closeConnection();
        }

        if(inventario == null){
            utils.emptyDataList("| Aun no hay productos en la Base de datos");
            return;
        }
            do {
                System.out.print("Ingresa el codigo de producto a ingresar: ");
                codigo_producto_busqueda = sc.nextLine();
                if (codigo_producto_busqueda.isEmpty()){
                    System.out.println("El codigo no puede estar vacio :)");
                }
            }while (codigo_producto_busqueda.isEmpty());

        String query = "SELECT * FROM producto WHERE `codigoproducto` = ?";
        ResultSet product = null;

        product = db.singleProduct(query, codigo_producto_busqueda);

        try {
            product = db.singleProduct(query, codigo_producto_busqueda);
        }catch (SQLException e){
            System.out.println("unable to get the product");
            e.printStackTrace();
        }

        String codigo_producto = "";
        String product_name = "";
        int cantidad_producto = 0;
        float unit_price = 0;
        int amount_to_update = 0;
        String date = "";
        int record_updated = 0;
        if(product != null){
            while (product.next()){
                codigo_producto = product.getString(1);
                product_name = product.getString(2);
                cantidad_producto = Integer.parseInt(product.getString(3));
                unit_price = Float.parseFloat(product.getString(4));
                date = product.getString(5);
            }
        }

        if(Objects.equals(codigo_producto, "")){
            //? Codigo no valido, el producto no se encontro
            System.out.println("Producto no encontrado o codigo no valido.");
            return;
        }

        //?Show details
        utils.headerBox();
        utils.bodyBox(codigo_producto, product_name, cantidad_producto, unit_price, cantidad_producto * unit_price, date);
        utils.boxFormating("-", 145);

        do {
            System.out.println("Ingrese la nueva cantidad en existencia: ");
            nueva_existencia = sc.nextInt();

            if(nueva_existencia <= 0){
                System.out.println("La existencia no puede ser menor a 0");
            }
        }while (nueva_existencia <= 0);
        amount_to_update = cantidad_producto + nueva_existencia;

        String queryUpdate = "UPDATE `producto` SET `cantidadproducto` = ? WHERE `producto`.`codigoproducto` = ?";
        try {
            record_updated = db.updateRecord(queryUpdate, codigo_producto_busqueda, amount_to_update);
            if(record_updated == 1){

                utils.headerBox();
                utils.bodyBox(codigo_producto, product_name, cantidad_producto + nueva_existencia, unit_price, amount_to_update * unit_price, date);
                utils.boxFormating("-", 145);

                utils.boxFormating("*", 65);
                utils.formatMsg("| Producto extraido correctamente", 65, true);
                utils.boxFormating("*", 65);
            }
        }catch (SQLException e){
            utils.boxFormating("*", 65);
            utils.formatMsg("| No se pudo completar el requisito :(", 65, true);
            utils.boxFormating("*", 65);
        }finally {
            db.closeConnection();
        }
    }
}
