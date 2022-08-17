import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTeste {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private ContaCorrente contaDoJoao;
    private Correntista joao;
    private float saldoInicial;
    private Correntista maria;
    private ContaCorrente contaDaMaria;


    @Before
    public void setUp() {
        joao = new Correntista("João", 222);
        contaDoJoao = new ContaCorrente(1, "joao");
        saldoInicial = contaDoJoao.getSaldoEmReais();
        maria = new Correntista("Maria",32093220);
        contaDaMaria = new ContaCorrente(2,"maria");

    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.", 10.0, saldoInicial,
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito", saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados", saldoInicial,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados", saldoInicial, contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato", extratoAntes, extratoDepois);

    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta", saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos", saldoInicial,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferencia() {


        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("Impossível realizar uma transferência de valor maior do que o saldo da conta.", saldoInicial + 3, contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        assertEquals("O valor não foi transferido da conta.", saldoInicial - 3, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {
        Correntista maria = new Correntista("Maria", 22222);
        ContaCorrente contaDaMaria = new ContaCorrente(2, "maria");

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Não realizar transferência de valor maior do que o saldo.", saldoInicial, contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        assertEquals("Não realizar transferência de valor abaixo ou igual a 0.", saldoInicial, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("teste",222,joao.getCpf(),FLOAT_DELTA);

    }

    @Test
    public void getQuantidadeDeTransacoesDeTodasAsContas() {

        contaDaMaria.receberDepositoEmDinheiro(10);
        contaDaMaria.receberDepositoEmDinheiro(29);
        contaDoJoao.receberDepositoEmDinheiro(2222);
        contaDaMaria.sacar(22);
        contaDoJoao.sacar(292);
        assertEquals("test",5,ContaCorrente.quantidadeDeTransacoesDeTodasAsContas,FLOAT_DELTA);

    }

}