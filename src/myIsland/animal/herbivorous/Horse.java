package myIsland.animal.herbivorous;

import myIsland.animal.Animal;
import myIsland.island.Cell;
import lombok.Data;

@Data
public class Horse extends Animal implements Herbivorous {

    public Horse() {
        super();
    }
}
