import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {
    private double[] coef;
    private int[] exp;

    public Polynomial() {
        coef = null;
        exp = null;
    }

    public Polynomial(double[] newcoef, int[] newexp) {
        coef = new double[newcoef.length];
        exp = new int[newexp.length];
        for (int i = 0; i < newcoef.length; i++) coef[i] = newcoef[i];
        for (int i = 0; i < newexp.length; i++) exp[i] = newexp[i];
    }

    public Polynomial(File file) {
        try {
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            int cnt = line.split("[\\+\\-]").length;

            String[] split1 = line.split("[\\+]");

            coef = new double[cnt];
            exp = new int[cnt];
            int idx = 0;
            
            for (String s : split1) {
                String[] split2 = s.split("[\\-]");
                
                for (int i = 0; i < split2.length; i++) {
                    String[] splitx = split2[i].split("[x]");
                    
                    exp[idx] = (splitx.length == 1 ? 0 : (splitx[1].isEmpty() ? 1 : Integer.parseInt(splitx[1])));
                    coef[idx] = (i == 0 ? (splitx[0].isEmpty() ? 1 : Double.parseDouble(splitx[0])) : (splitx[0].isEmpty() ? -1 : -Double.parseDouble(splitx[0])));
                    idx++;
                }
            }

            sc.close();
        }
        catch (IOException e) {
            System.err.println("Error");
        }
    }

    public Polynomial nonzero() {
        int count = 0;
        for (int i = 0; i < coef.length; i++) {
            if (coef[i]!=0) count++;
        }
        double[] new_coef = new double[count];
        int[] new_exp = new int[count];
        int k=0;
        for (int i = 0; i < coef.length; i++) {
            if (coef[i] != 0) {
                new_coef[k] = coef[i];
                new_exp[k] = exp[i];
                k++;
            }
        }
        return new Polynomial(new_coef,new_exp);
    }

    public int maxexp() {
        int k = 0;
        for (int i = 0;i < exp.length; i++) {
            k = Math.max(k, exp[i]);
        }
        return k;
    }

    public Polynomial add(Polynomial p) {
        double[] new_coef = new double[Math.max(this.maxexp(),p.maxexp())+1];
        int[] new_exp = new int[Math.max(this.maxexp(),p.maxexp())+1];
        for (int i = 0; i < new_exp.length; i++) {
            new_coef[i] = 0;
            new_exp[i] = i;
        }
        for (int i = 0; i < coef.length; i++) new_coef[exp[i]] += coef[i];
        for (int i = 0; i < p.coef.length;i++) new_coef[p.exp[i]] += p.coef[i];
        Polynomial poly = new Polynomial(new_coef, new_exp);
        return poly.nonzero();
    }

    public double evaluate(double x) {
        double result = 0.0;

        for (int i = 0; i < this.coef.length; i++)
            result += this.coef[i] * Math.pow(x, this.exp[i]);
        return result;
    }

    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0.0 ? true : false);
    }

    public Polynomial multiply(Polynomial newpoly) {
        int n = this.coef.length;
        int m = newpoly.coef.length;
        double[] coef_temp = new double[n*m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                coef_temp[this.exp[i]+newpoly.exp[j]] += this.coef[i] * newpoly.coef[j];
            }
        }

        int cnt = 0;
        for (int i = 0; i < n*m; i++) {
            if (coef_temp[i] != 0) cnt++;
        }

        int idx = 0;
        int[] exp_result = new int[cnt];
        double[] coef_result = new double[cnt];
        for (int i = 0; i < n*m; i++) {
            if (coef_temp[i] == 0) continue;
            exp_result[idx] = i;
            coef_result[idx] = coef_temp[i];
            idx++;
        }

        return new Polynomial(coef_result, exp_result);
    }

    public void saveToFile(String file) {
        String result = "";
        for (int i = 0; i < this.coef.length; i++) {
            if (this.coef[i] == -1) {
                if (i != 0) result += "-";
                if (this.exp[i] == 0) result += "1";
                else result += (this.exp[i] == 1 ? "x" : "x" + Integer.toString(this.exp[i]));
            }
            else if (this.coef[i] == 1) {
                if (i != 0) result += "+";
                if (this.exp[i] == 0) result += "1";
                else result += (this.exp[i] == 1 ? "x" : "x" + Integer.toString(this.exp[i]));
            }
            else {
                result += (i == 0 || this.coef[i] < 0 ? (Double.toString(this.coef[i])) : "+" + Double.toString(this.coef[i]));
                if (this.exp[i] != 0) result += (this.exp[i] == 1 ? "x" : "x" + Integer.toString(this.exp[i]));
            }
        }

        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}