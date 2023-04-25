package Ex3;

import javax.swing.JOptionPane;

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

            dadosString += String.format(formatString, curr) + "\n";

            curr = curr.dir;
        }

        dadosString += "]";

        String output = dadosString;

        return output;
    }

}


// Classe que representa uma carga
class Carga {
    private String cnpj;
    private String nomeEmpresa;
    private String destino;
    private int peso;

    public Carga(String cnpj, String nomeEmpresa, String destino, int peso) {
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.destino = destino;
        this.peso = peso;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getPeso() {
        return this.peso;
    }

    @Override
    public String toString() {
        return String.format("CNPJ: %s, Nome Empresa: %s, Destino: %s, Peso: %d", this.cnpj, this.nomeEmpresa, this.destino,
                this.peso);
    }
}

// Classe que representa o navio
class Navio {
    private ListaDuplaGenerica<Carga> cargas;
    private int capacidadeMaxima;

    public Navio(int capacidadeMaxima) {
        this.cargas = new ListaDuplaGenerica<Carga>();
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean reservarCarga(Carga carga) {
        int pesoTotal = 0;

        NodoGenerico<Carga> curr = cargas.getInicio();

        while (curr != null) {
            pesoTotal += curr.dado.getPeso();
            curr = curr.dir;
        }

        if (pesoTotal + carga.getPeso() > this.capacidadeMaxima) {
            return false;
        }

        cargas.inserirFim(carga);
        return true;
    }

    public Carga pesquisarCarga(String cnpj) {
        NodoGenerico<Carga> curr = cargas.getInicio();

        while (curr != null) {
            if (curr.dado.getCnpj().equals(cnpj)) {
                return curr.dado;
            }
            curr = curr.dir;
        }

        return null;
    }

    public boolean excluirCarga(String cnpj) {
        NodoGenerico<Carga> curr = cargas.getInicio();

        while (curr != null) {
            if (curr.dado.getCnpj().equals(cnpj)) {
                cargas.deletar(curr.dado);
                return true;
            }
            curr = curr.dir;
        }
        return false;
    }

    public String imprimirCargasReservadas() {
        return cargas.toString();
    }
}


public class SistemaReservaCargas {
    public static void main(String[] args) throws Exception {
        Navio navio = new Navio(10000);

        String menu = "Sistema de Reserva de Cargas\n1. Reservar Carga\n2. Pesquisar Carga\n3. Imprimir Cargas Reservadas\n4. Excluir Carga\n5. Finalizar";

        String opcao = JOptionPane.showInputDialog(menu);

        while (!opcao.equals("5")) {
            switch (opcao) {
                case "1":
                    String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da empresa");
                    String nomeEmpresa = JOptionPane.showInputDialog("Digite o nome da empresa");
                    String destino = JOptionPane.showInputDialog("Digite o destino da carga");
                    int peso = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso da carga"));

                    Carga carga = new Carga(cnpj, nomeEmpresa, destino, peso);

                    // Verifica se o cnpj já existe
                    if (navio.pesquisarCarga(cnpj) != null) {
                        JOptionPane.showMessageDialog(null, "CNJP já cadastrado!");
                        break;
                    }

                    if (navio.reservarCarga(carga)) {
                        JOptionPane.showMessageDialog(null, "Carga reservada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível reservar a carga!");
                    }

                    break;
                case "2":
                    cnpj = JOptionPane.showInputDialog("Digite o CNPJ da empresa");

                    Carga cargaPesquisada = navio.pesquisarCarga(cnpj);

                    if (cargaPesquisada != null) {
                        JOptionPane.showMessageDialog(null, cargaPesquisada.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Carga não encontrada!");
                    }

                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, navio.imprimirCargasReservadas());
                    break;
                case "4":
                    cnpj = JOptionPane.showInputDialog("Digite o CNPJ da empresa");

                    if (navio.excluirCarga(cnpj)) {
                        JOptionPane.showMessageDialog(null, "Carga excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Carga não encontrada!");
                    }

                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

            opcao = JOptionPane.showInputDialog(menu);
        }
    }
}