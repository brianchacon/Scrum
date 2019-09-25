import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;  
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
class newBox extends JDialog implements ActionListener{
    
    int ti, tf,cod;
    String d, a, c;
    float prog;
    
    private JLabel tiLb, tfLb, dLb, aLb, cLb;
    private JTextField tiTf, tfTf, dTf, aTf, cTf1,cTf2,cTf3;  
    JButton guardar,cancelar;

    int red = (int) Math.floor(Math.random()*256);//[M,N] = (N-M+1)+M) = (255-0+1)+0)
    int green = (int) Math.floor(Math.random()*256);
    int blue = (int) Math.floor(Math.random()*256);
    box result = null;

    int marginLeft = 25;

    sampleT sample = null;
    int widthSample = 20;
    int xSample = 240;
    int ySample = 110;
    
    public newBox(int w,int h){
        setLayout(null);
        setTitle("Nueva tarea");

        setModal(true);
        System.out.println("Colores: "+red+","+green+","+blue);
        tiLb = new JLabel("Tiempo inicial:");
        tiLb.setBounds(marginLeft,30,170,20);
        add(tiLb);
        tfLb = new JLabel("Tiempo final:");
        tfLb.setBounds(marginLeft,50,170,20);
        add(tfLb);
        dLb = new JLabel("Tarea:");
        dLb.setBounds(marginLeft,70,170,20);
        add(dLb);
        aLb = new JLabel("Persona asignada:");
        aLb.setBounds(marginLeft,90,170,20);
        add(aLb);
        cLb = new JLabel("Color:");
        cLb.setBounds(marginLeft,110,170,20);
        add(cLb);
        
        tiTf   = new JTextField("0");
        tiTf.setBounds(170-marginLeft,30,90,20);
        add(tiTf);
        tfTf   = new JTextField("0");
        tfTf.setBounds(170-marginLeft,50,90,20);
        add(tfTf);
        dTf    = new JTextField("");
        dTf.setBounds(170-marginLeft,70,90,20);
        add(dTf);
        aTf    = new JTextField("");
        aTf.setBounds(170-marginLeft,90,90,20);
        add(aTf);         
        
        sample = new sampleT(red,green,blue);
        sample.setBounds(110-marginLeft,ySample,170,widthSample+5);
        add(sample);

        guardar =  new JButton("Guardar");
        guardar.setBounds(marginLeft,200,100,20);
        guardar.addActionListener(this);
        add(guardar);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(marginLeft+100,200,100,20);
        cancelar.addActionListener(this);
        add(cancelar);       
        
        setBounds(w/2-150,h/2-150,300,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    class sampleT extends JPanel implements ChangeListener{
        JSpinner red, green, blue;
        int redc, greenc, bluec;
        int xv = 0;
        public sampleT (int r, int g, int b){
            setLayout(null);

            red = new JSpinner();
            red.setValue(r);
            red.setBounds(xv,0,50,20);
            red.addChangeListener(this);
            add(red);
            
            xv +=50;
                 
            green = new JSpinner();
            green.setValue(g);
            green.setBounds(xv,0,50,20);
            green.addChangeListener(this);
            add(green);
            
            xv +=50;
            
            blue = new JSpinner();
            blue.setValue(b);
            blue.setBounds(xv,0,50,20);
            blue.addChangeListener(this);
            add(blue); 
            
            xv +=50;
            redc   = r;
            greenc = g;
            bluec  = b;
        }
        
        @Override
        public void stateChanged(ChangeEvent e) {
            redc   = Integer.parseInt( red.getValue().toString()   );
            greenc = Integer.parseInt( green.getValue().toString() );
            bluec  = Integer.parseInt( blue.getValue().toString() );
            repaint();
        }
        
        Color genRandomColor(){   
            return (new Color(redc,greenc,bluec));
        }
       
        @Override
        public void paintComponent( Graphics g ) {
            super.paintComponent(g);
            g.setColor(genRandomColor());
            g.fillRect(xv +5, 0, widthSample, widthSample);
        }
    }    


    
    
    box getDatos(){

        return result;
    }
    boolean isNumberChar(char c){
        boolean result = false;
        //numeros 48-57  "," 44   "." 46
        if(c>47 && c<58 )
            result =  true;
        return result;    
    }
    
    boolean isPunto(char c){
        boolean result = false;
        if( c == 44 || c == 46)
            result = true;
        return result;    
    }
    
    boolean isNumberStr(String s,boolean is_float){
        boolean result = false;
        if(s.length()>0){   
            boolean r = true;
            boolean mutex = false;
            if(is_float)
                 mutex = true;
            for(int i=0;i<s.length() && r;i++){
                if(!isNumberChar(s.charAt(i)))
                    r = false;
                if( mutex && isPunto(s.charAt(i)) ){
                    r = true; //pisar el valor de arriba,si, lo se, es una logica mierdera :D  
                    mutex = false;
                }    
            }
            result = r;
        }
        return result;
    }  
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar) {
            String s = tiTf.getText();
            if(isNumberStr(s,false))
                ti = Integer.parseInt(s);
            else
                ti = 0;
            s = tfTf.getText();
            if(isNumberStr(s,false))
                tf = Integer.parseInt( s );
            else
                tf = 0;
            if(ti == 0 && tf == 0){
                ti = -1;
                tf = -1;
            }
            
            d = dTf.getText();
            System.out.println("la descripcion es["+d+"]"); 
            a = aTf.getText();
            String s1 = sample.redc + "";
            String s2 = sample.greenc + "";
            String s3 = sample.bluec + "";
            System.out.println("Colores"+sample.redc +sample.greenc +sample.bluec);
            if(s1.length() < 3){
               String tmp = s1;
               s1 = "";
               for(int i= 0, j= 0;i< 3;i++){
                    if(i< 3-tmp.length())
                        s1 += "0"; 
                    else{
                        s1 += tmp.charAt(j);
                        j++;
                    }     
               }
            }  
            if(s2.length() != 3){
               String tmp = s2;
               s2 = "";
               for(int i= 0, j= 0;i< 3;i++){
                    if(i< 3-tmp.length())
                        s2 += "0"; 
                    else{
                        s2 += tmp.charAt(j);
                        j++;
                    }     
               }
            } 
            if(s3.length() != 3){
               String tmp = s3;
               s3 = "";
               for(int i= 0, j= 0;i< 3;i++){
                    if(i< 3-tmp.length())
                        s3 += "0"; 
                    else{
                        s3 += tmp.charAt(j);
                        j++;
                    }     
               }
            }  
            
            c = s1 + s2 + s3;
            System.out.println("largo de c "+c.length());
            result = new box (1,ti , tf , d , a , c);
            this.dispose();     
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        }    
  }

 
}
