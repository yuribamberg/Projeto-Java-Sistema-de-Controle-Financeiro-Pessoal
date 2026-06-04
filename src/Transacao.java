public abstract class Transacao {
    private double valor;
    private String descricao;
    private String data;

    public Transacao(String descricao, double valor, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}