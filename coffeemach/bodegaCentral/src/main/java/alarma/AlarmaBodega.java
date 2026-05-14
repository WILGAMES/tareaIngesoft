package alarma;

import com.zeroc.Ice.Current;
import servicios.AlarmaService;
import bodega.BodegaService;

public class AlarmaBodega implements AlarmaService {

    private final BodegaService bodegaService;
    private final GestorAlarmaBodega gestorAlarmas;

    public AlarmaBodega(BodegaService bodegaService, GestorAlarmaBodega gestorAlarmas) {
        this.bodegaService = bodegaService;
        this.gestorAlarmas = gestorAlarmas;
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        String desc = "Escasez de ingrediente: " + iDing;
        gestorAlarmas.registrarAlarma(RegistroAlarmaBodega.ALARMA_INGREDIENTE, idMaq, desc);
        bodegaService.resolverAlarmaIngredientes(idMaq, iDing);
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(servicios.Moneda moneda, int idMaq, Current current) {
        String desc = "Insuficiencia de moneda: " + moneda;
        gestorAlarmas.registrarAlarma(RegistroAlarmaBodega.ALARMA_MONEDA, idMaq, desc);
        bodegaService.resolverAlarmaMoneda(idMaq, moneda);
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        String desc = "Escasez de suministro: " + idSumin;
        gestorAlarmas.registrarAlarma(RegistroAlarmaBodega.ALARMA_SUMINISTRO, idMaq, desc);
        bodegaService.resolverAlarmaSuministro(idMaq, idSumin);
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        bodegaService.confirmarAbastecimiento(idMaq, idInsumo, cantidad);
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        String desc = "Mal funcionamiento: " + descri;
        gestorAlarmas.registrarAlarma(RegistroAlarmaBodega.ALARMA_MAL_FUNCIONAMIENTO, idMaq, desc);
        bodegaService.resolverAlarmaMalFuncionamiento(idMaq, descri);
    }

    public GestorAlarmaBodega getGestorAlarmas() {
        return gestorAlarmas;
    }
}
