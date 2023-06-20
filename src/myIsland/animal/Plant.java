package myIsland.animal;

import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
@Data
public class Plant {
    static final Properties PROP1 = new Properties();
    static FileInputStream fis;

    Type type;
    double weight;
    int maxInOneCell;
    public double grassVol;

    public Plant() {
        try {
            fis = new FileInputStream("config.properties");
            PROP1.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        type = Type.PLANT;
        weight = 1;
        maxInOneCell = 200;
        grassVol = ThreadLocalRandom.current().nextDouble(0.5, 0.8);
    }

    public void growUp() {
        double a = grassVol;
        double threadLocalRandom = ThreadLocalRandom.current().nextDouble(0.5, 0.8);
        grassVol += threadLocalRandom;
        System.out.println("Трава: было " + a + ", стало "+ grassVol);
    }

}
