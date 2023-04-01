package MyIsland;

import MyIsland.Animal.Type;
import MyIsland.Island.Field;
import MyIsland.Island.Service;

public class Main {
    final static String block = "";
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();
        field.filling();

        Service service = new Service();

        while (flag) {
            // Рост травы, кушают хищники, травоядные
            synchronized (block) {
                for (int i = 0; i < Field.cells.length; i++) {
                    for (int j = 0; j < Field.cells[i].length; j++) {

                        new Thread(new Task(Field.cells[i][j])).start();
                    }
                }
            }
            Thread.sleep(1000);

            // выбор траектории, передвижение и размножение
            synchronized (block) {
                service.iterate();
            }

            Thread.sleep(5000);

            // рост голода и проверка на голод
            synchronized (block) {
                for (int i = 0; i < Field.cells.length; i++) {
                    for (int j = 0; j < Field.cells[i].length; j++) {
                        new Thread(new TaskHunger(Field.cells[i][j])).start();
                    }
                }
            }
            Thread.sleep(10000);

            // отчет по каждой клетки поля
            synchronized (block) {
                for (int i = 0; i < Field.cells.length; i++) {
                    for (int j = 0; j < Field.cells[i].length; j++) {
                        if (Field.cells[i][j].getMapCell().isEmpty()) {
                            System.out.print("|#|");
                        } else {
                            System.out.print("|");
                            for (Type type : Field.cells[i][j].getMapCell().keySet()) {
                                if (Field.cells[i][j].getMapCell().get(type).isEmpty()) {
                                    Field.cells[i][j].getMapCell().remove(type);
                                    System.out.print("#");
                                } else {
                                    System.out.print(Field.cells[i][j].getMapCell().get(type).get(0).getView() + "X" + Field.cells[i][j].getMapCell().get(type).size());
                                }
                            }
                        }
                    }
                    System.out.println();
                }
            }
            Thread.sleep(10000);

            gameStop();
        }
    }

    private static void gameStop() {
        int countAnimal = 0;

        for (int i = 0; i < Field.cells.length; i++) {
            for (int j = 0; j < Field.cells[i].length; j++) {
                for (Type type : Field.cells[i][j].getMapCell().keySet()) {
                    countAnimal += Field.cells[i][j].getMapCell().get(type).size();
                }
            }
        }

        if (countAnimal < 10) {
            flag = false;
        }
    }
}





