package com.trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class programas2e3{

    static int identificador = 1;
    @SuppressWarnings("rawtypes")
    static List lexema = new ArrayList();
    @SuppressWarnings("rawtypes")
    static List token = new ArrayList();
    static String[] palReservadas  = {"if", "for", "while"};
    static String[] opArititmetico = {"++", "--", "=", "+", "-", "*", "/", ";"};
    static String[] opRelacional   = {"!=", "==", "<=", ">=", ">", "<"};

    @SuppressWarnings({ "resource"})
    public static void main (String[] args) {
        String respostaFinal = "";

        System.out.println("Digite a frase:");
        String respostaUsuario = new Scanner(System.in).nextLine();

        respostaUsuario = respostaUsuario.replaceAll("\\s+","");

        int a = 0;

        while (respostaUsuario.indexOf("//") != -1 || respostaUsuario.indexOf("/*") != -1) {
            if (respostaUsuario.indexOf("//") != -1 && respostaUsuario.indexOf("/*") != -1) {
                String auxiliar = respostaUsuario.substring(0, respostaUsuario.indexOf("//"));
                respostaUsuario = respostaUsuario.substring(0, auxiliar.indexOf("/*"))+auxiliar.substring((auxiliar.indexOf("*/")+2), auxiliar.length());
            } else if (respostaUsuario.indexOf("//") != -1) {
                respostaUsuario = respostaUsuario.substring(0, respostaUsuario.indexOf("//"));
                respostaFinal = "Foi encontrado um Comentário de Linha, o resultado foi o seguinte:";
            } else if (respostaUsuario.indexOf("/*") != -1) {
                respostaUsuario = respostaUsuario.substring(0, respostaUsuario.indexOf("/*"))+respostaUsuario.substring((respostaUsuario.indexOf("*/")+2), respostaUsuario.length());
                respostaFinal = "Foi encontrado um Comentário de Bloco, o resultado foi o seguinte:";
            }
            a ++;
        }

        if (a>1) {
            System.out.println("Foram encontrados Comentários de Linha e de Bloco, o resultado foi o seguinte:");
        } else {
            System.out.println(respostaFinal);
        }
        System.out.println(respostaUsuario);

        a=0;

        while (!respostaUsuario.equals("")) {
            a++;
            if (Character.isLetter(respostaUsuario.charAt(0))) {
                respostaUsuario = checaIdentificador(respostaUsuario, a+"º");
            } else if (Character.isDigit(respostaUsuario.charAt(0))) {
                respostaUsuario = checaNumero(respostaUsuario, a+"º");
            } else if (Arrays.stream(opArititmetico).parallel().anyMatch(respostaUsuario::contains)) {
                respostaUsuario = checaOpArititmetico(respostaUsuario, a+"º");
            } else {
                respostaUsuario = checaOpRelacional(respostaUsuario, a+"º");
            }
        }

        System.out.println("Lexema |          Token");
        System.out.println("---------------------------------");
        for (int i=0;i<lexema.size();i++) {
            System.out.print(lexema.get(i));
            System.out.print("     | ");
            System.out.println(token.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    public static String checaIdentificador (String respostaUsuario, String qtd) {
        if (Character.isLetter(respostaUsuario.charAt(0))){
            token.add(token.size(), "Identificador " + identificador);
            lexema.add(lexema.size(), respostaUsuario.charAt(0));
            identificador ++;
            return respostaUsuario.substring(1, respostaUsuario.length());
        } else {
            System.out.println("Erro. Esperava-se um Identificador como " + qtd + " tipo token.");
            System.exit(1);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String checaOpRelacional (String respostaUsuario, String qtd) {
        for (int x=0; x < 6; x++) {
            if (respostaUsuario.substring(0, opRelacional[x].length()).equals(opRelacional[x]) ) {
                token.add(token.size(), "Operador Relacional");
                lexema.add(lexema.size(), opRelacional[x]);
                return respostaUsuario.substring(opRelacional[x].length(), respostaUsuario.length());
            }
        }
        System.out.println("Erro. Esperava-se um Operador Relacional como " + qtd + " tipo token.");
        System.exit(1);
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String checaOpArititmetico (String respostaUsuario, String qtd) {
        for (int x=0; x < 7; x++) {
            if (respostaUsuario.substring(0, opArititmetico[x].length()).equals(opArititmetico[x]) ) {
                token.add(token.size(), "Operador Aritmetico");
                lexema.add(lexema.size(), opArititmetico[x]);
                return respostaUsuario.substring(opArititmetico[x].length(), respostaUsuario.length());
            }
        }
        System.out.println("Erro. Esperava-se um Operador Aritmetico como " + qtd + " tipo token.");
        System.exit(1);
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String checaNumero (String respostaUsuario, String qtd) {
        try {
            if (Character.isDigit(respostaUsuario.charAt(0))){
                int i = 1;
                if (respostaUsuario.length() != 1) {
                    while (Character.isDigit(respostaUsuario.charAt(i)) || respostaUsuario.charAt(i) == '.' || respostaUsuario.charAt(i) == ',') {
                        i++;
                        if (respostaUsuario.length() == 1) {
                            break;
                        }
                    }
                }
                token.add(token.size(), "Numero");
                lexema.add(lexema.size(), respostaUsuario.substring(0, i));
                return respostaUsuario.substring(i, respostaUsuario.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro. Esperava-se um Numero como " + qtd + " tipo token.");
            System.exit(1);
        }
        return null;
    }
}