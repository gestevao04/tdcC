package com.trabalho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class programa1 {
/* Construa o analisador sintático de expressões do 4º Exercício
 * (identificador de erros da expressão do enunciado de atribuição com três
 * operandos e dois operadores, além do de atribuição).
 */

    @SuppressWarnings("resource")
    public static void main (String[] args) {
        List<Integer> posicoes   = new ArrayList<Integer>();
        Integer       posicao    = 0;

        System.out.println("Digite a frase:");
        String respostaUsuario    = new Scanner(System.in).nextLine();
        respostaUsuario           = respostaUsuario.replaceAll("\\s+","");
        String respostaUsuarioAux = respostaUsuario;

        while ( respostaUsuario.length() != 0) {
            if (Character.isDigit(respostaUsuario.charAt(0)) || Character.isAlphabetic(respostaUsuario.charAt(0))) {
                respostaUsuario = respostaUsuario.substring(1, respostaUsuario.length());
                posicao++;
            } else {
                respostaUsuario = respostaUsuario.substring(1, respostaUsuario.length());
                posicoes.add(posicao);
                posicao++;
            }
        }

        if ( posicoes.size() != 0 ) {
        	int i = 1;
            for (int x = posicoes.size(); x>0; x--) {
                if (posicoes.get(x-1) != respostaUsuarioAux.length()) {
                    for (int posInit = posicoes.get(x-1); posInit < (respostaUsuarioAux.length()-i); posInit++) {
                        char[] c = respostaUsuarioAux.toCharArray();

                        char temp = c[posInit];
                        c[posInit] = c[posInit+1];
                        c[posInit+1] = temp;

                        String swappedString = new String(c);

                        System.out.println(swappedString);

                        respostaUsuarioAux = swappedString;
                    }
                }
                i++;
            }
        }
    }
}