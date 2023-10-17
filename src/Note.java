import java.util.*;

public class Note {
    private String name;
    private int octave;
    private int id;

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
        int position = id;
        while(position >= 12) {
            position -= 12;
            octave++;
        }
        name = getName(position);
    }

    public void sharp() {
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
                    /*
                    toggleEquivalentName();
                    if(id % 12 == 0) {
                        octave--;
                    }
                    id--;
                    sharp();
                    */
                break;
            }
        }
    }

    public void flat() {
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
                    /*
                    toggleEquivalentName();
                    if(id % 12 == 11) {
                        octave++;
                    }
                    id++;
                    flat();
                    */
                break;
            }
        }
    }

    public int getPosition() {
        switch(name) {
            case "C", "B#":
                return 0;
            case "C#", "Db":
                return 1;
            case "D":
                return 2;
            case "D#", "Eb":
                return 3;
            case "E", "Fb":
                return 4;
            case "F", "E#":
                return 5;
            case "F#", "Gb":
                return 6;
            case "G":
                return 7;
            case "G#", "Ab":
                return 8;
            case "A":
                return 9;
            case "A#", "Bb":
                return 10;
            case "B", "Cb":
                return 11;
            default:
                return -1;
        }
    }

    public String getName(int position) {
        switch(position) {
            case 0:
                return "C";
            case 1:
                return "C#";
            case 2:
                return "D";
            case 3:
                return "Eb";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "F#";
            case 7:
                return "G";
            case 8:
                return "Ab";
            case 9:
                return "A";
            case 10:
                return "Bb";
            case 11:
                return "B";
            default:
                return "";
        }
    }

    public void toggleEquivalentName() {
        switch(name) {
            case "C":
                name = "B#";
            break;
            case "B#":
                name = "C";
            break;
            case "C#":
                name = "Db";
            break;
            case "Db":
                name = "C#";
            break;
            case "D":
            break;
            case "D#":
                name = "Eb";
            break;
            case "Eb":
                name = "D#";
            break;
            case "E":
                name = "Fb";
            break;
            case "Fb":
                name = "E";
            break;
            case "F":
                name = "E#";
            break;
            case "E#":
                name = "F";
            break;
            case "F#":
                name = "Gb";
            break;
            case "Gb":
                name = "F#";
            break;
            case "G":
            break;
            case "G#":
                name = "Ab";
            break;
            case "Ab":
                name = "G#";
            break;
            case "A":
            break;
            case "A#":
                name = "Bb";
            break;
            case "Bb":
                name = "A#";
            break;
            case "B":
                name = "Cb";
            break;
            case "Cb":
                name = "B";
            break;
            default:

        }
    }

    public String toString() {
        return name;
    }

    public void printDescription() {
        System.out.println(name + octave + ", ID: " + id);
    }

    public int getId() {
        return id;
    }
}
