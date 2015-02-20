package hello;

import java.io.IOException;
import logica.PenaltyCanvas;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Interface extends MIDlet implements CommandListener {

    private PenaltyCanvas auxCanvas;/*canvas auxiliar, so tem objeto jogo*/

    private PenaltyCanvas canvas;//canvas verdadeiro
    private boolean midletPaused = false;
    private Ticker ticker5;
    private Ticker ticker;
    private Ticker ticker6;
    private Ticker ticker2;
    private Ticker ticker3;
    private Ticker ticker4;
    private Ticker ticker1;
    private Command exitCommand1;
    private Command okBoxHints;
    private Command okFirst;
    private Command okLevel;
    private Command exitCommand;
    private Command exitFinal;
    private Command okNames;
    private Command backHints;
    private Command okBoxCredits;
    private Command okCommand2;
    private Command backCommand;
    private Command okCommand;
    private Command backCredits;
    private Command okGame;
    private Form formFinal;
    private TextBox textBoxHints;
    private List formLevel;
    private Form formFirst;
    private Form formNames;
    private TextField textField;
    private TextField textField1;
    private TextBox textBoxCredits;
    private List formMenu;
    private Form formGame;

    public void startMIDlet() {
        switchDisplayable(null, getFormMenu());
    }

    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {

        Display display = getDisplay();
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }
    }

    public void commandAction(Command command, Displayable displayable) {

        if (displayable == formFinal) {
            if (command == exitFinal) {
                exitMIDlet();
            }
        } else if (displayable == formFirst) {
            if (command == okFirst) {
                switchDisplayable(null, getFormGame());

            }
            try {
                canvas = new PenaltyCanvas(auxCanvas.getJogo());
                canvas.start();
                Display.getDisplay(this).setCurrent(canvas);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            if (command == canvas.getCommand())
                switchDisplayable(null, getFormFinal());
            
        /*} else if (displayable == formGame) {

            if (command == okGame) {
                switchDisplayable(null, getFormFinal());
            }*/
        } else if (displayable == formLevel) {
            auxCanvas.getJogo().setDificuldade(formLevel.getSelectedIndex());
            System.out.println("\n\no nível é "+ auxCanvas.getJogo().getDificuldade()+".\n\n");
            if (command == List.SELECT_COMMAND) {
                formLevelAction();
            } else if (command == okLevel) {
                switchDisplayable(null, getFormNames());
            }
        } else if (displayable == formMenu) {
            if (command == List.SELECT_COMMAND) {
                formMenuAction();
            }
        } else if (displayable == formNames) {
            if (command == okNames) {
                auxCanvas.getJogo().getPlayer().setNome(textField.getString());
                auxCanvas.getJogo().getComputer().setNome(textField1.getString());

                switchDisplayable(null, getFormFirst());
            }
        } else if (displayable == textBoxCredits) {
            if (command == okBoxCredits) {
                switchDisplayable(null, getFormMenu());
            }
        } else if (displayable == textBoxHints) {
            if (command == okBoxHints) {
                switchDisplayable(null, getFormMenu());
            }
        }
    }

    public Command getExitCommand() {
        if (exitCommand == null) {
            exitCommand = new Command("Exit", Command.EXIT, 0);
        }
        return exitCommand;
    }

    public Command getOkCommand() {
        if (okCommand == null) {
            okCommand = new Command("Ok", Command.OK, 0);
        }
        return okCommand;
    }

    public Command getOkNames() {
        if (okNames == null) {
            okNames = new Command("Ok", Command.OK, 0);
        }
        return okNames;
    }

    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|31-getter|0|31-preInit
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|31-getter|1|31-postInit
        }
        return exitCommand1;
    }

    public Form getFormNames() {
        if (formNames == null) {//GEN-END:|22-getter|0|22-preInit
            formNames = new Form("Entre com o nome dos jogadores:", new Item[]{getTextField(), getTextField1()});//GEN-BEGIN:|22-getter|1|22-postInit
            formNames.setTicker(getTicker3());
            formNames.addCommand(getOkNames());
            formNames.setCommandListener(this);//GEN-END:|22-getter|1|22-postInit
        }
        return formNames;
    }

    public Form getFormFirst() {
        if (formFirst == null) {
            formFirst = new Form("Abaixo quem come\u00E7ar\u00E1 a bater os penaltis.");//GEN-BEGIN:|23-getter|1|23-postInit
            formFirst.setTicker(getTicker4());
            formFirst.addCommand(getOkFirst());
            formFirst.setCommandListener(this);//GEN-END:|23-getter|1|23-postInit
            formFirst.append("O primeiro batedor e " + auxCanvas.getJogo().primeiroBatedor() + ".");// write post-init user code here
        }
        return formFirst;
    }

    public Command getOkCommand2() {
        if (okCommand2 == null) {
            okCommand2 = new Command("Ok", Command.OK, 0);
        }
        return okCommand2;
    }

    public Command getBackCommand() {
        if (backCommand == null) {
            backCommand = new Command("Back", Command.BACK, 0);
        }
        return backCommand;
    }

    public Command getOkLevel() {
        if (okLevel == null) {
            okLevel = new Command("Ok", Command.OK, 0);
        }
        return okLevel;
    }

    public Command getOkFirst() {
        if (okFirst == null) {
            okFirst = new Command("Ok", Command.OK, 0);
        }
        return okFirst;
    }

    public Command getOkGame() {
        if (okGame == null) {
            okGame = new Command("Ok", Command.OK, 0);
        }
        return okGame;
    }

    public Command getExitFinal() {
        if (exitFinal == null) {
            exitFinal = new Command("Exit", Command.EXIT, 0);
        }
        return exitFinal;
    }

    public TextField getTextField() {
        if (textField == null) {//GEN-END:|66-getter|0|66-preInit
            textField = new TextField("Entre com o seu nome:", null, 32, TextField.ANY);//GEN-LINE:|66-getter|1|66-postInit
        }
        return textField;
    }

    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|67-getter|0|67-preInit
            textField1 = new TextField("Entre com o nome do computador", null, 32, TextField.ANY);//GEN-LINE:|67-getter|1|67-postInit
        }//GEN-BEGIN:|67-getter|2|
        return textField1;
    }

    public List getFormMenu() {
        if (formMenu == null) {//GEN-END:|33-getter|0|33-preInit

            formMenu = new List("Menu Penalidde M\u00E1xima", Choice.IMPLICIT);//GEN-BEGIN:|33-getter|1|33-postInit
            formMenu.append("play", null);
            formMenu.append("credits", null);
            formMenu.append("hints to play", null);
            formMenu.append("exit", null);
            formMenu.setTicker(getTicker1());
            formMenu.setCommandListener(this);
            formMenu.setSelectedFlags(new boolean[]{false, false, false, false});//GEN-END:|33-getter|1|33-postInit

        }//GEN-BEGIN:|33-getter|2|
        return formMenu;
    }

    public void formMenuAction() {//GEN-END:|33-action|0|33-preAction
        // enter pre-action user code here
        String __selectedString = getFormMenu().getString(getFormMenu().getSelectedIndex());//GEN-BEGIN:|33-action|1|42-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("play")) {//GEN-END:|33-action|1|42-preAction
                // write pre-action user code here
                switchDisplayable(null, getFormLevel());//GEN-LINE:|33-action|2|42-postAction
            // write post-action user code here
            } else if (__selectedString.equals("credits")) {//GEN-LINE:|33-action|3|43-preAction
                // write pre-action user code here
                switchDisplayable(null, getTextBoxCredits());//GEN-LINE:|33-action|4|43-postAction
            // write post-action user code here
            } else if (__selectedString.equals("hints to play")) {//GEN-LINE:|33-action|5|44-preAction
                // write pre-action user code here
                switchDisplayable(null, getTextBoxHints());//GEN-LINE:|33-action|6|44-postAction
            // write post-action user code here
            } else if (__selectedString.equals("exit")) {//GEN-LINE:|33-action|7|97-preAction
                // write pre-action user code here
                exitMIDlet();
            }
        }
    }

    public List getFormLevel() {
        if (formLevel == null) {//GEN-END:|37-getter|0|37-preInit
            formLevel = new List("Escolha o nivel do jogo:", Choice.IMPLICIT);//GEN-BEGIN:|37-getter|1|37-postInit
            formLevel.append("easy", null);
            formLevel.append("medium", null);
            formLevel.append("hard", null);
            formLevel.setTicker(getTicker2());
            formLevel.addCommand(getOkLevel());
            formLevel.setCommandListener(this);
            formLevel.setSelectedFlags(new boolean[]{false, false, false});//GEN-END:|37-getter|1|37-postInit
        }
        return formLevel;
    }

    public void formLevelAction() {
        String __selectedString = getFormLevel().getString(getFormLevel().getSelectedIndex());
        if (__selectedString != null) {
            if (__selectedString.equals("easy")) {
            } else if (__selectedString.equals("medium")) {
            } else if (__selectedString.equals("hard")) {
            }
        }
    }

    public Form getFormGame() {
        if (formGame == null) {
            formGame = new Form("Bata e defenda se conseguir.");
            formGame.addCommand(getOkGame());
            formGame.setCommandListener(this);
        }
        return formGame;
    }

    public Form getFormFinal() {
        if (formFinal == null) {
            formFinal = new Form("Exit para voltar ao inicio.");//GEN-BEGIN:|73-getter|1|73-postInit
            formFinal.setTicker(getTicker6());
            formFinal.addCommand(getExitFinal());
            formFinal.setCommandListener(this);
        }
        return formFinal;
    }

    public Command getBackHints() {
        if (backHints == null) {//GEN-END:|102-getter|0|102-preInit
            backHints = new Command("Back", Command.BACK, 0);//GEN-LINE:|102-getter|1|102-postInit
        }
        return backHints;
    }

    public Command getBackCredits() {
        if (backCredits == null) {
            backCredits = new Command("Back", Command.BACK, 0);//GEN-LINE:|108-getter|1|108-postInit
        }
        return backCredits;
    }

    public Ticker getTicker() {
        if (ticker == null) {
            ticker = new Ticker("Nossos artistas abaixo !!!");//GEN-LINE:|84-getter|1|84-postInit
        }
        return ticker;
    }

    public Ticker getTicker1() {
        if (ticker1 == null) {//GEN-END:|89-getter|0|89-preInit
            // write pre-init user code here
            ticker1 = new Ticker("Welcome to Penalidade M\u00E1xima !!!");//GEN-LINE:|89-getter|1|89-postInit
        // write post-init user code here
        }//GEN-BEGIN:|89-getter|2|
        return ticker1;
    }

    public Ticker getTicker2() {
        if (ticker2 == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            ticker2 = new Ticker("Entre com o n\u00EDvel de acordo com suas habilidades.");//GEN-LINE:|90-getter|1|90-postInit
        // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return ticker2;
    }

    public Ticker getTicker3() {
        if (ticker3 == null) {//GEN-END:|91-getter|0|91-preInit
            ticker3 = new Ticker("Os nomes s\u00E3o obrigat\u00F3rios.");//GEN-LINE:|91-getter|1|91-postInit
        }
        return ticker3;
    }

    public Ticker getTicker4() {
        if (ticker4 == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            ticker4 = new Ticker("A escolha \u00E9 feita de forma aleat\u00F3ria.");//GEN-LINE:|92-getter|1|92-postInit
        // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return ticker4;
    }

    public Ticker getTicker5() {
        if (ticker5 == null) {//GEN-END:|106-getter|0|106-preInit
            ticker5 = new Ticker("Nossos artistas abaixo.");//GEN-LINE:|106-getter|1|106-postInit
        // write post-init user code here
        }//GEN-BEGIN:|106-getter|2|
        return ticker5;
    }

    public TextBox getTextBoxHints() {
        if (textBoxHints == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            String aux = "Ola. Para jogar corretamente o Penalidade\n" +
                    "Maxima recomenda-se que voce tenha\n" +
                    "dominio pleno de como manusear o celular.\n" +
                    "listamos botoes utilizados no jogo:\n";
            aux += "1: chuta e defende no canto esquerdo alto.\n";
            aux += "2: chuta e defende no centro alto.\n";
            aux += "3: chuta e defende no canto direito alto.\n";
            aux += "4: chuta e defende no canto esquerdo médio.\n";
            aux += "5: chuta e defende no centro médio.\n";
            aux += "6: chuta e defende no canto direito médio.\n";
            aux += "7: chuta e defende no canto esquerdo baixo.\n";
            aux += "8: chuta e defende no centro baixo.\n";
            aux += "9: chuta e defende no canto direito baixo.";

            textBoxHints = new TextBox("textBox", aux, 1000, TextField.ANY);
            textBoxHints.addCommand(getOkBoxHints());
            textBoxHints.setCommandListener(this);
        }
        return textBoxHints;
    }

    public TextBox getTextBoxCredits() {
        if (textBoxCredits == null) {
            String artistas = "Os artistas desse jogo são:\n" +
                    "Danilo Ribeiro,\n" +
                    "Eduardo Augusto,\n" +
                    "Rodolfo Cardoso,\n" +
                    "Rodrigo.";
            textBoxCredits = new TextBox("Nome dos criadores do jogo.", artistas, 100, TextField.ANY);
            textBoxCredits.setTicker(getTicker5());
            textBoxCredits.addCommand(getOkBoxCredits());
            textBoxCredits.setCommandListener(this);
        }
        return textBoxCredits;
    }

    public Ticker getTicker6() {
        if (ticker6 == null) {
            ticker6 = new Ticker("V\u00E1 em exit !!!");
        }
        return ticker6;
    }

    public Command getOkBoxCredits() {
        if (okBoxCredits == null) {
            okBoxCredits = new Command("Ok", Command.OK, 0);
        }
        return okBoxCredits;
    }

    public Command getOkBoxHints() {
        if (okBoxHints == null) {//GEN-END:|124-getter|0|124-preInit
            // write pre-init user code here
            okBoxHints = new Command("Ok", Command.OK, 0);//GEN-LINE:|124-getter|1|124-postInit

        }//GEN-BEGIN:|124-getter|2|
        return okBoxHints;
    }

    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    public void startApp() {
        auxCanvas = new PenaltyCanvas();
        System.out.println("\nAcabou de criar o canvas auxiliar...");
        startMIDlet();
    }

    public void pauseApp() {
        midletPaused = true;
    }

    public void destroyApp(boolean unconditional) {
        if (auxCanvas != null) {
            auxCanvas.stop();
            auxCanvas = null;
            return;
        } else if (canvas != null) {
            canvas.stop();
            canvas = null;
            return;
        }
    }
}
