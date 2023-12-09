import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.util.ArrayList;


public class BaseDatos {
   Connection conexion; 
   Statement transaccion;
   ResultSet cursor;
   
   String cadenaConexion="mysql://u3fax96yenjhzl6g:xozsUpkMGEe6dgyFcYui@bj2wefwhrxm01cmscwov-mysql.services.clever-cloud.com:3306/bj2wefwhrxm01cmscwov";
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
   public boolean insertarP(Persona p){
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
   public ArrayList<Persona> mostrarTodosP(){
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
   public Persona obtenerPorIdP(String idBuscar){
        String SQL_consulta = "Select * From persona Where ID="+idBuscar;
        try {
            //Resulset =Variable que manipula las tuplas resultado
            cursor=transaccion.executeQuery(SQL_consulta);
            if(cursor.next()){
               
                    Persona p=new Persona(
                    cursor.getInt(1),
                    cursor.getString(2),//Nombre
                    cursor.getString(3),//Domicilio
                    cursor.getString(4)//Telefono
                    );
                    return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Persona(0,"","","");
    }
    
    public boolean eliminar(String IdEliminar){
        String SQL_Eiliminar="Delete from Persona where ID="+IdEliminar;
        try {
            transaccion.execute(SQL_Eiliminar);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    public boolean acutalizar(Persona p){
        //Su codigo es muy similar a insertar
        String SQL_Actualizar="Update Persona SET NOMBRE=%NOM%,DOMICILIO=%DOM%,TELEFONO=%TEL% WHERE ID="+p.id;
        SQL_Actualizar=SQL_Actualizar.replace("%NOM%", p.nombre);
        SQL_Actualizar=SQL_Actualizar.replace("%DOM%", p.domicilio);
        SQL_Actualizar=SQL_Actualizar.replace("%TEL%", p.telefono);
        try{
            transaccion.executeUpdate(SQL_Actualizar);
        }catch(SQLException e){
            return false;
        }
        return true;
    }
       public boolean insertarC(Credencial c){
       String SQL_Insertar = "INSERT INTO `credencial` (`NumeroControl`, `NombreAlumno`, `Carrera`, `FechaExpedicion`,`Semestre`) VALUES (NULL, '%Num%', '%NomA%', '%Car%','%FE%','%Sem%');";
       
       SQL_Insertar = SQL_Insertar.replace("%Num%", c.NumControl);
       SQL_Insertar = SQL_Insertar.replace("%NomA%", c.NombreAlumno);
       SQL_Insertar = SQL_Insertar.replace("%Car%", c.Carrera);
      
        String semestreStr = String.valueOf(c.getSemestre());
        SQL_Insertar = SQL_Insertar.replace("%Sem%", semestreStr);
    
         try{
             transaccion.execute(SQL_Insertar);
         }catch(SQLException e){
             return false;
         }
       return true;
   }
          public ArrayList<Credencial> mostrarTodosC(){
        ArrayList<Credencial> datos= new ArrayList<Credencial>();
        String SQL_consulta = "Select * From credencial";
        try {
            //Resulset =Variable que manipula las tuplas resultado
            cursor=transaccion.executeQuery(SQL_consulta);
            if(cursor.next()){
                do{
//                    cursor.getInt(1);//NumControl
//                    cursor.getString(2);//NombreAlumno
//                    cursor.getString(3);//Carrera
//                    cursor.getString(4);//Fechaexpedicion
//                    cursor.getString(5);//Semestre
                Credencial c = new Credencial(
                cursor.getString(1), // NumControl
                 cursor.getString(2), // NombreAlumno
    cursor.getString(3), // Carrera
    cursor.getDate(4),    // Fechaexpedicion
    cursor.getInt(5)      // semestre
);
                    datos.add(c);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

}
