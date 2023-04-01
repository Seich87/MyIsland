package MyIsland.Animal.Predators;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Type;
import MyIsland.Island.Cell;
import lombok.Data;

import java.util.Map;

@Data

public class Eagle extends Animal implements Predators {

    @Override
    public void eat(Cell cell) {
        super.eat(cell);
    }

    @Override
    public void reproduce(Cell cell) {
        super.reproduce(cell);
    }

    @Override
    public synchronized void chooseDirectOfMove(Cell cell) {
        super.chooseDirectOfMove(cell);
    }

    @Override
    public synchronized void move(Cell cell) {
        super.move(cell);
    }

    public Eagle() {
        super();
    }
}
