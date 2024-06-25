package bancoDigital;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class TerminalUser {
    private static int numero = 1;

    private TerminalUser(){
        int contadorCliente = numero++;

    }

    public static void terminalUser(Cliente user,Banco banco){
        boolean continuar = true;
        Conta contaCorrente = user.getConta(0);
        Conta contaPoupanca = user.getConta(1);

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        while (continuar) {
            System.out.println("Digite 0: para sair do programa.\nDigite 1:depositar.\n " +
                    "Digite 2:sacar. \nDigite 3:tranferir para contaPoupanca. \nDigite 4:extrato \nDigite 5: Transferir para outro cliente");
            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    // TODO: depositar na corrente
                    if (contaCorrente == null) {
                        System.out.println("Conta não iniciada");
                        break;

                    } else {
                        System.out.println("Digite o valor do deposito: ");
                        double entrada = scanner.nextDouble();
                        contaCorrente.depositar(entrada);
                    }
                    break;
                case 2:
                    // TODO:Sacar
                    System.out.println("Digite 1 para sacar da poupança ,digite 2 para sacar da corrente ");
                    int saqueOP = scanner.nextInt();
                    if (saqueOP == 1) {
                        contaPoupanca = user.getConta(1); // Acessar a segunda conta
                        System.out.println("Digite o valor para saque");
                        double entrada1 = scanner.nextDouble();
                        if (contaPoupanca.getSaldo() >= entrada1) {
                            contaPoupanca.sacar(entrada1);
                        } else {
                            System.out.println("Saldo insuficiente.");
                        }
                    } else if (saqueOP == 2) {
                        contaCorrente = user.getConta(0); // Acessar a primeira conta
                        System.out.println("Digite o valor para saque");
                        double entrada1 = scanner.nextDouble();
                        if (contaCorrente.getSaldo()  >= entrada1) {
                            contaCorrente.sacar(entrada1);
                        } else {
                            if(contaCorrente.getSaldo() + contaCorrente.getChequeEspecial()>=entrada1) {
                                contaCorrente.usarChequeEspecial(entrada1);
                            }else{
                                System.out.println("Saldo insuficiente.");

                            }
                        }
                    } else {
                        System.out.println("opcao invalida ");
                    }
                    break;

                case 3:
                    // TODO: Tranferir para contaPoupanca ou corrente
                    System.out.println("Digite 1 para transferencia para contaPoupanca ou 2 para conta corrente");
                    int tipoTrans = scanner.nextInt();
                    if (tipoTrans == 1) {
                        System.out.println("Digite o valor da transferencia");
                        int valor = scanner.nextInt();
                        contaCorrente.transferir(valor, contaPoupanca);
                    } else if (tipoTrans == 2) {
                        System.out.println("Digite o valor da transferencia");
                        int valor = scanner.nextInt();
                        contaPoupanca.transferir(valor, contaCorrente);
                    } else {
                        System.out.println("opcao invalida");
                    }
                    break;
                case 4:
                    List<Conta> userContas = user.getContas();
                    for(Conta conta: userContas){
                        conta.imprimirExtrato();
                    }

                    break;
                case 5:
                    System.out.println("Digite o nome do Cliente que deseja transferir" );
                    String nome = scanner.next();
                    System.out.println("Digite o valor da transferencia");
                    int valor = scanner.nextInt();

                    banco.tranferirCliente(valor,nome,contaCorrente);
                    break;

                case 0:
                    System.out.println("Programa encerrado. Voltando para home");
                    continuar = false;  // Atualiza a variável de controle para encerrar o loop

                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }



        }

    }
}
