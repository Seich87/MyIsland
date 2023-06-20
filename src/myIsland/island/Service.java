package myIsland.island;

import myIsland.animal.Animal;
import myIsland.animal.Type;

public class Service {

    public void iterate() throws InterruptedException {

        synchronized (Service.class) {

            for (int i = 0; i < Field.getCELLS().length; i++) {
                for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                    for (Type type : Field.getCELLS()[i][j].getMapCell().keySet()) {
                        for (Animal animal : Field.getCELLS()[i][j].getMapCell().get(type)) {
                            animal.chooseDirectOfMove(Field.getCELLS()[i][j]);
                        }
                    }
                }
            }
        }

        Thread.sleep(5000);

        synchronized (Service.class) {
            for (int i = 0; i < Field.getCELLS().length; i++) {
                for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                    for (Type type : Field.getCELLS()[i][j].getMapCell().keySet()) {
                        for (Animal animal : Field.getCELLS()[i][j].getMapCell().get(type)) {
                            animal.move(Field.getCELLS()[i][j]);
                        }
                    }
                }
            }
        }

        Thread.sleep(10000);

        synchronized (Service.class) {
            for (int i = 0; i < Field.getCELLS().length; i++) {
                for (int j = 0; j < Field.getCELLS()[i].length; j++) {
                    for (Type type : Field.getCELLS()[i][j].getMapCell().keySet()) {
                        if (Field.getCELLS()[i][j].getMapCell().get(type).size() > 1) {
                            Field.getCELLS()[i][j].getMapCell().get(type).get(0).reproduce(Field.getCELLS()[i][j]);
                        }
                    }
                }
            }
        }

    }
}


