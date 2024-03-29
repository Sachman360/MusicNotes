import java.util.*;

public class Triad extends Chord {

    public Triad(Set<Note> notes) {
        if(notes.size() != 3) {
            throw new IllegalArgumentException();
        }
        chord = notes;
        base = getBase();
        List<Note> temp = new ArrayList<>(chord);
        sortTemp(temp);
        root = temp.get(0);
        type = getType(temp);
        name = root + type;
        switch(type) {
            case "M":
                id = root.getPosition();
            break;
            case "m":
                id = root.getPosition() + 12;
            break;
            case "*":
                id = root.getPosition() + 24;
            break;
            case "+":
                id = root.getPosition() + 36;
                break;
            default:
                id = -1;
        }

        List<String> keys = new ArrayList<>(Arrays.asList("C", "D", "E", "F", "G", "A", "B"));
        int pos = -1;
        boolean first = true;
        for(Note n : temp) {
            String letter = n.toString().substring(0, 1);
            if(first) {
                first = false;
                pos = keys.indexOf(letter);
            } else {
                if(!(keys.indexOf(letter) == pos + 2 || keys.indexOf(letter) + 7 == pos + 2)) {
                    n.toggleEquivalentName();
                }
                pos = keys.indexOf(n.toString().substring(0, 1));
            }
        }
    }

    public void sortTemp(List<Note> temp) {
        sort(temp);
        int ij = getInterval(temp.get(0), temp.get(1));
        int jk = getInterval(temp.get(1), temp.get(2));
        if(ij == 5 || ij == 6) {
            temp.add(temp.remove(0));
        } else if(jk == 5 || jk == 6) {
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
        }
    }

    public String getType(List<Note> temp) {
        int first = getInterval(temp.get(0), temp.get(1));
        int second = getInterval(temp.get(1), temp.get(2));
        if(first == 4 && second == 3) {
            return "M";
        } else if(first == 3 && second == 4) {
            return "m";
        } else if(first == 3 && second == 3) {
            return "*";
        } else if(first == 4 && second == 4) {
            return "+";
        } else {
            return "unknown";
        }
    }

    public void sort(List<Note> l) {
        for(int i = 0; i < l.size(); i++) {
            for(int j = 0; j < l.size() - i - 1; j++) {
                if(l.get(j).getPosition() > l.get(j + 1).getPosition()) {
                    l.add(j + 1, l.remove(j));
                }
            }
        }
    }

    public int getInterval(Note i, Note j) {
        int interval = j.getId() - i.getId();
        while(interval < 0) {
            interval += 12;
        }
        while(interval >= 12) {
            interval -= 12;
        }
        return interval;
    }

}
