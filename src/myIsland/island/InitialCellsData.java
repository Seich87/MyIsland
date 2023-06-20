package myIsland.island;

import myIsland.animal.Animal;
import myIsland.animal.herbivorous.*;
import myIsland.animal.predators.*;
import myIsland.animal.Type;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class InitialCellsData {


    public static void initial(Cell cell) {
        int countAnimalInCell = ThreadLocalRandom.current().nextInt(2, 4);
        int countAnimalInList = ThreadLocalRandom.current().nextInt(2, 3);
        int typeLength = Type.values().length - 2;

        cell.setMapCell(new ConcurrentHashMap<>());
        cell.getPlant().setGrassVol(ThreadLocalRandom.current().nextDouble(0.5, 0.8));

        for (int i = 0; i < countAnimalInCell; i++) {
            CopyOnWriteArrayList<Animal> list = new CopyOnWriteArrayList<>();

            int typeRandom = ThreadLocalRandom.current().nextInt(0, typeLength);
            for (int j = 0; j < countAnimalInList; j++) {
                mapPut(cell, typeRandom, list);
            }
        }
    }

    private static void mapPut(Cell cell, int typeRandom, CopyOnWriteArrayList<Animal> list) {
        Type type = Type.values()[typeRandom];

        switch (type) {
            case WOLF -> list.add(new Wolf());
            case BOA -> list.add(new Boa());
            case FOX -> list.add(new Fox());
            case BEAR -> list.add(new Bear());
            case RABBIT -> list.add(new Rabbit());
            case MOUSE -> list.add(new Mouse());
            case DUCK -> list.add(new Duck());
            case EAGLE -> list.add(new Eagle());
            case HORSE -> list.add(new Horse());
            case DEER -> list.add(new Deer());
            case GOAT -> list.add(new Goat());
            case SHEEP -> list.add(new Sheep());
            case WILDBOAR -> list.add(new WildBoar());
            case BUFFALO -> list.add(new Buffalo());
            case CATERPILLAR -> list.add(new Caterpillar());
        }
        cell.getMapCell().put(type, list);
    }

}
