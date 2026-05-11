import java.util.*;
import javax.swing.SwingUtilities;
import com.zeroc.Ice.*;
import bodega.Bodega;
import bodega.BodegaImpl;
import guiInventario.Interfaz;
import mantenimientoExistencias.Inventory;
import mantenimientoExistencias.InventarioImpl;
import servicios.*;

public class BodegaCentral {
    
    private static Bodega bodega;
    private static Inventario inventario;
    private static Interfaz interfazInventario;
    private static Communicator communicator;
    private static ObjectAdapter adapter;
    private static AlarmaService alarmaService;
    private static RecetaService recetaService;
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Inicializando Componente Bodega ===\n");

            // Inicializar comunicator Ice
            List<String> params = new ArrayList<>();
            communicator = Util.initialize(args, "bodega.cfg", params);
            System.out.println("Communicator Ice inicializado");

            // Crear adaptador de objetos
            adapter = communicator.createObjectAdapter("Server");
            
            // Conectar a los servicios del Servidor Central
            conectarAlServidorCentral();
            System.out.println("Conectado al Servidor Central");

            bodega = new BodegaImpl();
            System.out.println("Bodega creada e inicializada");
            
            inventario = new InventarioImpl();
            System.out.println("Sistema de Inventario inicializado");

            interfazInventario = new Interfaz();
            interfazInventario.setBodega(bodega);
            interfazInventario.setInventario(inventario);
            System.out.println("Interfaz de Inventario configurada\n");

            // Activar adaptador para que bodegaCentral sea accesible
            adapter.activate();

            SwingUtilities.invokeLater(() -> {
                interfazInventario.setVisible(true);
            });

            System.out.println("\n=== Bodega Central en línea ===");
            System.out.println("Interfaz gráfica disponible. Esperando solicitudes de abastecimiento...\n");
            System.out.println("Conectada al Servidor Central y receptiva a notificaciones\n");
            
        } catch (Exception e) {
            System.err.println("Error en Bodega Central: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void conectarAlServidorCentral() throws Exception {
        try {
            // Obtener referencias a los servicios del servidor central
            String alarmaServiceStr = "Alarmas:tcp -h localhost -p 12345";
            String recetaServiceStr = "Recetas:tcp -h localhost -p 12345";
            
            ObjectPrx alarmaBase = communicator.stringToProxy(alarmaServiceStr);
            ObjectPrx recetaBase = communicator.stringToProxy(recetaServiceStr);
            
            alarmaService = AlarmaServicePrxHelper.checkedCast(alarmaBase);
            recetaService = RecetaServicePrxHelper.checkedCast(recetaBase);
            
            if (alarmaService == null) {
                throw new RuntimeException("Error: No se pudo conectar al servicio de Alarmas del Servidor Central");
            }
            if (recetaService == null) {
                throw new RuntimeException("Error: No se pudo conectar al servicio de Recetas del Servidor Central");
            }
            
            System.out.println("Servicios del Servidor Central obtenidos exitosamente");
            
        } catch (LocalException ex) {
            throw new Exception("No se pudo conectar al Servidor Central en localhost:12345", ex);
        }
    }
    
    public static Bodega obtenerBodega() {
        return bodega;
    }

    public static Inventario obtenerInventario() {
        return inventario;
    }

    public static Interfaz obtenerInterfazInventario() {
        return interfazInventario;
    }
    
    public static AlarmaService obtenerAlarmaService() {
        return alarmaService;
    }
    
    public static RecetaService obtenerRecetaService() {
        return recetaService;
    }
}

