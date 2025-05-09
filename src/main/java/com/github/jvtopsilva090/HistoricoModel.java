package principal;

public class HistoricoModel {
    private int id;
    private String operacao;
    private double resultado;

    public HistoricoModel() {}

    public HistoricoModel(String operacao, double resultado) {
        this.operacao = operacao;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public String getOperacao() {
        return operacao;
    }

    public double getResultado() {
        return resultado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}

