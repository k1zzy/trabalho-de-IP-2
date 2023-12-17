public class Test {
    public static void main(String[] args) {
            LetterStatus[] elements = {LetterStatus.INEXISTENT,LetterStatus.INEXISTENT, LetterStatus.WRONG_POS,LetterStatus.INEXISTENT, LetterStatus.INEXISTENT};
            Clue clue = new Clue(elements);
            Clue clue2 = new Clue(22, 5);

            IpurdleGame game = new IpurdleGame(4,5);

            //System.out.println();
        }
}