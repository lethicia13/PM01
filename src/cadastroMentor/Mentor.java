package cadastroMentor;

public class Mentor extends Pessoa implements Autenticavel {
    private String formacao;
    private int anosExperiencia;
    private TipoMentor tipo;
    private String senha;
    private boolean ativo;

    // Construtores
    public Mentor() {
        super();
        this.ativo = true;
    }

    public Mentor(String nome, String email, String formacao, int anosExperiencia,
                  Endereco endereco, TipoMentor tipo, String senha) {
        super(nome, email, endereco);
        this.formacao = formacao;
        this.anosExperiencia = anosExperiencia;
        this.tipo = tipo;
        this.senha = senha;
        this.ativo = true;
    }

    // Construtor de cópia
    public Mentor(Mentor outroMentor) {
        super(outroMentor);
        this.formacao = outroMentor.formacao;
        this.anosExperiencia = outroMentor.anosExperiencia;
        this.tipo = outroMentor.tipo;
        this.senha = outroMentor.senha;
        this.ativo = outroMentor.ativo;
    }

    // Implementação da interface
    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Implementação do metodo abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Mentor: " + nome + "\nEmail: " + email + "\nFormação: " + formacao);
    }

    // Getters e Setters
    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }

    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public TipoMentor getTipo() { return tipo; }
    public void setTipo(TipoMentor tipo) { this.tipo = tipo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nEmail: " + email + "\nStatus: " + (ativo ? "Ativo" : "Desativado")
                + "\nFormação: " + formacao + "\nAnos de Experiência: " + anosExperiencia
                + "\nEndereço: " + endereco + "\nTipo: " + tipo;
    }
}