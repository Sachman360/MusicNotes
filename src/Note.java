import java.util.*;

public class Note {

    /** Class variables **/

    private String name;
    private int octave;
    private int id;
    private final String[] noteNames = {"C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B", "B#", "Db", " ", "D#", " ", "E#", "Gb", " ", "G#", " ", "A#", "Cb"};



    /** Constructors **/

    public Note(String n, int o) {
        name = n;
        octave = o;
        int pos = getPosition();
        if(pos != -1) {
            id = pos + (12 * octave);
        } else {
            id = pos;
        }
    }

    public Note(String n) {
        name = n;
        octave = 4;
        int pos = getPosition();
        if(pos != -1) {
            id = pos + (12 * octave);
        } else {
            id = pos;
        }
    }

    public Note(int idNum) {
        id = idNum;
        octave = id / 12;
        int position = id % 12;
        name = getName(position);
    }



    /** Methods **/

    // Basic get and print methods

    public String toString() {
        return name;
    }

    public void printDescription() {
        System.out.println(name + octave + ", ID: " + id);
    }

    public int getId() {
        return id;
    }

    public int getOctave() {
        return octave;
    }

    // Methods for setting name/position

    public int getPosition() {
        for(int i = 0; i < noteNames.length; i++) {
            if(name.equals(noteNames[i])) {
                return i % 12;
            }
        }
        return -1;
    }

    public String getName(int position) {
        if(position < 12) {
            return noteNames[position];
        }
        return "";
    }

    public void toggleEquivalentName() {
        for(int i = 0; i < noteNames.length; i++) {
            if(name.equals(noteNames[i])) {
                if(i >= 12) {
                    if(!noteNames[i - 12].equals(" ")) {
                        name = noteNames[i - 12];
                    }
                    return;
                } else {
                    if(!noteNames[i + 12].equals(" ")) {
                        name = noteNames[i + 12];
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
}
