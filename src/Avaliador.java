/*
 * Luis Eduardo Meduna C. - 10408871
 */

import java.util.Scanner;

public class Avaliador {
  public String exp;
  public Stack stack;
  private Scanner scanner;
  private int letraCount;
  private int values[];

  Avaliador() {
    exp = "";
    scanner = new Scanner(System.in);
    stack = new Stack(0);
    // values = new int[26]; // 26 letras
  }

  public String Expressao() {
    System.out.print("Digite a expressao: ");
    exp = scanner.nextLine();

    // Trim
    exp = exp.replaceAll("\\s+", "");

    while (!ValidarExpressao(exp)) {
      System.out.print("Expressao invalida! Digite novamente: \n-> ");
      exp = scanner.nextLine().replaceAll("\\s+", "");
    }

    return exp;
  }

  public boolean ValidarExpressao(String exp) {
    Stack stack = new Stack(exp.length());

    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      if (Character.isWhitespace(c)) {
        continue; // Ignorar whitespace
      } else if (c == '(') {
        stack.Push(c);
      } else if (c == ')') {
        if (stack.isEmpty() || stack.Peek() != '(') {
          return false; // Parenteses sem fechamento
        }
        stack.Pop();
      } else if (!Character.isLetterOrDigit(c) && !isValidOperator(c)) {
        return false; // Char invalido
      }
    }

    return stack.isEmpty();
  }

  private boolean isValidOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
  }

  // Realiza leitura dos valores numericos da expressao
  public int[] Valores() {
    boolean[] letraVista = new boolean[26]; // Letras;
    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      if (Character.isLetter(c)) {
        letraVista[c - 'A'] = true; // Letra ja vista
        letraCount++;
      }
    }

    values = new int[letraCount];

    for (int i = 0; i < 26; i++) {
      if (letraVista[i]) {
        char variable = (char) ('A' + i);
        System.out.print(variable + ": ");
        int value = scanner.nextInt();
        values[i] = value;
      }
    }

    // System.out.println("valores: " + java.util.Arrays.toString(values));
    return values;
  }

  public String InfixaToPosfixa(String exp) {
    StringBuilder postfix = new StringBuilder();
    Stack stack = new Stack(exp.length());

    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);

      if (Character.isLetterOrDigit(c)) {
        postfix.append(c);
      } else if (c == '(') {
        stack.Push(c);
      } else if (c == ')') {
        while (!stack.isEmpty() && stack.Peek() != '(') {
          postfix.append(" ").append(stack.Pop());
        }
        stack.Pop(); // Remove '('
      } else { // Operator
        while (!stack.isEmpty() && precedence(c) <= precedence(stack.Peek())) {
          postfix.append(" ").append(stack.Pop());
        }
        stack.Push(c);
      }
    }

    while (!stack.isEmpty()) {
      postfix.append(" ").append(stack.Pop());
    }

    return postfix.toString().trim();
  }

  public int AvaliarPosfixa(String exp) {
    Stack stack = new Stack(exp.length());
    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      if (Character.isLetter(c)) {
        stack.PushInt(values[c - 'A']);
      } else if (Character.isDigit(c)) {
        stack.PushInt((c - '0'));
      } else if (isValidOperator(c)) {
        int b = stack.PopInt();
        int a = stack.PopInt();
        stack.PushInt(operacao(a, b, c));
      }
    }

    return stack.PopInt();
  }

  // Realiza a operacao entre dois operandos, recebendo 2 valores numericos e a
  // operacao em char
  private int operacao(int a, int b, char c) {
    switch (c) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        return a / b;
      case '^':
        return (int) Math.pow(a, b);
      default:
        return 0;
    }
  }

  // Ordem de prioridades dos operadores
  private int precedence(char c) {
    switch (c) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
  }
}
