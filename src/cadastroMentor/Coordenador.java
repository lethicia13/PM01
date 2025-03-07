package cadastroMentor;

public class Coordenador extends Pessoa implements Autenticavel {
    private String senha;

    public Coordenador(String nome, String email, Endereco endereco, String senha) {
        super(nome, email, endereco);
        this.senha = senha;
    }

    // Implementação da interface
    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Implementação do metodo abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Coordenador: " + nome + "\nEmail: " + email);
    }

    // Getters e Setters
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}