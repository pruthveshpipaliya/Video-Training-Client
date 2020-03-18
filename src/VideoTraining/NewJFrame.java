package VideoTraining;


import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.netbeans.NativeInterfaceNetBeansHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import static javax.swing.SwingConstants.CENTER;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell 15R
 */


        
public final class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    private static final Image FacebookLogo = Toolkit.getDefaultToolkit().createImage(NewJFrame.class.getResource("FacebookLogo.png"));
    
    Connection  con     =   null ;
    Statement   stmt    =   null ;
    String  sCoarseName ;
    
    public void fnSetCoarse(String saCoarse)
    {
        this.sCoarseName = saCoarse ;
    }
    
    public String fnGenerateKey()
    {
        //Generate Pin
        
        String strKey = UUID.randomUUID().toString();
        
        //Genrate Pin End
        return strKey;
        
    }
    
    public void fnAddHeadLineLinks() throws SQLException
    {
        connect();
        String sql="select link,linkname from headlinelink" ;
        ResultSet rs=stmt.executeQuery(sql);
        int iCount = 0;
        while(rs.next())
        {
            iCount++;
            if(iCount > 10)
                continue ;
            final javax.swing.JLabel jTempLabel = new javax.swing.JLabel();
            jTempLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
            jTempLabel.setForeground(new java.awt.Color(0, 0, 0));
            jTempLabel.setText(""+rs.getString("linkname"));
            jTempLabel.setAutoscrolls(true);
            jTempLabel.setFocusable(false);
         
            jTempLabel.setMaximumSize(new java.awt.Dimension(136, 20));
            jTempLabel.setMinimumSize(new java.awt.Dimension(136, 20));
            jTempLabel.setHorizontalAlignment(CENTER);
            
            final String link = rs.getString("link");
            jTempLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        jTempLabelMouseClicked(evt);
                    } catch (SQLException ex) {
                        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jTempLabelMouseEntered(evt);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jTempLabelMouseExited(evt);
                }
                private void jTempLabelMouseEntered(java.awt.event.MouseEvent evt) {                                                      
                    jTempLabel.setForeground(Color.yellow);
                    setCursor(HAND_CURSOR);
                }                                                     

                private void jTempLabelMouseExited(java.awt.event.MouseEvent evt) {                                                     
                    jTempLabel.setForeground(Color.black);
                    setCursor(DEFAULT_CURSOR);
                }                                                    

                private void jTempLabelMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {                                                      
                    openWebpage(""+link);
                }
            });
            
            jHeadLineTextPanel.add(jTempLabel);
            jHeadLineTextPanel.validate();
        }
      
        
        disconnect();
       
    }
    
    public void fnAddVideoMenu() throws SQLException
    {
        connect();
        final javax.swing.JLabel jTempLabel = new javax.swing.JLabel();  
        jTempLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTempLabel.setForeground(new java.awt.Color(0,102,255));
        jTempLabel.setText("Home");
        jTempLabel.setAutoscrolls(true);
        jTempLabel.setFocusable(false);

        jTempLabel.setMaximumSize(new java.awt.Dimension(60, 20));
        jTempLabel.setMinimumSize(new java.awt.Dimension(60, 20));
        jTempLabel.setHorizontalAlignment(CENTER);
        jTempLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTempLabelMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTempLabelMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTempLabelMouseExited(evt);
            }
            private void jTempLabelMouseEntered(java.awt.event.MouseEvent evt) {                                                      
                jTempLabel.setForeground(Color.GRAY);
                setCursor(HAND_CURSOR);
            }                                                     

            private void jTempLabelMouseExited(java.awt.event.MouseEvent evt) {                                                     
                jTempLabel.setForeground(new java.awt.Color(0,102,255));
                setCursor(DEFAULT_CURSOR);
            }                                                    

            private void jTempLabelMouseClicked(java.awt.event.MouseEvent evt) {                                                      
                openWebpage("https://google.co.in");
            }
        });

        jVideoMenuPanel.add(jTempLabel);
        jVideoMenuPanel.validate();

        String sql="SELECT COUNT(titelofvideo) from videourl" ;
        ResultSet rs=stmt.executeQuery(sql);
        int iNumberVideo = 0 ;
        while(rs.next())
        {
            iNumberVideo = rs.getInt("COUNT(titelofvideo)");
        }
        
        for(int iLoop = 1 ; iLoop <= iNumberVideo ; iLoop++)
        { 
            final javax.swing.JLabel jTempLabelTemp = new javax.swing.JLabel();  
            jTempLabelTemp.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
            jTempLabelTemp.setForeground(new java.awt.Color(0,102,255));
            jTempLabelTemp.setText("Video "+iLoop);
            jTempLabelTemp.setAutoscrolls(true);
            jTempLabelTemp.setFocusable(false);
         
            jTempLabelTemp.setMaximumSize(new java.awt.Dimension(60, 20));
            jTempLabelTemp.setMinimumSize(new java.awt.Dimension(60, 20));
            jTempLabelTemp.setHorizontalAlignment(CENTER);
            jTempLabelTemp.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTempLabelMouseClicked(evt);
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jTempLabelMouseEntered(evt);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jTempLabelMouseExited(evt);
                }
                private void jTempLabelMouseEntered(java.awt.event.MouseEvent evt) {                                                      
                    jTempLabelTemp.setForeground(Color.GRAY);
                    setCursor(HAND_CURSOR);
                }                                                     

                private void jTempLabelMouseExited(java.awt.event.MouseEvent evt) {                                                     
                    jTempLabelTemp.setForeground(new java.awt.Color(0,102,255));
                    setCursor(DEFAULT_CURSOR);
                }                                                    

                private void jTempLabelMouseClicked(java.awt.event.MouseEvent evt) {                                                      
                    openWebpage("https://google.co.in");
                }
            });
            
            jVideoMenuPanel.add(jTempLabelTemp);
            jVideoMenuPanel.validate();
        }
        for(int iLoop = iNumberVideo ; iLoop < 18 ; iLoop++)
        {
            final javax.swing.JLabel jTempLabelTemp = new javax.swing.JLabel();  
            jTempLabelTemp.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
            jTempLabelTemp.setForeground(new java.awt.Color(0,102,255));
            jTempLabelTemp.setText("");
            jTempLabelTemp.setAutoscrolls(true);
            jTempLabelTemp.setFocusable(false);
         
            jTempLabelTemp.setMaximumSize(new java.awt.Dimension(60, 20));
            jTempLabelTemp.setMinimumSize(new java.awt.Dimension(60, 20));
            jTempLabelTemp.setHorizontalAlignment(CENTER);
            
            jVideoMenuPanel.add(jTempLabelTemp,BorderLayout.CENTER);
            jVideoMenuPanel.validate();
        }
        
        
    }
        
    public NewJFrame() throws SQLException, FileNotFoundException, IOException {
        
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //Add HeadLine Links.
        fnAddHeadLineLinks();
        
        //Add Video Menu Panel.
        fnAddVideoMenu();
        
        NativeInterface.initialize();
        NativeInterface.open();
        
        //Adding Icon
        try{
            BufferedImage image=ImageIO.read(new File("C:\\Users\\Dell 15R\\Desktop\\Video Training Software\\Desktop_Icon.png"));
            BufferedImage resizedImage=resize(image,jIcomImageLabel.getHeight(),jIcomImageLabel.getWidth());
            ImageIcon icon;
            icon=new ImageIcon(resizedImage);
            jIcomImageLabel.setIcon(icon);
            jIcomImageLabel.repaint();
        }
        catch(Exception e)
        {
            String st="Logo Image Loading Failed.";
            JOptionPane.showMessageDialog(null,st);
        }
        //Ends
        //fnAddImageToDatabase();
        //Adding fb logo
        /*try{
            
            ImageIcon icon;
            icon=new ImageIcon(FacebookLogo);
            jFacebookLogoLabel.setIcon(icon);
            jFacebookLogoLabel.repaint();
        }
        catch(Exception e)
        {
            System.out.println(""+e);
            String st="Logo Image Loading Failed.";
            JOptionPane.showMessageDialog(null,st);
        }*/
        //Ends
        fnGetImageFromDatabase();
        jFacebookLinkLabel.setText("Facebook Link");
        jFacebookLinkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jFacebookLinkLabelMouseClicked(evt);
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jFacebookLinkLabelMouseEntered(evt);
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jFacebookLinkLabelMouseExited(evt);
                }
                private void jFacebookLinkLabelMouseEntered(java.awt.event.MouseEvent evt) {                                                      
                    jFacebookLinkLabel.setForeground(Color.yellow);
                    setCursor(HAND_CURSOR);
                }                                                     

                private void jFacebookLinkLabelMouseExited(java.awt.event.MouseEvent evt) {                                                     
                    jFacebookLinkLabel.setForeground(Color.white);
                    setCursor(DEFAULT_CURSOR);
                }                                                    

                private void jFacebookLinkLabelMouseClicked(java.awt.event.MouseEvent evt) {                                                      
                    openWebpage("https://google.co.in");
                }
            });
        System.out.println(""+jVideoViewPanel.getWidth());
        System.out.println(""+jVideoViewPanel.getHeight());
        //jVideoViewPanel.add(getBrowserPanel(640,360), BorderLayout.PAGE_START);
        jVideoViewPanel.add(getBrowserPanel(640,360), BorderLayout.CENTER);
    }
    
    public void fnAddImageToDatabase() throws FileNotFoundException, SQLException
    {
        connect();
        File imgfile = new File("C:\\Users\\Dell 15R\\Desktop\\Video Training Software\\facebooklogo.png");
		  
        FileInputStream fin = new FileInputStream(imgfile);
        
        File imgfile1 = new File("C:\\Users\\Dell 15R\\Desktop\\Video Training Software\\Desktop_Icon.png");
		  
        FileInputStream fin1 = new FileInputStream(imgfile1);

         PreparedStatement pre =
         con.prepareStatement("insert into logos (facebook,desktopicon) values(?,?)");

         pre.setBinaryStream(1,(InputStream)fin,(int)imgfile.length());
         pre.setBinaryStream(2,(InputStream)fin1,(int)imgfile1.length());
         
         pre.executeUpdate();
         System.out.println("Successfully inserted the file into the database!");

         pre.close();
         disconnect();
    }
    
    public void fnGetImageFromDatabase() throws SQLException, IOException
    {
        connect();
        ResultSet rs = stmt.executeQuery("select facebook from logos");
        int i = 0;
        while (rs.next()) {
                InputStream in = rs.getBinaryStream(1);
                OutputStream f = new FileOutputStream(new File("test"+i+".jpg"));
                i++;
                int c = 0;
                while ((c = in.read()) > -1) {
                        f.write(c);
                }
                f.close();
                in.close();
        }
        Image FacebookLogoTemp = Toolkit.getDefaultToolkit().createImage(NewJFrame.class.getResource("FacebookLogo.png"));
        ImageIcon icontemp;
        icontemp=new ImageIcon(FacebookLogoTemp);
        jFacebookLogoLabel.setIcon(icontemp);
        jFacebookLogoLabel.repaint();
        disconnect();
    }
    
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/videotraining?" + "user=AdminDB&password=admindb123$");
                stmt=con.createStatement();
            }
            catch (SQLException ex) {
                //Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
                String st="Data-Base Unreachable.";
                JOptionPane.showMessageDialog(null,st);
            }
        }
        catch(ClassNotFoundException ex) {
            String st="Connection not Established.";
            JOptionPane.showMessageDialog(null,st);
        }
        
    }
    
    public void disconnect(){
        try{
            stmt.close();
            con.close();
        }
        catch(SQLException e){
            String st="Error in Release Connection.";
            JOptionPane.showMessageDialog(null,st);
        }
    }
    
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getHeight(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeadingPanel = new javax.swing.JPanel();
        jUpgradeLinkTitleLabel = new javax.swing.JLabel();
        jVideoTrainingTitleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jVideoViewPanel = new javax.swing.JPanel();
        jVideoMenuPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jIcomImageLabel = new javax.swing.JLabel();
        jHeadLinetext = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jHeadLineTextPanel = new javax.swing.JPanel();
        jFacebookLogoLabel = new javax.swing.JLabel();
        jFacebookLinkLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Training");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setName("cl_MainFrameVideoTraining"); // NOI18N

        jHeadingPanel.setBackground(new java.awt.Color(0, 102, 255));
        jHeadingPanel.setAutoscrolls(true);

        jUpgradeLinkTitleLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jUpgradeLinkTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        jUpgradeLinkTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jUpgradeLinkTitleLabel.setText("UPGRADE LINK TITEL");
        jUpgradeLinkTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jUpgradeLinkTitleLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jUpgradeLinkTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jUpgradeLinkTitleLabelMouseExited(evt);
            }
        });

        jVideoTrainingTitleLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jVideoTrainingTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        jVideoTrainingTitleLabel.setText("VIDEOKURS TITEL 1");
        jVideoTrainingTitleLabel.setAutoscrolls(true);
        jVideoTrainingTitleLabel.setFocusable(false);
        jVideoTrainingTitleLabel.setMaximumSize(new java.awt.Dimension(136, 20));
        jVideoTrainingTitleLabel.setMinimumSize(new java.awt.Dimension(136, 20));

        javax.swing.GroupLayout jHeadingPanelLayout = new javax.swing.GroupLayout(jHeadingPanel);
        jHeadingPanel.setLayout(jHeadingPanelLayout);
        jHeadingPanelLayout.setHorizontalGroup(
            jHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jHeadingPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jVideoTrainingTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
                .addComponent(jUpgradeLinkTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jHeadingPanelLayout.setVerticalGroup(
            jHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jHeadingPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jHeadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUpgradeLinkTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVideoTrainingTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel1.setText("      VIDEO TRAINING TITEL 1");
        jLabel1.setAutoscrolls(true);
        jLabel1.setOpaque(true);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setAutoscrolls(true);
        jLayeredPane1.setOpaque(true);
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(650, 370));

        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);

        jVideoViewPanel.setBackground(new java.awt.Color(255, 255, 255));
        jVideoViewPanel.setAutoscrolls(true);
        jVideoViewPanel.setOpaque(false);
        jVideoViewPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        jVideoViewPanel.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(jVideoViewPanel);

        jVideoMenuPanel.setBackground(new java.awt.Color(255, 255, 255));
        jVideoMenuPanel.setLayout(new java.awt.GridLayout(2, 9, 10, 0));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jVideoMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jVideoMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2))
        );
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jVideoMenuPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setAutoscrolls(true);

        jHeadLinetext.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jHeadLinetext.setForeground(new java.awt.Color(255, 255, 255));
        jHeadLinetext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jHeadLinetext.setText("HEADLINE TEXT");
        jHeadLinetext.setAutoscrolls(true);

        jHeadLineTextPanel.setBackground(new java.awt.Color(153, 153, 153));
        jHeadLineTextPanel.setAutoscrolls(true);
        jHeadLineTextPanel.setLayout(new java.awt.GridLayout(10, 1));
        jScrollPane4.setViewportView(jHeadLineTextPanel);

        jFacebookLogoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jFacebookLinkLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jFacebookLinkLabel.setForeground(new java.awt.Color(255, 255, 255));
        jFacebookLinkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFacebookLinkLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFacebookLogoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jHeadLinetext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jIcomImageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane4)
                    .addComponent(jFacebookLinkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jIcomImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jHeadLinetext, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFacebookLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jFacebookLinkLabel)
                .addGap(40, 40, 40))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHeadingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jHeadingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jUpgradeLinkTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUpgradeLinkTitleLabelMouseEntered
        jUpgradeLinkTitleLabel.setForeground(Color.yellow);
        setCursor(HAND_CURSOR);
    }//GEN-LAST:event_jUpgradeLinkTitleLabelMouseEntered

    private void jUpgradeLinkTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUpgradeLinkTitleLabelMouseExited
        jUpgradeLinkTitleLabel.setForeground(Color.white);
        setCursor(DEFAULT_CURSOR);
    }//GEN-LAST:event_jUpgradeLinkTitleLabelMouseExited

    private void jUpgradeLinkTitleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUpgradeLinkTitleLabelMouseClicked
        openWebpage("https://google.co.in");
    }//GEN-LAST:event_jUpgradeLinkTitleLabelMouseClicked

    
    
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        NativeInterface.open();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        NativeInterface.runEventPump();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                NativeInterface.close();
            }
        }));
    }
    
    public static JPanel getBrowserPanel(int Width,int Height) {
        
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser);
        webBrowser.setBarsVisible(true);
        webBrowser.setStatusBarVisible(false);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setMenuBarVisible(false);
        webBrowser.setLocationBarVisible(false);
        //webBrowser.setLayout(null);
       
        //webBrowser.("<iframe src=\"https://player.vimeo.com/video/164412681\" width=\"640\" height=\"360\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>");
//        webBrowser.navigate("http://player.vimeo.com/video/164412681?api=5&player_id=player1");
        webBrowser.setHTMLContent("<iframe src=\"https://player.vimeo.com/video/164412681\" width=\""+(Width)+"\" height=\""+(Height)+"\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>");
        
        //webBrowser.navigate("https://www.youtube.com/v/zlfbpC3dQWw");
        
        return webBrowserPanel;
    }
    
    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jFacebookLinkLabel;
    private javax.swing.JLabel jFacebookLogoLabel;
    private javax.swing.JPanel jHeadLineTextPanel;
    private javax.swing.JLabel jHeadLinetext;
    private javax.swing.JPanel jHeadingPanel;
    private javax.swing.JLabel jIcomImageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jUpgradeLinkTitleLabel;
    private javax.swing.JPanel jVideoMenuPanel;
    private javax.swing.JLabel jVideoTrainingTitleLabel;
    private javax.swing.JPanel jVideoViewPanel;
    // End of variables declaration//GEN-END:variables
}
