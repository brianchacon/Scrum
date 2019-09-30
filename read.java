import java.sql.*;
class read{
    String nameDB = "scrum";
    String port   = "3306";
    String dire   = "localhost";//"//192.168.2.1"
    String user   = "root";
    String pass   = "";
    String tableN = "usuarios";
    String queryP  = "select tabla from "+tableN+" where mail = ";                   
    String queryT  = "select * from ";
    //String campo1 = "id";
    //String campo2 = "mail";
        //SI NO SE AGREGA LA OPCION "?useSSL=false" NO FUNCIONARa
    String path = "jdbc:mysql://"+dire+":"+port+"/"+nameDB+ "?useSSL=false"; //MySQL
    //String path = "jdbc:sqlserver://"+dire+"\\"+nameDB;//SQL Server
    //String path = "jdbc:oracle:thin@"+dire+":"+port+":"+nameDB;// Oracle thin es el tipo del driver

    String login(String email,String pass2){
        String result = "";
        try{
       // Class.forName("com.mysql.jdbc.Driver");
            Connection miconexion = DriverManager.getConnection(path,user,pass); 
            System.out.println("!conexion exitosa");
            
            Statement mistatement = miconexion.createStatement();
            System.out.println("!statemen exitosa");
//            ResultSet miresultset = mistatement.executeQuery("select * from "+tableN);//select tabla from usuarios where mail = 'dinklage@gmail.com' and pass = 'le petit8';");
            ResultSet miresultset = mistatement.executeQuery(queryP+email+" and pass = "+pass2);
            System.out.println("!peticion exitosa");
            while(miresultset.next())
                result += miresultset.getString("tabla");//TODO tendré q devolver el nombre de la lista y usuario 
        
        }catch(Exception ex){
            System.out.println("OJO "+ex.getMessage());
        }
        return result;
    } 

    String getTable(String tableName){
        String result = "";
        try{
       // Class.forName("com.mysql.jdbc.Driver");
            Connection miconexion = DriverManager.getConnection(path,user,pass); 
            System.out.println("!conexion exitosa");
            
            Statement mistatement = miconexion.createStatement();
            System.out.println("!statemen exitosa");
  	  	 	      
            ResultSet miresultset = mistatement.executeQuery(queryT+tableName);
            System.out.println("!peticion exitosa");
            
            while(miresultset.next())
                    result += miresultset.getString("tarea") +" -> "+miresultset.getString("asignado")+" -> "+miresultset.getString("descripcion")+" -> "+miresultset.getInt("tiempoIni")+" -> "+miresultset.getInt("tiempoFin")+" -> "+miresultset.getInt("color") +" -> "+ miresultset.getInt("estado")+"\n";
        
        }catch(Exception ex){
            System.out.println("OJO "+ex.getMessage());
        }
        return result;
    }   

    /*public static void main(String[] args) {
         
         read f=new read();
         f.con();
         //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/
}    
