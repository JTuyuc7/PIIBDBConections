## Laboratorio Programacion II Seccion "B"

### El proyecto seleccionado para desarrolllar este proyecto es una concesionaria de vehiculos, desde automoviles de lujo, camionetas, limusinas, motocicletas, vehiculos de carga y mas.


 La estructura del proyecto consta de la siguiente forma
    
* Main - Este es el archivo principal de la applicacion, es el lugar donde se muestra la bienvenida al mismo y donde se encuentran las opciones del menu
* Menu - Archivo donde se encuentran las opciones desde el menu principal, opcion invalida, nueva operacion y salir del programa.
* RegistroInventario - Archivo donde se encuentra la logica de la aplicacion para ingresar un nuevo registro al inventario, en el archivo podemos encontrar validaciones para los campos correspondientes, Ej, el codigo del producto no puede ir vacio, validacion que el codigo sea unico y tambien poder guardar el nuevo registro en un docummento .txt
* LeerInventario - Archivo donde se hace la consulta al archivo donde se encuentran los registros almacenados.
* IngresoInventario - Archivo donde se ingresa la cantidad de un producto, este cuenta con distintas validaciones para buscar de forma dinamica mediante el codigo del producto si este existe o no, seguidamente actualizar la cantidad a registrar y almacenar la nueva informacion en el archivo correspondiente.
* ExtraerProductoInventario - Archivo que contiene la logica de restar la cantidad al producto mediante su codigo, este contiene la logica de no poder restar (extraer) la cantidad mayor a la que se tiene en existencia.
* Utils - Archivo de utilidades donde se tienen metodos que se reutilizan en distintos archivos, como darle formato al menu de opciones, formato a la tabla de registros etc.

Se agrego la columna y logica para mostrar la fecha de creacion/modificacion

#### Para Poder ejecutar la applicacion
* En el archivo ```Main``` Se debe proporcionar un ```Path``` que es donde de almacenaran los registros, este puede estar en cualquier parte del ordenador, pero debe cumplir con este formato ```"C:\\MiUser\\MiCompu\\Desktop\\MiEquipo\\proyectos\\miCarpeta\\inventario.txt"```
* Se debe ejecutar el archivo principal ```Main``` y este llamara los diferentes metodos en los distintos archivos.

