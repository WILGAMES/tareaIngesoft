package zonaGeografica;

public class ZonaGeografica {

    private int idZona;
    private String nombre;
    private String ubicacion;
    private int numeroMaquinas;
    private int tecnicoAsignado;
    private double latitud;
    private double longitud;

    public ZonaGeografica(int idZona, String nombre, String ubicacion, double latitud, double longitud) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.numeroMaquinas = 0;
        this.tecnicoAsignado = -1;
    }

    public int getIdZona() {
        return idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getNumeroMaquinas() {
        return numeroMaquinas;
    }

    public void incrementarMaquinas() {
        this.numeroMaquinas++;
    }

    public int getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void asignarTecnico(int idTecnico) {
        this.tecnicoAsignado = idTecnico;
        System.out.println("[Zonas] Técnico " + idTecnico + " asignado a zona " + this.nombre);
    }

    public void liberarTecnico() {
        this.tecnicoAsignado = -1;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "id=" + idZona +
                ", nombre='" + nombre + '\'' +
                ", ubicación='" + ubicacion + '\'' +
                ", máquinas=" + numeroMaquinas +
                ", técnico=" + tecnicoAsignado +
                ", lat=" + latitud +
                ", lng=" + longitud +
                '}';
    }
}
