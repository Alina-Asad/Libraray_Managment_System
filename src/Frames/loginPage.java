/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.DBconnection.con;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class loginPage extends javax.swing.JFrame {

    public loginPage() {
        initComponents();
    }

    //validation
    public boolean validateLogin() {
        String name = text_username.getText();
        String pwd = text_password.getText();

        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter username!!!");
            return false;
        }
        if (pwd.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter Password!!!");
            return false;
        }
        return true;
    }
    //check whether the given credentials are correct or not!!!!
    //verify creds method

    public void login() {
        String name = text_username.getText();
        String pwd = text_password.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");

            PreparedStatement pst = con.prepareStatement("select * from users where name = ? and password = ?  ");
           //setting values
            pst.setString(1, name);
            pst.setString(2, pwd);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successfull :) ");
                homePage home = new homePage();
                home.setVisible(true);
                this.dispose();//close the currunt screen
            } else {
                JOptionPane.showMessageDialog(this, "Wrong Credits Added!! ");
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        text_password = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        text_username = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1523, 828));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Riphah International Library ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 430, 50));

        jLabel4.setFont(new java.awt.Font("Stencil", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Welcome to ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 230, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/library-3.png.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 810, 610));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 820));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 51));
        jLabel5.setText("x");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 40, 40));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Login To Your Account");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 260, 60));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Secure_50px.png"))); // NOI18N
        jLabel8.setToolTipText("");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 50, 50));

        text_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(text_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 200, 40));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Password");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 130, 60));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 50, 50));

        text_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_usernameFocusLost(evt);
            }
        });
        text_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(text_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 190, 40));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Username");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 130, 60));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.setAutoscrolls(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 90, 50));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Signup ");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 100, 50));

        jLabel14.setFont(new java.awt.Font("Sitka Display", 1, 25)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Login Page ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 210, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 670, 840));

        setSize(new java.awt.Dimension(1523, 839));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_passwordActionPerformed

    private void text_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_usernameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);


    }//GEN-LAST:event_jLabel5MouseClicked

    private void text_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_usernameFocusLost

    }//GEN-LAST:event_text_usernameFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validateLogin()) {
            login();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        Signuppage page = new Signuppage();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2MouseClicked

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
            java.util.logging.Logger.getLogger(loginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField text_password;
    private javax.swing.JTextField text_username;
    // End of variables declaration//GEN-END:variables

}
