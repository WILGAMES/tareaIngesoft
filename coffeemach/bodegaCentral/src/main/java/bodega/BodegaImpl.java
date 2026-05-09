package bodega;

import java.util.*;

/**
 * Implementación del componente Bodega
 * Representa el sitio donde se maneja el inventario físico de suministros,
 * ingredientes y herramientas de reparación de las máquinas de café
 */
public class BodegaImpl implements Bodega {
    
    private Map<String, Integer> inventarioIngredientes;   // idIngrediente -> cantidad
    private Map<String, Integer> inventarioSuministros;    // idSuministro -> cantidad
    private Map<String, Integer> inventarioMonedas;        // tipoMoneda -> cantidad
    private Map<String, Integer> herramientasReparacion;   // herramienta -> cantidad
    
    private List<String> exitenciasSelencionadas;          // Existencias separadas para retiro
    private List<String> movimientos;                      // Historial de movimientos
    
    public BodegaImpl() {
        // Inicializar inventarios vacíos
        this.inventarioIngredientes = new HashMap<>();
        this.inventarioSuministros = new HashMap<>();
        this.inventarioMonedas = new HashMap<>();
        this.herramientasReparacion = new HashMap<>();
        
        this.exitenciasSelencionadas = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        
        inicializarInventarios();
    }
    
    /**
     * Inicializa los inventarios con valores predeterminados
     */
    private void inicializarInventarios() {
        // Ingredientes
        inventarioIngredientes.put("CAFE", 100);
        inventarioIngredientes.put("AZUCAR", 50);
        inventarioIngredientes.put("CHOCOLATE", 30);
        inventarioIngredientes.put("LECHE", 40);
        
        // Suministros
        inventarioSuministros.put("VASOS", 200);
        inventarioSuministros.put("FILTROS", 150);
        inventarioSuministros.put("SERVILLETAS", 300);
        inventarioSuministros.put("CUCHARAS", 100);
        
        // Monedas
        inventarioMonedas.put("MONEDA_500", 500);
        inventarioMonedas.put("MONEDA_1000", 300);
        inventarioMonedas.put("MONEDA_2000", 200);
        
        // Herramientas de reparación
        herramientasReparacion.put("DESTORNILLADOR", 10);
        herramientasReparacion.put("LLAVE_INGLESA", 5);
        herramientasReparacion.put("ALICATE", 8);
        herramientasReparacion.put("MARTILLO", 5);
        herramientasReparacion.put("REPUESTO_MOTOR", 3);
        herramientasReparacion.put("CABLE", 15);
        
        registrarMovimiento("Bodega inicializada con inventarios estándar");
    }
    
    /**
     * Consulta los ingredientes disponibles en bodega
     */
    @Override
    public void consultarIngredientes() {
        System.out.println("   [ Consulta de Ingredientes ]");
        if (inventarioIngredientes.isEmpty()) {
            System.out.println("   No hay ingredientes disponibles");
        } else {
            inventarioIngredientes.forEach((ingrediente, cantidad) -> 
                System.out.println("   - " + ingrediente + ": " + cantidad + " unidades")
            );
        }
        registrarMovimiento("Consulta de ingredientes realizada");
    }
    
    /**
     * Consulta las monedas disponibles en bodega
     */
    @Override
    public void consultarMonedas() {
        System.out.println("   [ Consulta de Monedas ]");
        if (inventarioMonedas.isEmpty()) {
            System.out.println("   No hay monedas disponibles");
        } else {
            inventarioMonedas.forEach((moneda, cantidad) -> 
                System.out.println("   - " + moneda + ": " + cantidad + " unidades")
            );
        }
        registrarMovimiento("Consulta de monedas realizada");
    }
    
    /**
     * Consulta los suministros disponibles en bodega
     */
    @Override
    public void consultarSuministros() {
        System.out.println("   [ Consulta de Suministros ]");
        if (inventarioSuministros.isEmpty()) {
            System.out.println("   No hay suministros disponibles");
        } else {
            inventarioSuministros.forEach((suministro, cantidad) -> 
                System.out.println("   - " + suministro + ": " + cantidad + " unidades")
            );
        }
        System.out.println("   [ Herramientas de Reparación ]");
        herramientasReparacion.forEach((herramienta, cantidad) -> 
            System.out.println("   - " + herramienta + ": " + cantidad + " unidades")
        );
        registrarMovimiento("Consulta de suministros realizada");
    }
    
    /**
     * Separa existencias de la bodega para ser retiradas
     * Prepara los items que serán entregados a las máquinas
     */
    @Override
    public void separarExistencias() {
        System.out.println("   [ Separando Existencias ]");
        
        exitenciasSelencionadas.clear();
        
        // Seleccionar ingredientes
        seleccionarItems(inventarioIngredientes, 5); // 5 unidades por tipo
        
        // Seleccionar suministros
        seleccionarItems(inventarioSuministros, 10); // 10 unidades por tipo
        
        // Seleccionar monedas
        seleccionarItems(inventarioMonedas, 20); // 20 unidades por tipo
        
        System.out.println("   Existencias separadas: " + exitenciasSelencionadas.size() + " items");
        registrarMovimiento("Existencias separadas para retiro");
    }
    
    /**
     * Método auxiliar para seleccionar items del inventario
     */
    private void seleccionarItems(Map<String, Integer> inventario, int cantidadPorItem) {
        for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
            if (entry.getValue() > 0) {
                int cantidad = Math.min(cantidadPorItem, entry.getValue());
                exitenciasSelencionadas.add(entry.getKey() + ": " + cantidad);
            }
        }
    }
    
    /**
     * Retira las existencias separadas del inventario
     */
    @Override
    public void retirarExistencias() {
        System.out.println("   [ Retirando Existencias de Bodega ]");
        
        if (exitenciasSelencionadas.isEmpty()) {
            System.out.println("   No hay existencias para retirar");
            return;
        }
        
        for (String item : exitenciasSelencionadas) {
            String[] partes = item.split(": ");
            String identificador = partes[0];
            int cantidad = Integer.parseInt(partes[1]);
            
            // Retirar del inventario correspondiente
            if (inventarioIngredientes.containsKey(identificador)) {
                inventarioIngredientes.put(identificador, inventarioIngredientes.get(identificador) - cantidad);
                System.out.println("   ✓ Retirando ingrediente: " + identificador + " (" + cantidad + " unidades)");
            } else if (inventarioSuministros.containsKey(identificador)) {
                inventarioSuministros.put(identificador, inventarioSuministros.get(identificador) - cantidad);
                System.out.println("   ✓ Retirando suministro: " + identificador + " (" + cantidad + " unidades)");
            } else if (inventarioMonedas.containsKey(identificador)) {
                inventarioMonedas.put(identificador, inventarioMonedas.get(identificador) - cantidad);
                System.out.println("   ✓ Retirando monedas: " + identificador + " (" + cantidad + " unidades)");
            }
        }
        
        exitenciasSelencionadas.clear();
        registrarMovimiento("Existencias retiradas de bodega");
    }
    
    /**
     * Abastece nuevas existencias a la bodega
     * Incrementa el inventario
     */
    @Override
    public void abastecerExistencia() {
        System.out.println("   [ Abasteciendo Bodega ]");
        
        // Reabastecer ingredientes
        abastecerInventario(inventarioIngredientes, 100);
        
        // Reabastecer suministros
        abastecerInventario(inventarioSuministros, 50);
        
        // Reabastecer monedas
        abastecerInventario(inventarioMonedas, 200);
        
        // Reabastecer herramientas
        abastecerInventario(herramientasReparacion, 10);
        
        System.out.println("   ✓ Inventarios abastecidos correctamente");
        registrarMovimiento("Abastecimiento de existencias realizado");
    }
    
    /**
     * Método auxiliar para abastecer un inventario
     */
    private void abastecerInventario(Map<String, Integer> inventario, int cantidadPorItem) {
        for (String clave : inventario.keySet()) {
            int cantidadActual = inventario.get(clave);
            inventario.put(clave, cantidadActual + cantidadPorItem);
            System.out.println("   - " + clave + " (nuevo total: " + (cantidadActual + cantidadPorItem) + ")");
        }
    }
    
    /**
     * Entrega un kit de reparación
     */
    @Override
    public void entregaKitReparacion() {
        System.out.println("   [ Preparando Kit de Reparación ]");
        
        if (herramientasReparacion.isEmpty()) {
            System.out.println("   No hay herramientas disponibles para armar kit");
            return;
        }
        
        // Retirar herramientas del inventario
        herramientasReparacion.forEach((herramienta, cantidad) -> {
            if (cantidad > 0) {
                herramientasReparacion.put(herramienta, cantidad - 1);
                System.out.println("   ✓ Agregado al kit: " + herramienta);
            }
        });
        
        System.out.println("   ✓ Kit de reparación completado");
        registrarMovimiento("Kit de reparación entregado");
    }
    
    /**
     * Registra los movimientos en el historial
     */
    private void registrarMovimiento(String movimiento) {
        movimientos.add(new Date() + " - " + movimiento);
    }
    
    /**
     * Retorna el historial de movimientos
     */
    public List<String> obtenerHistorial() {
        return new ArrayList<>(movimientos);
    }
    
    /**
     * Retorna el estado actual del inventario
     */
    public Map<String, Object> obtenerEstadoInventario() {
        Map<String, Object> estado = new HashMap<>();
        estado.put("ingredientes", new HashMap<>(inventarioIngredientes));
        estado.put("suministros", new HashMap<>(inventarioSuministros));
        estado.put("monedas", new HashMap<>(inventarioMonedas));
        estado.put("herramientas", new HashMap<>(herramientasReparacion));
        return estado;
    }
}
