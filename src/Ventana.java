import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ventana extends JFrame{
    JButton[] botones;
    List<String> values = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"," ");
    String[] ordenados = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"," "};
    int posicion;

    public Ventana() {
        //PROPIEDADES VENTANA
        this.setTitle("ROMPECABEZAS NUMERICO");
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4, 4, 1, 1));
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Collections.shuffle(values);

        botones = new JButton[16];

        for (int i = 0; i < values.size(); i++) {
            botones[i] = new JButton();
            botones[i].setText(values.get(i));

            //POS DEL BOTON VACIO
            posicion = values.indexOf(" ");

            botones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //POS BOTON SELECCIONADO
                    int seleccionado = -1;

                    for (int j = 0; j < botones.length; j++) {
                        //**el método getSource() devuelve el objeto que ha generado el evento, en este caso el botón que ha sido presionado**
                        if (botones[j] == e.getSource()) {
                            seleccionado = j;
                        }
                    }

                    //POSICIONES VALIDAS DEL BOTON
                    //**modulo comprueba en que columna está y si existe izq o der**
                    if (seleccionado == posicion - 4 || seleccionado == posicion + 4 ||
                            (posicion % 4 != 0 && seleccionado == posicion - 1) ||
                            (posicion % 4 != 3 && seleccionado == posicion + 1)) {

                        //CAMBIAR TEXTO
                        String textoSeleccionado = botones[seleccionado].getText();
                        botones[posicion].setText(textoSeleccionado);
                        botones[seleccionado].setText(" ");

                        //ACTUALIZA LA NUEVA POSICION EN ARRAY Y NUEVA POSICION EN VARIABLE
                        values.set(posicion, textoSeleccionado);
                        values.set(seleccionado, " ");
                        System.out.println(values);
                        posicion = seleccionado;

                        //COMPARACION GANAR
                        boolean ordenado = true;

                        for (int i = 0; i < botones.length; i++) {
                            if (!botones[i].getText().equals(ordenados[i])) {
                                ordenado = false;
                            }
                        }

                        //GANAR
                        if (ordenado) {
                            JOptionPane.showMessageDialog(null, "GANASTE");
                            reiniciarTablero();
                        }
                    }
                }
            });

            panel.add(botones[i]);
            botonEstilo(botones,i);

        }

        JPanel marco = new JPanel(new BorderLayout());
        marco.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        marco.setBackground(Color.decode("#c6dee9"));
        marco.add(panel, BorderLayout.CENTER);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 20));
        btnReiniciar.setPreferredSize(new Dimension(120, 40));
        btnReiniciar.setBackground(Color.WHITE);
        btnReiniciar.setForeground(Color.BLACK);

        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reiniciarTablero();
                repaint();
                revalidate();
            }
        });

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(Color.decode("#c6dee9"));
        panelInferior.add(btnReiniciar);

        marco.add(panelInferior, BorderLayout.SOUTH);

        this.add(marco, BorderLayout.CENTER);

        this.repaint();
        this.revalidate();
    }

    public void reiniciarTablero() {
        Collections.shuffle(values);

        for (int i = 0; i < values.size(); i++) {
            botones[i].setText(values.get(i));

            if (values.get(i).equals(" ")) {
                posicion = i;
            }
        }
    }

    public void botonEstilo(JButton[] botones, int i) {

        botones[i].setFont(new Font("Arial", Font.BOLD, 40));
        botones[i].setBorder(null);
        botones[i].setBackground(Color.WHITE);
        botones[i].setForeground(Color.BLACK);
    }
}