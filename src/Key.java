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
        List<String> keys = new ArrayList<>(Arrays.asList("C", "D", "E", "F", "G", "A", "B"));
        List<Integer> intervals = new ArrayList<>(Arrays.asList(2, 2, 1, 2, 2, 2, 1));
        scale.add(note);
        List<Note> otherScale = new ArrayList<>();
        otherScale.add(note);
        for(int i = 0; i < intervals.size() - 1; i++) {
            scale.add(new Note(scale.get(i).getId() + intervals.get(i)));
            otherScale.add(new Note(scale.get(i).getId() + intervals.get(i)));
            int index1 = keys.indexOf(scale.get(i).toString().substring(0, 1));
            int index2 = keys.indexOf(scale.get(i + 1).toString().substring(0, 1));
            if(index2 != index1 + 1 && index2 != 0) {
                System.out.println(scale.get(i) + " " + index1 + "  " + scale.get(i + 1) + " " + index2);
                scale.get(i + 1).toggleEquivalentName();
            } else if(index2 == 0 && index1 != 6) {
                System.out.println(scale.get(i) + " " + index1 + "  " + scale.get(i + 1) + " " + index2);
                scale.get(i + 1).toggleEquivalentName();
            }
        }

        System.out.println();

        for(Note kn : otherScale) {
            kn.printDescription();
        }
        System.out.println();
        for(Note kn : scale) {
            kn.printDescription();
        }
        System.out.println();
        System.out.println();






//        List<String> names = new ArrayList<>(Arrays.asList("Cbb", "Gbb", "Dbb", "Abb", "Ebb", "Bbb", "Fb", "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#", "G#", "D#", "A#", "E#", "B#", "F##", "C##"));
//        int times = 0;
//        if(names.contains(note.toString())) {
//            times = names.indexOf(note.toString()) - 14;
//        }
//        if(times > 0) {
//            for(int i = 0; i < times; i++) {
//                scale.get(3).sharp();
//                for(int j = 0; j < 4; j++) {
//                    scale.add(scale.remove(0));
//                }
//            }
//        } else if(times < 0) {
//            for(int i = 0; i < Math.abs(times); i++) {
//                scale.get(6).flat();
//                for(int j = 0; j < 3; j++) {
//                    scale.add(scale.remove(0));
//                }
//            }
//        }
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