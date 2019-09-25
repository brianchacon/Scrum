import java.awt.EventQueue;
public class main{

    public static void main(String[] ar) {
        EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
            //scrum ventana1=new scrum();
            setVentana ven = new setVentana();
            String s = null; 
            ven.abrir();
            while(s == null){
            s = ven.ancho;
            
            if(s !=null)
                System.out.println(s);
            }
            //ventana1.setBounds(10,10,600,400);
           // ventana1.setVisible(true);
          }
        });
    }
}
