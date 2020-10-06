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
    JTextField X0;
    JTextField[] cuadritosX = new JTextField[15];
    JTextField[] cuadritosY = new JTextField[15];
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
        
        int local = 60;
        for(int i = 0; i < 15; i++){
            cuadritosX[i] = new JTextField();
            cuadritosX[i].setBounds(255,local,35,30);
            cuadritosY[i] = new JTextField();
            cuadritosY[i].setBounds(288,local,35,30);
            local += 30;
            add(cuadritosX[i]);
            add(cuadritosY[i]);
            if(i >= n){
                cuadritosX[i].setVisible(false);
                cuadritosY[i].setVisible(false);
            }
        }

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
             //agregando y quitando campos de texto  
              for(int i = 0; i < n; i++){
                     cuadritosX[i].setVisible(true);
                     cuadritosY[i].setVisible(true);
                 }
                 for(int i = n; i < 15; i++){
                     cuadritosX[i].setVisible(false);
                     cuadritosY[i].setVisible(false);
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
                  
            for(int i = 0; i < n; i++){
                x[i] = Float.parseFloat(cuadritosX[i].getText());
                y[i] = Float.parseFloat(cuadritosY[i].getText());
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
            for(int i = 0; i < 15; i++){
                cuadritosX[i].setText("");
                cuadritosY[i].setText("");
            }
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
