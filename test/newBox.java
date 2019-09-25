class newBox extends JFrame implements ActionListener{
    
    int ti;
    int tf;
    String d; 
    String a;
    String c;
    int cod;
    float prog;
    
    private JLabel tiLb;
    private JLabel tfLb;
    private JLabel dLb;
    private JLabel aLb;
    private JLabel cLb;
    private JLabel codLb;
    private JLabel progLb;


    private JTextField tiTf;
    private JTextField tfTf;
    private JTextField dTf;
    private JTextField aTf;
    private JTextField cTf;
    private JTextField codTf;
    private JTextField progTf;
    
    JButton guardar,cancelar;
    
    String result;
    
    public newBox(){
        result = null;  
    }
    String getDatos(){
      
        
        setLayout(null);
        setTitle("Nueva tarea");
        
        tiLb = new JLabel("Tiempo inicial:");
        tiLb.setBounds(110,30,60,20);
        add(tiLb);
        tfLb = new JLabel("Tiempo final:");
        tfLb.setBounds(110,50,60,20);
        add(tfLb);
        dLb = new JLabel("Descripcion/nombre:");
        dLb.setBounds(110,70,60,20);
        add(dLb);
        aLb = new JLabel("Personal Asignado:");
        aLb.setBounds(110,90,60,20);
        add(aLb);
        cLb = new JLabel("Color:");
        cLb.setBounds(110,110,60,20);
        add(aLb);
        codLb = new JLabel("Color:");
        codLb.setBounds(110,130,60,20);
        add(codLb);
        progLb = new JLabel("Progreso:");
        progLb.setBounds(110,150,60,20);
        add(progLb);
        
        tiTf   = new JTextField();
        tiTf.setBounds(170,30,50,20);
        add(tiTf);
        tfTf   = new JTextField();
        tfTf.setBounds(170,50,50,20);
        add(tfTf);
        dTf    = new JTextField();
        dTf.setBounds(170,70,50,20);
        add(dTf);
        aTf    = new JTextField();
        aTf.setBounds(170,90,50,20);
        add(aTf);
        cTf    = new JTextField();
        cTf.setBounds(170,110,50,20);
        add(cTf);
        codTf  = new JTextField();
        codTf.setBounds(170,130,50,20);
        add(codTf);
        progTf = new JTextField();
        progTf.setBounds(170,150,50,20);
        add(progTf);

        guardar =  new JButton("Guardar");
        guardar.setBounds(50,150,100,20);
        guardar.addActionListener(this);
        add(guardar);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(150,150,100,20);
        cancelar.addActionListener(this);
        add(cancelar);
        
        
        setBounds(10,10,300,200);
        setVisible(true);
        //this.dispose();
        return result;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar) {
            ti = Integer.parseInt( tiTf.getText());
            tf = Integer.parseInt( tfTf.getText() );
            d = dTf.getText(); 
            a = aTf.getText();
            c = cTf.getText();
            cod = Integer.parseInt( codTf.getText() );
            prog = Float.parseFloat( progTf.getText() );
          
            this.dispose();
        }
        if (e.getSource()==cancelar) {
            this.dispose();
        }    
  }
}
