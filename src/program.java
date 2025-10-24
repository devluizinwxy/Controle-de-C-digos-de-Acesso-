public class program {
    public static void main(String[] args) {

        // Cria o primeiro gerenciador e gera alguns códigos
        GerenciadorDeCodigos gerenciador = new GerenciadorDeCodigos();
        gerenciador.gerarNovosCodigos(3, CodigoVisitante.class);
        gerenciador.gerarNovosCodigos(2, CodigoTerceirizado.class);

        // Salva tudo num arquivo
        gerenciador.salvarCodigos("codigos.txt");
        System.out.println(" Códigos salvos com sucesso!");

        // Cria um novo gerenciador e carrega os códigos do arquivo
        GerenciadorDeCodigos novoGerenciador = new GerenciadorDeCodigos();
        novoGerenciador.carregaCodigos("codigos.txt");
        System.out.println(" Códigos carregados com sucesso!");

        // Busca e exibe dois códigos de visitante
        CodigoAcesso cod1 = novoGerenciador.buscar(CodigoVisitante.class);
        System.out.println("Primeiro código encontrado: " + cod1.getCodigo());
        cod1.usar(); // marca como usado

        CodigoAcesso cod2 = novoGerenciador.buscar(CodigoVisitante.class);
        System.out.println("Segundo código (não usado): " + cod2.getCodigo());
    }
}
