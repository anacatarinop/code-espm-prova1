package Ex1;

import java.util.Stack;

public class PilhaIgual {

    public static boolean saoIguais(Stack<Integer> pilha1, Stack<Integer> pilha2) {
        if (pilha1.size() != pilha2.size()) { // Verifica se as pilhas têm tamanhos iguais
            return false;
        }
        while (!pilha1.empty() && !pilha2.empty()) {
            int elemento1 = pilha1.pop();
            int elemento2 = pilha2.pop();
            if (elemento1 != elemento2) { // Verifica se os elementos das pilhas são iguais
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Stack<Integer> pilha1 = new Stack<Integer>();
        pilha1.push(2);
        pilha1.push(2);
        pilha1.push(2);
        
        Stack<Integer> pilha2 = new Stack<Integer>();
        pilha2.push(2);
        pilha2.push(2);
        pilha2.push(2);
        System.out.println("As pilhas são iguais? " + saoIguais(pilha1, pilha2)); // Imprimir "true" ou "false" dependendo dos valores inseridos
    }

}
