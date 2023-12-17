import java.util.Scanner;

public class IpurdleTxt {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        IpurdleGame game = new IpurdleGame(5, 6);

        println("Bem-vindo ao Ipurdle!");
        println("O objetivo deste jogo e adivinhar uma palavra de certo tamanho, com um maximo de tentativas. As palavras sao todas relacionadas com a cadeira de IP.");
        println("O jogo iniciara com palavras de tamanho " + game.wordLength() + " e com " + game.maxGuesses() + " tentativas. Boa sorte!!");

        String guess = "";

        for(int i = 1; i <= game.maxGuesses(); i++) {
            print("Insere a palavra que achas que e a correta: ");
            guess = leitura.nextLine();;

            while(!game.isValid(guess)) {
                print("Palavra invalida, insere uma palavra valida: ");
                guess = leitura.nextLine();
            }

            game.playGuess(guess);
            println(game.toString());

            if(game.isOver()) {
                break;
            }
        }

        if(game.isOver()) {
            println("Parabens, adivinhaste a palavra!");
        }

        else {
            println("Nao adivinhaste a palavra, tenta outra vez!");
        }

        leitura.close();
    }

    /**
     * Imprime uma linha com linebreak
     * 
     * @param s string com o texto a imprimir
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Imprime uma linha sem linebreak
     * 
     * @param s string com o texto a imprimir
     */
    public static void print(String s) {
        System.out.print(s);
    }
}