import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    JButton[] botones;

    public Ventana() {
        //PROPIEDADES VENTANA
        this.setTitle("ROMPECABEZAS NUMERICO");
        this.setSize(800,800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        panel.setBackground(Color.decode("#0da192"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Crear botones
        botones = new JButton[16];
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("" + (i+1));
            panel.add(botones[i]);
        }

        JPanel marco = new JPanel(new BorderLayout());
        marco.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        marco.setBackground(Color.decode("#0da192"));
        marco.add(panel, BorderLayout.CENTER);

        this.add(marco, BorderLayout.CENTER);

        this.repaint();
        this.revalidate();
    }
}