import java.util.*;

public class Key {
    Note note;
    String type;
    List<Note> scale;

    public Key(Note n, String t) {
        note = n;
        type = t;
        scale = new ArrayList<>();
        populateScale();
    }

    public void populateScale() {
        List<Integer> intervals = new ArrayList<>(Arrays.asList(2, 2, 1, 2, 2, 2, 1));
        scale.add(new Note("C"));
        for(int i = 0; i < intervals.size() - 1; i++) {
            scale.add(new Note(scale.get(i).getId() + intervals.get(i)));
        }
        List<String> names = new ArrayList<>(Arrays.asList("Cbb", "Gbb", "Dbb", "Abb", "Ebb", "Bbb", "Fb", "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#", "G#", "D#", "A#", "E#", "B#", "F##", "C##"));
        int times = 0;
        if(names.contains(note.toString())) {
            times = names.indexOf(note.toString()) - 14;
        }
        if(times > 0) {
            for(int i = 0; i < times; i++) {
                scale.get(3).sharp();
                for(int j = 0; j < 4; j++) {
                    scale.add(scale.remove(0));
                }
            }
        } else if(times < 0) {
            for(int i = 0; i < Math.abs(times); i++) {
                scale.get(6).flat();
                for(int j = 0; j < 3; j++) {
                    scale.add(scale.remove(0));
                }
            }
        }
        switch(type) {
            case "M":
            break;
            case "m":
                scale.get(2).flat();
                scale.get(5).flat();
                scale.get(6).flat();
            break;
            default:

        }
    }

    public String toString() {
        switch(type) {
            case "M":
                return note.toString() + " Major";
            case "m":
                return note.toString() + " Minor";
            default:
                return note.toString();
        }
    }

    // WORK IN PROGRESS :/

    public void printKeySignature() {
        List<String> sheet = new ArrayList<>();

        for(int i = 0; i < 11; i++) {
            if(i % 2 == 0) {
                sheet.add("                    ");
            } else {
                sheet.add("--------------------");
            }
        }

        List<String> names = new ArrayList<>(Arrays.asList("Cbb", "Gbb", "Dbb", "Abb", "Ebb", "Bbb", "Fb", "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#", "G#", "D#", "A#", "E#", "B#", "F##", "C##"));
        int times = 0;
        if(names.contains(note.toString())) {
            times = names.indexOf(note.toString()) - 14;
        }

        if(type == "M" && times > 0) {
            switch(times) {
                case 7:
                    sheet.set(5, "-------------#------");
                case 6:
                    sheet.set(2, "            #       ");
                case 5:
                    sheet.set(6, "           #        ");
                case 4:
                    sheet.set(3, "----------#---------");
                case 3:
                    sheet.set(0, "         #          ");
                case 2:
                    sheet.set(4, "        #           ");
                case 1:
                    sheet.set(1, "-------#------------");
            }
        }
        for(int i = 0; i < sheet.size(); i++) {
            System.out.println(sheet.get(i));
        }
    }
}