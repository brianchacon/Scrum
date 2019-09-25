import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
class scrum extends JFrame implements ActionListener{
    int width = 800;
    int height = 600;
    
    JButton botonUno;

    public scrum(){
        setLayout(null);
        botonUno = new JButton("Nuevo +"); 
        botonUno.setBounds(10,5,width/4,50);
        botonUno.addActionListener(this);
        add(botonUno);
        
        setBounds(10,10,width,height);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==botonUno) {
    
            //newBox vent = new newBox();
            setVentana vent = new setVentana();
            //if(vent.getDatos()!= null){
            if(vent.abrir()!= null){/*
                int ti = vent.ti;
                int tf = vent.tf;
                String d = vent.d; 
                String a = vent.a;
                String c = vent.c;
                int cod = vent.cod;
                float prog = vent.prog;
                System.out.println("valores: "+ti +tf+d+a+c+cod+prog);
                */
            }else System.out.println("Ventana getDatos: Datos Nulos");
        }
        
    }
    
    public void paint (Graphics g){
        //super.paint(g);
        //g.setColor( Color.blue );
            
      }
    

}
