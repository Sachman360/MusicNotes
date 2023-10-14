import java.util.*;

public class Chord {
    protected List<Note> chord;
    protected String name;
    protected Note base;
    protected String type;
    protected int id;

    public Chord(List<Note> notes) {
        chord = notes;
    }

    public String toString() {
        return name;
    }

    public String printDescription() {
        return name + ": " + chord + "   ID: " + id;
    }
}
