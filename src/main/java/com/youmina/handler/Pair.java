package com.youmina.handler;

/**
 * Created by xinhezhang on 2018/3/29.
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair(){
        first = null;
        second =null;
    }

    public Pair(T first,T second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public T getSecond() {

        return second;
    }
}
