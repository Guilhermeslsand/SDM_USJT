package com.usjt.sdm.tarefa1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("www.google.com.br", 80)) {
            System.out.println("Conectado ao servidor do google no ip: " + socket.getInetAddress()+"\n");

            // Requisição que será enviada para o
            String requisicao = ""
                    + "GET / HTTP/1.1\r\n"
                    + "Host: www.google.com.br\r\n"
                    + "\r\n";

            System.out.println("Requisição enviada para o servidor do goole:");
            System.out.print(requisicao);

            // Variavel para enivar requesição para o servidor
            OutputStream envioServ = socket.getOutputStream();

            // Necessário transformar requisao em um vetor de bytes
            byte[] b = requisicao.getBytes();

            // Escreve o vetor de bytes no "recurso" de envio
            envioServ.write(b);

            // Finalização da escrita
            envioServ.flush();

            // Leitura da reposta do servidor
            System.out.println("Resposta do servidor do google:");
            Scanner sc = new Scanner(socket.getInputStream());
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
            envioServ.close();
            System.out.println("Conexão Encerrada");
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}