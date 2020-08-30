package com.hluther.controlClasses;
import com.hluther.entityClasses.Node;
/**
 *
 * @author helmuth
 */
public class NodesDriver {
    
     public Node createConcatNode(String value, Node leftNode){
        Node rightNode = new Node(value, true); 
        Node parentNode = new Node(".", leftNode, rightNode, false); 
        return parentNode;
    }
     
    public Node createUnitaryNode(String value, Node parentNode){
        Node leftNode = new Node(value, true); 
        parentNode.setLeft(leftNode);
        return parentNode;
    }
 
    public Node createUnitaryEdge(Node leftNode, Node parentNode){
        parentNode.setLeft(leftNode); 
        return parentNode;
    }

    public Node createBinaryNode(String value, Node parentNode, Node leftNode){
        Node rightNode = new Node(value, null, null, true);
        parentNode.setLeft(leftNode); 
        parentNode.setRight(rightNode); 
        return parentNode;
    }
    
    public Node createBinaryEdge(Node leftNode, Node parentNode, Node rightNode){
        parentNode.setLeft(leftNode); 
        parentNode.setRight(rightNode); 
        return parentNode;
    }

    public Node createMultiNode(String value, Node leftNode, Node parentNode, Node rightNode){
        Node node = new Node(value, true);
        rightNode.setLeft(node);
        parentNode.setLeft(leftNode);
        parentNode.setRight(rightNode);
        return parentNode;
    }

    public Node createMultiEdge(Node node, Node leftNode, Node parentNode, Node rightNode){
        rightNode.setLeft(node);
        parentNode.setLeft(leftNode);
        parentNode.setRight(rightNode);
        return parentNode;
    }
 
    public Node createMacroNode(String interval){
        char startValue = interval.charAt(0);
        char endValue = interval.charAt(2);
        Node currentNode = null;
        if(endValue < startValue){
            startValue = interval.charAt(2);
            endValue = interval.charAt(0);
        }
        for(int i = (int)startValue; i <= (int)endValue; i++){
            
            if(i == (int)startValue){
                Node leftNode = new Node((String.valueOf((char)i)), true);
                Node parentNode = new Node("|", leftNode, leftNode, false);
                currentNode = parentNode;
            }
            else if(i == (int)startValue+1){
                Node rightNode = new Node((String.valueOf((char)i)), true);
                currentNode.setRight(rightNode);
            }
            else{
                Node rightNode = new Node((String.valueOf((char)i)), true);
                Node root = new Node("|", currentNode, rightNode, false);
                currentNode = root;
            }   
        }
        return currentNode;
    }

}
