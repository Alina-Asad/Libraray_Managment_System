/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Issue_books extends javax.swing.JFrame {

    /**
     * Creates new form Issue_books
     */
    public Issue_books() {
        initComponents();
    }
//to fetch the details of books from database and display it to books details panel

    public void getBookDetails() {
        int bookId = Integer.parseInt(text_bookId.getText());
        try {
            //we are writing code to communicate with database and its rreturning a connection object... we also wrote this code in a separate class
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ? ");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            //we don't need to iterate on design that's whyy we used if and not while 
            //if record is present then loop will execute if not then else will
            if (rs.next()) {
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
                lbl_bookError.setText(" ");
            } else {
                lbl_bookError.setText("Invalid Book Id!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStudentDetails() {
        int Student_Id = Integer.parseInt(text_studentId.getText());
        try {
            //we are writing code to communicate with database and its rreturning a connection object... we also wrote this code in a separate class
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            PreparedStatement pst = con.prepareStatement("select * from students_Details where student_id = ? ");
            pst.setInt(1, Student_Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_Course.setText(rs.getString("course"));
                lbl_Dept.setText(rs.getString("branch"));
                lbl_studentError.setText(" ");
            } else {
                lbl_studentError.setText("Invalid Student Id!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //insert issue book details into database table 
    public boolean issueBook() {
        boolean isIssued = false;
        int book_Id = Integer.parseInt(text_bookId.getText());
        int Student_Id = Integer.parseInt(text_studentId.getText());
        String bookName = lbl_bookName.getText();
        String studentName = lbl_studentName.getText();

        Date uIssueDate = Issue_date.getDatoFecha();
        Date uDueDate = Due_date.getDatoFecha();//this date is an alag package and database uses sql date so we'll convert this date to use in that 

        Long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();

        java.sql.Date sIssueDate = new java.sql.Date(l1);/*in here in () weneed to provide 'long' value so we are getting it*/
        java.sql.Date sDueDate = new java.sql.Date(l2);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            String sql = "insert into issue_book_details(book_id,book_name,student_id, student_name, issue_date, due_date, status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, book_Id);
            pst.setString(2, bookName);
            pst.setInt(3, Student_Id);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "Pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isIssued = true;
            } else {
                isIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }
    //when book is issued quantity of book should be decrementedd...so  update count is created to update count

    public void updateBookCount() {

        int book_Id = Integer.parseInt(text_bookId.getText());
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            String sql = "update  book_details set  quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, book_Id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book quantity updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));//integer.to String is a method to convert int intro string...
            } else {
                JOptionPane.showMessageDialog(this, "Book quantity not updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //we have to check if the student already have the book he/she is asking for then we don,t have to sgain give tht book to student

    public boolean isAlreadyIssued() {

        boolean isAlreadyIssued = false;
        int book_Id = Integer.parseInt(text_bookId.getText());
        int Student_Id = Integer.parseInt(text_studentId.getText());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "43593");
            String sql = "select  * from issue_book_details where book_id = ? and student_id = ? and status = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, book_Id);
            pst.setInt(2, Student_Id);
            pst.setString(3, "Pending");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAlreadyIssued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_Dept = new javax.swing.JLabel();
        lbl_Course = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_bookError2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        text_bookId = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        text_studentId = new app.bolivia.swing.JCTextField();
        Issue_date = new rojeru_san.componentes.RSDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Due_date = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_main.setBackground(new java.awt.Color(255, 255, 255));
        Panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 320, 5));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Department : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 130, 40));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 160, 40));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Name : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, 40));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student Course :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 160, 40));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student Id : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 130, 40));

        lbl_studentId.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel1.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 150, 40));

        lbl_Dept.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel1.add(lbl_Dept, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 170, 40));

        lbl_Course.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel1.add(lbl_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 170, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel3.setText("Students Details");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 310, 110));

        lbl_studentError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 210, 40));

        Panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 400, 820));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText("  Books Details");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 310, 110));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 310, 5));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel2.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 180, 40));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name : ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 130, 40));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Auther : ");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 90, 40));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book Id : ");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 100, 40));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel2.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 220, 40));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel2.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 180, 40));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanel2.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 180, 40));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quantity : ");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 110, 40));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 210, 40));

        lbl_bookError2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        lbl_bookError2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_bookError2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 210, 40));

        Panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 830));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/Icons8-Windows-8-Printing-Books.48.png"))); // NOI18N
        jLabel1.setText(" Issue Book");
        Panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, 310, 70));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        Panel_main.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 320, 40));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        Panel_main.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 180, -1, -1));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 35)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("x");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 50, -1));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText(" Book id :");
        Panel_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 140, 50));

        text_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        text_bookId.setPlaceholder("Enter book ID here..");
        text_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_bookIdFocusLost(evt);
            }
        });
        text_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bookIdActionPerformed(evt);
            }
        });
        Panel_main.add(text_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 270, 250, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText(" Issue Date :");
        Panel_main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 470, 150, 40));

        text_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        text_studentId.setPlaceholder("Enter Student ID here...");
        text_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_studentIdFocusLost(evt);
            }
        });
        text_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentIdActionPerformed(evt);
            }
        });
        Panel_main.add(text_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 360, 250, -1));

        Issue_date.setColorBackground(new java.awt.Color(102, 102, 102));
        Issue_date.setColorButtonHover(new java.awt.Color(204, 204, 204));
        Issue_date.setColorDiaActual(new java.awt.Color(102, 102, 102));
        Issue_date.setColorForeground(new java.awt.Color(0, 0, 0));
        Issue_date.setPlaceholder("Choose Date..");
        Panel_main.add(Issue_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 470, 260, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText(" Student Id :");
        Panel_main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 150, 40));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText(" Return Date :");
        Panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 560, -1, 40));

        Due_date.setColorBackground(new java.awt.Color(102, 102, 102));
        Due_date.setColorButtonHover(new java.awt.Color(204, 204, 204));
        Due_date.setColorDiaActual(new java.awt.Color(102, 102, 102));
        Due_date.setColorForeground(new java.awt.Color(0, 0, 0));
        Due_date.setPlaceholder("Choose Date..");
        Panel_main.add(Due_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 560, 260, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonCircle1.setText("Issue Book");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        Panel_main.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 640, 160, 60));

        getContentPane().add(Panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1460, 820));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        homePage home = new homePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void text_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_bookIdActionPerformed

    private void text_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        //agar qauantity zero hai tw book nhi dy saktyyy
        if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "This book is not avalaible");
        } else {
//we need to check if book is already issued or not
            if (isAlreadyIssued() == false) {

                if (issueBook() == true) {
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully");
                    updateBookCount();

                } else {
                    JOptionPane.showMessageDialog(this, "Book Not issued!!!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Book Already issued to this student!!!");
            }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed
    }
    private void text_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_bookIdFocusLost
        // TODO add your handling code here:
        if (!text_bookId.getText().equals("")) {
            getBookDetails();
        } else {
        }

    }//GEN-LAST:event_text_bookIdFocusLost

    private void text_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_studentIdFocusLost

        if (!text_bookId.getText().equals(" ")) {
            getStudentDetails();
        } else {

        }      // TODO add your handling code here:
    }//GEN-LAST:event_text_studentIdFocusLost

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
            java.util.logging.Logger.getLogger(Issue_books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Issue_books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Issue_books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Issue_books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Issue_books().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser Due_date;
    private rojeru_san.componentes.RSDateChooser Issue_date;
    private javax.swing.JPanel Panel_main;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_Course;
    private javax.swing.JLabel lbl_Dept;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookError2;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField text_bookId;
    private app.bolivia.swing.JCTextField text_studentId;
    // End of variables declaration//GEN-END:variables

}
