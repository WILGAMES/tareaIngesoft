import java.util.*;
import com.zeroc.Ice.*;
import logistica.LogisticaService;
import logistica.AbastecimientoService;
import gui.InterfazLogistica;
import controlAlarma.AlarmaLogistica;
import controlAlarma.GestorAlarmaLogistica;
import tecnicoMantenimiento.TecnicoManager;
import zonaGeografica.GestorZonas;

public class CmLogistics {
    public static void main(String[] args) {
        List<String> extArgs = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "CmLogistic.cfg", extArgs)) {
            ObjectAdapter adapter = communicator.createObjectAdapter("CmLogistics");
            
            // Inicializar gestores base
            TecnicoManager tecnicoManager = new TecnicoManager();
            GestorZonas gestorZonas = new GestorZonas();
            
            // Registrar técnicos de ejemplo
            tecnicoManager.registrarTecnico(1, "Juan Pérez", "Entrega");
            tecnicoManager.registrarTecnico(2, "María García", "Mantenimiento");
            tecnicoManager.registrarTecnico(3, "Carlos López", "Reparación");
            
            // Registrar zonas geográficas de ejemplo
            gestorZonas.crearZona(1, "Centro", "Calle Principal 100", 10.5, -20.3);
            gestorZonas.crearZona(2, "Norte", "Av. Norte 500", 10.6, -20.2);
            gestorZonas.crearZona(3, "Sur", "Av. Sur 200", 10.4, -20.4);
            
            // Inicializar servicios de logística
            LogisticaService logisticaService = new LogisticaService();
            AbastecimientoService abastecimientoService = new AbastecimientoService();
            
            // Inicializar gestor de alarmas
            GestorAlarmaLogistica gestorAlarmas = new GestorAlarmaLogistica(tecnicoManager, gestorZonas);
            
            // Inicializar alarmas con el gestor
            AlarmaLogistica alarmaLogistica = new AlarmaLogistica(logisticaService, gestorAlarmas);
            
            // Inicializar GUI
            InterfazLogistica interfazLogistica = new InterfazLogistica();
            interfazLogistica.setLogisticaService(logisticaService);
            interfazLogistica.setTecnicoManager(tecnicoManager);
            interfazLogistica.setGestorZonas(gestorZonas);
            
            // Registrar servicios en el adapter
            adapter.add(logisticaService, Util.stringToIdentity("logistica"));
            adapter.add(abastecimientoService, Util.stringToIdentity("abastecer"));
            adapter.add(alarmaLogistica, Util.stringToIdentity("alarmas"));
            
            adapter.activate();
            System.out.println("[CmLogistics] ========================================");
            System.out.println("[CmLogistics] Servicio de logística iniciado");
            System.out.println("[CmLogistics] Gestor de técnicos iniciado (3 técnicos)");
            System.out.println("[CmLogistics] Gestor de zonas geográficas iniciado (3 zonas)");
            System.out.println("[CmLogistics] Sistema de alarmas inicializado");
            System.out.println("[CmLogistics] ========================================");
            System.out.println();
            
            // Thread para monitoreo periódico de alarmas
            Thread monitorAlarmas = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(60000); // Cada 60 segundos
                        gestorAlarmas.mostrarEstadisticas();
                        logisticaService.mostrarEstadisticasEntregas();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            monitorAlarmas.setDaemon(true);
            monitorAlarmas.start();
            
            communicator.waitForShutdown();
        }
    }
}
