import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Vehiculo extends JPanel implements Serializable {
    private int radio = 10;
    private int x; // Remover la inicialización aquí
    private int y; // Remover la inicialización aquí
    private int dx = 0;
    private int dy = 0;
    private int anchoApplet;
    private int altoApplet;
    private ImageIcon imageIcon;
    private static final int VELOCIDAD = 3; // Velocidad de movimiento del vehículo
    private BufferedImage bfimage;
    private BufferedImage bfimageLeft;
    private BufferedImage bfimageRight;
    private BufferedImage bfimageUp;
    private BufferedImage bfimageDown;

    public Vehiculo(ImageIcon imageicon) {
        this.imageIcon = imageicon;
        this.bfimage = toBufferedImage(imageIcon.getImage());
        this.bfimageLeft = rotateImage(bfimage, Math.PI); // Girar 180 grados a la izquierda
        this.bfimageRight = bfimage; // Imagen original mirando hacia la derecha
        this.bfimageUp = rotateImage(bfimage, -Math.PI / 2); // Girar 90 grados hacia arriba
        this.bfimageDown = rotateImage(bfimage, Math.PI / 2); // Girar 90 grados hacia abajo
        this.x=254;
        this.y=800;

    }

    private BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }

    private BufferedImage rotateImage(BufferedImage originalImage, double radians) {
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(radians, width / 2, height / 2);
        g2d.drawRenderedImage(originalImage, null);
        g2d.dispose();

        return rotatedImage;
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
        repaint();
    }

    public int getDX() {
        return this.dx;
    }

    public int getDY() {
        return this.dy;
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

    public void moverIzquierda() {
        System.out.println("Rotación antes: (" + this.x + ", " + this.y + ")");
        this.bfimage = bfimageLeft;
        this.dx -= 4;
        System.out.println("Rotación después: (" + this.x + ", " + this.y + ")");
        repaint();
    }

    public void moverDerecha() {
        System.out.println("Rotación antes: (" + this.x + ", " + this.y + ")");
        this.bfimage = bfimageRight;
        this.dx += 4;
        System.out.println("Rotación después: (" + this.x + ", " + this.y + ")");
        repaint();
    }

    public void acelerarVehiculo() {
        System.out.println("Aceleración antes: (" + this.x + ", " + this.y + ")");
        this.bfimage= bfimageUp;
        this.y -= 4;
        System.out.println("Aceleración después: (" + this.x + ", " + this.y + ")");
        repaint();
    }

    public void frenarVehiculo() {
        System.out.println("Frenado antes: (" + this.x + ", " + this.y + ")");
        this.y += 4;
        System.out.println("Frenado después: (" + this.x + ", " + this.y + ")");
        this.bfimage= bfimageDown;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bfimage, x, y, 32, 32, null);

    }


    public Ellipse2D getBoundsVehiculo() {
        return new Ellipse2D.Double(x + 10, y + 30, 80, 50);
    }

    public boolean llegaFinal() {
        Rectangle cuadrado = new Rectangle(520, 520, 110, 110);
        Area cuadradoArea = new Area(cuadrado);
        return cuadradoArea.contains(getBoundsVehiculo().getBounds());
    }

}
