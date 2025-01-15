package nyettotank2.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import java.time.format.DateTimeFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class Volmetrie {

    public Volmetrie() {
    }

    private JOptionPane location = new JOptionPane();

    public String verifie(HashMap s, String key) {
        if (s.containsKey(key)) {
            return (String) s.get(key);
        } else {
            return "";
        }
    }

    public String twoDecimale(double value, String typeValue) {

        if (typeValue.equals("decimale")) {

            String ch = "" + value;
            int pos = ch.indexOf(".");
            if (pos >= 0) {
                if ((ch.length() - pos) > 4) {
                    return ch.substring(0, pos + 4);
                } else {
                    return ch;
                }
            }
            return ch;

        } else {

            return Integer.toString((int) Math.floor(value));

        }

    }

    public void genererTableVolumetrie(HashMap info, HashMap data, List<Line> myLine) {

        if (info.containsKey("nombre divisions")) {

            BaremeArtisan bareme = new BaremeArtisan();
            String unit = info.get("unite des hauteurs").toString().toLowerCase();

            String nom = (String) info.get("table");
            String typeValueReturn = (String) info.get("type value");

            try (org.apache.poi.ss.usermodel.Workbook workbook = new HSSFWorkbook()) {

                CellStyle styleHeader = workbook.createCellStyle();
                styleHeader.setAlignment(HorizontalAlignment.CENTER);
                Font fontHeader = workbook.createFont();
                fontHeader.setBold(true);
                styleHeader.setFont(fontHeader);

                CellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);

                Sheet sheet = workbook.createSheet("Volumétrie");

                int nb_div = Integer.parseInt(info.get("nombre divisions").toString());

                List<Float> abcisse = new ArrayList<>();
                List<Double> ordonne = new ArrayList<>();
                for (int i = 0; i < myLine.size(); i++) {
                    abcisse.add(i, myLine.get(i).getHauteur());
                    ordonne.add(i, myLine.get(i).getVolume());
                }

                ordonne.add(0, 0d);
                abcisse.add(0, (float) 0.00);

                float lastHeight = bareme.convertToCentimeter(unit, abcisse.get(abcisse.size() - 1));
                int lineNumber = (int) Math.floor(lastHeight);

                int nb_group = (int) (lineNumber / nb_div);

                Sline fonction = new Sline(abcisse, ordonne);

                Row header = sheet.createRow(8);

                org.apache.poi.ss.usermodel.Cell cell1 = header.createCell(4);
                cell1.setCellValue("CERTIIFICAT DE BAREMAGE");
                cell1.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(8, 9, 4, 8));

                org.apache.poi.ss.usermodel.Cell cell2 = header.createCell(9);
                cell2.setCellValue("/ " + (LocalDateTime.now()).getYear() + " /MINCOMMERCE/DMQP/SIV");
                cell2.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(8, 9, 9, 14));

                Row header1 = sheet.createRow(10);

                org.apache.poi.ss.usermodel.Cell cell3 = header1.createCell(4);
                cell3.setCellValue("S/S " + verifie(info, "detenteur") + " " + verifie(info, "lieu des operations"));
                cell3.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(10, 11, 4, 7));

                org.apache.poi.ss.usermodel.Cell cell4 = header1.createCell(8);
                cell4.setCellValue(verifie(info, "type de la capacite") + " : " + verifie(info, "produit stocke"));
                cell4.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(10, 11, 8, 10));

                org.apache.poi.ss.usermodel.Cell cell5 = header1.createCell(11);
                cell5.setCellValue("Capacite : " + verifie(info, "volume nominal") + " " + verifie(info, "unite de volume"));
                cell5.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(10, 11, 11, 14));

                Row space = sheet.createRow(12);
                org.apache.poi.ss.usermodel.Cell cell6 = space.createCell(4);
                cell6.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(12, 12, 4, 14));

                double volume_residuel = 0;
                if (data.containsKey("volume residuel")) {
                    volume_residuel = Double.parseDouble(data.get("volume residuel").toString());
                }

                Row header2 = sheet.createRow(13);
                for (int i = 0; i < nb_group; i++) {

                    org.apache.poi.ss.usermodel.Cell cell7 = header2.createCell(i * 3 + 4);
                    cell7.setCellValue("H(cm)");
                    cell7.setCellStyle(styleHeader);

                    org.apache.poi.ss.usermodel.Cell cell8 = header2.createCell(cell7.getColumnIndex() + 1);
                    cell8.setCellValue("V(litre)");
                    cell8.setCellStyle(styleHeader);

                    org.apache.poi.ss.usermodel.Cell cell9 = header2.createCell(cell8.getColumnIndex() + 1);
                    cell9.setCellValue(" ");
                }

                // remplissage des donnees
                int t = 0;

                for (int j = 1; j <= nb_div; j++) {
                    Row row = sheet.createRow(13 + j);

                    for (int i = 0; i < nb_group; i++) {
                        float vol_fleche = (float) 0.00;
                        t = i * nb_div + j;
                        unit = info.get("unite des hauteurs").toString();

                        if (t <= lineNumber) {
                            float xx = bareme.convertToUnitDesire(unit, t);
                            double volumeCalc = fonction.interpolate(xx);
                            String ch = twoDecimale(volumeCalc + volume_residuel, typeValueReturn);

                            org.apache.poi.ss.usermodel.Cell cell10 = row.createCell(i * 3 + 4);
                            cell10.setCellStyle(style);
                            cell10.setCellValue(t);

                            org.apache.poi.ss.usermodel.Cell cell11 = row.createCell(i * 3 + 5);
                            cell11.setCellStyle(style);
                            cell11.setCellValue(ch);

                            org.apache.poi.ss.usermodel.Cell cell12 = row.createCell(i * 3 + 6);
                            cell12.setCellValue(" ");
                        }
                    }
                }

                // Ajout de la ligne de footer
                Row footerRow = sheet.createRow(13 + nb_div + 1);

                LocalDateTime currentDate = LocalDateTime.now();
                // Formatter la date avec le format "dd/MM/yyyy"
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(dateFormatter);

                org.apache.poi.ss.usermodel.Cell cell13 = footerRow.createCell(4);
                cell13.setCellValue("Diamtre " + verifie(data, "diametre") + " " + verifie(info, "unite des hauteurs"));
                cell13.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(footerRow.getRowNum(), footerRow.getRowNum() + 1, 4, 7));

                org.apache.poi.ss.usermodel.Cell cell14 = footerRow.createCell(8);
                cell14.setCellValue(formattedDate);
                cell14.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(footerRow.getRowNum(), footerRow.getRowNum() + 1, 8, 11));

                org.apache.poi.ss.usermodel.Cell cell15 = footerRow.createCell(12);
                cell15.setCellValue("E.N.E.M");
                cell15.setCellStyle(styleHeader);
                sheet.addMergedRegion(new CellRangeAddress(footerRow.getRowNum(), footerRow.getRowNum() + 1, 12, 14));

//                // Sauvegarde 
//                String filePath = System.getProperty("user.home") + "/Documents/" + nom + ".xls";
//                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//                workbook.write(fileOutputStream);
//                fileOutputStream.close();
//                workbook.close();
//
//                location.showMessageDialog(null, "Votre FICHIER SE TROUVE DANS \n  " + filePath, "LOCALISATION DU FICHIER",
//                        JOptionPane.INFORMATION_MESSAGE);
//
//                String documentDirectory = System.getProperty("user.home") + "/Documents/";
//                String nyettofDirectory = documentDirectory + "NyettoftTank_files/";
//
//                // Créer le répertoire si nécessaire
//                File nyettofFile = new File(nyettofDirectory);
//                if (!nyettofFile.exists()) {
//                    nyettofFile.mkdirs();
//                }
//                String filePath = nyettofDirectory + nom + ".xls";
//
//                JOptionPane.showMessageDialog(null, "Votre FICHIER SE TROUVE DANS \n  " + filePath, "LOCALISATION DU FICHIER",
//                        JOptionPane.INFORMATION_MESSAGE);

                // Définir les répertoires pour le document et le sous-dossier NyettoftTank_files
                String documentDirectory = System.getProperty("user.home") + "/Documents/";
                String nyettofDirectory = documentDirectory + "NyettoftTank_files/";

                // Créer le répertoire NyettoftTank_files si nécessaire
                File nyettofFile = new File(nyettofDirectory);
                if (!nyettofFile.exists()) {
                    nyettofFile.mkdirs();
                }

                // Construire le chemin complet du fichier
                String filePath = nyettofDirectory + nom + ".xls";

                // Écrire le fichier Excel à l'emplacement spécifié
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                workbook.write(fileOutputStream);

                // Fermer les flux pour éviter les fuites de mémoire
                fileOutputStream.close();
                workbook.close();

                // Afficher un message de confirmation à l'utilisateur
                location.showMessageDialog(null, "Votre FICHIER SE TROUVE DANS \n  " + filePath, "LOCALISATION DU FICHIER",
                        JOptionPane.INFORMATION_MESSAGE);


            } catch (IOException e) {
                location.showMessageDialog(null, e.getMessage(), "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            location.showMessageDialog(null, "Veillez renseigner le nombre de divisions ou une valeur décimale/entière du diamètre".toUpperCase(),
                    "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
        }
    }

}
