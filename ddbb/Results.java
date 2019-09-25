import javax.swing.*;
import java.awt.event.*;
class Results extends JFrame {
    int width = 800;//1600;
    int height = 600;//850;
    int marginLeft = 25;
    private JLabel tiLb;
        
    public Results(String tabla){
        setLayout(null);
        setTitle("Valores adquiridos");
        
        if(tabla != null)
            tiLb = new JLabel(tabla);
        else
            tiLb = new JLabel("PIFFIEEE#001");    
        tiLb.setBounds(marginLeft,30,170,20);
        add(tiLb);
        
        setBounds(150,150,width,height);
        setVisible(true);
        setResizable(false); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
