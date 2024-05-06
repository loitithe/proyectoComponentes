import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Circuito extends JPanel implements KeyListener {
    private Vehiculo vehiculo;
    private int puntuacion;
    private ImageIcon imageIcon;
    private int dx, dy; // Variables para almacenar la dirección del vehículo
    private Timer timer;
    private BufferedImage bfimage;
    private int circuitoWidth = 1200;
    private int circuitoHeight = 800;
    private int ventanaWidth = 1200;
    private int ventanaHeight = 1200;

    public Circuito(ImageIcon imgCircuito) {
        this.imageIcon = imgCircuito;
        this.puntuacion = 0;
        this.setSize(ventanaWidth, ventanaHeight);
        addKeyListener(this);
        setFocusable(true);
        try {
            this.bfimage = ImageIO.read(new File("src/resources/luigicircuit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dx = 0; // Inicialmente, el vehículo no se está moviendo
        dy = 0;
        timer = new Timer(1000 / 60, new ActionListener() { // 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                moverVehiculo();
            }
        });
    }
//    }
    public void moverVehiculo() {
        this.vehiculo.mover(dx, dy); // Mover el vehículo según las direcciones actuales
        int ventanaX = Math.max(0, Math.min(vehiculo.getX() - ventanaWidth / 2, circuitoWidth - ventanaWidth));
        int ventanaY = Math.max(0, Math.min(vehiculo.getY() - ventanaHeight / 2, circuitoHeight - ventanaHeight));
        this.scrollRectToVisible(new Rectangle(ventanaX, ventanaY, ventanaWidth, ventanaHeight));

        repaint();
    }

    // resolver uso
    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo; // Asignar el vehículo
        vehiculo.init(ventanaWidth, ventanaHeight); // Inicializar el vehículo con el tamaño del panel
    }


    public void aumentarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(e.getKeyChar());

        System.out.println("Posición antes: (" + this.vehiculo.getX() + ", " + this.vehiculo.getY() + ")");
        switch (keyCode) {
            case KeyEvent.VK_A:
                dx = -1;
                this.vehiculo.moverIzquierda(); // Rotar a la izquierda
                break;
            case KeyEvent.VK_D:
                dx = 1;
                this.vehiculo.moverDerecha(); // Rotar a la derecha
                break;
            case KeyEvent.VK_W:
                this.vehiculo.acelerarVehiculo();
                break;
            case KeyEvent.VK_S:
                this.vehiculo.frenarVehiculo(); // Frenar
                break;
        }
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                dx = 0; // Detener el movimiento horizontal cuando se suelta la tecla
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                dy = 0; // Detener el movimiento vertical cuando se suelta la tecla
                break;
        }

        // Detener el temporizador si no hay teclas presionadas
        if (dx == 0 && dy == 0) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Calcula la posición de visualización del circuito en función de la posición del vehículo
        int offsetX = getWidth() / 2 - vehiculo.getX();
        int offsetY = getHeight() / 2 - vehiculo.getY();
        offsetX = Math.min(0, Math.max(getWidth() - bfimage.getWidth(), offsetX));
        offsetY = Math.min(0, Math.max(getHeight() - bfimage.getHeight(), offsetY));

        // Dibuja el circuito en la posición calculada
        g2d.drawImage(bfimage, offsetX, offsetY, this);

        // Dibuja el vehículo
        vehiculo.paintComponent(g2d);

        g2d.dispose();
    }

}



