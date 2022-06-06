import processing.core.PApplet;
import processing.core.PImage;


 
/**
 * This is noah's file
 */
public class Sketch1 extends PApplet {
	
  PImage imgGameBackground;
  PImage imgPlayerCar;
  PImage imgStartscreen;
  PImage imgInstructionscreen;
  float fltPlayerX = 250;
  float fltPlayerY = 350;
  boolean boolRightPressed = false;
  boolean boolLeftPressed = false;
  final int MENUSCREEN = 0;
  final int INSTRUCTIONSCREEN = 1;
  final int GAMESCREEN = 2;
  final int TANKWINSCREEN = 3;
  int screenState = MENUSCREEN;

  float fltGunX;
  float fltGunY;
  boolean boolRightArrowPressed;
  boolean boolLeftArrowPressed;
  boolean boolSpacePressed;
  int lives  = 3; 
  int bulletcount = 4;
  float[] bulletX = new float[4];
  float[] bulletY = new float[4];
  boolean[] bulletActive = new boolean[4];


  PImage imgBulletIndicator;
  PImage imgTank;
  PImage imgBullet;
  PImage imgLives;
  PImage imgTankWin;



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
    background(0, 0, 0);

    imgBulletIndicator = loadImage("bulletindicator.png");
    imgGameBackground  = loadImage("background-1_0.png");
    imgPlayerCar = loadImage("car_red_1.png");
    imgStartscreen = loadImage("Startscreen.png");
    imgInstructionscreen = loadImage("Instructionscreen.png");
    imgLives = loadImage("tag_shield.png");
    imgTank = loadImage("tank_green.png");
    imgBullet = loadImage("bullet.png");
    imgTankWin = loadImage("TANKWIN.png");

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
    if (screenState == MENUSCREEN) {
      drawMenu();
    } else if (screenState == INSTRUCTIONSCREEN) {
      drawInstruction();
    } else if (screenState == GAMESCREEN) {
      drawGame();
    } else if (screenState == TANKWINSCREEN){
      drawTankWinScreen();
    }
     else {
      System.out.println("Something went wrong!");
    }
  }

  public void drawTankWinScreen() {
    imgTankWin.resize(400, 500);
    image(imgTankWin, 50, 0);
    if(keyPressed) {
      if(key == 'c') {
        screenState = GAMESCREEN;
      }
    }
  }

  public void drawInstruction(){
    imgInstructionscreen.resize(400,500);
    image(imgInstructionscreen, 50, 0);
    if (keyPressed){
      if(key == 'c') {
        
        screenState = GAMESCREEN;
        
      } 
    }
  }

  public void drawMenu(){
    imgStartscreen.resize(400,500);
    image(imgStartscreen, 50, 0);
    if (mousePressed){
      if (dist(250, 335, mouseX, mouseY) < 105){
      screenState = INSTRUCTIONSCREEN;
      }
    }
  }

  public void drawGame(){
    imgGameBackground.resize(500, 500);
    image(imgGameBackground, 0, 0);
    
    image(imgTank, fltGunX, fltGunY);
    imgPlayerCar.resize(50, 100);
    image(imgPlayerCar, fltPlayerX, fltPlayerY);

    if(lives == 0 ) {
      screenState = TANKWINSCREEN;
      lives = 3;
      fltGunX = 250;
      fltPlayerX = 250;
      for(int i = 0; i < bulletY.length; i++) {
      bulletY[i] = 55;
    }
    for(int i = 0; i < bulletActive.length; i++) {
      bulletActive[i] = false;
    }
    bulletcount = 4;
  
    }


    for(int i = 0; i < bulletActive.length; i++) {

      if(bulletActive[i] == true) {
        if(dist(bulletX[i]-15, bulletY[i]-30, fltPlayerX, fltPlayerY) <=30) {
          bulletY[i] = 50;

          bulletActive[i] = false;
          bulletcount++;
          lives--;

        } else {
          continue;
        }
      }else {
        continue;
      }

      
    }


    for(int i = 0; i < bulletX.length; i++) {
      if(bulletActive[i] == true) {
        image(imgBullet, bulletX[i], bulletY[i]);
        bulletY[i]+=5;

        if(bulletY[i] > height) {
          bulletY[i] = 50;
          bulletActive[i] = false;
          bulletcount++;
        }
      } else { 
      }
    }

    for(int i = 0; i < lives; i++) {
      imgLives.resize(75, 75);
      image(imgLives, 350 + 30*i, 0);
    }

    

    for(int i = 0; i < bulletcount; i++) {
      image(imgBulletIndicator, 400 + 20*i, 20);
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
    if (boolRightPressed) {
      fltPlayerX = fltPlayerX + 3;
    } 
    if(boolLeftPressed){
      fltPlayerX = fltPlayerX - 3;
      }
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
    if(keyCode == RIGHT) {
      boolRightArrowPressed = true;
    }

    if(keyCode == LEFT) {
      boolLeftArrowPressed = true; 
    }
  }

  public void keyReleased(){
    if(key == 'd') {
      boolRightPressed = false;
    }
    else if(key == 'a') {
      boolLeftPressed = false;
    }

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

  public void keyTyped() {
    if(key == ' ') {
      for(int i = 0; i < bulletActive.length; i++) {
        if(bulletActive[i] == false) {
          bulletActive[i] = true;
          bulletX[i] = fltGunX + 15;
          bulletcount--;
          break;
        }
      }
    }
  }
}