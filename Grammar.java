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

    public boolean testGrammar()
    {
	expr();
	return false;
    }

    public boolean expr()
    {
	System.out.println( "XP: " + getOp() );
	char op = getOp();

	if( op == '+' || op == '-' )
	{
	  //check to see if another term is added
	    String[] arr = expression.split("\\+",2);
	    for (String a: arr)
	    {
		System.out.println(":: " + a );
	    }
	    System.out.println("expr");
	}
	else
	{
	   //call factor
	   System.out.println("pass expr");
	   term();
	}
	
	return false;
    }

    public boolean term()
    {
	char op = getOp();
	if ( op == '*' || op == '/' || op == '%' )
	{
	// see if nother term
	    System.out.println("term");
	}
	else
	{
	//call to factor
	    System.out.println("pass term");
	    factor();
	}
	return false;
    }

    public boolean factor()
    {
	char op = getOp();
	if( isInteger( expression ) )
	{
	    System.out.println( "integer" );
	}
        else if ( isFloat( expression ) )
	{
	    System.out.println( "Float" );
	}
	else if ( id( expression ) )
        {
	    System.out.println( "ID" );
        }
	return false;
    }

    public boolean id(String s)
    {
	//test first char to make a char
	char[] x = new char[s.length()];

	//test rest to make sure int or letter
	return false;
    }

    public boolean isInteger(String s)
    {
       System.out.println("is int");
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
	System.out.println("is digit");
	return (Integer.toString(i).matches("[0-9]"));
    }
 
    public boolean isFloat(String s)
    {

	String arr[] = s.split("\\.");
	try{
	if( isInteger(arr[0]) && isInteger(arr[1]) )
	{
	    System.out.println("is float");	
	}} catch (ArrayIndexOutOfBoundsException e){
		return false;
	}
	return true;
    }

    public char getOp()
    {

        for(int i = 0; i < expression.length(); i++)
        {
	    switch( expression.charAt(i) )
	    {
		case '+':
		    return '+';
		case '-':
		    return '-';
		case '*':
		    return '*';
		case '/':
		    return '/';
		case '(':
		    return '(';
		case ')':
		    return ')';
	    }
	    
        }
	
	return 'x';
    }
    
}
