import edu.princeton.cs.algs4.StdOut;

// An immutable data type representing a US phone number.
public class PhoneNumber {
    private final int area; // area code (3 digits)
    private final int exch; // exchange  (3 digits)
    private final int ext;  // extension (4 digits)
    
    // Construct a phone number given the area code, exchange, and extension.
    public PhoneNumber(int area, int exch, int ext) {
       this.area = area;
       this.exch = exch;
       this.ext = ext;
    }

    // Is this phone number the same as that?
    public boolean equals(PhoneNumber that) {
        return (this.area == that.area) 
              &&  (this.exch == that.exch) 
              && (this.ext == that.ext); 
    }

    // A string representation of the phone number, in "(area) exch-ext" 
    // format. Use String.format().
    public String toString() {
       return "(" + String.format("%03d", area) +") " 
       +String.format("%03d", exch)  + "-"+String.format("%04d", ext);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        PhoneNumber a = new PhoneNumber(609, 258, 4455);
        PhoneNumber b = new PhoneNumber(609, 876, 5309);
        PhoneNumber c = new PhoneNumber(609, 003, 5309);
        PhoneNumber d = new PhoneNumber(215, 876, 5309);
        PhoneNumber e = new PhoneNumber(609, 876, 5309);
        StdOut.println(a);
        StdOut.println(b);
        StdOut.println(c);
        StdOut.println(d);
        StdOut.println(e);
        StdOut.println(b == b);
        StdOut.println(b == e);
        StdOut.println(b.equals(b));
        StdOut.println(b.equals(e));
    }
}
