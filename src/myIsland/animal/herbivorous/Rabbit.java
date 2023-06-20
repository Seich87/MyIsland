package myIsland.animal.herbivorous;

import myIsland.animal.Animal;
import myIsland.island.Cell;
import lombok.Data;

@Data
public class Rabbit extends Animal implements Herbivorous {

    public Rabbit() {
        super();
    }

}
