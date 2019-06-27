import java.io.*;
import java.util.LinkedList;

public class GestoreFilm {

    static LinkedList<FilmAnimazione> animazione = new LinkedList<FilmAnimazione>();
    static LinkedList<filmAzione> azione = new LinkedList<filmAzione>();
    static LinkedList<filmHorror> horror = new LinkedList<filmHorror>();

    static void caricaAnimazione(){

        String titolo;
        int durata;
        int posti;

        try{
            FileReader fr = new FileReader("FilmAnimazione.txt");
            BufferedReader br = new BufferedReader(fr);
            while(!((titolo=br.readLine()).equalsIgnoreCase("fine"))){
                durata = Integer.parseInt(br.readLine());
                posti = Integer.parseInt(br.readLine());

                FilmAnimazione f_ani = new FilmAnimazione(titolo,durata,posti);
                animazione.add(f_ani);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File non trovato");
        }
        catch (IOException e){
            System.out.println("Errore di IO");
        }
    }

    static void caricaAzione(){

        String titolo;
        int durata;
        int posti;

        try{
            FileReader fr = new FileReader("FilmAzione.txt");
            BufferedReader br = new BufferedReader(fr);
            while(!((titolo=br.readLine()).equalsIgnoreCase("fine"))){
                durata = Integer.parseInt(br.readLine());
                posti = Integer.parseInt(br.readLine());

                filmAzione f_azi = new filmAzione(titolo,durata,posti);
                azione.add(f_azi);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File non trovato");
        }
        catch (IOException e){
            System.out.println("Errore di IO");
        }
    }

    static void caricaHorror(){

        String titolo;
        int durata;
        int posti;

        try{
            FileReader fr = new FileReader("FilmHorror.txt");
            BufferedReader br = new BufferedReader(fr);
            while(!((titolo=br.readLine()).equalsIgnoreCase("fine"))){
                durata = Integer.parseInt(br.readLine());
                posti = Integer.parseInt(br.readLine());

                filmHorror f_hor = new filmHorror(titolo,durata,posti);
                horror.add(f_hor);
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File non trovato");
        }
        catch (IOException e){
            System.out.println("Errore di IO");
        }
    }

    static void visualizzaAnimazione(){

        System.out.println(animazione);
    }

    static void visualizzaAzione(){

        System.out.println(azione);
    }

    static void visualizzaHorror(){

        System.out.println(horror);
    }

    static void stampaFileAnimazione(LinkedList<FilmAnimazione> lista){
        try {
            FileWriter fw = new FileWriter("FilmAnimazione.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Film f : lista){
                bw.write(f.getTitolo());
                bw.newLine();
                bw.write(""+f.getDurata());
                bw.newLine();
                bw.write(""+f.getPostiLiberi());
                bw.newLine();
            }
            bw.write("fine");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void stampaFileAzione(LinkedList<filmAzione> lista){
        try {
            FileWriter fw = new FileWriter("FilmAzione.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Film f : lista){
                bw.write(f.getTitolo());
                bw.newLine();
                bw.write(""+f.getDurata());
                bw.newLine();
                bw.write(""+f.getPostiLiberi());
                bw.newLine();
            }
            bw.write("fine");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void stampaFileHorror(LinkedList<filmHorror> lista){
        try {
            FileWriter fw = new FileWriter("FilmHorror.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Film f : lista){
                bw.write(f.getTitolo());
                bw.newLine();
                bw.write(""+f.getDurata());
                bw.newLine();
                bw.write(""+f.getPostiLiberi());
                bw.newLine();
            }
            bw.write("fine");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String verificaTransizione(int posti, Film f){

        if((posti>0)&&(f.getPostiLiberi()>=posti)){
            f.setPostiLiberi(posti);
            return ("Prenotazione avvenuta con successo. Le auguriamo una buona visione.");
        }
        else{
            return ("Non abbiamo posti a sufficienza, ci dispiace.");
        }

    }
}