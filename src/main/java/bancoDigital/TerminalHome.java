package bancoDigital;

import java.util.Scanner;
import java.util.Locale;

public class TerminalHome {



    public static void terminalHome(){
        Banco banco = new Banco();
        Conta cc ;
        Conta poupanca ;
        boolean continuar = true;



        while(continuar){
            Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

            System.out.println("Digite 0: para sair do programa.\nDigite 1: para criar conta.\n " +
                    "Digite 2:para login");
            int opcao = scanner.nextInt();


            switch(opcao){

                case 1:
                    System.out.println("Digite seu nome: ");
                    String nome = scanner.next();
                    System.out.println("Digite sua senha: ");
                    String senha = scanner.next();

                    Cliente cliente = new Cliente(nome, senha);
                    cc = new ContaCorrente(cliente);
                    poupanca = new ContaPoupanca(cliente);

                    cliente.adicionarConta(cc);
                    cliente.adicionarConta(poupanca);
                    banco.adicionarCliente(cliente);
                    break;
                case 2:
                    System.out.println("Para acessar digite seu nome: ");
                    String nomeAcesso = scanner.next();
                    System.out.println("Digite sua senha: ");
                    String senhaAcesso = scanner.next();

                    Cliente clienteAutenticado = banco.autenticarCliente(nomeAcesso,senhaAcesso);
                    TerminalUser.terminalUser(clienteAutenticado,banco);

                    break;


                case 0:
                    System.out.println("Programa encerrado.");
                    continuar = false;  // Atualiza a variável de controle para encerrar o loop
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }



    }
}
