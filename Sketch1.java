import processing.core.PApplet;
import processing.core.PImage;



/**
 * This is noah's file
 */
public class Sketch1 extends PApplet {
	
  PImage imgBackground;
  PImage imgPlayerCar;


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
    
    image(imgPlayerCar, 100, 100);




  }
  
  // define other methods down here.
}
