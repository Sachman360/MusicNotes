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
        } else if(chord.size() == 4) {
            Seventh s = new Seventh(chord.get(0), chord.get(1), chord.get(2), chord.get(3));
            name = s.name;
            base = s.base;
            type = s.type;
            id = s.id;
        }
    }

    public Chord(String n) {
        name = n;
        int index = 1;
        if(name.substring(1, 2).equals("#") || name.substring(1, 2).equals("b")) {
            index = 2;
        }
        base = new Note(name.substring(0, index));
        type = name.substring(index);
        Triad t;
        Seventh s;
        switch(type) {
            case "M":
                t = new Triad(base, new Note(base.getId() + 4), new Note(base.getId() + 7));
                chord = t.chord;
                id = t.id;
            break;
            case "m":
                t = new Triad(base, new Note(base.getId() + 3), new Note(base.getId() + 7));
                chord = t.chord;
                id = t.id;
            break;
            case "*":
                t = new Triad(base, new Note(base.getId() + 3), new Note(base.getId() + 6));
                chord = t.chord;
                id = t.id;
            break;
            case "+":
                t = new Triad(base, new Note(base.getId() + 4), new Note(base.getId() + 8));
                chord = t.chord;
                id = t.id;
            break;
            case "M7":
                s = new Seventh(base, new Note(base.getId() + 4), new Note(base.getId() + 7), new Note(base.getId() + 11));
                chord = s.chord;
                id = s.id;
            break;
            case "m7":
                s = new Seventh(base, new Note(base.getId() + 3), new Note(base.getId() + 7), new Note(base.getId() + 10));
                chord = s.chord;
                id = s.id;
            break;
            case "7":
                s = new Seventh(base, new Note(base.getId() + 4), new Note(base.getId() + 7), new Note(base.getId() + 10));
                chord = s.chord;
                id = s.id;
            break;
            case "/*":
                s = new Seventh(base, new Note(base.getId() + 3), new Note(base.getId() + 6), new Note(base.getId() + 10));
                chord = s.chord;
                id = s.id;
            break;
            case "*7":
                s = new Seventh(base, new Note(base.getId() + 3), new Note(base.getId() + 6), new Note(base.getId() + 9));
                chord = s.chord;
                id = s.id;
            break;
        }
    }

    public String toString() {
        return name;
    }

    public String printDescription() {
        return name + ": " + chord + "   ID: " + id;
    }
}
