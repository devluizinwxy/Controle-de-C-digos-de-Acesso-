// Classe CodigoVisitante implementa a interface CodigoAcesso
// Representa um código de visitante com validação de formato e dígito verificador
public class CodigoVisitante implements CodigoAcesso {

    // Atributo que armazena o código do visitante
    private String codigo;

    // Atributo que indica se o código já foi utilizado
    private boolean usado;

    // Construtor da classe
    // Recebe um código como parâmetro e inicializa os atributos
    public CodigoVisitante(String codigo) {
        // Apenas armazena o código na instância, sem validação
        this.codigo = codigo;

        // Inicializa o código como não utilizado
        this.usado = false;
    }

    // Retorna o código completo do visitante
    @Override
    public String getCodigo() {
        return codigo;
    }

    // Marca o código como utilizado
    @Override
    public void usar() {
        this.usado = true;
    }

    // Verifica se o código já foi utilizado
    @Override
    public boolean isUsado() {
        return usado;
    }

    // Verifica se o código é válido
    @Override
    public boolean isValido() {

        // Verifica se o código é nulo ou contém apenas espaços em branco
        // Se for, o código é considerado inválido
        if (codigo == null || codigo.isBlank())
            return false;

        // Verifica se o código segue o padrão VIS-AAA9999-X
        // "^VIS-[A-Z]{3}\\d{4}-\\d$" corresponde a "VIS-AAA9999-X"
        if (!codigo.matches("^VIS-[A-Z]{3}\\d{4}-\\d$"))
            return false;

        // Inicializa a soma dos 4 números que compõem o código (posição 7 a 10)
        int soma = 0;
        for (int i = 0; i < 4; i++) {
            // charAt(i + 7) pega o dígito na posição correta
            // Character.getNumericValue converte o caractere em número
            soma += Character.getNumericValue(codigo.charAt(i + 7));
        }

        // Calcula o dígito verificador esperado
        int valor = soma % 10;

        // Obtém o último caractere do código (dígito verificador informado)
        int digitoVerificador = Character.getNumericValue(codigo.charAt(codigo.length() - 1));

        // Compara o dígito verificador calculado com o informado
        // Se não coincidir, o código é inválido
        if (valor != digitoVerificador)
            return false;

        // Se passou por todas as validações, o código é considerado válido
        return true;
    }

}
