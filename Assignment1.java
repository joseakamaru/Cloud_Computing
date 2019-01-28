/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String connectionString = "jdbc:sqlserver://mohammaddb.database.windows.net:1433;database=MyDB;user=mohammad@mohammaddb;password={Mmzmawad19902017};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        PreparedStatement prepsInsertPerson = null;
        PreparedStatement prepsUpdateAge = null;
	try {
            connection = DriverManager.getConnection(connectionString);
            
             // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM dbo.Customer";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println("Listing all customers" );
            // Iterate through the result set and print the attributes.
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + ", " +resultSet.getString(3) + ", " + resultSet.getString(4));
            }
            
            //show products with price less than 100$
            selectSql = "SELECT * FROM dbo.Product WHERE Price < 100;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println(" " );
            System.out.println(" Products less than 100$ " );
            // Iterate through the result set and print the attributes.
            while (resultSet.next()) {
                System.out.println("ID= " + resultSet.getString(1) + ", Quantity= " +resultSet.getString(2) + ", Price= " + resultSet.getString(3));
            }
            
            //Scanner scanner = new Scanner(System.in);
            //System.out.print("Enter your name: ");
            //String username = scanner.next();
            //System.out.print("Enter your age: ");
            //int age = scanner.nextInt();
            //System.out.println(String.format("%s, your age is %d", username, age));
            System.out.println(" " );
            Scanner scanner = new Scanner(System.in);
            System.out.print("Customer Name to find all purchase orders: ");
            String username = scanner.next();
            
            selectSql = "SELECT * FROM dbo.PurchaseOrder WHERE dbo.PurchaseOrder.CustomerID = (SELECT dbo.Customer.ID FROM dbo.Customer WHERE dbo.Customer.Name ='" +username+"');";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println(String.format("List all purchase orders for %s", username));
            // Iterate through the result set and print the attributes.
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + ", " +resultSet.getString(2) + ", " + resultSet.getString(3));
            }
            
            
            
            System.out.println(" " );
            scanner = new Scanner(System.in);
            System.out.print("Customer purchase order ID to find all items: ");
            String id = scanner.next();
            
            selectSql = "SELECT dbo.PurchasedItems.ProductID FROM dbo.PurchasedItems WHERE dbo.PurchasedItems.PurchaseOrderID ="+ id+";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println(String.format("List products for purchase orders id %s", id));
            // Iterate through the result set and print the attributes.
            while (resultSet.next()) {
                selectSql = "SELECT * FROM dbo.Product WHERE ID ="+resultSet.getString(1) +";";
                statement = connection.createStatement();
                resultSet2 = statement.executeQuery(selectSql);
                // Iterate through the result set and print the attributes.
                while (resultSet2.next()) {
                    System.out.println(resultSet2.getString(1) + ", " +resultSet2.getString(2) + ", " + resultSet2.getString(3));
                }
            }
      
            
            
            System.out.println(" " );
            scanner = new Scanner(System.in);
            System.out.print("ID of an item in the PurchasedItems table to find the customer name: ");
            id = scanner.next();
            
            selectSql = "SELECT dbo.PurchasedItems.CustomerID FROM dbo.PurchasedItems WHERE dbo.PurchasedItems.ProductID = "+ id +";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println(String.format("List all customer names"));
            // Iterate through the result set and print the attributes.
            while (resultSet.next()) {
                selectSql = "SELECT * FROM dbo.Customer WHERE ID ="+resultSet.getString(1) +";";
                statement = connection.createStatement();
                resultSet2 = statement.executeQuery(selectSql);
                // Iterate through the result set and print the attributes.
                while (resultSet2.next()) {
                    System.out.println(resultSet2.getString(1) + ", " +resultSet2.getString(2) + ", " + resultSet2.getString(3));
                }
            }
            

            
	}  catch (Exception e) {  e.printStackTrace();  }
	finally {
            if (prepsInsertPerson != null) try { prepsInsertPerson.close(); } catch(Exception e) {}
            if (prepsUpdateAge != null) try { prepsUpdateAge.close(); } catch(Exception e) {}
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
            if (connection != null) try { connection.close(); } catch(Exception e) {}
	} 
    }
    
}
