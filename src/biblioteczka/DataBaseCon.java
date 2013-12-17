/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteczka;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ania
 */
public class DataBaseCon {

      private java.sql.Connection  conn = null;
     private final String url = "jdbc:microsoft:sqlserver://";
     private final String serverName= "localhost";
     private final String portNumber = "1433";
     private final String databaseName= "Biblioteczka";
     private final String userName = "test";//"ANIA-LAPTOP\\test";//nazwa komputera i użytkownika
     private final String password = "test";//hasło do użytkownika komputera
     private final String selectMethod = "cursor";


    public  DataBaseCon() throws SQLException, IOException
    {
        conn = getConnection();
    }

   private String getConnectionUrl(){
          return
          "jdbc:sqlserver://ANIA-LAPTOP:1433;databaseName=Biblioteczka;user=test;password=test;";
     }

    public void closeConnection() throws Exception
    {
       conn.close();
    }

    private java.sql.Connection getConnection(){
          try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               conn = java.sql.DriverManager.getConnection(getConnectionUrl(),userName,password);
               if(conn!=null) System.out.println("Connection Successful!");
          }catch(ClassNotFoundException e){
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
         } catch (SQLException e) {
             System.out.println("Error Trace in getConnection() : " + e.getMessage());
          }
          return conn;
      }


    public ResultSet getBookDetails(String db) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM BooksDet";
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);
         return result;

    }

    public ResultSet getBookDetailsA(String db, String Name, String LName) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM BooksDet WHERE [Author's Name] LIKE '"+ Name +
                  "' AND [Author's Last Name] LIKE'"+LName+"'";
          System.out.println(newQuery);
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);

        return result;

    }

    public ResultSet getBookDetailsP(String db, String Name) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM BooksDet WHERE Publisher LIKE '"+ Name+"'";
          System.out.println(newQuery);
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);

        return result;

    }

        public ResultSet getBookDetailsG(String db, String Name) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM BooksDet WHERE Gendre LIKE '"+ Name+"'";
          System.out.println(newQuery);
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);

        return result;

    }
                public ResultSet getBookDetailsT(String db, String Name) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM BooksDet WHERE Title LIKE '"+ Name+"'";
          System.out.println(newQuery);
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);

        return result;

    }

    public ResultSet getSales(String db) throws SQLException{
         ResultSet result = null;
            int nr = 0;

          Statement stat = conn.createStatement();
          String newQuery = "SELECT * FROM SalesDet";
          System.out.println(newQuery);
          stat.execute("use "+ db);
          result = stat.executeQuery(newQuery);

        return result;

    }

    
    
     public void addRowToDB(String db, String table, String[] fields, String[] values) throws SQLException{
        
          Statement stat = conn.createStatement();
          String newQuery = "INSERT INTO " + table + " (";
          String fieldsList = "";
          int j = 0;
          for(int i= 0; i<fields.length-1; i++)
          {
              fieldsList += fields[i] + " ,";
              j = i;
          }
          j++;
          fieldsList += fields[j];
          newQuery += fieldsList + ") VALUES (";
          String valueList = "";
          j = 0;
          for(int i= 0; i<values.length-1; i++)
          {
              valueList += "'"+ values[i] + "' ,";
              j = i;
          }
          j++;
          valueList += "'"+ values[j] + "')";
          newQuery += valueList;
          stat.execute("use "+db);
          System.out.println(newQuery);
          stat.executeUpdate(newQuery);

    }
            public void editRowInDB(String db, String table, String id, String[] fields, String[] values) throws SQLException
    {



          Statement stat = conn.createStatement();

          String newQuery = "UPDATE  " + table + " SET ";
          String fieldsAndValueList = "";

          int j = 0;
          for(int i= 0; i<fields.length-1; i++)
          {
              fieldsAndValueList += fields[i] + "='" + values[i]+"', ";
              j = i;
          }
          j++;
          fieldsAndValueList += fields[j] + "='" + values[j]+"' ";

          newQuery += fieldsAndValueList + " WHERE isbn LIKE '" + id+"'";

          System.out.println("\n" + newQuery);
         stat.execute("use "+db);
          stat.executeUpdate(newQuery);


    }


            public void editRowInDBS(String db, String table, String id, String no, String[] fields, String[] values) throws SQLException
    {
        Statement stat = conn.createStatement();
        String newQuery = "UPDATE  " + table + " SET ";
        String fieldsAndValueList = "";

          int j = 0;
          for(int i= 0; i<fields.length-1; i++)
          {
              fieldsAndValueList += fields[i] + "='" + values[i]+"', ";
              j = i;
          }
          j++;
          fieldsAndValueList += fields[j] + "='" + values[j]+"' ";

          newQuery += fieldsAndValueList + " WHERE isbn LIKE '" + id+"' AND [Sale's No.]="+no;

          System.out.println("\n" + newQuery);
          stat.execute("use "+db);
          stat.executeUpdate(newQuery);


    }

     public void deleteRowFromDB(String db, String id) throws SQLException
    {

          Statement stat = conn.createStatement();

          String newQuery = "DELETE FROM  BooksDet WHERE isbn LIKE '" + id+"'";

          System.out.println(newQuery);
          stat.execute("use "+db);
          stat.executeUpdate(newQuery);

    }

     public void deleteRowFromDBS(String db,String isbn, String no) throws SQLException
    {

          Statement stat = conn.createStatement();

          String newQuery = "DELETE FROM SalesDet WHERE isbn LIKE '" + isbn+"' AND [Sale's No.] ="+no;

          stat.execute("use "+db);
          stat.executeUpdate(newQuery);

    }


}
