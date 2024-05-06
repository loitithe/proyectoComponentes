import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Vehiculo extends JPanel implements Serializable {
    private int radio = 10;
    private int x; // Remover la inicialización aquí
    private int y; // Remover la inicialización aquí
    private int dx = 1;
    private int dy = 1;
    private int anchoApplet;
    private int altoApplet;
    private ImageIcon imageIcon;
    private static final int VELOCIDAD = 3; // Velocidad de movimiento del vehículo

    public Vehiculo(ImageIcon imageicon) {
        this.imageIcon=imageicon;
    }
    public void init(int ancho, int alto) {
        this.anchoApplet = ancho;
        this.altoApplet = alto;
        this.x = anchoApplet / 4;
        this.y = altoApplet / 2;
    }

    public void mover(int dx, int dy) {
        // Actualizar las direcciones
        this.dx = dx;
        this.dy = dy;

        // Calcular las nuevas coordenadas
        int nuevaX = this.x + this.dx * VELOCIDAD; // velocidad es una constante que define la velocidad de movimiento
        int nuevaY = this.y + this.dy * VELOCIDAD;

        // Verificar que el vehículo no se salga de los límites del panel
        if (nuevaX >= radio && nuevaX <= anchoApplet - radio) {
            this.x = nuevaX;
        }
        if (nuevaY >= radio && nuevaY <= altoApplet - radio) {
            this.y = nuevaY;
        }

        // Solicitar un repintado del componente
        repaint();
    }



    public int getDX() {
        return this.dx;
    }

    public int getDY() {
        return this.dy;
    }



    public void moverIzquierda() {
        System.out.println("Rotación antes: (" + this.x + ", " + this.y + ")");

        this.dx -=4;
        System.out.println("Rotación después: (" + this.x + ", " + this.y + ")");

    }

    public void moverDerecha() {
        System.out.println("Rotación antes: (" + this.x + ", " + this.y + ")");

        this.dx +=4;
        System.out.println("Rotación después: (" + this.x + ", " + this.y + ")");

    }

    public void acelerarVehiculo() {
        System.out.println("Aceleración antes: (" + this.x + ", " + this.y + ")");
        this.y-=4;
        System.out.println("Aceleración después: (" + this.x + ", " + this.y + ")");

    }

    public void frenarVehiculo() {
        System.out.println("Frenado antes: (" + this.x + ", " + this.y + ")");
        this.y+=4;
        System.out.println("Frenado después: (" + this.x + ", " + this.y + ")");

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadio() {
        return radio;
    }

    public Ellipse2D getBoundsVehiculo(){
        return new Ellipse2D.Double(x+10,y+30,80,50);
    }

    public boolean llegaFinal(){
        Rectangle cuadrado = new Rectangle(520,520,110,110);
        Area cuadradoArea= new Area(cuadrado);
        return cuadradoArea.contains(getBoundsVehiculo().getBounds());
    }

//    @Override
//    public void paint(Graphics g) {
//        g.drawImage(imageIcon.getImage(),x,y,100,100,null);
//    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Repintando componente...");
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(), x, y, 100, 100, null);
    }

}
