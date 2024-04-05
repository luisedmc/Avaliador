/*
 * Luis Eduardo Meduna C. - 10408871
 */

public class App {
    public static void main(String[] args) throws Exception {
        Menu m = new Menu();
        m.StartMenu();
    }
}

/* 
  Notação infixa                       Notação posfixa
    A + B * C                           A B C * +
    A * (B + C)                         A B C + *
    (A + B) / (C – D)                   A B + C D - /
    (A + B) / (C – D) * E               A B + C D - / E *

    Incorreto: ((((A *(B – C))
 */