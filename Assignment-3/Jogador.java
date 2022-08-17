import java.util.ArrayList;

public class Jogador {

    private String Nome;
    private String Senha;
    public int Score;
    public boolean IsOnline;
    public boolean IsPlaying;

    private boolean Online;
    private boolean Playing;

    ArrayList<Partida> HistoricoJogadores;


    @Override
    public String toString() {
        return ("Nome Jogador: " + this.Nome + " - ") +
                ("Senha: " + this.Senha + " - ") +
                ("Status Online: " + this.IsOnline + " - ") +
                ("Está jogando: " + this.IsPlaying + "\n");
    }


    public Jogador(String Nome, String Senha) {
        this.Nome = Nome;
        this.Senha = Senha;
        this.Online = false;
        this.Playing = false;
        this.Score = 1000;
        this.HistoricoJogadores = new ArrayList<>();

    }

    public String getSenha() {
        return this.Senha;
    }

    public String getNome() {
        return this.Nome;
    }

    public void setOnline(boolean online) {
        IsOnline = online;
    }

    public boolean setPlaying(boolean playing) {
        this.IsPlaying = playing;
        return playing;

    }

    public boolean IsPlaying() {
        return this.IsPlaying;
    }

    public boolean IsOnline() {
        return !this.Online;
    }




    public void setScore(int resultado) {
        if (resultado == 1) {
            this.Score += 1;
        }
        if (resultado == 0) {
            this.Score -= 1;
        }


    }


}



