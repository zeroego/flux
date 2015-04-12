
package Presentacion;

import Conexion.HibernateUtil;
import Negocio.*;
import Entidad.*;
import daos.impl.UsuarioDaoImpl;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrmLogin extends javax.swing.JFrame {
    
    //algoritmos
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    
    String id[]=new String[50];
    static int intContador;
    int intentos=0;
    public String codigo;
    String usu,pass;
    static Connection conn=null;
    static ResultSet rs=null;
    static ResultSet rs1=null;
    DefaultTableModel dtm=new DefaultTableModel();
    
    //single instance for the HibernateUtil Class
    //private HibernateUtil hibernateUtil;
        
    public FrmLogin() {
        initComponents();
        //cargarComboCargo();
        generarAleatorio();
        lblIntentos.setVisible(false);
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(586, 307);
        
        /*
        new Thread(){
            @Override
            public void run(){
                System.out.println("stating Hibernate conexion...");
                HibernateUtil.getInstance();
                System.out.println("stating Hibernate conexion... DONE");
            }
        }.start();
        */
        
    }
//-----------------------------------------------------------------------------------------------
//--------------------------------------METODOS--------------------------------------------------
//-----------------------------------------------------------------------------------------------
    void cargarComboCargo(){
       ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
       ArrayList<ClsEntidadTipoUsuario> operacion=tipousuarios.listarTipoUsuario();
       Iterator iterator=operacion.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboCargo.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadTipoUsuario TipoUsuario=new ClsEntidadTipoUsuario();
           TipoUsuario=(ClsEntidadTipoUsuario) iterator.next();
           id[intContador]=TipoUsuario.getStrIdTipoUsuario();
           fila[0]=TipoUsuario.getStrIdTipoUsuario();
           fila[1]=TipoUsuario.getStrDescripcionTipoUsuario();

           //DefaultComboBoxModel.addElement(Tipo.getStrIdTipo()+ " " + Tipo.getStrNomTipo());
           DefaultComboBoxModel.addElement(TipoUsuario.getStrDescripcionTipoUsuario());
           intContador++;
              
       }
       cboCargo.setModel(DefaultComboBoxModel);
   }
 
    void generarAleatorio(){
       int n=10;  //numeros aleatorios
       int k=n;  //auxiliar;
        int[] numeros=new int[n];
        int[] resultado=new int[n];
        Random rnd=new Random();
        int res;
        
        
        //Rellenamos la matriz
        for(int i=0;i<n;i++){
            //numeros[i]=i+1; //----Empieza en 1
            numeros[i]=numeros[i]+i; //----Empieza en 0
        }
        
        for(int i=0;i<n;i++){
            res=rnd.nextInt(k);            
            resultado[i]=numeros[res];
            numeros[res]=numeros[k-1];
            k--;
            
        }

        
        btnD1.setText(String.valueOf(resultado[0]));
        btnD2.setText(String.valueOf(resultado[1]));
        btnD3.setText(String.valueOf(resultado[2]));
        btnD4.setText(String.valueOf(resultado[3]));
        btnD5.setText(String.valueOf(resultado[4]));
        btnD6.setText(String.valueOf(resultado[5]));
        btnD7.setText(String.valueOf(resultado[6]));
        btnD8.setText(String.valueOf(resultado[7]));
        btnD9.setText(String.valueOf(resultado[8]));
        btnD0.setText(String.valueOf(resultado[9]));
//        System.out.println("El resultado de la matriz es:");
//        for(int i=0;i<n;i++){
//            System.out.println(resultado[i]);
//        }
    }
    
     private static String toHexadecimal(byte[] digest)
    {
        String hash = "";
        for(byte aux : digest) 
        {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    public static String getStringMessageDigest(String message, String algorithm)
    {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        }
        catch (NoSuchAlgorithmException ex) 
        {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        lblIntentos = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnD9 = new javax.swing.JButton();
        btnD8 = new javax.swing.JButton();
        btnD7 = new javax.swing.JButton();
        btnD0 = new javax.swing.JButton();
        btnD4 = new javax.swing.JButton();
        btnD5 = new javax.swing.JButton();
        btnD2 = new javax.swing.JButton();
        btnD3 = new javax.swing.JButton();
        btnD6 = new javax.swing.JButton();
        btnD1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cargo:");

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCargo.setNextFocusableComponent(btnIniciar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");
        setFocusTraversalPolicyProvider(true);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ACCESO AL SISTEMA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 0, 194, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(340, 60, 43, 15);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Constraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(310, 90, 70, 15);

        txtUsuario.setNextFocusableComponent(txtContraseña);
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(390, 50, 160, 30);

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iniciar.png"))); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar);
        btnIniciar.setBounds(340, 170, 100, 75);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salir.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(450, 170, 100, 75);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Security.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 50, 134, 170);

        txtContraseña.setNextFocusableComponent(btnIniciar);
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        getContentPane().add(txtContraseña);
        txtContraseña.setBounds(390, 90, 160, 30);
        getContentPane().add(lblIntentos);
        lblIntentos.setBounds(478, 11, 11, 18);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(200, 160, 80, 33);

        btnD9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD9ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD9);
        btnD9.setBounds(240, 130, 40, 33);

        btnD8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD8ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD8);
        btnD8.setBounds(200, 130, 40, 33);

        btnD7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD7ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD7);
        btnD7.setBounds(160, 130, 40, 33);

        btnD0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD0ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD0);
        btnD0.setBounds(160, 160, 40, 33);

        btnD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD4ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD4);
        btnD4.setBounds(160, 100, 40, 33);

        btnD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD5ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD5);
        btnD5.setBounds(200, 100, 40, 33);

        btnD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD2);
        btnD2.setBounds(200, 70, 40, 33);

        btnD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD3);
        btnD3.setBounds(240, 70, 40, 33);

        btnD6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD6ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD6);
        btnD6.setBounds(240, 100, 40, 33);

        btnD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnD1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnD1);
        btnD1.setBounds(160, 70, 40, 33);

        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 40, 550, 220);

        setSize(new java.awt.Dimension(586, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        txtContraseña.requestFocus();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        this.logIn();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD9ActionPerformed
        String btnueve = txtContraseña.getText() + btnD9.getText();
        txtContraseña.setText(btnueve);
    }//GEN-LAST:event_btnD9ActionPerformed

    private void btnD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD8ActionPerformed
        String btnocho = txtContraseña.getText() + btnD8.getText();
        txtContraseña.setText(btnocho);
    }//GEN-LAST:event_btnD8ActionPerformed

    private void btnD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD7ActionPerformed
        String btnsiete = txtContraseña.getText() + btnD7.getText();
        txtContraseña.setText(btnsiete);
    }//GEN-LAST:event_btnD7ActionPerformed

    private void btnD0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD0ActionPerformed
        String btncero = txtContraseña.getText() + btnD0.getText();
        txtContraseña.setText(btncero);
    }//GEN-LAST:event_btnD0ActionPerformed

    private void btnD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD4ActionPerformed
        String btncuatro = txtContraseña.getText() + btnD4.getText();
        txtContraseña.setText(btncuatro);
    }//GEN-LAST:event_btnD4ActionPerformed

    private void btnD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD5ActionPerformed
        String btncinco = txtContraseña.getText() + btnD5.getText();
        txtContraseña.setText(btncinco);
    }//GEN-LAST:event_btnD5ActionPerformed

    private void btnD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD2ActionPerformed
        String btndos = txtContraseña.getText() + btnD2.getText();
        txtContraseña.setText(btndos);
    }//GEN-LAST:event_btnD2ActionPerformed

    private void btnD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD3ActionPerformed
        String btntres = txtContraseña.getText() + btnD3.getText();
        txtContraseña.setText(btntres);
    }//GEN-LAST:event_btnD3ActionPerformed

    private void btnD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD6ActionPerformed
        String btnsies = txtContraseña.getText() + btnD6.getText();
        txtContraseña.setText(btnsies);
    }//GEN-LAST:event_btnD6ActionPerformed

    private void btnD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnD1ActionPerformed
        String btnuno = txtContraseña.getText()+ btnD1.getText();
        txtContraseña.setText(btnuno);
        
    }//GEN-LAST:event_btnD1ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       txtContraseña.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
        System.out.println("KEY");
        this.logIn();
    }//GEN-LAST:event_txtContraseñaActionPerformed
    
    private void logIn(){
        String algorithm="SHA-512";  
        String resultado1;//,resultado2;
        String usuario=null;
        String contraseña=null;
        String descripcion=null;
        int sen=1;int tu=1;
        
        usuario=txtUsuario.getText();
        contraseña=txtContraseña.getText();
        descripcion= String.valueOf(cboCargo.getSelectedItem());
        resultado1=getStringMessageDigest(contraseña,algorithm);   
       
        boolean encuentra=false;
        
        if(usuario.length()==0 ||contraseña.length()==0) {
            JOptionPane.showMessageDialog(this, "!Ingrese Usuario y/o Constraseña!");
            txtUsuario.setText("");
            txtContraseña.setText("");
            txtUsuario.requestFocus();
            intentos=intentos+1;
            lblIntentos.setText(Integer.toString(intentos));

        }
        else 
        {
            try{
               UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
               ClsEntidadEmpleadoHib empleado = usuarioDao.login(usuario, resultado1);
                if( empleado != null ){
                    FrmPrincipal mdi=new FrmPrincipal();
                    mdi.setEmpleado(empleado);
                    mdi.setVisible(true);
                    
                    this.dispose();                    
                    mdi.strUsuario=empleado.getUsuario();
                    mdi.strIdEmpleado=empleado.getIdEmpleado().toString();
                    mdi.strNombreEmpleado=empleado.getNombre() + " " + empleado.getApellido();
                    mdi.strTipo=empleado.getIdTipoUsuario().getDescripcion();
                    mdi.intEstado=1; 
                    
                    
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        if(intentos==3){
            this.dispose();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    //SwingUtilities.updateComponentTreeUI(login);
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("stating Hibernate conexion...");
                HibernateUtil.getInstance();
                System.out.println("stating Hibernate conexion... DONE");
                
                Presentacion.FrmLogin login = new Presentacion.FrmLogin();
                login.setVisible(true);
            }
        });

        /*
        new Thread() {
            @Override
            public void run() {
                System.out.println("stating Hibernate conexion...");
                HibernateUtil.getInstance();
                System.out.println("stating Hibernate conexion... DONE");
            }
        }.start();
                */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnD0;
    private javax.swing.JButton btnD1;
    private javax.swing.JButton btnD2;
    private javax.swing.JButton btnD3;
    private javax.swing.JButton btnD4;
    private javax.swing.JButton btnD5;
    private javax.swing.JButton btnD6;
    private javax.swing.JButton btnD7;
    private javax.swing.JButton btnD8;
    private javax.swing.JButton btnD9;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox cboCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblIntentos;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
