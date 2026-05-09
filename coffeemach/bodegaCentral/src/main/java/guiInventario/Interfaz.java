package guiInventario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import bodega.Bodega;
import mantenimientoExistencias.Inventario;

public class Interfaz extends JFrame {

    private static final long serialVersionUID = 1L;

    private Inventario inventario;
    private Bodega bodega;
    private JTextArea textAreaSalida;
    private JButton btnConsultarInventario;
    private JButton btnSepararExistencias;
    private JButton btnRetirarExistencias;
    private JButton btnAbastecerIngredientes;
    private JButton btnAbastecerMonedas;
    private JButton btnAbastecerSuministros;
    private JButton btnEntregarKit;
    private JButton btnMostrarHistorial;

    public Interfaz() {
        setTitle("Bodega Central - Gestión de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 820, 520);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Bodega Central - Panel de Gestión");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 11, 784, 24);
        contentPane.add(lblTitulo);

        JPanel panelControles = new JPanel();
        panelControles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelControles.setBounds(10, 46, 784, 160);
        contentPane.add(panelControles);
        panelControles.setLayout(null);

        btnConsultarInventario = new JButton("Consultar Inventario");
        btnConsultarInventario.setBounds(10, 11, 238, 35);
        panelControles.add(btnConsultarInventario);

        btnSepararExistencias = new JButton("Separar Existencias");
        btnSepararExistencias.setBounds(10, 57, 238, 35);
        panelControles.add(btnSepararExistencias);

        btnRetirarExistencias = new JButton("Retirar Existencias");
        btnRetirarExistencias.setBounds(10, 103, 238, 35);
        panelControles.add(btnRetirarExistencias);

        btnAbastecerIngredientes = new JButton("Abastecer Ingredientes");
        btnAbastecerIngredientes.setBounds(264, 11, 238, 35);
        panelControles.add(btnAbastecerIngredientes);

        btnAbastecerMonedas = new JButton("Abastecer Monedas");
        btnAbastecerMonedas.setBounds(264, 57, 238, 35);
        panelControles.add(btnAbastecerMonedas);

        btnAbastecerSuministros = new JButton("Abastecer Suministros");
        btnAbastecerSuministros.setBounds(264, 103, 238, 35);
        panelControles.add(btnAbastecerSuministros);

        btnEntregarKit = new JButton("Entregar Kit de Reparación");
        btnEntregarKit.setBounds(518, 11, 256, 35);
        panelControles.add(btnEntregarKit);

        btnMostrarHistorial = new JButton("Mostrar Historial");
        btnMostrarHistorial.setBounds(518, 57, 256, 35);
        panelControles.add(btnMostrarHistorial);

        JLabel lblSalida = new JLabel("Salida de Operaciones");
        lblSalida.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalida.setBounds(10, 217, 784, 14);
        contentPane.add(lblSalida);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 242, 784, 228);
        contentPane.add(scrollPane);

        textAreaSalida = new JTextArea();
        textAreaSalida.setEditable(false);
        scrollPane.setViewportView(textAreaSalida);

        btnConsultarInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        btnSepararExistencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarSepararExistencias();
            }
        });

        btnRetirarExistencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarRetirarExistencias();
            }
        });

        btnAbastecerIngredientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarAbastecerIngredientes();
            }
        });

        btnAbastecerMonedas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarAbastecerMonedas();
            }
        });

        btnAbastecerSuministros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarAbastecerSuministros();
            }
        });

        btnEntregarKit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutarEntregaKit();
            }
        });

        btnMostrarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });
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

    private void mostrarInventario() {
        if (bodega == null) {
            appendSalida("Bodega no inicializada.");
            return;
        }
        appendSalida("=== Estado del Inventario ===");
        Map<String, Object> estado = bodega.obtenerEstadoInventario();
        estado.forEach((seccion, detalle) -> {
            appendSalida("-- " + seccion.toUpperCase() + " --");
            if (detalle instanceof Map) {
                ((Map<?, ?>) detalle).forEach((clave, valor) -> appendSalida("   " + clave + ": " + valor));
            }
            appendSalida("");
        });
    }

    private void ejecutarSepararExistencias() {
        if (bodega == null) {
            appendSalida("Bodega no inicializada.");
            return;
        }
        appendSalida("Procesando: separación de existencias...");
        bodega.separarExistencias();
        appendSalida("Operación completada. Actualice para ver el estado.");
        actualizarPanel();
    }

    private void ejecutarRetirarExistencias() {
        if (bodega == null) {
            appendSalida("Bodega no inicializada.");
            return;
        }
        appendSalida("Procesando: retiro de existencias...");
        bodega.retirarExistencias();
        appendSalida("Existencias retiradas.");
        actualizarPanel();
    }

    private void ejecutarAbastecerIngredientes() {
        if (inventario == null) {
            appendSalida("Inventario no inicializado.");
            return;
        }
        appendSalida("Procesando: abastecimiento de ingredientes...");
        inventario.abastecerIngredientes();
        appendSalida("Ingredientes abastecidos.");
        actualizarPanel();
    }

    private void ejecutarAbastecerMonedas() {
        if (inventario == null) {
            appendSalida("Inventario no inicializado.");
            return;
        }
        appendSalida("Procesando: abastecimiento de monedas...");
        inventario.abastecerMonedas();
        appendSalida("Monedas abastecidas.");
        actualizarPanel();
    }

    private void ejecutarAbastecerSuministros() {
        if (inventario == null) {
            appendSalida("Inventario no inicializado.");
            return;
        }
        appendSalida("Procesando: abastecimiento de suministros...");
        inventario.abastecerSuministros();
        appendSalida("Suministros abastecidos.");
        actualizarPanel();
    }

    private void ejecutarEntregaKit() {
        if (bodega == null) {
            appendSalida("Bodega no inicializada.");
            return;
        }
        appendSalida("Procesando: entrega de kit de reparación...");
        bodega.entregaKitReparacion();
        appendSalida("Kit de reparación entregado.");
        actualizarPanel();
    }

    private void mostrarHistorial() {
        if (inventario == null) {
            appendSalida("Inventario no inicializado.");
            return;
        }
        appendSalida("=== Historial de Inventario ===");
        if (inventario instanceof mantenimientoExistencias.InventarioImpl) {
            List<String> historial = ((mantenimientoExistencias.InventarioImpl) inventario).obtenerHistorialMovimientos();
            if (historial.isEmpty()) {
                appendSalida("No hay movimientos registrados.");
            } else {
                historial.forEach(this::appendSalida);
            }
        } else {
            appendSalida("El historial solo está disponible para la implementación interna.");
        }
    }

    private void actualizarPanel() {
        mostrarInventario();
    }

    private void appendSalida(String texto) {
        textAreaSalida.append(texto + "\n");
        textAreaSalida.setCaretPosition(textAreaSalida.getDocument().getLength());
    }
}
