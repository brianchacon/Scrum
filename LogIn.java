import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class LogIn extends JDialog implements ActionListener, ChangeListener{
    int width = 1600;//1600;
    int height = 600;//850;
    int w = /*width*/ 800/2-150;
    int marginLeft = 25;
    private JLabel titLb, namLb, passLb;
    private JTextField nameJTF;  
    private JPasswordField passJTF;
    JButton Login,cancelar;
    String name, pass, result, msgError;
    boolean validated = false;
    read r = new read();
    boolean offline = true;
    public LogIn(){
        msgError = ""; 
        setLayout(null);
        setTitle("Welcome hoa");
        setModal(true);    
     
        titLb = new JLabel("Sign In");
        titLb.setBounds(marginLeft+20,10,170,20);
        add(titLb);
        namLb = new JLabel("User:");
        namLb.setBounds(marginLeft,40,170,20);
        add(namLb);
        passLb = new JLabel("Password:");
        passLb.setBounds(marginLeft,80,170,20);
        add(passLb);
        //msgError = new JLabel("");
        //msgError.setBounds(marginLeft,70,170,20);
        //add(msgError);
        
        nameJTF   = new JTextField("");
        nameJTF.setBounds(marginLeft,60,w-marginLeft*2,20);
        add(nameJTF);
        passJTF   = new JPasswordField(10);
        passJTF.setBounds(marginLeft,100,w-marginLeft*2,20);
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
        
        setBounds(width/2-w/2,height/2-150,w,w);
        setVisible(true);
        setResizable(false); 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); 
       // repaint(); 
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
    String send(String n,String p){
        
        return r.login(n,p);
    }
    
    String check(String n,String p){//TODO
        
        return "goOn";
    } 
    
    String get_tables(String tableName,boolean offlineGet){
        if(!offlineGet)
            return r.getTable(tableName);
        //return "[3][2][1]\n[2][1][1]";//TODO cRud pedimos tablas
        else{//TODO
            return "goOn";
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Login) {
            name = nameJTF.getText();
            pass = new String(passJTF.getPassword());
            //result = new box (1,ti , tf , d , a , c);
            name = name;//"'"+name+"'";
            pass = pass;//"'"+pass+"'";
            System.out.println("name :"+name+" pass:"+pass);
            String answer;
            if(!offline){
                answer = send(name,pass);
            }
            else{
                answer = check(name,pass);    
            }
            if(answer != null && !(answer.equals(""))){
                System.out.println("Tabla name \""+answer+"\"");
                result = get_tables(answer,offline);
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
