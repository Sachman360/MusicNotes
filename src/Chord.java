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
        Key k = new Key(base, "M");
        Triad t;
        Seventh s;
        switch(type) {
            case "M":
                t = new Triad(k.scale.get(0), k.scale.get(2), k.scale.get(4));
                chord = t.chord;
                id = t.id;
            break;
            case "m":
                k.scale.get(2).flat();
                t = new Triad(k.scale.get(0), k.scale.get(2), k.scale.get(4));
                chord = t.chord;
                id = t.id;
            break;
            case "*":
                k.scale.get(2).flat();
                k.scale.get(4).flat();
                t = new Triad(k.scale.get(0), k.scale.get(2), k.scale.get(4));
                chord = t.chord;
                id = t.id;
            break;
            case "+":
                k.scale.get(4).sharp();
                t = new Triad(k.scale.get(0), k.scale.get(2), k.scale.get(4));
                chord = t.chord;
                id = t.id;
            break;
            case "M7":
                s = new Seventh(k.scale.get(0), k.scale.get(2), k.scale.get(4), k.scale.get(6));
                chord = s.chord;
                id = s.id;
            break;
            case "m7":
                k.scale.get(2).flat();
                k.scale.get(6).flat();
                s = new Seventh(k.scale.get(0), k.scale.get(2), k.scale.get(4), k.scale.get(6));
                chord = s.chord;
                id = s.id;
            break;
            case "7":
                k.scale.get(6).flat();
                s = new Seventh(k.scale.get(0), k.scale.get(2), k.scale.get(4), k.scale.get(6));
                chord = s.chord;
                id = s.id;
            break;
            case "/*":
                k.scale.get(2).flat();
                k.scale.get(4).flat();
                k.scale.get(6).flat();
                s = new Seventh(k.scale.get(0), k.scale.get(2), k.scale.get(4), k.scale.get(6));
                chord = s.chord;
                id = s.id;
            break;
            case "*7":
                k.scale.get(2).flat();
                k.scale.get(4).flat();
                k.scale.get(6).flat();
                k.scale.get(6).flat();
                s = new Seventh(k.scale.get(0), k.scale.get(2), k.scale.get(4), k.scale.get(6));
                chord = s.chord;
                id = s.id;
            break;
        }
    }

    public String toString() {
        return name;
    }

    public void printDescription() {
        System.out.println(name + ": " + chord + "   ID: " + id);
    }

    public String getRomanNumeral(Key k) {
        List<Note> key = k.scale;
        String check = base.toString();
        String numeral = "";
        if(check.equals(key.get(0).toString())) {
            numeral = "I";
        } else if(check.equals(key.get(1).toString())) {
            numeral = "II";
        } else if(check.equals(key.get(2).toString())) {
            numeral = "III";
        } else if(check.equals(key.get(3).toString())) {
            numeral = "IV";
        } else if(check.equals(key.get(4).toString())) {
            numeral = "V";
        } else if(check.equals(key.get(5).toString())) {
            numeral = "VI";
        } else if(check.equals(key.get(6).toString())) {
            numeral = "VII";
        } else {
            numeral = "?";
        }
        switch(type) {
            case "M":
                return numeral;
            case "m":
                return numeral.toLowerCase();
            case "*", "/*", "*7":
                return numeral.toLowerCase() + type;
            case "+", "M7":
                return numeral + type;
            case "m7":
                return numeral.toLowerCase() + "7";
            case "7":
                return numeral + "7";
            default:
                return "?";
        }
    }
}
