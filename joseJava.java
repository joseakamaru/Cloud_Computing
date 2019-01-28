import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
public class joseJava {
    public static void main(String[] args) {
        String connectionString =
            "jdbc:sqlserver://josedbserver0.database.windows.net:1433;database=JoseDB;user=mydbadmin@josedbserver0;password={RobertoFernando25!};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement prepsInsertPerson = null;
        PreparedStatement prepsUpdateAge = null;
        try {
                   connection = DriverManager.getConnection(connectionString);
                   // INSERT rows into the table
                   String insertSql = "INSERT INTO Persons (" +
                   	"id, firstName, lastName, age) VALUES "
                   	+ "(1,'Bill', 'Gates', 59), " + "(2,'Steve', 'Ballmer', 59);";
                   prepsInsertPerson = connection.prepareStatement(
                   	insertSql,
                   	Statement.RETURN_GENERATED_KEYS);
                   prepsInsertPerson.execute();
                   // Retrieve the generated key from the insert
                   resultSet = prepsInsertPerson.getGeneratedKeys();
                   // Iterate through the set of generated keys
                   while (resultSet.next()) {
                   	System.out.println("Generated: " + resultSet.getString(1)); }

         }  catch (Exception e) {  e.printStackTrace();  }
         finally {
              if (prepsInsertPerson != null) try { prepsInsertPerson.close(); } catch(Exception e) {}
              if (prepsUpdateAge != null) try { prepsUpdateAge.close(); } catch(Exception e) {}
              if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
              if (statement != null) try { statement.close(); } catch(Exception e) {}
              if (connection != null) try { connection.close(); } catch(Exception e) {}
         }
       } }
