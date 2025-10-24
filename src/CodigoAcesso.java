// Interface CodigoAcesso
// Serve como um "contrato" para qualquer tipo de código de acesso (visitante ou terceirizado)
// Ou seja, qualquer classe que implementar esta interface precisa ter esses métodos
public interface CodigoAcesso {

    // Retorna o código completo
    // Ex: VIS-ABC1234-5 ou TER-123-ABC-4
    String getCodigo();

    // Verifica se o código já foi usado
    // Retorna true se já tiver sido utilizado, false caso contrário
    boolean isUsado();

    // Marca o código como usado
    // Quando um visitante ou terceirizado utiliza o código, este método será chamado
    void usar();

    // Verifica se o código é válido
    // Por exemplo, se o formato está correto e o dígito verificador bate
    // Isso permite checar validade sem lançar exceção
    boolean isValido();
}
