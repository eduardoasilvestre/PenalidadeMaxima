/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Random;

/**
 *
 * @author a1042258
 */
public class Jogo {

    private Jogador players[];//0 para jogador 1 para computador
    private int batedor;//0 e o jogador, 1 e o computador
    private int dificuldade;//0 ou 1 ou 2
    private boolean auxDrawString;
    /*para setar ql jogador comecara a bater*/
    private static Random random = new Random();
    /*variavel que setara se o jogo acabou ou nao*/
    private boolean auxTemJogo = true;
    private int canto;/*gaurda o canto do jogador: para chutar ou defender*/

    private int cantoComp;/*guarda o canto do computador: para chutar ou defender*/

    private boolean helpGolDefesa;/*ajuda a imprimir a imagem de gol ou de defesa*/


    public Jogo() {
        players = new Jogador[2];
        players[0] = new Jogador();
        players[1] = new Jogador();
        auxDrawString = true;
    }

    /*Metodo que retorna o nome do primeiro batedor do jogo: pessoa fisica ou computador*/
    public String primeiroBatedor() {
        int x = random.nextInt(2);//vai retornar inteiros entre 0 e 1
        if (x == 0) {
            batedor = 0;
            return players[0].getNome();

        }
        batedor = 1;
        return players[1].getNome();
    }

    public boolean loopJogo() {
        int flag = 0; /*serve para entrar em apenas um if por chamada*/

        if (batedor == 0 && ngmVenceu()) { /*o jogador bate o penalti*/

            if (chanceCompDefender() == canto) {
                helpGolDefesa = false;
                players[1].setDefesas(1);
                auxDrawString = true;
                System.out.println("O computador acabou de fazer uma defesa, atualmente: " + players[1].getDefesas());
            } else {
                helpGolDefesa = true;
                players[0].setGols(1);
                auxDrawString = true;/*para mostrar a alteração do placar na tela*/
                System.out.println("O jogador acabou de marcar um gol, atualmente: " + players[0].getGols());
            }

            /*marca que o jogador acabou de bater um penalti*/
            players[0].setPenaltisBatidos(1);

            /*marca que o proximo batedor e o computador*/
            batedor = 1;
            flag++;



        } else if (batedor == 1 && ngmVenceu() && flag == 0) {/*o computador bate o penalti*/

            /*se a chance do comp fazer o gol for no mesmo canto escolhido pela maquina*/
            if (chanceCompFazerGol() == canto) {
                helpGolDefesa = false;
                players[0].setDefesas(1);
                auxDrawString = true;
                System.out.println("O jogador acabou de fazer uma defesa, atualmente: " + players[0].getDefesas() + ".");
            } else {
                helpGolDefesa = true;
                players[1].setGols(1);
                auxDrawString = true;/*para mostrar a alteração do placar na tela*/
                System.out.println("O computador acabou de fazer um gol, atualmente " + players[1].getGols() + ".");
            }

            /*marca que o computador acabou de bater um penalti*/
            players[1].setPenaltisBatidos(1);

            /*marca que o proximo batedor e o jogador*/
            batedor = 0;
        }

        return auxTemJogo;

    }

    /*para verificar se algum jogador venceu o jogo*/
    /*primeiro averiguando os casos que esta empatado:
    dois casos: empatado e mesmo nro de penaltis batidos e empatados e
    bateram nro de penaltis diferentes*/
    /*agora averiguando os casos em que não esta empatado mas pode ficar empatado
    primeiro: nao esta empatado mas pode ficar empatado se o outro fizer o gol
     */
    /* verificar quando bateram 5 penaltis...
     */
    /*verificar quando bateram mais de 5 penaltis*/
    public boolean ngmVenceu() {

        /*se estiver empatado e certeza que ninguem venceu*/
        if (empatado()) {
            System.out.println("/*se estiver empatado e certeza que ninguem venceu*/");
            return true;
        }

        /*a partir daqui são casos em que não estara empatado, pq se tivesse empatado
        tinha entrado no caso acima*/

        /*agora verificando o caso em que bateram mais de 5 penaltis*/
        if (bateramMesmoNro() && (players[0].getPenaltisBatidos() > 5) && (diferencaGols() > 0)) {
            System.out.println("/*agora verificando o caso em que bateram mais de 5 penaltis*/");
            auxTemJogo = false;
            return false;
        } else if (((players[1].getPenaltisBatidos() > 5)) && (diferencaGols() == 1) && compVencendo() && (batedor == 0)) {
            return true;
        } else if ((players[0].getPenaltisBatidos() > 5) && (diferencaGols() == 1) && jogadorVencendo() && (batedor == 1)) {
            return true;
        }

        /*agora verificando o caso em que o computador pode ganhar batendo menos de 5 penaltis,
        se o computador estiver vencendo e o jogador ainda tem penaltis a bater e esses penaltis
        podem conferir no mínimo a difença de gols*/
        if (compVencendo() && ((5 - players[0].getPenaltisBatidos()) > 0) && (5 - players[0].getPenaltisBatidos()) >= diferencaGols()) {
            System.out.println("/*agora verificando o caso em que o computador pode ganhar batendo menos de 5 penaltis*/");
            return true;
        }

        if (jogadorVencendo() && ((5 - players[1].getPenaltisBatidos()) > 0) && (5 - players[1].getPenaltisBatidos()) >= diferencaGols()) {
            System.out.println("/*agora verificando o caso em que o jogador pode ganhar batendo menos de 5 penaltis*/");
            return true;
        }

        /*verificar quando ambos bateram 5 penaltis*/
        if (bateramMesmoNro() && (players[0].getPenaltisBatidos() == 5) && (diferencaGols() > 0)) {
            System.out.println("/*verificar quando ambos bateram 5 penaltis*/");
            auxTemJogo = false;
            return false;
        }
        /*se não tiver acontecido esses casos de empate alguem venceu, então retorna false*/
        System.out.println("/*se não tiver acontecido esses casos de empate alguem venceu, então retorna false*/");
        auxTemJogo = false;
        return false;
    }

    /*retorna a diferenca de gols entre os oponentes*/
    public int diferencaGols() {
        return Math.abs(players[0].getGols() - players[1].getGols());
    }

    /*retorna true se o computador estiver vencendo*/
    public boolean compVencendo() {
        if (players[1].getGols() > players[0].getGols()) {
            return true;
        }
        return false;
    }

    /*retorna true se o jogador estiver vencendo*/
    public boolean jogadorVencendo() {
        if (players[0].getGols() > players[1].getGols()) {
            return true;
        }
        return false;
    }

    /*retorna true se o jogo estiver empatado*/
    public boolean empatado() {
        if (players[0].getGols() == players[1].getGols()) {
            return true;
        }
        return false;
    }

    /*retorna true se tanto computador quanto jogador bateram o mesmo nro de penalis*/
    public boolean bateramMesmoNro() {
        if (players[0].getPenaltisBatidos() == players[1].getPenaltisBatidos()) {
            return true;
        }
        return false;
    }

    /*chance do computador defender, pula de acordo com o nível*/
    int chanceCompDefender() {
        int x[];
        x = new int[5];
        x[0] = canto;/*canto que sera batido o penalti*/
        if (dificuldade == 0) {
            cantoComp = random.nextInt(9) + 1;/*nextInt retorna um nro 0...8*/
            System.out.println("\nNo easy a chance do computador defender e' " + cantoComp + ".");
            return cantoComp;
        } else if (dificuldade == 1) {
            for (int i = 1; i < 5; i++) {
                x[i] = random.nextInt(9) + 1;
            }
            cantoComp = x[random.nextInt(5)];
            System.out.println("\nNo medium a chance do computador defender e' " + cantoComp + ".");
            return cantoComp;
        } else {
            for (int i = 1; i < 3; i++) {
                x[i] = random.nextInt(9) + 1;
            }
            cantoComp = x[random.nextInt(3)];
            System.out.println("\nNo hard a chance do computador defender e' " + cantoComp + ".");
            return cantoComp;
        }
    }

    /*chance do pc fazer o gol, pode bater em qualquer lugar*/
    public int chanceCompFazerGol() {
        cantoComp = random.nextInt(9) + 1;
        System.out.println("\nA chance do computador fazer o gol e' " + cantoComp + ".");
        return cantoComp;
    }

    public boolean getHelpGolDefesa() {
        return helpGolDefesa;
    }

    public int getCantoComp() {
        return cantoComp;
    }

    public boolean getAuxDrawString() {
        return auxDrawString;
    }

    public void setAuxDrawString(boolean auxDrawString) {
        this.auxDrawString = auxDrawString;
    }

    public int getCanto() {
        return canto;
    }

    public void setCanto(int canto) {
        this.canto = canto;
    }

    public int getBatedor() {
        return batedor;
    }

    public void setBatedor(int batedor) {
        this.batedor = batedor;
    }

    public Jogador getComputer() {
        return players[1];
    }

    public void setComputer(Jogador computer) {
        this.players[1] = computer;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Jogador getPlayer() {
        return players[0];
    }

    public void setPlayer(Jogador player) {
        this.players[0] = player;
    }
}
