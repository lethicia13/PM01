package cadastroMentor;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String senha;

    // Construtor padrão
    public Pessoa() {}

    // Construtor parametrizado
    public Pessoa(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Construtor de cópia
    public Pessoa(Pessoa outraPessoa) {
        this.nome = outraPessoa.nome;
        this.email = outraPessoa.email;
        this.senha = outraPessoa.senha;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha(){ return senha; }
    public void setSenha(String senha){ this.senha = senha; }
}