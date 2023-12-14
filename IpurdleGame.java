import java.lang.String;

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

        if(guess.length() != board.wordLength()) {
            return false;
        }
        
        int contador = 0;
        for(int i = 0; i < dicionario.length; i++) { //PARA QUE UM CONTADOR NAO SEI NAO ME LEMBRO
            if(guess.equals(dicionario[i])) {
                contador++;
            }
        }
        return contador != 0;   
    }

    public boolean isOver() {
        if(guesses() == maxGuesses()) {
            return true;
        }
        return false;  //ESTA MAL, FALTA VER SE JA ACHOU A PALAVRA
    }

    private Clue clueForGuessAndWord(String guess, String word) {
        LetterStatus[] elements = new LetterStatus[word.length()];

        for(int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                elements[i] = LetterStatus.CORRECT_POS; // TA MAL CUIDADO GUI
            } else if (guess.charAt(i) == word.charAt(i)) {
                elements[i] = LetterStatus.WRONG_POS;
            } else {
                elements[i] = LetterStatus.INEXISTENT;
            }
        }
        return new Clue(elements);
    }

    public Clue playGuess(String guess) {
        Clue clue = new Clue(1, wordLength());
        int lowerOrderNumber = 0;

        for(int i = 0; i < dicionario.length; i++) {
            if(!dicionarioUsado[i]) {
                clue = clueForGuessAndWord(guess, dicionario[i]);
                if(clue.orderNumber() < lowerOrderNumber) {
                    lowerOrderNumber = clue.orderNumber();
                }
            }
        }

        clue = new Clue(lowerOrderNumber, wordLength());
        board.insertGuessAndClue(guess, clue);

        return clue;
    }

    public String toString() {
        StringBuilder board = new StringBuilder();
        board.append("Ipurdle with words of " + wordLength() + "letters.\n");
        board.append("Remaining guesses: " + (maxGuesses() - guesses()) + "\n");
        board.append(board.toString());

        return board.toString();
    }

    private boolean existeMasErrado() {
        return false;
    }
}
