/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import core.Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author robert
 */
public class MainGame extends javax.swing.JFrame {
    
    /**
     * Creates new form MainGame
     */
    
    public MainGame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void setPlayerItems(String string){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                
                @Override
                public void run() {
                    playerItems.setText(string);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setText(String string){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                
                @Override
                public void run() {
                    log.append(string);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        playerItems = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        playerItems.setColumns(20);
        playerItems.setRows(5);
        playerItems.setBorder(javax.swing.BorderFactory.createTitledBorder("Player Inventory"));
        jScrollPane3.setViewportView(playerItems);

        jPanel1.add(jScrollPane3);

        jPanel2.add(jPanel1);

        getContentPane().add(jPanel2);

        jPanel4.setLayout(new java.awt.GridLayout(0, 1));

        log.setColumns(20);
        log.setRows(5);
        log.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
        jScrollPane2.setViewportView(log);

        jPanel4.add(jScrollPane2);

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea log;
    private javax.swing.JTextArea playerItems;
    // End of variables declaration//GEN-END:variables
}
