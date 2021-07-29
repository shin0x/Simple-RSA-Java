import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {
    public Triple apply(final BigInteger e, final BigInteger N) {
        if (N.equals(BigInteger.ZERO)) {
            return new Triple(e, BigInteger.ONE, BigInteger.ZERO);
        } else {
            final Triple extension = apply(N, e.mod(N));
            return new Triple(extension.smallest, extension.k, extension.d.subtract(e.divide(N).multiply(extension.k)));
        }
    }

}
