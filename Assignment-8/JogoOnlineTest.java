import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {

    private JogoOnline jogo;

    @Before
    public void setUp() {
        jogo = new JogoOnline();
    }

    @Test
    public void testarLoginComUsuarioDesconhecido() throws SenhaInvalidaException {
        try {
            jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
            fail("Uma UsuarioInexistenteException deve ser lançada se o username for desconhecido");

        } catch (UsuarioInexistenteException e) {
            // tudo bem, aconteceu o que eu esperava!!!
        }
    }

    // equivalentemente...

    @Test(expected = UsuarioInexistenteException.class)
    public void testarLoginComUsuarioDesconhecidoOutraManeira() throws UsuarioInexistenteException, SenhaInvalidaException {
        JogoOnline jogo = new JogoOnline();
        jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
    }

    @Test
    public void testarCadastroELogin() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadorJoao = jogo.cadastrarJogador("joao", "1234");
        assertNotNull(jogadorJoao);
        assertEquals("joao", jogadorJoao.getUsername());

        jogo.fazerLogin("joao", "1234");

        assertTrue("Após o login (e antes do logout), o jogador " +
                "deve ser considerado online",
                jogadorJoao.isOnline());
    }

    @Test
    public void testarCadastroELoginComSenhaIncorreta() throws UsuarioInexistenteException {
        Jogador jogadorJoao = jogo.cadastrarJogador("joao", "1234");
        assertNotNull(jogadorJoao);
        assertEquals("joao", jogadorJoao.getUsername());

        try {
            jogo.fazerLogin("joao", "3246523");
            fail("O fazerLogin deveria lançar uma SenhaInvalidaException!!!");

        } catch (SenhaInvalidaException e) {
            // ok, era esperado que ela acontecesse!!!!
        }

        assertFalse(jogadorJoao.isOnline());
    }

    @Test
    public void testarIniciarPartida() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadorJoao = jogo.cadastrarJogador("joao", "1234");
        Jogador jogadoraMaria = jogo.cadastrarJogador("maria", "3456");

        jogo.fazerLogin("joao", "1234");
        jogo.fazerLogin("maria", "3456");

        // sanity check
        assertFalse(jogadorJoao.isJogando());
        assertFalse(jogadoraMaria.isJogando());

        Partida partida = jogo.iniciarPartida(jogadorJoao, jogadoraMaria);

        assertEquals(jogadorJoao, partida.getJogador1());
        assertEquals(jogadoraMaria, partida.getJogador2());
        assertEquals(Partida.PARTIDA_EM_ANDAMENTO,
                partida.getResultado());
        assertTrue(jogadorJoao.isJogando());
        assertTrue(jogadoraMaria.isJogando());
    }

    @Test
    public void testarLogout() throws UsuarioInexistenteException, SenhaInvalidaException {
        Jogador jogadoraMaria = jogo.cadastrarJogador("maria", "3456");

        jogo.fazerLogin("maria", "3456");

        assertTrue(jogadoraMaria.isOnline());

        jogo.fazerLogout(jogadoraMaria);

        assertFalse(jogadoraMaria.isOnline());
    }

    @Test(expected = RuntimeException.class)
    public void testarLogoutDeJogadorNaoOnline() {
        Jogador jogadoraMaria = jogo.cadastrarJogador("maria", "3456");

        jogo.fazerLogout(jogadoraMaria);
        // impossível fazer o logou t de jogador que nõ está online,
        // então esperamos tomar uma RuntimeException

    }

    @Test
    public void testarObterRanking() throws SenhaInvalidaException, UsuarioInexistenteException {


        Jogador jogadorWallace = jogo.cadastrarJogador("joao", "1234");
        Jogador jogadoraMaria = jogo.cadastrarJogador("wallace", "3456");

        jogo.fazerLogin("arthur", "1234");
        jogo.fazerLogin("wallace", "3456");

        Partida partida = jogo.iniciarPartida(jogadorWallace, jogadoraMaria);
        jogo.encerrarPartida(partida,1);
        Partida partida2 = jogo.iniciarPartida(jogadorWallace, jogadoraMaria);
        jogo.encerrarPartida(partida2,1);
        Partida partida3 = jogo.iniciarPartida(jogadorWallace, jogadoraMaria);
        jogo.encerrarPartida(partida3,1);


        assertEquals("joao",jogo.obterRanking().get(0).getUsername());

    }

    @Test
    public void testarObterJogadoresEmOrdemAlfabetica() throws SenhaInvalidaException, UsuarioInexistenteException {

        Jogador jogadorArthur = jogo.cadastrarJogador("arthur", "1234");
        Jogador jogadoraWallace = jogo.cadastrarJogador("wallace", "3456");

        jogo.fazerLogin("arthur", "1234");
        jogo.fazerLogin("wallace", "3456");


        assertEquals("arthur",jogo.obterJogadoresEmOrdemAlfabetica().get(0).getUsername());

    }

}
