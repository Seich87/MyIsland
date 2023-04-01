package MyIsland.Animal;

import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
@Data
public class Plant {
    static final Properties prop1 = new Properties();
    static FileInputStream fis;

    Type type;
    double weight;
    int maxInOneCell;
    public double grassVol;

    public Plant() {
        try {
            fis = new FileInputStream("config.properties");
            prop1.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        type = Type.PLANT; //Type.valueOf(getClass().getName().toUpperCase());
        String className = getClass().getName().toLowerCase(Locale.ROOT);
        weight = 1; //Double.parseDouble(prop1.getProperty(className + ".weight"));
        maxInOneCell = 200; //Integer.parseInt(prop1.getProperty(className + ".maxInOneCell"));
        grassVol = ThreadLocalRandom.current().nextDouble(0.5, 0.8);
    }

    // рост травы
    public void growUp() {
        double a = grassVol;
        double threadLocalRandom = ThreadLocalRandom.current().nextDouble(0.5, 0.8);
        grassVol += threadLocalRandom;
        System.out.println("Трава: было " + a + ", стало "+ grassVol);
    }

}
