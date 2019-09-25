import java.io.BufferedWriter;//Escribe la informacion en el archivo o sea lo graba en el disco duro.
import java.io.File;//Comprueva si existe el archivo.
import java.io.FileWriter;//Indica el archivo en el que se va a escribir.
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.awt.EventQueue;
public class archivo{
    String dire = null;
    String last = "recientes.txt";
    //private ParStr [] recientes;
    String proyectName = "";
    public archivo(){
             
    }  
    public archivo(String direccion){
        dire = direccion;
    }

    ParStr [] strToParStr(String []linea, int len){
        ParStr [] result = null;

        return result;
    }
    ParStr [] load_recientes(){
        ParStr [] result = null;
        try {
            File f = new File (last);
            FileReader fr = new FileReader (f);
            BufferedReader br = new BufferedReader(fr);
            String len = br.readLine();
            System.out.println("Valor de len: "+ len);
            if(len != null){
                String linea[] = new String[Integer.parseInt(len)*2];
                result = new ParStr[Integer.parseInt(len)] ;
                for(int i=0, j=0; i<linea.length && (linea[i]=br.readLine())!=null ;i++){
                    if(i%2==0){
                        result[j] = new ParStr();
                        result[j].clave = linea[i];
                    }
                    else{
                        result[j].valor = linea[i];
                        j++;
                    }    
                }
            }            
            fr.close();            
 
        }catch(Exception e){
    	  e.printStackTrace();
    	}   
        return result;
    }
    void saveRecientes(ParStr [] recientes){
        if(recientes != null){
            try{
                File  f = new File(last);
                FileWriter fw = new FileWriter(f);
                String res = "";
                res += recientes.length + "\r\n";
                for(int i=0;i < recientes.length;i++){
                    res += recientes[i].clave + "\r\n";
                    res += recientes[i].valor + "\r\n";
                }
                fw.write(res );
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();
        	}catch(Exception e){
        	    e.printStackTrace();
        	}
        }
    }
    //'\'
    void saveFormatedString(box [] b, String name, String direccion){
        if(direccion !=null)
            dire = direccion;   
        if(direccion ==null)
            System.out.println("NULLOOOOOOOo");
        
        if(b==null)
            System.out.println("tabla NULA");
        else
            System.out.println(b.length);
        
        if(name != null)
            proyectName = name;
        else
            proyectName = " ";    
              
        try{
            File  f = new File(dire);
            FileWriter fw = new FileWriter(f);
            String res = "";
            res += proyectName+ "\r\n";
            if(b!= null)
                res += b.length + "\r\n";
            else
                res += "0\r\n";    
            res += "[\r\n" ;
            if(b!= null){
                for(int i=0;i < b.length;i++){
                    res += b[i].estado + "\r\n";
                    res += b[i].timeIni + "\r\n";
                    res += b[i].timeFin + "\r\n";
                    res += b[i].descrip + "\r\n";
                    res += b[i].asign + "\r\n";
                    //res += b[i].[ ]conexion
                    res += b[i].color + "\r\n"; 
                    //res += b[i].codigo + "\r\n"; 
                    //res += b[i].progreso + "\r\n";
                    fw.write(res + ",\r\n" );
                    res ="";
                }
            }
            fw.write(res +"]\r\n" );
            BufferedWriter bw = new BufferedWriter(fw);
            bw.close();
    	}catch(Exception e){
    	  e.printStackTrace();
    	}
    }
    
    box[] loadFormatedString( String direccion){
        box []b = null;
        if(direccion !=null)
            dire = direccion;   
        if(dire != null){    
            try {
                File f = new File (dire);
                if(f.exists()){
                    FileReader fr = new FileReader (f);
                    BufferedReader br = new BufferedReader(fr);
                    String linea[] = new String[6];
                    int i=0;
                    proyectName =br.readLine();
                    if(proyectName == null)
                        proyectName = "";
                    linea[i]=br.readLine();
                    if(linea[i] != null){
                        int len = Integer.parseInt(linea[i]);
                        b = new box[len];
                        br.readLine();//saltarnos '['
                        for(int j=0; (linea[i]=br.readLine())!=null;i++){
                            if(i==5){    
                                int est    = Integer.parseInt(linea[0]);
                                int ti     = Integer.parseInt(linea[1]);
                                int tf     = Integer.parseInt(linea[2]);
                                String d   = linea[3];
                                String a   = linea[4];    
                                String c   = linea[5];
                                //int cod    = Integer.parseInt(linea[6]);
                                //float prog = Float.parseFloat(linea[7]);
                                b[j]= new box(est,ti,tf,d,a,c);
                                j++;
                                i=-1; 
                                br.readLine();//saltando las ','
                            }
                        }
                        fr.close();  
                    }else System.out.println("vacio = nulo");
                }  
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
           
        return b;
    }
       

}
