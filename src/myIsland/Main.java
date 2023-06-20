package myIsland;

import myIsland.animal.Type;
import myIsland.island.Field;
import myIsland.island.Service;

public class Main {
    static private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();
        field.filling();

        Service service = new Service();

        while (flag) {

            synchronized (Main.class) {
                for (int i = 0; i < Field.getCELLS().length; i++) {
                    for (int j = 0; j < Field.getCELLS()[i].length; j++) {

                        new Thread(new Task(Field.getCELLS()[i][j])).start();
                    }
                }
            }
            Thread.sleep(1000);

            synchronized (Main.class) {
                service.iterate();
            }

            Thread.sleep(5000);

            // рост голода и проверка на голод
            synchronized (Main.class) {
                for (int i = 0; i < Field.getCELLS().length; i++) {
                    for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                        new Thread(new TaskHunger(Field.getCELLS()[i][j])).start();
                    }
                }
            }
            Thread.sleep(10000);

            synchronized (Main.class) {
                for (int i = 0; i < Field.getCELLS().length; i++) {
                    for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                        if (Field.getCELLS()[i][j].getMapCell().isEmpty()) {
                            System.out.print("|#|");
                        } else {
                            System.out.print("|");
                            for (Type type : Field.getCELLS()[i][j].getMapCell().keySet()) {
                                if (Field.getCELLS()[i][j].getMapCell().get(type).isEmpty()) {
                                    Field.getCELLS()[i][j].getMapCell().remove(type);
                                    System.out.print("#");
                                } else {
                                    System.out.print(Field.getCELLS()[i][j].getMapCell().get(type).get(0).getView() + "X" + Field.getCELLS()[i][j].getMapCell().get(type).size());
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

        for (int i = 0; i < Field.getCELLS().length; i++) {
            for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                for (Type type : Field.getCELLS()[i][j].getMapCell().keySet()) {
                    countAnimal += Field.getCELLS()[i][j].getMapCell().get(type).size();
                }
            }
        }

        if (countAnimal < 10) {
            flag = false;
        }
    }
}





