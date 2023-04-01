package MyIsland.Animal;

public class AnimalView {
    public static String getAnimalView(Type type) {
        String view = "";
        switch (type) {
            case WOLF -> view =  "🐺";
            case BOA -> view = "\uD83D\uDC0D";
            case FOX -> view = "\uD83E\uDD8A";
            case BEAR -> view = "\uD83D\uDC3B";
            case EAGLE -> view = "\uD83E\uDD85";
            case HORSE -> view = "\uD83D\uDC0E";
            case DEER -> view = "\uD83E\uDD8C";
            case RABBIT -> view = "\uD83D\uDC07";
            case MOUSE -> view = "\uD83D\uDC01";
            case GOAT -> view = "\uD83D\uDC10";
            case SHEEP -> view = "\uD83D\uDC11";
            case WILDBOAR -> view = "\uD83D\uDC17";
            case BUFFALO -> view = "\uD83D\uDC03";
            case DUCK -> view = "\uD83E\uDD86";
            case CATERPILLAR -> view = "\uD83D\uDC1B";
            default -> view = "";
        }
        return view;
    }

}
