import java.util.*;

public class Parser
{
    public static boolean tokenFlag = false;

    public static void main( String [] args )
    {
        parseCMD( args );
        Scan scan = new Scan( tokenFlag );
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


}
