/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.sd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author renan
 */
public class ApiExecutar {

    public static void main(String[] args) throws IOException {

        /* Listar todos os Poemas
        ArrayList lista = ApiAcessar.verPoemas();
        int i = 0;
        
        while (!lista.isEmpty()){
            String numero = Integer.toString(i);
            System.out.println(numero + " - " + lista.remove(0));
            i++;
        }
         */
        //ApiAcessar.traduzirPoema();
        /*
        // Selecionar o poema da lista
        String texto = ApiAcessar.selecionarPoema("o-me-o-life");
        System.out.println(texto);
        
        // Tradução dos primeiros 79 carecteres do poema
        String textocortado = texto.substring(0, 74);
        System.out.println(textocortado);
        System.out.println(ApiAcessar.traduzirPoema(textocortado));
         */
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        do {
            System.out.println("## Escolha uma das opções abaixo ##");
            System.out.println("Opção 1 - Ler poema");
            System.out.println("Opção 2 - Imprime todos os poemas cadastradas");
            System.out.println("Opção 3 - Traduzir o titulo do poema (Nao funciona)");
            System.out.println("Opção 4 - Tradução dos primeiros 79 carecteres do poema");
            System.out.println("Opção 0 - Sair do programa");
            System.out.println("_______________________");

            System.out.print("Digite aqui sua opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 1) {
                System.out.print("Digite o nome do poema: ");
                String poema = sc.nextLine();
                String texto = ApiAcessar.selecionarPoema(poema);
                System.out.println(texto);

            }

            if (opcao == 2) {
                ArrayList lista = ApiAcessar.verPoemas();
                int i = 0;

                while (!lista.isEmpty()) {
                    String numero = Integer.toString(i);
                    System.out.println(numero + " - " + lista.remove(0));
                    i++;
                }
            }
            if (opcao == 3) {
                System.out.print("Digite o código do poema: ");
                String poema = sc.nextLine();
                // o-me-o-life
                System.out.println("Codigo do Poema: "+poema);
                System.out.println(ApiAcessar.traduzirTituloPoema(poema));

            } else if (opcao == 4) {
                System.out.print("Digite o nome do poema: ");
                String poema = sc.nextLine();
                String texto = ApiAcessar.selecionarPoema(poema);
                String textocortado = texto.substring(0, 74);
                System.out.println("Inglês: "+textocortado);
                System.out.println("Português: "+ApiAcessar.traduzirPoema(textocortado));
            }
        } while (opcao != 0);

        sc.close();

    }
}
