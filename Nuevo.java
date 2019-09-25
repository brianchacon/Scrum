import javax.swing.*;
import java.awt.event.*;

class Nuevo extends JDialog implements ActionListener{
    String result = null;
    private JLabel label;
    private JTextField nombreTF;

    JButton guardar,cancelar;
    
    public Nuevo(int w,int h){
        setLayout(null);
        setTitle("Nuevo proyecto");
        setModal(true);

        label = new JLabel("Ingrese el nombre del proyecto");
        label.setBounds(50,30,260,20);
        add(label);

        nombreTF  = new JTextField("");
        nombreTF.setBounds(50,70,200,20);
        add(nombreTF);

        guardar =  new JButton("Guardar");
        guardar.setBounds(50,110,100,20);
        guardar.addActionListener(this);
        add(guardar);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(150,110,100,20);
        cancelar.addActionListener(this);
        add(cancelar);  
        
        setBounds(w/2-150,h/2-95 ,300,190);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    }
    String getDatos(){
        return result;
    }
    
    public void actionPerformed(ActionEvent e) {
            if (e.getSource()==guardar) {
            String s = nombreTF.getText();
            result = s;
            this.dispose();     
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        } 
    }
}
