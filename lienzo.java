import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
class  lienzo extends JPanel{
    Estilo  estilo = new Estilo();
    int width;
    int height;
    int widthBox;
    int heightBox;
    int widthIdColor = widthBox/12;//10 de 120
    int overlapCards = estilo.margin_box_X;

    boolean do_not_draw = false;
    private box [] lista = null;
    private Par [] pos_elems = null;
    private int cantHoles = 0;
    
    public lienzo(int w,int h,int wb,int hb){
        if(w < wb + estilo.margin_box_X || h < hb + estilo.margin_box_Y){
            do_not_draw = true;
            System.out.println("Tamaño del panel muy pequeño para almacenar cajas");
        }    
        width     = w;
        height    = h;
        widthBox  = wb;
        heightBox = hb;
    }
    
    void load_lista(box [] entrada){
        if(entrada != null){
            System.out.println("Length del nuevo lista : "+ entrada.length);
            lista = new box[entrada.length];
            System.out.println("Length del nuevo lista : "+ lista.length);
            pos_elems = new Par[entrada.length];
            cantHoles = 0;
            for(int i = 0;i<entrada.length;i++){
                lista[i]=entrada[i];
               
                System.out.println("Entroooo");
            }
            if( !load_pos() ){
                lista = null;
                pos_elems = null;
                System.out.println("No se puede cargar lista: tamano excedido");
            }
            repaint();
        }
        else{
            lista = null;
            pos_elems = null;
            cantHoles = 0;
            repaint();
        }       
        
    }
    boolean load_pos(){
        boolean result = true;
        Par p = null;
        if(pos_elems != null && pos_elems.length >0){
             p = new Par();
             p.x = estilo.margin_box_X;
             p.y = estilo.margin_box_Y;
             pos_elems[0] = p;
             for(int i = 1; i <pos_elems.length; i++){
                if(p.x + widthBox*2 + estilo.margin_box_X > width){
                    if(p.y + heightBox*2 + estilo.margin_box_Y > height){
                        System.out.println("Alto excedido");
                        result = false;
                    }    
                    else{
                        pos_elems[i] = new Par();
                        pos_elems[i].x = estilo.margin_box_X;
                        pos_elems[i].y = p.y + heightBox + estilo.margin_box_Y;
                    }     
                }
                else{
                    pos_elems[i] = new Par();
                    pos_elems[i].x = p.x + widthBox + estilo.margin_box_X;
                    pos_elems[i].y = p.y;
                }
                p = pos_elems[i];
            }
        }
        return result;
    }
    
    box [] returnlista(){
        return lista;
    }

   public boolean inArea(Par elem, int  xMouse,int yMouse){
        boolean result = false;
        if(elem != null && elem.x <= xMouse && elem.y <= yMouse && widthBox+elem.x > xMouse && heightBox +elem.y > yMouse )
            result = true;
        return result;
    }
    
    box getSelected(int xMouse, int yMouse){
        box result = null;
        if(pos_elems != null){
            boolean ends = false;
            for(int i=0;i< pos_elems.length && !ends ;i++){
                if(inArea(pos_elems[i], xMouse, yMouse)){
                    if(lista[i] == null)
                        System.out.println("Listas de posición y de elementos no correspondida");
                    result = lista[i]; 
                    ends = true;
                }    
            }
        }
        return result;
    }
    
    void show_list(){
        if(lista != null && pos_elems != null){
            System.out.print("  [ ");
            for(int i = 0;i < lista.length;i++)
                System.out.print(lista[i].asign +", ");
            System.out.println("] ");
            System.out.print("  [ ");
            for(int i = 0;i < lista.length;i++){
              if(pos_elems[i]!= null)    
                System.out.print(pos_elems[i].x +":" + pos_elems[i].y +", ");
            }
            System.out.println("] ");
        }     
    }
    Par findHoles(){
        Par result = null;
        if(pos_elems != null ){
            if(pos_elems[0].x != estilo.margin_box_X || pos_elems[0].y != estilo.margin_box_Y){
               result = new Par();
               result.x = estilo.margin_box_X;
               result.y = estilo.margin_box_Y;
            }
            else{
                int x = 0;
                int y = 0;
                boolean ends = false;
                for(int i= 0;i<pos_elems.length-1 && !ends;i++){
                    if(pos_elems[i].y == pos_elems[i+1].y){
                        if(pos_elems[i].x + widthBox*2 + estilo.margin_box_X*2 <= pos_elems[i+1].x){
                            result = new Par(pos_elems[i].x + widthBox + estilo.margin_box_X, pos_elems[i].y);
                            ends = true;
                        }   
                    }
                    else{
                        if(pos_elems[i].x + widthBox*2 + estilo.margin_box_X <= width ){
                            result = new Par(pos_elems[i].x + widthBox + estilo.margin_box_X, pos_elems[i].y);
                            ends = true;
                        }
                        else if(estilo.margin_box_X*2 + widthBox <= pos_elems[i+1].x){
                            result = new Par(estilo.margin_box_X, pos_elems[i+1].y);
                            ends = true;
                        }
                        
                    }
                }
            }
            
        }
        if(result == null)
            System.out.println("NO se hhan encontrado huecos");
        return result;
    }
    Par ubicacion(){
        Par result = null;
        if(pos_elems == null){
            result = new Par ();
            result.x = estilo.margin_box_X;
            result.y = estilo.margin_box_Y;
        }
        else if(!do_not_draw && pos_elems.length>0){
            Par tmp;
            if(cantHoles!= 0){//
                
                result = findHoles(); 
                  
                cantHoles--; 
            }
            else{
                for(int i= 0; i<pos_elems.length;i++){
                }
                tmp = pos_elems[pos_elems.length-1];
                if(tmp == null) 
                    System.out.println("ubicacion:tmp NULL, :" +pos_elems.length);
                if(tmp.x + widthBox*2 + estilo.margin_box_X > width){
                    if(tmp.y + heightBox*2 + estilo.margin_box_Y > height)
                        System.out.println("Alto excedido");
                    else{
                        result = new Par();
                        result.x = estilo.margin_box_X;
                        result.y = tmp.y + heightBox + estilo.margin_box_Y;
                    }     
                }
                else{
                    result = new Par();
                    result.x = tmp.x + widthBox + estilo.margin_box_X;
                    result.y = tmp.y;
                }
            }
        }
        else if (!do_not_draw && pos_elems == null || pos_elems.length==0){
            pos_elems = new Par [1];
            result = new Par ();
            result.x = estilo.margin_box_X;
            result.y = estilo.margin_box_Y;
        }
        return result;
    }
    
    int add_lista(box newBox){
        Par nueva_pos = ubicacion();
        int result = 0;
        if(lista != null){
            if(nueva_pos != null){
            
                box [] tmp = lista;
                Par [] tmpPos =  pos_elems;
                lista = new box[tmp.length+1];
                pos_elems = new Par [tmp.length+1];
                for(int i = 0;i<tmp.length;i++){
                    lista[i]=tmp[i]; 
                    pos_elems[i] = tmpPos[i];
                }    
                if(newBox.estado == 0){
                    newBox.estado = 1;
                    System.out.println("Elemento no inicializado, agregado a la lista 1");
                }    
                lista[tmp.length] = newBox;
                System.out.println(nueva_pos.x+":"+nueva_pos.y);
                //TODO: ordenar elementos por posicion en pantalla
                pos_elems[tmp.length]= nueva_pos;
                
                repaint();
            }
            else{
                String msj = "Alerta: no se pudo agregar el elemento";
                JOptionPane.showMessageDialog(this, msj,"",JOptionPane.ERROR_MESSAGE);
                System.out.println(msj);
                result = 1;
            }
        }
        else{
            if(nueva_pos != null){
                lista = new box[1];
                pos_elems = new Par [1];
                lista[0] = newBox;
                pos_elems[0] = nueva_pos;
                
                repaint();
            }
            else{
                String msj = "Alerta: no se pudo agregar el elemento";
                JOptionPane.showMessageDialog(this, msj,"",JOptionPane.ERROR_MESSAGE);
                System.out.println(msj);
                result = 1;
            }
        }    
        return result;
    }
    private boolean exists(box [] l,box delBox){
        boolean result = false;
        if(l != null){
            for(int i =0;i< l.length && !result;i++){
                if(l[i] == delBox)
                    result = true;
            }
        }
        return result;
    }
    
    boolean del_lista(box delBox){
        boolean result = false;               
        if(exists(lista,delBox)){
            int indDeleted = -1;
            box [] tmp = lista;
            Par [] tmpPos = pos_elems;
            lista = new box[tmp.length-1];
            pos_elems = new Par[tmpPos.length-1];
            for(int i = 0,j=0;i<tmp.length;i++){
                if(tmp[i]!= delBox ){
                    lista[j]=tmp[i];
                    pos_elems[j]=tmpPos[i];
                    j++;
                }
                else
                    indDeleted = i;                   
            }
            //TODO ordenar Acomodar posiciones de elementos en pos_elem 
            result =true;
            if(indDeleted != -1 && tmpPos.length-1 != indDeleted)
                cantHoles++;  
            repaint();               
        }
        return result;
    }
    
    
    Color convertir_a_color(String c){
        Color res = new Color(0,0,0);
        
        if(c.length() == 9){
            String s1 = "";
            String s2 = "";
            String s3 = "";
            int i = 0;
            s1 += "" + c.charAt(i) + c.charAt(i+1) + c.charAt(i+2);  
            i+=3;
            s2 += "" + c.charAt(i) + c.charAt(i+1) + c.charAt(i+2);  
            i+=3;
            s3 += "" + c.charAt(i) + c.charAt(i+1) + c.charAt(i+2);  
            res = new Color(Integer.parseInt(s1),Integer.parseInt(s2),Integer.parseInt(s3));
        }
        else{
            System.out.println("Problemas al crear el color, largo : "+c.length() );
            res = null;
        }
        return res;
    }
    
void drawCard(Graphics g, Par loc, box card, int arcAncho, int arcAlto){

    int x = loc.x;
    int y = loc.y;
    String str;
//Formas de tarjeta
    g.setColor( Color.white);
    g.fillRoundRect(x+widthIdColor, y, widthBox-widthIdColor, heightBox,arcAncho, arcAlto);
    g.setColor(convertir_a_color(card.color));
    g.fillRect(x, y, widthIdColor+overlapCards, heightBox);
    
//Contenido de la tarjeta (Strings)
    g.setColor( estilo.tituloColor );
    g.setFont( estilo.textoFont );
    //VER SI SE PUEDE PONER AFUERA DE ESTA FUNCION, sino se debe pedir el arg "g2"
    /*g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, 
                    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB );*/
    x += estilo.marginTop_text_X;

    y += estilo.marginTop_text_Y;
    str = card.descrip ;
    g.drawString(str, x, y);
                    
    g.setColor( estilo.textoColor );
  //check if is null don't draw  
    y += estilo.margin_text_Y;
    str = card.asign ;
    g.drawString(str,x,y);

    if(card.timeIni != -1){
        y += estilo.margin_text_Y;
        str = card.timeIni + "";
        g.drawString(str,x,y);
    }
    if(card.timeFin != -1){
        y += estilo.margin_text_Y;
        str = card.timeFin+ "";
        g.drawString(str,x,y);
    }
}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //////////////////////////        
        //width 
        //height
        if(!do_not_draw && lista != null){
            Par loc;
            boolean ends = false;
            
            for(int i = 0;i<lista.length && !ends ;i++){
                loc = pos_elems[i];
                g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, 
                                        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB );
              //g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING,
              //  RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                drawCard(g,loc, lista[i], estilo.arcAncho, estilo.arcAlto);
            }
        } 
    }    
}
