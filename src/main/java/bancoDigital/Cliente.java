package bancoDigital;

import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String senha;
    private List<Conta> contas;

    public String getNome() {
        return nome;
    }
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public Cliente(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return contas;
    }
    public Conta getConta(int index) {
        if (index >= 0 && index < contas.size()) {
            return contas.get(index);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nome).append("\n");
        for (IConta conta : contas) {
            sb.append(conta.toString()).append("\n");
        }
        return sb.toString();    }

}
