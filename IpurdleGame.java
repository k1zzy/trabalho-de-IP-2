public class IpurdleGame {
    String[] dicionario = {"JAVA", "LOOP", "EXIT", "TRUE","LONG", "THIS",
    "BREAK","WHILE","GRADE","PUPIL", "FIELD", "BASIC", "ABORT",
    "ABOVE","FALSE","FLOAT","SHORT","CLASS","FINAL",
    "STATIC","METHOD","STRING","RETURN","RANDOM","EQUALS","OBJECT","FUNCTION",
    "VARIABLE","INTEGER","SCANNER"};

    boolean[] dicionarioUsado = new boolean[dicionario.length];

    private Board board;
    
    public IpurdleGame(int wordSize, int maxGuesses) {
        this.board = new Board(wordSize, maxGuesses);
    }

    public int wordLength() {
        return board.wordLength();
    }

    public int maxGuesses() {
        return board.maxGuesses();
    }

    public int guesses() {
        return board.guesses();
    }

    public boolean isValid(String guess) {
        guess = guess.toUpperCase();

        if (guess.length() != board.wordLength()) {
            return false;
        }
        
        int contador = 0;

        for(int i = 0; i < dicionario.length; i++) {
            if(guess.equals(dicionario[i])) {
                contador++;
            }
        }

        return contador != 0;
            
    }

    public boolean isOver() {
        if(board.guesses() == board.maxGuesses()) {
            return true;
        }
        
        return board.clue().isMax();
    }

    private Clue clueForGuessAndWord(String guess, String word) {
        LetterStatus[] elements = new LetterStatus[word.length()];

        for(int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                elements[i] = LetterStatus.CORRECT_POS;
            } else if (word.indexOf(guess.charAt(i)) != -1 ) {
                elements[i] = LetterStatus.WRONG_POS;
            } else {
                elements[i] = LetterStatus.INEXISTENT;
            }
        }
        
        return new Clue(elements);
    }
}
