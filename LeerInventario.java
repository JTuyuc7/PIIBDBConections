package ProyectoConcesionaria;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
    ?Mostrar los productos del inventario
 */

public class LeerInventario {

    //? Utils
    Utils utils = new Utils();
    public void mostrarProductosInventario() throws SQLException {

        float cantidad_total = 0;
        int unidadades_totales = 0;

        //? Conexion a base de datos
        DbConection db = new DbConection();
        ResultSet inventario = null;

        try {
            inventario = db.readFromDb("SELECT * FROM " + "producto");
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
