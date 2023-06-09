package myIsland.animal;

import myIsland.animal.herbivorous.*;
import myIsland.animal.predators.*;
import myIsland.island.Cell;
import myIsland.island.Field;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Data
public abstract class Animal {
    static final Properties PROP = new Properties();
    static FileInputStream fis;

    private Type type;
    private double weight;
    private int speed;
    private double satiety;

    private double hunger;
    private int maxInOneCell;
    private String view;
    private String gender;
    private Map<Type, Integer> ration;
    private int newX;
    private int newY;
    private double year;

    public void eat(Cell cell) {
        for (Class<?> clazz : this.getClass().getInterfaces()) {

            if (clazz.equals(Predators.class)) {
                eatPredators(cell);

            } else if (clazz.equals(Herbivorous.class)) {
                eatHerbivorous(cell);
            }
        }
    }

    private synchronized void eatPredators(Cell cell) {
        int maxRation = 0;
        Type maxType = null;
        for (Type typeRation : ration.keySet()) {
            for (Type typeCell : cell.getMapCell().keySet()) {
                if (typeRation.equals(typeCell) && ration.get(typeRation) > maxRation && !cell.getMapCell().get(typeCell).isEmpty()) {
                    maxRation = ration.get(typeRation);
                    maxType = typeCell;
                }
            }
        }


        if (maxType != null) {
            System.out.println(this.view + " сожрало " + cell.getMapCell().get(maxType).get(0).view + " в клетке Х-" + cell.getX() + ", Y-" + cell.getY());
            if (hunger > cell.getMapCell().get(maxType).get(0).weight) {
                hunger = hunger - cell.getMapCell().get(maxType).get(0).weight;
                cell.getMapCell().get(maxType).remove(0);
            } else {
                hunger = 0;
                cell.getMapCell().get(maxType).remove(0);
            }
        }
    }


    private void eatHerbivorous(Cell cell) {
        if (hunger + cell.getPlant().grassVol > satiety) {
            if (cell.getPlant().grassVol > hunger) {
                cell.getPlant().grassVol = cell.getPlant().grassVol - hunger;
                hunger = 0;
            } else {
                hunger = hunger - cell.getPlant().grassVol;
                cell.getPlant().grassVol = 0;
            }
        } else {
            hunger = hunger - cell.getPlant().grassVol;
            cell.getPlant().grassVol = 0;
        }
        System.out.println(this.view + "в клетке X-" + cell.getX() + ", Y-" + cell.getY() + " пожрало травы");
    }


    public void reproduce(Cell cell) {
        for (Type type : cell.getMapCell().keySet()) {
            CopyOnWriteArrayList<Animal> list = cell.getMapCell().get(type);
            int male = 0;
            int female = 0;
            if (cell.getMapCell().get(type).size() > 1) {
                for (Animal animal : cell.getMapCell().get(type)) {
                    if (("male").equals(animal.gender) && animal.year > 2) {
                        male += 1;
                    } else if (("female").equals(animal.gender) && animal.year > 2) {
                        female += 1;
                    }
                }
            }

            if (male > 0 && female > 0) {
                while (male > 0 && female > 0) {
                    Animal animal = createNewAnimal(type);
                    list.add(animal);
                    System.out.println("В ячейке Х-" + cell.getX() + ", Y-" + cell.getY() + " пополнение: " + animal.view);
                    male -= 1;
                    female -= 1;
                }
            }
            cell.getMapCell().put(type, list);
        }
    }


    public synchronized void chooseDirectOfMove(Cell cell) {
        int xRandom = ThreadLocalRandom.current().nextInt(1, speed + 1);
        int yRandom = speed - xRandom;

        newX = -1;
        newY = -1;
        if (cell.getX() + xRandom < Field.getCELLS().length) {
            newX = cell.getX() + xRandom;
        } else if (cell.getX() - xRandom >= 0) {
            newX = cell.getX() - xRandom;
        }

        if (cell.getY() + yRandom < Field.getCELLS().length) {
            newY = cell.getY() + yRandom;
        } else if (cell.getY() - yRandom >= 0) {
            newY = cell.getY() - yRandom;
        }
    }


    public synchronized void move(Cell cell) {
        if (Field.getCELLS()[newX][newY].getMapCell().containsKey(this.type)) {
            if (Field.getCELLS()[newX][newY].getMapCell().get(this.type).size() + 1 <= maxInOneCell) {
                Field.getCELLS()[newX][newY].getMapCell().get(this.type).add(this);
                cell.getMapCell().get(this.type).remove(this);
            }
        } else {
            CopyOnWriteArrayList<Animal> list = new CopyOnWriteArrayList<>();
            list.add(this);
            Field.getCELLS()[newX][newY].getMapCell().put(this.type, list);
            cell.getMapCell().get(this.type).remove(this);
        }
        if (newX != 0 && newY != 0) {
            System.out.println("Животное: " + this.type + " передвинулось из клетки Х-" + cell.getX() + ", Y-" + cell.getY() + " в клетку Х-" + newX + ", Y-" + newY);
        }
    }


    public Animal() {
        try {
            fis = new FileInputStream("config.properties");
            PROP.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int genderInt = ThreadLocalRandom.current().nextInt(0, 2);
        if (genderInt == 0) {
            gender = "male";
        } else if (genderInt == 1) {
            gender = "female";
        }

        type = Type.valueOf(getClass().getName().toUpperCase().substring(Integer.parseInt(String.valueOf(getClass().getName().lastIndexOf("."))) + 1));
        view = AnimalView.getAnimalView(this.type);
        year = ThreadLocalRandom.current().nextDouble(1, 5);
        //hunger = ThreadLocalRandom.current().nextInt(0, 50);
        String className = getClass().getName().substring(Integer.parseInt(String.valueOf(getClass().getName().lastIndexOf("."))) + 1).toLowerCase(Locale.ROOT);
        weight = Double.parseDouble(PROP.getProperty(className + ".weight"));
        speed = Integer.parseInt(PROP.getProperty(className + ".speed"));
        satiety = Double.parseDouble(PROP.getProperty(className + ".satiety"));
        hunger = ThreadLocalRandom.current().nextDouble(0, satiety / 2);
        maxInOneCell = Integer.parseInt(PROP.getProperty(className + ".maxInOneCell"));
        ration = Arrays.stream(PROP.getProperty(className + ".ration").split(","))
                .map(str -> str.split(":"))
                .collect(Collectors.toMap(arr -> Type.valueOf(arr[0]), arr -> Integer.parseInt(arr[1])));

    }


    public static Animal createNewAnimal(Type type) {
        Animal animal = null;
        switch (type) {
            case WOLF -> animal = new Wolf();
            case BOA -> animal = new Boa();
            case FOX -> animal = new Fox();
            case BEAR -> animal = new Bear();
            case RABBIT -> animal = new Rabbit();
            case MOUSE -> animal = new Mouse();
            case DUCK -> animal = new Duck();
            case EAGLE -> animal = new Eagle();
            case HORSE -> animal = new Horse();
            case DEER -> animal = new Deer();
            case GOAT -> animal = new Goat();
            case SHEEP -> animal = new Sheep();
            case WILDBOAR -> animal = new WildBoar();
            case BUFFALO -> animal = new Buffalo();
            case CATERPILLAR -> animal = new Caterpillar();
            default -> animal = new Wolf();
        }

            animal.year = 0.5;

        return animal;
    }
}