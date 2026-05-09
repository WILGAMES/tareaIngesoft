package servicios;

import java.util.*;
import com.zeroc.Ice.*;
import bodega.Bodega;

/**
 * Implementación del Servicio de Abastecimiento
 * Componente encargado de las responsabilidades de logística de abastecimiento
 * y reparación para resolver alarmas de las máquinas de café
 */
public class ServicioAbastecimientoImpl implements ServicioAbastecimiento {
    
    private Bodega bodega;
    private List<String> historialAbastecimiento;
    private Map<Integer, Integer> estadoMaquinas; // codMaquina -> tipo de alarma
    
    public ServicioAbastecimientoImpl(Bodega bodega) {
        this.bodega = bodega;
        this.historialAbastecimiento = new ArrayList<>();
        this.estadoMaquinas = new HashMap<>();
    }
    
    /**
     * Método para abastecer una máquina según el tipo de alarma
     * @param codMaquina código de la máquina a abastecer
     * @param tipoAlarma tipo de alarma:
     *                   1 - Escasez de ingredientes
     *                   2 - Insuficiencia de moneda
     *                   3 - Escasez de suministro
     *                   4 - Mal funcionamiento (requiere reparación)
     * @param current contexto de Ice
     */
    @Override
    public void abastecer(int codMaquina, int tipoAlarma, Current current) {
        System.out.println("--- Procesando Alarma para Máquina: " + codMaquina + " ---");
        System.out.println("Tipo de Alarma: " + tipoAlarma);
        
        try {
            String registro = "Máquina " + codMaquina + " - Alarma Tipo: " + tipoAlarma;
            
            switch (tipoAlarma) {
                case 1: // Escasez de ingredientes
                    System.out.println(">> Alarma: Escasez de Ingredientes");
                    abastecerIngredientes(codMaquina);
                    registro += " - Ingredientes Abastecidos";
                    break;
                    
                case 2: // Insuficiencia de moneda
                    System.out.println(">> Alarma: Insuficiencia de Moneda");
                    abastecerMonedas(codMaquina);
                    registro += " - Monedas Abastecidas";
                    break;
                    
                case 3: // Escasez de suministro
                    System.out.println(">> Alarma: Escasez de Suministro");
                    abastecerSuministros(codMaquina);
                    registro += " - Suministros Abastecidos";
                    break;
                    
                case 4: // Mal funcionamiento - requiere reparación
                    System.out.println(">> Alarma: Mal Funcionamiento - Enviando Kit de Reparación");
                    procesarReparacion(codMaquina);
                    registro += " - Kit de Reparación Enviado";
                    break;
                    
                default:
                    System.out.println(">> Tipo de alarma desconocida: " + tipoAlarma);
                    registro += " - Alarma no procesada";
            }
            
            historialAbastecimiento.add(registro);
            estadoMaquinas.put(codMaquina, tipoAlarma);
            
            System.out.println("✓ Abastecimiento completado para máquina: " + codMaquina);
            System.out.println();
            
        } catch (java.lang.Exception e) {
            System.err.println("Error al procesar abastecimiento para máquina " + codMaquina + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Abastece ingredientes a la máquina
     */
    private void abastecerIngredientes(int codMaquina) {
        System.out.println("   - Consultando disponibilidad de ingredientes en bodega...");
        bodega.consultarIngredientes();
        
        System.out.println("   - Separando existencias de ingredientes...");
        bodega.separarExistencias();
        
        System.out.println("   - Retirando ingredientes de bodega...");
        bodega.retirarExistencias();
        
        System.out.println("   - Ingredientes retirados para máquina: " + codMaquina);
    }
    
    /**
     * Abastece monedas a la máquina
     */
    private void abastecerMonedas(int codMaquina) {
        System.out.println("   - Consultando disponibilidad de monedas en bodega...");
        bodega.consultarMonedas();
        
        System.out.println("   - Separando existencias de monedas...");
        bodega.separarExistencias();
        
        System.out.println("   - Retirando monedas de bodega...");
        bodega.retirarExistencias();
        
        System.out.println("   - Monedas retiradas para máquina: " + codMaquina);
    }
    
    /**
     * Abastece suministros a la máquina
     */
    private void abastecerSuministros(int codMaquina) {
        System.out.println("   - Consultando disponibilidad de suministros en bodega...");
        bodega.consultarSuministros();
        
        System.out.println("   - Separando existencias de suministros...");
        bodega.separarExistencias();
        
        System.out.println("   - Retirando suministros de bodega...");
        bodega.retirarExistencias();
        
        System.out.println("   - Suministros retirados para máquina: " + codMaquina);
    }
    
    /**
     * Procesa la reparación de la máquina
     * Retira el kit de reparación de la bodega
     */
    private void procesarReparacion(int codMaquina) {
        System.out.println("   - Preparando kit de reparación...");
        
        System.out.println("   - Consultando herramientas de reparación en bodega...");
        bodega.consultarSuministros(); // Las herramientas se consultan como suministros
        
        System.out.println("   - Entregando kit de reparación...");
        bodega.entregaKitReparacion();
        
        System.out.println("   - Kit de reparación asignado para máquina: " + codMaquina);
    }
    
    /**
     * Retorna el historial de abastecimientos realizados
     */
    public List<String> obtenerHistorial() {
        return new ArrayList<>(historialAbastecimiento);
    }
    
    /**
     * Retorna el estado actual de las máquinas procesadas
     */
    public Map<Integer, Integer> obtenerEstadoMaquinas() {
        return new HashMap<>(estadoMaquinas);
    }
    
    /**
     * Limpia el historial de abastecimientos
     */
    public void limpiarHistorial() {
        historialAbastecimiento.clear();
    }
}
