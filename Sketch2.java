import processing.core.PApplet;
import processing.core.PImage;



/**
 * This is noah's file
 */
public class Sketch2 extends PApplet {
	
  PImage imgBackground;
  PImage imgPlayerCar;
  float fltPlayerX = 250;
  float fltPlayerY = 350;
  boolean boolRightPressed = false;
  boolean boolLeftPressed = false;
  


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(500, 500);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

    imgBackground  = loadImage("background-1_0.png");
    imgPlayerCar = loadImage("car_red_1.png");

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    imgBackground.resize(500, 500);
    image(imgBackground, 0, 0);
    
    if (boolRightPressed) {
      fltPlayerX = fltPlayerX + 10;
    } 
    if(boolLeftPressed){
      fltPlayerX = fltPlayerX - 10;
      }
    imgPlayerCar.resize(50, 100);
    image(imgPlayerCar, fltPlayerX, fltPlayerY);

    if(fltPlayerX > 350){
      fltPlayerX = 350;
    }
    if(fltPlayerX < 80){
      fltPlayerX = 80;
    }
  }

  public void keyPressed() {
    if(key == 'd') {
      boolRightPressed = true;
    } 
    if(key == 'a') {
      boolLeftPressed = true;
    }
  }

  public void keyReleased(){
    if(key == 'd') {
      boolRightPressed = false;
    }
    else if(key == 'a') {
      boolLeftPressed = false;
    }
  }
}