import java.awt.EventQueue;
public class main{

    public static void main(String[] ar) {
        EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
            LogIn vent0 = new LogIn();
            
            scrum ventana1;
         
            ventana1 = new scrum(vent0.result,vent0.name,vent0.validated);
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
