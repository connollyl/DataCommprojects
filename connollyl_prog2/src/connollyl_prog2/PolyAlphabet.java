/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connollyl_prog2;

/**
 * This class will take a string and encrypt it based on a pattern. The method 
 * in the class will break a string into characters, shift the characters, and
 * build a string of the shifted characters.
 * @author Logan
 */
public class PolyAlphabet 
{
    private final int C1 = 5;
    private final int C2 = 19;
    
    /**
     * This method encrypts a string in the pattern of C1, C2, C2, C1, and C2.
     * @param str
     * @return encrypted string
     */
    public String encrypt(String str)
    {
        StringBuilder strBuilder = new StringBuilder();
        char c;
        int cipherNumber = 1;
        for (int i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            if (Character.isLetter(c))
            {
                if (Character.isLowerCase(str.charAt(i))
                    || Character.isUpperCase(str.charAt(i)))
                {
                    if (cipherNumber == 1 || cipherNumber == 4)
                    {
                        for (int j = 1; j <= C1; j++)
                        {
                            if (c == 'z')
                            {
                                c = 'a';
                                i++;
                            }
                            if (c == 'Z')
                            {
                                c = 'A';
                                i++;
                            }
                            c += 1;
                        }
                    }
                    else if (cipherNumber == 2 || cipherNumber == 3 || 
                            cipherNumber == 5)
                    {
                        for (int j = 1; j <= C2; j++)
                        {
                            if (c == 'z')
                            {
                                c = 'a';
                            }
                            if (c == 'Z')
                            {
                                c = 'A';
                            }
                            c += 1;
                        }
                    }
                }
            }
            strBuilder.append(c);
            if(cipherNumber == 5)
            {
                cipherNumber = 0;
            }
            cipherNumber++;
        }
        return strBuilder.toString();
    }
}
