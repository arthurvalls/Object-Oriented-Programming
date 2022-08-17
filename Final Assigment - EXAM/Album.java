import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 *
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 *
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {

    /**
     * Construtor.
     *
     * @param tamanhoDoAlbum O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                       terão posições entre 1 e o tamanho do álbum)
     *
     * @param quantidadeItemPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */


    HashMap<Integer, Colecionavel> figurinhasRepetidas;
    HashMap<Integer, Colecionavel> figurinhasNaoRepetidas;
    ArrayList<Colecionavel> quantidadeMaximaFigurinhas;
    int tamanhoDoAlbum;
    int quantidadeItemPorPacotinho;


    public Album(int tamanhoDoAlbum, int quantidadeItemPorPacotinho) {

        figurinhasNaoRepetidas = new HashMap<>();
        figurinhasRepetidas = new HashMap<>();
        quantidadeMaximaFigurinhas = new ArrayList();

        this.quantidadeItemPorPacotinho = quantidadeItemPorPacotinho;
        this.tamanhoDoAlbum = tamanhoDoAlbum;

    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     *
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {


        if(quantidadeItemPorPacotinho > pacotinho.size()){throw new PacotinhoInvalidoException();}
        if(pacotinho.size() != this.quantidadeItemPorPacotinho){throw new PacotinhoInvalidoException();}


        for(int i = 0; i < pacotinho.size(); i++) {

            if (pacotinho.get(i).getPosicao() > tamanhoDoAlbum)
                throw new PacotinhoInvalidoException();

        }

        for (T figurinha : pacotinho) {

            quantidadeMaximaFigurinhas.add(figurinha);

            if (figurinhasNaoRepetidas.containsKey(figurinha.getPosicao())) {
                figurinhasRepetidas.put(figurinha.getPosicao(), figurinha);
            }
            figurinhasNaoRepetidas.put(figurinha.getPosicao(), figurinha);

        }
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {

        return this.tamanhoDoAlbum;

    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {

        return figurinhasNaoRepetidas.size();

    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {

        return quantidadeMaximaFigurinhas.size() - figurinhasNaoRepetidas.size();

    }

    public boolean possuiItemColado(int posicao) {

        return figurinhasNaoRepetidas.containsKey(posicao);

    }

    public boolean possuiItemRepetido(int posicao) {

        return figurinhasRepetidas.containsKey(posicao);

    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {

        return tamanhoDoAlbum - figurinhasNaoRepetidas.size();

    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {

        if(figurinhasNaoRepetidas.containsKey(posicao)){
            return (T) figurinhasNaoRepetidas.get(posicao);
        }

        return null;

    }
}