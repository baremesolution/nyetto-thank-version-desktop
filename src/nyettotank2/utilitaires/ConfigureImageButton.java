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
    public static String logoName;
    public static String signatureName;   

//    public File showImage(JButton button, JLabel label, int width, int height, boolean imageAlreadySelected) throws MalformedURLException, IOException {
//    if (imageAlreadySelected) {
//        
//    }
//
//    JFileChooser fileChooser = new JFileChooser();
//    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers images", "jpg", "jpeg", "png", "gif");
//    fileChooser.setFileFilter(filter);
//    int returnVal = fileChooser.showOpenDialog(null);
//    if (returnVal == JFileChooser.APPROVE_OPTION) {
//        File file = fileChooser.getSelectedFile();
//        System.out.println("Fichier sélectionné: " + file.getAbsolutePath());
//
//        // Redimensionnement de l'image
//        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
//        Image image = icon.getImage();
//        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        label.setIcon(new ImageIcon(scaledImage));
//        String fileName = file.getName();
//
//        // Création du répertoire si nécessaire
//        String imageDirectory = "imgSave";
//        Path imagePath = Paths.get(imageDirectory);
//        if (!Files.exists(imagePath)) {
//            Files.createDirectories(imagePath);
//        }
//
//        // Chemin complet du fichier
//        Path destinationPath = imagePath.resolve(fileName);
//
//        // Supprimer le fichier existant si nécessaire
//        if (Files.exists(destinationPath)) {
//            Files.delete(destinationPath);
//        }
//
//        // Copier le fichier
//        try (InputStream input = file.toURI().toURL().openStream()) {
//            Files.copy(input, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("Image copiée à: " + destinationPath.toString());
//        } catch (IOException e) {
//            System.out.println("Erreur lors de la copie de l'image");
//            throw new IOException("Erreur lors de la copie de l'image", e);
//        }
//
//        return file;
//    } else {
//        System.out.println("Aucun fichier sélectionné.");
//    }
//    return null;
//}
   
    
    public File showImage(JButton button, JLabel label, int width, int height, boolean imageAlreadySelected) throws MalformedURLException, IOException {
    if (imageAlreadySelected) {
       
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers images", "jpg", "jpeg", "png", "gif");
    fileChooser.setFileFilter(filter);
    int returnVal = fileChooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        System.out.println("Fichier sélectionné: " + file.getAbsolutePath());

        if (!file.exists() || !file.canRead()) {
             System.err.println("Le fichier source n'existe pas ou n'est pas lisible.");
            showImageErrorDialog("Le fichier source n'existe pas ou n'est pas lisible.");           
        }

        // Redimensionnement de l'image
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
        String fileName = file.getName();

        // Création du répertoire si nécessaire
        String imageDirectory = "images";
        Path imagePath = Paths.get(imageDirectory);
        if (!Files.exists(imagePath)) {
            Files.createDirectories(imagePath);
            System.out.println("Répertoire créé: " + imagePath.toAbsolutePath());
        } else {
            System.out.println("Répertoire existant: " + imagePath.toAbsolutePath());
        }

        // Chemin complet du fichier
        Path destinationPath = imagePath.resolve(fileName);

        // Supprimer le fichier existant si nécessaire
        if (Files.exists(destinationPath)) {
            Files.delete(destinationPath);
        }

        // Copier le fichier
        try (InputStream input = file.toURI().toURL().openStream()) {
            Files.copy(input, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image copiée à: " + destinationPath.toString());
        } catch (IOException e) {
            System.out.println("Erreur lors de la copie de l'image");
             showImageErrorDialog("Erreur lors de la copie de l'image");    
            throw new IOException("Erreur lors de la copie de l'image", e);
        }

        return file;
    } else {
        System.out.println("Aucun fichier sélectionné.");
    }
    return null;
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


    
//public File showImage(JButton button, JLabel label, int width, int height, boolean imageAlreadySelected) throws MalformedURLException, IOException {
//    if (imageAlreadySelected) {
//        System.out.println("Image déjà sélectionnée, retour de null.");
//        return null; // Return early if the image is already selected
//    }
//
//    JFileChooser fileChooser = new JFileChooser();
//    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//            "Fichiers images", "jpg", "jpeg", "png", "gif");
//    fileChooser.setFileFilter(filter);
//    int returnVal = fileChooser.showOpenDialog(null);
//    if (returnVal == JFileChooser.APPROVE_OPTION) {
//        File file = fileChooser.getSelectedFile();
//        System.out.println("Fichier sélectionné: " + file.getAbsolutePath());
//        // Redimensionnement de l'image
//        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
//        Image image = icon.getImage();
//        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        // Affectation de l'image redimensionnée à l'icône du JLabel
//        label.setIcon(new ImageIcon(scaledImage));
//        String fileName = file.getName();
//        String[] chs = fileName.split("\\.");
//        String timestampedFileName = (new Date()).getTime() + "_" + fileName;
//
//        if (button.getText().equals("Ajouter un logo")) {
//            try (InputStream logo = file.toURI().toURL().openStream()) {
//                String logoDirectory = "logo";
//                Path logoPath = Paths.get(logoDirectory);
//                if (!Files.exists(logoPath)) {
//                    Files.createDirectories(logoPath);
//                }
//                logoName = logoDirectory + "/" + timestampedFileName; 
//                
//                System.out.println("Répertoire de travail courant : " + System.getProperty("user.dir"));
//                System.out.println("Chemin du fichier de logo: " + logoName);
//                Path path_file = Files.createFile(Paths.get(logoName));
//                
//                Files.copy(logo, path_file, StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Logo copié à: " + logoName);
//            } catch (IOException e) {
//                System.out.println("Erreur lors de la copie du fichier logo");
//                throw new IOException("Erreur lors de la copie du fichier logo", e);
//            }
//        } else {
//            try (InputStream signature = file.toURI().toURL().openStream()) {
//                  String logoDirectory = "logo";
//                Path logoPath = Paths.get(logoDirectory);
//                if (!Files.exists(logoPath)) {
//                    Files.createDirectories(logoPath);
//                }
//                logoName = logoDirectory + "/" + timestampedFileName; 
//              
//                Path path_file = Files.createFile(Paths.get(logoName));
//                Files.copy(signature, path_file, StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Signature copiée à: " + signatureName);
//            } catch (IOException e) {
//                System.out.println("Erreur lors de la copie du fichier signature");
//                throw new IOException("Erreur lors de la copie du fichier signature", e);
//            }
//        }
//        return file;
//    } else {
//        System.out.println("Aucun fichier sélectionné.");
//    }
//    return null;
//}

    
    
//        public void saveImage(File imageFile, String nomImage) {
//    if (imageFile != null && imageFile.exists()) { // Vérifie si le fichier existe
//        String destinationDirectory = "images\\";
//
//        Path destinationImagePath = Paths.get(destinationDirectory + nomImage);
//
//        try {
//            Files.copy(imageFile.toPath(), destinationImagePath, StandardCopyOption.REPLACE_EXISTING);
//            // Fermer le flux n'est pas nécessaire car nous n'utilisons pas de flux externe
//        } catch (IOException e) {
//            System.err.println("Erreur lors de l'enregistrement de l'image : " + e.getMessage());
//        }
//    } else {
//        System.err.println("Le fichier d'image est null ou n'existe pas.");
//    }
//}

//    public void saveImage(File image, String nomImage) throws IOException {
//        if (image != null) {
//            String destinationDirectory = "D:\\IPE Sarl\\Project repo\\images\\";
//
//            Path sourceImagePath = image.toPath();
//            Path destinationImagePath = Paths.get(destinationDirectory + nomImage);
//            Files.move(sourceImagePath, destinationImagePath, StandardCopyOption.REPLACE_EXISTING);
//        }
//
//    }
    
    
    
    public static void sterchImage(JLabel label)  {  
        Icon icon = label.getIcon();
        ImageIcon imageIcon = (ImageIcon) icon;
        Image image = imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }
    
       private void showImageErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
