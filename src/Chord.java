import java.util.*;

public class Chord {

    /** Class Variables **/

    protected List<Note> chord;
    protected String name;
    protected Note root;
    protected Note base;
    protected String type;
    protected int id;



    /** Constructors **/

    public Chord() {

    }

    public Chord(List<Note> notes) {
        chord = notes;
        sort(chord);
        base = getBase();
        Chord c = new Chord();
        if(chord.size() == 3) {
            c = new Triad(chord.get(0), chord.get(1), chord.get(2));

        } else if(chord.size() == 4) {
            c = new Seventh(chord.get(0), chord.get(1), chord.get(2), chord.get(3));
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
        type = name.substring(index);
        Key k = new Key(root, "M");
        List<Note> scale = k.scale;

        Chord c = new Chord();
        Note b = scale.get(0);
        Note m = scale.get(2);
        Note t = scale.get(4);
        Note s = scale.get(6);
        switch(type) {
            case "M":
                c = new Triad(b, m, t);
            break;
            case "m":
                c = new Triad(b, m.flat(), t);
            break;
            case "*":
                c = new Triad(b, m.flat(), t.flat());
            break;
            case "+":
                c = new Triad(b, m, t.sharp());
            break;
            case "M7":
                c = new Seventh(b, m, t, s);
            break;
            case "m7":
                c = new Seventh(b, m.flat(), t, s.flat());
            break;
            case "7":
                c = new Seventh(b, m, t, s.flat());
            break;
            case "/*":
                c = new Seventh(b, m.flat(), t.flat(), s.flat());
            break;
            case "*7":
                c = new Seventh(b, m.flat(), t.flat(), s.flat().flat());
            break;
        }
        chord = c.chord;
        id = c.id;
    }

    public Chord(String romanNumeral, Key k) {
        List<Note> key = k.scale;
        int index = 0;
        String end = romanNumeral.substring(index, index + 1);
        boolean finalIndex = false;
        while(!finalIndex) {
            finalIndex = true;
            if(end.equalsIgnoreCase("I") || end.equalsIgnoreCase("V")) {
                index++;
                if(index != romanNumeral.length()) {
                    finalIndex = false;
                }
            }
            end = romanNumeral.substring(index, index + 1);
        }
        String numeral = romanNumeral.substring(0, index);
        String romanType = romanNumeral.substring(index);
        System.out.println(romanNumeral + " " + index + " " + numeral + " " + romanType);
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
        id = c.id;
    }



    /** Methods **/

    // Basic get and print methods

    public String toString() {
        return name;
    }

    public void printDescription() {
        if(base == null) {
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

    public List<Note> getChord() {
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

    public Note getBase() {
        int index = 0;
        int minId = 200;
        for(int i = 0; i < chord.size(); i++) {
            if(chord.get(i).getId() < minId) {
                minId = chord.get(i).getId();
                index = i;
            }
        }
        return chord.get(index);
    }

    public void sort(List<Note> notes) {
        for(int i = 0; i < notes.size(); i++) {
            for(int j = 0; j < notes.size() - i - 1; j++) {
                if(notes.get(j).getId() > notes.get(j + 1).getId()) {
                    notes.add(j + 1, notes.remove(j));
                }
            }
        }
    }
}
