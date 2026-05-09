package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import servicios.ServicioAbastecimientoImpl;

public class InterfazCmLogistics extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textFieldCodMaquina;
    private JComboBox<String> comboBoxTipoAlarma;
    private JTextArea textAreaHistorial;
    private JTextArea textAreaEstado;
    private JButton btnProcesarAlarma;
    private JButton btnLimpiarHistorial;
    private JButton btnActualizarEstado;

    private ServicioAbastecimientoImpl servicio;

    public InterfazCmLogistics(ServicioAbastecimientoImpl servicio) {
        this.servicio = servicio;
        setTitle("CM Logistics - Servicio de Abastecimiento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 460);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("CM Logistics - Abastecimiento de Máquinas");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 11, 724, 20);
        contentPane.add(lblTitulo);

        JPanel panelOperacion = new JPanel();
        panelOperacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelOperacion.setBounds(10, 42, 346, 160);
        contentPane.add(panelOperacion);
        panelOperacion.setLayout(null);

        JLabel lblCodMaquina = new JLabel("Código Máquina");
        lblCodMaquina.setBounds(10, 11, 120, 14);
        panelOperacion.add(lblCodMaquina);

        textFieldCodMaquina = new JTextField();
        textFieldCodMaquina.setBounds(140, 8, 190, 20);
        panelOperacion.add(textFieldCodMaquina);
        textFieldCodMaquina.setColumns(10);

        JLabel lblAlarma = new JLabel("Tipo de Alarma");
        lblAlarma.setBounds(10, 45, 120, 14);
        panelOperacion.add(lblAlarma);

        comboBoxTipoAlarma = new JComboBox<>();
        comboBoxTipoAlarma.setBounds(140, 42, 190, 22);
        comboBoxTipoAlarma.addItem("1 - Escasez de ingredientes");
        comboBoxTipoAlarma.addItem("2 - Insuficiencia de moneda");
        comboBoxTipoAlarma.addItem("3 - Escasez de suministro");
        comboBoxTipoAlarma.addItem("4 - Mal funcionamiento / reparación");
        panelOperacion.add(comboBoxTipoAlarma);

        btnProcesarAlarma = new JButton("Procesar Alarma");
        btnProcesarAlarma.setBounds(10, 80, 320, 30);
        panelOperacion.add(btnProcesarAlarma);

        btnLimpiarHistorial = new JButton("Limpiar Historial");
        btnLimpiarHistorial.setBounds(10, 121, 320, 30);
        panelOperacion.add(btnLimpiarHistorial);

        btnActualizarEstado = new JButton("Actualizar Estado");
        btnActualizarEstado.setBounds(10, 180, 346, 30);
        contentPane.add(btnActualizarEstado);

        JPanel panelHistorial = new JPanel();
        panelHistorial.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelHistorial.setBounds(366, 42, 368, 280);
        contentPane.add(panelHistorial);
        panelHistorial.setLayout(null);

        JLabel lblHistorial = new JLabel("Historial de Abastecimiento");
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setBounds(10, 11, 348, 14);
        panelHistorial.add(lblHistorial);

        JScrollPane scrollPaneHistorial = new JScrollPane();
        scrollPaneHistorial.setBounds(10, 36, 348, 233);
        panelHistorial.add(scrollPaneHistorial);

        textAreaHistorial = new JTextArea();
        textAreaHistorial.setEditable(false);
        scrollPaneHistorial.setViewportView(textAreaHistorial);

        JPanel panelEstado = new JPanel();
        panelEstado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelEstado.setBounds(10, 221, 346, 201);
        contentPane.add(panelEstado);
        panelEstado.setLayout(null);

        JLabel lblEstado = new JLabel("Estado de Máquinas Procesadas");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setBounds(10, 11, 326, 14);
        panelEstado.add(lblEstado);

        JScrollPane scrollPaneEstado = new JScrollPane();
        scrollPaneEstado.setBounds(10, 36, 326, 154);
        panelEstado.add(scrollPaneEstado);

        textAreaEstado = new JTextArea();
        textAreaEstado.setEditable(false);
        scrollPaneEstado.setViewportView(textAreaEstado);

        btnProcesarAlarma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarAlarma();
            }
        });

        btnLimpiarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                servicio.limpiarHistorial();
                textAreaHistorial.setText("");
                appendEstado("Historial limpiado.");
            }
        });

        btnActualizarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarHistorial();
                actualizarEstado();
            }
        });

        actualizarHistorial();
        actualizarEstado();
    }

    private void procesarAlarma() {
        try {
            int codMaquina = Integer.parseInt(textFieldCodMaquina.getText().trim());
            int tipoAlarma = comboBoxTipoAlarma.getSelectedIndex() + 1;
            servicio.abastecer(codMaquina, tipoAlarma, null);
            appendEstado("Alarma procesada para máquina " + codMaquina + " (tipo " + tipoAlarma + ")");
            actualizarHistorial();
            actualizarEstado();
        } catch (NumberFormatException ex) {
            appendEstado("Error: ingrese un código de máquina numérico.");
        } catch (Exception ex) {
            appendEstado("Error al procesar alarma: " + ex.getMessage());
        }
    }

    private void actualizarHistorial() {
        textAreaHistorial.setText("");
        for (String linea : servicio.obtenerHistorial()) {
            textAreaHistorial.append(linea + "\n");
        }
    }

    private void actualizarEstado() {
        textAreaEstado.setText("");
        Map<Integer, Integer> estadoMaquinas = servicio.obtenerEstadoMaquinas();
        if (estadoMaquinas.isEmpty()) {
            textAreaEstado.append("No se han procesado máquinas aún.\n");
            return;
        }
        textAreaEstado.append(estadoMaquinas.entrySet().stream()
                .map(entry -> "Máquina " + entry.getKey() + " -> Tipo de alarma " + entry.getValue())
                .collect(Collectors.joining("\n")));
    }

    private void appendEstado(String mensaje) {
        textAreaHistorial.append(mensaje + "\n");
    }
}
