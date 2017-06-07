import edu.princeton.cs.algs4.StdOut;

// A data type representing a rational number.
public class Rational {
    private long x; // numerator
    private long y; // denominator

    // Construct a rational number whose numerator is x and denominator is 1.
    public Rational(long x) {
        this.x = x;
        this.y = 1;
    }

    // Construct a rational number given its numerator and denominator.
    public Rational(long x, long y) {
        long a = gcd(x, y);
        this.x = x / a;
        this.y = y / a;
    }

    // The sum of this and that rational number.
    public Rational add(Rational that) {
        long num = (this.x)*(that.y)+(that.x)*(this.y);
        long den = (this.y)*(that.y);
        return new Rational(num, den);
    }

    // The product of this and that rational number.
    public Rational multiply(Rational that) {
        return new Rational(this.x*that.x, this.y*that.y);
    }

    // A string representation of the rational number.
    public String toString() {
        long a = x, b = y;
        if (a == 0 || b == 1) {
            return a + "";
        }
        if (b < 0) {
            a *= -1;
            b *= -1;
        }
        return a + "/" + b;
    }

    // gcd(p, q), computed using Euclid's algorithm.
    private static long gcd(long p, long q) {
        return q == 0 ? p : gcd(q, p % q);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Rational total = new Rational(0);
        Rational term = new Rational(1);
        for (int i = 1; i <= n; i++) {
            total = total.add(term);
            term = term.multiply(new Rational(1, 2));
        }
        StdOut.println(total);
    }
}
