import java.lang.String;

public class IpurdleGame {
    String[] dicionario = {"JAVA", "LOOP", "EXIT", "TRUE","LONG", "THIS",
    "BREAK","WHILE","GRADE","PUPIL", "FIELD", "BASIC", "ABORT",
    "ABOVE","FALSE","FLOAT","SHORT","CLASS","FINAL",
    "STATIC","METHOD","STRING","RETURN","RANDOM","EQUALS","OBJECT","FUNCTION",
    "VARIABLE","INTEGER","SCANNER"};

    boolean[] dicionarioUsado = new boolean[dicionario.length];

    private Board board;
    
    /**
     * Construtor para a classe Ipurdle .
     * @param wordSize O tamanho da palavra para ser adivinhada.
     * @param maxGuesses O número máximo de guesses permitido.
     */
    public IpurdleGame(int wordSize, int maxGuesses) {
        this.board = new Board(wordSize, maxGuesses);
    }

    /**
     * Retorna o tamanho da palavra para ser adivinhada.
     * @return O tamanho da palavra.
     */
    public int wordLength() {
        return board.wordLength();
    }

    /**
     * Retorna o número máximo de guesses permitido.
     * @return O número máximo de guesses.
     */
    public int maxGuesses() {
        return board.maxGuesses();
    }

    /**
     * Retorna o número de guesses já feitos.
     * @return O número de guesses.
     */
    public int guesses() {
        return board.guesses();
    }

    /**
     * Verifica se uma guess é valida.
     * @param guess A guess que é verificada.
     * @return True se a guess é valida, false caso contrário.
     */
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

    /**
     * Verifica se o jogo já acabou.
     * @return True se o jogo já acabou, falso caso contrário.
     */
    public boolean isOver() {
        if(guesses() == maxGuesses()) {
            return true;
        }
        return board.clue().isMax();
    }

    /**
     * Cria uma clue para uma dada guess e word.
     * @param guess a guess dada.
     * @param word a word dada.
     * @return Um object Clue que representa a clue para a guess e word dadas.
     */
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

    /**
     * Determina quantas palavras do dicionário têm uma dada clue para uma dada guess.
     * @param clue A clue dada.
     * @param guess A guess dada.
     * @return O número de palavras do dicionário que têm a clue para a guess dadas.
     */
    private int howManyWordsWithClue(Clue clue, String guess) {
        guess = guess.toUpperCase();
        int contador = 0;
        String palavraTeste = "";

        for(int i = 0; i < dicionario.length; i++) {
            if(!dicionarioUsado[i] && guess.length() == dicionario[i].length()) {
                palavraTeste = dicionario[i];
                if(clueForGuessAndWord(guess, palavraTeste).orderNumber() == clue.orderNumber()) {
                    contador++;
                }
            }
        }
        return contador;
    }
    /**
     * Faz uma guess e retorna a clue para essa guess.
     * @param guess O guess que vai ser feito.
     * @return A clue para o guess.
     */
    public Clue playGuess(String guess) {
        guess = guess.toUpperCase();
        Clue clue = betterClueForGuess(guess);

        for(int i = 0; i < dicionario.length; i++) {
            if(!dicionarioUsado[i] && guess.length() == dicionario[i].length() && clueForGuessAndWord(guess, dicionario[i]).orderNumber() != clue.orderNumber()) {
                dicionarioUsado[i] = true;
            }
        }

        board.insertGuessAndClue(guess, clue);
        return clue;
    }

    /**
     * Determina a melhor clue para uma dada guess.
     * @param guess A guess dada.
     * @return A melhor clue para a guess.
     */
    private Clue betterClueForGuess(String guess) {
        guess = guess.toUpperCase();

        int contador = 0;
        int betterContador = 0;
        Clue clue = new Clue(1, wordLength());
        Clue betterClue = new Clue(1, wordLength());

        if(isValid(guess) && faltaUmaPalavra()) {
            return new Clue((int) Math.pow(3, wordLength()), wordLength());
        }

        for(int orderNumber = 1; orderNumber <= (int) Math.pow(3, wordLength()); orderNumber++) {
            clue = new Clue(orderNumber, wordLength());
            contador = howManyWordsWithClue(clue, guess);
            
            if(contador > betterContador) {
                betterClue = clue;
                betterContador = contador;
            }
        }
        return betterClue;
    }

    /**
     * Retorna uma representação do jogo em string.
     * @return Representação do jogo em String.
     */
    public String toString() {
        StringBuilder tabela = new StringBuilder();
        tabela.append("Ipurdle with words of " + wordLength() + " letters.\n");
        tabela.append("Remaining guesses: " + (maxGuesses() - guesses()) + "\n");
        tabela.append(board.toString());

        return tabela.toString();
    }
    /**
     * Verifica se falta apenas uma palavra no dicionário.
     * @return True se faltar apenas uma palavra, falso caso contrário.
     */
    private boolean faltaUmaPalavra() {
        int contador = 0;
        for(int i = 0; i < dicionario.length; i++) {
            if(!dicionarioUsado[i]) {
                contador++;
            }
        }
        return contador == 1;
    }
}
