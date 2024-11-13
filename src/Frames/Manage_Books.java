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
public final class Manage_Books extends javax.swing.JFrame {

    /**
     */
    String author, book_name;
    int book_Id, quantity;
    DefaultTableModel Model;//this model will set values in the table>>>>>>

    public Manage_Books() {
        initComponents();
        setBookDetailstoTable();

    }
    //to set the book detail into thye table

    public void setBookDetailstoTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from book_details");
            while (rs.next()) {
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");

                Object[] obj = {bookId, bookName, author, quantity};
                Model = (DefaultTableModel) tbl_bookDetails.getModel();
                Model.addRow(obj);//this model will represent the table and it will take object..... so that is why we created object
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//to addd books to book details table

    public boolean addBook() {
        boolean isAdded = false;

        book_Id = Integer.parseInt(text_bookId.getText());
//we are using get function which takes string values only so, we need to use integer par
        quantity = Integer.parseInt(text_quantity.getText());
        author = text_authorName.getText();
        book_name = text_bookName.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            String sql = "Insert into book_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, book_Id);
            pst.setString(2, book_name);
            pst.setString(3, author);
            pst.setInt(4, quantity);
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
//to update books 

    public boolean updateBook() {
        boolean isUpdated = false;

        book_Id = Integer.parseInt(text_bookId.getText());
        quantity = Integer.parseInt(text_quantity.getText());
        author = text_authorName.getText();
        book_name = text_bookName.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");

            String sql = "Update book_details set book_name = ?, author = ?, quantity = ? where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(4, book_Id);
            pst.setString(1, book_name);
            pst.setString(2, author);
            pst.setInt(3, quantity);

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

    public boolean deleteBook() {

        boolean isDeleted = false;
        book_Id = Integer.parseInt(text_bookId.getText());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");

            String sql = "Delete from book_details where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, book_Id);
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

        DefaultTableModel Model = (DefaultTableModel) tbl_bookDetails.getModel(); ///we type casted the table model because the error 
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
        text_quantity = new app.bolivia.swing.JCTextField();
        text_bookId = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        text_bookName = new app.bolivia.swing.JCTextField();
        text_authorName = new app.bolivia.swing.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojerusan.RSTableMetro();
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
        jLabel9.setText("Enter Book name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 170, 50));

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
        jLabel13.setText("Enter Book id");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 160, 60));

        text_quantity.setBackground(new java.awt.Color(204, 204, 204));
        text_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_quantity.setPlaceholder("Enter quantity here...");
        text_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_quantityActionPerformed(evt);
            }
        });
        jPanel1.add(text_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 330, -1));

        text_bookId.setBackground(new java.awt.Color(204, 204, 204));
        text_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_bookId.setPlaceholder("Enter bookID here...");
        text_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bookIdActionPerformed(evt);
            }
        });
        jPanel1.add(text_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 310, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Enter Auther name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 190, 50));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 30, 50));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Enter Quantity");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 150, 50));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 30, 50));

        text_bookName.setBackground(new java.awt.Color(204, 204, 204));
        text_bookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_bookName.setPlaceholder("Enter Book name here...");
        text_bookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bookNameActionPerformed(evt);
            }
        });
        jPanel1.add(text_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 310, -1));

        text_authorName.setBackground(new java.awt.Color(204, 204, 204));
        text_authorName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_authorName.setPlaceholder("Enter auther's name here...");
        text_authorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_authorNameActionPerformed(evt);
            }
        });
        jPanel1.add(text_authorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 320, -1));

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
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 50, -1));

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel17.setText(" Manage Books");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 350, 60));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        tbl_bookDetails.setColorBordeFilas(new java.awt.Color(0, 153, 153));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_bookDetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_bookDetails.setColorSelBackgound(new java.awt.Color(153, 153, 153));
        tbl_bookDetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        tbl_bookDetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic Light", 1, 20)); // NOI18N
        tbl_bookDetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_bookDetails.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_bookDetails.setRowHeight(30);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 640, 220));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 420, 5));

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

    private void text_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_quantityActionPerformed

    private void text_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_bookIdActionPerformed

    private void text_bookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bookNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_bookNameActionPerformed

    private void text_authorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_authorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_authorNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (deleteBook() == true) {
            JOptionPane.showMessageDialog(this, "Book Deleted Successfully :) ");
            clearTable();
            setBookDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Book not Deleted :( ");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (addBook() == true) {
            JOptionPane.showMessageDialog(this, "Book added Successfully :) ");
            clearTable();
            setBookDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Book not Added :( ");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (updateBook() == true) {
            JOptionPane.showMessageDialog(this, "Book Updated Successfully :) ");
            clearTable();
            setBookDetailstoTable();
        } else {
            JOptionPane.showMessageDialog(this, "Book not Updated :( ");
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

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel Model = tbl_bookDetails.getModel();//we can get the table vallues displayed onto  the display field
        text_bookId.setText(Model.getValueAt(rowNo, 0).toString());//tostring is used because we have to use string but we used '0'
        text_bookName.setText(Model.getValueAt(rowNo, 1).toString());
        text_authorName.setText(Model.getValueAt(rowNo, 2).toString());
        text_quantity.setText(Model.getValueAt(rowNo, 3).toString());

// TODO add your handling code here:
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Books().setVisible(true);
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
    private rojerusan.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField text_authorName;
    private app.bolivia.swing.JCTextField text_bookId;
    private app.bolivia.swing.JCTextField text_bookName;
    private app.bolivia.swing.JCTextField text_quantity;
    // End of variables declaration//GEN-END:variables
}
