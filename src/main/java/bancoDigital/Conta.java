package bancoDigital;


public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private double chequeEspecial = 500.0;
    protected int agencia;
    protected int numero;
    protected double saldo = 0.0;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {

            if (saldo < 0) {
                double saldoNegativo = -saldo;
                if(valor <= saldoNegativo) {
                    chequeEspecial += valor;
                }else {
                    chequeEspecial += saldoNegativo;
                }
                saldo += valor;
            } else {
                saldo += valor;
            }
        }
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }



    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public  void usarChequeEspecial(double saque){
        if (getSaldo() >= 0 ){
            if(saque <= chequeEspecial + getSaldo()){
                chequeEspecial =chequeEspecial - (saque - getSaldo()) ;
                saldo -= saque;
                System.out.printf("Operacao realizada com o uso do cheeque especial , voce ainda possui R$%.2f de cheque especial %n",chequeEspecial);

            }else{
                System.out.println( "Voce nao tem limite de cheque especial ou no momento nossa plataforma está apresentando instabilidade." +
                        " \n Tente novamente mais tarde");

            }
        }else {
            if (saque <= chequeEspecial) {
                chequeEspecial = chequeEspecial  - saque ;
                saldo -= saque;
                System.out.printf("Operacao realizada com o uso do cheeque especial , voce ainda possui R$%.1f de cheque especial %n", chequeEspecial);

            } else {
                System.out.println("Voce nao tem limite de cheque especial ou no momento nossa plataforma está apresentando instabilidade." +
                        " \n Tente novamente mais tarde");
            }
        }
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s%n", this.cliente.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Numero: %d%n", this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }


}