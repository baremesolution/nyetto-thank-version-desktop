package nyettotank2.view.newIHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import nyettotank2.utilitaires.ConfigureImageButton;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainView extends javax.swing.JFrame {

    private static String chooseLanguage = "FRANCAIS";
    private static String activeMenu = "";
    private static String chooseISO = "";
    private static String choosePays = "Cameroun";
    private static String typeDonneData = "";
    private static String methodCalcul = "";
    private JScrollPane dataScrollPane;
    private InfoTypeView infoTypeView = new InfoTypeView();
    private InfoCapaciteView infoCapaciteView = new InfoCapaciteView();
    private DonneeGeometrique donneeGeometrique = new DonneeGeometrique();
    private DonneesVolumetriques donneeVolumetrie = new DonneesVolumetriques();
    private Traitement traitement = new Traitement();
    private HomePage homePage = new HomePage();
    private ButtonGroup bgPays = new ButtonGroup();
    private ButtonGroup bgLangue = new ButtonGroup();
    private ButtonGroup bgIsoGeometrie = new ButtonGroup();
    private ButtonGroup bgIsoVolumetrie = new ButtonGroup();
    Color DefaultColor, ClickedColor;
    private ButtonGroup buttonGroupForTypeData = new ButtonGroup();
    private ButtonGroup buttonGroupForGeometrie = new ButtonGroup();

    public MainView() {
        initComponents();
        ConfigureImageButton.sterchImage(logo);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/nyettoTankFtLogo.png")));
        this.setTitle("Nyettoft Tank");
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);

        // panelOfCalculMethod.setVisible(false);
        DefaultColor = new Color(255, 255, 254);
        ClickedColor = new Color(218, 180, 57);
        btnTraitement.setEnabled(false);

        setMenuItemIcon(menuCameroun, "/images/cameroun.png");
        setMenuItemIcon(menuGabon, "/images/gabon.png");
        setMenuItemIcon(menuTchad, "/images/tchad.png");
        setMenuItemIcon(menuNigeria, "/images/nigeria.png");
        setMenuItemIcon(menuLangueFR, "/images/france.png");
        setMenuItemIcon(menuLangueEN, "/images/royaume-uni.png");
        setMenuItemIcon(menuLangueGE, "/images/allemand.png");

        buttonGroupForTypeData.add(jRadioButtonGeometrique);
        buttonGroupForTypeData.add(jRadioButtonVolumetrique);

//        buttonGroupForGeometrie.add(jRadioButtonCalculIntegral);
//        buttonGroupForGeometrie.add(jRadioButtonMoindresCarrees);
        bgPays.add(menuCameroun);
        bgPays.add(menuGabon);
        bgPays.add(menuTchad);
        bgPays.add(menuNigeria);

        bgLangue.add(menuLangueFR);
        bgLangue.add(menuLangueEN);
        bgLangue.add(menuLangueGE);

        //jMenuBar1.setPreferredSize(new Dimension(310, 45));
        menuCameroun.setPreferredSize(new Dimension(100, 30));
        menuGabon.setPreferredSize(new Dimension(100, 30));
        menuTchad.setPreferredSize(new Dimension(100, 30));
        menuNigeria.setPreferredSize(new Dimension(100, 30));

        menuLangueEN.setPreferredSize(new Dimension(100, 30));
        menuLangueFR.setPreferredSize(new Dimension(100, 30));
        menuLangueGE.setPreferredSize(new Dimension(100, 30));

        container.setLayout(new BorderLayout());
        container.add(couvertureImage, BorderLayout.CENTER);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(navBar, BorderLayout.WEST);
        mainPanel.add(container, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        menuCameroun.addActionListener(new ActionListennerMenuPays());
        menuGabon.addActionListener(new ActionListennerMenuPays());
        menuTchad.addActionListener(new ActionListennerMenuPays());
        menuNigeria.addActionListener(new ActionListennerMenuPays());

        menuLangueFR.addActionListener(new ActionListennerMenuLangue());
        menuLangueEN.addActionListener(new ActionListennerMenuLangue());
        menuLangueGE.addActionListener(new ActionListennerMenuLangue());

        jRadioButtonGeometrique.addActionListener(new ActionListennerDonneeSurSite());
        jRadioButtonVolumetrique.addActionListener(new ActionListennerDonneeSurSite());

//        jRadioButtonCalculIntegral.addActionListener(new ActionListennerMethodCalcul());
        //      jRadioButtonMoindresCarrees.addActionListener(new ActionListennerMethodCalcul());
        labelCopyright.setText("Copyright IPE Sarl " + LocalDate.now().getYear());

        threadAnimation.start();
    }

    private void setMenuItemIcon(JMenuItem menuItem, String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        ImageIcon resizedIcon = redimensionnementIcon(icon, 15, 15);
        menuItem.setIcon(resizedIcon);
    }

    private ImageIcon redimensionnementIcon(ImageIcon imageIcon, int nouvelleLargeur, int nouvelleHauteur) {
        Image image = imageIcon.getImage().getScaledInstance(nouvelleLargeur, nouvelleHauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static String getChoosenActiveMenu() {
        return activeMenu;
    }

    public static String getChoosenLanguage() {
        return chooseLanguage;
    }

    public static String getChooseISO() {
        return chooseISO;
    }

    public static String getTypeDonne() {
        return typeDonneData;
    }

    public static String getMethodCalculGeometrie() {
        return methodCalcul;
    }

    public static String getChoosePays() {
        return choosePays;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        slogan = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        couvertureImage = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        labelCopyright = new javax.swing.JLabel();
        navBar = new javax.swing.JPanel();
        btnTraitement = new javax.swing.JButton();
        btnInfoType = new javax.swing.JButton();
        btnInfoCapacite = new javax.swing.JButton();
        panelDataSurSite = new javax.swing.JPanel();
        labelDonneeSurSite = new javax.swing.JLabel();
        jRadioButtonGeometrique = new javax.swing.JRadioButton();
        jRadioButtonVolumetrique = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        logo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        accueilMenu = new javax.swing.JMenu();
        menuPageGarde = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        menuPays = new javax.swing.JMenu();
        menuCameroun = new javax.swing.JRadioButtonMenuItem();
        menuGabon = new javax.swing.JRadioButtonMenuItem();
        menuTchad = new javax.swing.JRadioButtonMenuItem();
        menuNigeria = new javax.swing.JRadioButtonMenuItem();
        menuLangue = new javax.swing.JMenu();
        menuLangueFR = new javax.swing.JRadioButtonMenuItem();
        menuLangueEN = new javax.swing.JRadioButtonMenuItem();
        menuLangueGE = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(139, 95, 60));
        header.setForeground(new java.awt.Color(255, 255, 255));

        slogan.setFont(new java.awt.Font("Constantia", 2, 24)); // NOI18N
        slogan.setForeground(new java.awt.Color(255, 255, 255));
        slogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slogan.setText("NyettoftTank , Barémer en un clic !");
        header.add(slogan);

        couvertureImage.setBackground(new java.awt.Color(255, 255, 255));
        couvertureImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tank.jpg"))); // NOI18N
        couvertureImage.setOpaque(true);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(couvertureImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(couvertureImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        footer.setBackground(new java.awt.Color(204, 204, 204));

        labelCopyright.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCopyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCopyright.setText("Copyright IPE Sarl 2023");
        footer.add(labelCopyright);

        navBar.setBackground(new java.awt.Color(51, 51, 51));

        btnTraitement.setBackground(new java.awt.Color(255, 255, 254));
        btnTraitement.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnTraitement.setForeground(new java.awt.Color(51, 51, 51));
        btnTraitement.setText("Traitement");
        btnTraitement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTraitementMouseClicked(evt);
            }
        });
        btnTraitement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraitementActionPerformed(evt);
            }
        });

        btnInfoType.setBackground(new java.awt.Color(255, 255, 254));
        btnInfoType.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnInfoType.setForeground(new java.awt.Color(51, 51, 51));
        btnInfoType.setText("Info Type");
        btnInfoType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoTypeMouseClicked(evt);
            }
        });
        btnInfoType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoTypeActionPerformed(evt);
            }
        });

        btnInfoCapacite.setBackground(new java.awt.Color(255, 255, 254));
        btnInfoCapacite.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnInfoCapacite.setForeground(new java.awt.Color(51, 51, 51));
        btnInfoCapacite.setText("Info Capacités");
        btnInfoCapacite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoCapaciteMouseClicked(evt);
            }
        });
        btnInfoCapacite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoCapaciteActionPerformed(evt);
            }
        });

        labelDonneeSurSite.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        labelDonneeSurSite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDonneeSurSite.setText("Méthodes");

        jRadioButtonGeometrique.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButtonGeometrique.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jRadioButtonGeometrique.setText("Géométriques");
        jRadioButtonGeometrique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonGeometriqueMouseClicked(evt);
            }
        });

        jRadioButtonVolumetrique.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButtonVolumetrique.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jRadioButtonVolumetrique.setText("Volumétriques");
        jRadioButtonVolumetrique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonVolumetriqueMouseClicked(evt);
            }
        });
        jRadioButtonVolumetrique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVolumetriqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDataSurSiteLayout = new javax.swing.GroupLayout(panelDataSurSite);
        panelDataSurSite.setLayout(panelDataSurSiteLayout);
        panelDataSurSiteLayout.setHorizontalGroup(
            panelDataSurSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(labelDonneeSurSite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelDataSurSiteLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelDataSurSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonVolumetrique, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonGeometrique, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
        );
        panelDataSurSiteLayout.setVerticalGroup(
            panelDataSurSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataSurSiteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelDonneeSurSite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonGeometrique)
                .addGap(10, 10, 10)
                .addComponent(jRadioButtonVolumetrique)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nyettoTankFtLogo.png"))); // NOI18N

        javax.swing.GroupLayout navBarLayout = new javax.swing.GroupLayout(navBar);
        navBar.setLayout(navBarLayout);
        navBarLayout.setHorizontalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnInfoType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInfoCapacite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTraitement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDataSurSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        navBarLayout.setVerticalGroup(
            navBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navBarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInfoType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInfoCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDataSurSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTraitement, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(navBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1376, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(navBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        accueilMenu.setText("Accueil");
        accueilMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accueilMenuActionPerformed(evt);
            }
        });

        menuPageGarde.setText("Page de garde");
        menuPageGarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPageGardeActionPerformed(evt);
            }
        });
        accueilMenu.add(menuPageGarde);

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        accueilMenu.add(menuExit);

        jMenuBar1.add(accueilMenu);

        menuPays.setText("Pays");

        menuCameroun.setSelected(true);
        menuCameroun.setText("Cameroun");
        menuCameroun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCamerounActionPerformed(evt);
            }
        });
        menuPays.add(menuCameroun);

        menuGabon.setSelected(true);
        menuGabon.setText("Gabon");
        menuPays.add(menuGabon);

        menuTchad.setSelected(true);
        menuTchad.setText("Tchad");
        menuPays.add(menuTchad);

        menuNigeria.setSelected(true);
        menuNigeria.setText("Nigeria");
        menuPays.add(menuNigeria);

        jMenuBar1.add(menuPays);

        menuLangue.setText("Langue");

        menuLangueFR.setSelected(true);
        menuLangueFR.setText("Francais");
        menuLangue.add(menuLangueFR);

        menuLangueEN.setSelected(true);
        menuLangueEN.setText("Anglais");
        menuLangue.add(menuLangueEN);

        menuLangueGE.setSelected(true);
        menuLangueGE.setText("Allemand");
        menuLangue.add(menuLangueGE);

        jMenuBar1.add(menuLangue);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir quitter l'application?", "Quitter l'application", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuExitActionPerformed

    private void btnInfoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoTypeActionPerformed
        threadAnimation.stop();
        activeMenu = "info type";
//        container.setLayout(new GridBagLayout());
//        container.removeAll();
//        infoTypeView.traductionLabel();
//        dataScrollPane = new JScrollPane(infoTypeView);
//        container.add(dataScrollPane,
//                new GridBagConstraints(0, 0, 1, 1, 0, 0,
//                        GridBagConstraints.CENTER,
//                        GridBagConstraints.CENTER,
//                        new Insets(0, 0, 0, 0), 0, 0));
//        container.revalidate();
//        container.repaint();

//        panelOfCalculMethod.setVisible(false);
        container.setLayout(new BorderLayout());
        container.removeAll();
        infoTypeView.traductionLabel();
        dataScrollPane = new JScrollPane(infoTypeView);
        container.add(dataScrollPane, BorderLayout.CENTER);
        container.updateUI();
    }//GEN-LAST:event_btnInfoTypeActionPerformed

    private void btnInfoCapaciteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoCapaciteActionPerformed
        threadAnimation.stop();
        activeMenu = "info capacite";
//        panelOfCalculMethod.setVisible(false);
//        container.setLayout(new BorderLayout());
//        container.removeAll();
//        infoCapaciteView.traductionLabel();
//        dataScrollPane = new JScrollPane(infoCapaciteView);
//        container.add(dataScrollPane, BorderLayout.CENTER);
//        container.updateUI();

        container.setLayout(new GridBagLayout());
        container.removeAll();
        infoCapaciteView.traductionLabel();
        dataScrollPane = new JScrollPane(infoCapaciteView);
        container.add(dataScrollPane,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.CENTER,
                        new Insets(0, 0, 0, 0), 0, 0));
        container.revalidate();
        container.repaint();

    }//GEN-LAST:event_btnInfoCapaciteActionPerformed

    private void btnTraitementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraitementActionPerformed
        threadAnimation.stop();
        activeMenu = "traitement";
        container.setLayout(new GridBagLayout());
        container.removeAll();
        traitement.traductionLabel();
        dataScrollPane = new JScrollPane(traitement);
        container.add(dataScrollPane,
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.CENTER,
                        new Insets(0, 0, 0, 0), 0, 0));
        container.revalidate();
        container.repaint();

//        panelOfCalculMethod.setVisible(false);
//        container.setLayout(new BorderLayout());
//        container.removeAll();
//        traitement.traductionLabel();
//        dataScrollPane = new JScrollPane(traitement);
//        container.add(dataScrollPane, BorderLayout.CENTER);
//        container.updateUI();

    }//GEN-LAST:event_btnTraitementActionPerformed

    private void menuCamerounActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCamerounActionPerformed

    }//GEN-LAST:event_menuCamerounActionPerformed

    private void btnInfoTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoTypeMouseClicked
        btnInfoType.setBackground(ClickedColor);
        btnInfoType.setForeground(Color.WHITE);
        btnInfoCapacite.setBackground(DefaultColor);
        btnInfoCapacite.setForeground(Color.BLACK);
        btnTraitement.setBackground(DefaultColor);
        btnTraitement.setForeground(Color.BLACK);
        panelDataSurSite.setBackground(DefaultColor);
        labelDonneeSurSite.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnInfoTypeMouseClicked

    private void btnInfoCapaciteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoCapaciteMouseClicked
        btnInfoType.setBackground(DefaultColor);
        btnInfoType.setForeground(Color.BLACK);
        btnInfoCapacite.setBackground(ClickedColor);
        btnInfoCapacite.setForeground(Color.WHITE);
        btnTraitement.setBackground(DefaultColor);
        btnTraitement.setForeground(Color.BLACK);
        panelDataSurSite.setBackground(DefaultColor);
        labelDonneeSurSite.setForeground(Color.BLACK);

    }//GEN-LAST:event_btnInfoCapaciteMouseClicked

    private void btnTraitementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTraitementMouseClicked
        btnInfoType.setBackground(DefaultColor);
        btnInfoType.setForeground(Color.BLACK);
        btnInfoCapacite.setBackground(DefaultColor);
        btnInfoCapacite.setForeground(Color.BLACK);
        btnTraitement.setBackground(ClickedColor);
        btnTraitement.setForeground(Color.WHITE);
        panelDataSurSite.setBackground(DefaultColor);
        labelDonneeSurSite.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnTraitementMouseClicked

    private void accueilMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accueilMenuActionPerformed
        container.setLayout(new FlowLayout());
        container.removeAll();
        container.add(homePage);
        container.repaint();
        container.revalidate();
    }//GEN-LAST:event_accueilMenuActionPerformed

    private void menuPageGardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPageGardeActionPerformed
        container.setLayout(new BorderLayout());
        container.removeAll();
        container.add(homePage, BorderLayout.CENTER);
        container.repaint();
        container.revalidate();
    }//GEN-LAST:event_menuPageGardeActionPerformed

    private void jRadioButtonGeometriqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonGeometriqueMouseClicked
        btnInfoType.setBackground(DefaultColor);
        btnInfoType.setForeground(Color.BLACK);
        btnInfoCapacite.setBackground(DefaultColor);
        btnInfoCapacite.setForeground(Color.BLACK);
        btnTraitement.setBackground(DefaultColor);
        btnTraitement.setForeground(Color.BLACK);
        panelDataSurSite.setBackground(ClickedColor);
        labelDonneeSurSite.setForeground(Color.WHITE);
    }//GEN-LAST:event_jRadioButtonGeometriqueMouseClicked

    private void jRadioButtonVolumetriqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVolumetriqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonVolumetriqueActionPerformed

    private void jRadioButtonVolumetriqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonVolumetriqueMouseClicked
        btnInfoType.setBackground(DefaultColor);
        btnInfoType.setForeground(Color.BLACK);
        btnInfoCapacite.setBackground(DefaultColor);
        btnInfoCapacite.setForeground(Color.BLACK);
        btnTraitement.setBackground(DefaultColor);
        btnTraitement.setForeground(Color.BLACK);
        panelDataSurSite.setBackground(ClickedColor);
        labelDonneeSurSite.setForeground(Color.WHITE);
    }//GEN-LAST:event_jRadioButtonVolumetriqueMouseClicked

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainView().setVisible(true);
//            }
//        });
//    }
    class ActionListennerMenuLangue implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (menuLangueFR.isSelected()) {
                chooseLanguage = "FRANCAIS";
            } else if (menuLangueEN.isSelected()) {
                chooseLanguage = "ANGLAIS";
            } else if (menuLangueGE.isSelected()) {
                chooseLanguage = "ALLEMAND";
            } else {
                chooseLanguage = "FRANCAIS";
            }

            switch (activeMenu) {
                case "info type":
                    infoTypeView.traductionLabel();
                    break;
                case "info capacite":
                    infoCapaciteView.traductionLabel();
                    break;
                case "geometrique":
                    donneeGeometrique.traductionLabel();
                    break;
                case "volumetrique":
                    donneeVolumetrie.traductionLabel();
                    break;
                case "traitement":
                    traitement.traductionLabel();
                    break;
            }

            ResourceBundle valeurs = null;
            if (chooseLanguage.equalsIgnoreCase("FRANCAIS") || chooseLanguage.equalsIgnoreCase("FRENCH") || chooseLanguage.equalsIgnoreCase("FRANZÖSISCH")) {
                valeurs = val_fr;
            } else if (chooseLanguage.equalsIgnoreCase("ANGLAIS") || chooseLanguage.equalsIgnoreCase("ENGLISH") || chooseLanguage.equalsIgnoreCase("ENGLISCH")) {
                valeurs = val_en;
            } else if (chooseLanguage.equalsIgnoreCase("ALLEMAND") || chooseLanguage.equalsIgnoreCase("GERMAN") || chooseLanguage.equalsIgnoreCase("DEUTSCH")) {
                valeurs = val_de;
            }

            btnInfoType.setText(valeurs.getString("btnInfoType"));
            btnInfoCapacite.setText(valeurs.getString("btnInfoCapacite"));
            btnTraitement.setText(valeurs.getString("btnTraitement"));
            labelCopyright.setText(valeurs.getString("labelCopyright"));
            slogan.setText(valeurs.getString("slogan"));
            accueilMenu.setText(valeurs.getString("accueilMenu"));
            menuPays.setText(valeurs.getString("menuPays"));
            menuLangue.setText(valeurs.getString("menuLangue"));
            menuCameroun.setText(valeurs.getString("menuCameroun"));
            menuGabon.setText(valeurs.getString("menuGabon"));
            menuTchad.setText(valeurs.getString("menuTchad"));
            menuNigeria.setText(valeurs.getString("menuNigeria"));
            menuLangueFR.setText(valeurs.getString("menuLangueFR"));
            menuLangueEN.setText(valeurs.getString("menuLangueEN"));
            menuLangueGE.setText(valeurs.getString("menuLangueGE"));
            jRadioButtonGeometrique.setText(valeurs.getString("typeData1"));
            jRadioButtonVolumetrique.setText(valeurs.getString("typeData2"));
            labelDonneeSurSite.setText(valeurs.getString("donneSurSite"));
//            jRadioButtonCalculIntegral.setText(valeurs.getString("calcul1"));
            //          jRadioButtonMoindresCarrees.setText(valeurs.getString("calcul2"));
        }
    }

    class ActionListennerDonneeSurSiteV2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jRadioButtonGeometrique.isSelected()) {
                threadAnimation.stop();
                activeMenu = "geometrique";
                typeDonneData = "geometrique";

                //panelOfCalculMethod.setVisible(false);
                //jRadioButtonCalculIntegral.setSelected(true);
                btnTraitement.setEnabled(true);
                container.setLayout(new BorderLayout());

                container.removeAll();
                donneeGeometrique.traductionLabel();
                dataScrollPane = new JScrollPane(donneeGeometrique);
                donneeGeometrique.updateComboIndex();
                container.add(dataScrollPane, BorderLayout.CENTER);

//                if (jRadioButtonCalculIntegral.isSelected()) {
//                                    System.out.println("le bouton geometrique est actif   ");
//
//                    methodCalcul = "Calcul intégral";
//                    btnTraitement.setEnabled(true);
//                    container.setLayout(new BorderLayout());
//
//                    container.removeAll();
//                    donneeGeometrique.traductionLabel();
//                    dataScrollPane = new JScrollPane(donneeGeometrique);
//                    donneeGeometrique.updateComboIndex();
//                    container.add(dataScrollPane, BorderLayout.CENTER);
//                } else {
//                    jRadioButtonCalculIntegral.setSelected(false);
//
//                }
//                container.setLayout(new BorderLayout());
//
//                container.removeAll();
//                donneeGeometrique.traductionLabel();
//                dataScrollPane = new JScrollPane(donneeGeometrique);
//                container.add(dataScrollPane, BorderLayout.CENTER);
            } else if (jRadioButtonVolumetrique.isSelected()) {
                threadAnimation.stop();
                activeMenu = "volumetrique";
                typeDonneData = "volumetrique";

//                panelOfCalculMethod.setVisible(false);
                btnTraitement.setEnabled(true);

                container.setLayout(new BorderLayout());
                container.removeAll();
                donneeVolumetrie.traductionLabel();
                dataScrollPane = new JScrollPane(donneeVolumetrie);
                container.add(dataScrollPane, BorderLayout.CENTER);
                container.updateUI();

//                container.setLayout(new GridBagLayout());
//
//                container.removeAll();
//                donneeVolumetrie.traductionLabel();
//                container.add(donneeVolumetrie,
//                        new GridBagConstraints(0, 0, 1, 1, 0, 0,
//                                GridBagConstraints.CENTER,
//                                GridBagConstraints.CENTER,
//                                new Insets(0, 0, 0, 0), 0, 0));
            }
            //container.revalidate();
            //container.repaint();
        }

    }

    class ActionListennerDonneeSurSite implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jRadioButtonGeometrique.isSelected()) {
                threadAnimation.stop();
                activeMenu = "geometrique";
                typeDonneData = "geometrique";
                methodCalcul = "Calcul intégral";
                btnTraitement.setEnabled(true);
                container.setLayout(new BorderLayout());

                container.removeAll();
                donneeGeometrique.traductionLabel();
                dataScrollPane = new JScrollPane(donneeGeometrique);
                container.add(dataScrollPane, BorderLayout.CENTER);
                container.updateUI();

            } else if (jRadioButtonVolumetrique.isSelected()) {
                threadAnimation.stop();
                activeMenu = "volumetrique";
                typeDonneData = "volumetrique";

                btnTraitement.setEnabled(true);

                container.setLayout(new BorderLayout());
                container.removeAll();
                donneeVolumetrie.traductionLabel();
                dataScrollPane = new JScrollPane(donneeVolumetrie);
                container.add(dataScrollPane, BorderLayout.CENTER);
                container.updateUI();
            }
        }

    }

    class ActionListennerMenuPays implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (menuCameroun.isSelected()) {
                choosePays = "Cameroun";
            } else if (menuGabon.isSelected()) {
                choosePays = "Gabon";
            } else if (menuTchad.isSelected()) {
                choosePays = "Tchad";
            } else if (menuNigeria.isSelected()) {
                choosePays = "Nigeria";
            } else {
                choosePays = "Cameroun";
            }
        }
    }

    private Thread threadAnimation = new Thread() {
        public void run() {

            int width = container.getWidth(), height = container.getHeight();
            //container.removeAll();
            couvertureImage.setPreferredSize(new Dimension(width, height));

            while (true) {
                try {
                    //PanelImage  couvertureImage = new PanelImage("nigeria.png");
                    //
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion.png"))); // NOI18N
                    Thread.sleep(1000);
                    System.out.println("un");
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion1.png"))); // NOI18N
                    System.out.println("deux");
                    Thread.sleep(1000);
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tank.jpg"))); // NOI18N
                    System.out.println("trois");
                    Thread.sleep(1000);
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion3.png"))); // NOI18N
                    System.out.println("quatre");
                    Thread.sleep(1000);
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion4.jpeg"))); // NOI18N
                    System.out.println("cinq");
                    Thread.sleep(1000);
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion5.png"))); // NOI18N
                    System.out.println("six");
                    Thread.sleep(1000);
                    couvertureImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion6.png"))); // NOI18N
                    System.out.println("sept");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    };

    private ResourceBundle val_fr = ResourceBundle.getBundle("Bombe_fr", Locale.FRENCH);
    private ResourceBundle val_en = ResourceBundle.getBundle("Bombe_en", Locale.ENGLISH);
    private ResourceBundle val_de = ResourceBundle.getBundle("Bombe_de", Locale.GERMANY);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu accueilMenu;
    private javax.swing.JButton btnInfoCapacite;
    private javax.swing.JButton btnInfoType;
    private javax.swing.JButton btnTraitement;
    private javax.swing.JPanel container;
    private javax.swing.JLabel couvertureImage;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRadioButtonGeometrique;
    private javax.swing.JRadioButton jRadioButtonVolumetrique;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCopyright;
    private javax.swing.JLabel labelDonneeSurSite;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButtonMenuItem menuCameroun;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JRadioButtonMenuItem menuGabon;
    private javax.swing.JMenu menuLangue;
    private javax.swing.JRadioButtonMenuItem menuLangueEN;
    private javax.swing.JRadioButtonMenuItem menuLangueFR;
    private javax.swing.JRadioButtonMenuItem menuLangueGE;
    private javax.swing.JRadioButtonMenuItem menuNigeria;
    private javax.swing.JMenuItem menuPageGarde;
    private javax.swing.JMenu menuPays;
    private javax.swing.JRadioButtonMenuItem menuTchad;
    private javax.swing.JPanel navBar;
    private javax.swing.JPanel panelDataSurSite;
    private javax.swing.JLabel slogan;
    // End of variables declaration//GEN-END:variables
}
