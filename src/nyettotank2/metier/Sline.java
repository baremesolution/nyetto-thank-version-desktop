package nyettotank2.metier;

import java.util.List;
import javax.swing.JOptionPane;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

public class Sline {

    private final List<Float> mX;
    private final List<Double> mY;
    private final UnivariateFunction function;

    public Sline(List<Float> x, List<Double> y) {
        if (x == null || y == null || x.size() != y.size() || x.size() < 3) {
            JOptionPane.showMessageDialog(null, "Il doit y avoir au moins trois points de contrôles, et les tableaux doivent être de longueur égale.", "", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Données invalides pour l'interpolation.");
        }

        double[] abcisse = new double[x.size()];
        double[] ordonne = new double[x.size()];

        for (int i = 0; i < x.size(); i++) {
            abcisse[i] = x.get(i);
            ordonne[i] = y.get(i);
        }

        mX = x;
        mY = y;
        UnivariateInterpolator interpolator = new SplineInterpolator();
        function = interpolator.interpolate(abcisse, ordonne);
    }

    public UnivariateFunction getFunction() {
        return function;
    }

    public List<Float> getX() {
        return mX;
    }

    public List<Double> getY() {
        return mY;
    }

    public double interpolate(float x) {
        // Gère les cas limites.
        final int n = mX.size();
        if (Float.isNaN(x)) {
            return x;
        }
        if (x <= mX.get(0)) {
            return mY.get(0);
        }
        if (x >= mX.get(n - 1)) {
            return mY.get(n - 1);
        }

        // Trouve l'index 'i' du dernier point avec X plus petit.
// Nous savons que ce sera dans la spline en raison des tests de limite.
        return (float) function.value(x);
    }

}
