/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 *
 * @author Eduardo
 */
public class CampoDeJogo extends Sprite{

    public CampoDeJogo() throws IOException  {
        super(Image.createImage("/img/field.png"));
        /*deixar o espaço em cima para se escrever com draw string
         o nome dos jogadores e o placar do jogo*/
        setPosition(2, 25);
    }
}
