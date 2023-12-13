public class Clue {
    private LetterStatus[] elements;
    private int wordSize;
    private int orderNumber;
    private final int MAX_ORDERNUMBER = (int) Math.pow(wordSize, 3);
    //private final int MIN_ORDERNUMBER = 1;
PIXA
    /**
     * Representa uma pista de um jogo Ipurdle
     * 
     * @requires elements != null
     * @param elements um array de LetterStatus que representa a pista
     */
    public Clue(LetterStatus[] elements){  
        this.elements = elements;
        this.wordSize = elements.length;
        this.orderNumber = getOrderNumber();
    }	

    /**
     * Representa uma pista de um jogo Ipurdle
     * 
     * @requires wordSize > 0
     * @requires 1 <= orderNumber <= 3^wordSize
     * @param orderNumber o número de ordem da pista
     * @param wordSize o tamanho das palavras
     */
    public Clue(int orderNumber, int wordSize) {
        this.wordSize = wordSize;
        this.orderNumber = orderNumber;
        this.elements = getElements(orderNumber);
    }

    /**
     * Se a pista é a máxima, ou seja, o orderNumber é 3^wordSize
     * 
     * @return se a pista é a máxima
     */
    public boolean isMax(){
        return orderNumber == MAX_ORDERNUMBER;
    }

    /**
     * O tamanho da pista (e do jogo)
     * 
     * @return o tamanho da pista
     */
    public int length() {
        return wordSize;
    }

    /**
     * O número de ordem da pista
     * 
     * @return o número de ordem da pista
     */
    public int orderNumber() {
        return orderNumber;
    }

    /**
     * Retorna o vetor com os elementos da pista que podem ser INEXISTENT, WRONG_POS ou CORRECT_POS
     * 
     * @return o vetor com os elementos da pista
     */
    public LetterStatus[] letterStatus() {
        return elements;
    }

    /**
    * Retorna uma representação em string da pista com o formato: 
    *   - '_' - se for INEXISTENT, ou seja, se a letra não estiver na palavra;
    *   - 'o' - se for WRONG_POS, ou seja, se a letra estiver na palavra mas não na posição correta;
    *   - '*' - se for CORRECT_POS, ou seja, se a letra estiver na posição correta.
    *   Exemplo: "___o*"

    * @return uma representação em string da pista
    */
    public String toString() {
        StringBuilder clue = new StringBuilder();
        for (int i = 0; i < wordSize; i++) {
            switch(this.elements[i]) {
                case INEXISTENT:
                    clue.append("_");
                    break;
                case WRONG_POS:
                    clue.append("o");
                    break;
                case CORRECT_POS:
                    clue.append("*");
                    break;
            }
        }
        return clue.toString();
    }

    /**
     * Retorna a array dos elementos da pista dependente de dado número de ordem
     * 
     * @param orderNumber o número de ordem da pista
     * @return um array de elementos do tipo LetterStatus que representa a pista
     */
    private LetterStatus[] getElements(int orderNumber) {
        LetterStatus[] elements = new LetterStatus[wordSize];
        int number = (orderNumber-1);

        for(int i = (wordSize - 1); i >= 0; i--) {
            switch (number % 3) {
                case 0:
                    elements[i] = LetterStatus.INEXISTENT;
                    break;
                case 1:
                    elements[i] = LetterStatus.WRONG_POS;
                    break;
                case 2:
                    elements[i] = LetterStatus.CORRECT_POS;
                    break;
            }
            number /= 3;
        }
        return elements;
    }

    /**
     * Retorna o número de ordem da pista dependente dos elementos que representam a pista
     *
     * @return o número de ordem da pista
     */
    private int getOrderNumber() {
        int orderNumber = 0;

        for(int i = 0; i < wordSize; i++) {
            switch (elements[i]) {
                case INEXISTENT:
                    orderNumber += 0;
                    break;
                case WRONG_POS:
                    orderNumber += 1;
                    break;
                case CORRECT_POS:
                    orderNumber += 2;
                    break;
            }
            orderNumber *= 3;
        }
        return (orderNumber / 3) + 1; //processo inverso ao da função getElements
    }
}