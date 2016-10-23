package Parser;

import java.io.*;
import java.util.*;


public class expops 
{
     public static List<String> tokenize(String s) throws IOException
    {
        List<String> tokenlist = new ArrayList<>();
        StreamTokenizer tokenizer = new  StreamTokenizer(new StringReader(s));
        
        tokenizer.ordinaryChar('-');  // Don't use - as part of a digit

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) 
        {
          switch(tokenizer.ttype) 
          {
            case StreamTokenizer.TT_NUMBER:
              tokenlist.add(String.valueOf(tokenizer.nval));
              break;
            case StreamTokenizer.TT_WORD:
              tokenlist.add(tokenizer.sval);
              break;
            default:  // operator
              tokenlist.add(String.valueOf((char) tokenizer.ttype));
          }
        }
        return tokenlist; 
    }
     
    public static boolean letter(String s)
    {
        return s.matches("[a-zA-Z_]+");    
    }
    
    public static boolean digit(String s)
    {
        return s.matches("[0-9]+");
    }
    
    public static boolean addop(String s)
    {
        return s.matches("[+-]");
    }
    
     public static boolean mulop(String s)
    {
        return s.matches("[*/%]");
    }
    
    
}
