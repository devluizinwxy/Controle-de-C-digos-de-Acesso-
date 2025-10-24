// Classe que representa um código terceirizado e implementa a interface CodigoAcesso
public class CodigoTerceirizado implements CodigoAcesso {

    // Armazena o valor do código
    private String codigo;

    // Indica se o código já foi utilizado
    private boolean usado;

    // Construtor que recebe o código como parâmetro
    public CodigoTerceirizado(String codigo) {
        // Atribui o código à variável de instância
        this.codigo = codigo;

        // Inicializa o código como não usado
        this.usado = false;
    }

    // Método da interface que retorna o código
    @Override
    public String getCodigo() {
        return codigo;
    }

    // Método da interface que marca o código como usado
    @Override
    public void usar() {
        this.usado = true;
    }

    // Método da interface que verifica se o código é válido
    @Override
    public boolean isValido() {

        // Verifica se o código é nulo ou vazio
        if (codigo == null || codigo.isBlank())
            return false;

        // Verifica se o código segue o padrão TER-999-AAA-Y
        if (!codigo.matches("^TER-\\d{3}-[A-Z]{3}-\\d$"))
            return false;

        // Inicializa a soma dos valores ASCII das letras AAA
        int soma = 0;

        // Loop para percorrer as letras AAA no código (posição 7,8,9)
        for (int i = 7; i <= 9; i++) {
            // charAt(i) retorna o caractere na posição i
            // Quando somado a um int, o char é automaticamente convertido para seu valor ASCII
            soma += codigo.charAt(i);
        }

        // Calcula o dígito verificador a partir da soma das letras mod 10
        int valor = soma % 10;

        // Pega o último caractere do código (o dígito verificador Y) e converte para int
        int verificarDigito = Character.getNumericValue(codigo.charAt(codigo.length() - 1));

        // Compara o dígito verificador calculado com o dígito informado no código
        if (valor != verificarDigito)
            return false;

        // Se passou por todas as validações, o código é válido
        return true;
    }

    // Método da interface que verifica se o código já foi utilizado
    @Override
    public boolean isUsado() {
        return usado;
    }

}
