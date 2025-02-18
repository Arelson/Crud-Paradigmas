package VendinhaDeD.dao;

import VendinhaDeD.model.Aventureiro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AventureiroDAO {
    private static final String ARQUIVO = "src/avent.txt";

    // Carrega a lista de aventureiros do arquivo de texto
    public List<Aventureiro> carregar() {
        List<Aventureiro> aventureiros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";"); // Supondo que os dados são separados por ";"
                if (dados.length == 5) { // Verifica se a linha contém os dados corretos
                    String nome = dados[0];
                    String login = dados[1];
                    String senha = dados[2];
                    int nivel = Integer.parseInt(dados[3]); // Converte o nível para int
                    String classe = dados[4];

                    // Cria o objeto Aventureiro com todos os atributos
                    Aventureiro aventureiro = new Aventureiro(nome, login, senha, nivel, classe);
                    aventureiros.add(aventureiro);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado ou erro ao ler o arquivo. Criando novo arquivo.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter nível para inteiro. Verifique o formato do arquivo.");
        }
        return aventureiros;
    }

    // Salva um novo aventureiro no arquivo de texto
    public void salvar(Aventureiro aventureiro) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha = aventureiro.getNome() + ";" + aventureiro.getLogin() + ";" +   aventureiro.getSenha() + ";" + aventureiro.getNivel() + ";" + aventureiro.getClasse();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Atualiza a lista de aventureiros no arquivo de texto
    public void atualizar(List<Aventureiro> aventureiros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Aventureiro aventureiro : aventureiros) {
            	String linha = aventureiro.getNome() + ";" + aventureiro.getLogin() + ";" +   aventureiro.getSenha() + ";" + aventureiro.getNivel() + ";" + aventureiro.getClasse();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Exclui um aventureiro com base no login
    public void excluir(String login) {
        List<Aventureiro> aventureiros = carregar();
        aventureiros.removeIf(a -> a.getLogin().equals(login));
        atualizar(aventureiros);
    }

}