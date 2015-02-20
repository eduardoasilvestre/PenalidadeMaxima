/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Eduardo
 */

/*chama o construtor de sprite, que dividirá a imagem em frames: x e y
que ficarão indexados a partir do índice 0
o quadro atual é chamado quando o método paint é chamado*/
public class Goleiro extends Sprite {

    private int aux = 1;

    public Goleiro() throws IOException {
        super(Image.createImage("/img/goleiro.png"), 32, 33);

    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public void mover(int canto) {
        switch (canto) {
            /*canto esquerdo alto*/
            case 1:
                move(-2, 0);
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        this.setFrame(1);
                        aux++;
                    } else {
                        this.setFrame(0);
                        aux++;
                    }
                } else {
                    move(0, -1);
                    this.setFrame(2);
                }
                break;

            /*centro alto*/
            case 2:
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        this.setFrame(1);
                        aux++;
                    } else {
                        this.setFrame(0);
                        aux++;
                    }
                } else {
                    if (aux <= 15) {
                        move(0, -1);
                        this.setFrame(12);
                        aux++;
                    }

                }
                break;

            /*canto direito alto*/
            case 3:
                move(+2, 0);
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        this.setFrame(1);
                        aux++;
                    } else {
                        this.setFrame(0);
                        aux++;
                    }
                } else {
                    move(0, -1);
                    this.setFrame(7);
                }
                break;

            /*canto esquerdo medio*/
            case 4:
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        move(-2, 0);
                        this.setFrame(1);
                        aux++;
                    } else {
                        move(-2, 0);
                        this.setFrame(0);
                        aux++;
                    }
                } else if (aux == 5) {
                    move(0, -9);
                    this.setFrame(3);
                    aux++;
                } else if (aux <= 20) {
                    move(-2, 0);
                    aux++;

                }
                break;

            /*centro medio*/
            case 5:
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        move(-2, 0);
                        this.setFrame(1);
                        aux++;
                    } else {
                        move(+2, 0);
                        this.setFrame(0);
                        aux++;
                    }
                } else if (aux == 5) {
                    move(0, -5);
                    this.setFrame(12);
                    aux++;
                }
                break;

            /*centro direito*/
            case 6:
                if (aux < 5) {
                    if (this.getFrame() == 0) {
                        move(+2, 0);
                        this.setFrame(1);
                        aux++;
                    } else {
                        move(+2, 0);
                        this.setFrame(0);
                        aux++;
                    }
                } else if (aux == 5) {
                    move(0, -9);
                    this.setFrame(8);
                    aux++;
                } else if (aux <= 20) {
                    move(+2, 0);
                    aux++;

                }
                break;


            /*canto esquerdo baixo*/
            case 7:
                if (aux < 8) {
                    if (this.getFrame() == 0) {
                        move(-2, 0);
                        this.setFrame(1);
                        aux++;
                    } else {
                        move(-2, 0);
                        this.setFrame(0);
                        aux++;
                    }
                } else if (aux == 9) {
                    this.setFrame(4);
                    move(0, -2);
                    aux++;
                } else if (aux < 20) {
                    move(-2, 0);
                    aux++;

                }
                break;

            /*centro baixo*/
            case 8:
                if ((aux == 1) || (aux == 4) || (aux == 5) || (aux == 8) || (aux == 9) || (aux == 12)) {
                    if (this.getFrame() == 0) {
                        move(+4, 0);
                    }
                    this.setFrame(1);
                    aux++;
                } else if ((aux == 2) || (aux == 3) || (aux == 6) || (aux == 7) || (aux == 10) || (aux == 11)) {
                    move(-4, 0);
                    this.setFrame(0);
                    aux++;
                } else if (aux == 13) {
                    move(+17, -2);
                    this.setFrame(4);
                    aux++;
                }
                break;


            /*canto direito baixo*/
            case 9:
                if (aux < 8) {
                    if (this.getFrame() == 0) {
                        move(+2, 0);
                        this.setFrame(1);
                        aux++;
                    } else {
                        move(+2, 0);
                        this.setFrame(0);
                        aux++;
                    }
                } else if (aux == 9) {
                    this.setFrame(9);
                    move(0, -2);
                    aux++;
                } else if (aux <= 20) {
                    move(+2, 0);
                    aux++;
                }
                break;

            /*quando o jogador estiver correndo para a bola o goleiro
            fica se movimentando para amedrontar o batedor*/
            case 10:
                if ((aux == 1) || (aux == 2) || (aux == 3) || (aux == 10) || (aux == 11) || (aux == 12) || (aux == 13) || (aux == 14) || (aux == 15)) {
                    move(+2, 0);
                    if (this.getFrame() == 0) {
                        this.setFrame(1);
                    } else {
                        this.setFrame(0);
                    }
                    aux++;
                } else if ((aux == 4) || (aux == 5) || (aux == 6) || (aux == 7) || (aux == 8) || (aux == 9) || (aux == 16) || (aux == 17) || (aux == 18)) {
                    move(-2, 0);
                    if (this.getFrame() == 0) {
                        this.setFrame(1);
                    } else {
                        this.setFrame(0);
                    }
                    aux++;
                }
        }
    }
}
