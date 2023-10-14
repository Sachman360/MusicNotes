import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triad extends Chord {

    public Triad(Note i, Note j, Note k) {
        super(new ArrayList<>(Arrays.asList(i, j, k)));
        base = chord.get(0);
        type = getType();
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

    public String getType() {
        int iPos = chord.get(0).getPosition();
        int jPos = chord.get(1).getPosition();
        int kPos = chord.get(2).getPosition();
        int ij = Math.abs(jPos - iPos);
        int jk = Math.abs(kPos - jPos);
        int ik = Math.abs(kPos - iPos);
        int first;
        int second;
        if(ik == ij + jk) {
            if(iPos < kPos) {
                first = ij;
                second = jk;
            } else {
                first = jk;
                second = ij;
            }
        } else if(ij == ik + jk) {
            if(iPos < jPos) {
                first = ik;
                second = jk;
            } else {
                first = jk;
                second = ik;
            }
        } else {
            if(jPos < kPos) {
                first = ij;
                second = ik;
            } else {
                first = ik;
                second = ij;
            }
        }
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


}
