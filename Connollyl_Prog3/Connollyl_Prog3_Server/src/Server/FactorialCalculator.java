/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.math.BigInteger;
/**
 *
 * @author Logan
 */
public class FactorialCalculator 
{
    private BigInteger toBeCalculated;
    private final BigInteger SUBONE = new BigInteger(Integer.toString(1));
    private final BigInteger ZEROCHECK = new BigInteger(Integer.toString(0));
    
    public String compute(long i)
    {
        toBeCalculated = new BigInteger(Long.toString(i));
        BigInteger temp = new BigInteger(Integer.toString(1));
        while(!toBeCalculated.equals(ZEROCHECK))
        {
             temp = temp.multiply(toBeCalculated);
             toBeCalculated = toBeCalculated.subtract(SUBONE);
        }
        return temp.toString();
    }
}
