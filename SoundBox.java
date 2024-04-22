import java.io.Serializable;
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
import processing.sound.*;

class SoundBox extends StageComponent {
  String soundKey="";

  SoundBox(float X, float Y) {
    x=X;
    y=Y;
    type = "sound box";
  }
  
  SoundBox(JSONObject data, boolean stage_3D) {
    type = "sound box";
    x=data.getFloat("x");
    y=data.getFloat("y");
    soundKey=data.getString("sound key");
    if (!data.isNull("group")) {
      group=data.getInt("group");
    }
  }

  void draw() {
    Group group=getGroup();
    if (!group.visable)
      return;
    source.drawSoundBox((x+group.xOffset)*source.Scale-source.drawCamPosX*source.Scale, (y+group.yOffset)*source.Scale+source.drawCamPosY*source.Scale);
    Collider2D playerHitBox = source.players[source.currentPlayer].getHitBox2D(0,0);
    if (source.collisionDetection.collide2D(playerHitBox,Collider2D.createRectHitbox(x-30,y-30,60,60))) {
      source.displayText="Press E";
      source.displayTextUntill=source.millis()+100;
      if (source.E_pressed) {
        try {
          StageSound sound = source.level.sounds.get(soundKey);
          if(sound.isNarration){
            if (!(source.soundHandler.isNarrationPlaying(sound.sound))) {
              source.soundHandler.playNarration(sound.sound);
              if(!source.levelCreator){
                source.stats.incrementSoundBoxesUsed();
              }
            }
          }else{
            if (!(source.soundHandler.isPlaying(sound.sound)||source.soundHandler.isInQueue(sound.sound))) {
              source.soundHandler.addToQueue(sound.sound);
              if(!source.levelCreator){
                source.stats.incrementSoundBoxesUsed();
              }
            }
          }
        }
        catch(Exception e) {
        }
      }
    }
  }

  boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x >= ((this.x+group.xOffset))-30 && x <= ((this.x+group.xOffset)) + 30 && y >= ((this.y+group.yOffset)) - 30 && y <= (this.y+group.yOffset)+30) {
        return true;
      }
    }
    return false;
  }

  JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x", x);
    part.setFloat("y", y);
    part.setString("type", type);
    part.setString("sound key", soundKey);
    part.setInt("group", group);
    return part;
  }

  StageComponent copy() {
    SoundBox e=new SoundBox(x, y);
    e.soundKey=soundKey;
    return  e;
  }
  
  StageComponent copy(float offsetX,float offsetY){
    SoundBox e = new SoundBox(x+offsetX,y+offsetY);
    e.soundKey=soundKey;
    return e;
  }
  
  StageComponent copy(float offsetX,float offsetY,float offsetZ){
    System.err.println("Attempted to copy sound box in 3D. This opperation is not allowed");
    return null;
  }

  void setData(String data) {
    soundKey=data;
  }

  String getData() {
    return soundKey;
  }
  
  public Collider2D getCollider2D(){
    return null;
  }
  public Collider3D getCollider3D(){ 
    return null;
  }
}
