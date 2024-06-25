package bancoDigital;

import java.util.List;
import java.util.ArrayList;

public class Banco {
    private String nome;
    protected List<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente autenticarCliente(String nome,String senha){
        Cliente cliente = null;
        if(!clientes.isEmpty()){
            for (Cliente cliente1 : clientes) {
                if(cliente1.getNome().equals(nome) && cliente1.autenticar(senha)){
                    cliente = cliente1;
                }
            }
            return cliente;
        }else{
            System.out.println(" nao ha  cliente  cadastrado");
            return null;
        }
    }

    public void tranferirCliente(double valor, String nome, Conta contaCorrenteSaida){
        Cliente cliente = null;
        if(!clientes.isEmpty()){
            for (Cliente cliente1 : clientes) {
                if(cliente1.getNome().equals(nome) ){
                    cliente = cliente1;
                    IConta contaCorrenteEntrada =  cliente.getConta(0);
                    contaCorrenteSaida.sacar(valor);
                    contaCorrenteEntrada.depositar(valor);
                    System.out.println("Tranferencia para "+nome+ "realizada com sucesso .\n Seu saldo atual na Conta Corrente eh"+
                            contaCorrenteSaida.getSaldo());
                }
            }

        }else{
            System.out.println(" nao ha  cliente  cadastrado");

        }

    }
    public void listarContasDoCliente(Cliente userAutenticado) {

        if (userAutenticado != null) {
            System.out.println("Contas do cliente " + userAutenticado.getNome() + ":");
            List<Conta> contas = userAutenticado.getContas();
            for (int i = 0; i < contas.size(); i++) {
                System.out.println(i + ": " + contas.get(i));
            }
        } else {
            System.out.println("Autenticação falhou.");
        }
    }

    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
