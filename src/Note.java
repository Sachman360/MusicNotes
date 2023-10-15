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

    public String toString() {
        return name;
    }

    public String printDescription() {
        return name + octave + ", ID: " + id;
    }

    public int getId() {
        return id;
    }
}
