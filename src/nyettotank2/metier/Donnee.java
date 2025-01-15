package nyettotank2.metier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Donnee extends JPanel {

    private JPanel panel1, panel2, panel3;
    private JOptionPane jop1;
    private JTextField volume, hauteur;
    private int line_tab;
    private ZModel model;

    private JTable tableau;
    private Hashtable donneGenerale, donneeDonnee;
    private JLabel haut, vol;

    public void setNombreLigneTableau(int line) {
        line_tab = line;
    }

    public int getNombreLigneTableau() {
        return line_tab;
    }

    private JButton table;
    private JButton certificat;
    private JButton button, valider;
    private JPanel remplir_tab = new JPanel();

    public Donnee produireTableau(String unitVolume, String unitHeight) {

      
        button = new JButton("AJOUTER LIGNE");
        valider = new JButton("SUPPRIMER LIGNE");

        line_tab = line_tab % 1000;
        line_tab++;
        Object[][] data = new Object[line_tab][2];
        for (int i = 0; i < line_tab; i++) {
            data[i][0] = (i + 1) * 1000;
            data[i][1] = " ";
        }
        model = new ZModel(unitVolume, unitHeight);
        tableau = new JTable(model);
        tableau.setRowHeight(35);

        button.addActionListener(e -> model.addLine(new Line(0, 0)));

        valider.addActionListener(e -> model.removeLine(tableau.getSelectedRow()));

        tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // selection simple
        //tableau.setBorder( BorderFactory.createEmptyBorder(100, 0, 0, 0) );

        JPanel panelButton = new JPanel();
        button.setPreferredSize(new Dimension(200, 30));
        valider.setPreferredSize(new Dimension(200, 30));
        panelButton.add(button);
        panelButton.add(valider);

        remplir_tab.setBackground(Color.GRAY);
        remplir_tab.add(new JScrollPane(tableau));
        remplir_tab.setPreferredSize(new Dimension(500, 400));

        this.add(new JScrollPane(tableau));
        this.add(panelButton);

        this.setLayout(new FlowLayout());
        return this;

    }

    public ZModel getModel() {
        return model;
    }

    public void setModel(ZModel model) {
        this.model = model;
    }

    public Donnee() {
    }

    public void setDonneGenerale(Hashtable t) {
        donneGenerale = t;
    }

    public Hashtable getDonneGenerale() {
        return donneGenerale;
    }

    //public void setDonneeDonnee( Hashtable  t ){ donneeDonnee = t; } 
    public JTable getDonneeDonnee() {
        return tableau;
    }

}
