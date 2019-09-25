class box{
    //ATRIBUTOS
    int estado;//0=SinAsignar 1 =todo 2 =enProceso 3=finalizado 
    int timeIni; //Ej    0 = 000000 = 00:00:00
    int timeFin; //Ej 1100 = 001100 = 00:11:00
    String descrip;
    String asign;
    String color; //en modo rgb EJ: "000230230" == 000,230,230
    
    public box(){
        estado  = 0;
        timeIni =-1;
        timeFin =-1;
        descrip = null;
        asign = null;
        color = new String(); 
 
    }
    public box(int est,int ti,int tf,String d,String a,String c){ //empty box
        estado  = est;
        timeIni = ti;
        timeFin = tf;
        descrip = d;
        asign = a;
        color = c; 

    }
}
