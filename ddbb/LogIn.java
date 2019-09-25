import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class LogIn extends JDialog implements ActionListener, ChangeListener{
    int width = 800;//1600;
    int height = 600;//850;
    int marginLeft = 25;
    private JLabel titLb, namLb, passLb;
    private JTextField nameJTF;  
    private JPasswordField passJTF;
    JButton Login,cancelar;
    String name, pass, result, msgError;
    boolean validated = false;
    
    public LogIn(){
     msgError = ""; 
        setLayout(null);
        setTitle("Welcome");
        setModal(true);    
     
        titLb = new JLabel("Sign In");
        titLb.setBounds(marginLeft,10,170,20);
        add(titLb);
        namLb = new JLabel("User:");
        namLb.setBounds(marginLeft,30,170,20);
        add(namLb);
        passLb = new JLabel("Password:");
        passLb.setBounds(marginLeft,50,170,20);
        add(passLb);
        //msgError = new JLabel("");
        //msgError.setBounds(marginLeft,70,170,20);
        //add(msgError);
        
        nameJTF   = new JTextField("");
        nameJTF.setBounds(170-marginLeft,30,90,20);
        add(nameJTF);
        passJTF   = new JPasswordField(10);
        passJTF.setBounds(170-marginLeft,50,90,20);
        //passJTF.setActionCommand(OK);
        //passJTF.addChangeListener(this);
        passJTF.addActionListener(this);
        add(passJTF);
        
        Login =  new JButton("Login");
        Login.setBounds(marginLeft,200,100,20);
        Login.addActionListener(this);
        add(Login);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(marginLeft+100,200,100,20);
        cancelar.addActionListener(this);
        add(cancelar);       
        
        setBounds(width/2-150,height/2-150,width/3,height/2-40);
        setVisible(true);
        setResizable(false); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }
    
 //@Override
 @Override
    public void stateChanged(ChangeEvent e) {
        msgError = "";  
        repaint();
    }
    public void paint( Graphics g ) {
        //super.paintComponent(g);
        g.setColor(new Color(155,0,0));
        g.drawString(msgError, marginLeft,90);
    }
    String send(String name){
        return "pepe";
    }
    String get_tables(String name){
        return "[3][2][1]\n[2][1][1]";
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Login) {
            name = nameJTF.getText();
            pass = new String(passJTF.getPassword());
            //result = new box (1,ti , tf , d , a , c);
            String answer= send(name);
            if(pass.equals(answer)){
                result = get_tables(name);
                validated = true;
                this.dispose();
            }
            else{
                msgError = "Password Incorrecta";  
                repaint();
            }           
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        }    
  }
}
