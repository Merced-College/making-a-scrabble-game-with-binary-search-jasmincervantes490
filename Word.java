// Jasmin Cervantes
// Date: 10.17.2024
// I was not able to record a meeting because my group computer was acting weird and for some reason it did not display my groupmates response, I included the screenshots in Canvas
// I will turn in my response to the questions in a document that will be submitted to Canvas


public class Word implements Comparable<Word> { // this creates a comparable class named "Word" that contains word object types
    private String word;// declares word string

    // Constructor
    public Word(String word) {
        this.word = word;
    }

    // Accessor
    public String getWord() {
        return word;
    }
    
    // compareTo Method
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }

    // toString Method
    public String toString() {
        return word;
    }
}
