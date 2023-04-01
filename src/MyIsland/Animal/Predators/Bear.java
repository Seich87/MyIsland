package MyIsland.Animal.Predators;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Type;
import MyIsland.Island.Cell;
import lombok.Data;

import java.util.Map;

@Data

public class Bear extends Animal implements Predators {
    public Bear() {
        super();
    }

    @Override
    public void eat(Cell cell) {
        super.eat(cell);
    }

    @Override
    public void reproduce(Cell cell) {
        super.reproduce(cell);
    }

    @Override
    public void chooseDirectOfMove(Cell cell) {
        super.chooseDirectOfMove(cell);
    }




}
