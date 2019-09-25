import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.MouseInfo;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Cursor;
class scrum extends JFrame implements ActionListener{
    int width = 1600;//800;
    int height = 850;//600;
    int heightMenu = 50;
    int widthPanel = width/3;
    int heightPanel = height-heightMenu*3-17;
    int widthBox = 110; //tamaño de las cajas
    int heightBox = 100; //tamaño de las cajas
    
    boolean CAMBIOS_SIN_GUARDAR = false;
    private boolean FLAGG_SELECTED = false;
    private boolean FLAGG_SUBIR = false; 
    private boolean FLAGG_DELETE = false;
    
    mouseOps moOp =new mouseOps();
    
    JMenuBar barra; 
    JMenu   archivo;
    JMenuItem nuevo, abrir, guardar,guardarComo;

    lienzo panelUno = new lienzo(widthPanel,heightPanel,widthBox,heightBox);
    lienzo panelDos = new lienzo(widthPanel,heightPanel,widthBox,heightBox);
    lienzo panelTres = new lienzo(widthPanel,heightPanel,widthBox,heightBox);
    JPanel panelMenu= new JPanel();
    JPanel panelTituloUno = new JPanel();
    JPanel panelTituloDos = new JPanel();
    JPanel panelTituloTres = new JPanel();

    JLabel todoLeg, procLeg, hechoLeg;
    
    JButton botonUnoAdd;
    JButton botonUnoDel;
    JButton subirEstado, botNuevo, botAbrir, botGuardar, botGuardarComo;
    
    String direccion = null;
    String nombre = null;
    
    box selected;
    box [] list;

    ParStr [] recientes = null;
    String proyectName = "";
    
    int menuBarHeight;
    Color colorMenu = new Color(255,255,255);
    Color colorBorde = new Color(198,198,198);
    int margin_left_panel = 5;
    
    public scrum(){
        archivo a = new archivo();
        recientes = a.load_recientes();
        System.out.println("reciente inicial: "+recientes[recientes.length-1].clave);
        setLayout(null);
          
        barra = new JMenuBar(); 
        setJMenuBar(barra);
        archivo = new JMenu("Archivo");
        barra.add(archivo);
        nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(this);
        archivo.add(nuevo);
        abrir = new JMenuItem("Abrir");
        abrir.addActionListener(this);
        archivo.add(abrir);
        guardar = new JMenuItem("Guardar");
        guardar.addActionListener(this);
        archivo.add(guardar);
        guardarComo = new JMenuItem("Guardar como..");
        guardarComo.addActionListener(this);
        archivo.add(guardarComo);  

        archivo.setBackground(colorMenu);
        nuevo.setBackground(colorMenu);
        abrir.setBackground(colorMenu);
        guardar.setBackground(colorMenu);
        guardarComo.setBackground(colorMenu);        
        barra.setBackground(colorMenu);
        panelTituloUno.setBackground(Color.white);
        panelTituloDos.setBackground(Color.white);
        panelTituloTres.setBackground(Color.white);
        
        panelMenu.setBackground(colorMenu);
        panelUno.setBackground(new Color(230,230,230));
        panelDos.setBackground(new Color(230,230,230));
        panelTres.setBackground(new Color(230,230,230));
        
        panelMenu.setBounds(0, 0, width, heightMenu);
        
        panelTituloUno.setBounds(margin_left_panel,               heightMenu+margin_left_panel, widthPanel-margin_left_panel, heightMenu);  
        panelUno.setBounds(margin_left_panel,                     heightMenu*2+margin_left_panel, widthPanel-margin_left_panel, heightPanel);
   
        panelTituloDos.setBounds(widthPanel+margin_left_panel,    heightMenu+margin_left_panel,  widthPanel-margin_left_panel, heightMenu);
        panelDos.setBounds(widthPanel+margin_left_panel,          heightMenu*2+margin_left_panel, widthPanel-margin_left_panel, heightPanel);
    
        panelTituloTres.setBounds(widthPanel*2+margin_left_panel, heightMenu+margin_left_panel, widthPanel-margin_left_panel, heightMenu);
        panelTres.setBounds(widthPanel*2+margin_left_panel,       heightMenu*2+margin_left_panel, widthPanel-margin_left_panel, heightPanel);
      
        
        panelUno.setBorder(BorderFactory.createLineBorder(colorBorde));
        panelDos.setBorder(BorderFactory.createLineBorder(colorBorde));
        panelTres.setBorder(BorderFactory.createLineBorder(colorBorde));
        panelTituloUno.setBorder(BorderFactory.createLineBorder(colorBorde));
        panelTituloDos.setBorder(BorderFactory.createLineBorder(colorBorde));
        panelTituloTres.setBorder(BorderFactory.createLineBorder(colorBorde));
        
        botonUnoAdd    = new JButton();
        botonUnoDel    = new JButton();
        subirEstado    = new JButton();
        botNuevo       = new JButton();
        botAbrir       = new JButton();
        botGuardar     = new JButton();
        botGuardarComo = new JButton();

        todoLeg  = new JLabel("TODO");
        procLeg  = new JLabel("IN PROGRESS");
        hechoLeg = new JLabel("DONE");
        int p = (widthPanel-margin_left_panel)/2-100;
        todoLeg.setBounds(p,5,100, heightMenu-10);
        procLeg.setBounds(p,5,300, heightMenu-10);
        hechoLeg.setBounds(p,5, 100, heightMenu-10);
        todoLeg.setFont(todoLeg.getFont().deriveFont(30.0f));
        procLeg.setFont(procLeg.getFont().deriveFont(30.0f));
        hechoLeg.setFont(hechoLeg.getFont().deriveFont(30.0f));
        
        int w_bot = 40;
        p = widthPanel - w_bot*2;
        int h_bot = heightMenu-10;
        int marg_top_inf_bot = 2 ;
        ImageIcon botMas       = new ImageIcon("boton +3b.png");
        ImageIcon botDel       = new ImageIcon("boton -3b.png");
        ImageIcon subir        = new ImageIcon("boton subir.png");
        ImageIcon archivoNuevo = new ImageIcon("archivoNuevo.png");
        ImageIcon guardar      = new ImageIcon("guardar.png");
        ImageIcon guardarComo  = new ImageIcon("guardarComo.png");
        ImageIcon abrir        = new ImageIcon("abrir.png");
        Image icon             = new ImageIcon(getClass().getResource("LOGO.png")).getImage();
        setIconImage(icon);
       
        
        
        int pMenu = 5;
        botNuevo.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        botAbrir.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        botGuardar.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        botGuardarComo.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        subirEstado.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        botonUnoAdd.setBounds(pMenu,5,w_bot, h_bot);
        pMenu += w_bot + 5;
        botonUnoDel.setBounds(pMenu,5,w_bot, h_bot);

        botNuevo.setOpaque(false); 
        botNuevo.setContentAreaFilled(false);
        botAbrir.setOpaque(false); 
        botAbrir.setContentAreaFilled(false);
        botGuardar.setOpaque(false); 
        botGuardar.setContentAreaFilled(false);
        botGuardarComo.setOpaque(false); 
        botGuardarComo.setContentAreaFilled(false);     
        subirEstado.setOpaque(false); 
        
        botonUnoAdd.setIcon(botMas);
        botonUnoDel.setIcon(botDel);
        subirEstado.setIcon(subir);
        botNuevo.setIcon(archivoNuevo);
        botAbrir.setIcon(abrir);
        botGuardar.setIcon(guardar);
        botGuardarComo.setIcon(guardarComo);


        botonUnoAdd.addActionListener(this);
        botonUnoDel.addActionListener(this);            
        subirEstado.addActionListener(this);
        botNuevo.addActionListener(this);
        botAbrir.addActionListener(this);
        botGuardar.addActionListener(this);
        botGuardarComo.addActionListener(this);
        
        panelMenu.setLayout(null);
        panelTituloUno.setLayout(null);
        panelTituloDos.setLayout(null);
        panelTituloTres.setLayout(null);
        
        
        panelMenu.add(botonUnoAdd);
        panelMenu.add(botonUnoDel);  
        panelMenu.add(botNuevo);
        panelMenu.add(botAbrir);
        panelMenu.add(botGuardar);
        panelMenu.add(botGuardarComo);
        panelMenu.add(subirEstado);
        panelTituloUno.add(todoLeg);
        panelTituloDos.add(procLeg);
        panelTituloTres.add(hechoLeg);
        
        separador sep = new separador();
        if(recientes != null){
            ParStr actual = recientes[recientes.length-1];
            proyectName = actual.clave;       
            System.out.println("direcion de actaul"+actual.valor);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           	                                                                                                                                                                                                                                                                                                                            
            list = a.loadFormatedString(actual.valor);//cargamos el last de la lista 
        }    
        else
            list = a.loadFormatedString(null);
        sep.sep(list);
        panelUno.load_lista(sep.uno);
        panelDos.load_lista(sep.dos);
        panelTres.load_lista(sep.tres);
        
        add(panelMenu);
        add(panelTituloUno);
        add(panelTituloDos);
        add(panelTituloTres);
        add(panelUno);
        add(panelDos);
        add(panelTres);
        

        setBounds(0,10,width/*+16*/,height);
        setVisible(true);
        setTitle(proyectName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
        JMenu meme = this.getJMenuBar().getMenu(0);
        menuBarHeight = meme.getHeight();
        setResizable(false);   
      
    }
    
    void show_list(){
        if(list != null){
            System.out.print("[ ");
            for(int i = 0;i < list.length;i++)
                System.out.print(list[i].asign +", ");
            System.out.println("] ");
            panelUno.show_list();
            panelDos.show_list();
            panelTres.show_list();
        }
    }
    
    private void guardar(){
        if(direccion != null){
            System.out.println("Selected file: " + direccion);
            archivo a = new archivo();
            if(list == null)
                System.out.println("Pre; llamda nula");
            a.saveFormatedString(list, proyectName, direccion);
            a.saveRecientes(recientes);
            CAMBIOS_SIN_GUARDAR = false;
        }
    }
    
    private void guardarArchivoNuevo(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION && direccion == null) {
                File selectedFile = fileChooser.getSelectedFile();
                direccion = selectedFile.getAbsolutePath();
                guardar();
                CAMBIOS_SIN_GUARDAR = false;
            }    
    }
    
    private int searchInList(box selected){
        int result = -1;
        for(int i =0;i<list.length;i++){
            if(list[i] == selected)
                result = i;
        }
        return result;
   } 
    
    private boolean exists(box [] l,box delBox){
        boolean result = false;
        for(int i =0;i< l.length && !result;i++){
            if(l[i] == delBox)
                result = true;
        }
        return result;
    }
    
    private void subirEstado(box selected){
        if(selected.estado == 1){
            boolean exists = panelUno.del_lista(selected);
            if(exists){
                selected.estado = 2;
                if( panelDos.add_lista(selected) !=0){
                    selected.estado = 1;
                    panelUno.add_lista(selected);                 
                }
                int ind = searchInList(selected);
                if(ind != -1)
                    list[ind] = selected;
                else 
                    System.out.println("Elemento no existente en la lista general, pero si en la particular");   
            }
            else 
                System.out.println("Objeto elegido inexistente"); 
        }
        else if(selected.estado == 2){
            boolean exists = panelDos.del_lista(selected);
            if(exists){
                selected.estado = 3;
                if(panelTres.add_lista(selected) !=0){
                    selected.estado = 2;
                    panelDos.add_lista(selected);   
                }
                int ind = searchInList(selected);
                if(ind != -1)
                    list[ind] = selected;
                else 
                    System.out.println("Elemento no existente en la lista general, pero si en la particular");                 
            }
            else 
                System.out.println("Objeto elegido inexistente"); 
        }
        else
            System.out.println("Estado fuera de rango, o no es \"aumentable\" "); 
    }

    void newReciente(String direccion){
        if(recientes == null){
            recientes = new ParStr[1];
            recientes[0] = new ParStr();
            recientes[0].valor = direccion;  
        }
        else{
            ParStr [] tmp = recientes;
            recientes = new ParStr[tmp.length+1];
            for(int i=0;i<tmp.length;i++)
                recientes[i] = tmp[i];
            recientes[tmp.length] = new ParStr();    
            recientes[tmp.length].valor = proyectName;    
            recientes[tmp.length].valor = direccion;   
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        boolean needAdd = false;
        boolean needDel = false;
        show_list();
        int estado = -1;
        if (e.getSource()==nuevo || e.getSource()==botNuevo){
            if(CAMBIOS_SIN_GUARDAR){
                int eleccion = JOptionPane.showConfirmDialog (this, "Guardar datos antes de salir?","Warning",JOptionPane.YES_NO_OPTION);
                if(eleccion == JOptionPane.YES_OPTION){
                    if(direccion == null)
                         guardarArchivoNuevo(); 
                    else
                         guardar();
                }
                if(eleccion == JOptionPane.NO_OPTION){
                    System.out.println("no");
                }
                System.out.println("Opcion:"+eleccion); 
            }
            Nuevo vent = new Nuevo(width,height);
            String v = vent.getDatos();
            proyectName = v;
            if(v != null){
                this.setTitle(proyectName);
                list = null;
                panelUno.load_lista(null);
                panelDos.load_lista(null);
                panelTres.load_lista(null);          
                direccion = null;
                CAMBIOS_SIN_GUARDAR = true;
            }
        } 
        if (e.getSource()==abrir || e.getSource()==botAbrir){
            String oldDire = null;
            if(CAMBIOS_SIN_GUARDAR){
                int eleccion = JOptionPane.showConfirmDialog (/*this*/null, "Guardar datos antes de salir?","Warning",JOptionPane.YES_NO_OPTION);
                if(eleccion == JOptionPane.YES_OPTION){
                    if(direccion == null)
                         guardarArchivoNuevo(); 
                    else
                         guardar();  
                }
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                oldDire = direccion;
                direccion = selectedFile.getAbsolutePath();
                System.out.println("Selected file: " + direccion);
            }          
            if(direccion != null && oldDire != direccion){
                newReciente(direccion);
                archivo a = new archivo(direccion);        
                list = a.loadFormatedString(null); 
                separador sep = new separador();
                sep.sep(list);
                panelUno.load_lista(sep.uno);
                panelDos.load_lista(sep.dos);
                panelTres.load_lista(sep.tres);  
                panelUno.repaint();
                repaint();       
            } 
        }
        if (e.getSource()==guardar || e.getSource()==botGuardar){
            if(direccion == null)
                guardarArchivoNuevo();
            else
                guardar();    
        }
        if (e.getSource()==guardarComo || e.getSource()==botGuardarComo){
            guardarArchivoNuevo();  
        }
        if(e.getSource()==subirEstado){            
            FLAGG_SELECTED = true;
            FLAGG_SUBIR = true;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        if (e.getSource()==botonUnoAdd) {
            estado = 1;  
            newBox vent = new newBox(width,height);
            box v = vent.getDatos();
            
            if(v != null){
                if(v.descrip != null && !v.descrip.equals("") && !v.descrip.equals(" ")){
                    System.out.println(v.asign);
                    System.out.println("dENTRO DE VENT");
                    
                    needAdd = false;
                    v.estado = estado;
                    estado = -1;

                    //AGREGA ELEM A LISTA DEL PANEL CORRESPONDIENTE
                    boolean bo = true;
                    if(v.estado == 0)
                       panelUno.add_lista(v); 
                    else if(v.estado == 1)
                       panelUno.add_lista(v);
                    else if(v.estado == 2)
                        panelDos.add_lista(v);
                    else if(v.estado == 3) 
                        panelTres.add_lista(v);
                    else{
                        System.out.println("Excepcion logica: valor de estado fuera de rango");   
                        bo = false;
                    }
                    if(bo){
                        box [] tmpL = list;
                        if(tmpL != null){
                            list = new box[tmpL.length + 1 ];
                            for(int i=0;i<tmpL.length;i++)
                                list[i] = tmpL[i];
                            list[tmpL.length] = v;
                        }
                        else{
                            list = new box[1];
                            list[0] = v;
                        }
                    }
                        
                    CAMBIOS_SIN_GUARDAR = true;
                }
                else
                    JOptionPane.showMessageDialog(this, "Debe ingresar una tarea","",JOptionPane.ERROR_MESSAGE);
                
            }
            else
                System.out.println("Ventana getDatos: Datos Nulos");
          
        }
        if (e.getSource()==botonUnoDel){
             FLAGG_SELECTED = true;
             FLAGG_DELETE   = true;
             setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }
    void borrarElem(box delBox){
        //ELIMINA ELEM DE LA LISTA DEL PANEL CORRESPONDIENTE   
        boolean exists =false;
        if(exists(list, delBox)){
            box [] tmpL = list;
            list = new box[tmpL.length - 1 ];
            for(int i=0, j=0;i<tmpL.length;i++){
                if(tmpL[i]!= delBox ){
                    list[j] = tmpL[i];
                    j++;
                }    
            }
        }
        if(delBox.estado == 1)
           exists = panelUno.del_lista(delBox);
        else if(delBox.estado == 2)
           exists =  panelDos.del_lista(delBox);
        else if(delBox.estado == 3) 
           exists =  panelTres.del_lista(delBox);
        else
            System.out.println("Excepcion logica: valor de estado fuera de rango");   
        if(!exists)
            System.out.println("Elemento no existente");
        CAMBIOS_SIN_GUARDAR = true;
    }
    void cursorDefault(){
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    class mouseOps extends MouseAdapter implements MouseListener {

        public mouseOps(){
            System.out.println("constructor");
            addMouseListener(this);
        }
  
        public void mousePressed(MouseEvent e) {

            Component[] rel = getComponents();
            int x = MouseInfo.getPointerInfo().getLocation().x - rel[0].getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - rel[0].getLocationOnScreen().y;
            System.out.println("MouseClicked "+x+" : "+y);
            if(FLAGG_SELECTED){
               
                box selected = null;
                System.out.println("flagg selected");
                if(x >= 0 && y >= heightMenu && x <= widthPanel-2 && y <= heightPanel){
                    selected = panelUno.getSelected(x,y-heightMenu-menuBarHeight);
                    System.out.println("En panel UNO "+x+" : "+y);
                }
                else if(x >= widthPanel-1 && y >= heightMenu && x <= widthPanel*2-2  && y<= heightPanel){
                    selected = panelDos.getSelected(x-widthPanel,y-heightMenu-menuBarHeight);
                    System.out.println("En panel DOS "+x+" : "+y);
                }
                else if(x >= widthPanel*2 && y >= heightMenu && x <= widthPanel*3  && y <= heightPanel){
                    selected = panelTres.getSelected(x-widthPanel*2,y-heightMenu-menuBarHeight);
                    System.out.println("En panel TRES "+x+" : "+y);
                }    
                else
                    System.out.println("fuera de panel "+x+" "+y);
               
                if(selected != null){
                    if(FLAGG_SUBIR){
                       subirEstado(selected);
                       FLAGG_SUBIR = false; 
                    } 
                    if(FLAGG_DELETE){
                        borrarElem(selected);
                        FLAGG_DELETE = false;
                    }    
                }
                cursorDefault();   
                FLAGG_SELECTED =false; 
            }
        }
        
    }

}
