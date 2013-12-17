/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteczka;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Ania
 */
public class MainFrame extends javax.swing.JFrame {

    private static JTable Table;
    private static JTable Table2;
    public static DataBaseCon DBCM;
    static String p[]= new String[] {"Audiobook","Biografia i dokument", "Dla dzieci", "Encyklopedie i leksykony", 
   "Kuchnia", "Literatura", "Podreczniki szkolne", "Poradniki", "Sztuka", "Wakacje i podroze"} ;
    /** Creates new form MainBookshopFrame
     * @throws java.sql.SQLException
     * @throws java.io.IOException */
    public MainFrame() throws SQLException, IOException, Exception {
    DBCM=new DataBaseCon();
        Table=jTable1;
        Table2=jTable6;
        initComponents();
        getres();
       getresS();
    }

     public static void getres() throws SQLException{
        ResultSet rs = DBCM.getBookDetails("Biblioteczka");
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<9;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }    
        String[] col= {"ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable1.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}

     
     public static void getresT(String t) throws SQLException{
        ResultSet rs = DBCM.getBookDetailsT("Biblioteczka",t);
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<9;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }    
        String[] col= {"ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable2.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}

      public static void getresA(String im, String n) throws SQLException{
        ResultSet rs = DBCM.getBookDetailsA("Biblioteczka",im,n);
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<9;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }
        String[] col= {"ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable3.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}

      public static void getresP(String n) throws SQLException{
        ResultSet rs = DBCM.getBookDetailsP("Biblioteczka",n);
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<9;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }
        String[] col= {"ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable4.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}

      public static void getresG(String n) throws SQLException{
        ResultSet rs = DBCM.getBookDetailsG("Biblioteczka",n);
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<9;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }
        String[] col= {"ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable5.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}


      public static void getresS() throws SQLException{
        ResultSet rs = DBCM.getSales("Biblioteczka");
        ResultSetMetaData metaData=rs.getMetaData();
        int columnCount=metaData.getColumnCount();
        System.out.println(columnCount);
        int rowCnt=0;
        ArrayList o=new ArrayList();

        while(rs.next()){
            Object[] row=new Object[columnCount];
            for (int j=0;j<5;j++){
                System.out.println(rs.getString(j+1));
                row[j]=rs.getObject(j+1);
            }
            o.add(row);
            rowCnt++;
        }
        String[] col= {"Numer sprzedaży", "ISBN", "Ilość sprzedanych", "Cena całkowita", "Data Sprzedaży"};
        TableModel model = new SparseTableModel(o.size(), col);
        jTable6.setModel(model);
        Object[][] tab=new Object[o.size()][columnCount];
        for(int i=0;i<o.size();i++){
            Object[] row=(Object[]) o.get(i);
            for(int j=0;j<columnCount;j++){
                tab[i][j]=row[j];
                model.setValueAt(row[j],i,j);
            }
         }
}


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ContestMainPanel = new javax.swing.JTabbedPane();
        Books = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        Borrow = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        Title = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        Authors = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Gendres = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        Publishers = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 600));

        Books.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Dodaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Books.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 418, 114, -1));

        jButton2.setText("Usuń");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Books.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 418, 114, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        Books.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, 971, 353));

        jButton6.setText("Edytuj");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Books.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 418, 112, -1));

        ContestMainPanel.addTab("Katalog", Books);

        jButton11.setText("Dodaj wypożyczenie");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton3.setText("Usuń wypożyczenie");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Edytuj wypożyczenie");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Imię", "Nazwisko", "ISBN", "Data wypożyczenia", "Data zwrotu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setColumnSelectionAllowed(true);
        jScrollPane6.setViewportView(jTable6);
        jTable6.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout BorrowLayout = new javax.swing.GroupLayout(Borrow);
        Borrow.setLayout(BorrowLayout);
        BorrowLayout.setHorizontalGroup(
            BorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BorrowLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(BorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(BorrowLayout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(501, Short.MAX_VALUE))
        );
        BorrowLayout.setVerticalGroup(
            BorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BorrowLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(BorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        ContestMainPanel.addTab("Wypożyczenia", Borrow);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(5).setHeaderValue("Wydawca");
            jTable2.getColumnModel().getColumn(6).setHeaderValue("Rok Wydania");
            jTable2.getColumnModel().getColumn(7).setHeaderValue("Rodzaj");
            jTable2.getColumnModel().getColumn(8).setHeaderValue("Ilość Dostępnych");
        }

        jLabel5.setText("Tytuł:");

        jButton8.setText("Szukaj");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
        Title.setLayout(TitleLayout);
        TitleLayout.setHorizontalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitleLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        TitleLayout.setVerticalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        ContestMainPanel.addTab("Tytuł", Title);

        jButton4.setText("Wyszukaj po autorze");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Imię:");

        jLabel3.setText("Nazwisko:");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(5).setHeaderValue("Wydawca");
            jTable3.getColumnModel().getColumn(6).setHeaderValue("Rok Wydania");
            jTable3.getColumnModel().getColumn(7).setHeaderValue("Rodzaj");
            jTable3.getColumnModel().getColumn(8).setHeaderValue("Ilość Dostępnych");
        }

        javax.swing.GroupLayout AuthorsLayout = new javax.swing.GroupLayout(Authors);
        Authors.setLayout(AuthorsLayout);
        AuthorsLayout.setHorizontalGroup(
            AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AuthorsLayout.createSequentialGroup()
                .addGroup(AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AuthorsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton4))
                    .addGroup(AuthorsLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        AuthorsLayout.setVerticalGroup(
            AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AuthorsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(AuthorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        ContestMainPanel.addTab("Autor", Authors);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Audiobook","Biografia i dokument", "Biznes i ekonomia", "Dla dzieci", "Dla mlodziezy",
            "Dom i ogrod", "Encyklopedie i leksykony", "Informatyka", "Inzynieria i technologia",
            "Jezyk i nauka jezykow obcych", "Komiks", "Ksiazka akademicka i fachowa", "Ksiazka obcojezyczna",
            "Ksiazka regionalna", "Kuchnia i diety", "Literatura", "Medycyna", "Nauki humanistyczne",
            "Nauki spoleczne", "Nauki scisle", "Podreczniki szkolne", "Poradniki", "Prawo", "Sztuka", "Wakacje i podroze" }));
jComboBox1.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1ActionPerformed(evt);
    }
    });

    jTable5.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane5.setViewportView(jTable5);
    jTable5.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    if (jTable5.getColumnModel().getColumnCount() > 0) {
        jTable5.getColumnModel().getColumn(5).setHeaderValue("Wydawca");
        jTable5.getColumnModel().getColumn(6).setHeaderValue("Rok Wydania");
        jTable5.getColumnModel().getColumn(7).setHeaderValue("Rodzaj");
        jTable5.getColumnModel().getColumn(8).setHeaderValue("Ilość Dostępnych");
    }

    jButton9.setText("Szukaj");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout GendresLayout = new javax.swing.GroupLayout(Gendres);
    Gendres.setLayout(GendresLayout);
    GendresLayout.setHorizontalGroup(
        GendresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(GendresLayout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(GendresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(GendresLayout.createSequentialGroup()
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(36, 36, 36)
                    .addComponent(jButton9)))
            .addContainerGap(18, Short.MAX_VALUE))
    );
    GendresLayout.setVerticalGroup(
        GendresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(GendresLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(GendresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton9))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(22, 22, 22))
    );

    ContestMainPanel.addTab("Gatunek", Gendres);

    jButton7.setText("Wyszukaj po wydawnictwie");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    jLabel4.setText("Wydawnictwo:");

    jTable4.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ISBN", "Tytuł", "Cena", "Imię Autora", "Nazwisko Autora", "Wydawca", "Rok Wydania", "Rodzaj", "Ilość Dostępnych"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane4.setViewportView(jTable4);
    jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    if (jTable4.getColumnModel().getColumnCount() > 0) {
        jTable4.getColumnModel().getColumn(5).setHeaderValue("Wydawca");
        jTable4.getColumnModel().getColumn(6).setHeaderValue("Rok Wydania");
        jTable4.getColumnModel().getColumn(7).setHeaderValue("Rodzaj");
        jTable4.getColumnModel().getColumn(8).setHeaderValue("Ilość Dostępnych");
    }

    javax.swing.GroupLayout PublishersLayout = new javax.swing.GroupLayout(Publishers);
    Publishers.setLayout(PublishersLayout);
    PublishersLayout.setHorizontalGroup(
        PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(PublishersLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jButton7)
            .addContainerGap(603, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PublishersLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    PublishersLayout.setVerticalGroup(
        PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(PublishersLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(PublishersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton7))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30))
    );

    ContestMainPanel.addTab("Wydawnictwo", Publishers);

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jLabel1.setText(" Twoja Biblioteczka");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(ContestMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(ContestMainPanel)
            .addGap(95, 95, 95))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(38, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddBook add=new AddBook();
        add.setVisible(true);
        AddBook.addOrEdit=0;
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int i=jTable1.getSelectedRow();

        Delete del=new Delete();
        Delete.bookOrSale=0;
        del.setVisible(true);
        del.jLabel2.setText("Książkę: "+jTable1.getValueAt(i,1).toString());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       AddBook edit=new AddBook();
       edit.setVisible(true);
       AddBook.addOrEdit=1;
       int i=jTable1.getSelectedRow();
       edit.jTextField1.setText(jTable1.getValueAt(i,0).toString());
       edit.jTextField2.setText(jTable1.getValueAt(i,1).toString());
       edit.jTextField3.setText(jTable1.getValueAt(i,3).toString());
       edit.jTextField4.setText(jTable1.getValueAt(i,4).toString());
       edit.jTextField5.setText(jTable1.getValueAt(i,5).toString());
       edit.jTextField6.setText(jTable1.getValueAt(i,6).toString());
       edit.jTextField8.setText(jTable1.getValueAt(i,8).toString());
       edit.jTextField9.setText(jTable1.getValueAt(i,2).toString());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       String tytul=jTextField4.getText();
       ResultSet rs;
        try {
            getresT(tytul);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    String imie=jTextField1.getText();
    String nazw=jTextField2.getText();
       ResultSet rs;
        try {
           getresA(imie,nazw);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       String n=jTextField3.getText();

       ResultSet rs;
        try {
            getresP(n);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       String n=getGendre();

       ResultSet rs;
        try {
            getresG(n);
            
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      AddBorrow as=new AddBorrow();
      as.setVisible(true);
      AddBorrow.addOrEdit=0;
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    Delete de=new Delete();
    Delete.bookOrSale=1;
    de.setVisible(true);
    int i=jTable6.getSelectedRow();
    Delete.delNo=jTable6.getValueAt(i,0).toString();
    de.jLabel2.setText("Książkę:"+jTable6.getValueAt(i,1).toString()+"ze sprzedaży numer:"+jTable6.getValueAt(i,0).toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      AddBorrow as=new AddBorrow();
      as.setVisible(true);
      AddBorrow.addOrEdit=1;
       int i=jTable6.getSelectedRow();
       Delete.delNo=jTable6.getValueAt(i, 0).toString();
       as.jTextField1.setText(jTable6.getValueAt(i,0).toString());
       as.jTextField2.setText(jTable6.getValueAt(i,1).toString());
       as.jTextField3.setText(jTable6.getValueAt(i,2).toString());
       as.jTextField4.setText(jTable6.getValueAt(i,3).toString());
       as.jTextField5.setText(jTable6.getValueAt(i,4).toString());
    }//GEN-LAST:event_jButton5ActionPerformed

     public static String getISBN(){
       int i=jTable1.getSelectedRow();
       String isbn=jTable1.getValueAt(i,0).toString();
       return isbn;
     }


      public static String getISBN2(){
       int i=jTable6.getSelectedRow();
       String isbn=jTable6.getValueAt(i,1).toString();
       return isbn;
     }

     private String getGendre(){
             int i = jComboBox1.getSelectedIndex();

        switch(i){
            case 0: return p[0];
            case 1: return p[1];
            case 2: return p[2];
            case 3: return p[3];
            case 4: return p[4];
            case 5: return p[5];
            case 6: return p[6];
            case 7: return p[7];
            case 8: return p[8];
            case 9: return p[9];
            case 10: return p[10];
            case 11: return p[11];
            case 12: return p[12];
            case 13: return p[13];
            case 14: return p[14];
            case 15: return p[15];
            case 16: return p[16];
            case 17: return p[17];
            case 18: return p[18];
            case 19: return p[19];
            case 20: return p[20];
            case 21: return p[21];
            case 22: return p[22];
            case 23: return p[23];
            case 24: return p[24];
            case 25: return p[25];
        }
        return "null";
        }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Authors;
    private javax.swing.JPanel Books;
    private javax.swing.JPanel Borrow;
    private javax.swing.JTabbedPane ContestMainPanel;
    private javax.swing.JPanel Gendres;
    private javax.swing.JPanel Publishers;
    private javax.swing.JPanel Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private static javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private static javax.swing.JTable jTable3;
    private static javax.swing.JTable jTable4;
    private static javax.swing.JTable jTable5;
    private static javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
