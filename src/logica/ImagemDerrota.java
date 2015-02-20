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
public class ImagemDerrota extends Sprite{
    public ImagemDerrota() throws IOException {
        super(Image.createImage("/img/derrota.png"));
        setPosition(2, 25);
    }
}

