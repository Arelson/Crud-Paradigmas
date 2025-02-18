package VendinhaDeD.dao;
import VendinhaDeD.model.Missao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoDAO implements Persistencia<Missao> {
    private static final String ARQUIVO = "missoes.dat";

    @Override
    public void salvar(List<Missao> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Missao> carregar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Missao>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
