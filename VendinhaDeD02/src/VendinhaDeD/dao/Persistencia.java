package VendinhaDeD.dao;
import java.util.List;

public interface Persistencia<T> {
	List<T> carregar();
    void salvar(T entidade);
    void atualizar(List<T> entidades);
    void excluir(String n);
}

