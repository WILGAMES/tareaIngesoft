package guiInventario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bodega.BodegaService;

public class Interfaz extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextArea textAreaLog;
    private JTextField txtItem;
    private JTextField txtMaquina;
    private JTextField txtCantidad;
    private BodegaService bodega;

    public Interfaz() {
        setTitle("Interfaz Bodega Central");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 520);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Panel de Bodega Central");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(10, 11, 300, 30);
        contentPane.add(lblTitulo);

        JButton btnVerIngredientes = new JButton("Ver ingredientes");
        btnVerIngredientes.setBounds(10, 60, 180, 25);
        contentPane.add(btnVerIngredientes);

        JButton btnVerSuministros = new JButton("Ver suministros");
        btnVerSuministros.setBounds(200, 60, 180, 25);
        contentPane.add(btnVerSuministros);

        JButton btnVerMonedas = new JButton("Ver monedas");
        btnVerMonedas.setBounds(390, 60, 180, 25);
        contentPane.add(btnVerMonedas);

        JButton btnVerKits = new JButton("Ver kits reparación");
        btnVerKits.setBounds(580, 60, 160, 25);
        contentPane.add(btnVerKits);

        JButton btnAbastecerIngredientes = new JButton("Abastecer ingredientes");
        btnAbastecerIngredientes.setBounds(10, 100, 220, 25);
        contentPane.add(btnAbastecerIngredientes);

        JButton btnAbastecerSuministros = new JButton("Abastecer suministros");
        btnAbastecerSuministros.setBounds(240, 100, 220, 25);
        contentPane.add(btnAbastecerSuministros);

        JButton btnAbastecerMonedas = new JButton("Abastecer monedas");
        btnAbastecerMonedas.setBounds(470, 100, 220, 25);
        contentPane.add(btnAbastecerMonedas);

        JButton btnEntregaKit = new JButton("Entregar kit reparación");
        btnEntregaKit.setBounds(10, 140, 220, 25);
        contentPane.add(btnEntregaKit);

        JButton btnRetirarExistencias = new JButton("Retirar existencias");
        btnRetirarExistencias.setBounds(240, 140, 220, 25);
        contentPane.add(btnRetirarExistencias);

        JButton btnSepararExistencias = new JButton("Separar existencias");
        btnSepararExistencias.setBounds(470, 140, 220, 25);
        contentPane.add(btnSepararExistencias);

        JLabel lblMaquina = new JLabel("ID máquina:");
        lblMaquina.setBounds(10, 190, 90, 20);
        contentPane.add(lblMaquina);

        txtMaquina = new JTextField();
        txtMaquina.setBounds(110, 190, 120, 22);
        contentPane.add(txtMaquina);
        txtMaquina.setColumns(10);

        JLabel lblItem = new JLabel("Item / moneda:");
        lblItem.setBounds(250, 190, 120, 20);
        contentPane.add(lblItem);

        txtItem = new JTextField();
        txtItem.setBounds(360, 190, 160, 22);
        contentPane.add(txtItem);
        txtItem.setColumns(10);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(530, 190, 80, 20);
        contentPane.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(610, 190, 130, 22);
        contentPane.add(txtCantidad);
        txtCantidad.setColumns(10);

        JButton btnConfirmarAbastecimiento = new JButton("Confirmar abastecimiento");
        btnConfirmarAbastecimiento.setBounds(10, 230, 220, 25);
        contentPane.add(btnConfirmarAbastecimiento);

        JButton btnActualizar = new JButton("Actualizar inventario");
        btnActualizar.setBounds(240, 230, 220, 25);
        contentPane.add(btnActualizar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 270, 740, 190);
        contentPane.add(scrollPane);

        textAreaLog = new JTextArea();
        textAreaLog.setEditable(false);
        scrollPane.setViewportView(textAreaLog);

        btnVerIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarIngredientes();
            }
        });

        btnVerSuministros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSuministros();
            }
        });

        btnVerMonedas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMonedas();
            }
        });

        btnVerKits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarKits();
            }
        });

        btnAbastecerIngredientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abastecerIngredientes();
            }
        });

        btnAbastecerSuministros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abastecerSuministros();
            }
        });

        btnAbastecerMonedas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abastecerMonedas();
            }
        });

        btnEntregaKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregaKitReparacion();
            }
        });

        btnRetirarExistencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarExistencias();
            }
        });

        btnSepararExistencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                separarExistencias();
            }
        });

        btnConfirmarAbastecimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarAbastecimiento();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescarInventario();
            }
        });
    }

    public void setBodegaService(BodegaService bodega) {
        this.bodega = bodega;
    }

    private void mostrarIngredientes() {
        if (bodega != null) {
            appendLog("[INTERFAZ] Inventario de ingredientes:\n");
            bodega.consultarIngredientes();
            refrescarInventario();
        }
    }

    private void mostrarSuministros() {
        if (bodega != null) {
            appendLog("[INTERFAZ] Inventario de suministros:\n");
            bodega.consultarSuministros();
            refrescarInventario();
        }
    }

    private void mostrarMonedas() {
        if (bodega != null) {
            appendLog("[INTERFAZ] Inventario de monedas:\n");
            bodega.consultarMonedas();
            refrescarInventario();
        }
    }

    private void mostrarKits() {
        if (bodega != null) {
            appendLog("[INTERFAZ] Ver kits de reparación:\n");
            Map<String, Integer> estado = bodega.obtenerEstadoInventario();
            appendLog("  Kits reparación: " + estado.getOrDefault("Kits reparación", 0) + "\n");
        }
    }

    private void abastecerIngredientes() {
        if (bodega != null) {
            bodega.abastecerIngredientes();
            appendLog("[INTERFAZ] Se repuso inventario de ingredientes.\n");
            refrescarInventario();
        }
    }

    private void abastecerSuministros() {
        if (bodega != null) {
            bodega.abastecerSuministros();
            appendLog("[INTERFAZ] Se repuso inventario de suministros.\n");
            refrescarInventario();
        }
    }

    private void abastecerMonedas() {
        if (bodega != null) {
            bodega.abastecerMonedas();
            appendLog("[INTERFAZ] Se repuso inventario de monedas.\n");
            refrescarInventario();
        }
    }

    private void entregaKitReparacion() {
        if (bodega != null) {
            bodega.entregaKitReparacion();
            appendLog("[INTERFAZ] Solicitud de kit de reparación procesada.\n");
            refrescarInventario();
        }
    }

    private void retirarExistencias() {
        if (bodega != null) {
            bodega.retirarExistencias();
            appendLog("[INTERFAZ] Inventario reducido para despacho.\n");
            refrescarInventario();
        }
    }

    private void separarExistencias() {
        if (bodega != null) {
            bodega.separarExistencias();
            appendLog("[INTERFAZ] Existencias separadas para despacho.\n");
            refrescarInventario();
        }
    }

    private void confirmarAbastecimiento() {
        if (bodega != null) {
            try {
                int idMaquina = Integer.parseInt(txtMaquina.getText().trim());
                String item = txtItem.getText().trim();
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                bodega.confirmarAbastecimiento(idMaquina, item, cantidad);
                appendLog("[INTERFAZ] Confirmado abastecimiento máquina " + idMaquina + " item " + item + " cantidad " + cantidad + "\n");
                refrescarInventario();
            } catch (NumberFormatException ex) {
                appendLog("[ERROR] Debes ingresar valores numéricos válidos en ID máquina y cantidad.\n");
            }
        }
    }

    private void refrescarInventario() {
        if (bodega != null) {
            appendLog("[INTERFAZ] Resumen de inventario:\n");
            Map<String, Integer> estado = bodega.obtenerEstadoInventario();
            estado.forEach((key, value) -> appendLog("  " + key + ": " + value + "\n"));
            appendLog("\n");
        }
    }

    private void appendLog(String mensaje) {
        textAreaLog.append(mensaje);
        textAreaLog.setCaretPosition(textAreaLog.getDocument().getLength());
    }

    public void iniciar() {
        EventQueue.invokeLater(() -> {
            setVisible(true);
            refrescarInventario();
        });
    }
}
