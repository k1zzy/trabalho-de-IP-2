public class Test {
    public static void main(String[] args) {
            LetterStatus[] elements = {LetterStatus.CORRECT_POS, LetterStatus.CORRECT_POS, LetterStatus.INEXISTENT};
            Clue clue = new Clue(elements);
            Clue clue2 = new Clue(22, 3);

            IpurdleGame game = new IpurdleGame(5,5);

            System.out.println(game.playGuess("WHILE"));
            //System.out.println();
        }
}