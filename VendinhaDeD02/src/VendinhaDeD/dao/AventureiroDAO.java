package VendinhaDeD.dao;
import VendinhaDeD.model.Aventureiro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AventureiroDAO implements Persistencia<Aventureiro> {
    private static final String ARQUIVO = "avent.dat";

    @SuppressWarnings("unchecked")
	public List<Aventureiro> carregar() {
        List<Aventureiro> aventureiros = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            aventureiros = (List<Aventureiro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum aventureiro encontrado. Criando novo arquivo.");
        }
        return aventureiros;
    }

    public void salvar(Aventureiro aventureiro) {
        List<Aventureiro> aventureiros = carregar();
        aventureiros.add(aventureiro);
        atualizar(aventureiros);
    }

    public void atualizar(List<Aventureiro> aventureiros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(aventureiros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String login) {
        List<Aventureiro> aventureiros = carregar();
        aventureiros.removeIf(a -> a.getLogin().equals(login));
        atualizar(aventureiros);
    }
}


