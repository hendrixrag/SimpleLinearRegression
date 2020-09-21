/*
Datos necesarios:
X - Y - Xi - Yi - XiYi - Xi2 - Ym - Ui - Ui2 - X2
Formulario:
maX = sumX/n
maY = sumY/n
Xi = X - maX
Yi = Y - maY
b0 = maY - b1*maX
b1 = sumXiYi/sumXi2
Y = b0 + b1x

ui = Y - Ym
Ym = maY + b1*Xi
t2 = sumUi2/(n-2)
t = rc(t2)
varb0 = (sumX2/(n*sumXi2))* t2
varb1 = t2/sumXi2
eeb0 = rc(sumX2 / (n*sumXi2)) * t
eeb1 = t/rc(sumXi2)

SRC = sumUi2
SEC = (b1^2) * sumXi2
STC = SEC - SRC 
r2 = 1-(SRC/STC)
r = rc(r2)
mcX = rc ((sumX2 - sumXi2)/n)
PM = t2 ((1/n) + (x0 - mcX)^2/sumXi2)
PI = t2 (1 + (1/n) + ((x0 - mcX)^2/sumXi2)
 */
package regresion;
import javax.swing.*;
import java.awt.event.*;
import static java.lang.Math.sqrt;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hendrix
 */

public class Regresion extends JFrame implements ActionListener, ItemListener {
    
    public static Regresion regresion;
    JLabel l1, l2, l3, l4;
    JTextField X0, x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7,x8,y8,x9,y9,x10,y10,x11,y11,x12,y12,x13,y13,x14,y14,x15,y15;
    JButton calcular, clean, exit; 
    JComboBox N;
    int n = 3;
    public static float x0 = 0, b1 = 0, b0 = 0, t2 = 0, t = 0, varb0 = 0, varb1 = 0, eeb0 = 0, eeb1 = 0,
                        SRC = 0, SEC = 0, STC = 0, r2 = 0, r = 0, mcX = 0, PM = 0, PI = 0;
       
    //funciones:
    //para no exceder 5 decimales
    public float formatearDecimales(float numero) {
    return  (float) (Math.round(numero * Math.pow(10, 5)) / Math.pow(10, 5));
    }
    
    //para calcular la sumatoria
    public float sumatoria (float a[], int n){
        float sum = 0;
        for(int i = 0; i < n; i++){
                    sum += a[i];
                }
        return sum;
    }
    
    public Regresion(){
        setTitle("Regresión Lineal");
        setLayout(null);
        
        
        l1 = new JLabel("Cantidad de muestras:" );
        l1.setBounds(30,30, 180, 40);
        add(l1);
        
        l2 = new JLabel("X   |   Y");
        l2.setBounds(270, 30, 70,40);
        add(l2);
        
        l3 = new JLabel("*Utilizar puntos (.) para marcar los decimales.");
        l3.setBounds(30, 520, 280, 40);
        add(l3);
        
        l4 = new JLabel("X0");
        l4.setBounds(348, 30, 35, 40);
        add(l4);
        
        X0 = new JTextField();
        X0.setBounds(342,60,35,30);
        X0.setText("0");
        add(X0);
        
        x1 = new JTextField();
        x1.setBounds(255,60,35,30);
        add(x1);
        y1 = new JTextField();
        y1.setBounds(288,60,35,30);
        add(y1);
        
        x2 = new JTextField();
        x2.setBounds(255,90,35,30);
        add(x2);
        y2 = new JTextField();
        y2.setBounds(288,90,35,30);
        add(y2);
        
        x3 = new JTextField(); 
        x3.setBounds(255,120,35,30);
        add(x3);
        y3 = new JTextField();
        y3.setBounds(288,120,35,30); 
        add(y3);
        
        x4 = new JTextField(); 
        add(x4);
        y4 = new JTextField();
        add(y4);
        
        x5 = new JTextField(); 
        add(x5);
        y5 = new JTextField();
        add(y5);
        
        x6 = new JTextField(); 
        add(x6);
        y6 = new JTextField();
        add(y6);
        
        x7 = new JTextField(); 
        add(x7);
        y7 = new JTextField();
        add(y7);
        
        x8 = new JTextField(); 
        add(x8);
        y8 = new JTextField();
        add(y8);
        
        x9 = new JTextField(); 
        add(x9);
        y9 = new JTextField();
        add(y9);
        
        x10 = new JTextField(); 
        add(x10);
        y10 = new JTextField();
        add(y10);
        
        x11 = new JTextField(); 
        add(x11);
        y11 = new JTextField();
        add(y11);
        
        x12 = new JTextField(); 
        add(x12);
        y12 = new JTextField();
        add(y12);
        
        x13 = new JTextField(); 
        add(x13);
        y13 = new JTextField();
        add(y13);
        
        x14 = new JTextField(); 
        add(x14);
        y14 = new JTextField();
        add(y14);
        
        x15 = new JTextField(); 
        add(x15);
        y15 = new JTextField();
        add(y15); 
        
        N = new JComboBox(); //para pedir cantidad de muestras
        N.setBounds(180,30,50,40);
        for(int i=3;i<=15;i++){
            N.addItem(String.valueOf(i));
        }
        N.addItemListener(this);
        add(N);
              
        clean = new JButton("Limpiar campos");
        clean.setBounds(30,81,200,50);
        clean.addActionListener(this);
        add(clean);
        
        calcular = new JButton("Calcular");
        calcular.setBounds(30,140,200,180);
        calcular.addActionListener(this);
        add(calcular);
        
        exit = new JButton("Salir");
        exit.setBounds(30,330,200,180);
        exit.addActionListener(this);
        add(exit);      
    }
    
    //eventos del ComboBox
    public void itemStateChanged(ItemEvent x){
        if(x.getStateChange() == ItemEvent.SELECTED){
             
             n = Integer.parseInt(N.getSelectedItem().toString());
             //campos por defecto   
             if(n == 3){
                 x3.setVisible(true);
                 y3.setVisible(true);           
                 x4.setVisible(false);
                 y4.setVisible(false);
                 x5.setVisible(false);
                 y5.setVisible(false);
                 x6.setVisible(false);
                 y6.setVisible(false);
                 x7.setVisible(false);
                 y7.setVisible(false);
                 x8.setVisible(false);
                 y8.setVisible(false);
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
             //agregando campos
             if(n==4){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 
                 x5.setVisible(false);
                 y5.setVisible(false);
                 x6.setVisible(false);
                 y6.setVisible(false);
                 x7.setVisible(false);
                 y7.setVisible(false);
                 x8.setVisible(false);
                 y8.setVisible(false);
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 5){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 
                 x6.setVisible(false);
                 y6.setVisible(false);
                 x7.setVisible(false);
                 y7.setVisible(false);
                 x8.setVisible(false);
                 y8.setVisible(false);
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 6){ 
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 
                 x7.setVisible(false);
                 y7.setVisible(false);
                 x8.setVisible(false);
                 y8.setVisible(false);
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 7){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30); 
                 
                 
                 x8.setVisible(false);
                 y8.setVisible(false);
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 8){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);  
                 
                 x9.setVisible(false);
                 y9.setVisible(false);
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 9){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30); 
                 
                 x10.setVisible(false);
                 y10.setVisible(false);
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 10){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30); 
                 
                 x11.setVisible(false);
                 y11.setVisible(false);
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 11){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30);  
                 x11.setVisible(true);
                 x11.setBounds(255,360,35,30);
                 y11.setVisible(true);
                 y11.setBounds(288,360,35,30); 
                 
                 x12.setVisible(false);
                 y12.setVisible(false);
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 12){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30);  
                 x11.setVisible(true);
                 x11.setBounds(255,360,35,30);
                 y11.setVisible(true);
                 y11.setBounds(288,360,35,30); 
                 x12.setVisible(true);
                 x12.setBounds(255,390,35,30);
                 y12.setVisible(true);
                 y12.setBounds(288,390,35,30); 
                 
                 x13.setVisible(false);
                 y13.setVisible(false);
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 13){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30);  
                 x11.setVisible(true);
                 x11.setBounds(255,360,35,30);
                 y11.setVisible(true);
                 y11.setBounds(288,360,35,30); 
                 x12.setVisible(true);
                 x12.setBounds(255,390,35,30);
                 y12.setVisible(true);
                 y12.setBounds(288,390,35,30); 
                 x13.setVisible(true);
                 x13.setBounds(255,420,35,30);
                 y13.setVisible(true);
                 y13.setBounds(288,420,35,30);
                 
                 x14.setVisible(false);
                 y14.setVisible(false);
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 14){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30);  
                 x11.setVisible(true);
                 x11.setBounds(255,360,35,30);
                 y11.setVisible(true);
                 y11.setBounds(288,360,35,30); 
                 x12.setVisible(true);
                 x12.setBounds(255,390,35,30);
                 y12.setVisible(true);
                 y12.setBounds(288,390,35,30); 
                 x13.setVisible(true);
                 x13.setBounds(255,420,35,30);
                 y13.setVisible(true);
                 y13.setBounds(288,420,35,30);  
                 x14.setVisible(true);
                 x14.setBounds(255,450,35,30);
                 y14.setVisible(true);
                 y14.setBounds(288,450,35,30);  
                 
                 x15.setVisible(false);
                 y15.setVisible(false);
             }
                if(n == 15){
                 x3.setVisible(true);
                 x3.setBounds(255,120,35,30);
                 y3.setVisible(true);
                 y3.setBounds(288,120,35,30); 
                 x4.setVisible(true);
                 x4.setBounds(255,150,35,30);
                 y4.setVisible(true);
                 y4.setBounds(288,150,35,30);
                 x5.setVisible(true);
                 x5.setBounds(255,180,35,30);
                 y5.setVisible(true);
                 y5.setBounds(288,180,35,30); 
                 x6.setVisible(true);
                 x6.setBounds(255,210,35,30);
                 y6.setVisible(true);
                 y6.setBounds(288,210,35,30); 
                 x7.setVisible(true);
                 x7.setBounds(255,240,35,30);
                 y7.setVisible(true);
                 y7.setBounds(288,240,35,30);  
                 x8.setVisible(true);
                 x8.setBounds(255,270,35,30);
                 y8.setVisible(true);
                 y8.setBounds(288,270,35,30);   
                 x9.setVisible(true);
                 x9.setBounds(255,300,35,30);
                 y9.setVisible(true);
                 y9.setBounds(288,300,35,30);  
                 x10.setVisible(true);
                 x10.setBounds(255,330,35,30);
                 y10.setVisible(true);
                 y10.setBounds(288,330,35,30);  
                 x11.setVisible(true);
                 x11.setBounds(255,360,35,30);
                 y11.setVisible(true);
                 y11.setBounds(288,360,35,30); 
                 x12.setVisible(true);
                 x12.setBounds(255,390,35,30);
                 y12.setVisible(true);
                 y12.setBounds(288,390,35,30); 
                 x13.setVisible(true);
                 x13.setBounds(255,420,35,30);
                 y13.setVisible(true);
                 y13.setBounds(288,420,35,30);  
                 x14.setVisible(true);
                 x14.setBounds(255,450,35,30);
                 y14.setVisible(true);
                 y14.setBounds(288,450,35,30);   
                 x15.setVisible(true);
                 x15.setBounds(255,480,35,30);
                 y15.setVisible(true);
                 y15.setBounds(288,480,35,30); 
             }
                        
         }
    }
    
    //eventos de los botones
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == calcular){
            try{
            float maX = 0, maY = 0,
                  x[] = new float[n], sumX = 0,
                  y[] = new float[n], sumY = 0,
                  xi[] = new float[n], sumxi = 0,
                  yi[] = new float[n], sumyi = 0,
                  xiyi[] = new float[n], sumxiyi = 0,
                  xi2[] = new float[n], sumxi2 = 0,
                  X2[] = new float[n], sumx2 = 0,
                  ym[] = new float[n], sumym = 0,
                  ui[] = new float[n], sumui = 0,
                  ui2[] = new float[n], sumui2 = 0;
                  x0 = Float.parseFloat(X0.getText());
              
            if(n == 2){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());

            }
            if(n == 3){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                
               
            }
            if(n == 4){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
              
            }
            if(n == 5){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                
                
            }
            if(n == 6){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                
               
            }
            if(n == 7){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                
                
            }
            if(n == 8){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                
                
            }
            if(n == 9){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                
              
            }
            if(n == 10){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                
           
            }
            if(n == 11){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                x[10] = Float.parseFloat(x11.getText());
                y[10] = Float.parseFloat(y11.getText());
          
            }
            if(n == 12){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                x[10] = Float.parseFloat(x11.getText());
                y[10] = Float.parseFloat(y11.getText());
                x[11] = Float.parseFloat(x12.getText());
                y[11] = Float.parseFloat(y12.getText());
                
           
            }
            if(n == 13){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                x[10] = Float.parseFloat(x11.getText());
                y[10] = Float.parseFloat(y11.getText());
                x[11] = Float.parseFloat(x12.getText());
                y[11] = Float.parseFloat(y12.getText());
                x[12] = Float.parseFloat(x13.getText());
                y[12] = Float.parseFloat(y13.getText());
                
             
            }
            if(n == 14){
               x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                x[10] = Float.parseFloat(x11.getText());
                y[10] = Float.parseFloat(y11.getText());
                x[11] = Float.parseFloat(x12.getText());
                y[11] = Float.parseFloat(y12.getText());
                x[12] = Float.parseFloat(x13.getText());
                y[12] = Float.parseFloat(y13.getText());
                x[13] = Float.parseFloat(x14.getText());
                y[13] = Float.parseFloat(y14.getText());
                
              
            }
            if(n == 15){
                x[0] = Float.parseFloat(x1.getText());
                y[0] = Float.parseFloat(y1.getText());
                x[1] = Float.parseFloat(x2.getText());
                y[1] = Float.parseFloat(y2.getText());
                x[2] = Float.parseFloat(x3.getText());
                y[2] = Float.parseFloat(y3.getText());
                x[3] = Float.parseFloat(x4.getText());
                y[3] = Float.parseFloat(y4.getText());
                x[4] = Float.parseFloat(x5.getText());
                y[4] = Float.parseFloat(y5.getText());
                x[5] = Float.parseFloat(x6.getText());
                y[5] = Float.parseFloat(y6.getText());
                x[6] = Float.parseFloat(x7.getText());
                y[6] = Float.parseFloat(y7.getText());
                x[7] = Float.parseFloat(x8.getText());
                y[7] = Float.parseFloat(y8.getText());
                x[8] = Float.parseFloat(x9.getText());
                y[8] = Float.parseFloat(y9.getText());
                x[9] = Float.parseFloat(x10.getText());
                y[9] = Float.parseFloat(y10.getText());
                x[10] = Float.parseFloat(x11.getText());
                y[10] = Float.parseFloat(y11.getText());
                x[11] = Float.parseFloat(x12.getText());
                y[11] = Float.parseFloat(y12.getText());
                x[12] = Float.parseFloat(x13.getText());
                y[12] = Float.parseFloat(y13.getText());
                x[13] = Float.parseFloat(x14.getText());
                y[13] = Float.parseFloat(y14.getText());
                x[14] = Float.parseFloat(x15.getText());
                y[14] = Float.parseFloat(y15.getText());
                

            }
            
                            
                
               sumX = sumatoria(x, n);
               sumY = sumatoria(y,n);
                
               maX = sumX/n;
               maY = sumY/n;

            //calculo de Xi y Yi
             for(int i = 0; i < n; i++){
                    xi[i] = (x[i] - maX);
                    yi[i] = (y[i] - maY);
             }
             
             
          
             
             //calculo de XiYi
              for(int i = 0;i < n; i++){
                   xiyi[i] = xi[i] * yi[i]; 
                }
              
              //calculo de Xi²
              for(int i = 0; i < n; i++ ){
                  xi2[i] = xi[i]*xi[i];
              }
              
              //sumatoria de Xiyi y sumatoria de Xi²
             
              sumxiyi = sumatoria(xiyi,n);
              sumxi2 = sumatoria(xi2,n);

            
              
              //cálculo de b1 y b0
              b1 = formatearDecimales(sumxiyi/sumxi2);
              b0 = formatearDecimales(maY - b1*maX);
              
            
              
              
           
              //calculo de X²
               for(int i = 0;i < n; i++){
                   X2[i] = x[i]*x[i];
                }
               
               
               //calculo de Y muestral (Ŷ)
               for(int i = 0; i < n; i++){
                   ym[i] = maY + b1*xi[i];
               }
               
               //calculo de Ui
               for(int i = 0; i < n; i++){
                   ui[i] = y[i] - ym[i];
               }
               
               //calculo de Ui²
               for(int i = 0; i < n; i++){
                   ui2[i] = ui[i]*ui[i];
               }
             
               //sumatorias para la tabla
               sumxi = sumatoria(xi,n);
               sumyi = sumatoria(yi,n);
               sumx2 = sumatoria(X2,n);
               sumym = sumatoria(ym,n);
               sumui = sumatoria(ui,n);
               sumui2 = sumatoria(ui2,n);
               
               //calculo de t²
               t2 = sumui2/(n-2);
               
               //calculo de t
               t = (float)sqrt(Double.parseDouble(Float.toString(t2)));
               
               //calculo de la varianza de B0
               varb0 = (sumx2/(n*sumxi2))*t2;
               
               //calculo de la varianza de B1
               varb1 = t2/sumxi2;
               
               //calculo de Error Estandar de B0
               eeb0 = (float) sqrt(Double.parseDouble(Float.toString(sumx2/(n*sumxi2))))*t ;
               
               //calculo de Error Estandar de B1
               eeb1 = t/((float)sqrt(Double.parseDouble(Float.toString(sumxi2))));
               
              
               //calculo de la Suma de los residuos al cuadrado
               SRC = sumui2;
               
               //calculo de la Suma Explicada de Cuadrado
               SEC = (b1*b1) * sumxi2;
               
               //calculo de la Suma total de Cuadrado
               STC = SEC + SRC;
               
               //calculo de Coeficiente de Determinacion
               r2 = 1-(SRC/STC);
               
               //calculo de Coeficiente de correlacion
               r = (float)sqrt(Double.parseDouble(Float.toString(r2)));
               
               //calculo de la media cuadrática
               mcX = (float)sqrt(Double.parseDouble(Float.toString((sumx2 - sumxi2)/n)));
               
               //calculo de la Predicción Media
               PM = t2*((1/n) + (float) Math.pow((x0 - mcX),2)/sumxi2);
               
               //calculo de la Predicción indiivual
               PI = t2*(1 + (1/n) + (float) Math.pow((x0 - mcX),2)/sumxi2);
               
             //datos para rellenar la tabla
             String headers[] = {"X", "Y", "Xi", "Yi", "XiYi", "X²", "Xi²", "Ŷ", "Ui", "Ui²"};
             Object [][] data = new Object[n+1][10];
             for(int i = 0; i < n; i++){
                   data[i][0] = formatearDecimales(x[i]);
                   data[i][1] = formatearDecimales(y[i]);
                   data[i][2] = formatearDecimales(xi[i]);
                   data[i][3] = formatearDecimales(yi[i]);
                   data[i][4] = formatearDecimales(xiyi[i]);
                   data[i][5] = formatearDecimales(X2[i]);
                   data[i][6] = formatearDecimales(xi2[i]);
                   data[i][7] = formatearDecimales(ym[i]);
                   data[i][8] = formatearDecimales(ui[i]);
                   data[i][9] = formatearDecimales(ui2[i]); 
                }
                
             //sumatorias 
                   data[n][0] = "Σ=" + formatearDecimales(sumX);
                   data[n][1] = "Σ=" + formatearDecimales(sumY);
                   data[n][2] = "Σ=" + sumxi;
                   data[n][3] = "Σ=" + sumyi;
                   data[n][4] = "Σ=" + formatearDecimales(sumxiyi);
                   data[n][5] = "Σ=" + formatearDecimales(sumx2);
                   data[n][6] = "Σ=" + formatearDecimales(sumxi2);
                   data[n][7] = "Σ=" + formatearDecimales(sumym);
                   data[n][8] = "Σ=" + formatearDecimales(sumui);
                   data[n][9] = "Σ=" + formatearDecimales(sumui2); 

             
             //creación de la tabla
             DefaultTableModel datos = new DefaultTableModel(data,headers);             
             cuadro tabla = new cuadro(datos);
             tabla.setVisible(true);
             tabla.setLocationRelativeTo(null);
             tabla.setResizable(false);
             this.setVisible(false);
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Debes llenar todos los campos!");
            }
            
        }
        
        //limpiar campos
        if(e.getSource() == clean){
            x1.setText(" ");
            y1.setText(" ");
            x2.setText(" ");
            y2.setText(" ");
            x3.setText(" ");
            y3.setText(" ");
            x4.setText(" ");
            y4.setText(" ");
            x5.setText(" ");
            y5.setText(" ");
            x6.setText(" ");
            y6.setText(" ");
            x7.setText(" ");
            y7.setText(" ");
            x8.setText(" ");
            y8.setText(" ");
            x9.setText(" ");
            y9.setText(" ");
            x10.setText(" ");
            y10.setText(" ");
            x11.setText(" ");
            y11.setText(" ");
            x12.setText(" ");
            y12.setText(" ");
            x13.setText(" ");
            y13.setText(" ");
            x14.setText(" ");
            y14.setText(" ");
            x15.setText(" ");
            y15.setText(" ");
        }
        if(e.getSource() == exit){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cuadro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cuadro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cuadro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cuadro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        regresion = new Regresion();
        regresion.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        regresion.setBounds(0, 0, 400, 600);
        regresion.setVisible(true);
        regresion.setLocationRelativeTo(null);
        regresion.setResizable(false);
    }   
}
