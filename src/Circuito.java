import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeSupport;
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

    public Circuito(ImageIcon imgCircuito) {
        this.imageIcon = imgCircuito;
        this.puntuacion = 0;
        this.setSize(600, 1200);
        addKeyListener(this);
        setFocusable(true);
        dx = 0; // Inicialmente, el vehículo no se está moviendo
        dy = 0;
        timer = new Timer(1000 / 60, new ActionListener() { // 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                moverVehiculo();
            }
        });
    }
//    public void iniciarTimer() {
//        Timer timer = new Timer(1000 / 60, new ActionListener() { // 60 FPS
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                moverVehiculo();
//                repaint();
//            }
//        });
//        timer.start();
//    }
public void moverVehiculo() {
    this.vehiculo.mover(dx, dy); // Mover el vehículo según las direcciones actuales
    repaint();
}

    // resolver uso
    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo; // Asignar el vehículo
        vehiculo.init(getWidth(), getHeight()); // Inicializar el vehículo con el tamaño del panel
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
                dx=-1;
                this.vehiculo.moverIzquierda(); // Rotar a la izquierda
                break;
            case KeyEvent.VK_D:
                dx=1;
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
        // Dibujar el circuito
        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
        // Dibujar el vehículo en las coordenadas actuales
        //g.setColor(Color.RED);
        g.fillOval(this.vehiculo.getX(), this.vehiculo.getY(), this.vehiculo.getRadio() * 2, this.vehiculo.getRadio() * 2);
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),this);
//        vehiculo.paint(g);
//
//        g.dispose();
//    }
}



