package logistica;

import com.zeroc.Ice.Current;
import servicios.ServicioComLogistica;
import servicios.Moneda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogisticaService implements ServicioComLogistica {

    private Map<Integer, String> estadoEntregas; // idMaquina -> estado
    private Map<Integer, Long> fechasEntrega;    // idMaquina -> fecha programada
    private int contadorEntregas;

    public LogisticaService() {
        this.estadoEntregas = new HashMap<>();
        this.fechasEntrega = new HashMap<>();
        this.contadorEntregas = 0;
    }

    @Override
    public List<String> asignacionMaquina(int codigoOperador, Current current) {
        List<String> asignaciones = new ArrayList<>();
        asignaciones.add("Operador " + codigoOperador + ": atender abasto de la máquina 101");
        asignaciones.add("Operador " + codigoOperador + ": verificar reparación en la máquina 107");
        asignaciones.add("Operador " + codigoOperador + ": revisar stock de ingredientes en bodega");
        System.out.println("[CmLogistics] asignacionMaquina para operador " + codigoOperador + " -> " + asignaciones);
        return asignaciones;
    }

    @Override
    public List<String> asignacionMaquinasDesabastecidas(int codigoOperador, Current current) {
        List<String> maquinas = new ArrayList<>();
        maquinas.add("Máquina 102: escasez de ingredientes");
        maquinas.add("Máquina 108: moneda insuficiente");
        maquinas.add("Máquina 110: falta de suministros");
        System.out.println("[CmLogistics] asignacionMaquinasDesabastecidas para operador " + codigoOperador + " -> " + maquinas);
        return maquinas;
    }

    @Override
    public boolean inicioSesion(int codigoOperador, String password, Current current) {
        boolean activo = password != null && password.equals("logistica123");
        System.out.println("[CmLogistics] inicioSesion operador " + codigoOperador + " -> " + (activo ? "aceptado" : "rechazado"));
        return activo;
    }

    // ===== MÉTODOS DE RESOLUCIÓN DE ALARMAS =====

    public void programarEntregaIngredientes(int idMaq, String ingrediente) {
        contadorEntregas++;
        System.out.println("[CmLogistics] 🔧 Procesando alarma de ingrediente '" + ingrediente + "'");
        System.out.println("[CmLogistics] 📦 Entrega #" + contadorEntregas + " programada para máquina " + idMaq);
        
        estadoEntregas.put(idMaq, "INGREDIENTE_PROGRAMADO");
        fechasEntrega.put(idMaq, System.currentTimeMillis() + 60000); // 1 minuto
        
        simularTransporte(idMaq, "ingrediente", ingrediente);
    }

    public void programarEntregaMonedas(int idMaq, Moneda moneda) {
        contadorEntregas++;
        System.out.println("[CmLogistics] 🔧 Procesando alarma de monedas " + moneda);
        System.out.println("[CmLogistics] 📦 Entrega #" + contadorEntregas + " programada para máquina " + idMaq);
        
        estadoEntregas.put(idMaq, "MONEDAS_PROGRAMADO");
        fechasEntrega.put(idMaq, System.currentTimeMillis() + 45000); // 45 segundos
        
        simularTransporte(idMaq, "monedas", moneda.toString());
    }

    public void programarEntregaSuministros(int idMaq, String suministro) {
        contadorEntregas++;
        System.out.println("[CmLogistics] 🔧 Procesando alarma de suministro '" + suministro + "'");
        System.out.println("[CmLogistics] 📦 Entrega #" + contadorEntregas + " programada para máquina " + idMaq);
        
        estadoEntregas.put(idMaq, "SUMINISTRO_PROGRAMADO");
        fechasEntrega.put(idMaq, System.currentTimeMillis() + 50000); // 50 segundos
        
        simularTransporte(idMaq, "suministro", suministro);
    }

    public void programarMantenimiento(int idMaq, String descripcion) {
        contadorEntregas++;
        System.out.println("[CmLogistics] 🔧 Procesando alarma de mantenimiento");
        System.out.println("[CmLogistics] Problema: " + descripcion);
        System.out.println("[CmLogistics] 🚗 Visita técnica #" + contadorEntregas + " programada para máquina " + idMaq);
        
        estadoEntregas.put(idMaq, "MANTENIMIENTO_PROGRAMADO");
        fechasEntrega.put(idMaq, System.currentTimeMillis() + 120000); // 2 minutos
        
        simularTransporte(idMaq, "mantenimiento", descripcion);
    }

    public void registrarEntrega(int idMaq, String idInsumo, int cantidad) {
        System.out.println("[CmLogistics] ✅ Entrega confirmada - Máquina: " + idMaq + 
                          ", Insumo: " + idInsumo + ", Cantidad: " + cantidad);
        estadoEntregas.put(idMaq, "ENTREGADO");
    }

    // ===== MÉTODOS DE MONITOREO =====

    public Map<Integer, String> obtenerEstadoEntregas() {
        return new HashMap<>(estadoEntregas);
    }

    public String obtenerEstadoMaquina(int idMaq) {
        return estadoEntregas.getOrDefault(idMaq, "SIN_PENDIENTES");
    }

    public int obtenerTotalEntregas() {
        return contadorEntregas;
    }

    public void mostrarEstadisticasEntregas() {
        System.out.println("\n[CmLogistics] === ESTADÍSTICAS DE ENTREGAS ===");
        System.out.println("Total de entregas procesadas: " + contadorEntregas);
        System.out.println("Entregas activas: " + estadoEntregas.size());
        
        for (Map.Entry<Integer, String> entry : estadoEntregas.entrySet()) {
            System.out.println("  Máquina " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("==========================================\n");
    }

    // ===== MÉTODOS AUXILIARES =====

    private void simularTransporte(int idMaq, String tipo, String detalles) {
        Thread transporteThread = new Thread(() -> {
            try {
                System.out.println("[CmLogistics] 🚗 En tránsito hacia máquina " + idMaq + 
                                 " (" + tipo + ": " + detalles + ")");
                
                Thread.sleep(5000); // Simular transporte
                
                System.out.println("[CmLogistics] ✅ " + tipo.toUpperCase() + 
                                 " entregado en máquina " + idMaq);
                estadoEntregas.put(idMaq, "ENTREGADO");
            } catch (InterruptedException e) {
                System.out.println("[CmLogistics] ❌ Error en transporte para máquina " + idMaq);
            }
        });
        
        transporteThread.setDaemon(true);
        transporteThread.start();
    }
}
