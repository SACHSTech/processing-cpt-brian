import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;



/**
 * This is noah's file
 */
public class Sketch1 extends PApplet {
	

  float[] carX = new float[2];
  float[] carY = new float[2];
  float fltGunX = 12;
  float fltGunY = 20;
  boolean boolRightArrowPressed;
  boolean boolLeftArrowPressed;
  boolean boolSpacePressed;
  int lives  = 3; 
  float[] bulletX = new float[4];
  float[] bulletY = new float[4];
  boolean[] bulletActive = new boolean[4];



  PImage imgBackground;
  PImage imgPlayerCar;
  PImage imgTank;
  PImage imgBullet;
  PImage imgLives;


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
    imgPlayerCar = loadImage("tag_shield.png");
    imgLives = loadImage("tag_shield.png");
    imgTank = loadImage("tank_green.png");
    imgBullet = loadImage("bullet.png");



    for (int i = 0; i < carX.length; i++) {

      carX[i] = 120 + random(height-200);
    }
    for(int i = 0; i < carY.length; i++) {
      carY[i] = random(width);
    }

    for(int i = 0; i < bulletY.length; i++) {
      bulletY[i] = 55;
    }

    for(int i = 0; i < bulletActive.length; i++) {
      bulletActive[i] = false;
    }
   



  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

   

    imgBackground.resize(500, 500);
    image(imgBackground, 0, 0);
    
   
    image(imgTank, fltGunX, fltGunY);

    //all 3 shoot at once 

    


    for(int i = 0; i < bulletX.length; i++) {

      if(bulletActive[i] == true) {
        image(imgBullet, bulletX[i], bulletY[i]);
        bulletY[i]+=3;

        if(bulletY[i] > height) {
          bulletY[i] = 50;
          bulletActive[i] = false;
        }
      } else {
        
      }
    }
   


    for(int i = 0; i < carY.length; i++) {

      image(imgPlayerCar, carX[i], carY[i]);
      carY[i]++;

      if(carY[i] > height) {
        carY[i] = 0;
        carX[i] = 120 + random(width-200);
      }


    }
    

    for(int i = 0; i < lives; i++) {
      imgLives.resize(75, 75);
      image(imgLives, 350 + 30*i, 0);
    }


    if (boolRightArrowPressed) {
      fltGunX = fltGunX + 2;
    }

    if(boolLeftArrowPressed) {
     fltGunX = fltGunX - 2;
    }



    if (fltGunX > 350) {
      fltGunX = 350;
    } 
    if(fltGunX < 80){
      fltGunX = 80;
    }

  }


  public void keyPressed() {

    if(keyCode == RIGHT) {
      boolRightArrowPressed = true;
    }

    if(keyCode == LEFT) {
      boolLeftArrowPressed = true; 
    }

  




  
  }

  public void keyTyped() {
    if(key == ' ') {
      

      for(int i = 0; i < bulletActive.length; i++) {
        if(bulletActive[i] == false) {
          bulletActive[i] = true;
          bulletX[i] = fltGunX + 15;
          break;
        }
      }



    }
  }


  public void keyReleased() {
    if(keyCode == RIGHT ) {
      boolRightArrowPressed = false;
    }

    if(key == ' ') {
      boolSpacePressed = false; 
    }

    if(keyCode == LEFT) {
      boolLeftArrowPressed = false;
    }

  }


  
}
