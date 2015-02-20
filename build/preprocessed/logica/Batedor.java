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

/*o batedor é composto de 5 frames de 32x32*/
public class Batedor extends Sprite {
    public Batedor() throws IOException {
        super(Image.createImage("/img/correndo.png"),32,32);
        setFrameSequence(new int[] {1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,3,4});
    }
    /*move(dx,dy): dx positivo movendo para a direita, dx negativo
         movendo para a esquerda; dy positivo movendo para baixo,
         dy negativa movendo para a cima*/
    public void mover() {
        move(+2,-1);
        this.nextFrame();
        
    }
}
