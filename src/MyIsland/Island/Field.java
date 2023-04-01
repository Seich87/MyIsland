package MyIsland.Island;

public class Field {
    public static final Cell[][] cells = new Cell[10][10];

    public void filling() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        System.out.println("Поле заполнено. Игра начинается...");
    }

}
