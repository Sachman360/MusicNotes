
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) {

        //AudioFileFormat aff =

        System.out.println("Welcome to Music Notes!");

        //Chord c = new Chord(new ArrayList<Note>(Arrays.asList(new Note(23), new Note(18), new Note(27))));
        Chord c = new Chord("VI7", new Key("D"));
        //c.printDescription();

        MusicMaker mm = new MusicMaker();
        Song aisong = mm.generateSong();
        //System.out.println(aisong);

    }


}