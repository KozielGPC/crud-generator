/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import java.util.ArrayList;
import java.util.List;
import myTools.Ferramentas;

/**
 *
 * @author radames
 */
public class GerarClasseDeControle {
    
    String projetoDestino;
    String nomeClasse;
    
    public GerarClasseDeControle(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseControle();
    }
    
    private void gerarClasseControle() {
        Ferramentas ferramentas = new Ferramentas();
        
        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");
        
        List<String> codigoGerado = new ArrayList<>();

        //fazer a classe de controle de lista
        codigoGerado.clear();
        codigoGerado.add("package Main;\n"
                + "\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n");

        //import java.util.Date;
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("import java.util.Date;\n"
                        + "import java.text.SimpleDateFormat;");
                break;
            }
        }
        
        codigoGerado.add("public class " + nomeClasse + "Controle {\n");
        codigoGerado.add("  List<" + nomeClasse + "> lista = new ArrayList<>();\n\n");
        codigoGerado.add("public " + nomeClasse + "Controle() { \n }");
        codigoGerado.add("SimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"dd/MM/yyyy\");");
        codigoGerado.add("public String converteDeDateParaString(Date data) {\n"
                + "\n"
                + "       try {\n"
                + "\n"
                + "           return simpleDateFormat.format(data); //converte a data para string\n"
                + "\n"
                + "       } catch (Exception e) {\n"
                + "\n"
                + "           return null;//se algo estiver errado na data, retorne null\n"
                + "\n"
                + "           //tem que tratar o erro na classe que chamou\n"
                + "\n"
                + "       }\n"
                + "\n"
                + "   }");
        int cont = 0;
        String ident_tipo = "";
        String ident_nome = "";
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (cont == 0) {
                ident_nome = aux[1];
                ident_tipo = aux[0];
                
            }
            cont++;
        }
        if (ident_tipo.equals("String")) {
            codigoGerado.add("public " + nomeClasse + " buscar(String chave){\n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "            if (chave.equals(String.valueOf(lista.get(i).get" + ident_nome + "()))) {\n"
                    + "                return lista.get(i);\n"
                    + "            }\n"
                    + "        }\n"
                    + "        return null;\n"
                    + "    }\n");
        } else if (ident_tipo.equals("int")) {
            codigoGerado.add("public " + nomeClasse + " buscar(int chave){\n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "            if (chave == lista.get(i).get" + ident_nome + "()) {\n"
                    + "                return lista.get(i);\n"
                    + "            }\n"
                    + "        }\n"
                    + "        return null;\n"
                    + "    }\n");
            
        } else if (ident_tipo.equals("long")) {
            codigoGerado.add("public " + nomeClasse + " buscar(long chave){\n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "            if (chave == lista.get(i).get" + ident_nome + "()) {\n"
                    + "                return lista.get(i);\n"
                    + "            }\n"
                    + "        }\n"
                    + "        return null;\n"
                    + "    }\n");
            
        } else if (ident_tipo.equals("double")) {
            codigoGerado.add("public " + nomeClasse + " buscar(double chave){\n"
                    + "        for (int i = 0; i < lista.size(); i++) {\n"
                    + "            if (chave == lista.get(i).get" + ident_nome + "()) {\n"
                    + "                return lista.get(i);\n"
                    + "            }\n"
                    + "        }\n"
                    + "        return null;\n"
                    + "    }\n");
        }
        
        codigoGerado.add("public void inserir(" + nomeClasse + " " + nomeClasse.toLowerCase() + "){\n"
                + "    lista.add(" + nomeClasse.toLowerCase() + ");\n"
                + "    }\n");
        
        codigoGerado.add("void alterar(" + nomeClasse + " " + nomeClasse.toLowerCase() + "Original, " + nomeClasse + " " + nomeClasse.toLowerCase() + "Alterado){\n"
                + "    lista.set(lista.indexOf(" + nomeClasse.toLowerCase() + "Original), " + nomeClasse.toLowerCase() + "Alterado);\n"
                + "    }\n");
        
        codigoGerado.add("");
        
        codigoGerado.add("public List<String> listar() {\n"
                + "List<String> ls = new ArrayList<>();\n"
        );
        codigoGerado.add("for (int i = 0; i < lista.size(); i++) {\n"
                + "          ls.add(\"\"");
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("+ converteDeDateParaString(lista.get(i).get" + aux[1] + "()) + \";\"\n"
            );
            }
            else{
                codigoGerado.add("+ lista.get(i).get" + aux[1] + "() + \";\"\n"
            );
            }
            
            
        }
        codigoGerado.add(");\n"
                + "\n}" + "\n return ls;" + "\n}");
        
        codigoGerado.add("public void excluir(" + nomeClasse + " " + nomeClasse.toLowerCase() + "){\n"
                + "        lista.remove(" + nomeClasse.toLowerCase() + ");\n"
                + "    }\n"
                + "}");
        String cc = projetoDestino + "/src/Main/";
        System.out.println("Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc + nomeClasse + "Controle.java", codigoGerado);
    }
}
