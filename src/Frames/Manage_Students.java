/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author USER
 */
public final class Manage_Students extends javax.swing.JFrame {

    /**
     */
    String course, dept, name;
    int Students_Id;
    DefaultTableModel Model;//this model will set values in the table>>>>>>

    public Manage_Students() {
        initComponents();
        setStudentsDetailstoTable();

    }
    //to set the book detail into thye table

    public void setStudentsDetailstoTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from students_details");
            while (rs.next()) {
                String Students_Id = rs.getString("student_id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                String dept = rs.getString("branch");

                Object[] obj = {Students_Id, name, course, dept};
                Model = (DefaultTableModel) tbl_StudentsDetails.getModel();
                Model.addRow(obj);//this model will represent the table and it will take object..... so that is why we created object
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//to addd students to students details table

    public boolean addStudents() {
        boolean isAdded = false;

        Students_Id = Integer.parseInt(text_studentId.getText());
        name = text_studentName.getText();
        course = text_studentCourse.getSelectedItem().toString();
        dept = text_Dept.getSelectedItem().toString();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            String sql = "Insert into students_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Students_Id);
            pst.setString(2, name);
            pst.setString(3, course);
            pst.setString(4, dept);
            int rowCount = pst.executeUpdate();//non select method and it will return an updated row count

            if (rowCount > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAdded;
    }
//to update students

    public boolean updateStudent() {
        boolean isUpdated = false;

        Students_Id = Integer.parseInt(text_studentId.getText());
        name = text_studentName.getText();
        course = text_studentCourse.getSelectedItem().toString();
        dept = text_Dept.getSelectedItem().toString();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");

            String sql = "Update students_details set name = ?, course = ?, branch = ? where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, course);
            pst.setString(3, dept);
            pst.setInt(4, Students_Id);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;

    }

    public boolean deleteStudents() {

        boolean isDeleted = false;
        Students_Id = Integer.parseInt(text_studentId.getText());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");

            String sql = "Delete from students_details where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Students_Id);
            int rowCount = pst.executeUpdate();
            //to get updated row count 
            if (rowCount > 0) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }

        } catch (Exception e) {

        }

        return isDeleted;
    }

//we created this method to have the values added to show up on the table it can be done simply by calling the 
//other method but it will copy the whole data frrom database and then will show it... so, we don't want that forthatreason we are creating a method
    public void clearTable() {

        DefaultTableModel Model = (DefaultTableModel) tbl_StudentsDetails.getModel(); ///we type casted the table model because the error 
//said we can not use tablemodel with default table model
        Model.setRowCount(0);//it will clear the table so that only data added will be dispalyed, remove all rows

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
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        text_studentId = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        text_studentName = new app.bolivia.swing.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        text_Dept = new javax.swing.JComboBox<>();
        text_studentCourse = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_StudentsDetails = new rojerusan.RSTableMetro();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Enter Student name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 200, 50));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jLabel8.setToolTipText("");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 30, 50));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 40, 50));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Enter Student id");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 180, 60));

        text_studentId.setBackground(new java.awt.Color(204, 204, 204));
        text_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_studentId.setPlaceholder("Enter StudentsID here...");
        text_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentIdActionPerformed(evt);
            }
        });
        jPanel1.add(text_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 330, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Enter Student Course");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 210, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 30, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Enter Department");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 180, 50));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 30, 50));

        text_studentName.setBackground(new java.awt.Color(204, 204, 204));
        text_studentName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_studentName.setPlaceholder("Enter Student's Name here...");
        text_studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentNameActionPerformed(evt);
            }
        });
        jPanel1.add(text_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 330, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton1.setText("Delete");
        jButton1.setAutoscrolls(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 110, 50));

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton2.setText("Add Now");
        jButton2.setAutoscrolls(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 110, 50));

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.setAutoscrolls(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, 110, 50));

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        text_Dept.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        text_Dept.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SE ", "CS", "DPT", "MLT", "CA", " " }));
        text_Dept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_DeptActionPerformed(evt);
            }
        });
        jPanel1.add(text_Dept, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, 330, 30));

        text_studentCourse.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        text_studentCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BS", "MS", "PHD" }));
        text_studentCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentCourseActionPerformed(evt);
            }
        });
        jPanel1.add(text_studentCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 330, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("x");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 50, -1));

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel17.setText(" Manage Students");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 450, 90));

        tbl_StudentsDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Department"
            }
        ));
        tbl_StudentsDetails.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        tbl_StudentsDetails.setColorBordeFilas(new java.awt.Color(0, 153, 153));
        tbl_StudentsDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_StudentsDetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_StudentsDetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_StudentsDetails.setColorSelBackgound(new java.awt.Color(153, 153, 153));
        tbl_StudentsDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        tbl_StudentsDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic Light", 1, 20)); // NOI18N
        tbl_StudentsDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_StudentsDetails.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_StudentsDetails.setRowHeight(30);
        tbl_StudentsDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentsDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_StudentsDetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 590, 220));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 480, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 1150, 830));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void text_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentIdActionPerformed

    private void text_studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (deleteStudents() == true) {
            JOptionPane.showMessageDialog(this, "Student Record Deleted Successfully :) ");
            clearTable();
            setStudentsDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Students Record not Deleted :( ");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (addStudents() == true) {
            JOptionPane.showMessageDialog(this, "Student record Updated Successfully :) ");
            clearTable();
            setStudentsDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Student Record not updated :( ");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateStudent() == true) {
            JOptionPane.showMessageDialog(this, "Student Record Updated Successfully :) ");
            clearTable();
            setStudentsDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Student Record not Updated :( ");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        homePage page = new homePage();
        page.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void tbl_StudentsDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentsDetailsMouseClicked

        int rowNo = tbl_StudentsDetails.getSelectedRow();
        TableModel Model = tbl_StudentsDetails.getModel();//we can get the table vallues displayed onto  the display field

        text_studentId.setText(Model.getValueAt(rowNo, 0).toString());//tostring is used because we have to use string but we used '0'
        text_studentName.setText(Model.getValueAt(rowNo, 1).toString());
        text_studentCourse.setSelectedItem(Model.getValueAt(rowNo, 2).toString());
        text_Dept.setSelectedItem(Model.getValueAt(rowNo, 3).toString());

// TODO add your handling code here:
    }//GEN-LAST:event_tbl_StudentsDetailsMouseClicked

    private void text_DeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_DeptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_DeptActionPerformed

    private void text_studentCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentCourseActionPerformed

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
            java.util.logging.Logger.getLogger(Manage_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Students().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro tbl_StudentsDetails;
    private javax.swing.JComboBox<String> text_Dept;
    private javax.swing.JComboBox<String> text_studentCourse;
    private app.bolivia.swing.JCTextField text_studentId;
    private app.bolivia.swing.JCTextField text_studentName;
    // End of variables declaration//GEN-END:variables
}
