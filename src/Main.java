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
        System.out.println(c.printDescription());
        List<Note> l2 = new ArrayList<>(Arrays.asList(new Note(17), new Note(20), new Note("Cb")));
        Chord c2 = new Chord(l2);
        System.out.println(c2.printDescription());
    }
}