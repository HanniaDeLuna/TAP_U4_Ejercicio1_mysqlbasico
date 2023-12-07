import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.util.ArrayList;


public class BaseDatos {
   Connection conexion; 
   Statement transaccion;
   ResultSet cursor;
   
   String cadenaConexion="jdbc:mysql://bj2wefwhrxm01cmscwov-mysql.services.clever-cloud.com:3306/bj2wefwhrxm01cmscwov?zeroDateTimeBehavior=CONVERT_TO_NULL";
   String usuario="u3fax96yenjhzl6g";
   String pass="xozsUpkMGEe6dgyFcYui";
   
   public BaseDatos(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
           transaccion = conexion.createStatement();
                   
       }catch(SQLException e ){
   }catch(ClassNotFoundException e){
   }
   }//codigo de la conexion 
   public boolean insertar(Persona p){
       String SQL_Insertar = "INSERT INTO `persona` (`ID`, `NOMBRE`, `DOMICILIO`, `TELEFONO`) VALUES (NULL, '%NOM%', '%DOM%', '%TEL%');";
       
       SQL_Insertar = SQL_Insertar.replace("%NOM%", p.nombre);
       SQL_Insertar = SQL_Insertar.replace("%DOM%", p.domicilio);
       SQL_Insertar = SQL_Insertar.replace("%TEL%", p.telefono);
    
         try{
             transaccion.execute(SQL_Insertar);
         }catch(SQLException e){
             return false;
         }
       return true;
   }
   public ArrayList<Persona> mostrarTodos(){
        ArrayList<Persona> datos= new ArrayList<Persona>();
        String SQL_consulta = "Select * From persona";
        try {
            //Resulset =Variable que manipula las tuplas resultado
            cursor=transaccion.executeQuery(SQL_consulta);
            if(cursor.next()){
                do{
//                    cursor.getInt(1);//Id
//                    cursor.getString(2);//Nombre
//                    cursor.getString(3);//Domicilio
//                    cursor.getString(4);//Telefono
                    Persona p=new Persona(
                    cursor.getInt(1),
                    cursor.getString(2),//Nombre
                    cursor.getString(3),//Domicilio
                    cursor.getString(4)//Telefono
                    );
                    datos.add(p);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
}
