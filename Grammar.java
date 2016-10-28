import java.util.*;

/**
 * This class holds all the grammar functions. For checking whether the grammar
 * of the users expression is correct or not
 * @author 7110015
 */
public class Grammar
{
    /**
     * Global string that holds the expression
     */
    protected String expression;   
	
    /**
     * Global string that holds the original expression just in case the other
     * gets changed at all
     */
    protected String oExpression;

    /**
     * Fills the expression strings
     * @param s
     */
    public Grammar( String s )
    {
	expression = s;
	oExpression = s;
    }

    /**
     * Prints out whether the expression is valid or not 
     */
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
    
    /**
     * Checks if the expressions format is correct. It does this by first 
     * looking for addops in the expression and if they are in the correct
     * location it sets the exprFlag to true otherwise it stays false. It will
     * then run the term function to see if the terms are correct.
     * @param s
     * @return
     */
    public boolean expr( String s )
    {
	boolean exprFlag = false;
	char op = getOp( s );
        String[] arr;       
 
        System.out.println("expr: " + s);
        if( s.charAt(0) == '+' )
	    return false; 
 
	// test for operator
	if( (op == '+' || op == '-') && s.charAt(0) != '-' )
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
	   exprFlag = term( s );
	}
	
	return exprFlag;
    }
	
    /**
     * Checks whether the grammar of the term is correct. It does this by 
     * first checking if the term starts with mulop, if it does then it 
     * returns false. Else it will check if there is another term. It will
     * then run the factor function to make sure those are correct. If it
     * passes all those tests it will then return true. 
     * @param s
     * @return
     */
    public boolean term( String s )
    {
	boolean termFlag = false;
	char op = getOp( s );
	String[] arr;

	System.out.println( "term: " + s );
	if( isMultop(s.charAt(0)))
		return false; 

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
	    int count = 0;
	    if( s.charAt(0) != '(' )
		return false;
	
	    for( int i = 0; i < s.length(); i++)
	    {
		//find beginning and end
		if( s.charAt(i) == '(' )
		{
		   count++;
		}
		else if( s.charAt(i) == ')' )
		{
		    count--;
		}

                //if the end is found it is an expression
		if( s.charAt(i) == ')' && count == 0 )
		{
		    try{
		    fo = s.indexOf("(");
		    lo = i;
		    System.out.println("end peren at: " + i);
		    sub = s.substring(fo + 1, lo);
	            }catch (StringIndexOutOfBoundsException e){
		        return false;
	            }
	            factorFlag = expr( sub );
		    break;
                }
	    }
	}
	else if( s.charAt(0) == '-' )
	{
	    sub = s.substring(1 , s.length() );
	    factorFlag = factor( sub );
	}
	return factorFlag;
    }

    public boolean id(String s)
    {
	System.out.println( " in id" );
        boolean lFlag = false;
	boolean dFlag = false;   
	char d = 0;
        int o = 0;
	
	if( isLetter(s.charAt(0)) == false)
	{
	    return false;
        } 
	for( int i = 0; i < s.length(); i++)
        {
	    lFlag = isLetter(s.charAt(i));
	   
            try{
                d = s.charAt(i);
		o = Integer.parseInt(s.valueOf( d ));
		dFlag = isDigit( o );
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


