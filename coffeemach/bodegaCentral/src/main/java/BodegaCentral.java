import java.util.*;
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
            

            demostrarFuncionalidades();
            
            System.out.println("\n=== Bodega Central en línea ===");
            System.out.println("Esperando solicitudes de abastecimiento...\n");
            
        } catch (Exception e) {
            System.err.println("Error en Bodega Central: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    private static void demostrarFuncionalidades() {
        System.out.println("--- Demostrando Funcionalidades ---\n");
        

        System.out.println("1. Consultando Ingredientes:");
        bodega.consultarIngredientes();
        System.out.println();
        
        System.out.println("2. Consultando Monedas:");
        bodega.consultarMonedas();
        System.out.println();
        
        System.out.println("3. Consultando Suministros y Herramientas:");
        bodega.consultarSuministros();
        System.out.println();

        System.out.println("4. Separando existencias para retiro:");
        bodega.separarExistencias();
        System.out.println();
        
        System.out.println("5. Retirando existencias de bodega:");
        bodega.retirarExistencias();
        System.out.println();
        

        System.out.println("6. Abasteciendo suministros:");
        inventario.abastecerSuministros();
        System.out.println();
        
        System.out.println("7. Abasteciendo ingredientes:");
        inventario.abastecerIngredientes();
        System.out.println();
        
        System.out.println("8. Abasteciendo monedas:");
        inventario.abastecerMonedas();
        System.out.println();
        

        System.out.println("9. Preparando kit de reparación:");
        bodega.entregaKitReparacion();
        System.out.println();
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
