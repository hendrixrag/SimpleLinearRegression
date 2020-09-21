
package regresion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import static regresion.Regresion.*;
/**
 *
 * @author Hendrix
 */
public class grafica extends JDialog implements ActionListener, ItemListener {
   public JButton graficar;
   JToggleButton cambio;
    public grafica(JFrame frame, String nombre){ //crear frame para el plano
        graficar = new JButton("Graficar");
        graficar.addActionListener(this);
        cambio = new JToggleButton("Modo Oscuro(off)");
        cambio.setBounds(10,10,140,40);
        cambio.addItemListener(this);
        add(cambio);
    }
     
    public void actionPerformed(ActionEvent e) {  
       //colocar grafica en el plano
        if(e.getSource() == graficar){
            cuadro.plano.paintLinealF(cuadro.plano.getGraphics(), b0, b1);
        }     
    }
    
    public void itemStateChanged(ItemEvent d){
     //colocar modo oscuro
        if(cambio.isSelected()){
            cuadro.plano.setBackground(Color.black);
            cambio.setText("Modo Oscuro (ON)");
        }else{
                cuadro.plano.setBackground(Color.white);
                cambio.setText("Modo Oscuro (OFF)");
            } 
    }
}


