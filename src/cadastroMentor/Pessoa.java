package cadastroMentor;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected Endereco endereco;

    // Construtor padrão
    public Pessoa() {}

    // Construtor parametrizado
    public Pessoa(String nome, String email, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = new Endereco(endereco);
    }

    // Construtor de cópia
    public Pessoa(Pessoa outraPessoa) {
        this.nome = outraPessoa.nome;
        this.email = outraPessoa.email;
        this.endereco = new Endereco(outraPessoa.endereco);
    }

    // Metodo abstrato para polimorfismo
    public abstract void exibirDetalhes();

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}