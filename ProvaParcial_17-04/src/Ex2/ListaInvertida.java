package Ex2;

class NodoGenerico<T> {
    T dado;
    NodoGenerico<T> esq, dir;

    public NodoGenerico(T dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return dado.toString();
    }

    public T getDado() {
        return this.dado;
    }

    public NodoGenerico<T> getDireita() {
        return this.dir;
    }

    public NodoGenerico<T> getEsquerda() {
        return this.esq;
    }
}

class ListaDuplaGenerica<T> {
    private NodoGenerico<T> inicio, fim;
    private int tamanho = 0;

    public void inserirInicio(T dado) {
        NodoGenerico<T> novoNodo = new NodoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            inicio.esq = novoNodo;
            novoNodo.dir = inicio;

            inicio = novoNodo;
        }

        tamanho++;
    }

    public void inserirFim(T dado) {
        NodoGenerico<T> novoNodo = new NodoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            fim.dir = novoNodo;
            novoNodo.esq = fim;

            fim = novoNodo;
        }

        tamanho++;
    }

    public NodoGenerico<T> pesquisar(T busca) {
        NodoGenerico<T> curr = inicio;

        while (curr != null) {
            if (curr.dado.equals(busca)) {
                return curr;
            }
            curr = curr.dir;
        }

        return null;
    }

    public boolean deletar(T busca) {
        NodoGenerico<T> nodoParaDeletar = pesquisar(busca);

        if (nodoParaDeletar == null) {
            return false;
        }

        if (tamanho == 1) {
            inicio = null;
            fim = null;
        } else if (nodoParaDeletar.equals(inicio)) {
            // NodoGenerico<T> nodoDepois = nodoParaDeletar.dir;
            // nodoDepois.esq = null;

            // inicio = nodoDepois;

            nodoParaDeletar.dir.esq = null;

            inicio = nodoParaDeletar.dir;
        } else if (nodoParaDeletar.equals(fim)) {
            // NodoGenerico<T> nodoAnterior = nodoParaDeletar.esq;
            // nodoAnterior.dir = null;

            // fim = nodoAnterior;

            nodoParaDeletar.esq.dir = null;

            fim = nodoParaDeletar.esq;
        } else {
            // NodoGenerico<T> nodoAnterior = nodoParaDeletar.esq;
            // NodoGenerico<T> nodoDepois = nodoParaDeletar.dir;

            // nodoAnterior.dir = nodoDepois;
            // nodoDepois.esq = nodoAnterior;

            nodoParaDeletar.esq.dir = nodoParaDeletar.dir;

            nodoParaDeletar.dir.esq = nodoParaDeletar.esq;
        }

        nodoParaDeletar = null;
        tamanho--;
        return true;
    }

    public NodoGenerico<T> getInicio() {
        return inicio;
    }

    public NodoGenerico<T> getFim() {
        return fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        String dadosString = "[";
        NodoGenerico<T> curr = inicio;

        while (curr != null) {
            String formatString = "%s, ";
            if (this.fim.equals(curr)) {
                formatString = "%s";
            }

            dadosString += String.format(formatString, curr);

            curr = curr.dir;
        }

        dadosString += "]";

        String output = dadosString;

        return output;
    }

}

public class ListaInvertida {

    public static ListaDuplaGenerica<Integer> inverteLista(ListaDuplaGenerica<Integer> lista) {
        ListaDuplaGenerica<Integer> listaInvertida = new ListaDuplaGenerica<Integer>();
        NodoGenerico<Integer> curr = lista.getFim();

        while (curr != null) {
            listaInvertida.inserirFim(curr.getDado());
            curr = curr.getEsquerda();
        }

        return listaInvertida;
    }


    public static void main(String[] args) {
        ListaDuplaGenerica<Integer> lista = new ListaDuplaGenerica<Integer>();
        lista.inserirFim(1);
        lista.inserirFim(2);
        lista.inserirFim(3);
        lista.inserirFim(4);
        System.out.println("Lista original: " + lista); // Imprimir a lista original Ex: "[1, 2, 3, 4]"
        ListaDuplaGenerica<Integer> listaInvertida = inverteLista(lista);
        System.out.println("Lista invertida: " + listaInvertida); // Imprimir a lista invertida Ex: "[4, 3, 2, 1]"
    }

}
