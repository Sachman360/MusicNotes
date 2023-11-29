import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Music Notes!");

        //Chord c = new Chord(new ArrayList<Note>(Arrays.asList(new Note(23), new Note(18), new Note(27))));
        Chord c = new Chord("IV", new Key(new Note("C"), "M"));
        c.printDescription();

        MusicMaker mm = new MusicMaker();
        //Song aisong = mm.generateSong();
        //System.out.println(aisong);

    }
}