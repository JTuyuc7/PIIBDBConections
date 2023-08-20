package ProyectoConcesionaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

/*
    * Metodo para coneccion a base de datos
 */
public class DbConection {
    public Connection getConnection() {
        Connection dbConnection = null;
        try {
            String url = "jdbc:mysql://localhost:3357/db_drivexport";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "mypass123");
            dbConnection = DriverManager.getConnection(url, info);

        }catch (SQLException e){
            System.out.println("No nos conectamos F");
            e.printStackTrace();
            return null;
        }
        return dbConnection;
    }

    //* Crear conexion statement y query
    public ResultSet readFromDb(String query) throws SQLException {
        Connection con = getConnection();
        ResultSet data = null;
        Statement statement = con.createStatement();
        data = statement.executeQuery(query);
        return data;
    }

}