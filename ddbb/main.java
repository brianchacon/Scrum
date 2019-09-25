import java.awt.EventQueue;
public class main{

    public static void main(String[] ar) {
        EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
            LogIn vent1=new LogIn();
            Results vent2;
            //ventana1.
            if(vent1.validated)
                vent2 = new Results(vent1.result);
            
            /*setVentana ven = new setVentana();
            String s = null; 
            ven.abrir();
            while(s == null){
            s = ven.ancho;
            
            if(s !=null)
                System.out.println(s);
            }*/
          }
        });
    }
}
