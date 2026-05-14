package controlAlarma;

import com.zeroc.Ice.Current;
import servicios.AlarmaService;
import logistica.LogisticaService;

public class AlarmaLogistica implements AlarmaService {

    private final LogisticaService logisticaService;
    private final GestorAlarmaLogistica gestorAlarmas;

    public AlarmaLogistica(LogisticaService logisticaService, GestorAlarmaLogistica gestorAlarmas) {
        this.logisticaService = logisticaService;
        this.gestorAlarmas = gestorAlarmas;
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        String desc = "Entrega de ingrediente: " + iDing;
        gestorAlarmas.registrarAlarma(RegistroAlarmaLogistica.ALARMA_ENTREGA_INGREDIENTE, idMaq, desc);
        logisticaService.programarEntregaIngredientes(idMaq, iDing);
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(servicios.Moneda moneda, int idMaq, Current current) {
        String desc = "Entrega de monedas: " + moneda;
        gestorAlarmas.registrarAlarma(RegistroAlarmaLogistica.ALARMA_ENTREGA_MONEDA, idMaq, desc);
        logisticaService.programarEntregaMonedas(idMaq, moneda);
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        String desc = "Entrega de suministro: " + idSumin;
        gestorAlarmas.registrarAlarma(RegistroAlarmaLogistica.ALARMA_ENTREGA_SUMINISTRO, idMaq, desc);
        logisticaService.programarEntregaSuministros(idMaq, idSumin);
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        logisticaService.registrarEntrega(idMaq, idInsumo, cantidad);
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        String desc = "Mantenimiento: " + descri;
        gestorAlarmas.registrarAlarma(RegistroAlarmaLogistica.ALARMA_MANTENIMIENTO, idMaq, desc);
        logisticaService.programarMantenimiento(idMaq, descri);
    }

    public GestorAlarmaLogistica getGestorAlarmas() {
        return gestorAlarmas;
    }
}
