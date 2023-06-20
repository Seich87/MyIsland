package myIsland.animal.predators;

import myIsland.animal.Animal;
import myIsland.island.Cell;
import lombok.Data;

@Data
public class Wolf extends Animal implements Predators {

    public Wolf() {
        super();
    }
}
