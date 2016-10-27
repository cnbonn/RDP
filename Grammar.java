import java.util.*;

public class Grammar
{

    protected String expression;   
    protected String oExpression;

    public Grammar( String s )
    {
	expression = s;
	oExpression = s;
    }

    public void testGrammar()
    {
	boolean grammarFlag = false;
        if( expr( expression ) )
	{
	    System.out.println("\"" + oExpression + "\" is a valid expression");
	}
	else
	{
	    System.out.println("\"" + oExpression + "\" is not a valid expression");
	}
    }

    public boolean expr( String s )
    {
	boolean exprFlag = false;
	char op = getOp( s );
        String[] arr;       
 
        System.out.println("expr: " + s);
    
	// test for operator
	if( op == '+' || op == '-' )
	{
            //check to see if another term is added
	    switch(op)
	    {
		case '+':
		    arr = s.split("\\+",2);
		    if( isAddop(getOp(arr[1])) == true && expr(arr[1]) == true )
		    {
			exprFlag = true;
		    } 
		    else if( term(arr[0]) == true && term(arr[1]) == true)
		    {
			exprFlag = true;
                    }
		    break;
		case '-':
		    arr = s.split("\\-",2);
		    if( isAddop(getOp(arr[1])) == true && expr(arr[1]) == true )
		    {
			exprFlag = true;
		    }
		    else if( term(arr[0]) == true && term(arr[1]) == true)
                    {
			exprFlag = true;
		    }
	            break;
	    }
	}
	else
	{
	   //call factor
	   System.out.println( "skip expr");
	   exprFlag = term( s );
	}
	
	return exprFlag;
    }

    public boolean term( String s )
    {
	boolean termFlag = false;
	char op = getOp( s );
	String[] arr;

	System.out.println( "term: " + s );

	if ( op == '*' || op == '/' || op == '%' )
	{
	// see if nother term
	    switch(op)
	    {
		case '*':
		     arr = s.split("\\*",2);
		     if( isMultop(getOp(arr[1])) == true && term(arr[1]) == true )
		     {
			termFlag = true;
		     } 
		     if( factor(arr[0]) == true && factor(arr[1]) == true)
		     {
			termFlag = true;
		     }
		    break;
		case '/':
		    arr = s.split("\\/",2);
	             if( isMultop(getOp(arr[1])) == true && term(arr[1]) == true )
		     {
			termFlag = true;
		     }
		     if( factor(arr[0]) == true && factor(arr[1]) == true)
		     {
			termFlag = true;
		     }
		    break;
	    	case '%':
		     arr = s.split("\\%",2);
		     if( isMultop(getOp(arr[1])) == true && term(arr[1]) == true )
		     {
			termFlag = true;
		     }
                     if( factor(arr[0]) == true && factor(arr[1]) == true)
		     {
			termFlag = true;
		     }
	            break;
	    }
	}
	else
	{
	//call to factor
	    System.out.println("skip term");
	    termFlag = factor( s );
	}
	return termFlag;
    }

    public boolean factor( String s)
    {
	System.out.println("factor: " + s );
	int fo, lo;
	String sub;

	boolean factorFlag = false;
	char op = getOp(s);
	if( isInteger( s ) )
	{
	    factorFlag = true;
	}
        else if( isFloat( s ) )
	{
	    factorFlag = true;
	}
	else if( id( s ) )
        {
	    factorFlag = true;
        }
        else if( op == '(' )
	{
	    try{
	    fo = s.indexOf("(");
	    lo = s.lastIndexOf(")");
	    sub = s.substring(fo + 1, lo);
	    }catch (StringIndexOutofBoundsException e){
		return false;
	    }
	    factorFlag = expr( sub );
	}
	return factorFlag;
    }

    public boolean id(String s)
    {
	System.out.println( " in in" );
        boolean lFlag = false;
	boolean dFlag = false;   
	int d = 0;

	if( isLetter(s.charAt(0)) == false)
	{
	    return false;
        } 
	for( int i = 0; i < s.length(); i++)
        {
	    lFlag = isLetter(s.charAt(i));
	   
            try{
                d = Integer.parseInt(s);
		dFlag = isDigit( d );
            } catch (NumberFormatException e){
	    }

	    if( dFlag == false && lFlag == false)
	    {
		return false;
            }
	}	

	//test rest to make sure int or letter
	return true;
    }

    public boolean isInteger(String s)
    {

        int d;
        try{
           d = Integer.parseInt(s);
        } catch (NumberFormatException e){
	return false;
	}

       
       return isDigit( d );
    }
 
    public boolean isLetter(char c)
    {
	return (Character.toString(c).matches("[a-zA-Z_]"));
    }
 
 
    public boolean isDigit(int i)
    {
	return (Integer.toString(i).matches("[0-9]"));
    }
 
    public boolean isFloat(String s)
    {

	String arr[] = s.split("\\.");
	boolean floatFlag = false;
	try{
	if( isInteger(arr[0]) && isInteger(arr[1]) )
	{
	    floatFlag = true;	
	}} catch (ArrayIndexOutOfBoundsException e){
		return false;
	}
	return floatFlag;
    }

    public char getOp(String segment)
    {

        for(int i = 0; i < segment.length(); i++)
        {
	    switch( segment.charAt(i) )
	    {
		case '+':
		    return '+';
		case '-':
		    return '-';
		case '*':
		    return '*';
		case '/':
		    return '/';
		case '%':
		    return '%';
		case '(':
		    return '(';
		case ')':
		    return ')';
	    }
	    
        }
	
	return 'x';
    }
    public boolean isAddop(char c)
    {
    	return (Character.toString(c).matches("[+-]"));
    }	  

    public boolean isMultop(char c)
    {
	return (Character.toString(c).matches("[*/%]"));
    }
   
}


