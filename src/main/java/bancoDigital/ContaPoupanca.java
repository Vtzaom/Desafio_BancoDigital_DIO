package bancoDigital;


public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupanca ===");
        super.imprimirInfosComuns();
    }



    public void usarChequeEspecial(){
        System.out.println("Nao eh possivel usar cheque especial na poupanca");
    }




}