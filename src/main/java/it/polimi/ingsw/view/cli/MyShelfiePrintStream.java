package it.polimi.ingsw.view.cli;
import java.io.PrintStream;
public class MyShelfiePrintStream extends PrintStream {
    MyShelfiePrintStream() {
        super(System.out, true);

}}