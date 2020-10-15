package Geradores;

import java.util.ArrayList;
import java.util.List;
import myTools.Ferramentas;

public class GerarClasseDeGUI {

    String projetoDestino;
    String nomeClasse;

    public GerarClasseDeGUI(String projetoDestino, String nomeClasse) {
        this.projetoDestino = projetoDestino;
        this.nomeClasse = nomeClasse;
        gerarClasseGUI();
    }

    private void gerarClasseGUI() {
        Ferramentas ferramentas = new Ferramentas();

        List<String> arquivoBase = ferramentas.abrirArquivo("src/Main/" + nomeClasse + ".txt");
        String coisas_separadas_por_aspas = "";

        String coisas_separadas_por_ponto_e_virgula = "";
        List<String> codigoGerado = new ArrayList<>();
        //fazer a classe de controle de lista
        int quantidade = 0;
        int cont = 0;
        for (String s : arquivoBase) {
            quantidade++;
        }
        for (String s : arquivoBase) {
            String aux[] = s.split(";");

            if (cont == quantidade - 1) {
                coisas_separadas_por_aspas += "\"" + aux[1] + "\"";
            } else {
                coisas_separadas_por_aspas += "\"" + aux[1] + "\",";
            }

            cont++;
        }
        String identificador[] = coisas_separadas_por_aspas.split(",");
        String remover_aspas = "\"";
        String remover_get = "";
        for (int i = 0; i < cont; i++) {

            remover_get += nomeClasse.toLowerCase() + ".get" + identificador[i] + "() + \'-\' +";
            remover_get = remover_get.replace(remover_aspas, "");
            identificador[i] = identificador[i].replace(remover_aspas, "");
        }
        String vetores_inserir = "";
        int i = 0;
        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (i == quantidade - 1) {
                if (aux[0].equals("int")) {
                    vetores_inserir += "Integer.valueOf(aux[" + i + "])";
                } else if (aux[0].equals("String")) {
                    vetores_inserir += "aux[" + i + "]";
                } else if (aux[0].equals("double")) {
                    vetores_inserir += "Double.parseDouble(aux[" + i + "])";
                } else if (aux[0].equals("boolean")) {
                    vetores_inserir += "Boolean.valueOf(aux[" + i + "])";
                } else if (aux[0].equals("Date")) {
                    vetores_inserir += "converteDeStringParaDate(aux[" + i + "])";
                } else if (aux[0].equals("long")) {
                    vetores_inserir += "Long.valueOf(aux[" + i + "])";
                }
            } else if (aux[0].equals("int")) {
                vetores_inserir += "Integer.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("String")) {
                vetores_inserir += "aux[" + i + "], ";
            } else if (aux[0].equals("double")) {
                vetores_inserir += "Double.parseDouble(aux[" + i + "]), ";
            } else if (aux[0].equals("boolean")) {
                vetores_inserir += "Boolean.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("Date")) {
                vetores_inserir += "converteDeStringParaDate(aux[" + i + "]), ";
            } else if (aux[0].equals("long")) {
                vetores_inserir += "Long.valueOf(aux[" + i + "]), ";
            }

            i++;
        }
        codigoGerado.clear();
        codigoGerado.add("package Main;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import static java.awt.Frame.NORMAL;\n"
                + "import java.awt.GridBagConstraints;\n"
                + "import java.awt.GridBagLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.ItemEvent;\n"
                + "import java.awt.event.ItemListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import java.util.Date;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JColorChooser;\n"
                + "import javax.swing.JComboBox;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JTextField;\n"
                + "import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import javax.swing.text.DefaultCaret;\n"
                + "import myTools.Ferramentas;\n"
                + "import myTools.UsarGridBagLayout;\n"
                + "\n");

        codigoGerado.add("public class " + nomeClasse + "GUI extends JFrame {\n");
        codigoGerado.add("    private Container cp;\n"
                + "    GridBagConstraints cons = new GridBagConstraints();\n"
                + "    GridBagLayout GridBagLayout = new GridBagLayout();\n"
                + "    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"dd/MM/yyyy\");\n"
                + "    private CardLayout CardLayout = new CardLayout();\n"
                + "    private BorderLayout BorderLayout = new BorderLayout();\n"
                + "    private JButton btBuscar = new JButton(\"Buscar\");\n"
                + "    private JButton btNBuscar = new JButton(\"Nova busca\");\n"
                + "    private JButton btInserir = new JButton(\"Inserir\");\n"
                + "    private JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    private JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    private JButton btListar = new JButton(\"Listar\");\n"
                + "    private JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    private JButton btCancelar = new JButton(\"Cancelar\");\n"
                + "    private JButton btGravar = new JButton(\"Gravar\");\n"
                + "    private JButton btExcluirTudo = new JButton(\"Excluir tudo\");\n"
                + "    private JButton btCor = new JButton(\"Escolher cor\");\n"
                + "    private JPanel pnNorte = new JPanel(BorderLayout);\n"
                + "    private JPanel pnCentro = new JPanel(GridBagLayout);\n"
                + "    private JPanel pnSul = new JPanel(CardLayout);\n"
                + "    private JPanel pnSulMsg = new JPanel();\n"
                + "    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));\n"
                + "    private JColorChooser colorchooser = new JColorChooser();\n"
                + "    private JPanel pnCard = new JPanel(CardLayout);\n"
                + "    private JPanel pnTudo = new JPanel(new BorderLayout());"
                + "    private JPanel pnNorteNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));\n"
                + "    private JPanel pnNorteSul = new JPanel(GridBagLayout);\n"
                + "\n"
                + "    private Color color;\n"
                + "    \n"
                + "private JCheckBox checkbox = new JCheckBox(\"Cor do layout personalizada\");"
                + "\n"
                + "    JScrollPane scrollList = new JScrollPane();\n"
                + "\n"
                + "    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem\n"
                + "\n"
                + "    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas\n"
                + "\n"
                + "    private boolean inserindo; //esta variável controla se é uma operação de INSERT ou UPDATE no botão salvar\n"
                + "\n"
                + "    private " + nomeClasse + "Controle " + nomeClasse.toLowerCase()
                + "controle = new " + nomeClasse + "Controle(); \n"
                + "    private " + nomeClasse + " " + nomeClasse.toLowerCase() + " = new " + nomeClasse + "(); \n"
                + "\n"
                + "    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea\n"
                + "    Ferramentas fer = new Ferramentas();\n");

        for (String s : arquivoBase) {
            String aux[] = s.split(";");
            if (aux[0].equals("Date")) {
                codigoGerado.add("private JLabel lb" + aux[1]
                        + " = new JLabel (\"" + aux[1] + "\");");
                codigoGerado.add("DateTextField dtf" + aux[1] + " = new DateTextField();");
                codigoGerado.add("Date date" + aux[1] + " = new Date();");
                coisas_separadas_por_ponto_e_virgula += "lb" + aux[1] + ";";
                coisas_separadas_por_ponto_e_virgula += "date" + aux[1] + ";";

            } else if (aux[0].equals("boolean")) {
                codigoGerado.add("private JLabel lb" + aux[1]
                        + " = new JLabel (\"" + aux[1] + "\");");
                codigoGerado.add("private JCheckBox checkbox" + aux[1] + " = new JCheckBox();");
                codigoGerado.add("Date date" + aux[1] + " = new Date();");
                coisas_separadas_por_ponto_e_virgula += "lb" + aux[1] + ";";
                coisas_separadas_por_ponto_e_virgula += "checkbox" + aux[1] + ";";
            } else {
                codigoGerado.add("private JLabel lb" + aux[1]
                        + " = new JLabel (\"" + aux[1] + "\");");
                codigoGerado.add("private JTextField tf" + aux[1]
                        + " = new JTextField (30);");
                coisas_separadas_por_ponto_e_virgula += "lb" + aux[1] + ";";
                coisas_separadas_por_ponto_e_virgula += "tf" + aux[1] + ";";
            }
        }
        String aux[] = coisas_separadas_por_ponto_e_virgula.split(";");
        codigoGerado.add("String[] colunas = new String[]"
                + "{" + coisas_separadas_por_aspas + "};\n"
                + "    String[][] dados = new String[0][" + quantidade + "];");
        codigoGerado.add("DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "    JTable tabela = new JTable(model);");
        codigoGerado.add("    private void setLog(String msg) {\n"
                + "        textAreaMsg.append(msg + \"\\n\");\n"
                + "        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());\n"
                + "    }\n"
                + "\n"
        );
        codigoGerado.add("private void travarTextFields(boolean campo) {\n");

        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
                codigoGerado.add("dtf" + aux3[1] + ".setEnabled(!campo);");
            } else if (aux3[0].equals("boolean")) {
                codigoGerado.add("checkbox" + aux3[1] + ".setEnabled(!campo);");
            } else {
                codigoGerado.add("tf" + aux3[1] + ".setEditable(!campo);");
            }
        }
        codigoGerado.add("if (!campo) {\n"
                + "            tf" + identificador[0] + ".requestFocus();\n"
                + "            tf" + identificador[0] + ".selectAll();\n"
                + "      }\n"
                + "tf" + identificador[0] + ".setEditable(campo);\n"
                + "}");

        codigoGerado.add("private void limparValoresDosAtributos() {\n");
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
            } else if (aux3[0].equals("boolean")) {
                codigoGerado.add("checkbox" + aux3[1] + ".setSelected(false);");
            } else {
                codigoGerado.add("tf" + aux3[1] + ".setText(\"\");");
            }
        }
        codigoGerado.add("}");

        codigoGerado.add("   public Date converteDeStringParaDate(String s) {\n"
                + "\n"
                + "       try {\n"
                + "\n"
                + "           return simpleDateFormat.parse(s);//converte\n"
                + "\n"
                + "       } catch (Exception e) {\n"
                + "\n"
                + "           return null;// se algo estiver errado, retorne null\n"
                + "\n"
                + "       }\n"
                + "\n"
                + "   }");
        codigoGerado.add("    public " + nomeClasse + "GUI() {\n"
                + "        //abrir o arquivo\n"
                + "        btCor.setVisible(false);\n"
                + "        List<String> listaAuxiliar = fer.abrirArquivo(\"" + nomeClasse + ".txt\");\n"
                + "        if (listaAuxiliar != null) {\n");
        codigoGerado.add("            for (int i = 0; i < listaAuxiliar.size(); i++) {\n"
                + "                String aux[] = listaAuxiliar.get(i).split(\";\");\n"
                + "                " + nomeClasse + " c = new " + nomeClasse + "(" + vetores_inserir + ");\n"
                + "                " + nomeClasse.toLowerCase() + "controle.inserir(c);\n"
                + "            }\n"
                + "        }\n");
        codigoGerado.add("        tabela.setEnabled(false);\n"
                + "\n"
                + "\n"
                + "        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);\n"
                + "        scrollMensagem.setViewportView(textAreaMsg);\n"
                + "        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal\n"
                + "\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "\n");
        codigoGerado.add("        cp = getContentPane();\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "        cp.add(pnTudo);\n"
                + "        pnNorte.add(pnNorteNorte, BorderLayout.NORTH);\n"
                + "        pnNorte.add(pnNorteSul, BorderLayout.SOUTH);\n"
                + "        \n"
                + "        pnNorteNorte.add(lb" + identificador[0] + ");\n"
                + "        pnNorteNorte.add(tf" + identificador[0] + ");\n"
                + "        pnNorteNorte.add(btExcluirTudo);\n"
                + "        pnNorteNorte.add(btBuscar);\n"
                + "        pnNorteNorte.add(btNBuscar);\n"
                + "        btNBuscar.setVisible(false);\n"
                + "        pnNorteNorte.add(btInserir);\n"
                + "        pnNorteNorte.add(btAlterar);\n"
                + "        pnNorteNorte.add(btExcluir);\n"
                + "        pnNorteNorte.add(btSalvar);\n"
                + "        pnNorteNorte.add(btCancelar);\n"
                + "        pnNorteNorte.add(btListar);\n"
                + "\n");

        codigoGerado.add("       cons.fill = GridBagConstraints.BOTH;\n"
                + "       cons.gridy = 0;\n"
                + "       cons.gridx = 0;\n"
                + "       pnNorteSul.add(checkbox, cons);\n"
                + "       cons.fill = GridBagConstraints.BOTH;\n"
                + "       cons.gridy = 0;\n"
                + "       cons.gridx = 1;\n"
                + "       pnNorteSul.add(btCor, cons);\n");
        int contador = 0;
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 0;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(lb" + aux3[1] + ", cons);\n");
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 1;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(dtf" + aux3[1] + ", cons);\n"
                        + "dtf" + aux3[1] + ".setText(date" + aux3[1] + ");"
                );
            } else if (aux3[0].equals("boolean")) {
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 0;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(lb" + aux3[1] + ", cons);\n");
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 1;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(checkbox" + aux3[1] + ", cons);\n");

            } else {
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 0;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(lb" + aux3[1] + ", cons);\n");
                codigoGerado.add("cons.fill = GridBagConstraints.BOTH;\n"
                        + "       cons.gridy = " + (contador + 1) + ";\n"
                        + "       cons.gridx = 1;\n"
                        + "       cons.gridwidth = 1;\n"
                        + "       pnCentro.add(tf" + aux3[1] + ", cons);\n");

            }
            contador++;
        }
        codigoGerado.add("        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);\n"
                + "        usarGridBagLayoutSul.add(new JLabel(\"Registro de atividades\"), scrollMensagem);\n"
                + "        pnSul.add(pnSulMsg, \"pnMsg\");\n"
                + "        pnSul.add(pnSulListagem, \"pnLst\");\n"
                + "\n"
                + "        \n"
                + "        pnTudo.add(pnNorte, BorderLayout.NORTH);\n"
                + "        pnTudo.add(pnCentro, BorderLayout.CENTER);\n"
                + "        pnTudo.add(pnSul, BorderLayout.SOUTH);\n"
                + "        \n"
                + "        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/busca.png\")));\n"
                + "        btNBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/busca.png\")));\n"
                + "        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/lista.jpg\")));\n"
                + "        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/alterar.png\")));       \n"
                + "        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/excluir.png\")));\n"
                + "        btExcluirTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/excluir.png\")));\n"
                + "        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/cancelar.png\")));\n"
                + "        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/salvar.png\")));\n"
                + "        btInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/inserir.png\")));\n"
                + "        btCor.setIcon(new javax.swing.ImageIcon(getClass().getResource(\"/Figuras/cor.jpg\")));\n"
                + "\n"
                + "        btExcluir.setToolTipText(\"Excluir\");\n"
                + "        btBuscar.setToolTipText(\"Buscar\");\n"
                + "        btNBuscar.setToolTipText(\"Nova busca\");\n"
                + "        btListar.setToolTipText(\"Listar\");\n"
                + "        btSalvar.setToolTipText(\"Salvar\");\n"
                + "        btInserir.setToolTipText(\"Inserir\");\n"
                + "        btCancelar.setToolTipText(\"Cancelar\");\n"
                + "        btAlterar.setToolTipText(\"Alterar\");\n"
                + "        btExcluirTudo.setToolTipText(\"Apaga todos os dados e fecha\");\n"
                + "        btCor.setToolTipText(\"Escolher cor\");\n"
                + "        \n"
                + "           \n"
                + "        btSalvar.setVisible(false);\n"
                + "        btCancelar.setVisible(false);\n"
                + "        btInserir.setVisible(false);\n"
                + "        btAlterar.setVisible(false);\n"
                + "        btExcluir.setVisible(false);\n"
                + "        \n"
                + "        travarTextFields(true);\n"
                + "        textAreaMsg.setEditable(false);\n"
                + "        \n"
                + "        \n"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                btGravar.doClick();\n"
                + "                // Sai   \n"
                + "                dispose();\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "//################ COLOR ##################\n"
                + "        btCor.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "               color =  colorchooser.showDialog(null, \"Escolher Cor\", null);\n"
                + "               pnSulListagem.setBackground(color);\n"
                + "                    pnSulMsg.setBackground(color);\n"
                + "                    pnCentro.setBackground(color);\n"
                + "                    pnNorteSul.setBackground(color);\n"
                + "                    pnNorteNorte.setBackground(color);\n"
                + "               \n"
                + "            }\n"
                + "        });\n"
                + "//################## Check Box ##################33\n"
                + "   checkbox.addItemListener(new ItemListener() {\n"
                + "            @Override\n"
                + "            public void itemStateChanged(ItemEvent e) {\n"
                + "                if (checkbox.isSelected() == false) {\n"
                + "                    pnSulListagem.setBackground(null);\n"
                + "                    pnSulMsg.setBackground(null);\n"
                + "                    pnCentro.setBackground(null);\n"
                + "                    pnNorteSul.setBackground(null);\n"
                + "                    pnNorteNorte.setBackground(null);\n"
                + "                    cp.setBackground(null);\n"
                + "                    btCor.setVisible(false);\n"
                + "                    \n"
                + "                }\n"
                + "                else{\n"
                + "                    \n"
                + "                    btCor.setVisible(true);\n"
                + "                    pnSulListagem.setBackground(color);\n"
                + "                    pnSulMsg.setBackground(color);\n"
                + "                    pnCentro.setBackground(color);\n"
                + "                    pnNorteSul.setBackground(color);\n"
                + "                    pnNorteNorte.setBackground(color);\n"
                + "                }\n"
                + "            }\n"
                + "        });\n"
                + "   \n");

        codigoGerado.add("//*********************** BOTÃO GRAVAR ****************************************\n"
                + "        btGravar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                List<String> listaStr = " + nomeClasse.toLowerCase() + "controle.listar();\n"
                + "                fer.salvarArquivo(\"" + nomeClasse + ".txt\", listaStr);\n"
                + "\n"
                + "            }\n"
                + "        });\n"
                + "\n");

        codigoGerado.add("//###################### BOTAO EXCLUIR TUDO ################################\n"
                + "           btExcluirTudo.addActionListener(new ActionListener() {\n"
                + "           @Override\n"
                + "           public void actionPerformed(ActionEvent e) {\n"
                + "               fer.LimparArquivo(\"" + nomeClasse + ".txt\", \"\");\n"
                + "                dispose();\n"
                + "               }\n"
                + "       });\n");
        ///////////////////////////// BOTAO BUSCAR ///////////////////////////
        codigoGerado.add("// ------------------------BOTAO BUSCAR ----------------------------------------        \n"
                + "        btBuscar.addActionListener((ActionEvent e) -> {\n"
                + "\n"
                + "            if (tf" + identificador[0] + ".getText().trim().equals(\"\")) {\n"
                + "                JOptionPane.showMessageDialog(cp, \"Nenhuma informação passada \");\n"
                + "            } else {\n"
                + "                try {\n"
                + "                    tf" + identificador[0] + ".setBackground(Color.green);\n");
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
            } else if (aux3[0].equals("boolean")) {
            } else {
                codigoGerado.add("tf" + aux3[1] + ".setEditable(false);");
            }
        }
        codigoGerado.add("                    CardLayout.show(pnSul, \"pnMsg\");\n");
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("int")) {
                codigoGerado.add(nomeClasse.toLowerCase() + " = " + nomeClasse.toLowerCase() + "controle.buscar(Integer.valueOf(tf" + identificador[0] + ".getText()));\n");
                break;
            } else if (aux3[0].equals("double")) {
                codigoGerado.add(nomeClasse.toLowerCase() + " = " + nomeClasse.toLowerCase() + "controle.buscar(Double.valueOf(tf" + identificador[0] + ".getText()));\n");
                break;
            } else {
                codigoGerado.add(nomeClasse.toLowerCase() + " = " + nomeClasse.toLowerCase() + "controle.buscar(tf" + identificador[0] + ".getText());\n");
                break;
            }
        }
        codigoGerado.add("                    if (" + nomeClasse.toLowerCase() + " == null) { //nao achou\n"
                + "                        btInserir.setVisible(true);\n"
                + "                        btAlterar.setVisible(false);\n"
                + "                        btExcluir.setVisible(false);\n"
                + "                        btBuscar.setVisible(false);\n"
                + "                        btNBuscar.setVisible(true);\n"
                + "                        setLog(\"Não achou na lista, pode inserir se quiser...\");\n"
                + "                    } else { //achou\n"
                + "                        btAlterar.setVisible(true);\n"
                + "                        btExcluir.setVisible(true);\n"
                + "                        btInserir.setVisible(false);\n"
                + "                        btNBuscar.setVisible(true);\n"
                + "                        btBuscar.setVisible(false);\n"
                + "                        btListar.setVisible(true);\n"
                + "                        \n"
                + "                        \n");
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
                codigoGerado.add("dtf" + aux3[1] + ".setText(converteDeStringParaDate(" + nomeClasse.toLowerCase() + ".get" + aux3[1] + "()));\n");
            } else if (aux3[0].equals("int")) {
                codigoGerado.add("tf" + aux3[1] + ".setText(String.valueOf(" + nomeClasse.toLowerCase() + ".get" + aux3[1] + "()));\n");
            } else if (aux3[0].equals("double")) {
                codigoGerado.add("tf" + aux3[1] + ".setText(String.valueOf(" + nomeClasse.toLowerCase() + ".get" + aux3[1] + "()));\n");
            } else if (aux3[0].equals("boolean")) {
                codigoGerado.add("checkbox" + aux3[1] + ".setSelected(" + nomeClasse.toLowerCase() + ".get" + aux3[1] + "());\n");
            } else {
                codigoGerado.add("tf" + aux3[1] + ".setText(" + nomeClasse.toLowerCase() + ".get" + aux3[1] + "());\n");
            }
        }
        codigoGerado.add("                        setLog(\"Achou [\" +" + remover_get + " \"]\");\n"
                + "\n"
                + "                    }\n");

        codigoGerado.add("} catch (Exception x) {\n"
                + "                    tf" + identificador[0] + ".selectAll();\n"
                + "                    tf" + identificador[0] + ".requestFocus();\n"
                + "                    tf" + identificador[0] + ".setBackground(Color.red);\n"
                + "                    tf" + identificador[0] + ".setEditable(true);\n"
                + "                    setLog(\"Erro no tipo de dados da chave. (\" + x.getMessage() + \")\");\n"
                + "                }//else\n"
                + "            }\n"
                + "            tf" + identificador[0] + ".selectAll();\n"
                + "            tf" + identificador[0] + ".requestFocus();\n"
                + "        });");

        ////////////////////////////////// BOTAO NOVA BUSCA //////////////////////////////////
        codigoGerado.add("//########################3 Nova Busca ##########################3\n"
                + "        btNBuscar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                btBuscar.setVisible(true);\n"
                + "                btNBuscar.setVisible(false);\n"
                + "                btAlterar.setVisible(false);\n"
                + "                btExcluir.setVisible(false);\n"
                + "                btInserir.setVisible(false);\n"
                + "                tf" + identificador[0] + ".requestFocus();\n"
                + "                limparValoresDosAtributos();\n");

        codigoGerado.add("travarTextFields(true);"
                + "tf" + identificador[0] + ".setEditable(true);\n");
        codigoGerado.add("}\n});");

        codigoGerado.add("//*********************** BOTÃO SALVAR ****************************************        \n"
                + "        btSalvar.addActionListener((ActionEvent e) -> {\n"
                + "            " + nomeClasse + " " + nomeClasse.toLowerCase() + "Original = " + nomeClasse.toLowerCase() + "; //para pesquisar na lista\n"
                + "            //precisamos do contato original (para depois modificar)\n"
                + "            if (inserindo) {\n"
                + "                " + nomeClasse.toLowerCase() + " = new " + nomeClasse + "(); //criar um novo contato\n"
                + "            }\n"
                + "try {");
        for (String s : arquivoBase) {
            String aux3[] = s.split(";");
            if (aux3[0].equals("Date")) {
                codigoGerado.add(nomeClasse.toLowerCase() + ".set" + aux3[1]
                        + "(converteDeStringParaDate(dtf" + aux3[1] + ".getText()));");
            } else if (aux3[0].equals("int")) {
                codigoGerado.add("if (tf" + aux3[1] + ".getText().trim().equals(\"\")) {\n"
                        + "                JOptionPane.showMessageDialog(cp, \"Campo " + aux3[1] + " está vazio  e será preenhido automaticamente como 'null' \");\n"
                        + "            } else{\n"
                        + "                 " + nomeClasse.toLowerCase() + ".set" + aux3[1] + "(Integer.valueOf(tf" + aux3[1] + ".getText())); \n"
                        + "            }");
            } else if (aux3[0].equals("double")) {
                codigoGerado.add("if (tf" + aux3[1] + ".getText().trim().equals(\"\")) {\n"
                        + "                JOptionPane.showMessageDialog(cp, \"Campo " + aux3[1] + " está vazio  e será preenhido automaticamente como 'null' \");\n"
                        + "            } else{\n"
                        + "                 " + nomeClasse.toLowerCase() + ".set" + aux3[1] + "(Double.parseDouble(tf" + aux3[1] + ".getText())); \n"
                        + "            }");
            } else if (aux3[0].equals("boolean")) {
                codigoGerado.add("                 " + nomeClasse.toLowerCase() + ".set" + aux3[1] + "(Boolean.valueOf(checkbox" + aux3[1] + ".isSelected())); \n"
                );
            } else {
                codigoGerado.add("if (tf" + aux3[1] + ".getText().trim().equals(\"\")) {\n"
                        + "                JOptionPane.showMessageDialog(cp, \"Campo " + aux3[1] + " está vazio  e será preenhido automaticamente como 'null' \");\n"
                        + "            } else{\n"
                        + "                 " + nomeClasse.toLowerCase() + ".set" + aux3[1] + "(tf" + aux3[1] + ".getText()); \n"
                        + "            }");
            }
        }
        codigoGerado.add("if (inserindo) { //a variavel inserindo é preenchida nos\n"
                + "                " + nomeClasse.toLowerCase() + "controle.inserir(" + nomeClasse.toLowerCase() + ");\n");

        codigoGerado.add("setLog(\"Inseriu [\" + " + remover_get + "\"]\");\n"
                + "            } else {//alterar\n"
                + "                " + nomeClasse.toLowerCase() + "controle.alterar(" + nomeClasse.toLowerCase() + "Original, " + nomeClasse.toLowerCase() + ");\n"
                + "                setLog(\"Alterou [\" + " + remover_get + "\"]\");\n"
                + "            }"
                + "} catch(Exception b) {\n"
                + "                setLog(\"Erro no tipo de dados da chave. (\" + b.getMessage() + \")\");\n"
                + "                }");

        codigoGerado.add("//voltar para tela inicial\n"
                + "            tf" + identificador[0] + ".requestFocus();\n"
                + "            tf" + identificador[0] + ".selectAll();\n"
                + "            btSalvar.setVisible(false);\n"
                + "            btCancelar.setVisible(false);\n"
                + "            btBuscar.setVisible(true);\n"
                + "            btListar.setVisible(true);\n"
                + "            limparValoresDosAtributos();\n"
                + "            travarTextFields(true);\n"
                + "            btGravar.doClick();\n"
                + "        });");

        codigoGerado.add("//**************** Cancelar alteração ou inclusão ********************\n"
                + "\n"
                + "\n"
                + "        btCancelar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                tf" + identificador[0] + ".requestFocus();\n"
                + "                tf" + identificador[0] + ".selectAll();\n"
                + "                btInserir.setVisible(false);\n"
                + "                btSalvar.setVisible(false);\n"
                + "                btCancelar.setVisible(false);\n"
                + "                btBuscar.setVisible(true);\n"
                + "                btNBuscar.setVisible(false);\n"
                + "                btListar.setVisible(true);\n"
                + "                travarTextFields(true);\n"
                + "                limparValoresDosAtributos();\n"
                + "                setLog(\"Cancelou a alteração ou inclusão do registro\");\n"
                + "            }\n"
                + "        });");

        codigoGerado.add("//***************************** alterar *************************************\n"
                + "btAlterar.addActionListener((ActionEvent e) -> {\n"
                + "            tf" + identificador[0] + ".requestFocus();\n"
                + "            btSalvar.setVisible(true);\n"
                + "            btCancelar.setVisible(true);\n"
                + "            btBuscar.setVisible(false);\n"
                + "            btNBuscar.setVisible(false);\n"
                + "            btAlterar.setVisible(false);\n"
                + "            btExcluir.setVisible(false);\n"
                + "            btListar.setVisible(false);\n"
                + "            inserindo = false;\n"
                + "            travarTextFields(false);\n"
                + "            setLog(\"Alterando um registro existente\");\n"
                + "        });");

        codigoGerado.add("//***************************** inserir ************************************* \n"
                + "btInserir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                tf" + identificador[0] + ".requestFocus();\n"
                + "                btInserir.setVisible(false);\n"
                + "                btSalvar.setVisible(true);\n"
                + "                btCancelar.setVisible(true);\n"
                + "                btBuscar.setVisible(false);\n"
                + "                btNBuscar.setVisible(false);\n"
                + "                btListar.setVisible(false);\n"
                + "                inserindo = true;\n"
                + "travarTextFields(false);"
                + "                setLog(\"Inserindo um novo registro\");\n"
                + "            }\n"
                + "        });");

        String vetores = "String []{";
        for (int k = 0; k < cont; k++) {
            if (k == cont - 1) {
                vetores += "aux[" + k + "]}";
            } else {
                vetores += "aux[" + k + "], ";
            }

        }
        codigoGerado.add("//======================= LISTAR =============================================\n"
                + "        btListar.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                CardLayout.show(pnSul, \"pnLst\");\n"
                + "                scrollList.setPreferredSize(tabela.getPreferredSize());\n"
                + "                pnSulListagem.add(scrollList);\n"
                + "                scrollList.setViewportView(tabela);\n"
                + "                List<String> listaDe" + nomeClasse + " = " + nomeClasse.toLowerCase() + "controle.listar();//busca a lista de contatos\n"
                + "                String[] aux;\n"
                + "                colunas = new String[]{" + coisas_separadas_por_aspas + "};\n"
                + "                dados = new String[0][" + quantidade + "];\n"
                + "                model.setDataVector(dados, colunas);\n"
                + "                for (int i = 0; i < listaDe" + nomeClasse + ".size(); i++) {\n"
                + "                    aux = listaDe" + nomeClasse + ".get(i).split(\";\");\n");

        codigoGerado.add("String[] linha = new " + vetores + ";\n"
                + "                    model.addRow(linha);"
                + "                }\n"
                + "            }\n"
                + "        });");

        codigoGerado.add("//***************************** EXCLUIR *************************************\n"
                + "        btExcluir.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n");

        codigoGerado.add("int dialogResult = JOptionPane.showConfirmDialog(cp, \"Vai excluir [ \" +\n"
                + remover_get + "\" ]?\", \"Exclui da lista\", NORMAL);\n"
                + "                if (dialogResult == JOptionPane.YES_OPTION) {\n"
                + nomeClasse.toLowerCase() + "controle.excluir(" + nomeClasse.toLowerCase() + ");\n"
                + "                    setLog(\"Excluiu [\" +" + remover_get + " \"]\");\n"
                + "\n"
                + "                }\n"
                + "btGravar.doClick();\n"
                + "\n"
                + "btExcluir.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btNBuscar.setVisible(false);\n"
                + "btBuscar.setVisible(true);\n"
                + "tf" + identificador[0] + ".setText(\"\");"
                + "limparValoresDosAtributos();"
                + "travarTextFields(true);"
                + "tf" + identificador[0] + ".requestFocus();"
                + "}});");

        codigoGerado.add("//$$$$$$$$$$$$$$ FIM DOS LISTENERS $$$$$$$$$$$$$\n"
                + "        // parâmetros para janela inicial\n"
                + "        setSize(900, 400);\n"
                + "        setTitle(\"" + nomeClasse + "\");\n"
                + "        setLocationRelativeTo(null);\n"
                + "        setVisible(true);\n"
                + "        setResizable(true);\n"
                + "    }\n"
                + "}\n"
                + "\n"
                + "");

        String cc = projetoDestino + "/src/Main/";

        System.out.println(
                "Vai criar a classe nesse caminho=> " + cc);
        ferramentas.salvarArquivo(cc
                + nomeClasse + "GUI.java", codigoGerado);
    }
}
