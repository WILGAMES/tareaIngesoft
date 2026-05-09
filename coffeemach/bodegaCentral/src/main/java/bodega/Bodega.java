package bodega;

import java.util.List;
import java.util.Map;

public interface Bodega {
    public void consultarMonedas();
    public void consultarIngredientes();
    public void consultarSuministros();
    public void entregaKitReparacion();
    public void retirarExistencias();
    public void abastecerExistencia();
    public void separarExistencias();
    public Map<String, Object> obtenerEstadoInventario();
    public List<String> obtenerHistorial();
}
