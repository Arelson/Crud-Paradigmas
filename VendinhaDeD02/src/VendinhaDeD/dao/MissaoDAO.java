package VendinhaDeD.dao;
import VendinhaDeD.model.Missao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoDAO implements Persistencia<Missao> {
    private static final String ARQUIVO = "missao.dat";

    @SuppressWarnings("unchecked")
	@Override
    public List<Missao> carregar() {
        List<Missao> missoes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            missoes = (List<Missao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhuma missão encontrada. Criando novo arquivo.");
        }
        return missoes;
    }

    public void salvar(Missao missao) {
        List<Missao> missoes = carregar();
        missoes.add(missao);
        atualizar(missoes);
    }

    public void atualizar(List<Missao> missoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(missoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String titulo) {
        List<Missao> missoes = carregar();
        missoes.removeIf(m -> m.getTitulo().equals(titulo));
        atualizar(missoes);
    }

}
