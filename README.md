# Music Visualiser Project

Name: Christina Alferieva

Student Number: C19401882

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
For my OOP assignment I had to create a game or a visual art piece that fits the quote "Something beautiful to enjoy while listening to music." I wanted to make a visual experience, that a user could relax to while listening to and enjoying some music. The song I chose was "Your Love" by A7S, ATB and Topic. I chose it because after hearing it play on the radio I could already envision all the fun visuals and art I could create. I thought it was the perfect fit for this assignment. I made sure to include lots of colours, patterns and different 2D and 3D shapes. I have some relaxing and calming visuals for the slower parts of the song, and more entertaining and fun visuals and patterns for when the beat drops or the song speeds up. These visuals react to the music and change when keys 0 - 6 are pressed. 


# Instructions
- To stop the music and visuals press the "spacebar".

- To play the music and visuals press the "spacebar".

- To switch to the first visual press "0".

- To switch to the second visual press "1".

- To switch to the third visual press "2".

- To switch to the fourth visual press "3".

- To switch to the fifth visual press "4".

- To switch to the sixth visual press "5".

- To switch to the seventh visual press "6".

- When on visual "5", you can also press key "UP" to switch to another visual.

# How it works
The Main file runs the whole ChristinasVisual program. There is a PApplet that contains the processor signals that connect the files.

```Java
public class Main {
    
    public void ChristinasVisual()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ChristinasVisual());
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.ChristinasVisual();
    }
}
```
In the ChristinasVisual file there is a method which sets the size for every visual to be full screen and allows for 3D displays.

```Java
    public void settings() {
        fullScreen(P3D, SPAN);  
        cx = width / 2;
        cy = height / 2;
    }
```

In the setup method I loaded the song I chose for this assignment and connected the buffer to the mp3 file. Here I also set the colour.

```Java
   public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("music.mp3", width);
        ap.play();
        ab = ap.mix; 

        colorMode(HSB);
        lerpedBuffer = new float[width];
    }
```

There is a keyPressed method which allows the user to choose between visual 0 - 6. In this method the song and visuals also stop and play when the user presses the spacebar. There is another option to press the key "UP" when on visual "5".

```Java
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
            shapes = ! shapes;
        }
    }
```

Then in the draw method I had set specific keys to correspond to different visuals. I did this using a switch statement.
Each key pressed, ran the corresponding case and the visual appeared on the screen.

```Java
switch (which)
        {
            case 0:
            {
                background(255);
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 50);
                    stroke(c, 255, 250);
                    strokeWeight(2);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.5f);
            
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 2, halfHeight + lerpedBuffer[i] * halfHeight * 2, i);
                }        
                break;
            }
            case 1:
            {
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
                translate(width / 2, height / 2, 50 + (lerpedAverage * 3000));
                beginShape();
                fill(255, 204, 0);
                stroke(255);
                strokeWeight(2);
                vertex(0, -50);
                vertex(14, -20);
                vertex(47, -15);
                vertex(23, 7);
                vertex(29, 40);
                vertex(0, 25);
                vertex(-29, 40);
                vertex(-23, 7);
                vertex(-47, -15);
                vertex(-14, -20);
                endShape();    
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
                lights();
                strokeWeight(2);
                float c = map(lerpedAverage, 0, 1, 0, 300);
                stroke(c, 300, 300);
                noFill();
                angle += 0.05f;
                float s = 100 + (100 * lerpedAverage * 10);
                    
                if (! shapes)
                {
                    translate(width / 2, height / 2, 50 + (lerpedAverage * 800));
                    rotateX(angle);
                    rotateY(angle);
                    
                    beginShape();
                    vertex(-200, -200, -200);
                    vertex(200, -200, -200);
                    vertex(0, 0, 200);

                    vertex(200, -200, -200);
                    vertex(200, 200, -200);
                    vertex(0, 0, 200);

                    vertex(200, 200, -200);
                    vertex(-200, 200, -200);
                    vertex(0, 0, 200);

                    vertex(-200, 200, -200);
                    vertex(-200, -200, -200);
                    vertex(0, 0, 200);
                    endShape();
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
                break;
            }
            case 6:
            {
                float r = 1f;
                int numPoints = 5;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(3);                
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 2f + lerpedAverage;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
            }     
        }   
```

# What I am most proud of in the assignment
What I am most proud of in my assignment is the star shape in the Visual "2" and my ability to keep going and not give up. The star reacts to the music by expanding and contracting to the beat. This semester was my first time coding in Java, and after attending all lectures and labs I was able to see my coding skills improve. However, I still struggled with many things such as mapping, and getting the coordinates of shapes and objects correct. I also found it very difficult to make sure all the visuals react correctly to the music. After many different attempts to make the star sit in the center and pulse to the music, I was finally proud of what I created. I'm proud that I was able to keep going through the difficulty. Through looking back on many lectures and tutorials and watching different Youtube videos, I finally created the visual I had in mind for a long time. This star visual stood out to me as even after being stuck on it, through research and not giving up I got it completed. I was also very proud of the overall outcome of my program. 

This is the code of the star visual that I am proud of:

```Java
translate(width / 2, height / 2, 50 + (lerpedAverage * 3000));
beginShape();
fill(255, 204, 0);
stroke(255);
strokeWeight(2);
vertex(0, -50);
vertex(14, -20);
vertex(47, -15);
vertex(23, 7);
vertex(29, 40);
vertex(0, 25);
vertex(-29, 40);
vertex(-23, 7);
vertex(-47, -15);
vertex(-14, -20);
endShape(); 
```

*Visual 0:* Iterates over all the elements in the audio buffer and forms this beautiful pattern that pulses and changes to the music.

![An image](images/0.PNG)

*Visual 1:* Also iterates over all the elements in the audio buffer and shows the beat of the music along a horizontal line in the centre.

![An image](images/1.PNG)

*Visual 2:* This visual shows the beat of the music in little pulses all around the screen with a big black star in the centre. The star also expands and contracts to the music.

![An image](images/2.PNG)

*Visual 3:* As the music becomes louder and faster, in this visual three red circles on a white background pulse.

![An image](images/3.PNG)

*Visual 4:* In this visual there are 8 blue lines that move and interchange from one angle to the beat of the song.

![An image](images/4.PNG)

*Visual 5:* When key 5 is chosen there are two visuals. The first one is a 3D pyramid that rotates and pulses to the music. When the key "UP" is pressed it forms two 3D cubes also rotating and pulsing to the beat. Both of these shapes also change colour as the song goes on.

![An image](images/5.PNG)

![An image](images/5.2.PNG)

*Visual 6:* This visual creates a beautiful pattern that changes while forming allurinng spirals and designs. There are many intricate patterns which form and rotate. 

![An image](images/6.PNG)


My Youtube link (After using OBS to record the video the quality became worse):
- https://youtu.be/-1TtDx_KYmI

[![YouTube](https://youtu.be/-1TtDx_KYmI/0.JPG)](https://youtu.be/-1TtDx_KYmI)




