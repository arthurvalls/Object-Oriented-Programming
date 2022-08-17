public class ImpressoraJatoDeTinta extends Impressora {

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando bicos de impressão e verificando tintas...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando jatos de tinta...");
    }


}
