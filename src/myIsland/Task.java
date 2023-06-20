package myIsland;

import myIsland.animal.Animal;
import myIsland.animal.herbivorous.Herbivorous;
import myIsland.animal.predators.Predators;
import myIsland.animal.Type;
import myIsland.island.Cell;

public class Task implements Runnable {
    private final Cell cell;
    private boolean flag = false;

    public Task(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {

        cell.getPlant().growUp();

        eatPredators();

        while (flag) {
            eatHerbivorous();
        }
    }

    private void eatPredators() {
        for (Type type : cell.getMapCell().keySet()) {
            if (!cell.getMapCell().get(type).isEmpty()) {
                for (Class<?> clazz : cell.getMapCell().get(type).get(0).getClass().getInterfaces()) {
                    if (Predators.class.equals(clazz)) {
                        for (Animal animal : cell.getMapCell().get(type)) {
                            animal.eat(cell);
                        }
                    }
                }
            } else {
                cell.getMapCell().remove(type);
            }
        }
        flag = true;
    }

    private void eatHerbivorous() {

        for (Type type : cell.getMapCell().keySet()) {
            if (!cell.getMapCell().get(type).isEmpty()) {
                for (Class<?> clazz : cell.getMapCell().get(type).get(0).getClass().getInterfaces()) {
                    if (Herbivorous.class.equals(clazz)) {
                        for (Animal animal : cell.getMapCell().get(type)) {
                            animal.eat(cell);
                        }
                    }
                }
            }
            else {
                cell.getMapCell().remove(type);
            }
        }
        flag = false;
    }
}




