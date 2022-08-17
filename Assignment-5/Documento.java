import java.util.ArrayList;

public class Documento {


    private boolean cores;
    public ArrayList<String> numeroDePaginas;

    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.cores = emCores;
        numeroDePaginas = paginas;

    }

    public boolean isEmCores() {
        return this.cores;

    }

    public int getQuantPaginas() {
        return numeroDePaginas.size();

    }

    public String getPagina(int numeroDaPagina) {
        return numeroDePaginas.get(numeroDaPagina);
    }
}
