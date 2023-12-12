public class Test {
    public static void main(String[] args) {
            LetterStatus[] elements = {LetterStatus.CORRECT_POS, LetterStatus.WRONG_POS, LetterStatus.INEXISTENT};
            Clue clue = new Clue(elements);

            int orderNumber = clue.orderNumber();

            System.out.println(orderNumber);
        }
}