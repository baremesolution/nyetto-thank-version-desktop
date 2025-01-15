import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageApp {

    private static String currentLanguage = "FRANCAIS"; // Default language

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }

    static class MainFrame extends JFrame {

        private JMenuBar menuBar;
        private JMenu languageMenu;
        private JMenu fileMenu;
        private JPanel navbar;
        private JPanel container;
        private JButton btnInfoType;
        private JButton btnInfoCapacite;

        public MainFrame() {
            setTitle("Language App");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);

            menuBar = new JMenuBar();
            languageMenu = new JMenu("Language");
            fileMenu = new JMenu("File");
            menuBar.add(languageMenu);
            menuBar.add(fileMenu);
            setJMenuBar(menuBar);

            JMenuItem menuItemFR = new JMenuItem("Français");
            JMenuItem menuItemEN = new JMenuItem("English");
            languageMenu.add(menuItemFR);
            languageMenu.add(menuItemEN);

            navbar = new JPanel();
            navbar.setLayout(new GridLayout(2, 1));
            btnInfoType = new JButton("Info Type");
            btnInfoCapacite = new JButton("Info Capacité");
            navbar.add(btnInfoType);
            navbar.add(btnInfoCapacite);

            container = new JPanel();
            container.setLayout(new CardLayout());

            add(navbar, BorderLayout.WEST);
            add(container, BorderLayout.CENTER);

            menuItemFR.addActionListener(new LanguageChangeListener("FRANCAIS"));
            menuItemEN.addActionListener(new LanguageChangeListener("ENGLISH"));

            btnInfoType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateContainer(new InfoTypePanel());
                }
            });

            btnInfoCapacite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateContainer(new InfoCapacitePanel());
                }
            });

            updateLanguage();
        }

        private void updateContainer(JPanel newPanel) {
            container.removeAll();
            container.add(newPanel);
            container.revalidate();
            container.repaint();
        }

        private void updateLanguage() {
            Locale[] locales = {Locale.FRENCH, Locale.ENGLISH};
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", locales[getIndexForLanguage(currentLanguage)]);

            languageMenu.setText(bundle.getString("languageMenu"));
            fileMenu.setText(bundle.getString("fileMenu"));
            btnInfoType.setText(bundle.getString("btnInfoType"));
            btnInfoCapacite.setText(bundle.getString("btnInfoCapacite"));
        }

        private int getIndexForLanguage(String language) {
            if (language.equalsIgnoreCase("FRANCAIS")) {
                return 0;
            } else {
                return 1;
            }
        }

        private class LanguageChangeListener implements ActionListener {
            private String language;

            public LanguageChangeListener(String language) {
                this.language = language;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                currentLanguage = language;
                updateLanguage();
            }
        }
    }

    static class InfoTypePanel extends JPanel {
        public InfoTypePanel() {
            add(new JLabel("Info Type Panel"));
        }
    }

    static class InfoCapacitePanel extends JPanel {
        public InfoCapacitePanel() {
            add(new JLabel("Info Capacité Panel"));
        }
    }
}
