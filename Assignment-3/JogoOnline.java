

import java.util.ArrayList;

public class JogoOnline {

    public static ArrayList<Jogador> ListaJogadores = new ArrayList<>();


    public static void Cadastro(String Nome, String Senha) {

        Jogador Jogador = new Jogador(Nome, Senha);
        JogoOnline.ListaJogadores.add(Jogador);

    }

    public static void login(String Nome, String Senha) {

        for (int i = 0; i < ListaJogadores.size(); i++) {
            Jogador jogador = JogoOnline.ListaJogadores.get(i);
            if (jogador.getSenha().equals(Senha) && jogador.getNome().equals(Nome)) {
                jogador.setOnline(true);

            }
        }
    }

    public static void logout(String Nome) {
        for (int i = 0; i < ListaJogadores.size(); i++) {
            Jogador Jogador = JogoOnline.ListaJogadores.get(i);

            if (Jogador.getNome().equals(Nome)) {
                Jogador.setOnline(false);
            }
        }

    }

    public static Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {

        if (!jogador1.IsOnline() || jogador1.IsPlaying() || !jogador2.IsOnline() || jogador2.IsPlaying())
            return null;

        Partida partida = new Partida(jogador1, jogador2);
        jogador1.setPlaying(true);
        jogador2.setPlaying(true);
        return partida;
    }

    public static void encerrarPartida(Partida partida, int resultado) {
        partida.setResultado(resultado);

        partida.getJogador1().setPlaying(false);
        partida.getJogador2().setPlaying(false);

        partida.getJogador1().HistoricoJogadores.add(partida);
        partida.getJogador2().HistoricoJogadores.add(partida);


        partida.HistoricoPartidas.add(partida);

        if (resultado == 0) {
            return;
        }
        if (resultado == 1) {
            partida.getJogador1().setScore(1);
            partida.getJogador2().setScore(0);

        }
        if (resultado == 2) {
            partida.getJogador1().setScore(0);
            partida.getJogador2().setScore(1);

        }



    }

    public static Jogador escolherAdversario(Jogador solicitante){
        for (int i = 0; i < ListaJogadores.size(); i++){
            Jogador jogador = JogoOnline.ListaJogadores.get(i);
            if (jogador != solicitante && jogador.IsOnline() && !jogador.IsPlaying()){
                return jogador;
            }
        }
        System.out.println("Nenhum jogador disponível.");
        return null;
    }

}




