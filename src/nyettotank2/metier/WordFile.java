package nyettotank2.metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import nyettotank2.utilitaires.ConfigureImageButton;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class WordFile {

    private JOptionPane location = new JOptionPane();

    public String verifie(HashMap s, String key) {
        if (s.containsKey(key)) {
            return (String) s.get(key);
        } else {
            return "";
        }
    }

    public void createSimpleTable(HashMap info, HashMap data) throws Exception {
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFTable table = doc.createTable(13, 2);

            List<XWPFTableRow> rows = table.getRows();

            XWPFParagraph p2 = table.getRow(0).getCell(0).getParagraphs().get(0);
            XWPFParagraph p3 = table.getRow(0).getCell(1).getParagraphs().get(0);

            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun r2 = p2.createRun();
            XWPFRun r3 = p3.createRun();

            r2.setBold(true);
            r3.setBold(true);
            r2.setText("REPUBLIQUE DU CAMEROUN");
            r3.setText("REPUBLIC OF CAMEROON");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(1).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(1).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("Paix-Travail-Patrie");
            r3.setText("Peace-Work-Fatherland");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table.getRow(2).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(2).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table.getRow(3).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(3).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(true);
            r3.setBold(true);
            r2.setText("MINISTERE DU COMMERCE");
            r3.setText("MINISTRY OF TRADE");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(4).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(4).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(5).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(5).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("direction de la metrologie,".toUpperCase());
            r3.setText("department of metrology,".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(6).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(6).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("quality and prices".toUpperCase());
            r3.setText("de la qulite et des prix".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(7).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(7).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(8).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(8).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SOUS-DIRECTION DE LA METROLOGIE");
            r3.setText("SUB-DEPARTMENT OF METROLOGY");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(9).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(9).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(10).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(10).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SERVICE DES INSTRUMENTS");
            r3.setText("SERVICE OF VOLUMETRICS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(11).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(11).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("VOLUMETRIQUES");
            r3.setText("INSTRUMENTS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table.getRow(12).getCell(0).getParagraphs().get(0);
            p3 = table.getRow(12).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            XWPFParagraph p = doc.createParagraph();
            XWPFRun r = p.createRun();
            r.setText(" ");
            for (int i = 0; i < 3; i++) {
                p = doc.createParagraph();
                r = p.createRun();
                r.setText("  ");

            }
            configurerTableau(table);

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText("TABLE DE JAUGEAGE A L'ECHELLE CENTIMETRIQUE DES VOLUMES");
            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText("D'EXPLOITATION DES " + verifie(info, "type de la capacite")
                    + " DE " + verifie(info, "lieu des operations"));
            r.setBold(true);

            String logoName = ConfigureImageButton.logoName;

            if (!logoName.isEmpty()) {
                int format = giveFormatFile(logoName);
                String imgFile = logoName;

                p = doc.createParagraph();
                p.setAlignment(ParagraphAlignment.CENTER);
                r = p.createRun();
                try (FileInputStream is = new FileInputStream(imgFile)) {
                    r.addPicture(is, format, imgFile, Units.toEMU(350), Units.toEMU(140)); // 200x200 pixels
                }
            }

            p = doc.createParagraph();
            r = p.createRun();
            r.addBreak(BreakType.PAGE);
            r.setText(" ".toUpperCase());

            // DEFINITION DE LA PAGE 2
            XWPFTable table2 = doc.createTable(13, 2);

            rows = table2.getRows();
            rows.get(0).getTableCells();

            p2 = table2.getRow(0).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(0).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(true);
            r3.setBold(true);
            r2.setText("REPUBLIQUE DU CAMEROUN");
            r3.setText("REPUBLIC OF CAMEROON");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(1).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(1).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("Paix-Travail-Patrie");
            r3.setText("Peace-Work-Fatherland");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table2.getRow(2).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(2).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table2.getRow(3).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(3).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(true);
            r3.setBold(true);
            r2.setText("MINISTERE DU COMMERCE");
            r3.setText("MINISTRY OF TRADE");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(4).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(4).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(5).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(5).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("direction de la metrologie,".toUpperCase());
            r3.setText("department of metrology,".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(6).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(6).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("quality and prices".toUpperCase());
            r3.setText("de la qulite et des prix".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(7).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(7).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(8).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(8).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SOUS-DIRECTION DE LA METROLOGIE");
            r3.setText("SUB-DEPARTMENT OF METROLOGY");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(9).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(9).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(10).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(10).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SERVICE DES INSTRUMENTS");
            r3.setText("SERVICE OF VOLUMETRICS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(11).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(11).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("VOLUMETRIQUES");
            r3.setText("INSTRUMENTS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table2.getRow(12).getCell(0).getParagraphs().get(0);
            p3 = table2.getRow(12).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            for (int i = 0; i < 2; i++) {
                p = doc.createParagraph();
                r = p.createRun();
                r.setText("  ");
            }

            configurerTableau(table2);

            p = doc.createParagraph();
            r = p.createRun();
            p.setAlignment(ParagraphAlignment.CENTER);
            int y = LocalDate.now().getYear();
            y %= 100;
            r.setText("      N°………………./" + y + "/MINCOMMERCE /SG/DMQP/SDM/SIV");
            r.setBold(true);

            for (int i = 0; i < 2; i++) {
                p = doc.createParagraph();
                r = p.createRun();
                r.setText("  ");
            }

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText(" Table de jaugeage donnant à l’échelle centimetrique les volumes de "
                    + verifie(info, "produit stocke") + " contenus dans la " + verifie(info, "type de la capacite")
                    + " de  ");

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText(verifie(info, "immatriculation") + " " + verifie(data, "volume nominal")
                    + verifie(info, "unite de volume") + ",  " + " de  " + verifie(info, "detenteur") + " situe a  "
                    + verifie(info, "lieu des operations"));

            for (int i = 0; i < 2; i++) {
                p = doc.createParagraph();
                r = p.createRun();
                r.setText("  ");
            }
            p = doc.createParagraph();
            r = p.createRun();
            r.setText("LIMITE DE VALIDITE : " + verifie(info, "fin des travaux"));

            for (int i = 0; i < 2; i++) {
                p = doc.createParagraph();
                r = p.createRun();
                r.setText("  ");
            }
            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                                                   POUR LE MINISTERE DU COMMERCE");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                                                             ET PAR ORDRE        ");

            p = doc.createParagraph();
            r = p.createRun();
            r.addBreak(BreakType.PAGE);
            r.setText(" ".toUpperCase());

            // DEFINITION DE LA PAGE3
            XWPFTable table3 = doc.createTable(13, 2);

            rows = table3.getRows();
            rows.get(0).getTableCells();

            p2 = table3.getRow(0).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(0).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(true);
            r3.setBold(true);
            r2.setText("REPUBLIQUE DU CAMEROUN");
            r3.setText("REPUBLIC OF CAMEROON");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(1).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(1).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("Paix-Travail-Patrie");
            r3.setText("Peace-Work-Fatherland");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table3.getRow(2).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(2).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(true);
            r3.setItalic(true);

            p2 = table3.getRow(3).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(3).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(true);
            r3.setBold(true);
            r2.setText("MINISTERE DU COMMERCE");
            r3.setText("MINISTRY OF TRADE");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(4).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(4).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(5).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(5).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("direction de la metrologie,".toUpperCase());
            r3.setText("department of metrology,".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(6).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(6).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("quality and prices".toUpperCase());
            r3.setText("de la qulite et des prix".toUpperCase());
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(7).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(7).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(8).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(8).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SOUS-DIRECTION DE LA METROLOGIE");
            r3.setText("SUB-DEPARTMENT OF METROLOGY");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(9).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(9).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(10).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(10).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("SERVICE DES INSTRUMENTS");
            r3.setText("SERVICE OF VOLUMETRICS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(11).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(11).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("VOLUMETRIQUES");
            r3.setText("INSTRUMENTS");
            r2.setItalic(false);
            r3.setItalic(false);

            p2 = table3.getRow(12).getCell(0).getParagraphs().get(0);
            p3 = table3.getRow(12).getCell(1).getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            p3.setAlignment(ParagraphAlignment.CENTER);
            r2 = p2.createRun();
            r3 = p3.createRun();
            r2.setBold(false);
            r3.setBold(false);
            r2.setText("--------");
            r3.setText("--------");
            r2.setItalic(false);
            r3.setItalic(false);

            configurerTableau(table3);

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  "); // NOTE TECHNIQUE

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText("NOTE TECHNIQUE");
            r.setBold(true);

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            y = LocalDate.now().getYear();
            y %= 100;
            r.setText(" CERTIFICAT DE JAUGEAGE N°………./" + y + "/MINCOMMERCE/SG/DMQP/SDM/SIV");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            r = p.createRun();
            p.setAlignment(ParagraphAlignment.CENTER);
            r.setText(verifie(info, "type de la capacite") + "  " + verifie(info, "immatriculation") + " DE "
                    + verifie(data, "volume nominal") + verifie(info, "unite de volume"));

            // FORME GENERALE
            p = doc.createParagraph();
            r = p.createRun();
            r.setText("FORME GENERALE : - Compartiment de " + verifie(info, "type de la capacite") + " : "
                    + verifie(info, "nombre compartiments"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                             - Fond : " + verifie(info, "fond capacite"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                             - Hauteur totale temoin : " + verifie(info, "hauteur temoin") + ""
                    + verifie(info, "unite des hauteurs"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("PRODUIT EN STOCK  :  " + verifie(info, "produit stocke"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("DETENTEUR         :  " + verifie(info, "detenteur"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("CONSTRUCTEUR      :  " + verifie(info, "fabricant"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("MODE OPERATOIRE  :  " + verifie(info, "mode operatoire"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("RESULTATS  : ");

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText(
                    "                     - La table des volumes en fonction des hauteurs à l’échelle centimétrique;");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                             - Les hauteurs sont relevées à la verticale de la position de jaugeage;");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                             - Les volumes sont valables a 30°C");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                             - L’erreur relative commise sur les volumes portés par");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                              le barème ci-après est inférieure ou égale à + ou - 3/1100 pour ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                              une livraison de produit supérieure ou égal à 50 cm");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.RIGHT);
            r = p.createRun();
            r.setText("FAIT A                   ");

            p = doc.createParagraph();
            r = p.createRun();
            r.addBreak(BreakType.PAGE);
            r.setText(" ".toUpperCase());

            // DEFINITION DE LA PAGE4
            p = doc.createParagraph();
            r = p.createRun();
            r.setText(" ");

            // int format = giveFormatFile(info.get("logo name").toString());
            // String imgFile = info.get("logo name").toString();
            //
            // p = doc.createParagraph();
            // p.setAlignment(ParagraphAlignment.CENTER);
            // r = p.createRun();
            // try (FileInputStream is = new FileInputStream(imgFile)) {
            // r.addPicture(is, format, imgFile, Units.toEMU(400), Units.toEMU(200)); //
            // 200x200 pixels
            // }
            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            y = LocalDate.now().getYear();
            y %= 100;
            r.setText(" CERTIFICAT DE JAUGEAGE N°………./" + y + "/MINCOMMERCE/SG/DMQP/SDM/SIV");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.CENTER);
            r = p.createRun();
            r.setText("FICHE TECHNIQUE");
            r.setBold(true);
            r.setBold(true);

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("NOM ET ADRESSE DU DEMANDEUR  : " + verifie(info, "agree"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("PROFESSION                   :  AGREE");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("EXPLOITANT DE LA   "
                    + verifie(info, "TYPE de la CAPPACITE".toLowerCase() + " : " + verifie(info, "detenteur")));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("DATE DES TRAVAUX  :  " + verifie(info, "debut des travaux"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("LIEU DES OPERATIONS  : " + verifie(info, "lieu des operations"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("CHEF DES OPERATIONS  : " + verifie(info, "chef des operations"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("    1.) IDENTIFICATION DE LA " + verifie(info, "type de la capacite") + verifie(data, "volume nominal")
                    + verifie(info, "unite de volume"));
            r.setBold(true);
            r.setUnderline(UnderlinePatterns.WORDS);

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            IMMATRICULATION DE LA " + verifie(info, "type de la capacite") + "  : ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            NOMBRE DE COMPARTIMENTS    : " + verifie(info, "nombre compartiments"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            CONSTRUCTEUR               : " + verifie(info, "fabricant"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            NUMERO DE SERIE            : " + verifie(info, "numero serie"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            ANNEE DE FABRICATION " + verifie(info, "annee fabrication") + "  : ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("            DIAMETRE NOMINAL           : " + verifie(data, "diametre"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("  ");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("    2.) EXAMEN DE CONFORMITE ");
            r.setBold(true);
            r.setUnderline(UnderlinePatterns.WORDS);

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                FORME         : " + verifie(info, "forme capacite") + " "
                    + verifie(info, "orientation")
                    + "  A FOND " + verifie(info, "fond capacite"));

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("                ETANCHEITE  : BONNE");

            p = doc.createParagraph();
            r = p.createRun();
            r.setText("    ");

            p = doc.createParagraph();
            p.setAlignment(ParagraphAlignment.RIGHT);
            r = p.createRun();
            r.setText(" FAIT A   " + verifie(info, "lieu operations") + "         ");

            String filePath = System.getProperty("user.home") + "/Documents/" + info.get("certificat").toString() + ".docx";
            try (OutputStream out = new FileOutputStream(filePath)) {
                doc.write(out);
                location.showMessageDialog(null, "Votre FICHIER SE TROUVE DANS \n  " + filePath,
                        "LOCALISATION DU FICHIER",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                location.showMessageDialog(null,
                        e.getMessage(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static int giveFormatFile(String imgFile) {
        int format = 0;

        if (imgFile.endsWith(".emf")) {
            format = Document.PICTURE_TYPE_EMF;
        } else if (imgFile.endsWith(".wmf")) {
            format = Document.PICTURE_TYPE_WMF;
        } else if (imgFile.endsWith(".pict")) {
            format = Document.PICTURE_TYPE_PICT;
        } else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg")) {
            format = Document.PICTURE_TYPE_JPEG;
        } else if (imgFile.endsWith(".png")) {
            format = Document.PICTURE_TYPE_PNG;
        } else if (imgFile.endsWith(".dib")) {
            format = Document.PICTURE_TYPE_DIB;
        } else if (imgFile.endsWith(".gif")) {
            format = Document.PICTURE_TYPE_GIF;
        } else if (imgFile.endsWith(".tiff")) {
            format = Document.PICTURE_TYPE_TIFF;
        } else if (imgFile.endsWith(".eps")) {
            format = Document.PICTURE_TYPE_EPS;
        } else if (imgFile.endsWith(".bmp")) {
            format = Document.PICTURE_TYPE_BMP;
        } else if (imgFile.endsWith(".wpg")) {
            format = Document.PICTURE_TYPE_WPG;
        }
        return format;
    }

    private void configurerTableau(XWPFTable table) {
        // Centrer le tableau
        CTTblWidth tableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        tableWidth.setType(STTblWidth.PCT);
        tableWidth.setW(BigInteger.valueOf(5000)); // Largeur du tableau en centièmes de point

        // Suppression des bordures externes
        CTTblBorders borders = table.getCTTbl().getTblPr().getTblBorders();
        borders.unsetTop();
        borders.unsetBottom();
        borders.unsetLeft();
        borders.unsetRight();

        // mettre la couleur des bordures en blanc
        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                CTTcPr cellProperties = cell.getCTTc().getTcPr();
                if (cellProperties == null) {
                    cellProperties = cell.getCTTc().addNewTcPr();
                }

                CTTcBorders cellBorders = cellProperties.addNewTcBorders();
                cellBorders.addNewTop().setColor("FFFFFF");
                cellBorders.addNewBottom().setColor("FFFFFF");
                cellBorders.addNewLeft().setColor("FFFFFF");
                cellBorders.addNewRight().setColor("FFFFFF");

                // reduire l'espace entre les lignes
                CTPPr cellParagraphProperties = cell.getParagraphs().get(0).getCTP().getPPr();
                if (cellParagraphProperties == null) {
                    cellParagraphProperties = cell.getParagraphs().get(0).getCTP().addNewPPr();
                }
                CTSpacing cellSpacing = cellParagraphProperties.addNewSpacing();
                cellSpacing.setAfter(BigInteger.valueOf(0)); // Espacement apres le paragraphe
                cellSpacing.setBefore(BigInteger.valueOf(0)); // Espacement avant le paragraphe
                cellSpacing.setLineRule(STLineSpacingRule.AUTO); // regle d'espacement des lignes
                cellSpacing.setLine(BigInteger.valueOf(240)); // Hauteur de ligne en twips (1 twip = 1/20ème de point)
            }
        }
    }

}
