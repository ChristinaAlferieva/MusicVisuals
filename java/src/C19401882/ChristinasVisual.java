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
        cx = width / 2;
        cy = height / 2;
    }

    float y = 200;
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

    float cx;
    float cy;

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '6') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.play();
            }
        }
        if (keyCode == UP)
        {
            twoCubes = ! twoCubes;
        }
    }
    

    float lerpedAverage = 0;
    private float angle = 0;

    private boolean twoCubes = false;
        
    public void draw() {
        background(0);
        stroke(200);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < ab.size(); i ++)
        {
            sum += abs(ab.get(i));
        }
        average = sum / ab.size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch (which)
        {
            case 0:
            {
                background(255);
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }        
                break;
            }
            case 1:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }        
                break;
            }
            case 2:
            {
                background(180);
                for (int i = 0; i < ab.size(); i++) {

                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
                lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                line(0, i, lerpedBuffer[i] * halfHeight * 4, i);
                line(width, i, width - (lerpedBuffer[i] * halfHeight * 4), i);
                line(i, 0, i, lerpedBuffer[i] * halfHeight * 4);
                line(i, height, i, height - (lerpedBuffer[i] * halfHeight * 4));
                }        
                break;
            }
            case 3:
            {
                background(255);
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                fill(255, 200, 250);
                ellipse(width / 2, height / 2, 50 + (lerpedAverage * 800), 50 + (lerpedAverage * 800)); 
                ellipse(width / 4, height / 2, 50 + (lerpedAverage * 800), 50 + (lerpedAverage * 800));               
                ellipse(width * 0.75f, height / 2, 50 + (lerpedAverage * 800), 50 + (lerpedAverage * 800));
                break;
            }
            case 4:
            {
                stroke(175, 100, 220);
                strokeWeight(2);
                int lines = 8;
                float thetaInc = TWO_PI / (float) lines; 
                float radius = 920;
                for(int i = 0; i < lines; i++)
                {
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float angle = theta * i;
                    float x = sin(angle) * radius;
                    float y = cos(angle) * radius;
                    line(cx * 38, cy * 20, cx + x, cy + y);

                }
                break;
            }
            case 5:
            {
                float r = 1f;
                int numPoints = 3;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);                
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedAverage;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                break;
            }
            case 6:
            {
                lights();
                strokeWeight(2);
                float c = map(lerpedAverage, 0, 1, 0, 300);
                stroke(c, 300, 300);
                noFill();
                angle += 0.05f;
                float s = 100 + (100 * lerpedAverage * 10);
                    
                if (! twoCubes)
                {
                    translate(width / 2, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                }
                else
                {
                    pushMatrix();
                    translate(width / 4, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();

                    pushMatrix();
                    translate(width * 0.75f, height / 2, 0);
                    rotateY(angle);
                    rotateX(angle);
                    box(s);
                    popMatrix();
                }
            }     
        }   
    }
}
