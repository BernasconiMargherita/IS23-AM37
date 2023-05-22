package it.polimi.ingsw.Network;

public class Paair<T, U> {
    private T first;
    private U second;

    public Paair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}