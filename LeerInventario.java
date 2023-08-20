package ProyectoConcesionaria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

/*
    ?Mostrar los productos del inventario
 */

public class LeerInventario {

    //? Utils
    Utils utils = new Utils();
    public void mostrarProductosInventario(String filePath, int cols) throws SQLException {

        float cantidad_total = 0;
        int unidadades_totales = 0;
        //? Leer los datos para mostrarlos formateados
        List<String> proudctos;
        //? Productos
        proudctos = utils.openData(filePath);

        //? Conexion a base de datos
        DbConection db = new DbConection();
        ResultSet inventario = null;
        try {
            inventario = db.readFromDb("SELECT * FROM producto");
        } catch (SQLException e) {
            System.out.println("Oops, no pudimos obtener los productos");
        }

        if(inventario == null){
            utils.emptyDataList("| Lo siento!!! No hay productos ");
        }else {
            utils.headerBox();
            while (inventario.next()){
                String codigo_producto = inventario.getString(1);
                String nombre_producto = inventario.getString(2);
                int cantidad_producto = Integer.parseInt(inventario.getString(3));
                float precio = Float.parseFloat(inventario.getString(4));
                String date = inventario.getString(5);
                unidadades_totales += cantidad_producto;
                cantidad_total += (cantidad_producto * precio);
                utils.bodyBox(codigo_producto, nombre_producto, cantidad_producto, precio, (cantidad_producto * precio), date);
            }
            utils.footerBox(unidadades_totales, cantidad_total);

        }
    }
}

/*
//            for (String single_product : proudctos) {
//                unidadades_totales += utils.getFieldsProducts(single_product).unidadesProductoData;
//                cantidad_total += utils.getFieldsProducts(single_product).totalProductPrice;
//                utils.bodyBox(utils.getFieldsProducts(single_product).codigoProducto, utils.getFieldsProducts(single_product).nombreProducto, utils.getFieldsProducts(single_product).unidadesProductoData, utils.getFieldsProducts(single_product).precioProducto, utils.getFieldsProducts(single_product).totalProductPrice, utils.getFieldsProducts(single_product).date);
//            }
//            utils.footerBox(unidadades_totales, cantidad_total);
 */