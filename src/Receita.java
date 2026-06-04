public class Receita extends Transacao implements Relatorio {

    public void ExibirResumo() {
        System.out.println("Receita: " + getDescricao() + " | R$" + getValor());
    }
    
    public Receita(String descricao, double valor, String data) {
    super(descricao, valor, data); 
    }
}
