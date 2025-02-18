package VendinhaDeD.dao;
import VendinhaDeD.model.Aventureiro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AventureiroDAO implements Persistencia<Aventureiro> {
    private static final String ARQUIVO = "aventureiros.dat";

    @Override
    public void salvar(List<Aventureiro> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
	@SuppressWarnings("unchecked")
	@Override
    public List<Aventureiro> carregar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Aventureiro>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

