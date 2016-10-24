package Parser;

import java.io.*;
import java.util.*;


public class expops 
{
     public static List<String> tokenize(String s) throws IOException
    {
        List<String> tokenlist = new ArrayList<>();
        StreamTokenizer tokenizer = new  StreamTokenizer(new StringReader(s));
        int last = 0, i=0;
        String newstring = "";
        
        tokenizer.ordinaryChar('-');  // Don't use - as part of a digit

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) 
        {
          switch(tokenizer.ttype) 
          {
            case StreamTokenizer.TT_NUMBER:
              tokenlist.add(String.valueOf(tokenizer.nval));
              break;
            case StreamTokenizer.TT_WORD:
              //if the last token was a digit than its part of the word
              if(last == -2)
              {
                  i--;
                  newstring = tokenlist.get(i);//gets last token
                  newstring += tokenizer.sval; //appends current token to old
                  tokenlist.set(i, newstring); //re-adds token
              }
              else
              {
                  tokenlist.add(tokenizer.sval);
              }
              break;
            default:  // operator
              tokenlist.add(String.valueOf((char) tokenizer.ttype));
          }
          last = tokenizer.ttype;
          i++;
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
    
    public static boolean id(String s)
     {
         boolean status; //Keeps track of valid id
         char idtest; //grabs a single char from the id
         String singlechar = ""; //create a string to hold single char
         
         for(int i = 0; i<s.length();i++) //loop through id string
         {
             idtest = s.charAt(i); //grab single char from id string
             singlechar += idtest; //store single char into new string
             
             if(i == 0) //if first char make sure its a letter
             {
                 status = letter(singlechar);
                 if(status == false)
                 {
                     return false;
                 }
             }
             else //make sure rest of id is either letters or digits 
             {
                 status = letter(singlechar);
                 
                 if(status == false)
                 {
                     status = digit(singlechar);
                     
                     if(status == false)
                     {
                         return false;
                     }
                 }
             }
             singlechar = "";//reset single char string
         }
         
         return true;
     }
}

