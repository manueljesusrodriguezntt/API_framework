import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectorExample {

    public static void main(String[] args) {
        // Variables de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/variables_entorno"; // Cambia si tu servidor MySQL no está en localhost
        String user = "root"; // Cambia por tu usuario
        String password = "contraseña__1"; // Cambia por tu contraseña

        // Consulta SQL para seleccionar todos los datos de la tabla 'tabla_prueba'
        String query = "SELECT * FROM tabla_prueba";

        // Conexión a la base de datos y ejecución de la consulta
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Mostrar los encabezados de las columnas
            System.out.printf("%-15s %-15s %-45s%n", "idVariables", "Web/Android", "Variables");

            // Iterar sobre los resultados y mostrarlos
            while (rs.next()) {
                int idVariables = rs.getInt("idVariables");
                Integer webAndroid = rs.getObject("Web/Android") != null ? rs.getInt("Web/Android") : null;
                String variables = rs.getString("Variables");

                // Mostrar los datos en consola
                System.out.printf("%-15d %-15s %-45s%n", idVariables, webAndroid != null ? webAndroid : "NULL", variables);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
