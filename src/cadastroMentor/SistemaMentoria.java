package cadastroMentor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaMentoria {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static Coordenador coordenadorPadrao = new Coordenador(
            "Admin", "admin@uniamerica.br",
            "admin123"
    );

    public static void main(String[] args) {
        pessoas.add(coordenadorPadrao);
        int opcao;
        do {
            exibirMenu();
            opcao = Validacao.lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    cadastrarMentor();
                    break;
                case 2:
                    listarMentores();
                    break;
                case 3:
                    modificarMentor();
                    break;
                case 4:
                    desativarConta();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Mentoria ===");
        System.out.println("1 - Cadastrar Mentor");
        System.out.println("2 - Listar Mentores");
        System.out.println("3 - Editar Mentor");
        System.out.println("4 - Desativar Conta");
        System.out.println("5 - Sair");
    }

    private static void cadastrarMentor() {
        String nome = Validacao.lerString("Nome Completo: ");
        String email = Validacao.lerEmail("Email: ");
        String formacao = Validacao.lerString("Formação: ");
        int anos = Validacao.lerInteiro("Anos de Experiência: ");
        String senha = Validacao.lerString("Senha: ");

        String rua = Validacao.lerString("Endereço: ");
        String cidade = Validacao.lerString("Cidade: ");
        String estado = Validacao.lerString("Estado: ");
        Endereco endereco = new Endereco(rua, cidade, estado);

        TipoMentor tipo = escolherTipoMentor();

        Mentor mentor = new Mentor(nome, email, formacao, anos, endereco, tipo, senha);
        pessoas.add(mentor);
        System.out.println("Mentor cadastrado!");
    }

    private static TipoMentor escolherTipoMentor() {
        System.out.println("Escolha o tipo de mentor:");
        for (TipoMentor tipo : TipoMentor.values()) {
            System.out.println((tipo.ordinal() + 1) + " - " + tipo);
        }
        int escolha = Validacao.lerInteiro("Opção: ") - 1;
        return TipoMentor.values()[escolha];
    }

    // Metodo de listagem com menu de filtros
    private static void listarMentores() {
        if (!autenticarCoordenador()) {
            System.out.println("Acesso negado! Apenas coordenadores podem listar mentores.");
            return;
        }

        if (pessoas.isEmpty()) {
            System.out.println("Nenhum cadastro encontrado.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma opção de listagem:");
        System.out.println("1. Todos os mentores");
        System.out.println("2. Mentores ativos");
        System.out.println("3. Filtrar por curso");
        System.out.println("4. Filtrar por tipo de mentor");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarTodosMentores();
                break;
            case 2:
                listarMentoresAtivos();
                break;
            case 3:
                System.out.print("Digite o curso: ");
                String curso = scanner.nextLine();
                listarMentoresPorCurso(curso);
                break;
            case 4:
                System.out.println("Escolha o tipo de mentor:");
                System.out.println("1. Mentor Formado");
                System.out.println("2. Mentor Experiente");
                System.out.println("3. Mentor Acadêmico");
                System.out.print("Opção: ");
                int tipoOpcao = scanner.nextInt();
                scanner.nextLine();
                listarMentoresPorTipo(tipoOpcao);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarTodosMentores() {
        boolean encontrou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Mentor) {
                System.out.println(pessoa + "\n---------------");
                encontrou = true;
            }
        }
        if(!encontrou){
            System.out.println("Nenhum mentor ativo encontrado.");
        }
    }

    private static void listarMentoresAtivos() {
        boolean encontrou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Mentor mentor && mentor.isAtivo()) {
                System.out.println(mentor + "\n---------------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum mentor ativo encontrado.");
        }
    }

    private static void listarMentoresPorCurso(String curso) {
        boolean encontrou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Mentor mentor && mentor.getFormacao().equalsIgnoreCase(curso)) {
                System.out.println(mentor + "\n---------------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum mentor encontrado para o curso: " + curso);
        }
    }

    private static void listarMentoresPorTipo(int tipoOpcao) {
        if (tipoOpcao < 1 || tipoOpcao > 3) {
            System.out.println("Opção de tipo inválida.");
            return;
        }
        TipoMentor tipoSelecionado = null;
        switch (tipoOpcao) {
            case 1:
                tipoSelecionado = TipoMentor.Formado;
                break;
            case 2:
                tipoSelecionado = TipoMentor.Experiente;
                break;
            case 3:
                tipoSelecionado = TipoMentor.Academico;
                break;
        }
        boolean encontrou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Mentor mentor && mentor.getTipo() == tipoSelecionado) {
                System.out.println(mentor + "\n---------------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum mentor encontrado para o tipo selecionado.");
        }
    }

    private static void modificarMentor() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhum mentor cadastrado.");
            return;
        }

        String email = Validacao.lerString("Digite seu email: ");
        String senha = Validacao.lerString("Digite sua senha: ");

        Mentor mentor = buscarMentorPorEmail(email);
        if (mentor == null || !mentor.autenticar(senha)) {
            System.out.println("Email ou senha incorretos!");
            return;
        }

        System.out.println("Dados atuais:\n" + mentor);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o atual):");

        String novoNome = Validacao.lerString("Novo nome [" + mentor.getNome() + "]: ");
        if (!novoNome.isEmpty()) mentor.setNome(novoNome);

        String novaFormacao = Validacao.lerString("Nova formação [" + mentor.getFormacao() + "]: ");
        if (!novaFormacao.isEmpty()) mentor.setFormacao(novaFormacao);

        String anosStr = Validacao.lerString("Novos anos de experiência [" + mentor.getAnosExperiencia() + "]: ");
        if (!anosStr.isEmpty()) {
            try {
                mentor.setAnosExperiencia(Integer.parseInt(anosStr));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Mantendo o anterior.");
            }
        }

        System.out.println("Novo endereço:");
        Endereco endereco = mentor.getEndereco();
        String novaRua = Validacao.lerString("Rua [" + endereco.getRua() + "]: ");
        if (!novaRua.isEmpty()) endereco.setRua(novaRua);

        String novaCidade = Validacao.lerString("Cidade [" + endereco.getCidade() + "]: ");
        if (!novaCidade.isEmpty()) endereco.setCidade(novaCidade);

        String novoEstado = Validacao.lerString("Estado [" + endereco.getEstado() + "]: ");
        if (!novoEstado.isEmpty()) endereco.setEstado(novoEstado);

        System.out.println("Dados atualizados com sucesso!");
    }

    private static void desativarConta() {
        System.out.println("\n---------------");
        System.out.println("Você é:");
        System.out.println("1 - Coordenador");
        System.out.println("2 - Mentor");
        int escolha = Validacao.lerInteiro("Escolha uma opção: ");

        boolean autenticado = false;
        Pessoa pessoaAlvo = null;

        switch (escolha) {
            case 1:
                if (autenticarCoordenador()) {
                    String email = Validacao.lerString("Email do mentor a desativar: ");
                    pessoaAlvo = buscarMentorPorEmail(email);
                }
                break;
            case 2:
                String email = Validacao.lerString("Seu email: ");
                String senha = Validacao.lerString("Sua senha: ");
                pessoaAlvo = buscarMentorPorEmail(email);
                if (pessoaAlvo != null && ((Mentor) pessoaAlvo).autenticar(senha)) {
                    autenticado = true;
                }
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (pessoaAlvo instanceof Mentor) {
            ((Mentor) pessoaAlvo).setAtivo(false);
            System.out.println("Conta desativada com sucesso!");
        } else {
            System.out.println("Operação não permitida!");
        }
    }

    private static Mentor buscarMentorPorEmail(String email) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Mentor && pessoa.getEmail().equalsIgnoreCase(email)) {
                return (Mentor) pessoa;
            }
        }
        return null;
    }

    private static boolean autenticarCoordenador() {
        String email = Validacao.lerString("Email do Coordenador: ");
        String senha = Validacao.lerString("Senha: ");

        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Coordenador) {
                Coordenador coord = (Coordenador) pessoa;
                if (coord.getEmail().equalsIgnoreCase(email) && coord.autenticar(senha)) {
                    return true;
                }
            }
        }
        return false;
    }
}
