package ProyectoConcesionaria;
/*
    *Modulo para crear un respaldo de datos que se tienen en el inventario, validar que si aun no hay productos, mostrar un mensaje
    * Mostrar en la tabla los productos que se respaldaran
  */

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CopiaRespaldo {

    Utils utils = new Utils();
    public void CrearCopiaRespaldo(String filePath, String backupPath ){
        List<String> productos;
        productos = utils.openData(filePath);

        if(productos.isEmpty()){
            utils.emptyDataList("| Sin productos para hacer el respaldo aun :)");
        }else {
            System.out.println("Datos a respaldar: : ");
            utils.headerBox();
            for (String single_product : productos) {
                utils.bodyBox(utils.getFieldsProducts(single_product).codigoProducto, utils.getFieldsProducts(single_product).nombreProducto, utils.getFieldsProducts(single_product).unidadesProductoData, utils.getFieldsProducts(single_product).precioProducto, utils.getFieldsProducts(single_product).totalProductPrice, utils.getFieldsProducts(single_product).date);
            }
            utils.boxFormating("_", 145);

            try {
                FileWriter backupFile = new FileWriter(backupPath, true);
                for(String single_product : productos){
                    backupFile.write(single_product+"\n");
                }
                backupFile.close();

                utils.boxFormating("-", 65);
                utils.formatMsg("| Respaldo creado correctamente", 65, true);
                utils.boxFormating("-", 65);
            }catch ( IOException e){
                System.out.println("Oops, no se pudo crear la copia de respaldo intenta de nuevo");
                e.printStackTrace();
            }


        }

    }
}
