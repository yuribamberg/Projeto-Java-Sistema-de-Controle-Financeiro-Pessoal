import java.util.Scanner;
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("\n===== CONTROLE FINANCEIRO PESSOAL =====");
            System.out.println("1 - Cadastrar Receita");
            System.out.println("2 - Cadastrar Despesa");
            System.out.println("3 - Listar Transações");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("Cadastro de receita em desenvolvimento...");
                    break;

                case 2:
                    System.out.println("Cadastro de despesa em desenvolvimento...");
                    break;

                case 3:
                    System.out.println("Listagem de transações em desenvolvimento...");
                    break;

                case 4:
                    System.out.println("Relatórios em desenvolvimento...");
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}