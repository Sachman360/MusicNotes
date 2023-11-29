import java.util.*;

public class Seventh extends Chord{

    public Seventh(Set<Note> notes) {
        if(notes.size() != 4) {
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
            case "M7":
                id = root.getPosition() + 48;
            break;
            case "m7":
                id = root.getPosition() + 60;
            break;
            case "7":
                id = root.getPosition() + 72;
            break;
            case "/*":
                id = root.getPosition() + 84;
            break;
            case "*7":
                id = root.getPosition() + 96;
            break;
            default:
                id = -1;
        }
    }

    public void sortTemp(List<Note> temp) {
        sort(temp);
        int ij = getInterval(temp.get(0), temp.get(1));
        int jk = getInterval(temp.get(1), temp.get(2));
        int ki = getInterval(temp.get(2), temp.get(0));
        if(ij == 1 || ij == 2) {
            temp.add(temp.remove(0));
        } else if(jk == 1 || jk == 2) {
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
        } else if(ki == 1 || ki == 2) {
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
        }
    }

    public String getType(List<Note> temp) {
        int first = getInterval(temp.get(0), temp.get(1));
        int second = getInterval(temp.get(1), temp.get(2));
        int third = getInterval(temp.get(2), temp.get(3));
        if(first == 4 && second == 3 && third == 4) {
            return "M7";
        } else if(first == 3 && second == 4 && third == 3) {
            return "m7";
        } else if(first == 4 && second == 3 && third == 3) {
            return "7";
        } else if(first == 3 && second == 3 && third == 4) {
            return "/*";
        } else if(first == 3 && second == 3 && third == 3) {
            return "*7";
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
