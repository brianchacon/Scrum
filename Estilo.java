import java.awt.Color;
import java.awt.Font;
class Estilo{
    Color tarjetaColor = Color.white; 
    Color tituloColor = Color.green ;
    Color textoColor = Color.black ;
    int sizeFont = 16;
    Font  textoFont = new Font( "Tahoma", Font.BOLD, sizeFont ) ;
    int margin_box_X = 5;
    int margin_box_Y = 5;
    int marginTop_text_X = 10;
    int marginTop_text_Y = 20;
    int margin_text_X = 10;
    int margin_text_Y = sizeFont;
    //Redondeo de las tarjetas:
    int arcAncho = 10;
    int arcAlto = 10;
}
