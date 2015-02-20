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
public class ImagemGol extends Sprite {

    public ImagemGol() throws IOException {
        super(Image.createImage("/img/gol.png"));
    }
}
