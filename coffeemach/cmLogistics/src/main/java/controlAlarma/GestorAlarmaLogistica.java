package controlAlarma;

import tecnicoMantenimiento.TecnicoManager;
import tecnicoMantenimiento.TecnicoMantenimiento;
import zonaGeografica.GestorZonas;
import java.util.*;

public class GestorAlarmaLogistica {

    private List<RegistroAlarmaLogistica> alarmas;
    private int contadorAlarmas;
    private TecnicoManager tecnicoManager;
    private GestorZonas gestorZonas;

    public GestorAlarmaLogistica(TecnicoManager tecnicoManager, GestorZonas gestorZonas) {
        this.alarmas = new ArrayList<>();
        this.contadorAlarmas = 0;
        this.tecnicoManager = tecnicoManager;
        this.gestorZonas = gestorZonas;
    }

    public void registrarAlarma(int tipoAlarma, int idMaquina, String descripcion) {
        contadorAlarmas++;
        RegistroAlarmaLogistica alarma = new RegistroAlarmaLogistica(contadorAlarmas, tipoAlarma, idMaquina, descripcion);
        alarmas.add(alarma);
        System.out.println("[CmLogistics] ⚠️  Nueva alarma registrada: " + alarma);
    }

    public RegistroAlarmaLogistica obtenerAlarmaPendiente() {
        for (RegistroAlarmaLogistica alarma : alarmas) {
            if (alarma.getEstado().equals("PENDIENTE")) {
                return alarma;
            }
        }
        return null;
    }

    public List<RegistroAlarmaLogistica> obtenerAlarmasPendientes() {
        List<RegistroAlarmaLogistica> pendientes = new ArrayList<>();
        for (RegistroAlarmaLogistica alarma : alarmas) {
            if (alarma.getEstado().equals("PENDIENTE")) {
                pendientes.add(alarma);
            }
        }
        return pendientes;
    }

    public boolean asignarTecnicoAalarma(int idAlarma, String especialidad) {
        TecnicoMantenimiento tecnico = tecnicoManager.obtenerTecnicoDisponible(especialidad);
        
        if (tecnico != null) {
            for (RegistroAlarmaLogistica alarma : alarmas) {
                if (alarma.getIdAlarma() == idAlarma) {
                    alarma.asignarTecnico(tecnico.getIdTecnico());
                    tecnico.setDisponible(false);
                    System.out.println("[CmLogistics] ✅ Técnico " + tecnico.getNombre() + " asignado a alarma " + idAlarma);
                    return true;
                }
            }
        } else {
            System.out.println("[CmLogistics] ⚠️  No hay técnicos disponibles con especialidad '" + especialidad + "'");
        }
        return false;
    }

    public void marcarEnTransito(int idAlarma) {
        for (RegistroAlarmaLogistica alarma : alarmas) {
            if (alarma.getIdAlarma() == idAlarma) {
                alarma.setEstadoEnTransito();
                break;
            }
        }
    }

    public void marcarEntregado(int idAlarma) {
        for (RegistroAlarmaLogistica alarma : alarmas) {
            if (alarma.getIdAlarma() == idAlarma && alarma.getTecnicoAsignado() >= 0) {
                alarma.setEstadoEntregado();
                
                // Liberar técnico
                TecnicoMantenimiento tecnico = tecnicoManager.obtenerTecnico(alarma.getTecnicoAsignado());
                if (tecnico != null) {
                    tecnico.setDisponible(true);
                    System.out.println("[CmLogistics] ✅ Técnico " + tecnico.getNombre() + " liberado");
                }
                break;
            }
        }
    }

    public List<RegistroAlarmaLogistica> listarTodasLasAlarmas() {
        return new ArrayList<>(alarmas);
    }

    public void mostrarEstadisticas() {
        long totalPendiente = alarmas.stream().filter(a -> a.getEstado().equals("PENDIENTE")).count();
        long totalAsignado = alarmas.stream().filter(a -> a.getEstado().equals("ASIGNADO")).count();
        long totalEnTransito = alarmas.stream().filter(a -> a.getEstado().equals("EN_TRANSITO")).count();
        long totalEntregado = alarmas.stream().filter(a -> a.getEstado().equals("ENTREGADO")).count();

        System.out.println("\n[CmLogistics] === ESTADÍSTICAS DE ALARMAS ===");
        System.out.println("Total Pendiente: " + totalPendiente);
        System.out.println("Total Asignado: " + totalAsignado);
        System.out.println("Total En Tránsito: " + totalEnTransito);
        System.out.println("Total Entregado: " + totalEntregado);
        System.out.println("===========================================\n");
    }
}
