package C19401882;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class ChristinasVisual extends PApplet {

    Minim minim; 
    AudioPlayer ap;
    AudioBuffer ab; 

    float[] lerpedBuffer;

    public void settings() {
        fullScreen(P3D, SPAN);  
    }

    float y = 250;
    float lerpedY = y;

    int which = 0;

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("music.mp3", width);
        ap.play();
        ab = ap.mix; 

        colorMode(HSB);
        lerpedBuffer = new float[width];
    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '8') {
            which = keyCode - '0';
        }

        if (keyCode == ' ') {
            if (ap.isPLaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
        if (keyCode == UP)
        {
            twoCubes = ! twoCubes;
        }
    }

    
}