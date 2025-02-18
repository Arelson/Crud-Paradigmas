package VendinhaDeD.dao;

import VendinhaDeD.model.Missao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoDAO {
    private static final String ARQUIVO = "src/missao.txt";

    // Carrega a lista de missoes do arquivo de texto
    public List<Missao> carregar() {
        List<Missao> missoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";"); // Supondo que os dados sao separados por ";"
                if (dados.length == 3) { // Verifica se a linha contam os dados corretos
                    String titulo = dados[0];
                    String descricao = dados[1];
                    int nivelRequestdo = Integer.parseInt(dados[2]); // Converte o nivel para int

                    // Cria o objeto Missao com todos os atributos
                    Missao missao = new Missao(titulo, descricao, nivelRequestdo);
                    missoes.add(missao);
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo nao encontrado ou erro ao ler o arquivo. Criando novo arquivo.");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter nivel requerido para inteiro. Verifique o formato do arquivo.");
        }
        return missoes;
    }

    // Salva uma nova missao no arquivo de texto
    public void salvar(Missao missao) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha = missao.getTitulo() + ";" + missao.getDescricao() + ";" + missao.getNivelRequerido();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Atualiza a lista de missoes no arquivo de texto
    public void atualizar(List<Missao> missoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Missao missao : missoes) {
                String linha = missao.getTitulo() + ";" + missao.getDescricao() + ";" + missao.getNivelRequerido();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Exclui uma missoo com base no titulo
    public void excluir(String titulo) {
        List<Missao> missoes = carregar();
        missoes.removeIf(m -> m.getTitulo().equals(titulo));
        atualizar(missoes);
    }
}