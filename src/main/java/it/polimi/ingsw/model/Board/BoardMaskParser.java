package it.polimi.ingsw.model.Board;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;

/**
 * Parses a JSON file containing board mask configurations and provides access to the parsed BoardMask object.
 */
public class BoardMaskParser implements Serializable {

    private final BoardMask boardMask;

    /**
     * Constructs a BoardMaskParser and parses the board mask configurations from a JSON file.
     *
     * @throws FileNotFoundException If the JSON file cannot be found.
     */
    public BoardMaskParser() throws FileNotFoundException {
        FileReader reader = new FileReader("src/main/resources/json/boardMasks.json");
        Gson gson = new Gson();
        boardMask = gson.fromJson(reader, BoardMask.class);
    }

    /**
     * Retrieves the parsed BoardMask object.
     *
     * @return The parsed BoardMask object.
     */
    public BoardMask getBoardMask() {
        return boardMask;
    }
}
