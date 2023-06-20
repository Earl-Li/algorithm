package com.atlisheng.algor.bean;

public class Node<T> {
    public T value;
    public Node left;
    public Node right;

    public Node(T value) {
        this.value = value;
    }
}
