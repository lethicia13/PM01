package cadastroMentor;

public class Mentor extends Pessoa implements Autenticavel {
    private String formacao;
    private int anosExperiencia;
    private TipoMentor tipo;
    private boolean ativo;
    protected Endereco endereco;

    // Construtores
    public Mentor() {
        super();
        this.ativo = true;
    }

    public Mentor(String nome, String email, String formacao, int anosExperiencia,
                  Endereco endereco, TipoMentor tipo, String senha) {
        super(nome, email, senha);
        this.formacao = formacao;
        this.anosExperiencia = anosExperiencia;
        this.tipo = tipo;
        this.ativo = true;
        this.endereco = new Endereco(endereco);
    }

    // Construtor de cópia
    public Mentor(Mentor outroMentor) {
        super(outroMentor);
        this.formacao = outroMentor.formacao;
        this.anosExperiencia = outroMentor.anosExperiencia;
        this.tipo = outroMentor.tipo;
        this.ativo = outroMentor.ativo;
        this.endereco = new Endereco(outroMentor.endereco);
    }

    // Implementação da interface
    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Getters e Setters
    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }

    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public TipoMentor getTipo() { return tipo; }
    public void setTipo(TipoMentor tipo) { this.tipo = tipo; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nEmail: " + email + "\nStatus: " + (ativo ? "Ativo" : "Desativado")
                + "\nFormação: " + formacao + "\nAnos de Experiência: " + anosExperiencia
                + "\nEndereço: " + endereco + "\nTipo: " + tipo;
    }
}