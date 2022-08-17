import java.util.ArrayList;

public class Grafica {

    public float precoCor;
    private float precoPretoeBranco;
    public float orcamento;
    public ArrayList<Impressora> ListaImpressoras = new ArrayList<>();
    int contador = 0;

    public void imprimirDocumento(Documento documento) {
        ListaImpressoras.get(contador).imprimirDocumento(documento);
        if (contador != ListaImpressoras.size() - 1){
            contador++;
            return;
        }
        contador = 0;
    }


    public float orcarImpressao(Documento documento) {
        if (documento.isEmCores()) {
            orcamento = this.precoCor * (documento.getQuantPaginas());
            return orcamento;
        }
        orcamento = this.precoPretoeBranco * (documento.getQuantPaginas());
        return orcamento;
    }

    public void adicionarImpressora(Impressora impressora) {

        ListaImpressoras.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if (emCores == false) {
            this.precoPretoeBranco = precoPorPagina;
        }
        this.precoCor = precoPorPagina;
    }
}
