package MyIsland.Island;

import MyIsland.Animal.Animal;
import MyIsland.Animal.Plant;
import lombok.Data;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data
public class Cell {
    Plant plant;

    ConcurrentMap<MyIsland.Animal.Type, CopyOnWriteArrayList<Animal>> mapCell;

    public int x;
    public int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        plant = new Plant();
        InitialCellsData.initial(this);
    }
}
