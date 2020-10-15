package Geradores;

import java.util.ArrayList;
import java.util.List;
import myTools.Ferramentas;

public class GerarClasseMain {

    String projetoDestino;
    String nomeClasse;

    public GerarClasseMain(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseMain();
    }

    private void gerarClasseMain() {
        Ferramentas ferramentas = new Ferramentas();

        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");

        List<String> codigoGerado = new ArrayList<>();

        codigoGerado.add("package Main;");

        codigoGerado.add("public class Main {\n"
                + "\n"
                + "    /**\n"
                + "     * @param args the command line arguments\n"
                + "     */\n"
                + "    public static void main(String[] args) {\n"
                + "       \n"
                + "        \n"
                + "        "+nomeClasse + "GUI "+nomeClasse.toLowerCase() + "gui = new "+nomeClasse + "GUI();\n"
                + "\n"
                + "        \n"
                + "  \n"
                + "        \n"
                + "    }\n"
                + "\n"
                + "}");

        String cc = projetoDestino + "/src/Main/Main.java";
        System.out.println("Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc, codigoGerado);

        //terminou a classe de entidade
    }

}
