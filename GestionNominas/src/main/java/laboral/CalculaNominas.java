/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * La clase CalculaNominas se encarga de calcular los sueldos de los empleados
 * y mostrar sus datos.
 *
 * @author angel
 *
 */
package laboral;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La clase CalculaNominas calcula y muestra información relacionada los
 * empleados y sus respectivos sueldos.
 */
public class CalculaNominas {

    /**
     * Método main crea empleados, realiza operaciones sobre ellos y muestra
     * información de sus sueldos.
     *
     *
     */
    public static void main(String[] args) throws DatosNoCorrectosException, SQLException, IOException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            Empleado e1 = new Empleado("James Colling", "32000032G,", "M", 1, 1);

            Empleado e2 = new Empleado("Ada Lovelace", "32000031R,", "F");

            escribe(e1);
            escribe(e2);
            System.out.println("----------------------------");

            e2.incrAnyo();
            e1.setCategoria(9);

            escribe(e2);
            escribe(e1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------------------------------------------");

        List<Empleado> listaEmpleados = new ArrayList<Empleado>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(
                    "empleado.txt"));
            String linea;

            // Lee cada línea del archivo
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 5) {
                    String nombre = campos[0].trim();
                    String dni = campos[1].trim();
                    String sexo = campos[2].trim();
                    int categoria = Integer.parseInt(campos[3].trim());
                    int anyos = Integer.parseInt(campos[4].trim());
                    Empleado empleado = new Empleado(nombre, sexo, dni, categoria, anyos);
                    listaEmpleados.add(empleado);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Guarda los sueldos de los empleados en un archivo binario y luego los
         * lee para mostrarlos.
         *
         * @param listaEmpleados La lista de empleados cuyos sueldos se van a
         * guardar.
         */
        try {
            // Guardar sueldos de empleados en un archivo binario
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(
                    "salarios.dat"));

            for (Empleado empleado : listaEmpleados) {

                int sueldo = Nomina.sueldo(empleado);
                String dni = empleado.dni;
                outputStream.writeUTF(dni);
                outputStream.writeInt(sueldo);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Leer y mostrar los sueldos desde el archivo binario
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(
                    "salarios.dat"));
            while (true) {
                try {
                    String dni = inputStream.readUTF();
                    int sueldo = inputStream.readInt();
                    System.out.println("El Dni del empleado es : " + dni + ", El sueldo del empleado es : " + sueldo);
                    System.out.println("----------------------------------------------------------------------------");
                } catch (EOFException e) {
                    // Se llegó al final del archivo
                    break;
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Realiza una serie de actualizaciones en la base de datos de
         * empleados, incrementa en uno al empleado con DNI=32000031R y le pone
         * la categoria 9 al empleado con DNI=32000032G, luego actualiza los
         * datos en la base de datos *
         *
         *
         */
        try {
            conn = DBUtils.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("UPDATE empleados SET anyos= anyos+1 where dni ='32000031R';");
            rs = st.executeQuery("UPDATE empleados SET categoria = 9 where dni = '32000032G'; ");
            rs = st.executeQuery(" select * from empleados ");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String anyos = rs.getString("anyos");
                String categoria = rs.getString("categoria");

                System.out.println("--------------------------------------");
                System.out.println("El nombre del empleado es " + nombre);
                System.out.println("Los años trabajados que lleva son " + anyos);
                System.out.println("La categoria del empleado es " + categoria);

            }

        } catch (SQLException e) {
            System.out.println("Ocurrió algún error al conectar u operar con la BD");
        } finally {
            try {
                DBUtils.close(st);
                DBUtils.close(conn);
            } catch (SQLException e) {
                System.out.println("Ocurrió una excepción al cerrar la BD");
            }
        }

        /**
         * Crea un nuevo empleado y lo da de alta
         *
         *
         *
         */
        Empleado nuevoEmpleado = null;
        try {
            nuevoEmpleado = new Empleado("Ada Lovelace", "32000031R", "F");
        } catch (DatosNoCorrectosException e) {
            e.printStackTrace();
            return;
        }

        /**
         * Llama al método altaEmpleado que inserta un empleado en la base de
         * datos.
         *
         *
         */
// 			
        altaEmpleado(nuevoEmpleado);
        /**
         * Llama al método altaEmpleado que inserta empleados desde un archivo
         * en la base de datos.
         *
         */

        altaEmpleado(
                "empleadosNuevos.txt");

        /**
         * Consulta empleados desde la base de datos y los almacena en un
         * fichero llamado datosEmpledos.txt donde estaran todos los datos de
         * los empleados de la base de datos.
         *
         *
         */
        List<Empleado> empleados = new ArrayList<>();

        // Consultar empleados desde la base de datos
        try {
            conn = DBUtils.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT nombre, dni, sexo, categoria, anyos FROM empleados");

            while (rs.next()) {
                // Crear instancias de Empleado desde los resultados de la base de datos
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String sexo = rs.getString("sexo");
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");
                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
// 		Agregar empleado a la lista
                empleados.add(empleado);
            }
// 		Cerrar recursos
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
// 		Guardar empleados en un archivo de texto
        guardarEmpleadosEnArchivos(empleados);
        /**
         * Ejecuta el menú principal del programa que permite interactuar con
         * las opciones disponibles.
         *
         * @param scanner Un objeto Scanner para leer la entrada del usuario.
         * @param conn La conexión a la base de datos.
         * @param st Una declaración para ejecutar consultas SQL.
         */
        Scanner scanner = new Scanner(System.in);

        try {

            // Obtener una conexión a la base de datos
            conn = DBUtils.getConnection();
//
//			// Crear una declaración
            st = conn.createStatement();

            boolean salir = false;
            while (!salir) {
                System.out.println("----------------------------------------");
                System.out.println("Menú de opciones:");
                System.out.println("1. Mostrar información de todos los empleados");
                System.out.println("2. Ponme un numero de Dni valido y te daré su sueldo");
                System.out.println("3. Modificar los datos del empleado");
                System.out.println("4. Recalcular el sueldo del empleado");
                System.out.println("5. Recalcular el sueldo de todos los empleados");
                System.out.println("6. Hacer una copia de seguridad de la base de datos en un fichero");

                System.out.println("0. Salir");

                int opcion = obtenerEnteroDesdeConsola("Seleccione una opción: ", scanner);

                switch (opcion) {

                    case 0:
                        salir = true;
                        System.out.println("Adiooosss");
                        break;
                    case 1:
                        mostrarInformacionEmpleados(st);
                        break;
                    case 2:
                        obtenerSueldoPorDNI(st, scanner);
                        break;
                    case 3:
                        modificarEmpleado(st, scanner);
                        break;
                    case 4:
                        recalcularYActualizarSueldo(st, scanner);
                        break;
                    case 5:
                        recalcularYActualizarSueldos(st);
                        break;
                    case 6:
                        String usuario = "root";
                        String contrasena = "123456";
                        String nombreBaseDatos = "gestion_nominas";
                        String rutaRespaldo = "copiaBaseDeDatos.sql";
                        realizarCopiaSeguridad(usuario, contrasena, nombreBaseDatos, rutaRespaldo);
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                DBUtils.close(rs);
                DBUtils.close(st);
                DBUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
// Cerrar el scanner
        scanner.close();
    }

    /**
     * Muestra información sobre un empleado y su sueldo.
     *
     * @param e1 El empleado del cual se mostrará la información.
     */
    private static void escribe(Empleado e1) {
        e1.imprime();
        System.out.println("Sueldo " + Nomina.sueldo(e1));
        System.out.println("------------");
    }

    /**
     * Crea un empleado con los datos proporcionados y lo da de alta en el
     * sistema.
     *
     * @param empleado El empleado a dar de alta.
     */
    public static void altaEmpleado(Empleado empleado) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            // Obtengo una conexión utilizando la clase DBUtils
            conn = DBUtils.getConnection();

            // Insertar el nuevo empleado en la tabla empleados
            String insertarEmpleado = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
            st = conn.prepareStatement(insertarEmpleado);
            st.setString(1, empleado.getDni());
            st.setString(2, empleado.getNombre());
            st.setString(3, empleado.getSexo());
            st.setInt(4, empleado.getCategoria());
            st.setInt(5, empleado.getAnyos());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empleado dado de alta exitosamente y metido en la base de datos.");
                System.out.println("----------------------------------------------------------------");

                // Calcular el sueldo y actualizar la tabla nominas
                int sueldo = Nomina.sueldo(empleado);
                String updateSueldoSql = "INSERT INTO nominas (dni_empleado, sueldo) VALUES (?, ?) ON DUPLICATE KEY UPDATE sueldo = ?";
                st = conn.prepareStatement(updateSueldoSql);
                st.setString(1, empleado.getDni());
                st.setInt(2, sueldo);
                st.setInt(3, sueldo);

                int sueldoRowsAffected = st.executeUpdate();
                if (sueldoRowsAffected > 0) {
                    System.out.println("Sueldo actualizado exitosamente.");
                } else {
                    System.out.println("No se pudo actualizar el sueldo.");
                }
            } else {
                System.out.println("No se pudo dar de alta al empleado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja cualquier excepción de SQL aquí
        } finally {
            try {
                // Cierra la conexión y el PreparedStatement utilizando la clase DBUtils
                DBUtils.close(st);
                DBUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sobrecargado para dar de alta empleados desde un archivo de texto.
     *
     * @param archivoEmpleado El archivo de texto se llama empleadosNuevos y
     * contiene los datos de los empleados a dar de alta.
     */
    public static void altaEmpleado(String archivoEmpleado) {

//	// Implementación para dar de alta empleados por lotes desde un archivo
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoEmpleado));
            String linea;
            while ((linea = br.readLine()) != null) {
                // Parsear los datos del archivo y crear un nuevo empleado
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String nombre = datos[0].trim();
                    String dni = datos[1].trim();
                    String sexo = datos[2].trim();
                    int categoria = Integer.parseInt(datos[3].trim());
                    int anyos = Integer.parseInt(datos[4].trim());

                    Empleado nuevoEmpleado = new Empleado(nombre, dni, sexo, categoria, anyos);
                    altaEmpleado(nuevoEmpleado);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DatosNoCorrectosException e) {
            e.printStackTrace();
        }
    }
    /**
     * Guarda los datos de empleados en un archivo de texto.
     *
     * 
     * 
     */
    public static void guardarEmpleadosEnArchivos(List<Empleado> empleados)
            throws DatosNoCorrectosException, IOException {

        try (FileWriter fw = new FileWriter(
                "datosEmpleados.txt")) {
            for (Empleado empleado : empleados) {
                // Formatear los datos del empleado y escribir en el archivo
                String empleadoData = empleado.getNombre() + "," + empleado.getDni() + "," + empleado.getSexo() + ","
                        + empleado.getCategoria() + "," + empleado.getAnyos() + "\n";
                fw.write(empleadoData);
            }
        }
    }

    /**
     * Lee un número entero desde la consola después de mostrar un mensaje de
     * solicitud.
     *
     * @param mensaje El mensaje que se muestra para solicitar la entrada del
     * usuario.
     * @param scanner El objeto Scanner utilizado para la entrada de datos desde
     * la consola.
     * @return El número entero leído desde la consola.
     */
    public static int obtenerEnteroDesdeConsola(String mensaje, Scanner scanner) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número entero válido.");
            scanner.nextLine(); // Limpiar el búfer de entrada
        }
        return scanner.nextInt();
    }

    /**
     * Muestra la información existente en la base de datos de todos los
     * empleados (nombre, dni, sexo, categoría y años trabajados).
     *
     * @param st La declaración SQL para ejecutar la consulta.
     * 
     * correctos.
     */
    private static void mostrarInformacionEmpleados(Statement st) throws SQLException, DatosNoCorrectosException {

        String consultaSQL = "SELECT * FROM empleados";
        ResultSet rs = st.executeQuery(consultaSQL);

        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String sexo = rs.getString("sexo");
            int categoria = rs.getInt("categoria");
            int anyos = rs.getInt("anyos");

            System.out.println("DNI del empleado: " + dni);
            System.out.println("Nombre del empleado: " + nombre);
            System.out.println("Sexo del empleado: " + sexo);
            System.out.println("Categoría del empleado: " + categoria);
            System.out.println("Años en la empresa: " + anyos);
            System.out.println("--------------------------------------------------");
        }
        rs.close();
    }

    /**
     * Obtiene y muestra el sueldo de un empleado a partir de su número de DNI.
     *
     * @param st El objeto Statement utilizado para ejecutar las consultas SQL
     * en la base de datos.
     * @param scanner El objeto Scanner utilizado para la entrada de datos desde
     * la consola.
     */
    private static void obtenerSueldoPorDNI(Statement st, Scanner scanner)
            throws SQLException, DatosNoCorrectosException {
        ResultSet rs = null;

        String dni;
        System.out.println("Dime el DNI");
        dni = scanner.next();

        rs = st.executeQuery(
                "select DNI , sueldo FROM Empleados e  JOIN nominas n ON e.Dni = n.dni_empleado WHERE e.dni ='" + dni
                + "';");
        while (rs.next() != false) {
            int sueldo = rs.getInt(2);
            System.out.println("El sueldo del empleado es " + sueldo);
        }
    }

    /**
     * Modifica los datos de un empleado en la base de datos, permitiendo
     * actualizar su nombre, sexo, categoría y años trabajados.
     *
     * @param st El objeto Statement utilizado para ejecutar las consultas SQL
     * en la base de datos.
     * @param sc El objeto Scanner utilizado para la entrada de datos desde la
     * consola.
     */
    private static void modificarEmpleado(Statement st, Scanner sc) throws SQLException, DatosNoCorrectosException {
        System.out.println("Ingrese el Dni del empleado que desea modificar: ");

        String dni = sc.next();

        ResultSet empleadoExiste = st.executeQuery("SELECT * FROM empleados WHERE dni = '" + dni + "'");

        if (!empleadoExiste.next()) {
            System.out.println("El empleado no existe");
            return;
        }

        String nombreActual = empleadoExiste.getString("nombre");
        String sexoActual = empleadoExiste.getString("sexo");
        String categoriaActual = empleadoExiste.getString("categoria");
        String anyosActual = empleadoExiste.getString("anyos");

        System.out.println("Datos actuales del empleado");
        System.out.println("El nombre del empleado es " + nombreActual);
        System.out.println("El sexo del empleado es " + sexoActual);
        System.out.println("La categoria del empleado es " + categoriaActual);
        System.out.println("Los anyos que lleva el empleado es " + anyosActual);
        System.out.println("--------------------------------------------------");

//		Solicito la informacion y lo actualizo en la base datos
        System.out.println("Dime el nuevo nombre del empleado ");
        String nuevoNombre = sc.next();
        System.out.println("Dime el nuevo sexo del empleado ");
        String nuevoSexo = sc.next();
        System.out.println("Dime la nueva categoria del empleado ");
        int nuevaCategoria = sc.nextInt();
        System.out.println("Dime los nuevos anyos trabajados del empleado ");
        int nuevoAnyos = sc.nextInt();

        if (nuevaCategoria < 1 || nuevaCategoria > 10) {
            System.out.println("La categoría proporcionada no es válida.");
            return;
        }

//Actualizo la informacion del empleado en la base de datos
        String actualizarDatos = "UPDATE empleados SET nombre = '" + nuevoNombre + "', sexo = '" + nuevoSexo
                + "', categoria = " + nuevaCategoria + ", anyos = " + nuevoAnyos + " WHERE dni = '" + dni + "'";

        st.executeUpdate(actualizarDatos);

//Actualizo el sueldo del nuevo empleado
        int sueldoNuevo = Nomina.sueldo(new Empleado(nuevoNombre, dni, nuevoSexo, nuevaCategoria, nuevoAnyos));
        System.out.println("Nuevo sueldo del empleado: " + sueldoNuevo);
        System.out.println("--------------------------------------------");

        String actualizarSueldo = "UPDATE nominas SET sueldo = " + sueldoNuevo + " WHERE dni_empleado = '" + dni + "'";

        try {
            st.executeUpdate(actualizarSueldo);
            System.out.println("Sueldo actualizado en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        }
    }


    /**
     * Recalcula y actualiza el sueldo de un empleado en la base de datos según
     * los nuevos años trabajados.
     *
     * @param st El objeto Statement utilizado para ejecutar las consultas SQL
     * en la base de datos.
     * @param sc El objeto Scanner utilizado para la entrada de datos desde la
     * consola.
     */
    private static void recalcularYActualizarSueldo(Statement st, Scanner sc)
            throws SQLException, DatosNoCorrectosException {
        System.out.println("Ingrese el DNI del empleado cuyo sueldo desea recalcular: ");
        String dni = sc.next();

        ResultSet empleadoExiste = st.executeQuery("SELECT * FROM empleados WHERE dni = '" + dni + "'");

        if (!empleadoExiste.next()) {
            System.out.println("El empleado no existe");
            return;
        }

        String nombreActual = empleadoExiste.getString("nombre");
        String sexoActual = empleadoExiste.getString("sexo");
        int categoriaActual = empleadoExiste.getInt("categoria");
        int anyosActual = empleadoExiste.getInt("anyos");

        System.out.println("Datos actuales del empleado");
        System.out.println("El nombre del empleado es " + nombreActual);
        System.out.println("El sexo del empleado es " + sexoActual);
        System.out.println("La categoría del empleado es " + categoriaActual);
        System.out.println("Los años que lleva el empleado en la empresa son " + anyosActual);
        System.out.println("--------------------------------------------------");

        // Solicitar nuevos años de servicio para recalcular el sueldo
        System.out.println("Dime los nuevos años trabajados del empleado: ");
        int nuevosAnyos = sc.nextInt();

        // Validar que los nuevos años sean no negativos
        if (nuevosAnyos < 0) {
            System.out.println("Los años trabajados deben ser no negativos.");
            return;
        }

        // Recalcular el sueldo
        int nuevoSueldo = Nomina.sueldo(new Empleado(nombreActual, dni, sexoActual, categoriaActual, nuevosAnyos));
        System.out.println("Nuevo sueldo del empleado: " + nuevoSueldo);

        // Actualizar el sueldo en la base de datos
        String actualizarSueldo = "UPDATE nominas SET sueldo = " + nuevoSueldo + " WHERE dni_empleado = '" + dni + "'";

        try {
            st.executeUpdate(actualizarSueldo);
            System.out.println("Sueldo actualizado en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
        }
    }


    /**
     * Recalcula y actualiza los sueldos de todos los empleados en la base de
     * datos de acuerdo con sus categorías y años trabajados.
     *
     * @param st El objeto Statement utilizado para ejecutar las consultas SQL
     * en la base de datos.
     * 
     */
    private static void recalcularYActualizarSueldos(Statement st) throws SQLException, DatosNoCorrectosException {
        ResultSet empleados = st.executeQuery("SELECT * FROM empleados");

        while (empleados.next()) {
            String dni = empleados.getString("dni");
            String nombre = empleados.getString("nombre");
            String sexo = empleados.getString("sexo");
            int categoria = empleados.getInt("categoria");
            int anyos = empleados.getInt("anyos");

            // Calcular el nuevo sueldo para este empleado
            int nuevoSueldo = Nomina.sueldo(new Empleado(nombre, dni, sexo, categoria, anyos));

            // Actualizar el sueldo en la tabla de nóminas
            String actualizarSueldo = "UPDATE nominas SET sueldo = " + nuevoSueldo + " WHERE dni_empleado = '" + dni
                    + "'";
            try {
                st.executeUpdate(actualizarSueldo);
                System.out.println("Sueldo actualizado para el empleado con DNI " + dni + " es: " + nuevoSueldo);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error al ejecutar la consulta SQL: " + e.getMessage());
            }
        }
        empleados.close();
    }

    /**
     * Realiza una copia de seguridad de la base de datos MySQL en un archivo de
     * respaldo llamado copiaBaseDeDatos.txt
     *
     * @param usuario El nombre de usuario de la base de datos.
     * @param contrasena La contraseña del usuario de la base de datos.
     * @param nombreBaseDatos El nombre de la base de datos que se va a
     * respaldar.
     * @param rutaRespaldo La ruta completa donde se guardará el archivo de
     * respaldo.
     */
    public static void realizarCopiaSeguridad(String usuario, String contrasena, String nombreBaseDatos,
            String rutaRespaldo) {

        try (Connection conn = DBUtils.getConnection()) {
            if (conn != null) {
                // La conexión a la base de datos se ha establecido correctamente
                String comando = "mysqldump -u " + usuario + " -p" + contrasena + " " + nombreBaseDatos;

                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
                builder.redirectErrorStream(true);

                Process process = builder.start();

                // Redirige la salida del proceso al archivo de respaldo
                try (InputStream inputStream = process.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(rutaRespaldo)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    process.waitFor();
                    System.out.println("Copia de seguridad exitosa. El respaldo se encuentra en: " + rutaRespaldo);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error al redirigir la salida: " + e.getMessage());
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException | IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Error al realizar la copia de seguridad: " + e.getMessage());
        }
    }
}
