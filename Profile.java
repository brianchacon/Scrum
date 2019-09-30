import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
public class Profile extends JPanel{
   
    JLabel userName;
    int wProf;
    int siz = 25;
    int marg_top = 13;
    public Profile(String n,int width/*,Estilo e*/){
        wProf = n.length()*siz;
        if(n == null || n=="")
            n ="Unknown";
        userName = new JLabel(n);
        userName.setBounds(20,marg_top-6,wProf,siz+2);
        userName.setForeground(new Color(123,123,123));
        userName.setFont(new Font("Monospaced",java.awt.Font.PLAIN,siz));
        add(userName);
        setBounds(width-wProf-50,0,wProf,siz+marg_top);
        setLayout(null);
    }

}
