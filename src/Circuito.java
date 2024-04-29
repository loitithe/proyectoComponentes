import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Circuito extends JPanel implements KeyListener {
    private List<Vehiculo> vehiculos;
    private int puntuacion;
    private ImageIcon imageIcon;

    public Circuito(ImageIcon imgCircuito) {
        //
        this.imageIcon = imgCircuito;
        this.vehiculos = new ArrayList<>();
        this.puntuacion = 0;
        this.setSize(800, 400);
        this.setBackground(Color.green);
        addKeyListener(this);
        setFocusable(true);
    }

    // resolver uso
    public void agregarVehiculo(Vehiculo vehiculo) {
        this.add(vehiculo);

    }

    public void removerVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }

    public void moverVehiculos() {
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mover();
        }
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
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
        for (Vehiculo vehiculo : vehiculos) {
            switch (keyCode) {
                case KeyEvent.VK_A:
                    vehiculo.rotarVehiculo(-Math.PI / 16); // Rotar a la izquierda
                    break;
                case KeyEvent.VK_D:
                    vehiculo.rotarVehiculo(Math.PI / 16); // Rotar a la derecha
                    break;
                case KeyEvent.VK_W:
                    vehiculo.acelerarVehiculo();
                    break;
                case KeyEvent.VK_S:
                    vehiculo.frenarVehiculo(); // Frenar
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension height = getSize();
        //    g.drawImage(imageIcon.getImage(),0,0,height.width,height.height,null);
//        for (Vehiculo vehiculo : vehiculos) {
//
//            g.drawImage(vehiculo.getImg().getImage(), 0, 0, height.width, height.height, null);
//        }
        //   setOpaque(false);
        super.paintComponent(g);
    }
}



