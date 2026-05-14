package alarma;

import java.util.Date;

public class RegistroAlarmaBodega {

    public static final int ALARMA_INGREDIENTE = 1;
    public static final int ALARMA_MONEDA = 2;
    public static final int ALARMA_SUMINISTRO = 3;
    public static final int ALARMA_MAL_FUNCIONAMIENTO = 4;

    private int idAlarma;
    private int tipoAlarma;
    private int idMaquina;
    private Date fechaGeneracion;
    private Date fechaResolucion;
    private String estado; // "PENDIENTE", "PROCESANDO", "RESUELTA"
    private String descripcion;

    public RegistroAlarmaBodega(int idAlarma, int tipoAlarma, int idMaquina, String descripcion) {
        this.idAlarma = idAlarma;
        this.tipoAlarma = tipoAlarma;
        this.idMaquina = idMaquina;
        this.fechaGeneracion = new Date();
        this.fechaResolucion = null;
        this.estado = "PENDIENTE";
        this.descripcion = descripcion;
    }

    public int getIdAlarma() {
        return idAlarma;
    }

    public int getTipoAlarma() {
        return tipoAlarma;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstadoProcesando() {
        this.estado = "PROCESANDO";
        System.out.println("[BodegaCentral] Alarma " + idAlarma + " en máquina " + idMaquina + " -> PROCESANDO");
    }

    public void setEstadoResuelta() {
        this.estado = "RESUELTA";
        this.fechaResolucion = new Date();
        System.out.println("[BodegaCentral] Alarma " + idAlarma + " en máquina " + idMaquina + " -> RESUELTA");
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getTiempoResolucion() {
        if (fechaResolucion != null) {
            return fechaResolucion.getTime() - fechaGeneracion.getTime();
        }
        return System.currentTimeMillis() - fechaGeneracion.getTime();
    }

    @Override
    public String toString() {
        return "AlarmaB{" +
                "id=" + idAlarma +
                ", tipo=" + tipoAlarma +
                ", maq=" + idMaquina +
                ", estado='" + estado + '\'' +
                ", desc='" + descripcion + '\'' +
                '}';
    }
}
