package com.hluther.entityClasses;

import java.util.ArrayList;

/**
 *
 * @author helmuth
 */
public class Node {
    
    private String value;
    private Node left;
    private Node right;
    private int id;
    private boolean nullable;
    private ArrayList<Integer> firstPos;
    private ArrayList<Integer> lastPos;
    private ArrayList<Integer> nextPos;
    private boolean leaf;

    public Node(String value, Node left, Node right, boolean leaf) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.leaf = leaf;
        this.firstPos = new ArrayList<>();
        this.lastPos = new ArrayList<>();
        if(this.leaf){
            nextPos = new ArrayList<>();
        }
    }

    public Node(String value, boolean leaf) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.leaf = leaf;
        this.firstPos = new ArrayList<>();
        this.lastPos = new ArrayList<>();
        if(this.leaf){
            nextPos = new ArrayList<>();
        }
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public ArrayList<Integer> getFirstPos() {
        return firstPos;
    }

    public void setFirstPos(ArrayList<Integer> firstPos) {
        this.firstPos = firstPos;
    }

    public ArrayList<Integer> getLastPos() {
        return lastPos;
    }

    public void setLastPos(ArrayList<Integer> lastPos) {
        this.lastPos = lastPos;
    }

    public ArrayList<Integer> getNextPos() {
        return nextPos;
    }

    public void setNextPos(ArrayList<Integer> nextPos) {
        this.nextPos = nextPos;
    }
    
    public void addFirstPos(int id){
        firstPos.add(id);
    }

    public void addLastPos(int id){
        lastPos.add(id);
    }
}
