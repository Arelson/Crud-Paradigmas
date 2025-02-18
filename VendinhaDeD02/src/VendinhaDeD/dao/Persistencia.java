package VendinhaDeD.dao;
import java.util.List;

public interface Persistencia<T> {
    void salvar(List<T> lista);
    List<T> carregar();
}

