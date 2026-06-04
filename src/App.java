import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcao;

        ArrayList<Receita> receitas = new ArrayList<>();
        ArrayList<Despesa> despesas = new ArrayList<>();
        
        do {
            System.out.println("\n===== CONTROLE FINANCEIRO PESSOAL =====");
            System.out.println("1 - Cadastrar Receita");
            System.out.println("2 - Cadastrar Despesa");
            System.out.println("3 - Listar Transações");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try { 
                opcao = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println(" ");
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1: {
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();

                    scanner.nextLine();
                    
                    System.out.print("Data: ");
                    String data = scanner.nextLine();

                    Receita receita = new Receita(descricao, valor, data);
                    receitas.add(receita);

                    System.out.println("Receita cadastrada!");
                    break;
                }
                case 2: {
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();

                    scanner.nextLine();
                    
                    System.out.print("Data: ");
                    String data = scanner.nextLine();

                    Despesa despesa = new Despesa(descricao, valor, data);
                    despesas.add(despesa);

                    System.out.println("Despesa cadastrada!");
                    break;
                }
                case 3: {
                    System.out.println("\n====== Transações ======");

                    if(receitas.size() == 0 && despesas.size() == 0) {
                        System.out.println("Nenhuma transação cadastrada!");
                    } else {
                    for(Receita r : receitas) {
                        r.ExibirResumo();
                    }
                    for(Despesa d : despesas) {
                        d.ExibirResumo();
                    }
                    }
                    break;
                }
                case 4: {
                    double totalReceitas = 0;
                    double totalDespesas = 0;

                    for(Receita r : receitas) {
                        totalReceitas = totalReceitas + r.getValor();
                    }

                    for(Despesa d : despesas) {
                        totalDespesas = totalDespesas + d.getValor();
                    }

                    double saldo = totalReceitas - totalDespesas;
                    System.out.println("A receita é de: R$" + totalReceitas);
                    System.out.println("A despesa é de: R$" + totalDespesas);
                    System.out.println("O saldo é de: R$" + saldo);
                    break;
                }
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