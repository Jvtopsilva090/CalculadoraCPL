package principal;

import java.util.List;

public class HistoricoManager {
    private final BancoDeDados banco;

    public HistoricoManager(BancoDeDados banco) {
        this.banco = banco;
    }

    public void adicionarAoHistorico(String linha) {
        banco.inserir(linha);
    }

    public List<String> getHistorico() {
        return banco.listar();
    }

    public void limparHistorico() {
        banco.limpar();
    }
}
