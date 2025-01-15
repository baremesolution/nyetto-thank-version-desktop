package nyettotank2.utilitaires;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormValidator {

    public static boolean isTextValid(String text) {
        return text != null && !text.isEmpty();
    }

    public static boolean isEmailValid(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static boolean isNumberValid(String number) {
        // Vérifier si le nombre est valide (ex : format, plage autorisée, etc.)
        try {
            int value = Integer.parseInt(number);
            return value >= 0 && value <= 999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDoubleValid(String number) {
        try {
            double value = Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void setupRealTimeValidation(JTextField textField, JLabel label) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                String text = textField.getText();
                if (!text.matches("\\d+(\\.\\d+)?") && !text.isEmpty()) {
                    label.setText("Valeur non valide");
                    textField.setBackground(Color.red);
                    textField.setForeground(Color.white);
                } else {
                    label.setText("");
                    textField.setBackground(Color.white);
                    textField.setForeground(Color.black);
                }
            }
        });
    }

}
