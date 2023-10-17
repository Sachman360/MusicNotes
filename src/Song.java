import java.util.*;

public class Song {
    List<Note> melody;
    List<Integer> melodyDurations;
    List<Chord> chords;
    List<Integer> chordDurations;
    Key key;

    public Song(Key k) {
        melody = new ArrayList<>();
        melodyDurations = new ArrayList<>();
        chords = new ArrayList<>();
        chordDurations = new ArrayList<>();
        key = k;
    }

    public void addNote(Note n) {
        melody.add(n);
        melodyDurations.add(1);
    }

    public void addNote(Note n, int d) {
        melody.add(n);
        melodyDurations.add(d);
    }

    public void addChord(Chord c) {
        chords.add(c);
        chordDurations.add(1);
    }

    public void addChord(Chord c, int d) {
        chords.add(c);
        chordDurations.add(d);
    }

    public String toString() {
        String song = "Melody:   ";
        for(int i = 0; i < melody.size(); i++) {
            if(melodyDurations.get(i) > 0) {
                song += melody.get(i);
                for(int j = 0; j < 6 - melody.get(i).toString().length(); j++) {
                    song += " ";
                }
                for(int k = 0; k < melodyDurations.get(i) - 1; k++) {
                    song += "      ";
                }
            }
        }
        song += "\nChords:   ";
        for(int i = 0; i < chords.size(); i++) {
            if(chordDurations.get(i) > 0) {
                song += chords.get(i);
                for(int j = 0; j < 6 - chords.get(i).toString().length(); j++) {
                    song += " ";
                }
                for(int k = 0; k < chordDurations.get(i) - 1; k++) {
                    song += "      ";
                }
            }
        }

        return song;
    }

    public String romanNumeralAnalysis() {
        String analysis = "";
        analysis += "\nAnalysis: ";
        for(int i = 0; i < chords.size(); i++) {
            if(chordDurations.get(i) > 0) {
                analysis += chords.get(i).getRomanNumeral(key);
                for(int j = 0; j < 6 - chords.get(i).getRomanNumeral(key).length(); j++) {
                    analysis += " ";
                }
                for(int k = 0; k < chordDurations.get(i) - 1; k++) {
                    analysis += "      ";
                }
            }
        }

        return analysis;
    }
}
