package myIsland.island;

import myIsland.animal.Animal;
import myIsland.animal.Plant;
import lombok.Data;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Data
public class Cell {
    private Plant plant;

    private ConcurrentMap<myIsland.animal.Type, CopyOnWriteArrayList<Animal>> mapCell;

    private int x;
    private int y;



    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        plant = new Plant();
        InitialCellsData.initial(this);
    }
}
