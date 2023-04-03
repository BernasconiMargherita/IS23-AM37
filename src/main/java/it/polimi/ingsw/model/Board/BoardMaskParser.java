package it.polimi.ingsw.model.Board;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class BoardMaskParser {

    private BoardMask boardMask;

    public BoardMaskParser() throws FileNotFoundException {
        FileReader reader = new FileReader("boardMasks.json");
        Gson gson = new Gson();
         boardMask = gson.fromJson(reader, BoardMask.class);
    }

    public BoardMask getBoardMask() {
        return boardMask;
    }
}
