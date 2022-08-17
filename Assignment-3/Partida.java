import java.util.ArrayList;


public class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    public int resultado;
    public static  ArrayList<Partida> HistoricoPartidas = new ArrayList<>();


//    @Override




    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

    }


    public int getResultado() {
        return resultado;
    }


    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public Jogador getJogador1() {
        return this.jogador1;
    }

    public Jogador getJogador2() {
        return this.jogador2;
    }


}
