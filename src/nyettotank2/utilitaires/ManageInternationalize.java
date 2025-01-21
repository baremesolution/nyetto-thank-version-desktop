/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nyettotank2.utilitaires;

import java.util.Locale;
import java.util.ResourceBundle;
import nyettotank2.view.newIHM.MainView;

/**
 *
 * @author user
 */
public class ManageInternationalize {
    
    
     Locale[] locale = {Locale.FRENCH, Locale.ENGLISH, Locale.GERMANY};
        ResourceBundle val_fr = ResourceBundle.getBundle("Bombe_fr", locale[0]);
        ResourceBundle val_en = ResourceBundle.getBundle("Bombe_en", locale[1]);
        ResourceBundle val_de = ResourceBundle.getBundle("Bombe_de", locale[2]);
        ResourceBundle valeurs ;
        
          public ManageInternationalize(){
            valeurs = null;
        }
        
        public String translate( String code ){
                String chooseLanguage = MainView.getChoosenLanguage();

        if (chooseLanguage.equalsIgnoreCase("FRANCAIS") || chooseLanguage.equalsIgnoreCase("FRENCH") || chooseLanguage.equalsIgnoreCase("FRANZÖSISCH")) {
            valeurs = val_fr;
        } else if (chooseLanguage.equalsIgnoreCase("ANGLAIS") || chooseLanguage.equalsIgnoreCase("ENGLISH") || chooseLanguage.equalsIgnoreCase("ENGLISCH")) {
            valeurs = val_en;
        } else if (chooseLanguage.equalsIgnoreCase("ALLEMAND") || chooseLanguage.equalsIgnoreCase("GERMAN") || chooseLanguage.equalsIgnoreCase("DEUTSCH")) {
            valeurs = val_de;
        }
        
        return valeurs.getString(code);
        }

 
}
