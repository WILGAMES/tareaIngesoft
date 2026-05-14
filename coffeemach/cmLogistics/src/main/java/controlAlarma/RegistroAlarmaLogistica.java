package controlAlarma;

import java.util.Date;

public class RegistroAlarmaLogistica {

    public static final int ALARMA_ENTREGA_INGREDIENTE = 1;
    public static final int ALARMA_ENTREGA_MONEDA = 2;
    public static final int ALARMA_ENTREGA_SUMINISTRO = 3;
    public static final int ALARMA_MANTENIMIENTO = 4;

    private int idAlarma;
    private int tipoAlarma;
    private int idMaquina;
    private Date fechaGeneracion;
    private Date fechaResolucion;
    private String estado; // "PENDIENTE", "ASIGNADO", "EN_TRANSITO", "ENTREGADO"
    private String descripcion;
    private int tecnicoAsignado;

    public RegistroAlarmaLogistica(int idAlarma, int tipoAlarma, int idMaquina, String descripcion) {
        this.idAlarma = idAlarma;
        this.tipoAlarma = tipoAlarma;
        this.idMaquina = idMaquina;
        this.fechaGeneracion = new Date();
        this.fechaResolucion = null;
        this.estado = "PENDIENTE";
        this.descripcion = descripcion;
        this.tecnicoAsignado = -1;
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

    public int getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void asignarTecnico(int idTecnico) {
        this.tecnicoAsignado = idTecnico;
        this.estado = "ASIGNADO";
        System.out.println("[CmLogistics] Alarma " + idAlarma + " asignada a técnico " + idTecnico);
    }

    public void setEstadoEnTransito() {
        this.estado = "EN_TRANSITO";
        System.out.println("[CmLogistics] Alarma " + idAlarma + " -> EN TRÁNSITO");
    }

    public void setEstadoEntregado() {
        this.estado = "ENTREGADO";
        this.fechaResolucion = new Date();
        System.out.println("[CmLogistics] Alarma " + idAlarma + " -> ENTREGADO");
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
        return "AlarmaL{" +
                "id=" + idAlarma +
                ", tipo=" + tipoAlarma +
                ", maq=" + idMaquina +
                ", estado='" + estado + '\'' +
                ", técnico=" + tecnicoAsignado +
                ", desc='" + descripcion + '\'' +
                '}';
    }
}
