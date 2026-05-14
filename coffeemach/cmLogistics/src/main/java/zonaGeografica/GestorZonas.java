package zonaGeografica;

import java.util.*;

public class GestorZonas {

    private List<ZonaGeografica> zonas;

    public GestorZonas() {
        this.zonas = new ArrayList<>();
    }

    public void crearZona(int idZona, String nombre, String ubicacion, double latitud, double longitud) {
        ZonaGeografica zona = new ZonaGeografica(idZona, nombre, ubicacion, latitud, longitud);
        zonas.add(zona);
        System.out.println("[Zonas] Zona creada: " + zona);
    }

    public ZonaGeografica obtenerZona(int idZona) {
        for (ZonaGeografica zona : zonas) {
            if (zona.getIdZona() == idZona) {
                return zona;
            }
        }
        return null;
    }

    public List<ZonaGeografica> listarZonas() {
        return new ArrayList<>(zonas);
    }

    public ZonaGeografica obtenerZonaMasCercana(double latitud, double longitud) {
        ZonaGeografica masProxima = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (ZonaGeografica zona : zonas) {
            double distancia = calcularDistancia(latitud, longitud, zona.getLatitud(), zona.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masProxima = zona;
            }
        }

        return masProxima;
    }

    public void asignarTecnicoAZona(int idZona, int idTecnico) {
        ZonaGeografica zona = obtenerZona(idZona);
        if (zona != null) {
            zona.asignarTecnico(idTecnico);
        }
    }

    public void registrarMaquinaEnZona(int idZona) {
        ZonaGeografica zona = obtenerZona(idZona);
        if (zona != null) {
            zona.incrementarMaquinas();
            System.out.println("[Zonas] Máquina registrada en zona " + zona.getNombre());
        }
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Fórmula de Haversine simplificada
        double dx = lat2 - lat1;
        double dy = lon2 - lon1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
