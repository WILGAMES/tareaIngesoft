package bodega;

import mantenimientoExistencias.Inventario;
import servicios.Moneda;
import java.util.HashMap;
import java.util.Map;

public class BodegaService implements Bodega, Inventario {

    private final Map<String, Integer> ingredientes;
    private final Map<String, Integer> suministros;
    private final Map<String, Integer> monedas;
    private int kitsReparacion;
    
    // Umbrales mínimos para disparar alarmas
    private static final int UMBRAL_INGREDIENTE = 30;
    private static final int UMBRAL_SUMINISTRO = 50;
    private static final int UMBRAL_MONEDA = 20;
    private static final int UMBRAL_KIT_REPARACION = 2;

    public BodegaService() {
        ingredientes = new HashMap<>();
        ingredientes.put("Café", 100);
        ingredientes.put("Leche", 80);
        ingredientes.put("Azúcar", 120);

        suministros = new HashMap<>();
        suministros.put("Vasos", 200);
        suministros.put("Filtros", 150);
        suministros.put("Jeringas", 50);

        monedas = new HashMap<>();
        monedas.put("CIEN", 100);
        monedas.put("DOCIENTOS", 80);
        monedas.put("QUINIENTOS", 50);

        kitsReparacion = 10;
    }

    @Override
    public void consultarMonedas() {
        System.out.println("[Bodega] Inventario de monedas: " + monedas);
    }

    @Override
    public void consultarIngredientes() {
        System.out.println("[Bodega] Inventario de ingredientes: " + ingredientes);
    }

    @Override
    public void consultarSuministros() {
        System.out.println("[Bodega] Inventario de suministros: " + suministros);
    }

    @Override
    public void entregaKitReparacion() {
        if (kitsReparacion > 0) {
            kitsReparacion--;
            System.out.println("[Bodega] ✅ Entrega de kit de reparación. Kits restantes: " + kitsReparacion);
        } else {
            System.out.println("[Bodega] ❌ No hay kits de reparación disponibles.");
        }
    }

    @Override
    public void retirarExistencias() {
        System.out.println("[Bodega] Retirando existencias para entrega a mantenimiento.");
        ingredientes.computeIfPresent("Café", (k, v) -> Math.max(v - 10, 0));
        suministros.computeIfPresent("Vasos", (k, v) -> Math.max(v - 20, 0));
    }

    @Override
    public void abastecerExistencia() {
        System.out.println("[Bodega] Reponiendo existencias en bodega.");
        ingredientes.replaceAll((k, v) -> v + 20);
        suministros.replaceAll((k, v) -> v + 30);
    }

    @Override
    public void separarExistencias() {
        System.out.println("[Bodega] Separando existencias para despacho.");
        monedas.computeIfPresent("CIEN", (k, v) -> Math.max(v - 10, 0));
        monedas.computeIfPresent("DOCIENTOS", (k, v) -> Math.max(v - 5, 0));
    }

    @Override
    public void abastecerSuministros() {
        suministros.replaceAll((k, v) -> v + 40);
        System.out.println("[Bodega] ✅ Suministros reabastecidos: " + suministros);
    }

    @Override
    public void abastecerMonedas() {
        monedas.put("CIEN", monedas.getOrDefault("CIEN", 0) + 50);
        monedas.put("DOCIENTOS", monedas.getOrDefault("DOCIENTOS", 0) + 30);
        monedas.put("QUINIENTOS", monedas.getOrDefault("QUINIENTOS", 0) + 20);
        System.out.println("[Bodega] ✅ Monedas reabastecidas: " + monedas);
    }

    @Override
    public void abastecerIngredientes() {
        ingredientes.replaceAll((k, v) -> v + 50);
        System.out.println("[Bodega] ✅ Ingredientes reabastecidos: " + ingredientes);
    }

    // ===== MÉTODOS DE RESOLUCIÓN DE ALARMAS =====

    public void resolverAlarmaIngredientes(int idMaquina, String ingrediente) {
        System.out.println("[Bodega] 🔧 Procesando alarma de ingrediente '" + ingrediente + "' para máquina " + idMaquina);
        
        if (ingredientes.containsKey(ingrediente)) {
            int cantidad = ingredientes.get(ingrediente);
            if (cantidad < UMBRAL_INGREDIENTE) {
                System.out.println("[Bodega] Stock bajo (" + cantidad + ") - Iniciando reabastecimiento...");
                abastecerIngredientes();
                System.out.println("[Bodega] ✅ Alarma resuelta: ingrediente '" + ingrediente + "' reabastecido");
            }
        }
    }

    public void resolverAlarmaMoneda(int idMaquina, Moneda moneda) {
        System.out.println("[Bodega] 🔧 Procesando alarma de moneda " + moneda + " para máquina " + idMaquina);
        
        String monedaStr = moneda.toString();
        int cantidad = monedas.getOrDefault(monedaStr, 0);
        
        if (cantidad < UMBRAL_MONEDA) {
            System.out.println("[Bodega] Stock bajo de " + monedaStr + " (" + cantidad + ") - Iniciando reabastecimiento...");
            abastecerMonedas();
            System.out.println("[Bodega] ✅ Alarma resuelta: monedas reabastecidas");
        }
    }

    public void resolverAlarmaSuministro(int idMaquina, String suministro) {
        System.out.println("[Bodega] 🔧 Procesando alarma de suministro '" + suministro + "' para máquina " + idMaquina);
        
        if (suministros.containsKey(suministro)) {
            int cantidad = suministros.get(suministro);
            if (cantidad < UMBRAL_SUMINISTRO) {
                System.out.println("[Bodega] Stock bajo (" + cantidad + ") - Iniciando reabastecimiento...");
                abastecerSuministros();
                System.out.println("[Bodega] ✅ Alarma resuelta: suministro '" + suministro + "' reabastecido");
            }
        }
    }

    public void resolverAlarmaMalFuncionamiento(int idMaquina, String descripcion) {
        System.out.println("[Bodega] 🔧 Procesando alarma de mal funcionamiento en máquina " + idMaquina);
        System.out.println("[Bodega] Descripción del problema: " + descripcion);
        
        if (kitsReparacion >= UMBRAL_KIT_REPARACION) {
            System.out.println("[Bodega] Kits disponibles: " + kitsReparacion + " - Enviando kit de reparación...");
            entregaKitReparacion();
            System.out.println("[Bodega] ✅ Alarma resuelta: kit de reparación despachado");
        } else {
            System.out.println("[Bodega] ⚠️  Stock bajo de kits (" + kitsReparacion + ") - Ordenando más kits urgentemente");
        }
    }

    public void confirmarAbastecimiento(int idMaquina, String idInsumo, int cantidad) {
        System.out.println("[Bodega] 📦 Confirmando abastecimiento: máquina " + idMaquina + 
                          ", insumo '" + idInsumo + "', cantidad " + cantidad);
        // Lógica para validar y registrar el abastecimiento completado
    }

    // Métodos de monitoreo
    public boolean verificarInventarioBajo() {
        boolean hayAlerta = false;
        
        for (Map.Entry<String, Integer> entry : ingredientes.entrySet()) {
            if (entry.getValue() < UMBRAL_INGREDIENTE) {
                System.out.println("[Bodega] ⚠️  Alerta: Ingrediente " + entry.getKey() + " bajo (" + entry.getValue() + ")");
                hayAlerta = true;
            }
        }
        
        for (Map.Entry<String, Integer> entry : suministros.entrySet()) {
            if (entry.getValue() < UMBRAL_SUMINISTRO) {
                System.out.println("[Bodega] ⚠️  Alerta: Suministro " + entry.getKey() + " bajo (" + entry.getValue() + ")");
                hayAlerta = true;
            }
        }
        
        if (kitsReparacion < UMBRAL_KIT_REPARACION) {
            System.out.println("[Bodega] ⚠️  Alerta: Kits de reparación bajo (" + kitsReparacion + ")");
            hayAlerta = true;
        }
        
        return hayAlerta;
    }

    public Map<String, Integer> obtenerEstadoInventario() {
        Map<String, Integer> estado = new HashMap<>();
        estado.put("Ingredientes totales", ingredientes.values().stream().mapToInt(Integer::intValue).sum());
        estado.put("Suministros totales", suministros.values().stream().mapToInt(Integer::intValue).sum());
        estado.put("Monedas totales", monedas.values().stream().mapToInt(Integer::intValue).sum());
        estado.put("Kits reparación", kitsReparacion);
        return estado;
    }
}
