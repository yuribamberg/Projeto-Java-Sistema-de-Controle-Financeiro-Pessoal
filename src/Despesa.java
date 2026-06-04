public class Despesa extends Transacao implements Relatorio {

    public void ExibirResumo() {
        System.out.println("Despesa: " + getDescricao() + " | R$" + getValor());
    }
    
    public Despesa(String descricao, double valor, String data) {
    super(descricao, valor, data);
    }
}
