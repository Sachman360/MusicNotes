import java.util.*;

public class Chord {
    private List<Note> chord;
    private String name;
    private int id;

    public Chord(Note i, Note j, Note k) {
        chord = new ArrayList<>();
        chord.add(i);
        chord.add(j);
        chord.add(k);
        name = getName();
    }

    public String getName() {
        int iPos = chord.get(0).getPosition();
        int jPos = chord.get(1).getPosition();
        int kPos = chord.get(2).getPosition();
        int first = jPos - iPos;
        int second = kPos - jPos;
        if(first == 3 && second == 4) {
            return chord.get(0) + "M";
        } else if(first == 4 && second == 3) {
            return chord.get(0) + "m";
        } else if(first == 3 && second == 3) {
            return chord.get(0) + "*";
        } else if(first == 4 && second == 4) {
            return chord.get(0) + "+";
        } else {
            return "unknown";
        }
    }

    public String toString() {
        return name;
    }
}
