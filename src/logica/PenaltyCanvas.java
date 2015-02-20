/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.*;

/**
 *
 * @author Eduardo
 */
public class PenaltyCanvas extends GameCanvas implements Runnable {

    private Command command;
    private Thread t;
    private Jogo jogo;
    private LayerManager layer;
    private Bola bola;
    private Batedor batedor;
    private Goleiro goleiro;
    private CampoDeJogo field;
    private ImagemGol imagemGol;
    private ImagemDefesa imagemDefesa;
    private ImagemDerrota imagemDerrota;
    private ImagemVitoria imagemVitoria;
    private boolean temJogo; /*indica se ainda tem partida */

    private boolean ballRunning = false;/*bola esta indo em direção ao gol*/

    private boolean isRunning = false;/*jogador partiu para a bola*/

    private boolean captureiIO = false;/*diz se capturou a entrada do usuário*/

    private boolean terminouPenalty = true;/*quando terminar entra loop jogo*/

    private int auxRun = 1;/*ajuda o jogador a correr*/

    private int auxBall = 1;/*ajuda o a bola ir em direção ao gol*/

    int a = 1;
    int b = 1;
    int c = 1;

    public PenaltyCanvas() {
        super(false);
        jogo = new Jogo();
    }

    public PenaltyCanvas(Jogo jogoIgual) throws IOException {
        super(true);/*indica se o sistema de teclas vai ficar ativo ou não*/
        command = new Command("Ok", Command.OK, 0);
        this.addCommand(command);
        jogo = jogoIgual;

        /*LayerManager facilita a renderização de objetos Sprites e TiledLayer,
        ao invés do programador inserir os objetos e chamar paint especificamente para
        cada objeto, chama-se o método paint() do layer manager que fica responsável,
        por chamar o método paint() na ordem especficada*/

        bola = new Bola();
        batedor = new Batedor();

        goleiro = new Goleiro();
        field = new CampoDeJogo();

        imagemGol = new ImagemGol();
        imagemDefesa = new ImagemDefesa();
        imagemVitoria = new ImagemVitoria();
        imagemDerrota = new ImagemDerrota();



        imagemGol.setPosition(2, 105);
        imagemDefesa.setPosition(field.getWidth() - 60, 109);
        imagemDefesa.setVisible(false);
        imagemGol.setVisible(false);
        imagemVitoria.setVisible(false);
        imagemDerrota.setVisible(false);

        bola.setPosition((field.getWidth() / 2) - 4, 132);
        goleiro.setPosition((field.getWidth() / 2) - 16, 73);
        batedor.setPosition((field.getWidth() / 5) + 11, 148);
        layer = new LayerManager();

        /*o append é utilizado no no formato z-order, os primeiros 
        elementos gráficos inseridos tem uma prioridade maior*/
        layer.append(imagemDerrota);
        layer.append(imagemVitoria);
        layer.append(bola);
        layer.append(batedor);
        layer.append(goleiro);
        layer.append(imagemDefesa);
        layer.append(imagemGol);

        /*campo deve ser adicionado aqui, pois é o que tem menor prioridade*/
        layer.append(field);
    }

    public Command getCommand() {
        return this.command;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void start() {
        temJogo = true;
        t = new Thread(this);
        t.start();

    }

    public void stop() {
        temJogo = false;
    }

    /*metodo main da thread*/
    public void run() {
        Graphics g = getGraphics();
        Font fonte = Font.getFont(Font.FACE_MONOSPACE, Font.SIZE_MEDIUM, Font.SIZE_MEDIUM);
        g.setFont(fonte);

        while (temJogo) {

            if (!captureiIO) {/*se nao capturou a entrada do usuário*/
                capturaAcao();
                descarrega(g);
            } else if (captureiIO) {
                /*se a bola ja chegou ao gol está apto a entrar
                no loop do jogo novamente */
                if (terminouPenalty) {
                    temJogo = jogo.loopJogo();
                }
                terminouPenalty = false;// volta a ser true quando terminar ballRunning

                if (temJogo == false)/*jogo acabou hora de terminar a thread*/ {
                    if (jogo.getPlayer().getGols() > jogo.getComputer().getGols()) {
                        System.out.println("o jogador venceu");
                        imagemVitoria.setVisible(true);
                        descarrega(g);
                    } else {
                        System.out.println("o computador venceu");
                        imagemDerrota.setVisible(true);
                        descarrega(g);
                    }

                    break;
                }

                /*depois de capturar IO entra no loop do jogo e depois corre*/
                if (isRunning) {
                    correndo();
                }

                /*marca que a bola esta indo em direção ao gol*/
                if (ballRunning) {
                    ballRunning(g);
                }

                descarrega(g);
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
    }

    /*jogador correndo para a bola*/
    public void correndo() {
        if (auxRun < batedor.getFrameSequenceLength()) {
            batedor.mover();
            goleiro.mover(10);
            auxRun++;
        } else {
            isRunning = false;/*batedor parou de correr*/
            ballRunning = true;/*agora vai movimentar a bola*/
            auxRun = 1;/*quando for outra vez começa a correr da posição inicial*/
        }
    }


    /*bola indo para o gol e goleiro quem sabe para a bola*/
    public void ballRunning(Graphics g) {

        if (auxBall < bola.getFrameSequenceLength()) {
            if (auxBall == 1) {
                a = jogo.getCanto();
                b = jogo.getCantoComp();
                /*variaveis auxiliares que vão ajudar a percorrer os frames*/
                bola.setAux(1);
                goleiro.setAux(1);

            }
            /*o código abaixo parece inconsistente, mas está correto pq o batedor corrente é inverso do que deve executar
            a ação pq seu valor foi modificado no loopJogo*/
            if (jogo.getBatedor() == 0) {
                //System.out.println("\nO jogador vai bater o penalti em " + b + " e o goleiro vai pular em " + a + ".\n\n");
                bola.mover(b);
                goleiro.mover(a);
            } else {
                //System.out.println("\nO computador vai bater o penalti em " + a + " e o goleiro vai pular em " + b + ".\n\n");
                bola.mover(a);
                goleiro.mover(b);

            }
            auxBall++;
        } else {
            /*o código try abaixo serve para que as imagens não voltem para o lugar
            tão rapido que não dê para ver as posições finais na tela*/
            if (jogo.getHelpGolDefesa()) {
                imagemGol.setVisible(true);
            } else {
                imagemDefesa.setVisible(true);
            }
            layer.paint(g, 0, 0);
            flushGraphics();
            try {
                Thread.sleep(700);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }

            batedor.setPosition((field.getWidth() / 5) + 11, 148);
            batedor.setFrame(0);

            bola.setPosition((field.getWidth() / 2) - 4, 132);
            bola.setFrame(0);

            goleiro.setPosition((field.getWidth() / 2) - 16, 73);
            goleiro.setFrame(0);

            auxBall = 1;/*quando for outra vez começa a bola do início*/
            ballRunning = false;/*bola parou de correr*/
            captureiIO = false;/*espera a entrada do usuário novamente*/
            terminouPenalty = true;/*agora pode chamar o loop do jogo novamente*/
            imagemGol.setVisible(false);
            imagemDefesa.setVisible(false);
        }
    }

    /*mostra o placar do jogo*/
    public void descarrega(Graphics g) {
        if (jogo.getAuxDrawString()) {
            g.setColor(255, 255, 255);/*cor branca*/
            g.fillRect(0, 0, getWidth(), 25);/*pinta o espaço referente de branco*/
            flushGraphics();
            g.setColor(0, 0, 255);/*cor azul*/
            if (jogo.getBatedor() == 0) {
                g.drawString(jogo.getPlayer().getNome() + " " + jogo.getPlayer().getGols() + " | " + jogo.getComputer().getNome() + " " + jogo.getComputer().getGols() + " | Vez: " + jogo.getPlayer().getNome(), 2, 2, Graphics.TOP | Graphics.LEFT);
            } else {
                g.drawString(jogo.getPlayer().getNome() + " " + jogo.getPlayer().getGols() + " | " + jogo.getComputer().getNome() + " " + jogo.getComputer().getGols() + " | Vez: " + jogo.getComputer().getNome(), 2, 2, Graphics.TOP | Graphics.LEFT);
            }
        }
        jogo.setAuxDrawString(false);
        layer.paint(g, 0, 0);
        flushGraphics();/*descarrega os objetos na tela*/
    }

    //Método que captura uma tentativa de defesa ou chute do usuário
    public void capturaAcao() {
        int keyStates = getKeyStates();
        if (keyStates != 0) {
            System.out.println("\n\no valor de keyStates e'" + keyStates);
        }
        if ((keyStates & GAME_A_PRESSED) != 0) {
            jogo.setCanto(1);
            System.out.println("Chuta ou defende a bola em 1");
            captureiIO = true;
        } else if ((keyStates & GAME_B_PRESSED) != 0) {
            jogo.setCanto(3);
            System.out.println("Chuta ou defende a bola em 3");
            captureiIO = true;
        } else if ((keyStates & GAME_C_PRESSED) != 0) {
            jogo.setCanto(7);
            System.out.println("Chuta ou defende a bola em 7");
            captureiIO = true;
        } else if ((keyStates & GAME_D_PRESSED) != 0) {
            jogo.setCanto(9);
            System.out.println("Chuta ou defende a bola em 9");
            captureiIO = true;
        }
        //  depois de capturar a entrada está apto a correr
        isRunning = true;
    }

    /*O método keyPressed é chamado em resposta ao pressionamento de qualquer
    tecla, vai ficar esperando até que o evento ocorra
     */
    protected void keyPressed(int keyCode) {
        System.out.println(keyCode);
        System.out.println("Imprimindo GAME_A: " + GAME_A);
        System.out.println("Imprimindo GAME_B: " + GAME_B);
        System.out.println("Imprimindo GAME_C: " + GAME_C);
        System.out.println("Imprimindo GAME_D: " + GAME_D);
        System.out.println("sera que chegou aqui " + getGameAction(keyCode));
        switch (keyCode) {

            case 50:
                jogo.setCanto(2);
                System.out.println("Chuta ou defende a bola em 2");
                captureiIO = true;
                break;
            case 52:
                jogo.setCanto(4);
                System.out.println("Chuta ou defende a bola em 4");
                captureiIO = true;
                break;
            case 53:
                jogo.setCanto(5);
                System.out.println("Chuta ou defende a bola em 5");
                captureiIO = true;
                break;
            case 54:
                jogo.setCanto(6);
                System.out.println("Chuta ou defende a bola em 6");
                captureiIO = true;
                break;
            case 56:
                jogo.setCanto(8);
                System.out.println("Chuta ou defende a bola em 8");
                captureiIO = true;
                break;
            default:
                System.out.println("Aqui esta no default" + keyCode);
        }
        isRunning = true;
    }
}
