import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA
{

    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger PHI;
    private BigInteger e;
    private BigInteger d;
    private int maxLength = 1024;
    private SecureRandom R = new SecureRandom();

    public RSA()
    {
        P = BigInteger.probablePrime(maxLength, R);
        Q = BigInteger.probablePrime(maxLength, R);
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(  Q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(maxLength / 2, R);
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(PHI);
       }

    public RSA(BigInteger e, BigInteger N)
    {
        this.e = e;
        this.N = N;
    }

    public RSA(BigInteger e, BigInteger N, BigInteger d)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public BigInteger encryptMessage(BigInteger message)
    {
        return message.modPow(e, N);
    }


    public BigInteger decryptMessage(BigInteger message)
    {
        return message.modPow(d, N);
    }

    public BigInteger getE(){
        return e;
    }

    public BigInteger getN(){
        return N;
    }

    public BigInteger getD(){
        return d;
    }
}

