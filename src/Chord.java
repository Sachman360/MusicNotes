import java.util.*;

public class Chord {
    protected List<Note> chord;
    protected String name;
    protected Note base;
    protected String type;
    protected int id;

    public Chord() {

    }

    public Chord(List<Note> notes) {
        chord = notes;
        if(chord.size() == 3) {
            Triad t = new Triad(chord.get(0), chord.get(1), chord.get(2));
            name = t.name;
            base = t.base;
            type = t.type;
            id = t.id;
        }
    }

    public String toString() {
        return name;
    }

    public String printDescription() {
        return name + ": " + chord + "   ID: " + id;
    }
}
