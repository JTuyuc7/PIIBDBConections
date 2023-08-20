package ProyectoConcesionaria;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
    * Metodos para poder agregar un nuevo registro al inventario
 */
public class RegistroInventario {
    Scanner sc = new Scanner(System.in);
    int cantidad_producto = 0;
    String codigo_producto, nombre_producto, precio_unitario, datos_nuevo_registro, regex = "\\d+";

    public void nuevoRegistro(String fileName) {

        //? Utilidades
        Utils utils = new Utils();
        List<String> products;
        List<String> code_products = new ArrayList<>();
        String temp_product_code = "";

        //? Productos
        products = utils.openData(fileName);

        System.out.println("INGRESA LOS DATOS PARA UN NUEVO REGISTRO \n");
        for( String product_code : products){
            temp_product_code = utils.getFieldsProducts(product_code).codigoProducto;
            code_products.add(temp_product_code);
        }
        do {
            //? Codigo de producto
            System.out.print("Ingresa el codigo del producto: ");
            codigo_producto = sc.nextLine();

            if(code_products.contains(codigo_producto)){
                System.out.println("Codigo existente, por favor ingrese uno nuevo");
            }

            if(codigo_producto.length() == 0 ){
                System.out.println("El codigo no puede estar vacio");
            }

        }while (codigo_producto.length() == 0 || code_products.contains(codigo_producto));

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

        // List<String> nombreProductosList = Arrays.asList(nombre_producto.split("\\s+"));
        String transformed = nombre_producto.replace(" ", "-");

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
        //TODO: para actualizar o eliminar ver si es necesario separar los registros por comas o por algun otro elemento
        datos_nuevo_registro = code_with_no_spaces + " " + transformed + " " + cantidad_producto + " " + precio_unitario + " " + date + "\n";
        try {
            FileWriter registro = new FileWriter(fileName, true);
            registro.write(datos_nuevo_registro);
            registro.close();
            utils.boxFormating("-", 65);
            utils.formatMsg("| Producto guardado cxitosamente", 65, true);
            utils.boxFormating("-", 65);
        }catch (IOException e){
            System.out.println("No se pudo crear el registro :(");
            e.printStackTrace();
        }
    }
}
