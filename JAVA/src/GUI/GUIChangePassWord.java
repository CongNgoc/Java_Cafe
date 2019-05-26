/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.userBUS;
import DAO.dBConnection;
import DTO.userDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HoiNguyen
 */
public class GUIChangePassWord extends javax.swing.JFrame {

    /**
     * Creates new form GUIChangePassWord
     */
    private String idUser;
    private String pass;
    dBConnection db = new dBConnection();
    public GUIChangePassWord() {
        initComponents();
        loadThongTin(GUILogin.IdNhanVien);
        
    }
    
    public void loadThongTin(String id){
        try {
            ArrayList<userDTO> list = userBUS.userId(id);
            for (int i = 0; i < list.size(); i++) {
                idUser = list.get(i).getId();
                pass = list.get(i).getPassword();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIThongTinNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        lbPassOld = new javax.swing.JLabel();
        lbPassNew1 = new javax.swing.JLabel();
        lbPassNew2 = new javax.swing.JLabel();
        btUpdated = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        jPassOld = new javax.swing.JPasswordField();
        jPassNew1 = new javax.swing.JPasswordField();
        jPassNew2 = new javax.swing.JPasswordField();
        lbMsg = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thay đổi mật khẩu");

        lbPassOld.setText("Mật khẩu cũ");

        lbPassNew1.setText("Mật khẩu mới");

        lbPassNew2.setText("Nhập lại");

        btUpdated.setText("Cập nhật");
        btUpdated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdatedActionPerformed(evt);
            }
        });

        btCancel.setText("Hủy");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPassNew1)
                            .addComponent(lbPassNew2)
                            .addComponent(lbPassOld))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPassOld, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jPassNew1)
                            .addComponent(jPassNew2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btUpdated)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(lbMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassOld)
                    .addComponent(jPassOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassNew1)
                    .addComponent(jPassNew1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassNew2)
                    .addComponent(jPassNew2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btUpdated)
                    .addComponent(btCancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btUpdatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdatedActionPerformed
        boolean flag1 = true;
        boolean flag2 = true;
        if(!jPassOld.getText().equals(pass)){
            flag1 = false;
            lbMsg.setText("Mật khẩu cũ không đúng.");
        }else{
            lbMsg.setText("");
            flag1 = true;
        }
        
        if(flag1 == true)
        {
            if(!jPassNew1.getText().equals(jPassNew2.getText()))
            {
                lbMsg.setText("Mật khẩu mới không khớp.");
                flag2 = false;
            }else{
                lbMsg.setText("");
                flag2 = true;
            }
        }
        
        if(flag1 == true && flag2 == true){
            String query = "UPDATE `user` "
                    + "SET `password`='" + jPassNew1.getText() + "' WHERE id = '" + idUser + "'";
        try {
                db.open();
                if (db.excuteUpdate(query) == 1) {
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại");
                }
                db.close();
            } catch (SQLException | ClassNotFoundException ex) {
                db.displayError((SQLException) ex);
            }
        }
    }//GEN-LAST:event_btUpdatedActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

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
            java.util.logging.Logger.getLogger(GUIChangePassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIChangePassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIChangePassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIChangePassWord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIChangePassWord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btUpdated;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPassNew1;
    private javax.swing.JPasswordField jPassNew2;
    private javax.swing.JPasswordField jPassOld;
    private javax.swing.JLabel lbMsg;
    private javax.swing.JLabel lbPassNew1;
    private javax.swing.JLabel lbPassNew2;
    private javax.swing.JLabel lbPassOld;
    // End of variables declaration//GEN-END:variables
}
