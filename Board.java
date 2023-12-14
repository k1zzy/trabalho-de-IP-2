public class Board {
    private int wordSize;
    private int maxGuesses;
    private int guessNumber;
    private Clue clue;
    private String guess;

    /**
     * Cria um tabuleiro de um jogo de Ipurdle no seu estado inicial, ou seja, vazio
     * 
     * @param wordSize o tamanho das palavras do jogo
     * @param maxGuesses o número máximo de tentativas
     * @requires wordSize > 0
     * @requires maxGuesses > 0
     */
    public Board(int wordSize, int maxGuesses) {
        this.wordSize = wordSize;
        this.maxGuesses = maxGuesses;
        this.guessNumber = 0;
    }

    /**
     * Retorna o tamanho da palavra
     * 
     * @return o tamanho da palavra
     */
    public int wordLength() {
        return wordSize;
    }

    /**
     * Retorna o número máximo de tentativas definidas
     *
     * @return o número máximo de tentativas permitidas
     */
    public int maxGuesses() {
        return maxGuesses;
    }

    /**
     * Retorna o número de tentativas já feitas
     *
     * @return O número de tentativas já feitas
     */
    public int guesses() {
        return guessNumber;
    }

    /**
     * Insere uma guess e uma pista no tabuleiro do jogo
     * 
     * @requires guess.length()==clue.length()==wordLength()
     * @requires guesses() < maxGuesses()
     * @param guess a guess a ser inserida no tabuleiro
     * @param clue a pista a ser inserida no tabuleiro
     */
    public void insertGuessAndClue(String guess, Clue clue) {
        this.guess = guess;
        this.clue = clue;
        this.guessNumber++;
    }

    /**
     * Retorna uma representação em formato de string do tabuleiro com o seguinte formato:
     *  +---------------+
     *  | WHILE | ____* |
     *  +---------------+
     *  | FIELD | __o__ |
     *  +---------------+
     *  | ABOVE | ***** |
     *  +---------------+
     * 
     * @return A representação em formato de string do tabuleiro
     */
    public String toString() {
        StringBuilder board = new StringBuilder();
        
        for(int i = 0; i < guessNumber; i++) {
            board.append("+---------------+\n | ");
            board.append(guess);
            board.append(" | ");
            board.append(clue.toString());
            board.append(" |\n");
        }
        board.append("+---------------+\n");

        return board.toString();
    }
}