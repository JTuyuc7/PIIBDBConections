package ProyectoConcesionaria;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class IngresoProducto {

    //? Utils
    Utils utils = new Utils();
    public void ingresoProductoInventario(String filePath){

        List<String> proudctos, temp_products_non_matches = new ArrayList<>();
        String codigo_producto_busqueda, temp_product_code = "", temp_product_name = "", less_one_data, temp_date = "", new_date_ingreso = "";
        String non_match_product_code = "", non_match_product_name = "", new_data_to_update = "", non_matches_date = "";
        int temp_unities_data = 0, non_match_product_unities = 0, nueva_existencia;;
        float temp_price_product = 0f, non_match_product_price = 0f, temp_total_price = 0f, new_total_to_show = 0f;
        int unities_to_update;
        Scanner sc = new Scanner(System.in);
        //? Productos
        proudctos = utils.openData(filePath);

        if(proudctos.size() == 0){
            System.out.println("Para comenzar ingresa un nuevo producto");
            return;
        }
            do {
                System.out.print("Ingresa el codigo de producto a ingresar: ");
                codigo_producto_busqueda = sc.nextLine();
                if (codigo_producto_busqueda.length() == 0){
                    System.out.println("El codigo no puede estar vacio :)");
                }
            }while (codigo_producto_busqueda.length() == 0);

            // * Validar cuando se ingrese el codigo
            if(proudctos.size() == 0){
                utils.emptyDataList("| Aun no hay productos en la Base de datos");
            }else {
                for(String single_product : proudctos){
                    temp_product_code = utils.getFieldsProducts(single_product).codigoProducto;
                    if(Objects.equals(temp_product_code, codigo_producto_busqueda)){
                        temp_product_code = utils.getFieldsProducts(single_product).codigoProducto;
                        temp_product_name = utils.getFieldsProducts(single_product).nombreProducto;
                        temp_unities_data = utils.getFieldsProducts(single_product).unidadesProductoData;
                        temp_price_product = utils.getFieldsProducts(single_product).precioProducto;
                        temp_total_price = utils.getFieldsProducts(single_product).totalProductPrice;
                        temp_date = utils.getFieldsProducts(single_product).date;
                    }else {
                        non_match_product_code = utils.getFieldsProducts(single_product).codigoProducto;
                        non_match_product_name = utils.getFieldsProducts(single_product).nombreProducto;
                        non_match_product_unities = utils.getFieldsProducts(single_product).unidadesProductoData;
                        non_match_product_price = utils.getFieldsProducts(single_product).precioProducto;
                        non_matches_date = utils.getFieldsProducts(single_product).date;
                        less_one_data = non_match_product_code + " " + non_match_product_name + " " + non_match_product_unities + " " + non_match_product_price + " "+ non_matches_date;
                        temp_products_non_matches.add(less_one_data);
                    }

                }
                if( temp_products_non_matches.size() == proudctos.size()){
                    utils.emptyDataList("| Lo siento, producto no existe");
                }else {
                    utils.headerBox();
                    utils.bodyBox(codigo_producto_busqueda, temp_product_name,temp_unities_data, temp_price_product,temp_total_price, temp_date);
                    utils.boxFormating("-", 145);

                    do {
                        System.out.println("Ingrese la nueva cantidad en existencia: ");
                        nueva_existencia = sc.nextInt();

                        if(nueva_existencia < 0){
                            System.out.println("La existencia no puede ser menor a 0");
                        }
                    }while (nueva_existencia < 0);

                    unities_to_update = temp_unities_data + nueva_existencia;
                    new_total_to_show = temp_price_product * unities_to_update;
                    new_date_ingreso = utils.dateTimeInfo();
                    utils.headerBox();
                    utils.bodyBox(codigo_producto_busqueda, temp_product_name,unities_to_update, temp_price_product,new_total_to_show, new_date_ingreso);
                    utils.boxFormating("-", 145);

                    new_data_to_update = codigo_producto_busqueda + " " + temp_product_name + " " + unities_to_update + " " + temp_price_product + " " +new_date_ingreso;
                    temp_products_non_matches.add(new_data_to_update);

                    //? Reescribir los datos
                    try {
                        FileWriter tempDatos = new FileWriter(filePath, false);
                        for(String new_temp_string: temp_products_non_matches){
                            tempDatos.write(new_temp_string +"\n");
                        }
                        tempDatos.close();
                    }catch (IOException e){
                        System.out.println("No se pudo agregar el registro");
                    }
                }
        }
    }
}
