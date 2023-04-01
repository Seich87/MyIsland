package MyIsland.Animal.Herbivorous;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Type;
import MyIsland.Island.Cell;
import lombok.Data;

import java.util.Map;

@Data
public class Mouse extends Animal implements Herbivorous {

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

    public Mouse() {
        super();
    }
}
