import java.math.BigInteger;

public class Triple {
    final BigInteger smallest;
    final BigInteger k;
    BigInteger d;

    public Triple(final BigInteger smallest, final BigInteger k, final BigInteger d) {
        this.smallest = smallest;
        this.k = k;

        this.d = d;
    }
}
