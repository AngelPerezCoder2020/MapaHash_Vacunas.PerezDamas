package classes;
import java.util.Date;

public class Vacuna {
    private String vacuna;
    private String fecha;

    public Vacuna(String vacuna, String fecha) {
        this.vacuna = vacuna;
        this.fecha = fecha;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}