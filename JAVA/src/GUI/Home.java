/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.banBUS;
import BUS.goiMonBUS;
import BUS.khuVucBUS;
import BUS.thucDonBUS;
import BUS.userBUS;
import DAO.dBConnection;
import DAO.func;
import DTO.banDTO;
import DTO.goiMonDTO;
import DTO.khuVucDTO;
import DTO.thucDonDTO;
import DTO.userDTO;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public final class Home extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Home
     *
     * @throws java.sql.SQLException
     */
    public static String tenBan;
    dBConnection db = new dBConnection();

    public Home() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        //this.setSize(1920, 1080);
        showDate();
        showTime();
        loadThucDon();
        loadBan();
        changeAvt();
        this.setSize(1366, 728);
        txtGiamGia.setEditable(false);
        txtTienThoi.setEditable(false);
        txtTongTien.setEditable(false);
        soLuong.setValue(1);
        btnThem.setEnabled(false);
        btnXoas.setEnabled(false);
        btnTang.setEnabled(false);
        btnGiam.setEnabled(false);
        btnMoBan.setEnabled(false);
        btnHuyBan.setEnabled(false);
        btnChuyenBan.setEnabled(false);
        btnGopBan.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnInHoaDon.setEnabled(false);
        txtName.setText(GUILogin.name);
        txtGhiChu.setText(null);
        user();
        
    }
    //Hàm đổi avt
    public void changeAvt()
    {
        if(GUILogin.gioiTinh.equals("Nam")){
            jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/male_100px.png")));
        }
        else
        {
            jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lady_100px.png")));
        }
    }
    public void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText(s.format(d));
    }

    public void showTime() {
        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
            txtTime.setText(s.format(d));
        }).start();
    }
//Load thực đơn

    public void user() {
        try {
            ArrayList<userDTO> list = userBUS.userId(GUILogin.IdNhanVien);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getName());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadThucDon() throws SQLException, ClassNotFoundException {
        ArrayList<thucDonDTO> list = thucDonBUS.thucDonAll();
        DefaultTableModel model = (DefaultTableModel) tableThucDon.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdThucDon();
            row[1] = list.get(i).getTenThucDon();
            row[2] = list.get(i).getDonViTinh();
            row[3] = list.get(i).getDonGiaTon();
            model.addRow(row);
        }
    }

    /*//Search*/
    public void searchFilter(String searchTerm) throws SQLException, ClassNotFoundException {
        ArrayList<thucDonDTO> list = thucDonBUS.thucDonSearchTen(searchTerm);
        DefaultTableModel model = (DefaultTableModel) tableThucDon.getModel();
        model.setNumRows(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdThucDon();
            row[1] = list.get(i).getTenThucDon();
            row[2] = list.get(i).getDonViTinh();
            row[3] = list.get(i).getDonGiaTon();
            model.addRow(row);
        }
    }

    public void loadBan() throws SQLException, ClassNotFoundException {
        ArrayList<khuVucDTO> list = khuVucBUS.khuVucAll();
        //Lấy dữ liệu khu vực
        for (int i = 0; i < list.size(); i++) {
            JPanel pane = new JPanel();
            tabP.addTab(list.get(i).getTenKhuVuc(), pane);
            pane.setBackground(new java.awt.Color(255, 255, 255));
            GridLayout gridLayout = new GridLayout(5, 5);
            pane.setLayout(gridLayout);
            ArrayList<banDTO> listBan = banBUS.banSearchHome(list.get(i).getTenKhuVuc());
            //Lấy dữ liệu bàn
            for (int j = 0; j < listBan.size(); j++) {
                JButton but = new JButton(listBan.get(j).getTenBan());
                but.setSize(80, 80);
                but.setFont(new java.awt.Font("Times New Roman", 1, 14));
                switch (listBan.get(j).getTrangThai()) {
                    case "Trống":
                        but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee_Trong.png")));
                        break;
                    case "Có khách":
                        but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee_CoKhach.png")));
                        break;
                    case "Đặt trước":
                        but.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffee_DatTruoc.png")));
                        break;
                    default:
                        break;
                }
                but.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                but.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                pane.add(but);
                but.addActionListener(this);
            }

        }
    }
//((JButton) e.getSource()).getText()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            try {
                txtBan.setText(((JButton) e.getSource()).getText());
                tenBan = ((JButton) e.getSource()).getText();
                dsGoiMonBan();
                btnTang.setEnabled(true);
                btnGiam.setEnabled(true);
                btnMoBan.setEnabled(true);
                btnHuyBan.setEnabled(true);
                btnChuyenBan.setEnabled(true);
                btnGopBan.setEnabled(true);
                btnThanhToan.setEnabled(true);
                btnInHoaDon.setEnabled(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Gọi món
    public String getIdBan(String tenBan) {
        try {

            String sql = "SELECT IdBan FROM `ban` WHERE TenBan = '" + tenBan + "'";
            db.open();
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                return rs.getString("IdBan");
            }
            db.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void dsGoiMonBan() throws ClassNotFoundException {
        ArrayList<goiMonDTO> listGm = goiMonBUS.goiMonIdBan(getIdBan(tenBan));
        DefaultTableModel mode = (DefaultTableModel) tableBanGoiMon.getModel();
        mode.setNumRows(0);
        Object[] row = new Object[5];
        for (int i = 0; i < listGm.size(); i++) {
            row[0] = listGm.get(i).getIdThucDon();
            row[1] = listGm.get(i).getTenThucDon();
            row[2] = listGm.get(i).getSoLuong();
            row[3] = listGm.get(i).getDonGiaTon();
            row[4] = listGm.get(i).getThanhTien();
            mode.addRow(row);
        }
        tongTien();
    }

    //Tính tiền
    public void tongTien() {
        try {
            float tongTien = 0;
            db.open();
            String sql = "SELECT * FROM `goimon` WHERE IdBan ='" + getIdBan(tenBan) + "'";
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                tongTien = tongTien + rs.getFloat("ThanhTien");
            }
            txtTongTien.setText(String.valueOf(tongTien));

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void moBan() {
        try {
            db.open();
            String tt = null;
            String sqlUpdate = "UPDATE `ban` SET `TrangThai`='Có khách' WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlSelect = "SELECT * FROM `ban` WHERE IdBan ='" + getIdBan(tenBan) + "'";

            ResultSet rs = db.excuteQuery(sqlSelect);
            while (rs.next()) {
                tt = rs.getString("TrangThai");
            }
            switch (tt) {
                case "Đặt trước":
                    JOptionPane.showMessageDialog(null, "Bàn đã được đặt trước");
                    break;
                case "Trống":
                    db.excuteUpdate(sqlUpdate);
                    tabP.removeAll();
                    loadBan();
                    break;
                case "Có khách":
                    JOptionPane.showMessageDialog(null, "Bàn đã có khách!!!");
                    break;
                default:
                    break;
            }
            db.close();
            tableThucDon.setEnabled(true);
        } catch (HeadlessException | SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void huyBan() {
        try {
            db.open();
            String tt = null;
            String sqlUpdate = "UPDATE `ban` SET `TrangThai`='Trống' WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlSelect = "SELECT * FROM `ban` WHERE IdBan ='" + getIdBan(tenBan) + "'";
            String sqlDeleMon = "DELETE FROM `goimon` WHERE IdBan = '" + getIdBan(tenBan) + "'";
            ResultSet rs = db.excuteQuery(sqlSelect);
            while (rs.next()) {
                tt = rs.getString("TrangThai");
            }
            switch (tt) {
                case "Đặt trước":
                    JOptionPane.showMessageDialog(null, "Bàn đã được đặt trước");
                    break;
                case "Trống":
                    btnMoBan.setEnabled(true);
                    break;
                case "Có khách":
                    if (JOptionPane.showConfirmDialog(this, "Bàn chưa được thanh toán! Bạn chắc chắn hủy bàn?", "Confirm",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        try {
                            db.excuteUpdate(sqlDeleMon);
                            if (db.excuteUpdate(sqlUpdate) == 1) {
                                tabP.removeAll();
                                loadBan();
                                DefaultTableModel model = (DefaultTableModel) tableBanGoiMon.getModel();
                                model.setNumRows(0);
                                dsGoiMonBan();
                                JOptionPane.showMessageDialog(null, "Hủy bàn thành công!!!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Hủy bàn thất bại!!!");
                            }
                        } catch (SQLException | ClassNotFoundException ex) {
                            db.displayError((SQLException) ex);
                        }
                    }
                    break;
                default:
                    break;
            }
            db.close();
            tableThucDon.setEnabled(true);
        } catch (HeadlessException | SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getBan() {
        return tenBan;
    }

    public void chuyenBan(String idBan) {
        try {
            db.open();
            ArrayList<goiMonDTO> list = goiMonBUS.goiMonIdBan(getIdBan(tenBan));

            String sqlDelete = "DELETE FROM `goimon` WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlUpdate = "UPDATE `ban` SET `TrangThai`='Trống' WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlUpdate2 = "UPDATE `ban` SET `TrangThai`='Có khách' WHERE IdBan = '" + idBan + "'";
            String sqlSel = "SELECT TrangThai FROM ban WHERE IdBan = '" + idBan + "'";
            ResultSet rs = db.excuteQuery(sqlSel);
            while (rs.next()) {
                switch (rs.getString("TrangThai")) {
                    case "Có khách":
                        JOptionPane.showMessageDialog(null, "Bàn đã có khách không thể chuyển. Xin chọn lại bàn");
                        break;
                    case "Đặt trước":
                        JOptionPane.showMessageDialog(null, "Bàn đã được đặt trước không thể chuyển. Xin chọn lại bàn");
                        break;
                    case "Trống":
                        db.excuteUpdate(sqlDelete);
                        db.excuteUpdate(sqlUpdate);
                        db.excuteUpdate(sqlUpdate2);
                        for (int i = 0; i < list.size(); i++) {
                            String sql = "INSERT INTO `goimon`(`IdBan`, `IdThucDon`, `TenThucDon`, `DonGiaTon`, `SoLuong`, `ThoiGian`, `ThanhTien`) "
                                    + "VALUES ('" + idBan + "','" + list.get(i).getIdThucDon() + "','" + list.get(i).getTenThucDon() + "','" + list.get(i).getDonGiaTon()
                                    + "','" + list.get(i).getSoLuong() + "','" + list.get(i).getThoiGian() + "','" + list.get(i).getThanhTien() + "')";
                            db.excuteUpdate(sql);
                        }
                        break;
                    default:
                        break;
                }
            }

            db.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gopBan(String idBan) {
        try {
            db.open();
            ArrayList<goiMonDTO> list = goiMonBUS.goiMonIdBan(getIdBan(tenBan));
            for (int i = 0; i < list.size(); i++) {
                String sql = "INSERT INTO `goimon`(`IdBan`, `IdThucDon`, `TenThucDon`, `DonGiaTon`, `SoLuong`, `ThoiGian`, `ThanhTien`) "
                        + "VALUES ('" + idBan + "','" + list.get(i).getIdThucDon() + "','" + list.get(i).getTenThucDon() + "','" + list.get(i).getDonGiaTon()
                        + "','" + list.get(i).getSoLuong() + "','" + list.get(i).getThoiGian() + "','" + list.get(i).getThanhTien() + "')";
                db.excuteUpdate(sql);

            }
            String sqlDelete = "DELETE FROM `goimon` WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlUpdate = "UPDATE `ban` SET `TrangThai`='Trống' WHERE IdBan = '" + getIdBan(tenBan) + "'";
            String sqlUpdate2 = "UPDATE `ban` SET `TrangThai`='Có khách' WHERE IdBan = '" + idBan + "'";
            db.excuteUpdate(sqlDelete);
            db.excuteUpdate(sqlUpdate);
            db.excuteUpdate(sqlUpdate2);
            db.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void thanhToan() {
        try {
            ArrayList<goiMonDTO> list = goiMonBUS.goiMonIdBan(getIdBan(tenBan));
            float tongTien = 0;
            for (int i = 0; i < list.size(); i++) {
                tongTien = tongTien + list.get(i).getThanhTien();
            }
            Date dt = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String ngayLap = s.format(dt);
            func fn = new func();
            String IdHoaDon = fn.MaTuDong("IdHoaDon", "hoadon", "HD");
            String sql = "INSERT INTO `hoadon`(`IdHoaDon`, `IdNhanVien`, `IdBan`, `NgayLap`, `DienGiai`, `SoTien`) "
                    + "VALUES ('" + IdHoaDon + "','" + GUILogin.IdNhanVien + "','" + getIdBan(tenBan) + "','" + ngayLap + "','" + txtGhiChu.getText()
                    + "','" + tongTien + "')";
            
            db.open();
            if (db.excuteUpdate(sql) == 1) {
                String sqlDel = "DELETE FROM `goimon` WHERE IdBan = '" + getIdBan(tenBan) + "'";
                String sqlUp = "UPDATE `ban` SET `TrangThai`='Trống' WHERE IdBan = '" + getIdBan(tenBan) + "'";
                //ADD DATA OF CTHD
                //GET data Table DS

                int i = tableBanGoiMon.getSelectedRow();
                TableModel modelBanGoiMon = tableBanGoiMon.getModel();
                int sL = Integer.parseInt(modelBanGoiMon.getValueAt(i, 2).toString());//SoLuong
                float TriGia = Float.parseFloat(modelBanGoiMon.getValueAt(i, 4).toString());//Giá
                String IdThucDonGM = modelBanGoiMon.getValueAt(i, 0).toString();//IdThucDon
                String sqlCTHD = "INSERT INTO `cthd`(`IdHoaDon`, `IdThucDon`, `SoLuong`, `TriGia`) "
                   + "VALUES ('" + IdHoaDon + "','" + IdThucDonGM + "','" + sL + "','" + TriGia + "')";
                //Float TriGia = sL * gia;//Thành tiền
                //JOptionPane.showMessageDialog(null, "Id: " + IdThucDonGM + " SL: " + sL + " Tri Gia: " + TriGia);
//                
//                
//                db.excuteUpdate(sqlCTHD);
                //Done Code cua Cong
                db.excuteUpdate(sqlDel);       
                db.excuteUpdate(sqlUp);
                tabP.removeAll();
                loadBan();
                DefaultTableModel model = (DefaultTableModel) tableBanGoiMon.getModel();
                model.setNumRows(0);
                dsGoiMonBan();
                JOptionPane.showMessageDialog(null, sqlCTHD);
                //JOptionPane.showMessageDialog(null, "Thanh toán " + tenBan + " thành công!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Thanh toán " + tenBan + " thất bại!!!");
            }
            db.close();
        } catch (SQLException | ClassNotFoundException ex) {
            db.displayError((SQLException) ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        PnDanhSachKVBAN = new javax.swing.JPanel();
        tabP = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        txtBan = new javax.swing.JLabel();
        btnChuyenBan = new javax.swing.JButton();
        btnGopBan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBanGoiMon = new javax.swing.JTable();
        soLuong = new javax.swing.JSpinner();
        btnThem = new javax.swing.JButton();
        btnXoas = new javax.swing.JButton();
        btnTang = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhanTram = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSotienKD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTienThoi = new javax.swing.JTextField();
        btnGiam = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnHuyBan = new javax.swing.JButton();
        btnMoBan = new javax.swing.JButton();
        txtTrangThai = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtName = new javax.swing.JLabel();
        btnTaiKhoan = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThucDon = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM QUẢN LÝ QUÁN CAFFEE");
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(1366, 728));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ BÀN HÀNG");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_clock_25px.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_planner_25px_1.png"))); // NOI18N

        txtTime.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtDate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(753, 753, 753)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnDanhSachKVBANLayout = new javax.swing.GroupLayout(PnDanhSachKVBAN);
        PnDanhSachKVBAN.setLayout(PnDanhSachKVBANLayout);
        PnDanhSachKVBANLayout.setHorizontalGroup(
            PnDanhSachKVBANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabP)
        );
        PnDanhSachKVBANLayout.setVerticalGroup(
            PnDanhSachKVBANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabP)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBan.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        txtBan.setText("Bàn 1");
        jPanel4.add(txtBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 110, 50));

        btnChuyenBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnChuyenBan.png"))); // NOI18N
        btnChuyenBan.setBorder(null);
        btnChuyenBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChuyenBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenBanActionPerformed(evt);
            }
        });
        jPanel4.add(btnChuyenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 90, -1, 50));

        btnGopBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnGopBan.png"))); // NOI18N
        btnGopBan.setBorder(null);
        btnGopBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGopBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGopBanActionPerformed(evt);
            }
        });
        jPanel4.add(btnGopBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 151, -1, 44));

        tableBanGoiMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên thực đơn", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        tableBanGoiMon.setRowHeight(25);
        tableBanGoiMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBanGoiMonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableBanGoiMon);
        if (tableBanGoiMon.getColumnModel().getColumnCount() > 0) {
            tableBanGoiMon.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableBanGoiMon.getColumnModel().getColumn(2).setPreferredWidth(35);
        }

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 90, 419, 295));
        jPanel4.add(soLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 228, 91, -1));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnThem.png"))); // NOI18N
        btnThem.setBorder(null);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel4.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 254, -1, -1));

        btnXoas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnXoa.png"))); // NOI18N
        btnXoas.setBorder(null);
        btnXoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoasActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoas, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 285, -1, -1));

        btnTang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn+.png"))); // NOI18N
        btnTang.setBorder(null);
        btnTang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangActionPerformed(evt);
            }
        });
        jPanel4.add(btnTang, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 325, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Ghi chú");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 438, -1, -1));
        jPanel4.add(txtGhiChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 433, 286, -1));

        jLabel9.setText("Tổng tiền: ");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 469, -1, -1));
        jPanel4.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 463, 286, -1));

        jLabel10.setText("Giảm giá: ");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 496, -1, -1));
        jPanel4.add(txtPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 493, 67, -1));
        jPanel4.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 493, 192, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("%");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 496, -1, -1));

        jLabel12.setText("Số tiền khách đưa: ");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 526, -1, -1));

        txtSotienKD.setText("0");
        txtSotienKD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSotienKDFocusGained(evt);
            }
        });
        txtSotienKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSotienKDKeyReleased(evt);
            }
        });
        jPanel4.add(txtSotienKD, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 523, 286, -1));

        jLabel13.setText("Tiền thối: ");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 556, -1, -1));
        jPanel4.add(txtTienThoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 553, 286, -1));

        btnGiam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btn-.png"))); // NOI18N
        btnGiam.setBorder(null);
        btnGiam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamActionPerformed(evt);
            }
        });
        jPanel4.add(btnGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 360, -1, -1));

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnThanhToan.png"))); // NOI18N
        btnThanhToan.setBorder(null);
        btnThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel4.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 512, 93, -1));

        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnInHoaDon.png"))); // NOI18N
        btnInHoaDon.setBorder(null);
        btnInHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel4.add(btnInHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 433, -1, -1));

        btnHuyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnHuyBan.png"))); // NOI18N
        btnHuyBan.setBorder(null);
        btnHuyBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuyBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBanActionPerformed(evt);
            }
        });
        jPanel4.add(btnHuyBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 43, -1, -1));

        btnMoBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnMoBan.png"))); // NOI18N
        btnMoBan.setBorder(null);
        btnMoBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoBanMouseClicked(evt);
            }
        });
        btnMoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoBanActionPerformed(evt);
            }
        });
        jPanel4.add(btnMoBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 12, -1, -1));
        jPanel4.add(txtTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 27, 123, 22));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtName.setText("Mai Văn Hoàng");
        txtName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user_male_circle_20px.png"))); // NOI18N
        btnTaiKhoan.setText("Tài khoản");
        btnTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });

        jLabel14.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnTaiKhoan))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(969, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtName)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaiKhoan))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setText("Nhập món...");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tableThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Thực đơn", "Đơn vị tính", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableThucDon.setRowHeight(20);
        tableThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableThucDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableThucDon);
        if (tableThucDon.getColumnModel().getColumnCount() > 0) {
            tableThucDon.getColumnModel().getColumn(0).setPreferredWidth(35);
            tableThucDon.getColumnModel().getColumn(2).setPreferredWidth(40);
            tableThucDon.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee_Trong.png"))); // NOI18N
        jLabel2.setText("Bàn trống");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 60));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffee_DatTruoc.png"))); // NOI18N
        jLabel4.setText("Bàn đặt trước");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 60));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee_CoKhach.png"))); // NOI18N
        jLabel5.setText("Bàn có khách");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 130, 60));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnDanhSachKVBAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(PnDanhSachKVBAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_home_filled_20px.png"))); // NOI18N
        jMenu1.setText("TRANG CHỦ");
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_system_information_20px.png"))); // NOI18N
        jMenu2.setText("HỆ THỐNG");

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_user_filled_20px.png"))); // NOI18N
        jMenu7.setText("Thông tin nhân viên");
        jMenu2.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_password_20px_1.png"))); // NOI18N
        jMenu8.setText("Đổi mật khẩu");
        jMenu2.add(jMenu8);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_manager_20px.png"))); // NOI18N
        jMenu3.setText("QUẢN LÝ");

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Organization_Chart_People_20px.png"))); // NOI18N
        jMenu13.setText("Quản lý nhân viên");
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu13MouseClicked(evt);
            }
        });
        jMenu13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenu13);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Split_Table_20px.png"))); // NOI18N
        jMenu9.setText("Quản lý khu vực");
        jMenu9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu9MouseClicked(evt);
            }
        });
        jMenu3.add(jMenu9);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_restaurant_table_filled_20px.png"))); // NOI18N
        jMenu10.setText("Quản lý bàn");
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenu3.add(jMenu10);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_warehouse_20px.png"))); // NOI18N
        jMenu4.setText("KHO");

        jMenu11.setText("Quản lý loại thực đơn");
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenu4.add(jMenu11);

        jMenu12.setText("Quản lý thực đơn");
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });
        jMenu4.add(jMenu12);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_list_15px.png"))); // NOI18N
        jMenu5.setText("THỐNG KÊ");
        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_exit_15px.png"))); // NOI18N
        jMenu6.setText("ĐĂNG XUẤT");
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_shutdown_20px_2.png"))); // NOI18N
        jMenu14.setText("THOÁT");
        jMenu14.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
                jMenu14MenuCanceled(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
            }
        });
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenu14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu14ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu14);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu14ActionPerformed


    }//GEN-LAST:event_jMenu14ActionPerformed

    private void jMenu14MenuCanceled(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu14MenuCanceled
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu14MenuCanceled

    private void jMenu14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MouseClicked

        if (JOptionPane.showConfirmDialog(this, "Bạn muốn thoát?", "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_jMenu14MouseClicked

    private void jMenu13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu13MouseClicked
        GUIQuanLyNhanVien qlGUIQuanLyNhanVien = new GUIQuanLyNhanVien();
        qlGUIQuanLyNhanVien.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu13MouseClicked

    private void jMenu13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu13ActionPerformed

    }//GEN-LAST:event_jMenu13ActionPerformed

    private void jMenu9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu9MouseClicked
        try {
            GUIQuanLyKhuVuc guiqlkv = new GUIQuanLyKhuVuc();
            guiqlkv.setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu9MouseClicked

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        try {
            GUIQuanLyBan guiqlb = new GUIQuanLyBan();
            guiqlb.setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu10MouseClicked

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked

        try {
            GUIQuanLyLoaiThucDon guiqlltd = new GUIQuanLyLoaiThucDon();
            guiqlltd.setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu11MouseClicked

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        GUIQuanLyThucDon guiqltd = new GUIQuanLyThucDon();
        guiqltd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu12MouseClicked

    private void btnGopBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGopBanActionPerformed
        try {
            GUIGopBan gop = new GUIGopBan(this, true);
            gop.setVisible(true);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGopBanActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            searchFilter(txtSearch.getText());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        txtSearch.setText("");
    }//GEN-LAST:event_txtSearchFocusGained

    private void btnChuyenBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenBanActionPerformed
        try {
            //        GUIChuyenBan guicb = new GUIChuyenBan();
//        guicb.setVisible(true);
            GUIChuyenB guicb = new GUIChuyenB(this, true);
            guicb.setVisible(true);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnChuyenBanActionPerformed

    private void btnXoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoasActionPerformed
        int i = tableBanGoiMon.getSelectedRow();
        TableModel model = tableBanGoiMon.getModel();
        String id = model.getValueAt(i, 0).toString();
        String quyryString = "DELETE FROM `goimon` WHERE IdThucDon = '" + model.getValueAt(i, 0).toString() + "'";
        try {
            db.open();
            db.excuteUpdate(quyryString);
            db.close();
            DefaultTableModel model1 = (DefaultTableModel) tableBanGoiMon.getModel();
            model1.setNumRows(0);
            dsGoiMonBan();

        } catch (SQLException ex) {
            Logger.getLogger(GUIQuanLyKhuVuc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXoasActionPerformed

    private void btnMoBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoBanMouseClicked

    }//GEN-LAST:event_btnMoBanMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            db.open();
            String sqlKT = "SELECT TrangThai FROM `ban` WHERE IdBan = '" + getIdBan(tenBan) + "'";

            ResultSet rs = db.excuteQuery(sqlKT);
            int i = tableThucDon.getSelectedRow();
            TableModel model = tableThucDon.getModel();
            int sL = (int) soLuong.getValue();//Số lượng
            float gia = Float.parseFloat(model.getValueAt(i, 3).toString());//Giá
            String IdThucDon = model.getValueAt(i, 0).toString();//IdThucDon
            String TenThucDon = model.getValueAt(i, 1).toString();//Tên thực đơn
            Float thanhTien = sL * gia;//Thành tiền
            Date dt = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String thoiGian = s.format(dt);
            String sql2 = "SELECT * FROM `goimon` WHERE IdBan = '" + getIdBan(tenBan) + "' AND IdThucDon = '" + IdThucDon + "'";
            ResultSet rs2 = db.excuteQuery(sql2);
            if (rs2.next()) {
                JOptionPane.showMessageDialog(null, "Bàn có món này.Vui lòng chọn món khác!!!");
            } else {
                while (rs.next()) {
                    if (rs.getString("TrangThai").equals("Trống")) {
                        JOptionPane.showMessageDialog(null, "Bàn chưa được mở, Hãy mở bàn trước khi thêm món!!!");
                    } else {
                        String sql = "INSERT INTO `goimon` (`IdBan`, `IdThucDon`, `TenThucDon`, `DonGiaTon`, `SoLuong`, `ThoiGian`, `ThanhTien`) "
                                + "VALUES ('" + getIdBan(tenBan) + "', '" + IdThucDon + "', '" + TenThucDon + "', '" + gia + "', '" + sL + "',"
                                + " '" + thoiGian + "', '" + thanhTien + "')";
                        db.excuteUpdate(sql);
                        db.close();
                        DefaultTableModel model1 = (DefaultTableModel) tableBanGoiMon.getModel();
                        model1.setNumRows(0);
                        dsGoiMonBan();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnThemActionPerformed

    private void tableBanGoiMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBanGoiMonMouseClicked
        btnXoas.setEnabled(true);
        btnThem.setEnabled(false);
        btnTang.setEnabled(true);
        btnGiam.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnInHoaDon.setEnabled(true);
    }//GEN-LAST:event_tableBanGoiMonMouseClicked

    private void tableThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableThucDonMouseClicked
        btnThem.setEnabled(true);
        btnXoas.setEnabled(false);
        btnTang.setEnabled(false);
        btnGiam.setEnabled(false);

    }//GEN-LAST:event_tableThucDonMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTangActionPerformed
        try {
            int i = tableBanGoiMon.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tableBanGoiMon.getModel();
            int soLuongGM = 0;
            float giaGM = 0;
            db.open();
            String sql = "SELECT SoLuong,DonGiaTon FROM `goimon` WHERE IdThucDon = '" + model.getValueAt(i, 0) + "'";
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                soLuongGM = rs.getInt("SoLuong");
                giaGM = rs.getFloat("DonGiaTon");
            }
            int soLuongMoi = soLuongGM + 1;
            float tong = soLuongMoi * giaGM;
            String sqlUpdate = "UPDATE `goimon` "
                    + "SET `SoLuong`=" + soLuongMoi + ",`ThanhTien`=" + tong + " WHERE IdThucDon = '" + model.getValueAt(i, 0) + "'";
            db.excuteUpdate(sqlUpdate);
            model.setNumRows(0);
            dsGoiMonBan();
            db.close();
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTangActionPerformed

    private void btnGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamActionPerformed
        try {
            int i = tableBanGoiMon.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tableBanGoiMon.getModel();
            int soLuongGM = 0;
            float giaGM = 0;
            db.open();
            String sql = "SELECT SoLuong,DonGiaTon FROM `goimon` WHERE IdThucDon = '" + model.getValueAt(i, 0) + "'";
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                soLuongGM = rs.getInt("SoLuong");
                giaGM = rs.getFloat("DonGiaTon");
            }
            int soLuongMoi = soLuongGM - 1;
            float tong = soLuongMoi * giaGM;
            if (soLuongMoi == 0) {
                JOptionPane.showMessageDialog(null, "Số lượng = 0");
            } else {
                String sqlUpdate = "UPDATE `goimon` "
                        + "SET `SoLuong`=" + soLuongMoi + ",`ThanhTien`=" + tong + " WHERE IdThucDon = '" + model.getValueAt(i, 0) + "'";

                db.excuteUpdate(sqlUpdate);
                model.setNumRows(0);
                dsGoiMonBan();
            }
            db.close();
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGiamActionPerformed

    private void txtSotienKDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSotienKDFocusGained

    }//GEN-LAST:event_txtSotienKDFocusGained

    private void txtSotienKDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSotienKDKeyReleased
        try {
            float tongTien = 0;
            float tienThoi = 0;
            float tienKhachDua = Float.valueOf(txtSotienKD.getText().toString());
            db.open();
            String sql = "SELECT * FROM `goimon` WHERE IdBan ='" + getIdBan(tenBan) + "'";
            ResultSet rs = db.excuteQuery(sql);
            while (rs.next()) {
                tongTien = tongTien + rs.getFloat("ThanhTien");
            }
            if (tienKhachDua <= tongTien) {
                txtTienThoi.setText(String.valueOf(tienThoi));
            } else {
                tienThoi = tienKhachDua - tongTien;
                txtTienThoi.setText(String.valueOf(tienThoi));
            }

        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSotienKDKeyReleased

    private void btnMoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoBanActionPerformed
        moBan();
    }//GEN-LAST:event_btnMoBanActionPerformed

    private void btnHuyBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBanActionPerformed
        huyBan();

    }//GEN-LAST:event_btnHuyBanActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        Print p = new Print(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        GUIThongTinNhanVien tt = new GUIThongTinNhanVien();
        tt.setVisible(true);
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Home().setVisible(true);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnDanhSachKVBAN;
    private javax.swing.JButton btnChuyenBan;
    private javax.swing.JButton btnGiam;
    private javax.swing.JButton btnGopBan;
    private javax.swing.JButton btnHuyBan;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnMoBan;
    private javax.swing.JButton btnTaiKhoan;
    private javax.swing.JButton btnTang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner soLuong;
    public static javax.swing.JTabbedPane tabP;
    private javax.swing.JTable tableBanGoiMon;
    private javax.swing.JTable tableThucDon;
    private javax.swing.JLabel txtBan;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JLabel txtName;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSotienKD;
    private javax.swing.JTextField txtTienThoi;
    private javax.swing.JLabel txtTime;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables

}
