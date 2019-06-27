public class Main {

    public static void main(String[] args) {

        if (args.length!=1){
            System.out.println("Per favore, specifica il numero di porta da usare.");
            System.exit(-1);
        }

        Server server = new Server(Integer.parseInt(args[0]));

        server.go();
    }
}