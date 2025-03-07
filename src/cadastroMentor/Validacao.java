package cadastroMentor;

import java.util.Scanner;

public class Validacao {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    public static String lerEmail(String mensagem) {
        while (true) {
            String email = lerString(mensagem);
            if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                return email;
            } else {
                System.out.println("Email inválido! Use o formato nome@dominio.com");
            }
        }
    }
}