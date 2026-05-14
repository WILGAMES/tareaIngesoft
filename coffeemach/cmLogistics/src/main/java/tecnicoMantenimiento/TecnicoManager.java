package tecnicoMantenimiento;

import java.util.*;

public class TecnicoManager {

    private List<TecnicoMantenimiento> tecnicos;

    public TecnicoManager() {
        this.tecnicos = new ArrayList<>();
    }

    public void registrarTecnico(int idTecnico, String nombre, String especialidad) {
        TecnicoMantenimiento tecnico = new TecnicoMantenimiento(idTecnico, nombre, especialidad);
        tecnicos.add(tecnico);
        System.out.println("[Técnicos] Técnico registrado: " + tecnico);
    }

    public TecnicoMantenimiento obtenerTecnicoDisponible(String especialidad) {
        for (TecnicoMantenimiento tecnico : tecnicos) {
            if (tecnico.isDisponible() && tecnico.getEspecialidad().equalsIgnoreCase(especialidad)) {
                return tecnico;
            }
        }
        return null;
    }

    public List<TecnicoMantenimiento> listarTecnicos() {
        return new ArrayList<>(tecnicos);
    }

    public TecnicoMantenimiento obtenerTecnico(int idTecnico) {
        for (TecnicoMantenimiento tecnico : tecnicos) {
            if (tecnico.getIdTecnico() == idTecnico) {
                return tecnico;
            }
        }
        return null;
    }

    public void cambiarDisponibilidad(int idTecnico, boolean disponible) {
        TecnicoMantenimiento tecnico = obtenerTecnico(idTecnico);
        if (tecnico != null) {
            tecnico.setDisponible(disponible);
            System.out.println("[Técnicos] Técnico " + tecnico.getNombre() + " - Disponible: " + disponible);
        }
    }
}
