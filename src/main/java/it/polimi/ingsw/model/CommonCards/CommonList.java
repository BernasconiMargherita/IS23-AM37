package it.polimi.ingsw.model.CommonCards;

import java.io.Serializable;

/**
 * Enum for distinguish the common goal cards, used by Utils to generate the corresponding card
 */
public enum CommonList implements Serializable {

    SIX_GROUPS_OF_TWO,
    FOUR_EQUALS_ANGLES,
    FOUR_GROUPS_OF_FOUR,
    TWO_GROUPS_IN_SQUARE,
    THREE_COLUMNS_THREE_DIFFERENT_TYPES,
    EIGHT_EQUALS,
    FIVE_IN_DIGONAL,
    FOUR_ROWS_THREE_DIFFERENT_TYPES,
    TWO_COLUMNS_ALL_DIFFERENT,
    TWO_ROWS_ALL_DIFFERENT,
    FIVE_IN_A_X,
    IN_DESCENDING_ORDER;
}
