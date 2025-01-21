package nyettotank2.utilitaires;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Cynthia
 */
public class ConfigureImageButton {
    private InputStream logo;
    private InputStream signature;
    public static String extensionLogo = "";
    public static String extensionSignature="";   
    private ManageInternationalize manageInternationalize = new ManageInternationalize();
   
    
    public File showImageLogo(JButton button, JLabel label, int width, int height, boolean imageAlreadySelected) throws MalformedURLException, IOException {
    if (imageAlreadySelected) {
       
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers images", "jpg", "jpeg", "png");
    fileChooser.setFileFilter(filter);
    int returnVal = fileChooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
                extensionLogo = file.getName().split("\\.")[1];

        if (!file.exists() || !file.canRead()) {
            showImageErrorDialog( manageInternationalize.translate("error_lisibilite_file") );           
        }

        // Redimensionnement de l'image
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));

        return file;
    } 
    else {
        extensionLogo="";
                label.setIcon(new ImageIcon( ));
        showImageErrorDialog( manageInternationalize.translate("none_file_selected") );
        return null;
    }
   // return null;
}

    
     public File showImageSignature(JButton button, JLabel label, int width, int height, boolean imageAlreadySelected) throws MalformedURLException, IOException {
    if (imageAlreadySelected) {
       
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers images", "jpg", "jpeg", "png");
    fileChooser.setFileFilter(filter);
    int returnVal = fileChooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        extensionSignature = file.getName().split("\\.")[1];

        if (!file.exists() || !file.canRead()) {
            showImageErrorDialog( manageInternationalize.translate("error_lisibilite_file") );           
        }

        // Redimensionnement de l'image
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
        return file;
    } 
    else {
        extensionSignature="";
                label.setIcon(new ImageIcon( ));
        showImageErrorDialog( manageInternationalize.translate("none_file_selected") );
        return null;
    }
   // return null;
}

   
    public void saveImage(File imageFile, String nomImage) {
    if (imageFile != null && imageFile.exists()) {
        String imageDirectory = "images";
        Path imagePath = Paths.get(imageDirectory);
        if (!Files.exists(imagePath)) {
            try {
                Files.createDirectories(imagePath);
                System.out.println("Répertoire créé: " + imagePath.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erreur lors de la création du répertoire : " + e.getMessage());
                return;
            }
        }

        Path destinationPath = imagePath.resolve(nomImage);

        // Supprimer le fichier existant si nécessaire
        if (Files.exists(destinationPath)) {
            try {
                Files.delete(destinationPath);
            } catch (IOException e) {
                System.err.println("Erreur lors de la suppression de l'image existante : " + e.getMessage());
            }
        }

        // Copier le nouveau fichier
        try {
            Files.copy(imageFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement de l'image : " + e.getMessage());
        }
    } else {
        System.err.println("Le fichier d'image est null ou n'existe pas.");
        showImageErrorDialog("Le fichier d'image est null ou n'existe pas");
    }
}
    
    
    public static void sterchImage(JLabel label)  {  
        Icon icon = label.getIcon();
        ImageIcon imageIcon = (ImageIcon) icon;
        Image image = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }
    
       private void showImageErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    public static String getExtensionSignature() {
        return extensionSignature;
    }

    public static String getExtensionLogo() {
        return extensionLogo;
    }

}
