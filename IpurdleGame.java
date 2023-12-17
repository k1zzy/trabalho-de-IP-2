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
        return true;
    }

    private Clue clueForGuessAndWord(String guess, String word) {
        LetterStatus[] elements = new LetterStatus[word.length()];
        boolean existe = false;
        boolean repetido = false;
        for(int i = 0; i < guess.length(); i++){
            if(word.charAt(i)== guess.charAt(i)){
                elements[i] = LetterStatus.CORRECT_POS;
            }
            
            else {
                for( int j = 0; j < word.length(); j++) {
                    existe = false;
                    repetido = false;
                    if( guess.charAt(i) == word.charAt(j)){
                        existe = true;
                        break;
                    }

                }

                if(!existe) {
                    elements[i] = LetterStatus.INEXISTENT;
                }

                else {
                    for( int k = i; k >=1; k--) {
                        if(guess.charAt(i) == guess.charAt( k-1) && i != 0){
                            repetido = true;
                            elements[i] = LetterStatus.INEXISTENT;
                            break;
                        }
                    }
                    if(!repetido) {
                        elements[i] = LetterStatus.WRONG_POS;
                    }

                }
            }
        } 
        return new Clue(elements);
    }

    private int HowManyWordsWithClue(Clue clue, String guess) {
        int contador = 0;
        String palavraTeste = "";

        for (int i = 0; i < dicionario.length; i++) {
            palavraTeste = dicionario[i];
            if (clueForGuessAndWord(guess, palavraTeste) == clue) {
                contador++;
            }
        }
        return contador;
    }

    public Clue playGuess(String guess) {
        guess = guess.toUpperCase();
        
        for(int orderNumber = 1; orderNumber <= (int) Math.pow(3, wordLength()); orderNumber++) {
            if()
        }
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
