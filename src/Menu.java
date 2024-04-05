/*
 * Luis Eduardo Meduna C. - 10408871
 */

import java.util.Scanner;

public class Menu {
  private Avaliador avaliador;
  private Scanner scanner;
  private int opt;
  private String exp;

  Menu() {
    scanner = new Scanner(System.in);
    opt = 0;
  }

  public void StartMenu() {
    PrintMenu();

    while (opt != 4) {
      System.out.print("-> ");
      opt = scanner.nextInt();

      switch (opt) {
        case 1:
          avaliador = new Avaliador();
          exp = avaliador.Expressao();
          avaliador.exp = exp;
          avaliador.Valores();
          break;

        case 2:
          if (exp == null) {
            System.out.println("Nenhuma expressao foi digitada ainda.");
            break;
          }

          avaliador.stack = new Stack(exp.length());
          String expr = avaliador.InfixaToPosfixa(exp);
          System.out.println("Expressao posfixa: " + expr);
          System.out.println("Resultado: " + avaliador.AvaliarPosfixa(expr));
          break;

        case 3:
          PrintMenu();
          break;

        case 4: // Sair
          break;

        default:
          System.out.println("Opçao invalida!");
      }
    }
    scanner.close();
  }

  public void PrintMenu() {
    System.out.println("--- Menu ---\n");
    System.out.println("Opçoes: ");
    System.out.println("1. Digitar expressao (infixa)");
    System.out.println("2. Converter e avaliar expressao (posfixa)");
    System.out.println("3. Mostrar menu");
    System.out.println("4. Sair");
  }
}
