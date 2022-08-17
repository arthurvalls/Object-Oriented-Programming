public class ContaInvestimento extends Conta {
    private  String tipoInvestimento;
    private  float taxaJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros){
        super(numeroDaConta,correntista);
        this.tipoInvestimento = tipoInvestimento;
        this.taxaJuros = taxaJuros;
        for (Conta conta : Conta.ContasDoBanco ){
            if(conta.getCorrentista() == correntista && conta != this ){
                return;
            }
        }
        throw new RuntimeException("Correntista sem conta corrente!");
    }


    public float aplicarJuros(){

    float SaldoAtualizado = 0;
    SaldoAtualizado = this.taxaJuros * this.getSaldoEmReais();
    return SaldoAtualizado;

    }


    public void resgatarTotal(Conta conta){
        if(this.getSaldoEmReais() <0 ){
            return;
        }
        if(conta.getCorrentista() == this.getCorrentista() ){
            this.efetuarTransferecia(conta,this.getSaldoEmReais());
        }
    }

}
