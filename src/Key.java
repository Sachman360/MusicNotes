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
        List<Integer> intervals;
        switch(type) {
            case "M":
                intervals = new ArrayList<>(Arrays.asList(2, 2, 1, 2, 2, 2, 1));
            break;
            case "m":
                intervals = new ArrayList<>(Arrays.asList(2, 1, 2, 2, 1, 2, 2));
            break;
            default:
                intervals = new ArrayList<>();
        }
        scale.add(note);
        for(int i = 0; i < intervals.size() - 1; i++) {
            scale.add(new Note(scale.get(i).getId() + intervals.get(i)));
        }
        System.out.println(scale);
    }
}
