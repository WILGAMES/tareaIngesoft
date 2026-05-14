package tecnicoMantenimiento;

public class TecnicoMantenimiento {

    private int idTecnico;
    private String nombre;
    private String especialidad;
    private boolean disponible;
    private int zonasAsignadas;

    public TecnicoMantenimiento(int idTecnico, String nombre, String especialidad) {
        this.idTecnico = idTecnico;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = true;
        this.zonasAsignadas = 0;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getZonasAsignadas() {
        return zonasAsignadas;
    }

    public void asignarZona() {
        this.zonasAsignadas++;
    }

    public void liberarZona() {
        if (zonasAsignadas > 0) {
            this.zonasAsignadas--;
        }
    }

    @Override
    public String toString() {
        return "Técnico{" +
                "id=" + idTecnico +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", disponible=" + disponible +
                ", zonas=" + zonasAsignadas +
                '}';
    }
}
