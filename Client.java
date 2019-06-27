import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Client {

    public static void main (String[] args){

        ObjectInputStream objectInputStream;
        LinkedList<Film> listaFilm=null;

        if(args.length<2){

            System.out.println("Specificare come argomento l'indirizzo e la porta");
            System.exit(-1);

        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);
        Socket socket = null;

        try {
            socket = new Socket(address,port);
        }

        catch (IOException e) {
            System.out.println("Host non raggiungibile");
            e.printStackTrace();
            System.exit(-1);
        }

        MenuInterface ui = new MenuCinema();

        ui.mostraMenu();
        int scelta = ui.getScelta();
        System.out.println("Hai scelto " + scelta);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nMessaggio mandato \"" + scelta + "\" da " + socket.getRemoteSocketAddress()+"\n");

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());

            String mess_mandati, mess_ricevuti;

            out.println(scelta);
            out.flush();

            if(scelta==1||scelta==2||scelta==3){

                // 1. Ricevo
                mess_ricevuti = in.nextLine();
                System.out.println(mess_ricevuti);
                out.println("");
                out.flush();

                objectInputStream = new ObjectInputStream(socket.getInputStream());

                try {

                    // 2. Ricevo
                    System.out.println("\n");
                    listaFilm = (LinkedList<Film>) objectInputStream.readObject();
                    System.out.println(listaFilm);
                    out.println("");
                    out.flush();

                    // 3. Mando
                    System.out.println("\nInserire il titolo del film -> ");
                    mess_mandati = scanner.nextLine();
                    out.println(mess_mandati);
                    out.flush();
                    in.nextLine();

                    // 4.ricevo
                    System.out.println("\n");
                    String esito=in.nextLine();
                    System.out.println(esito);
                    out.println("");
                    out.flush();
                    if(esito.equalsIgnoreCase("Film non presente nella nostra lista. Ci dispiace.")){
                        System.exit(-1);
                    }

                    // 5. Ricevo
                    System.out.println("\n");
                    System.out.println(in.nextLine());
                    out.println("");
                    out.flush();

                    // 6. Mando
                    out.println(scanner.nextLine());
                    out.flush();
                    in.nextLine();

                    // 7. Ricevo
                    System.out.println("\n");
                    System.out.println(in.nextLine());
                    out.println("");
                    out.flush();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                socket.close();

            }
            else{
                System.out.println("Chiusura client...");
                socket.close();
            }
        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }
}