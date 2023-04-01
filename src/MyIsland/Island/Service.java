package MyIsland.Island;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Type;

public class Service {
    final static String block = "";

    public void iterate() throws InterruptedException {

        // выбор траектории
        synchronized (block) {
            for (int i = 0; i < Field.cells.length; i++) {
                for (int j = 0; j < Field.cells[i].length; j++) {
                    for (Type type : Field.cells[i][j].mapCell.keySet()) {
                        for (Animal animal : Field.cells[i][j].mapCell.get(type)) {
                            animal.chooseDirectOfMove(Field.cells[i][j]);
                        }
                    }
                }
            }
        }

        Thread.sleep(5000);

        // передвижение
        synchronized (block) {
            for (int i = 0; i < Field.cells.length; i++) {
                for (int j = 0; j < Field.cells[i].length; j++) {
                    for (Type type : Field.cells[i][j].mapCell.keySet()) {
                        for (Animal animal : Field.cells[i][j].mapCell.get(type)) {
                            animal.move(Field.cells[i][j]);
                        }
                    }
                }
            }
        }

        Thread.sleep(10000);

        // размножение
        synchronized (block) {
            for (int i = 0; i < Field.cells.length; i++) {
                for (int j = 0; j < Field.cells[i].length; j++) {
                    for (Type type : Field.cells[i][j].mapCell.keySet()) {
                        if (Field.cells[i][j].mapCell.get(type).size() > 1) {
                            Field.cells[i][j].mapCell.get(type).get(0).reproduce(Field.cells[i][j]);
                        }
                    }
                }
            }
        }

    }
}


