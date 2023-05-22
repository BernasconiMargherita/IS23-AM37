package it.polimi.ingsw.Network;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PairSocket {
    private BufferedReader in;
    private PrintWriter out;

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PairSocket(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
}
