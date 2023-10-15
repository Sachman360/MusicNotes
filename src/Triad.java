import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triad extends Chord {

    public Triad(Note i, Note j, Note k) {
        chord = new ArrayList<>(Arrays.asList(i, j, k));;
        List<Note> temp = new ArrayList<>(Arrays.asList(i, j, k));
        sortTemp(temp);
        base = temp.get(0);
        type = getType(temp);
        name = base + type;
        List<String> types = new ArrayList<>();
        switch(type) {
            case "M":
                id = base.getPosition();
                break;
            case "m":
                id = base.getPosition() + 12;
            case "*":
                id = base.getPosition() + 24;
                break;
            case "+":
                id = base.getPosition() + 36;
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
