package VendinhaDeD.model;

public abstract class usuario implements altenticavel{
    private String nome;
    private String login;
    private String senha;

    public usuario(String nome, String login, String senha) {
    	this.nome = nome;
    	this.login = login;
    	this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean autenticar(String senha) {
    	return this.senha.equals(senha);
    }
}

