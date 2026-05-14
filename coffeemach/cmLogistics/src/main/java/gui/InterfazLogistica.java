package gui;

import logistica.LogisticaService;
import tecnicoMantenimiento.TecnicoManager;
import zonaGeografica.GestorZonas;

public class InterfazLogistica {

    private LogisticaService logisticaService;
    private TecnicoManager tecnicoManager;
    private GestorZonas gestorZonas;

    public InterfazLogistica() {
    }

    public void setLogisticaService(LogisticaService logisticaService) {
        this.logisticaService = logisticaService;
    }

    public void setTecnicoManager(TecnicoManager tecnicoManager) {
        this.tecnicoManager = tecnicoManager;
    }

    public void setGestorZonas(GestorZonas gestorZonas) {
        this.gestorZonas = gestorZonas;
    }

    public void mostrarEstadoEntregas() {
        System.out.println("[GUI Logística] Mostrando estado de entregas...");
    }

    public void mostrarTecnicos() {
        System.out.println("[GUI Logística] Mostrando técnicos disponibles...");
    }

    public void mostrarZonasGeograficas() {
        System.out.println("[GUI Logística] Mostrando zonas geográficas...");
    }

    public void mostrarRutas() {
        System.out.println("[GUI Logística] Mostrando rutas de distribución...");
    }
}
