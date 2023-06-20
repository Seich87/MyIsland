package myIsland.animal.herbivorous;

import myIsland.animal.Animal;
import myIsland.island.Cell;
import lombok.Data;

@Data
public class Caterpillar extends Animal implements Herbivorous {
    public Caterpillar() {
        super();
    }
}
