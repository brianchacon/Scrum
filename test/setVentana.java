import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
class setVentana extends JFrame implements ActionListener{
  JSpinner spinner,spinner2;
  JLabel l,l2;
  JButton guardar,cancelar;
  public String ancho,alto;
  public setVentana(){}
  int flag = 0;
  public String abrir(){
    setLayout(null);
    l = new JLabel("Ancho");
    l.setBounds(110,30,60,20);
    add(l);
    l2 = new JLabel("Alto");
    l2.setBounds(110,50,60,20);
    add(l2);
    
    spinner = new JSpinner();
    spinner.setValue(100);
    spinner.setBounds(170,30,50,20);
    add(spinner);
    spinner2 = new JSpinner();
    spinner2.setValue(100);
    spinner2.setBounds(170,50,50,20);
    add(spinner2);
    
    guardar =  new JButton("Guardar");
    guardar.setBounds(50,150,100,20);
    guardar.addActionListener(this);
    add(guardar);
    cancelar = new JButton("Cancelar");
    cancelar.setBounds(150,150,100,20);
    cancelar.addActionListener(this);
    add(cancelar);
    
    setBounds(10,10,300,200);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //while(flag !=1){
    //}
    return ancho;
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==guardar) {
      ancho = new String(spinner.getValue().toString());
      alto = new String(spinner2.getValue().toString());
      flag =1;
      this.dispose();
    }
    if (e.getSource()==cancelar) {
      this.dispose();
    }    
  }
    
  public String valAncho(){  
    return ancho; 
  }
  public String valAlto(){  
    return alto; 
  }  
}
