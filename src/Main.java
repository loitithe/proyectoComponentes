import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        this.setLayout(new BorderLayout());
        ImageIcon blackOut = new ImageIcon("/resources/BlackOut.png");
        ImageIcon blueStrip = new ImageIcon("resources/BlueStrip.png");
        ImageIcon greenStrip = new ImageIcon("resources/GreenStrip.png");
        ImageIcon pinkStrip = new ImageIcon("resources/PinkStrip.png");
        ImageIcon imgCircuito = new ImageIcon("resources/luigicircuit.png");

        Vehiculo vehiculo = new Vehiculo(); // Proporciona la ruta correcta
        Circuito circuito = new Circuito(imgCircuito);
        circuito.setVisible(true);
        JButton start = new JButton();
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getSource());
                circuito.setSize(500, 500);
                circuito.agregarVehiculo(vehiculo);

                //    circuito.setBounds(0,0,imgCircuito.getIconWidth(),imgCircuito.getIconHeight());
                add(circuito, BorderLayout.CENTER);
                circuito.requestFocus();

            }
        });
        add(start, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });

    }
}