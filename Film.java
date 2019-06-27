import java.io.Serializable;

public class Film implements Serializable{

    private String titolo;
    private int durata;
    private int postiLiberi;

    Film(){

    }

    Film(String titolo, int durata, int posti_liberi){
        this.titolo =titolo;
        this.durata = durata;
        this.postiLiberi =posti_liberi;
    }

    public String getTitolo() { return titolo; }

    public void setTitolo(String titolo) { this.titolo = titolo; }


    public int getDurata() { return durata; }

    public void setDurata() {
        this.durata = durata;
    }


    public int getPostiLiberi() {
        return postiLiberi;
    }

    synchronized void setPostiLiberi(int posti) { postiLiberi -=posti; }

    @Override
    public String toString(){
        return ("\nNome: " + titolo + "\nDurata: " + durata + " min\nNumero posti disponibili: " + postiLiberi);
    }
}