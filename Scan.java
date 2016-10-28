import java.io.*;
import java.util.*;

/**
 * This class deals with the user inputted expression.
 * @author 7110015
 */
public class Scan
{
    /**
     * Function that checks the tokenFlag
     * @param tokenFlag
     */
    public Scan( boolean tokenFlag )
    {
	System.out.println( "in scan");
	readInput( tokenFlag );
    }

    /**
     * This function reads in the users expression and then places it into a
     * string. After that is done, it then removes the whitespace from the
     * expression and checks the grammar of it. It will continue to do this
     * until the user enters in a blank line.
     * @param tokenFlag
     */
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
		    splitExpression( expression );
                }
	    }
	}
    }

    /**
     * Function that prints out the tokens of the expression if tokenFlag is
     * true.
     * @param expression
     */
    public void splitExpression(String expression)
    {
        String delims = "+-*%()";

	StringTokenizer st = new StringTokenizer( expression, delims );
        while( st.hasMoreElements() )
        {
	    System.out.println("Token: " + st.nextElement() );
        }
        
	
	
    }
    
    /**
     * The function that removes spaces from the user inputted expression
     * @param expression
     * @return
     */
    public String removeWhiteSpace(String expression)
    {
	expression = expression.replaceAll("\\s","");
	//System.out.println("expression: " + expression);
        return expression;
    }
}
