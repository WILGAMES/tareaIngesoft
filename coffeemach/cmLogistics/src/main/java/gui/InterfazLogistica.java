package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logistica.LogisticaService;
import tecnicoMantenimiento.TecnicoManager;
import zonaGeografica.GestorZonas;

public class InterfazLogistica extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextArea textAreaLog;
    private JTextField txtCodigoOperador;
    private JTextField txtIdMaquina;
    private JTextField txtDetalle;
    private JComboBox<String> comboTipoEntrega;

    private LogisticaService logisticaService;
    private TecnicoManager tecnicoManager;
    private GestorZonas gestorZonas;

    public InterfazLogistica() {
        setTitle("Gestión Logística");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 520);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Panel de Logística");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(10, 11, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblOperador = new JLabel("Código de operador:");
        lblOperador.setBounds(10, 60, 140, 20);
        contentPane.add(lblOperador);

        txtCodigoOperador = new JTextField();
        txtCodigoOperador.setBounds(160, 60, 140, 22);
        contentPane.add(txtCodigoOperador);
        txtCodigoOperador.setColumns(10);

        JButton btnAsignar = new JButton("Asignaciones");
        btnAsignar.setBounds(320, 58, 160, 25);
        contentPane.add(btnAsignar);

        JButton btnMostrarTecnicos = new JButton("Mostrar técnicos");
        btnMostrarTecnicos.setBounds(10, 95, 190, 25);
        contentPane.add(btnMostrarTecnicos);

        JButton btnMostrarZonas = new JButton("Mostrar zonas");
        btnMostrarZonas.setBounds(210, 95, 190, 25);
        contentPane.add(btnMostrarZonas);

        JButton btnMostrarEntregas = new JButton("Estado entregas");
        btnMostrarEntregas.setBounds(410, 95, 190, 25);
        contentPane.add(btnMostrarEntregas);

        JLabel lblMaquina = new JLabel("ID de máquina:");
        lblMaquina.setBounds(10, 150, 100, 20);
        contentPane.add(lblMaquina);

        txtIdMaquina = new JTextField();
        txtIdMaquina.setBounds(110, 150, 80, 22);
        contentPane.add(txtIdMaquina);
        txtIdMaquina.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo de intervención:");
        lblTipo.setBounds(210, 150, 140, 20);
        contentPane.add(lblTipo);

        comboTipoEntrega = new JComboBox<>(new String[] {"Ingrediente", "Moneda", "Suministro", "Mantenimiento"});
        comboTipoEntrega.setBounds(330, 150, 150, 22);
        contentPane.add(comboTipoEntrega);

        JLabel lblDetalle = new JLabel("Detalle / descripción:");
        lblDetalle.setBounds(10, 190, 150, 20);
        contentPane.add(lblDetalle);

        txtDetalle = new JTextField();
        txtDetalle.setBounds(160, 190, 320, 22);
        contentPane.add(txtDetalle);
        txtDetalle.setColumns(10);

        JButton btnProgramar = new JButton("Programar acción");
        btnProgramar.setBounds(490, 150, 170, 60);
        contentPane.add(btnProgramar);

        JButton btnActualizar = new JButton("Actualizar vista");
        btnActualizar.setBounds(10, 240, 190, 25);
        contentPane.add(btnActualizar);

        JButton btnRutas = new JButton("Mostrar rutas");
        btnRutas.setBounds(210, 240, 190, 25);
        contentPane.add(btnRutas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 280, 720, 190);
        contentPane.add(scrollPane);

        textAreaLog = new JTextArea();
        textAreaLog.setEditable(false);
        scrollPane.setViewportView(textAreaLog);

        btnAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarOperador();
            }
        });

        btnMostrarTecnicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTecnicos();
            }
        });

        btnMostrarZonas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarZonas();
            }
        });

        btnMostrarEntregas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadoEntregas();
            }
        });

        btnProgramar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programarAccion();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarInformacion();
            }
        });

        btnRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRutas();
            }
        });
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

    private void asignarOperador() {
        try {
            int codigo = Integer.parseInt(txtCodigoOperador.getText().trim());
            if (logisticaService != null) {
                java.util.List<String> asignaciones = logisticaService.asignacionMaquina(codigo, null);
                appendLog("[INTERFAZ] Asignaciones para operador " + codigo + ":\n");
                for (String line : asignaciones) {
                    appendLog("  - " + line + "\n");
                }
            }
        } catch (NumberFormatException e) {
            appendLog("[ERROR] Código de operador inválido.\n");
        }
    }

    private void mostrarTecnicos() {
        if (tecnicoManager != null) {
            appendLog("[INTERFAZ] Técnicos disponibles:\n");
            tecnicoManager.listarTecnicos().forEach(t -> appendLog("  - " + t + "\n"));
        }
    }

    private void mostrarZonas() {
        if (gestorZonas != null) {
            appendLog("[INTERFAZ] Zonas geográficas:\n");
            gestorZonas.listarZonas().forEach(z -> appendLog("  - " + z + "\n"));
        }
    }

    private void mostrarEstadoEntregas() {
        if (logisticaService != null) {
            appendLog("[INTERFAZ] Estado de entregas:\n");
            logisticaService.obtenerEstadoEntregas().forEach((id, estado) -> appendLog("  Máquina " + id + ": " + estado + "\n"));
        }
    }

    private void programarAccion() {
        if (logisticaService == null) {
            appendLog("[ERROR] Servicio de logística no inicializado.\n");
            return;
        }

        try {
            int idMaquina = Integer.parseInt(txtIdMaquina.getText().trim());
            String detalle = txtDetalle.getText().trim();
            String tipo = (String) comboTipoEntrega.getSelectedItem();

            if (tipo == null || detalle.isEmpty()) {
                appendLog("[ERROR] Selecciona un tipo y escribe un detalle válido.\n");
                return;
            }

            switch (tipo) {
                case "Ingrediente":
                    logisticaService.programarEntregaIngredientes(idMaquina, detalle);
                    appendLog("[INTERFAZ] Programada entrega de ingrediente: " + detalle + " para máquina " + idMaquina + "\n");
                    break;
                case "Moneda":
                    try {
                        servicios.Moneda moneda = servicios.Moneda.valueOf(detalle.toUpperCase());
                        logisticaService.programarEntregaMonedas(idMaquina, moneda);
                        appendLog("[INTERFAZ] Programada entrega de moneda: " + moneda + " para máquina " + idMaquina + "\n");
                    } catch (IllegalArgumentException ex) {
                        appendLog("[ERROR] Moneda inválida. Usa CIEN, DOCIENTOS o QUINIENTOS.\n");
                    }
                    break;
                case "Suministro":
                    logisticaService.programarEntregaSuministros(idMaquina, detalle);
                    appendLog("[INTERFAZ] Programada entrega de suministro: " + detalle + " para máquina " + idMaquina + "\n");
                    break;
                case "Mantenimiento":
                    logisticaService.programarMantenimiento(idMaquina, detalle);
                    appendLog("[INTERFAZ] Programado mantenimiento para máquina " + idMaquina + ": " + detalle + "\n");
                    break;
            }
        } catch (NumberFormatException e) {
            appendLog("[ERROR] ID de máquina inválido.\n");
        }
    }

    private void refrescarInformacion() {
        appendLog("[INTERFAZ] Actualizando información...\n");
        mostrarTecnicos();
        mostrarZonas();
        mostrarEstadoEntregas();
    }

    private void mostrarRutas() {
        appendLog("[INTERFAZ] Rutas de distribución:\n");
        appendLog("  - Ruta 1: Centro -> Norte -> Sur\n");
        appendLog("  - Ruta 2: Sur -> Centro -> Norte\n");
    }

    private void appendLog(String mensaje) {
        textAreaLog.append(mensaje);
        textAreaLog.setCaretPosition(textAreaLog.getDocument().getLength());
    }

    public void iniciar() {
        EventQueue.invokeLater(() -> {
            setVisible(true);
            refrescarInformacion();
        });
    }
}
