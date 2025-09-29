import java.io.File;

public class Driver {
    public static void main(String [] args) {
        // double [] c1 = {6, -2, 5};
        // int [] e1 = {3, 2, 7};
        // Polynomial p1 = new Polynomial(c1, e1);

        // // System.out.println(p1.evaluate(-1.379));
        // // System.out.println(p1.evaluate(0));
        // // System.out.println(p1.evaluate(3.1415829));
        // // System.out.println((p1.hasRoot(-0.47) ? "p1 has root x = -0.47" : "-0.47 is not a root of p1"));
        // // System.out.println((p1.hasRoot(0) ? "p1 has root x = 0" : "0 is not a root of p1"));
        // // System.out.println((p1.hasRoot(0.330069) ? "p1 has root x = 0.330069" : "0.330069 is not a root of p1"));
        // // System.out.println((p1.hasRoot(52.54) ? "p1 has root x = 52.54" : "52.54 is not a root of p1"));

        // double [] c2 = {5, -2, 8, -7, -9};
        // int [] e2 = {6, 1, 0, 5, 2};
        // Polynomial p2 = new Polynomial(c2, e2);

        // System.out.println(p2.evaluate(-1.379));
        // System.out.println(p2.evaluate(0));
        // System.out.println(p2.evaluate(3.1415829));
        // System.out.println((p2.hasRoot(0.783404) ? "p1 has root x = 0.783404" : "0.783404 is not a root of p1"));
        // System.out.println((p2.hasRoot(0) ? "p1 has root x = 0" : "0 is not a root of p1"));
        // System.out.println((p2.hasRoot(1.70111) ? "p1 has root x = 1.70111" : "1.70111 is not a root of p1"));
        // System.out.println((p2.hasRoot(52.54) ? "p1 has root x = 52.54" : "52.54 is not a root of p1"));

        // Polynomial s = p1.add(p2);
        // System.out.println(s.evaluate(-1.379));
        // System.out.println(s.evaluate(0));
        // System.out.println(s.evaluate(3.1415829));
        // System.out.println((s.hasRoot(-1) ? "s has root x = 0.783404" : "0.783404 is not a root of p1"));
        // System.out.println((s.hasRoot(0) ? "s has root x = 0" : "0 is not a root of p1"));
        // System.out.println((s.hasRoot(-1.00001) ? "s has root x = 1.70111" : "1.70111 is not a root of p1"));
        // System.out.println((s.hasRoot(52.54) ? "s has root x = 52.54" : "52.54 is not a root of p1"));

        double[] c1 = {-14.5,-12.07,1,1.7,52.54};
        int[] e1 = {2,1,4,3,0};
        Polynomial p1 = new Polynomial(c1,e1);
        double[] c2 = {2,4.4,-12.1,-11.7,-17.6,48.4,14.8};
        int[] e2 = {6,5,4,3,2,1,0};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(-3.7))
            System.out.println("k is a root of s");
        else
            System.out.println("k is not a root of s");
        Polynomial r = p1.multiply(p2);
        System.out.println("r(2) = " + r.evaluate(2));
        if(r.hasRoot(2))
            System.out.println("k is a root of r");
        else
            System.out.println("k is not a root of r");
        File f = new File("/Users/lucia/b07lab1/test.txt");
        Polynomial pFile = new Polynomial(f);
        System.out.println("pFile(0.1) = " + pFile.evaluate(0.1));
        String sFile = "/Users/lucia/b07lab1/testwrite.txt";
        p1.saveToFile(sFile);
    }
}