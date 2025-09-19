public class Polynomial {
    private double[] poly;

    public Polynomial() {
        poly = new double[1];
        poly[0] = 0;
    }

    public Polynomial(double[] arr) {
        poly = new double[arr.length];
        for (int i = 0; i < arr.length; i++) poly[i] = arr[i];
    }

    public Polynomial add(Polynomial newpoly) {
        int n = this.poly.length;
        int m = newpoly.poly.length;
        double[] result = new double[Math.max(n, m)];
        
        for (int i = 0; i < Math.max(n, m); i++) result[i] = (i < n ? this.poly[i] : 0.0) + (i < m ? newpoly.poly[i] : 0.0);
        return new Polynomial(result);
    }

    public double evaluate(double x) {
        double result = 0.0;

        for (int i = 0; i < this.poly.length; i++) result = result + this.poly[i] * Math.pow(x, i);
        return result;
    }

    public boolean hasRoot(double x) {
        return (evaluate(x) == 0.0 ? true : false);
    }
}
