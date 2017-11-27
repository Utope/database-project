/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Game;
import database.Repository;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author robert
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void closeWindow(){
        this.setVisible(false);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        titlejLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        usernamejLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        passwordjLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        createAccountButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(4, 0));

        titlejLabel.setText("~~~ Game Login ~~~");
        jPanel5.add(titlejLabel);

        getContentPane().add(jPanel5);

        usernamejLabel.setText("username");
        jPanel1.add(usernamejLabel);

        usernameTextField.setColumns(10);
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(usernameTextField);

        getContentPane().add(jPanel1);

        passwordjLabel.setText("password");
        jPanel3.add(passwordjLabel);

        passwordTextField.setColumns(10);
        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });
        jPanel3.add(passwordTextField);

        getContentPane().add(jPanel3);

        loginButton.setText("login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel4.add(loginButton);

        createAccountButton.setText("Create Account");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });
        jPanel4.add(createAccountButton);

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed
        
        String username = null;
        String password = null;
        
        username = JOptionPane.showInputDialog("Input Username");
        
         Connection con = Repository.Instance().getConn();
         Statement stmt = null;
         String query = "select username from DatabaseGame.player where username=\"" + username + "\""; 
         try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                username = null;
            }
        
        } catch (SQLException e ) {
       
        }
         
        if(username == null){
             JOptionPane.showMessageDialog(
                        null,
                        "Username Already Exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
             this.createAccountButtonActionPerformed(null);
        }else{
            password = JOptionPane.showInputDialog("Input password");
            stmt = null;
            query = "insert into DatabaseGame.player (username, password) VALUES (" + "\"" + username + "\"" + ", " + "\"" + password + "\"" + ")"; 
            
            
            try {
            stmt = con.createStatement();
            int count = stmt.executeUpdate(query);
            
            if(count > 0){
                JOptionPane.showMessageDialog(
                        null,
                        "Creation Success!");
            }
        
        } catch (SQLException e ) {
       
        }
            
            
            
        }
        
    }//GEN-LAST:event_createAccountButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
              String username = usernameTextField.getText();
              String password = passwordTextField.getText();
                
               if(!Repository.Instance().doPlayerCredentialsExits(username, password)){
                   JOptionPane.showMessageDialog(
                        null,
                        "Login error", "Error",
                        JOptionPane.ERROR_MESSAGE);
               }else{
                JOptionPane.showMessageDialog(
                        null,
                        "Login Success!");
                Thread thread = new Thread(new Game(username, password));
                thread.start();
            }               
                  closeWindow();
            
    }//GEN-LAST:event_loginButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createAccountButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JLabel passwordjLabel;
    private javax.swing.JLabel titlejLabel;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JLabel usernamejLabel;
    // End of variables declaration//GEN-END:variables
    
    public static void main(String[] args){
         
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
}
