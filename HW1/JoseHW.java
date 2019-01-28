import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.io.*;
import java.util.Scanner;
public class JoseHW {
    public static void main(String[] args) {
        String connectionString =
            "jdbc:sqlserver://josedbserver0.database.windows.net:1433;database=JoseDB;user=mydbadmin@josedbserver0;password={RobertoFernando25!};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        PreparedStatement prepsInsertPerson = null;
        PreparedStatement prepsUpdateAge = null;
        try {
                   connection = DriverManager.getConnection(connectionString);

                    // Create and execute a SELECT SQL statement.
                    String selectSql = "SELECT * FROM dbo.Customer; ";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(selectSql);
                    // Iterate through the result set and print the attributes.
                    while (resultSet.next()) {
                      System.out.println(resultSet.getString(2) + ", " +resultSet.getString(3) + ", " + resultSet.getString(4));
                    }
                    System.out.println(" " );

                    //Select all customer and selcte all product worth less than $20
                    selectSql = "SELECT * FROM dbo.Product WHERE Price<20;";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(selectSql);
                    System.out.println(" " );
                    // Iterate through the result set and print the attributes.
                    while (resultSet.next()) {
                      System.out.println("ID= " + resultSet.getString(1) + ", Quantity= " +resultSet.getString(2) + ", Price= " + resultSet.getString(3));
                    }
                    System.out.println(" " );



                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Find all purchase orders related to user:  ");
                    String name = scanner.next();

                    selectSql = "SELECT * FROM dbo.PurchaseOrder WHERE dbo.PurchaseOrder.CustomerID = (SELECT dbo.Customer.CustomerID FROM dbo.Customer WHERE dbo.Customer.Name = '" + name + "');";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(selectSql);
                    // Iterate through the result set and print the attributes.
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) + ", " +resultSet.getString(2) + ", " + resultSet.getString(3));
                    }
                    System.out.println(" " );


                    scanner = new Scanner(System.in);
                    System.out.print("Find all items realated to purchase order ID: ");
                    int POIDnumber = scanner.nextInt();
                    selectSql = "SELECT dbo.PurchasedItems.ProductID FROM dbo.PurchasedItems WHERE dbo.PurchasedItems.PurchaseOrderID =" + POIDnumber + ";";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(selectSql);
                    System.out.println(String.format("List of items purchase base on purchase order ID: %s", POIDnumber));
                    // Iterate through the result set and print the attributes.
                    while (resultSet.next()) {
                      selectSql = "SELECT * FROM dbo.Product WHERE ProductID ="+resultSet.getString(1) +";";
                      statement = connection.createStatement();
                      resultSet2 = statement.executeQuery(selectSql);
                      // Iterate through the result set and print the attributes.
                      while (resultSet2.next()) {
                          System.out.println(resultSet2.getString(1) + ", " +resultSet2.getString(2) + ", " + resultSet2.getString(3));
                      }
                    }
                    System.out.println(" " );



                    scanner = new Scanner(System.in);
                    System.out.print("Find the customer name associated with the PurchasedItems ID: ");
                    int PurchasedItemsIDNumber = scanner.nextInt();
                    selectSql = "SELECT dbo.Customer.Name FROM dbo.Customer WHERE dbo.Customer.CustomerID = (SELECT dbo.PurchasedItems.CustomerID FROM dbo.PurchasedItems WHERE dbo.PurchasedItems.PurchasedItemsID = "+ PurchasedItemsIDNumber + ");";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(selectSql);
                    // Iterate through the result set and print the attributes.
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1) );
                    }
                    System.out.println(" " );




         }  catch (Exception e) {  e.printStackTrace();  }
         finally {
              if (prepsInsertPerson != null) try { prepsInsertPerson.close(); } catch(Exception e) {}
              if (prepsUpdateAge != null) try { prepsUpdateAge.close(); } catch(Exception e) {}
              if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
              if (statement != null) try { statement.close(); } catch(Exception e) {}
              if (connection != null) try { connection.close(); } catch(Exception e) {}
         }
       } }
