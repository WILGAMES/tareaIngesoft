package mantenimientoExistencias;

import java.util.*;

/**
 * Implementación del componente Inventario
 * Gestiona el mantenimiento y control de existencias de suministros,
 * ingredientes, monedas y herramientas de reparación
 */
public class InventarioImpl implements Inventario {
    
    private Map<String, Integer> suministros;
    private Map<String, Integer> ingredientes;
    private Map<String, Integer> monedas;
    private List<String> movimientos;
    
    public InventarioImpl() {
        this.suministros = new HashMap<>();
        this.ingredientes = new HashMap<>();
        this.monedas = new HashMap<>();
        this.movimientos = new ArrayList<>();
        
        inicializarInventarios();
    }
    
    /**
     * Inicializa los inventarios con valores predeterminados
     */
    private void inicializarInventarios() {
        // Inicializar suministros
        suministros.put("VASOS", 200);
        suministros.put("FILTROS", 150);
        suministros.put("SERVILLETAS", 300);
        suministros.put("CUCHARAS", 100);
        suministros.put("DESTORNILLADOR", 10);
        suministros.put("LLAVE_INGLESA", 5);
        
        // Inicializar ingredientes
        ingredientes.put("CAFE", 100);
        ingredientes.put("AZUCAR", 50);
        ingredientes.put("CHOCOLATE", 30);
        ingredientes.put("LECHE", 40);
        
        // Inicializar monedas
        monedas.put("MONEDA_500", 500);
        monedas.put("MONEDA_1000", 300);
        monedas.put("MONEDA_2000", 200);
        
        registrarMovimiento("Inventarios inicializados");
    }
    
    /**
     * Abastece suministros
     * Incrementa las existencias de suministros en bodega
     */
    @Override
    public void abastecerSuministros() {
        System.out.println("   [ Abasteciendo Suministros ]");
        
        try {
            int cantidadAbastecer = 50; // Cantidad a reabastecer por tipo
            
            // Reabastecer cada suministro
            for (String suministro : suministros.keySet()) {
                int cantidadActual = suministros.get(suministro);
                int nuevaCantidad = cantidadActual + cantidadAbastecer;
                suministros.put(suministro, nuevaCantidad);
                
                System.out.println("   ✓ " + suministro);
                System.out.println("     - Anterior: " + cantidadActual + " unidades");
                System.out.println("     - Agregado: " + cantidadAbastecer + " unidades");
                System.out.println("     - Nuevo total: " + nuevaCantidad + " unidades");
            }
            
            System.out.println("   ✓ Abastecimiento de suministros completado");
            registrarMovimiento("Suministros abastecidos: " + suministros.size() + " tipos");
            
        } catch (Exception e) {
            System.err.println("   Error al abastecer suministros: " + e.getMessage());
            registrarMovimiento("ERROR: Fallo en abastecimiento de suministros");
        }
    }
    
    /**
     * Abastece monedas
     * Incrementa las existencias de monedas en bodega
     */
    @Override
    public void abastecerMonedas() {
        System.out.println("   [ Abasteciendo Monedas ]");
        
        try {
            int cantidadAbastecer = 100; // Cantidad a reabastecer por denominación
            
            // Reabastecer cada denominación
            for (String moneda : monedas.keySet()) {
                int cantidadActual = monedas.get(moneda);
                int nuevaCantidad = cantidadActual + cantidadAbastecer;
                monedas.put(moneda, nuevaCantidad);
                
                System.out.println("   ✓ " + moneda);
                System.out.println("     - Anterior: " + cantidadActual + " unidades");
                System.out.println("     - Agregado: " + cantidadAbastecer + " unidades");
                System.out.println("     - Nuevo total: " + nuevaCantidad + " unidades");
            }
            
            System.out.println("   ✓ Abastecimiento de monedas completado");
            registrarMovimiento("Monedas abastecidas: " + monedas.size() + " denominaciones");
            
        } catch (Exception e) {
            System.err.println("   Error al abastecer monedas: " + e.getMessage());
            registrarMovimiento("ERROR: Fallo en abastecimiento de monedas");
        }
    }
    
    /**
     * Abastece ingredientes
     * Incrementa las existencias de ingredientes en bodega
     */
    @Override
    public void abastecerIngredientes() {
        System.out.println("   [ Abasteciendo Ingredientes ]");
        
        try {
            int cantidadAbastecer = 30; // Cantidad a reabastecer por tipo
            
            // Reabastecer cada ingrediente
            for (String ingrediente : ingredientes.keySet()) {
                int cantidadActual = ingredientes.get(ingrediente);
                int nuevaCantidad = cantidadActual + cantidadAbastecer;
                ingredientes.put(ingrediente, nuevaCantidad);
                
                System.out.println("   ✓ " + ingrediente);
                System.out.println("     - Anterior: " + cantidadActual + " unidades");
                System.out.println("     - Agregado: " + cantidadAbastecer + " unidades");
                System.out.println("     - Nuevo total: " + nuevaCantidad + " unidades");
            }
            
            System.out.println("   ✓ Abastecimiento de ingredientes completado");
            registrarMovimiento("Ingredientes abastecidos: " + ingredientes.size() + " tipos");
            
        } catch (Exception e) {
            System.err.println("   Error al abastecer ingredientes: " + e.getMessage());
            registrarMovimiento("ERROR: Fallo en abastecimiento de ingredientes");
        }
    }
    
    /**
     * Registra un movimiento en el historial
     */
    private void registrarMovimiento(String movimiento) {
        String timestamp = new Date().toString();
        movimientos.add("[" + timestamp + "] " + movimiento);
    }
    
    /**
     * Retorna el historial de movimientos
     */
    public List<String> obtenerHistorialMovimientos() {
        return new ArrayList<>(movimientos);
    }
    
    /**
     * Retorna el estado actual de suministros
     */
    public Map<String, Integer> obtenerSuministros() {
        return new HashMap<>(suministros);
    }
    
    /**
     * Retorna el estado actual de ingredientes
     */
    public Map<String, Integer> obtenerIngredientes() {
        return new HashMap<>(ingredientes);
    }
    
    /**
     * Retorna el estado actual de monedas
     */
    public Map<String, Integer> obtenerMonedas() {
        return new HashMap<>(monedas);
    }
    
    /**
     * Obtiene el estado completo del inventario
     */
    public Map<String, Map<String, Integer>> obtenerEstadoCompleto() {
        Map<String, Map<String, Integer>> estado = new HashMap<>();
        estado.put("suministros", new HashMap<>(suministros));
        estado.put("ingredientes", new HashMap<>(ingredientes));
        estado.put("monedas", new HashMap<>(monedas));
        return estado;
    }
    
    /**
     * Verifica si hay suficiente cantidad de un item específico
     */
    public boolean verificarExistencia(String tipo, String item, int cantidad) {
        Map<String, Integer> inventario = obtenerInventarioPorTipo(tipo);
        if (inventario == null) {
            return false;
        }
        Integer disponible = inventario.get(item);
        return disponible != null && disponible >= cantidad;
    }
    
    /**
     * Obtiene el inventario según el tipo
     */
    private Map<String, Integer> obtenerInventarioPorTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "suministros":
                return suministros;
            case "ingredientes":
                return ingredientes;
            case "monedas":
                return monedas;
            default:
                return null;
        }
    }
}
