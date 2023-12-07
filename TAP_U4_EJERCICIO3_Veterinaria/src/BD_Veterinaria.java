import java.sql.*;

public class BD_Veterinaria {
    Connection conexion; 
   Statement transaccion;
   ResultSet cursor;
   
   String cadenaConexion="jdbc:mysql://bj2wefwhrxm01cmscwov-mysql.services.clever-cloud.com:3306/bj2wefwhrxm01cmscwov?zeroDateTimeBehavior=CONVERT_TO_NULL";
   String usuario="u3fax96yenjhzl6g";
   String pass="xozsUpkMGEe6dgyFcYui";
   
   public BD_Veterinaria(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
           transaccion = conexion.createStatement();
                   
       }catch(SQLException e ){
   }catch(ClassNotFoundException e){
   }
   }//codigo de la conexion   
      public boolean insertar(Mascota m ){
       String SQL_Insertar = "INSERT INTO `persona` (`ID`, `NOMBRE`, `PROPIETARIO`, `ATENCION`,`COSTO`,`RAZA`) VALUES (NULL, '%NOM%', '%PROP', '%ATEN%', '%COS%', '%RAZA%');";
       
       SQL_Insertar = SQL_Insertar.replace("%ID%", m.id);
       SQL_Insertar = SQL_Insertar.replace("%NOM%", m.nombre);
       SQL_Insertar = SQL_Insertar.replace("%PROP%", m.propietario);
       SQL_Insertar = SQL_Insertar.replace("%ATEN%", m.atencion);
       SQL_Insertar = SQL_Insertar.replace("%COS%", m.costo);
       SQL_Insertar = SQL_Insertar.replace("%RAZA%", m.raza);
    
         try{
             transaccion.execute(SQL_Insertar);
         }catch(SQLException e){
             return false;
         }
       return true;
   }
}

}
