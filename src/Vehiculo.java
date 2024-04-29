import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.Serializable;

public class Vehiculo extends JPanel implements Serializable {

    Thread anima;
    int radio=10;     //radio de la pelota
    int x, y;       //posición del centro de la pelota
    int dx = 1;     //desplazamientos
    int dy = 1;
    int anchoApplet;
    int altoApplet;

    public void init () {
      //  setBackground(Color.white);
//dimensiones del applet
        anchoApplet=getSize().width;
        altoApplet=getSize().height;
//posición inicial de partida
        x=anchoApplet/4;
        y=altoApplet/2;
    }

    public void start(){
        if(anima ==null){
       //     anima=new Thread(this);
      //      anima.start();
        }
    }

    public void stop(){
        if(anima!=null){
            anima.stop();
            anima=null;
        }
    }

    public void run() {
        while (true) {
            mover();
        }
    }
    void mover(){
        x += dx;
        y += dy;
        if (x >= (anchoApplet-radio) || x < radio) dx*= -1;
        if (y >= (altoApplet-radio) || y < radio) dy*= -1;
        repaint();   //llama a paint
    }


//    public void paint (Graphics g) {
//        g.setColor(Color.red);
//        g.fillOval(x-radio, y-radio, 2*radio, 2*radio);
//    }
}