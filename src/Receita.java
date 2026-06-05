// Receita herda de Transacao (recebe descrição, valor e data) e implementa a interface Relatorio
public class Receita extends Transacao implements Relatorio {

    // Construtor repassa os dados para a classe pai Transacao via super()
    public Receita(String descricao, double valor, String data) {
        super(descricao, valor, data);
    }

    // Implementação obrigatória do método definido na interface Relatorio
    public void ExibirResumo() {
        System.out.println("Receita: " + getDescricao() + " | R$" + getValor());
    }
}
