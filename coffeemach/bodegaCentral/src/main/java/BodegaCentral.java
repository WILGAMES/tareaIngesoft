import java.util.ArrayList;
import java.util.List;
import com.zeroc.Ice.*;
import bodega.BodegaService;
import alarma.AlarmaBodega;
import alarma.GestorAlarmaBodega;

public class BodegaCentral {

    public static void main(String[] args) {
        List<String> extArgs = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "BodegaCentral.cfg", extArgs)) {
            ObjectAdapter adapter = communicator.createObjectAdapter("BodegaCentral");
            
            // Inicializar servicio de bodega
            BodegaService bodega = new BodegaService();
            
            // Inicializar gestor de alarmas
            GestorAlarmaBodega gestorAlarmas = new GestorAlarmaBodega();
            
            // Inicializar alarmas con el gestor
            AlarmaBodega alarma = new AlarmaBodega(bodega, gestorAlarmas);

            adapter.add(bodega, Util.stringToIdentity("Bodega"));
            adapter.add(alarma, Util.stringToIdentity("Alarmas"));
            adapter.activate();

            System.out.println("[BodegaCentral] ========================================");
            System.out.println("[BodegaCentral] Servicio de bodega iniciado");
            System.out.println("[BodegaCentral] Servicio de alarmas iniciado");
            System.out.println("[BodegaCentral] Gestor de alarmas inicializado");
            System.out.println("[BodegaCentral] ========================================");
            System.out.println();
            
            // Thread para monitoreo periódico del inventario
            Thread monitorInventario = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(30000); // Cada 30 segundos
                        if (bodega.verificarInventarioBajo()) {
                            System.out.println("[BodegaCentral] Verificación de inventario - Se detectaron items bajos");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            monitorInventario.setDaemon(true);
            monitorInventario.start();
            
            communicator.waitForShutdown();
        }
    }
}
