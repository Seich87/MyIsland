package myIsland.animal.predators;

import myIsland.animal.Animal;
import myIsland.island.Cell;
import lombok.Data;

@Data
public class Fox extends Animal implements Predators {

    public Fox() {
        super();
    }
}
