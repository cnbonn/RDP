import java.io.*;
import java.util.*;

public class Scan
{
    public Scan( boolean tokenFlag )
    {
	System.out.println( "in scan");
	readInput( tokenFlag );
    }

    public void readInput( boolean tokenFlag )
    {
	boolean exit = false;

	while( exit == false )
	{
	    System.out.print( "Enter Expression: " );
	    Scanner sc = new Scanner( System.in );
	    String expression = sc.nextLine();

            if( expression.isEmpty() )
	    {
		System.out.println( "(end of input)" );
		System.exit(0); // normal exit
	    }
	    else
            {
		//System.out.println( "expression: " + expression );
		expression = removeWhiteSpace(expression);
                //splitExpression(expression);
                
		Grammar grammar = new Grammar( expression );
		grammar.testGrammar();

		if( tokenFlag == true )
                {
                }
	    }
	}
    }

    public void splitExpression(String expression)
    {
        String delims = "+-*%()";

	StringTokenizer st = new StringTokenizer( expression, delims );
        while( st.hasMoreElements() )
        {
	    System.out.println("Token: " + st.nextElement() );
        }
        
	
	
    }
    
    public String removeWhiteSpace(String expression)
    {
	expression = expression.replaceAll("\\s","");
	//System.out.println("expression: " + expression);
        return expression;
    }
}
