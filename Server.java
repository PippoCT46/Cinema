import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Server {

    int porta;
    int connessione_n;
    ServerSocket server;

    public Server(int port){

        this.porta = port;

        GestoreFilm.caricaAnimazione();
        GestoreFilm.caricaAzione();
        GestoreFilm.caricaHorror();

    }

    public void go() {

        try {
            server = new ServerSocket(porta);
            System.out.println("Server fatto partire sulla porta " + porta);
        }
        catch (IOException ex) {
            System.out.println("Il server non è partito sulla porta " + porta);
            ex.printStackTrace();
            System.exit(-1);
        }

        while (true) {
            try {
                System.out.println("Pronto per accettare connessioni...");
                Socket client = server.accept();
                connessione_n++;
                System.out.println("\nConnessione accettata da " + client.getRemoteSocketAddress()+"\n");

                ClientManager cm = new ClientManager(client);
                Thread t = new Thread(cm,"Thread " + String.valueOf(connessione_n));
                t.start();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class ClientManager implements Runnable{

        Socket client_assegnato;
        String nome;

        public ClientManager(Socket s){
            this.client_assegnato = s;
        }

        @Override
        public void run() {

            this.nome = Thread.currentThread().getName();

            System.out.println("\nInizio Thread del client manager");

            try {
                Scanner in = new Scanner(client_assegnato.getInputStream());
                PrintWriter out = new PrintWriter(client_assegnato.getOutputStream());
                ObjectOutputStream objectOutputStream;

                int i = in.nextInt();
                in.nextLine();

                switch (i){

                    case(1):{

                        // 1. Mando
                        System.out.println("L'utente " + client_assegnato.getRemoteSocketAddress() + " ha scelto come genere: Film d'animazione\n");
                        out.println("Hai scelto i film di animazione. Ecco la lista:");
                        out.flush();
                        in.nextLine();

                        LinkedList<FilmAnimazione> film_animazione = new LinkedList<FilmAnimazione>();
                        film_animazione = GestoreFilm.animazione;
                        System.out.println(film_animazione);

                        objectOutputStream = new ObjectOutputStream(client_assegnato.getOutputStream());

                        // 2. Mando
                        System.out.println("\n");
                        objectOutputStream.writeObject(film_animazione);
                        objectOutputStream.flush();
                        in.nextLine();

                        // 3. Ricevo
                        String filmScelto = in.nextLine();
                        System.out.println(filmScelto);
                        out.println("");
                        out.flush();

                        boolean trovato = false;

                        for(Film f : film_animazione){

                            if(f.getTitolo().equalsIgnoreCase(filmScelto)){

                                trovato=true;

                                if(trovato){

                                    // 4.Mando
                                    out.println("Film presente nella nostra lista!");
                                    out.flush();
                                    in.nextLine();
                                }

                                // 5. Mando
                                out.println("Indica il numero di posti che vuoi prenotare");
                                out.flush();
                                in.nextLine();

                                // 6. Ricevo
                                int posti = in.nextInt();
                                in.nextLine();
                                out.println("");
                                out.flush();

                                // 7. Mando
                                String verifica;
                                verifica= GestoreFilm.verificaTransizione(posti, f);
                                out.println(verifica);
                                out.flush();
                                in.nextLine();
                                System.out.println("Lista aggiornata\n");
                                System.out.println(film_animazione);
                            }
                        }

                        if(!trovato){

                            // 4- Mando
                            out.println("Film non presente nella nostra lista. Ci dispiace.");
                            out.flush();
                            in.nextLine();
                        }

                        GestoreFilm.stampaFileAnimazione(film_animazione);

                        break;
                    }
                    case(2):{

                        // 1. Mando
                        System.out.println("L'utente " + client_assegnato.getRemoteSocketAddress() + " ha scelto come genere: Film d'azione\n");
                        out.println("Hai scelto i film d'azione. Ecco la lista:");
                        out.flush();
                        in.nextLine();

                        LinkedList<filmAzione> film_azione = new LinkedList<filmAzione>();
                        film_azione = GestoreFilm.azione;
                        System.out.println(film_azione);

                        objectOutputStream = new ObjectOutputStream(client_assegnato.getOutputStream());

                        // 2. Mando
                        System.out.println("\n");
                        objectOutputStream.writeObject(film_azione);
                        objectOutputStream.flush();
                        in.nextLine();

                        // 3. Ricevo
                        String filmScelto = in.nextLine();
                        System.out.println(filmScelto);
                        out.println("");
                        out.flush();

                        boolean trovato = false;

                        for(Film f : film_azione){

                            if(f.getTitolo().equalsIgnoreCase(filmScelto)){

                                trovato=true;

                                if(trovato){

                                    // 4.Mando
                                    out.println("Film presente nella nostra lista!");
                                    out.flush();
                                    in.nextLine();
                                }

                                // 5. Mando
                                out.println("Indica il numero di posti che vuoi prenotare");
                                out.flush();
                                in.nextLine();

                                // 6. Ricevo
                                int posti = in.nextInt();
                                in.nextLine();
                                out.println("");
                                out.flush();

                                // 7. Mando
                                String verifica;
                                verifica= GestoreFilm.verificaTransizione(posti, f);
                                out.println(verifica);
                                out.flush();
                                in.nextLine();
                                System.out.println("Lista aggiornata\n");
                                System.out.println(film_azione);
                            }
                        }

                        if(!trovato){

                            // 4- Mando
                            out.println("Film non presente nella nostra lista. Ci dispiace.");
                            out.flush();
                            in.nextLine();
                        }

                        GestoreFilm.stampaFileAzione(film_azione);

                        break;
                    }
                    case(3):{

                        // 1. Mando
                        System.out.println("L'utente " + client_assegnato.getRemoteSocketAddress() + " ha scelto come genere: Film horror\n");
                        out.println("Hai scelto i film horror. Ecco la lista:");
                        out.flush();
                        in.nextLine();

                        LinkedList<filmHorror> film_horror= new LinkedList<filmHorror>();
                        film_horror= GestoreFilm.horror;
                        System.out.println(film_horror);

                        objectOutputStream = new ObjectOutputStream(client_assegnato.getOutputStream());

                        // 2. Mando
                        System.out.println("\n");
                        objectOutputStream.writeObject(film_horror);
                        objectOutputStream.flush();
                        in.nextLine();

                        // 3. Ricevo
                        String filmScelto = in.nextLine();
                        System.out.println(filmScelto);
                        out.println("");
                        out.flush();

                        boolean trovato = false;

                        for(Film f : film_horror){

                            if(f.getTitolo().equalsIgnoreCase(filmScelto)){

                                trovato=true;

                                if(trovato){

                                    // 4.Mando
                                    out.println("Film presente nella nostra lista!");
                                    out.flush();
                                    in.nextLine();
                                }

                                // 5. Mando
                                out.println("Indica il numero di posti che vuoi prenotare");
                                out.flush();
                                in.nextLine();

                                // 6. Ricevo
                                int posti = in.nextInt();
                                in.nextLine();
                                out.println("");
                                out.flush();

                                // 7. Mando
                                String verifica;
                                verifica= GestoreFilm.verificaTransizione(posti, f);
                                out.println(verifica);
                                out.flush();
                                in.nextLine();
                                System.out.println("Lista aggiornata\n");
                                System.out.println(film_horror);
                            }
                        }

                        if(!trovato){

                            // 4- Mando
                            out.println("Film non presente nella nostra lista. Ci dispiace.");
                            out.flush();
                            in.nextLine();
                        }

                        GestoreFilm.stampaFileHorror(film_horror);

                        break;
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}