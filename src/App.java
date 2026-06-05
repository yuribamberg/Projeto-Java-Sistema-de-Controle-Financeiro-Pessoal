import java.util.ArrayList;                // Lista dinâmica para armazenar receitas e despesas
import java.util.InputMismatchException;   // Exceção lançada quando o usuário digita texto em vez de número
import java.util.Scanner;                  // Leitura de entradas do usuário via terminal
import java.util.Locale;                   // Configuração regional (usado para definir o separador decimal)


public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Locale.US garante que o separador decimal seja ponto (.) em vez de vírgula
        scanner.useLocale(Locale.US); 

        int opcao;

        ArrayList<Receita> receitas = new ArrayList<>();
        ArrayList<Despesa> despesas = new ArrayList<>();
        
        // do/while garante que o menu apareça pelo menos uma vez antes de checar a condição de saída
        do {
            System.out.println("\n===== CONTROLE FINANCEIRO PESSOAL =====");
            System.out.println("1 - Cadastrar Receita");
            System.out.println("2 - Cadastrar Despesa");
            System.out.println("3 - Listar Transações");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Consultoria Inteligente 🤖");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try { 
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Se o usuário digitar texto em vez de número, evita que o programa trave
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine(); // limpa o buffer do scanner para a próxima leitura funcionar
                opcao = -1;
            }

            switch (opcao) {
                case 1: {
                    // nextLine() após nextInt() é necessário para consumir o Enter que ficou no buffer
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Valor (use ponto para centavos): ");
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

                    System.out.print("Valor (use ponto para centavos): ");
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

                    if (receitas.isEmpty() && despesas.isEmpty()) {
                        System.out.println("Nenhuma transação cadastrada!");
                    } else {
                        for (Receita r : receitas) {
                            r.ExibirResumo();
                        }
                        for (Despesa d : despesas) {
                            d.ExibirResumo();
                        }
                    }
                    break;
                }
                case 4: {
                    double totalReceitas = calcularTotalReceitas(receitas);
                    double totalDespesas = calcularTotalDespesas(despesas);
                    double saldo = totalReceitas - totalDespesas;

                    System.out.println("A receita é de: R$" + totalReceitas);
                    System.out.println("A despesa é de: R$" + totalDespesas);
                    System.out.println("O saldo é de: R$" + saldo);
                    break;
                }
                case 5: {
                    System.out.println("\n====== CONSULTORIA FINANCEIRA ======");
                    
                    if (receitas.isEmpty() && despesas.isEmpty()) {
                        System.out.println("Cadastre algumas transações primeiro para a análise!");
                        break;
                    }

                    double totalReceitas = calcularTotalReceitas(receitas);
                    double totalDespesas = calcularTotalDespesas(despesas);
                    double saldo = totalReceitas - totalDespesas;

                    System.out.println("Conectando à API de mercado e analisando sua saúde financeira...");
                    
                    // Chama a classe Api para buscar a cotação em tempo real e extrair o valor do campo "bid"
                    String respostaBruta = Api.enviarComando("");
                    String precoDolar = Api.extrairTextoDoJson(respostaBruta);
                    
                    System.out.println("\n🤖 INSIGHTS DO CONSULTOR FINANCEIRO:");
                    System.out.println("[Mercado] O Dólar comercial está cotado hoje em R$ " + precoDolar);
                    System.out.println("-----------------------------------------------------");

                    // Quatro faixas de saldo com dicas contextualizadas à cotação do dólar
                    if (saldo < 0) {
                        System.out.println("🚨 STATUS: Seu saldo está NEGATIVO em R$ " + saldo);
                        System.out.println("Dica: Evite compras internacionais desnecessárias com o dólar a R$ " + precoDolar + ".");
                        System.out.println("Corte imediatamente gastos supérfluos para equilibrar suas contas.");
                    } else if (saldo == 0) {
                        System.out.println("⚠️ STATUS: Seu saldo está zerado (R$ 0.00).");
                        System.out.println("Dica: Orçamento no limite! Evite qualquer tipo de nova despesa");
                        System.out.println("e tente economizar pequenos valores para criar uma reserva de segurança.");
                    } else if (saldo > 0 && saldo < 500) {
                        System.out.println("👍 STATUS: Saldo positivo moderado de R$ " + saldo);
                        System.out.println("Dica: Bom começo! Guarde pelo menos 10% desse valor.");
                        System.out.println("Fique atento às variações de preço do mercado antes de gastar em lazer.");
                    } else {
                        System.out.println("💰 STATUS: Excelente! Saldo positivo de R$ " + saldo);
                        System.out.println("Dica: Ótima saúde financeira! Com esse saldo, você já pode começar");
                        System.out.println("a estudar sobre investimentos em renda fixa para valorizar seu dinheiro.");
                    }
                    System.out.println("-----------------------------------------------------");
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

    private static double calcularTotalReceitas(ArrayList<Receita> receitas) {
        double total = 0;
        for (Receita r : receitas) {
            total += r.getValor();
        }
        return total;
    }

    private static double calcularTotalDespesas(ArrayList<Despesa> despesas) {
        double total = 0;
        for (Despesa d : despesas) {
            total += d.getValor();
        }
        return total;
    }
}
