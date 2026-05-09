import java.util.*;
import javax.swing.SwingUtilities;
import bodega.Bodega;
import bodega.BodegaImpl;
import guiInventario.Interfaz;
import mantenimientoExistencias.Inventario;
import mantenimientoExistencias.InventarioImpl;

public class BodegaCentral {
    
    private static Bodega bodega;
    private static Inventario inventario;
    private static Interfaz interfazInventario;
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Inicializando Componente Bodega ===\n");

            bodega = new BodegaImpl();
            System.out.println("Bodega creada e inicializada");
            
            inventario = new InventarioImpl();
            System.out.println("Sistema de Inventario inicializado");

            interfazInventario = new Interfaz();
            interfazInventario.setBodega(bodega);
            interfazInventario.setInventario(inventario);
            System.out.println("Interfaz de Inventario configurada\n");

            SwingUtilities.invokeLater(() -> {
                interfazInventario.setVisible(true);
            });

            System.out.println("\n=== Bodega Central en línea ===");
            System.out.println("Interfaz gráfica disponible. Esperando solicitudes de abastecimiento...\n");
            
        } catch (Exception e) {
            System.err.println("Error en Bodega Central: " + e.getMessage());
            e.printStackTrace();
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
}
