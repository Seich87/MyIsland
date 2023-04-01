package MyIsland;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Type;
import MyIsland.Island.Cell;

public class TaskHunger implements Runnable {
    Cell cell;

    public TaskHunger(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        //рост голода
        for (Type type : cell.getMapCell().keySet()) {
            if (!cell.getMapCell().get(type).isEmpty()) {
                for (Animal animal : cell.getMapCell().get(type)) {
                    animal.setHunger(animal.getHunger() - animal.getHunger() / 4);
                    hunger(animal);
                }
            } else {
                cell.getMapCell().remove(type);
            }
        }
    }

    public void hunger(Animal animal) {
        // проверка на голод
        if (animal.getHunger() < animal.getSatiety() / 4) {
            cell.getMapCell().get(animal.getType()).remove(animal);
            System.out.println(animal.getView() + " сдохло от голода в клетке X-" + cell.getX() + ", Y-" + cell.getY());
        }
    }
}
