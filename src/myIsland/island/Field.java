package myIsland.island;

import lombok.Data;

@Data
public class Field {
    private final static Cell[][] CELLS = new Cell[10][10];

    public static Cell[][] getCELLS() {
        return CELLS;
    }

    public void filling() {
        for (int i = 0; i < CELLS.length; i++) {
            for (int j = 0; j < CELLS[i].length; j++) {
                CELLS[i][j] = new Cell(i, j);
            }
        }
        System.out.println("Поле заполнено. Игра начинается...");
    }

}
