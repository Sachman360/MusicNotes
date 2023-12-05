import java.util.*;

public class Note implements Comparable<Note> {

    /** Class variables **/

    private String name;
    private int octave;
    private int id;

    private final String[] NOTE_NAMES = {"C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B", "B#", "Db", " ", "D#", " ", "E#", "Gb", " ", "G#", " ", "A#", "Cb"};
    private final Map<Integer, Set<String>> noteNames = getNoteNames();

    private Map<Integer, Set<String>> getNoteNames() {
        Map<Integer, Set<String>> noteNames = new TreeMap<>();
        noteNames.put(0, new HashSet<>(Arrays.asList("C", "B#")));
        noteNames.put(1, new HashSet<>(Arrays.asList("C#", "Db")));
        noteNames.put(2, new HashSet<>(Arrays.asList("D")));
        noteNames.put(3, new HashSet<>(Arrays.asList("D#", "Eb")));
        noteNames.put(4, new HashSet<>(Arrays.asList("E", "Fb")));
        noteNames.put(5, new HashSet<>(Arrays.asList("F")));
        noteNames.put(6, new HashSet<>(Arrays.asList("F#", "Gb")));
        noteNames.put(7, new HashSet<>(Arrays.asList("G")));
        noteNames.put(8, new HashSet<>(Arrays.asList("G#", "Ab")));
        noteNames.put(9, new HashSet<>(Arrays.asList("A")));
        noteNames.put(10, new HashSet<>(Arrays.asList("A#", "Bb")));
        noteNames.put(11, new HashSet<>(Arrays.asList("B", "Cb")));
        return noteNames;
    }



    /** Constructors **/

    public Note() {

    }

    public Note(String n, int o) {
        name = n;
        octave = o;
        id = getPosition() + (12 * octave);
    }

    public Note(String n) {
        name = n;
        octave = 4;
        id = getPosition() + (12 * octave);
    }

    public Note(int idNum) {
        name = getName(idNum % 12);
        octave = idNum / 12;
        id = idNum;
    }



    /** Methods **/

    // Basic get and print methods

    public String getName() {
        return name;
    }

    public int getOctave() {
        return octave;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    public void printDescription() {
        System.out.println(name + octave + ", ID: " + id);
    }

    // Methods for setting name/position

    // Considering adding a parameter to this method

    public int getPosition() {
        if(name.equals("")) {
            return -1;
        }
        for(int i = 0; i < NOTE_NAMES.length; i++) {
            if(name.equals(NOTE_NAMES[i])) {
                return i % 12;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getName(int position) {
        if(position < 0 || position >= 12) {
            throw new IllegalArgumentException();
        }
        return NOTE_NAMES[position];
    }

    // Consider deleting this method

    public void toggleEquivalentName() {
        for(int i = 0; i < NOTE_NAMES.length; i++) {
            if(name.equals(NOTE_NAMES[i])) {
                if(i >= 12) {
                    if(!NOTE_NAMES[i - 12].equals(" ")) {
                        name = NOTE_NAMES[i - 12];
                    }
                    return;
                } else {
                    if(!NOTE_NAMES[i + 12].equals(" ")) {
                        name = NOTE_NAMES[i + 12];
                    }
                    return;
                }
            }
        }
    }

    // Note editing methods

    public Note sharp() {
        if(id % 12 == 11) {
            octave++;
        }
        id++;
        if(name.length() == 1) {
            name += "#";
        } else if(name.length() == 2) {
            switch(name.substring(1)) {
                case "b":
                    name = name.substring(0, 1);
                break;
                case "#":
                    name += "#";
                break;
            }
        }
        return this;
    }

    public Note flat() {
        if(id % 12 == 0) {
            octave--;
        }
        id--;
        if(name.length() == 1) {
            name += "b";
        } else if(name.length() == 2) {
            switch(name.substring(1)) {
                case "#":
                    name = name.substring(0, 1);
                break;
                case "b":
                    name += "b";
                break;
            }
        }
        return this;
    }

    // Equals and Compare methods

    public boolean equals(Object o) {
        if(o instanceof Note) {
            if(o == this) {
                return true;
            } else if(((Note) o).id == id) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Note o) {
        return Integer.compare(id, o.id);
    }
}
