package nyettotank2.metier;

import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import nyettotank2.utilitaires.ManageInternationalize;

public class ZModel extends AbstractTableModel {

    private List<Line> data = new ArrayList<Line>();
    private ManageInternationalize manageInternationalize = new ManageInternationalize();
    String title[] = new String[2];

   
    public ZModel(String unitVolume, String unitHeight) { 
        super();
        title[0] =  manageInternationalize.translate("volume") + " ( " + manageInternationalize.translate("unite_volume") + " )";
        title[1] =  manageInternationalize.translate("hauteur") + " ( " + manageInternationalize.translate("unite_longueur") + " )";
    }

    @Override
    public int getRowCount() {
        return this.data.size();

    }

    @Override
    public int getColumnCount() {
        return this.title.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return data.get(row).getVolume();
        } else if (col == 1) {
            return data.get(row).getHauteur();
        } else {
            return null;
        }
    }

    public String getColumnName(int col) {
        return this.title[col];
    }

    public List<Line> getData() {
        return data;
    }

    public void setData(List<Line> data) {
        this.data = data;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public void setValueAt(Object value, int row, int col) {
        //On interdit la modification sur certaines colonnes !
        Line exple = data.get(row);
        if (col == 0) {
            exple.setVolume(parseFloat(value.toString()));
        } else if (col == 1) {
            exple.setHauteur(parseFloat(value.toString()));
        }
        
    }

    public void addLine(Line ami) {
        data.add(ami);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeLine(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

}
