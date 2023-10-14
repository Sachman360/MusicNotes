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
            System.out.print(melody.get(i) + " ");
        }
        System.out.println();
        Chord c = new Chord(new Note("C#"), new Note("E#"), new Note("A"));
        System.out.println(c);
    }
}