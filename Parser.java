package Parser;

import java.util.*;
import java.io.IOException;


public class Parser
{
    public static boolean tokenFlag = true;

    public static void main( String [] args ) throws IOException
    {
	parseCMD( args );
	readInput();
    }
 
    public static void parseCMD(String [] args)
    {
	for( String arg : args )
 	{
	    if( arg.equals("-t") )
	    {
		tokenFlag = true;
            }
	}
    }    

    public static void readInput() throws IOException
    {
	//exit condition
	boolean exit = false;
	while( exit == false)
	{
	    System.out.print( "Enter Expression: " );
	    Scanner sc = new Scanner( System.in );
	    String expression = sc.nextLine();

	    // check for empty string
	    if( expression.isEmpty() )
	    {
		System.out.println( "(end of input)" );
		return; //exit the program
            }
	    else
            { 
	        System.out.println( " expression: " + expression );
		if( tokenFlag == true)
		{
		    splitExpression( expression );
		}
            }
	}
    }

    public static void splitExpression(String expression) throws IOException
    {
	String delims = "+-()*%";

	StringTokenizer st = new StringTokenizer( expression, delims );
        
        test(expression);
        
	while( st.hasMoreElements())
        {
	     System.out.println("Token: " + st.nextElement());
        }
    }
    
    public static void test(String s) throws IOException
    {
        List<String> newexp;
        
        newexp = expops.tokenize(s);
        
        for(int i = 0; i<newexp.size();i++)
        {
            System.out.println("Opperation = "+newexp.get(i)+"\n");
            System.out.print(expops.id(newexp.get(i))+"\n");
            /*
            System.out.print(expops.mulop(newexp.get(i))+"\n");
            System.out.print(expops.digit(newexp.get(i))+"\n");
            System.out.print(expops.addop(newexp.get(i))+"\n");*/
            System.out.println("-------------------------------\n");
        }
    }
}
