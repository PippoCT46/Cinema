import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCinema implements MenuInterface {

    @Override
    public void mostraMenu() {
        System.out.println("-------------------------");
        System.out.println("\nBenvenuto nel Cinema di Pippo!\nChe genere di film ti piacerebbe vedere oggi?\n");
        System.out.println(" (1) - Animazione");
        System.out.println(" (2) - Azione");
        System.out.println(" (3) - Horror");
        System.out.println(" (4) - Per uscire");
        System.out.println("\n-------------------------\n");
    }

    @Override
    public int getScelta() {
        Scanner scanner = new Scanner(System.in);

        boolean controllo = false;

        int scelta = 0;

        while (!controllo) {
            System.out.println("Fai la tua scelta:");
            try {
                scelta = scanner.nextInt();
                controllo = verificaScelta(scelta);
                if(!controllo){
                    System.out.println("Inserisci un valore compreso tra 1 e 4");
                }
            }
            catch (InputMismatchException e) {
                controllo = false;
                System.out.println("Inserire un numero intero!");
                String spaz = scanner.nextLine();
            }
        }
        return scelta;
    }

    @Override
    public boolean verificaScelta(int verifica) {
        return (verifica > 0 && verifica < 5);
    }
}