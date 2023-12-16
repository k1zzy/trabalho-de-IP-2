public class Test {
    public static void main(String[] args) {
            LetterStatus[] elements = {LetterStatus.CORRECT_POS, LetterStatus.CORRECT_POS, LetterStatus.INEXISTENT};
            Clue clue = new Clue(elements);
            Clue clue2 = new Clue(22, 3);

            System.out.println(clue.orderNumber());
            //System.out.println();
        }
}