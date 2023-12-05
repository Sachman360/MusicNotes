import com.sun.source.tree.Tree;

import java.util.*;

public class Chord {

    /** Class Variables **/

    protected Set<Note> chord;
    protected String name;
    protected Note root;
    protected Note base;
    protected String type;
    protected int id;



    /** Constructors **/

    public Chord() {

    }

    public Chord(Collection<Note> notes) {
        chord = new TreeSet<Note>(notes);
        base = getBase();
        Chord c = new Chord();
        if(chord.size() == 3) {
            c = new Triad(chord);

        } else if(chord.size() == 4) {
            c = new Seventh(chord);
        }
        name = c.name;
        root = c.root;
        type = c.type;
        id = c.id;
    }

    public Chord(String n) {
        name = n;
        if(name.isEmpty()) {
            return;
        }
        int index = 1;
        char end = name.charAt(index);
        while(end == '#' || end == 'b') {
            index++;
            end = name.charAt(index);
        }
        root = new Note(name.substring(0, index));
        base = root;
        type = name.substring(index);
        Key k = new Key(root, "M");
        List<Note> scale = k.scale;

        Chord c = new Chord();
        Note b = scale.get(0);
        Note m = scale.get(2);
        Note t = scale.get(4);
        Note s = scale.get(6);
        Set<Note> notes = new TreeSet<>(Arrays.asList(b, m, t));
        switch(type) {
            case "M":
                c = new Triad(notes);
            break;
            case "m":
                m.flat();
                c = new Triad(notes);
            break;
            case "*":
                m.flat();
                t.flat();
                c = new Triad(notes);
            break;
            case "+":
                t.sharp();
                c = new Triad(notes);
            break;
            case "M7":
                notes.add(s);
                c = new Seventh(notes);
            break;
            case "m7":
                notes.add(s);
                m.flat();
                s.flat();
                c = new Seventh(notes);
            break;
            case "7":
                notes.add(s);
                s.flat();
                c = new Seventh(notes);
            break;
            case "/*":
                notes.add(s);
                m.flat();
                t.flat();
                s.flat();
                c = new Seventh(notes);
            break;
            case "*7":
                notes.add(s);
                m.flat();
                t.flat();
                s.flat().flat();
                c = new Seventh(notes);
            break;
        }
        chord = c.chord;
        id = c.id;
    }

    public Chord(String romanNumeral, Key k) {
        List<Note> key = k.scale;
        int index = 0;
        String end;
        boolean finalIndex = false;
        while(!finalIndex) {
            finalIndex = true;
            end = romanNumeral.substring(index, index + 1);
            if(end.equalsIgnoreCase("I") || end.equalsIgnoreCase("V")) {
                index++;
                if(index != romanNumeral.length()) {
                    finalIndex = false;
                }
            }
        }
        String numeral = romanNumeral.substring(0, index);
        String romanType = romanNumeral.substring(index);
        //System.out.println(romanNumeral + " " + index + " " + numeral + " " + romanType + " ");
        switch(numeral.toUpperCase()) {
            case "I":
                root = key.get(0);
            break;
            case "II":
                root = key.get(1);
            break;
            case "III":
                root = key.get(2);
            break;
            case "IV":
                root = key.get(3);
            break;
            case "V":
                root = key.get(4);
            break;
            case "VI":
                root = key.get(5);
            break;
            case "VII":
                root = key.get(6);
            break;
            default:
                root = key.get(0);
        }
        switch(romanType) {
            case "":
                if(romanNumeral.toUpperCase().equals(romanNumeral)) {
                    type = "M";
                } else {
                    type = "m";
                }
            break;
            case "7":
                if(romanNumeral.toUpperCase().equals(romanNumeral)) {
                    type = "7";
                } else {
                    type = "m7";
                }
            break;
            default:
                type = romanType;
        }
        name = root.toString() + type;
        Chord c = new Chord(name);
        chord = c.chord;
        base = root;
        id = c.id;
    }



    /** Methods **/

    // Basic get and print methods

    public String toString() {
        return name;
    }

    public void printDescription() {
        if(root == null) {
            System.out.println();
            return;
        }
        String over = "";
        if(base.getPosition() != root.getPosition()) {
            over = "/" + base.toString();
        }
        System.out.println(name + over +  ": " + chord + "   ID: " + id);
    }

    public int getId() {
        return id;
    }

    public Set<Note> getChord() {
        return chord;
    }

    // Roman numeral and base get methods

    public String getRomanNumeral(Key k) {
        List<Note> key = k.scale;
        String check = root.toString();
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

    // no

    public Note getBase() {
        for(Note n : chord) {
            return n;
        }
        return new Note();
    }
}
