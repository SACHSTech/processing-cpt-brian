import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


 
/**
 * This is noah's file
 */
public class Sketch extends PApplet {

  //all variable declaration + images 
  PImage imgSpeedPowerup;
  PImage imgNoBulletPowerup;
  PImage imgNoBulletPowerupIcon;
  PImage imgCoin;
  PImage imgspeedindicator;
  PImage imgGameBackground;
  PImage imgPlayerCar;
  PImage imgStartscreen;
  PImage imgInstructionscreen;
  float fltPlayerX = 250;
  float fltPlayerY = 350;
  boolean boolRightPressed = false;
  boolean boolLeftPressed = false;
  boolean boolSpeedPowerupSpawn = false;
  boolean boolSpeedPowerupPickup = false;
  boolean boolRemoveBulletPowerupSpawn = false;
  boolean boolRemoveBulletPowerupPickup = false;
  boolean boolRemoveBulletPowerupActive = false;
  boolean boolCoinSpawn = false;
  boolean boolCoinPickup = false;
  final int MENUSCREEN = 0;
  final int INSTRUCTIONSCREEN = 1;
  final int GAMESCREEN = 2;
  final int TANKWINSCREEN = 3;
  final int CARWINSCREEN = 4;
  int screenState = MENUSCREEN;
  int savedTimeSpeedPowerup;
  int totalTimeSpeedPowerup = 23000;
  int SpeedPowerUpY = 50;
  float SpeedPowerupX;


  float fltGunX;
  float fltGunY;
  boolean boolRightArrowPressed;
  boolean boolLeftArrowPressed;
  boolean boolSpacePressed;
  int lives  = 3; 
  int bulletcount = 4;
  float[] bulletX = new float[4];
  float[] bulletY = new float[4];
  ArrayList<Boolean> bulletActive = new ArrayList<Boolean>();

  float carSpeed = 3;

  PImage imgBulletIndicator;
  PImage imgTank;
  PImage imgBullet;
  PImage imgLives;
  PImage imgTankWin;
  PImage imgCarWin;

  int PowerUpSpeed = 3;
  int powerupcount = 0;

  int Points = 0;

  float CoinX;
  int CoinY = 50;
  float PowerUpX;
  int PowerUpY = 50;
  int savedTimeNoBulletPowerup;
  int savedTimeCoin;
  int totalTimeNoBulletPowerup = 13000;
  int totalTimeCoin = 20000;

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

    //loading all images
    imgBulletIndicator = loadImage("bulletindicator.png");
    imgGameBackground  = loadImage("background-1_0.png");
    imgPlayerCar = loadImage("car_red_1.png");
    imgStartscreen = loadImage("Startscreen.png");
    imgInstructionscreen = loadImage("Instructionscreen.png");
    imgLives = loadImage("tag_shield.png");
    imgTank = loadImage("tank_green.png");
    imgBullet = loadImage("bullet.png");
    imgTankWin = loadImage("TANKWIN.png");
    imgNoBulletPowerup = loadImage("NoBulletPowerup.png");
    imgNoBulletPowerupIcon = loadImage("NoBulletPowerupICON.png");
    imgCoin = loadImage("Coin.png");
    imgCarWin = loadImage("carwin.png");
    imgSpeedPowerup = loadImage("SpeedPowerup.png");
    imgspeedindicator = loadImage("SpeedPowerup.png");


    //for loops that set up bullets, placing at top of screen and setting inactive
    for(int i = 0; i < bulletY.length; i++) {
      bulletY[i] = 55;
    }


      Boolean bullet1status = new Boolean(false);
      bulletActive.add(bullet1status);
      Boolean bullet2status = new Boolean(false);
      bulletActive.add(bullet2status);
      Boolean bullet3status = new Boolean(false);
      bulletActive.add(bullet3status);
      Boolean bullet4status = new Boolean(false);
      bulletActive.add(bullet4status);


   

  

    //resetting timers 
    savedTimeCoin = millis();
    savedTimeNoBulletPowerup = millis();
    savedTimeSpeedPowerup = millis();
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
    } else if (screenState == CARWINSCREEN) {
      drawCarWinScreen();
    }
     else {
      System.out.println("Something went wrong!");
    }
  }

  /**
   * This method draws the screen for when the tank wins 
   */
  public void drawTankWinScreen() {

    imgTankWin.resize(400, 500);
    image(imgTankWin, 50, 0);
    if(keyPressed) {

      //sets screenstate to game if continue is pressed
      if(key == 'c') {
        screenState = GAMESCREEN;
      }
    }
  }



  /**
   * Method to draw the screen for when the car wins 
   */
  public void drawCarWinScreen() {
    imgCarWin.resize(400, 500);
    image(imgCarWin, 50, 0);

    //returns to the game screen 
    if(keyPressed) {
      if(key == 'c') {
        screenState = GAMESCREEN;
      }
    }

  }

  /**
   * Instruction screen
   */
  public void drawInstruction(){
    imgInstructionscreen.resize(400,500);
    image(imgInstructionscreen, 50, 0);
    if (keyPressed){

      //continue botton
      if(key == 'c') {
        screenState = GAMESCREEN;
      } 
    }
  }

  
   /**
    * Method for the menu
    */
  public void drawMenu(){
    imgStartscreen.resize(400,500);
    image(imgStartscreen, 50, 0);
    if (mousePressed){

      //logic for clicking the button
      if (dist(250, 335, mouseX, mouseY) < 105){
      screenState = INSTRUCTIONSCREEN;
      }
    }
  }

  /**
   * Main game drawing method
   */
  public void drawGame(){
    imgGameBackground.resize(500, 500);

    //draws background, tank and car 
    image(imgGameBackground, 0, 0);
    
    image(imgTank, fltGunX, fltGunY);
    imgPlayerCar.resize(50, 100);
    image(imgPlayerCar, fltPlayerX, fltPlayerY);

    //if lives run out
    if(lives <= 0 ) {
      //resets game and makes tank win 
      screenState = TANKWINSCREEN;
      lives = 3;
      fltGunX = 250;
      fltPlayerX = 250;
      Points = 0;
      for(int i = 0; i < bulletY.length; i++) {
      bulletY[i] = 55;
    }

    //sets every bullet to false 
    for(int i = 0; i < bulletActive.size(); i++) {
      bulletActive.set(i, false);
    }

    boolRemoveBulletPowerupActive = false;
    boolCoinSpawn = false;
    bulletcount = 4;
    savedTimeCoin = millis();
    savedTimeNoBulletPowerup = millis();
    }

    //if car collects all points 
    if(Points >= 3) {
      
      //resets game and makes car win 
      screenState = CARWINSCREEN;
      lives = 3;
      fltGunX = 250;
      fltPlayerX = 250;
      Points = 0;

      for(int i = 0; i < bulletY.length; i++) {
        bulletY[i] = 55;
      }

      //sets every bullet to false 
    for(int i = 0; i < bulletActive.size(); i++) {
      bulletActive.set(i, false);
    }

      boolRemoveBulletPowerupActive = false;
      boolCoinSpawn = false;
      savedTimeCoin = millis();
      savedTimeNoBulletPowerup = millis();
      bulletcount = 4;
    }


    //for loop to draw bullets repeatedly
    
    for(int i = 0; i < bulletActive.size(); i++) {

      //if bullet comes close to car, collision detection 
      if(bulletActive.get(i) == true) {
        if(dist(bulletX[i]-15, bulletY[i]-30, fltPlayerX, fltPlayerY) <=30) {
          bulletY[i] = 50;

          bulletActive.set(i, false);
          bulletcount++;
          lives--;

        } else {
          continue;
        }
      }else {
        continue;
      }

    }

    imgspeedindicator.resize(40, 40);
    for(int i = 0; i < powerupcount; i++) {
      image(imgspeedindicator, 20 + 30*i, 450);
    }
    //draws the bullets, resetting when they reach the bottom of the page 
    for(int i = 0; i < bulletX.length; i++) {
      if(bulletActive.get(i) == true) {
        image(imgBullet, bulletX[i], bulletY[i]);
        bulletY[i]+=5;

        if(bulletY[i] > height) {
          bulletY[i] = 50;
          bulletActive.set(i, false);
          bulletcount++;
        }
      } else { 
      }
    }

    //these for loops draw the live, bullet, and point indicators
    for(int i = 0; i < lives; i++) {
      imgLives.resize(75, 75);
      image(imgLives, 350 + 30*i, 0);
    }

    for(int i = 0; i < Points; i++) {
      image(imgCoin, 395 + 30 * i, 80);
    }

    for(int i = 0; i < bulletcount; i++) {
      image(imgBulletIndicator, 400 + 20*i, 20);
    }

    //the following if statements handle input
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
      fltPlayerX = fltPlayerX + carSpeed;
    } 
    if(boolLeftPressed){
      fltPlayerX = fltPlayerX - carSpeed;
      }
    if(fltPlayerX > 350){
      fltPlayerX = 350;
    }
    if(fltPlayerX < 80){
      fltPlayerX = 80;
    }

    //timers and the following if statements handle timed events 
    int passedTimeNoBulletPowerup = millis() - savedTimeNoBulletPowerup;
    int passedTimeCoin = millis() - savedTimeCoin;
    int passedTimeSpeedPowoerup = millis() - savedTimeSpeedPowerup;


    if (passedTimeNoBulletPowerup > totalTimeNoBulletPowerup){
      PowerUpX = calculatecoinposition(270);
      boolRemoveBulletPowerupSpawn = true;
      savedTimeNoBulletPowerup = millis();
    }
    
    if (passedTimeCoin > totalTimeCoin){
      CoinX =  calculatecoinposition(270);
      boolCoinSpawn = true;
      savedTimeCoin = millis();
    }

    if (passedTimeSpeedPowoerup > totalTimeSpeedPowerup){
      SpeedPowerupX = random(270) + 80;
      boolSpeedPowerupSpawn = true;
      savedTimeSpeedPowerup = millis();
    }


    //the following controls powerup spawning and use 
    if (boolRemoveBulletPowerupSpawn == true) {
      imgNoBulletPowerup.resize(25, 25);
      image(imgNoBulletPowerup, PowerUpX, PowerUpY);
      PowerUpY += PowerUpSpeed;
    }
    
    if (boolCoinSpawn == true) {
      imgCoin.resize(25, 25);
      image(imgCoin, CoinX, CoinY);
      CoinY += PowerUpSpeed;
    } 
  
    if (boolSpeedPowerupSpawn == true) {
      imgSpeedPowerup.resize(25, 25);
      image(imgSpeedPowerup, SpeedPowerupX, SpeedPowerUpY);
      SpeedPowerUpY += PowerUpSpeed;
    }

    if (PowerUpY > height){
      boolRemoveBulletPowerupSpawn = false;
      PowerUpY = 50;  
    }

    if (CoinY > height){
      boolCoinSpawn = false;
      CoinY = 50;  
    }

    if (SpeedPowerUpY > height){
      boolSpeedPowerupSpawn = false;
      SpeedPowerUpY = 50;  
    }

    if (dist(PowerUpX - 25, PowerUpY, fltPlayerX, fltPlayerY) < 30){
      boolRemoveBulletPowerupPickup = true;
      boolRemoveBulletPowerupSpawn = false;
      PowerUpY = 50;  
    }

    if (dist(CoinX - 25, CoinY, fltPlayerX, fltPlayerY) < 30){
      boolCoinPickup = true;
      boolCoinSpawn = false;
      CoinY = 50;  
    }

    if (dist(SpeedPowerupX - 25, SpeedPowerUpY, fltPlayerX, fltPlayerY) < 30){
      boolSpeedPowerupPickup = true;
      boolSpeedPowerupSpawn = false;
      powerupcount = powerupcount + 1; 
      SpeedPowerUpY = 50;  
    }

    if (boolRemoveBulletPowerupPickup){
      imgNoBulletPowerupIcon.resize(50, 50);
      image(imgNoBulletPowerupIcon, 425, 450);
    }

    if (boolCoinPickup){
      Points = Points + 1;
      System.out.println("You have " + Points + " points.");
      boolCoinPickup = false;
    }

    if (boolSpeedPowerupPickup){

      carSpeed = carSpeed + 1;
      boolSpeedPowerupPickup = false;
    }

    if (boolRemoveBulletPowerupActive == true){
      for(int i = 0; i < bulletActive.size(); i++) {
        bulletActive.set(i, false);
        bulletY[i] = 50;
      }
    }
  }

  public void keyPressed() {
    if(key == 'd') {
      boolRightPressed = true;
    } 
    if(key == 'a') {
      boolLeftPressed = true;
    }
    if(key =='q' && boolRemoveBulletPowerupPickup == true) {
      boolRemoveBulletPowerupActive = true;
      boolRemoveBulletPowerupPickup = false;
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
    if(key =='q'){
      boolRemoveBulletPowerupActive = false;
    }
  }

  public void keyTyped() {
    if(key == ' ') {
      for(int i = 0; i < bulletActive.size(); i++) {
        if(bulletActive.get(i) == false) {
          bulletActive.set(i, true);
          bulletX[i] = fltGunX + 15;
          bulletcount--;
          break;
        }
      }
    }
  }


  /**
   * 
   * 
   * @param i input value to limit for the randomly generated number
   * @return returns a random position used for placing coins and powerups
   */
  public float calculatecoinposition(float i) {
    return (random(i) + 80);
  }


 
}