import java.util.*;

public class MusicMaker {
    public MusicMaker() {

    }

    public Song generateSong() {
        Key k = getRandomKey();
        Song song = new Song(k);
        List<Chord> chords = getRandomProgression(k);
        List<Note> scale = k.scale;
        if(k.type.equals("m")) {
            Note leadingTone = new Note(scale.get(6).toString());
            leadingTone.sharp();
            scale.add(leadingTone);
        }
        for(int i = 0; i < chords.size(); i++) {
            song.addChord(chords.get(i));
            song.addNote(getRandomNote(scale, chords.get(i)));
        }
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
}
