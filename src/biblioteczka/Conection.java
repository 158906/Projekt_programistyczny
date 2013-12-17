/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteczka;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Conection{
     private java.sql.Connection  con = null;
     private final String url = "jdbc:microsoft:sqlserver://";
     private final String serverName= "localhost";
     private final String portNumber = "1433";
     private final String databaseName= "Biblioteczka";
     private final String userName = "test";//"ANIA-LAPTOP\\test";//nazwa komputera i użytkownika
     private final String password = "test";//hasło do użytkownika komputera
     private final String selectMethod = "cursor";

     public Conection(){}

     private String getConnectionUrl(){
          return
                  "jdbc:sqlserver://ANIA-LAPTOP:1433;databaseName=Biblioteczka;user=test;password=test;";

     }

     private java.sql.Connection getConnection(){
          try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               con = java.sql.DriverManager.getConnection(getConnectionUrl(),userName,password);
               if(con!=null) {
                   System.out.println("Connection Successful!");
               }
          }catch(ClassNotFoundException e){
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
         } catch (SQLException e) {
             System.out.println("Error Trace in getConnection() : " + e.getMessage());
         }
          return con;
      }

  public void displayDbProperties(){
          java.sql.DatabaseMetaData dm = null;
          java.sql.ResultSet rs = null;
          try{
               con= this.getConnection();
               if(con!=null){
                    dm = con.getMetaData();
                    System.out.println("Driver Information");
                    System.out.println("\tDriver Name: "+ dm.getDriverName());
                    System.out.println("\tDriver Version: "+ dm.getDriverVersion ());
                    System.out.println("\nDatabase Information ");
                    System.out.println("\tDatabase Name: "+ dm.getDatabaseProductName());
                    System.out.println("\tDatabase Version: "+ dm.getDatabaseProductVersion());
                    System.out.println("Avalilable Catalogs ");
                    rs = dm.getCatalogs();
                    while(rs.next()){
                         System.out.println("\tcatalog: "+ rs.getString(1));
                    }
                    rs.close();
                    rs = null;
                    closeConnection();
               }else {
                   System.out.println("Error: No active Connection");
               }
          }catch(SQLException e){
          }
          dm=null;
     }

     private void closeConnection(){
          try{
               if(con!=null) {
                   con.close();
               }
               con=null;
          }catch(SQLException e){
          }
     }
   /*  public static void main(String[] args) throws Exception
       {
          Conection myDbTest = new Conection();
          myDbTest.displayDbProperties();
       }*/
    private static final Logger LOG = Logger.getLogger(Conection.class.getName());
}