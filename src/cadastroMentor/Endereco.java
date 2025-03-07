package cadastroMentor;

public class Endereco {
    private String rua;
    private String cidade;
    private String estado;

    // Construtor padrão
    public Endereco() {}

    // Construtor parametrizado
    public Endereco(String rua, String cidade, String estado) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Construtor de cópia
    public Endereco(Endereco outroEndereco) {
        this.rua = outroEndereco.rua;
        this.cidade = outroEndereco.cidade;
        this.estado = outroEndereco.estado;
    }

    // Getters e Setters
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return rua + ", " + cidade + ", " + estado;
    }
}