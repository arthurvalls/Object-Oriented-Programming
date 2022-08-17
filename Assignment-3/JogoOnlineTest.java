import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JogoOnlineTest {

    Jogador Arthur = new Jogador("Arthur", "senha1");
    Jogador Caio = new Jogador("Caio", "senha2");
    Jogador Joao = new Jogador("Joao", "senha3");
    Jogador Wallace = new Jogador("Wallace", "senha4");

    @Before
    public void setUp() {

        JogoOnline.ListaJogadores.add(Arthur);
        JogoOnline.ListaJogadores.add(Caio);
        JogoOnline.ListaJogadores.add(Joao);
        JogoOnline.ListaJogadores.add(Wallace);

    }

    @Test
    public void cadastroTest() {
        JogoOnline.Cadastro("Gabriel", "senha5");

        Assert.assertEquals(5, JogoOnline.ListaJogadores.size());
    }

    @Test
    public void loginTest() {

        JogoOnline.login("Arthur", "senha1");

        Assert.assertTrue(Arthur.IsOnline);

    }

    @Test
    public void logoutTest() {
        JogoOnline.login("Arthur", "senha1");

        JogoOnline.logout("Arthur");

        Assert.assertFalse(Arthur.IsOnline);
    }

    @Test
    public void iniciarPartidaTest() {


        JogoOnline.login("Arthur", "senha1");
        JogoOnline.login("Caio", "senha2");

        JogoOnline.iniciarPartida(Arthur, Caio);
        Assert.assertTrue(Arthur.IsOnline);
        Assert.assertTrue(Arthur.IsPlaying);
        Assert.assertTrue(Caio.IsOnline);
        Assert.assertTrue(Caio.IsPlaying);

    }

    @Test
    public void encerrarPartidaTest() {
        JogoOnline.login("Arthur", "senha1");
        JogoOnline.login("Caio", "senha2");
        JogoOnline.iniciarPartida(Arthur, Caio);

        Partida partida1 = new Partida(Arthur, Caio);

        JogoOnline.encerrarPartida(partida1, 2);


        Assert.assertEquals(2, partida1.resultado);

        Assert.assertFalse(Arthur.IsPlaying);
        Assert.assertFalse(Caio.IsPlaying);
    }

    @Test
    public void escolherAdversarioTest() {
        JogoOnline.login("Arthur", "senha1");
        JogoOnline.login("Caio", "senha2");
        JogoOnline.login("Joao", "senha3");
        JogoOnline.login("Wallace", "senha4");
        JogoOnline.iniciarPartida(Joao, Caio);


        //Como Hugo e Joao estão jogando e Arthur é o solicitando, sobra apenas Wallace como adversário
        //Testando apenas este test sozinho,ele obtêm êxito, porém, ao testar todos ao mesmo tempo, este é o único test que falha.
        Assert.assertTrue(Wallace.IsOnline());
        Assert.assertEquals(Wallace, JogoOnline.escolherAdversario(Arthur));

    }
    @Test
    public void historicoTest(){
        JogoOnline.login("Arthur", "senha1");
        JogoOnline.login("Caio", "senha2");
        JogoOnline.login("Joao", "senha3");
        JogoOnline.login("Wallace", "senha4");
        JogoOnline.iniciarPartida(Arthur,Caio);
        JogoOnline.iniciarPartida(Wallace,Joao);

        Partida partida1 = new Partida(Arthur,Caio);
        Partida partida2 = new Partida(Wallace,Joao);

        JogoOnline.encerrarPartida(partida1,2);
        JogoOnline.encerrarPartida(partida2,2);
        Assert.assertEquals(2,Partida.HistoricoPartidas.size());


    }
}



