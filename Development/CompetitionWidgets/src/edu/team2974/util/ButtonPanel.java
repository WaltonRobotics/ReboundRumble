/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ButtonPanel.java
 *
 * Created on Mar 10, 2012, 4:30:27 PM
 */
package edu.team2974.util;

import javax.swing.JButton;

/**
 *
 * @author Chris
 */
public class ButtonPanel extends javax.swing.JPanel {

    /** Creates new form ButtonPanel */
    public ButtonPanel() {
        initComponents();
    }
    
    public JButton getButton() {
        return jButton1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        DashboardLogger.getInstance().logMessage("Button Clicked!");
    }//GEN-LAST:event_jButton1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
