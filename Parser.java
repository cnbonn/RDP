import java.util.*;

public class Parser
{
    public static boolean tokenFlag = false;

    public static void main( String [] args )
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

    public static void readInput()
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

    public static void splitExpression(String expression)
    {
	String delims = "+-()*%";

	StringTokenizer st = new StringTokenizer( expression, delims );
	while( st.hasMoreElements())
        {
	     System.out.println("Token: " + st.nextElement());
        }
    }
}
