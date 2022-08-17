public abstract class Impressora {

    public int paginas;
    public int documentosImpressos;

    public Impressora(){
        this.paginas = 0;
        this.documentosImpressos = 0;
    }

    public boolean imprimirDocumento(Documento documento) {

        if (documento.getQuantPaginas() > this.paginas) {
            return false;
        }

        for (int i = 0; i < documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.paginas--;

        }

        this.documentosImpressos++;
        return true;

    }


    public void carregarPapel(int numeroDeFolhas) {

        this.paginas += numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {

        return this.paginas;

    }

    public int getQuantidadeDocumentosImpressos() {
        return this.documentosImpressos;

    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}
