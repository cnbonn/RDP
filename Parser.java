import java.util.*;

/**
 * This class handles the command line argument for printing token charters
 * if -t is entered it will set the flag to true otherwise it will remain false.
 * @author Charles Bonn
 */
public class Parser
{
    /**
     *A global flag, so the program knows whether to print the tokens or not.
     */
    public static boolean tokenFlag = false;

    /**
     *This function pulls in the command line arguments
     * 
     * @param args
     */
    public static void main( String [] args )
    {
        parseCMD( args );
        Scan scan = new Scan( tokenFlag );
    }
 
    /**
     *This function checks whether or not to set the tokenFlag to true
     * @param args
     */
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


}
