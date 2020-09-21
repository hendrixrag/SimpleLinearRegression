
package regresion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Hendrix
 */
public class plano extends JPanel {
    
    public plano(){
        iniciar();
    }
    
    //colocar fondo y bordes
    private void iniciar(){
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
    }
    
   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics)g;
        g.setColor(Color.BLUE);
        g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2); //dibujar eje X
        g.drawLine(this.getWidth()/2,0 , this.getWidth()/2, this.getHeight()); //dibujar eje Y
        Font font = new Font("SansSerif", Font.PLAIN, 12);
        if(this.getBackground() == Color.white){
        g2.setColor(Color.black);
        }else{
            g2.setColor(Color.white);
        }
        g2.setFont(font);
        
        //mostrar numeros en el eje X
        for(int i = 0; i < 150; i+=10){
          g2.drawString(String.valueOf(i/10), ((int)coordX(i)-3), (this.getHeight()/2)+10);
        }
        
        //mostrar numeros en el eje Y
        if(regresion.Regresion.b0 <= 2){
        for(int i = -150; i < 150; i+=10){
          g2.drawString(String.valueOf(i/10),(this.getWidth()/2)-13, ((int)coordY(i))+3);
        }
        }else{
          for(int i = -150; i < 150; i+=10){
          g2.drawString(String.valueOf(i),(this.getWidth()/2)-13, ((int)coordY(i))+3);
          }
        }
    }
    
    public void paintLinealF(Graphics g, float b0, float b1){
        float Y;
        
       //lineas hacia el punto
       for (int x = 0; x < 150; x+=10){
           g.setColor(Color.LIGHT_GRAY);
           Y = b0+(b1*x);
           g.drawLine((int) coordX(x), this.getHeight()/2, (int) coordX(x), (int) coordY(Y));
           g.drawLine(this.getWidth()/2,(int)coordY(Y) ,(int) coordX(x), (int) coordY(Y));
       }
       
       //puntos de la coordenada
       for (int x = 0; x < 150; x+=10){
           g.setColor(Color.blue);
           Y = b0+(b1*x);
           g.drawOval((int) coordX(x)-2, (int) coordY(Y)-2, 4, 4);
       }
       
       //recta de la ecuación del modelo
       Graphics2D g2d = (Graphics2D)g;
       g2d.setStroke(new BasicStroke(2));
       g2d.setColor(Color.red);
       for (int x = 0; x < 150; x++){
       Y = b0+(b1*x);
       System.out.println(x+" "+Y);
       g2d.drawLine((int) coordX(x),(int) coordY(Y), (int) coordX(x), (int) coordY(Y));
       }
    }
    
    //para encontrar el valor real en el plano
    private float coordX(float x){
        float real_x = x*2+this.getWidth()/2;
        return real_x;
    }
    private float coordY(float y){
        float real_y = -y*2+this.getHeight()/2;
        return (real_y);
    }
}
