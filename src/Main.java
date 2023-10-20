import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Music Notes!");

        Chord c = new Chord("C#*7");
        c.printDescription();

        MusicMaker mm = new MusicMaker();
        Song aisong = mm.generateSong();
        //System.out.println(aisong);

    }
}