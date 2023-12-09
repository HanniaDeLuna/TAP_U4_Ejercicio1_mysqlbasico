
import java.util.Date;


public class Credencial {
 String NumControl;
 String NombreAlumno;
 String Carrera;
 Date FechaExpedicion;
 int Semestre;
 
 
 public Credencial(String n, String na, String c, Date f, int s){
     NumControl= n;
     NombreAlumno = na;
     Carrera= c;
     FechaExpedicion = f;
     Semestre = s;
 }
 public String[] toRenglon(){
        String[] vector =new String[5];
        vector[0]=""+NumControl;
        vector[1]=""+NombreAlumno;
        vector[2]=""+Carrera;
        vector[3]=""+FechaExpedicion;
        vector[4]=""+Semestre;
        return vector;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }
}
