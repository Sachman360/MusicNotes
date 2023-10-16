import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Music Notes!");
        List<Note> melody = new ArrayList<>();
        melody.add(new Note("B"));
        melody.add(new Note("A"));
        melody.add(new Note("G#"));
        melody.add(new Note("A"));
        melody.add(new Note("D", 5));
        for(int i = 0; i < melody.size(); i++) {
            //System.out.print(melody.get(i) + " ");
        }
        System.out.println();
        Chord c = new Triad(new Note(18), new Note("D"), new Note("A", 6));
        //System.out.println(c.printDescription());
        List<Note> l2 = new ArrayList<>(Arrays.asList(new Note(17), new Note(20), new Note("Cb")));
        Chord c2 = new Chord(l2);
        //System.out.println(c2.printDescription());
        Chord c3 = new Seventh(new Note(7), new Note(4), new Note(0), new Note(11));
        //System.out.println(c3.printDescription());
        Chord c4 = new Chord(new ArrayList<>(Arrays.asList(new Note(50), new Note(47), new Note(55), new Note(41))));
        //System.out.println(c4.printDescription());

        Chord c5 = new Chord("Cm7");
        //System.out.println(c5.printDescription());

        Song firstSong = new Song();
        for(int i = 0; i < melody.size(); i++) {
            firstSong.addNote(melody.get(i));
        }
        firstSong.addChord(c);
        firstSong.addChord(c2);
        firstSong.addChord(c3);
        firstSong.addChord(c4);
        firstSong.addChord(c5);
        System.out.println(firstSong);

        Key k = new Key(new Note("C"), "m");
    }
}