package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    final private String userName= "root";
    final private String password = "codeDatabase";
    final private String url = "jdbc:mysql://127.0.0.1:3306/universityResources";
    Connection databasConnection;
    public Connection getDatabaseConnection(){
        try{
            databasConnection= DriverManager.getConnection(this.url, this.userName, this.password);
        }
        catch(SQLException e){
            System.out.println("Something Went Wrong");
        }
        return databasConnection;
    }

}
