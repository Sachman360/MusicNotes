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
        //System.out.println(temp);
        root = temp.get(0);
        type = getType(temp);
        name = root + type;
        //System.out.println(name);
        switch(type) {
            case "M7":
                id = root.getPosition() + 48;
            break;
            case "m7":
                //System.out.println("we made it!");
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
        //System.out.println("sortTemp");
        sort(temp);
        //System.out.println(temp);
        int ij = getInterval(temp.get(0), temp.get(1));
        int jk = getInterval(temp.get(1), temp.get(2));
        int kl = getInterval(temp.get(2), temp.get(3));
        //System.out.println(ij + " " + jk + " " + kl);
        if(ij == 1 || ij == 2) {
            temp.add(temp.remove(0));
        } else if(jk == 1 || jk == 2) {
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
        } else if(kl == 1 || kl == 2) {
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
            temp.add(temp.remove(0));
        }
    }

    public String getType(List<Note> temp) {
        //System.out.println("getType");
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
