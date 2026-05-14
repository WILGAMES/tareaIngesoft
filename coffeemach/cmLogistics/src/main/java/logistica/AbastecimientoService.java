package logistica;

import com.zeroc.Ice.Current;
import servicios.ServicioAbastecimiento;

public class AbastecimientoService implements ServicioAbastecimiento {

    @Override
    public void abastecer(int codMaquina, int tipoAlarma, Current current) {
        String evento;
        switch (tipoAlarma) {
            case 1:
                evento = "Abastecimiento de ingredientes para máquina " + codMaquina;
                break;
            case 2:
                evento = "Abastecimiento de suministros para máquina " + codMaquina;
                break;
            case 3:
                evento = "Reparación de máquina " + codMaquina + " solicitada por alarma";
                break;
            default:
                evento = "Solicitud de abasto desconocida para máquina " + codMaquina + " (tipo " + tipoAlarma + ")";
                break;
        }
        System.out.println("[CmLogistics] " + evento);
    }
}
