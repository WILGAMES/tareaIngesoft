import java.util.*;
import com.zeroc.Ice.*;
import servicios.ServicioAbastecimientoImpl;
import bodega.Bodega;
import bodega.BodegaImpl;


public class CmLogistics {
    
    private static Communicator communicator;
    private static ServicioAbastecimientoImpl servicioAbastecimiento;
    private static Bodega bodega;
    
    public static void main(String[] args) {
        List<String> extArgs = new ArrayList<>();
        try {
            communicator = Util.initialize(args, "CmLogistic.cfg", extArgs);
            
            ObjectAdapter adapter = communicator.createObjectAdapter("CmLogistics");
            
       
            bodega = new BodegaImpl();
            System.out.println("Bodega inicializada");
            
      
            servicioAbastecimiento = new ServicioAbastecimientoImpl(bodega);
            System.out.println("Servicio de Abastecimiento inicializado");
            
      
            adapter.add(servicioAbastecimiento, Util.stringToIdentity("Abastecimiento"));
            System.out.println("Servicio registrado como: Abastecimiento");
 
            adapter.activate();
            System.out.println("Adaptador activado");
            System.out.println("\n=== Cm_logistics en línea ===\n");
        
            communicator.waitForShutdown();
            
        } catch (java.lang.Exception e) {
            System.err.println("Error en Cm_logistics: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (communicator != null) {
                try {
                    communicator.destroy();
                    System.out.println("Cm_logistics finalizado");
                } catch (java.lang.Exception e) {
                    System.err.println("Error al destruir communicator: " + e.getMessage());
                }
            }
        }
    }
}
