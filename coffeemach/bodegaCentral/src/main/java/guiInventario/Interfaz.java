package guiInventario;

import bodega.Bodega;
import mantenimientoExistencias.Inventario;


public class Interfaz {

    private Inventario inventario;
    private Bodega bodega;
    
    public Interfaz() {
    }
    

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Bodega getBodega() {
        return bodega;
    }
    
 
    public void consultarInventario() {
        if (bodega != null) {
            System.out.println("=== Estado del Inventario ===");
            bodega.consultarIngredientes();
            bodega.consultarMonedas();
            bodega.consultarSuministros();
        } else {
            System.out.println("Bodega no inicializada");
        }
    }
    

    public void generarReporte() {
        System.out.println("\n=== Reporte de Bodega ===");
        if (bodega != null) {
            bodega.consultarIngredientes();
            System.out.println();
            bodega.consultarMonedas();
            System.out.println();
            bodega.consultarSuministros();
        }
    }
}
