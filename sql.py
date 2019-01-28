import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
public class SQLDatabaseTest
{
    public static void main(String[] args)
    {
        String connectionString =
            "jdbc:sqlserver://myserver0.database.windows.net:1433;"
            + "database=MyDB; " + "user=leon@myserver0;"
            + "password={Noel1234$}; " + "encrypt=true;"
            + "trustServerCertificate=false; "
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement prepsInsertPerson = null;
        PreparedStatement prepsUpdateAge = null;
        try
        {
            connection = DriverManager.getConnection(connectionString);
            // INSERT two rows into the table
            // …
            // TRANSACTION and commit for an UPDATE
            // …
            // SELECT rows from the table
            // …
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if (prepsInsertPerson != null) try { prepsInsertPerson.close(); } catch (Exception e) { }
            if (prepsUpdateAge != null) try { prepsUpdateAge.close(); } catch (Exception e) { }
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) { }
            if (statement != null) try { statement.close(); } catch (Exception e) { }
            if (connection != null) try { connection.close(); } catch (Exception e) { }
        }
    }
}
