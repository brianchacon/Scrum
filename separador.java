class separador{
    box [] uno;
    box [] dos;
    box [] tres;
    box [] cero; //espacio para los no inicializados
    
    separador(){
        uno = null;
        dos = null;
        tres = null;
        cero = null;
    }
 
    
    void sep(box [] entrada){
        if(entrada != null){
            int irregularidades_count = 0;
            box [] cero2 = new box [entrada.length];
            box [] uno2  = new box [entrada.length];
            box [] dos2  = new box [entrada.length];
            box [] tres2 = new box [entrada.length];
            int i_c=0,i_u=0,i_d=0,i_t=0;
            for(int i=0;i<entrada.length;i++){
                if(entrada[i].estado==0){
                    cero2[i_c] = entrada[i];
                    System.out.println("Nota: Estado no inicializado. Posicion: "+ i);
                    irregularidades_count++;
                    i_c++;
                }    
                else if(entrada[i].estado==1){
                    uno2[i_u] = entrada[i];    
                    i_u++;
                }
                else if(entrada[i].estado==2){
                    dos2[i_d] = entrada[i];    
                    i_d++;
                }
                else if(entrada[i].estado==3){
                    tres2[i_t] = entrada[i];    
                    i_t++;
                }
                else{
                    System.out.println("Excepcion logica: valor de estado fuera de rango");
                    irregularidades_count++;
                } 
            }
            cero = new box [i_c];
            uno  = new box [i_u];
            dos  = new box [i_d];
            tres = new box [i_t];
            for(int i=0;i<i_c;i++)
                cero[i] = cero2[i];
            for(int i=0;i<i_u;i++)
                uno[i] = uno2[i];
            for(int i=0;i<i_d;i++)
                dos[i] = dos2[i];
            for(int i=0;i<i_t;i++)
                tres[i] = tres2[i];  
            System.out.println("Separación de array inicial terminada");
            System.out.println("Cantidad de irregularidades" + irregularidades_count);
        }
    }
    
}
