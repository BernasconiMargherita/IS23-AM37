package it.polimi.ingsw.model.Board;


import com.google.gson.Gson;

import java.io.*;

public class BoardMaskParser implements Serializable {

    private BoardMask boardMask;

    public BoardMaskParser() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/boardMasks.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        boardMask = gson.fromJson(reader, BoardMask.class);
        reader.close();
    }

    public BoardMask getBoardMask() {
        return boardMask;
    }
}
