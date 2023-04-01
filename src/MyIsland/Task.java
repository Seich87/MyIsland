package MyIsland;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Herbivorous.Herbivorous;
import MyIsland.Animal.Predators.Predators;
import MyIsland.Animal.Type;
import MyIsland.Island.Cell;

public class Task implements Runnable {
    Cell cell;
    final String block = "";
    boolean flag = false;

    public Task(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {

        // рост травы
        cell.getPlant().growUp();

        //  кушают хищники
        eatPredators();

        // кушают травоядные
        while (flag) {
            eatHerbivorous();
        }
    }

    private void eatPredators() {
        for (Type type : cell.getMapCell().keySet()) {
            if (!cell.getMapCell().get(type).isEmpty()) {
                for (Class<?> clazz : cell.getMapCell().get(type).get(0).getClass().getInterfaces()) {
                    if (clazz.equals(Predators.class)) {
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
                    if (clazz.equals(Herbivorous.class)) {
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




