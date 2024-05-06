import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        this.setLayout(new BorderLayout());

        // Carga de las imágenes
        ImageIcon blackOut = new ImageIcon("src/resources/BlackOut.png");
        ImageIcon blueStrip = new ImageIcon("resources/BlueStrip.png");
        ImageIcon greenStrip = new ImageIcon("resources/GreenStrip.png");
        ImageIcon pinkStrip = new ImageIcon("resources/PinkStrip.png");
        ImageIcon imgCircuito = new ImageIcon("src/resources/luigicircuit.png");

        // Creación del vehículo y el circuito
        Vehiculo vehiculo = new Vehiculo(blackOut); // Proporciona la ruta correcta
        Circuito circuito = new Circuito(imgCircuito);

        // Agregar el vehículo al circuito
        circuito.agregarVehiculo(vehiculo);

        // Crear y configurar el botón de inicio
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getSource());
                add(circuito, BorderLayout.CENTER);
                circuito.setVisible(true);
                circuito.requestFocus();

                repaint();
            }
        });
        // Agregar el botón de inicio al panel sur
        add(start, BorderLayout.SOUTH);
        // Configuración del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // Imprimir el estado de carga de la imagen
        System.out.println("Estado de carga de la imagen: " + imgCircuito.getImageLoadStatus());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}


