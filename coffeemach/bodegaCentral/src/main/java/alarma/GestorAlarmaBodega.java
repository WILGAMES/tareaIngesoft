package alarma;

import java.util.*;

public class GestorAlarmaBodega {

    private List<RegistroAlarmaBodega> alarmas;
    private int contadorAlarmas;

    public GestorAlarmaBodega() {
        this.alarmas = new ArrayList<>();
        this.contadorAlarmas = 0;
    }

    public void registrarAlarma(int tipoAlarma, int idMaquina, String descripcion) {
        contadorAlarmas++;
        RegistroAlarmaBodega alarma = new RegistroAlarmaBodega(contadorAlarmas, tipoAlarma, idMaquina, descripcion);
        alarmas.add(alarma);
        System.out.println("[BodegaCentral] ⚠️  Nueva alarma registrada: " + alarma);
    }

    public List<RegistroAlarmaBodega> obtenerAlarmasPendientes() {
        List<RegistroAlarmaBodega> pendientes = new ArrayList<>();
        for (RegistroAlarmaBodega alarma : alarmas) {
            if (alarma.getEstado().equals("PENDIENTE")) {
                pendientes.add(alarma);
            }
        }
        return pendientes;
    }

    public RegistroAlarmaBodega obtenerAlarmaPorMaquina(int idMaquina) {
        for (RegistroAlarmaBodega alarma : alarmas) {
            if (alarma.getIdMaquina() == idMaquina && alarma.getEstado().equals("PENDIENTE")) {
                return alarma;
            }
        }
        return null;
    }

    public void procesarAlarma(int idAlarma) {
        for (RegistroAlarmaBodega alarma : alarmas) {
            if (alarma.getIdAlarma() == idAlarma) {
                alarma.setEstadoProcesando();
                break;
            }
        }
    }

    public void resolverAlarma(int idAlarma) {
        for (RegistroAlarmaBodega alarma : alarmas) {
            if (alarma.getIdAlarma() == idAlarma) {
                alarma.setEstadoResuelta();
                break;
            }
        }
    }

    public List<RegistroAlarmaBodega> listarTodasLasAlarmas() {
        return new ArrayList<>(alarmas);
    }

    public void mostrarEstadisticas() {
        long totalPendiente = alarmas.stream().filter(a -> a.getEstado().equals("PENDIENTE")).count();
        long totalProcesando = alarmas.stream().filter(a -> a.getEstado().equals("PROCESANDO")).count();
        long totalResuelta = alarmas.stream().filter(a -> a.getEstado().equals("RESUELTA")).count();

        System.out.println("\n[BodegaCentral] === ESTADÍSTICAS DE ALARMAS ===");
        System.out.println("Total Pendiente: " + totalPendiente);
        System.out.println("Total Procesando: " + totalProcesando);
        System.out.println("Total Resuelta: " + totalResuelta);
        System.out.println("========================================\n");
    }
}
