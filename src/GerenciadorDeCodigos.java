import java.io.IOException;                       // Para tratar exceções de entrada/saída
import java.nio.charset.StandardCharsets;        // Define a codificação de caracteres (UTF-8)
import java.nio.file.Files;                       // Para ler e escrever arquivos
import java.nio.file.Path;                        // Representa o caminho do arquivo
import java.util.ArrayList;                       // Lista dinâmica para armazenar códigos
import java.util.List;                            // Interface List
import java.util.Random;                          // Para gerar números aleatórios

// Classe responsável por gerenciar todos os códigos criados
// Ela armazena, gera, busca, valida, marca como usado e salva códigos
public class GerenciadorDeCodigos {

    // Lista que guarda todos os códigos (visitantes e terceirizados)
    private List<CodigoAcesso> codigos;

    // Construtor: inicializa a lista de códigos
    public GerenciadorDeCodigos() {
        this.codigos = new ArrayList<>();
    }

    // Adiciona um novo código à lista
    // Primeiro verifica se o código já existe para evitar duplicatas
    public void adicionar(CodigoAcesso novo) {
        for (CodigoAcesso c : codigos) {
            if (c.getCodigo().equals(novo.getCodigo()))
                throw new IllegalArgumentException("Esse codigo ja existe");
        }
        codigos.add(novo);
    }

    // Busca o primeiro código disponível de um tipo específico (visitante ou terceirizado)
    // Retorna null se não encontrar nenhum disponível
    public CodigoAcesso buscar(Class tipo) {
        for (CodigoAcesso c : codigos) {
            if (tipo.isInstance(c)) {   // Verifica se o código é do tipo solicitado
                if (!c.isUsado()) {      // Só retorna se não tiver sido usado
                    return c;
                }
            }
        }
        return null;
    }

    // Gera um novo código único para o tipo informado
    public String gerarNovoCodigo(Class tipo) {
        if (tipo == CodigoVisitante.class) {
            boolean verificar = false; // Flag para garantir que o código seja único
            Random rand = new Random();
            String codigoVisitanteGerados = null;

            while (!verificar) {
                StringBuilder codigoVisitanteGerado = new StringBuilder();

                // Parte fixa do código
                codigoVisitanteGerado.append("VIS-");

                // Gera 3 letras aleatórias
                for (int i = 0; i < 3; i++) {
                    char l = (char) ('A' + rand.nextInt(26));
                    codigoVisitanteGerado.append(l);
                }

                int soma = 0;
                // Gera 4 números aleatórios e calcula soma para dígito verificador
                for (int i = 0; i < 4; i++) {
                    int num = rand.nextInt(10);
                    codigoVisitanteGerado.append(num);
                    soma += num;
                }

                // Calcula dígito verificador (soma % 10)
                int x = soma % 10;
                codigoVisitanteGerado.append("-").append(x);

                // Transforma StringBuilder em String
                codigoVisitanteGerados = codigoVisitanteGerado.toString();

                // Verifica se o código gerado já existe
                verificar = true;
                for (CodigoAcesso c : codigos) {
                    if (c.getCodigo().equals(codigoVisitanteGerados)) {
                        verificar = false; // Se já existe, gera outro
                        break;
                    }
                }
            }

            return codigoVisitanteGerados;
        }

        if (tipo == CodigoTerceirizado.class) {
            boolean verificar = false;
            Random random = new Random();
            String codigoTerceirizadoGerados = null;

            while (!verificar) {
                StringBuilder codigoTerceirizadoGerado = new StringBuilder();

                // Parte fixa do código
                codigoTerceirizadoGerado.append("TER-");

                int soma = 0;
                // Gera 3 números aleatórios
                for (int i = 0; i < 3; i++) {
                    int num = random.nextInt(10);
                    codigoTerceirizadoGerado.append(num);
                }

                codigoTerceirizadoGerado.append("-");

                // Gera 3 letras aleatórias e soma seus valores para dígito verificador
                for (int i = 0; i < 3; i++) {
                    char l = (char) ('A' + random.nextInt(26));
                    soma += l;
                    codigoTerceirizadoGerado.append(l);
                }

                int y = soma % 10; // dígito verificador
                codigoTerceirizadoGerado.append("-").append(y);

                codigoTerceirizadoGerados = codigoTerceirizadoGerado.toString();

                // Verifica se o código já existe
                verificar = true;
                for (CodigoAcesso c : codigos) {
                    if (c.getCodigo().equals(codigoTerceirizadoGerados)) {
                        verificar = false;
                        break;
                    }
                }
            }

            return codigoTerceirizadoGerados;
        }

        return null; // Retorna null se o tipo não for reconhecido
    }

    // Gera múltiplos códigos de um tipo e adiciona à lista
    public void gerarNovosCodigos(int qtd, Class tipo) {
        if (tipo == CodigoVisitante.class) {
            for (int i = 0; i < qtd; i++) {
                CodigoVisitante codigoVisitante = new CodigoVisitante(gerarNovoCodigo(tipo));
                adicionar(codigoVisitante);
            }
        } else if (tipo == CodigoTerceirizado.class) {
            for (int i = 0; i < qtd; i++) {
                CodigoTerceirizado codigoTerceirizado = new CodigoTerceirizado(gerarNovoCodigo(tipo));
                adicionar(codigoTerceirizado);
            }
        }
    }

    // Carrega códigos de um arquivo
    public void carregaCodigos(String caminho) {
        try {
            // Lê todas as linhas do arquivo
            List<String> linhas = Files.readAllLines(Path.of(caminho), StandardCharsets.UTF_8);

            for (String linha : linhas) {
                if (linha != null && !linha.isBlank()) {
                    try {
                        // Identifica se é código de visitante
                        if (linha.startsWith("VIS-")) {
                            CodigoVisitante codigoVisitante = new CodigoVisitante(linha);
                            if (codigoVisitante.isValido()) {
                                adicionar(codigoVisitante);
                            }
                        }

                        // Identifica se é código terceirizado
                        if (linha.startsWith("TER-")) {
                            CodigoTerceirizado codigoTerceirizado = new CodigoTerceirizado(linha);
                            if (codigoTerceirizado.isValido()) {
                                adicionar(codigoTerceirizado);
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        // Ignora códigos inválidos, mas mostra uma mensagem
                        System.out.println("Codigo inválido ignorado " + linha + " " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            // Trata erros de leitura do arquivo
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Salva todos os códigos em um arquivo
    public void salvarCodigos(String caminho) {
        List<String> linha = new ArrayList<>();

        // Converte cada objeto em String para gravar no arquivo
        for (CodigoAcesso codigoAcesso : codigos) {
            linha.add(codigoAcesso.getCodigo());
        }

        try {
            // Grava todas as linhas no arquivo em UTF-8
            Files.write(Path.of(caminho), linha, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Trata erros de escrita
            System.out.println("Erro ao salvar codigo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
