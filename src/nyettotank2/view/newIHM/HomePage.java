package nyettotank2.view.newIHM;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage extends javax.swing.JPanel {

    public HomePage() {
        initComponents();
        threadAnimation.start();
    }
    private Thread threadAnimation = new Thread() {
        public void run() {

            int width = container.getWidth(), height = container.getHeight();
            //container.removeAll();
            logo.setPreferredSize(new Dimension(width, height));

            while (true) {
                try {
                    //PanelImage  logo = new PanelImage("nigeria.png");
                    //
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion.png"))); // NOI18N
                    Thread.sleep(1000);
                    System.out.println("un");
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion1.png"))); // NOI18N
                    System.out.println("deux");
                    Thread.sleep(1000);
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tank.jpg"))); // NOI18N
                    System.out.println("trois");
                    Thread.sleep(1000);
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion3.png"))); // NOI18N
                    System.out.println("quatre");
                    Thread.sleep(1000);
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion4.jpeg"))); // NOI18N
                    System.out.println("cinq");
                    Thread.sleep(1000);
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion5.png"))); // NOI18N
                    System.out.println("six");
                    Thread.sleep(1000);
                    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageinsertion6.png"))); // NOI18N
                    System.out.println("sept");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();

        container.setBackground(new java.awt.Color(255, 255, 255));

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tank.jpg"))); // NOI18N
        logo.setOpaque(true);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
