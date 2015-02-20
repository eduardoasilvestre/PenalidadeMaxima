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
/*aqui aparece um super com o nome da imagem
a largura e a altura de cada quadro do frame
 */
public class Bola extends Sprite {

    private int aux;

    public Bola() throws IOException {
        super(Image.createImage("/img/bola.png"), 10, 10);
        defineCollisionRectangle(3, 3, 1, 1);
        setFrameSequence(new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3});

    }

    /*método que move a bola em direção ao gol, os valores de
    1 a 9 são especificados de acordo com o celular*/
    public void mover(int canto) {
        switch (canto) {
            /*canto esquerdo alto*/
            case 1:
                move(-2, -3);
                this.nextFrame();
                break;

            /*centro alto*/
            case 2:
                move(0, -3);
                this.nextFrame();
                break;

            /*canto direito alto*/
            case 3:
                move(2, -3);
                this.nextFrame();
                break;

            /*canto esquerdo medio*/
            case 4:

                if (aux <= 5) {
                    move(-2, -2);
                    aux++;
                } else {
                    move(-2, -3);
                }
                this.nextFrame();
                break;


            /*centro médio*/
            case 5:
                if (aux <= 5) {
                    move(0, -2);
                    aux++;
                } else {
                    move(0, -3);
                }

                this.nextFrame();
                break;

            /*canto direito médio*/
            case 6:
                if (aux <= 5) {
                    move(+2, -2);
                    aux++;
                } else {
                    move(+2, -3);
                }
                this.nextFrame();
                break;


            /*canto esquerdo baixo*/
            case 7:
                move(-2, -2);
                this.nextFrame();
                break;

            /*centro baixo*/
            case 8:
                move(0, -2);
                this.nextFrame();
                break;

            /*direito baixo*/
            case 9:
                move(2, -2);
                this.nextFrame();
                break;
        }
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }
}
