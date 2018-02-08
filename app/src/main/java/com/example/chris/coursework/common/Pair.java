package com.example.chris.coursework.common;

/**
 * Created by Chris on 06/02/2018.
 */

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair() {}

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public L getFirst() {
        return left;
    }

    public void setFirst(L first) {
        this.left = left;
    }

    public R getLast() {
        return right;
    }

    public void setLast(R last) {
        this.right = right;
    }

    @Override
    public String toString() {
        return this.left + " : " + this.right;
    }
}
