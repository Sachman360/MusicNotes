import java.util.*;

public class MusicMaker {
    public MusicMaker() {

    }

    public Song generateSong() {
        Random random = new Random();
        Key k = getRandomKey();
        Song song = new Song(k);
        List<Chord> chords = getRandomProgression(k);
        List<Note> scale = k.scale;
        if(k.type.equals("m")) {
            Note leadingTone = new Note(scale.get(6).toString());
            leadingTone.sharp();
            scale.add(leadingTone);
        }
        int[] timeSignature = getRandomTimeSignature();
        int measureLength;
        int beats;
        int length = random.nextInt(2) + 1;
        if(timeSignature[1] == 4) {
            measureLength = timeSignature[0] * 2;
            beats = timeSignature[0];
        } else {
            measureLength = timeSignature[0];
            beats = timeSignature[0] / 3;
        }
        boolean syncopation = getSyncopation();
        for(int l = 0; l < length; l++) {
            for(int i = 0; i < chords.size(); i++) {
                song.addChord(chords.get(i), measureLength);
                if(i == chords.size() - 1) {
                    song.addNote(getRandomNote(scale, chords.get(i)));
                } else {
                    for(int j = 0; j < measureLength; j++) {
                        if(j % (measureLength / beats) == 0) {
                            song.addNote(getRandomNote(scale, chords.get(i)));
                        } else if(random.nextInt(3) < 1) {
                            song.addNote(getRandomNote(scale, chords.get(i)));
                        } else {
                            song.addNote(new Note(""));
                        }

                    }
                }
            }
        }
        System.out.println(song);
        editMelody(song);
        return song;
    }

    public Key getRandomKey() {
        List<Key> basicKeys = new ArrayList<>();
        List<String> bases = new ArrayList<>(Arrays.asList("B", "C", "D", "E", "F", "G", "A", "Eb", "Ab", "Bb"));
        for(int i = 1; i < bases.size(); i++) {
            basicKeys.add(new Key(new Note(bases.get(i)), "M"));
        }
        for(int i = 0; i < bases.size() - 3; i++) {
            basicKeys.add(new Key(new Note(bases.get(i)), "m"));
        }
        Random random = new Random();
        return basicKeys.get(random.nextInt(basicKeys.size()));
    }

    public List<Chord> getRandomProgression(Key k) {
        List<Chord> chords = new ArrayList<>();
        List<String[]> progressions = new ArrayList<>();
        List<String[]> majorProgressions = new ArrayList<>();
        majorProgressions.add(new String[] {"I", "IV", "V", "I"});
        majorProgressions.add(new String[] {"I", "vi", "IV", "V"});
        majorProgressions.add(new String[] {"I", "III", "IV", "V"});
        majorProgressions.add(new String[] {"I", "V", "V7", "I", "IV", "I", "V", "I"});
        List<String[]> minorProgressions = new ArrayList<>();
        minorProgressions.add(new String[] {"i", "iv", "V", "i"});
        minorProgressions.add(new String[] {"i", "I", "iv", "V"});
        minorProgressions.add(new String[] {"i", "VII", "VI", "V"});
        minorProgressions.add(new String[] {"i", "iv", "VII", "III", "VI", "ii*", "V", "V7"});

        if(k.type.equals("M")) {
            progressions = majorProgressions;
        } else if(k.type.equals("m")) {
            progressions = minorProgressions;
        }

        Random random = new Random();
        String[] chordNumerals = progressions.get(random.nextInt(progressions.size()));
        for(int i = 0; i < chordNumerals.length; i++) {
            chords.add(new Chord(chordNumerals[i], k));
        }
        return chords;
    }

    public Note getRandomNote(List<Note> notes, Chord c) {
        Random random = new Random();
        Note n = new Note(random.nextInt(100));
        List<String> chord = new ArrayList<>();
        List<String> noteStrings = new ArrayList<>();
        for(int i = 0; i < c.chord.size(); i++) {
            chord.add(c.chord.get(i).toString());
        }
        for(int i = 0; i < notes.size(); i++) {
            noteStrings.add(notes.get(i).toString());
        }
        while(!noteStrings.contains(n.toString()) || !chord.contains(n.toString())) {
            n = new Note(random.nextInt(100));
        }
        return n;
    }

    public int[] getRandomTimeSignature() {
        Random random = new Random();
        int[] ts = new int[2];
        int[] possibilities = new int[] {4, 4, 3, 4, 2, 4, 12, 8, 9, 8, 6, 8};
        int index = random.nextInt(possibilities.length / 2);
        ts[0] = possibilities[index * 2];
        ts[1] = possibilities[(index * 2) + 1];
        return ts;
    }

    public boolean getSyncopation() {
        Random random = new Random();
        if(random.nextInt(100) < 25) {
            return true;
        }
        return false;
    }

    public void editMelody(Song s) {
        Random random = new Random();
        List<Note> scale = s.key.scale;
        List<String> noteStrings = new ArrayList<>();
        int measureLength = ((s.melody.size() - 1) / (s.chords.size() - 1));
        if(s.key.type.equals("m")) {
            Note leadingTone = new Note(scale.get(6).toString());
            leadingTone.sharp();
            scale.add(leadingTone);
        }
        for(int i = 0; i < scale.size(); i++) {
            noteStrings.add(scale.get(i).toString());
        }
        for(int i = 0; i < s.chords.size(); i++) {
            List<String> chord = new ArrayList<>();
            Chord c = s.chords.get(i);
            for(int k = 0; k < c.chord.size(); k++) {
                chord.add(c.chord.get(k).toString());
            }
            for(int j = 0; j < measureLength; j++) {
                if(random.nextInt(10) > 5) {
                    //System.out.println(i + " " + j + " " + measureLength);
                    int i1 = i * measureLength + j - 1;
                    int i2 = i * measureLength + j + 1;

                    if(i1 >= 0 && i2 >= 0 && i1 < s.melody.size() && i2 < s.melody.size()) {

                        Note n1 = s.melody.get(i1);
                        Note n2 = s.melody.get(i2);

                        //System.out.println(i1 + " " + i2 + " " + n1 + " " + n2);
                        int interval = shiftScale(noteStrings, s.melody.get(i1).toString(), s.melody.get(i2).toString());
                        if(interval == 2) {
                            System.out.println(s.melody.get(i1) + " " + s.melody.get(i2) + " " + interval);
                            Note n = new Note(noteStrings.get((noteStrings.indexOf(n1.toString()) + noteStrings.indexOf(n2.toString())) / 2));
                            s.melody.set(i1 + 1, n);
                        }
                    }
                }
            }
        }
    }

    public int shiftScale(List<String> scale, String n1, String n2) {
        int times = 0;
        if(scale.contains(n1) && scale.contains(n2)) {
            int min = 8;
            for(int i = 0; i < scale.size(); i++) {
                int temp = Math.abs(scale.indexOf(n1) - scale.indexOf(n2));
                if(temp < min) {
                    min = temp;
                    times = i;
                }
                scale.add(scale.remove(0));
            }
            for(int i = 0; i < times; i++) {
                scale.add(scale.remove(0));
            }
            return min;
        } else {
            return -1;
        }
    }
}
