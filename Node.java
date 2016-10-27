import java.util.*;

class Node
{
    Node parent;
    Node left;
    Node right;
    String expression;
    String operator;

    public Node(String expression, String operator, Node left, Node right)
    {
        this.expression = expression;
        this.operator = operator;
        this.left = null;
        this.right = null;
    }

    
    
}
