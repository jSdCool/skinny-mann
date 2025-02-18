import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class LogicButton extends StageComponent implements Interactable {//ground component
  
  public static final Identifier ID = new Identifier("logic_button");

  int variable=-1;
  LogicButton(JSONObject data) {
    type="logic button";
    x=data.getFloat("x");
    y=data.getFloat("y");
    boolean stage_3D = data.getBoolean("s3d");
    if (stage_3D) {
      z=data.getFloat("z");
    }
    if (!data.isNull("group")) {
      group=data.getInt("group");
    }
    variable=data.getInt("variable");
  }
  
  public LogicButton(StageComponentPlacementContext context){
    type="logic button";
    x = context.getX();
    y = context.getY();
    if(context.has3D()){
      z = context.getZ();
    }
  }
  
  StageComponent copy() {
    return new LogicButton(new StageComponentPlacementContext(x, y, z));
  }
  
  StageComponent copy(float offsetX,float offsetY){
    return new LogicButton(new StageComponentPlacementContext(x+offsetX,y+offsetY));
  }
  
  StageComponent copy(float offsetX,float offsetY,float offsetZ){
    return new LogicButton(new StageComponentPlacementContext(x+offsetX,y+offsetY,z+offsetZ));
  }
  
  public LogicButton(SerialIterator iterator){
    deserial(iterator);
    variable = iterator.getInt();
  }
  
  JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x", x);
    part.setFloat("y", y);
    if (stage_3D) {
      part.setFloat("z", z);
    }
    part.setString("type", type);
    part.setInt("group", group);
    part.setInt("variable", variable);
    return part;
  }

  void draw(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    boolean state=false;
    if (source.level.multyplayerMode!=2) {

      if (variable!=-1) {
        state=source.level.variables.get(variable);
        Collider2D playerHitBox = source.players[source.currentPlayer].getHitBox2D(0,0);
        if (source.collisionDetection.collide2D(playerHitBox,Collider2D.createRectHitbox(x+group.xOffset-10,y+group.yOffset-10,20,10))) {
          source.level.variables.set(variable, true);
          if(!state){
            if(!source.levelCreator){
              source.stats.incrementButtonsActivated();
            }
          }
        } else {
          source.level.variables.set(variable, false);
        }
      }
    }
    if (variable!=-1) {
      state=source.level.variables.get(variable);
    }
    source.drawLogicButton(((x+group.xOffset)-source.drawCamPosX)*source.Scale, ((y+group.yOffset)+source.drawCamPosY)*source.Scale, source.Scale, state,render);
  }

  void draw3D(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    boolean state=false;
    if (source.level.multyplayerMode!=2) {

      if (variable!=-1) {
        state=source.level.variables.get(variable);
        Collider3D playerHitBox = source.players[source.currentPlayer].getHitBox3D(0,0,0);
        if (source.collisionDetection.collide3D(playerHitBox,Collider3D.createBoxHitBox(x+group.xOffset-10,y+group.yOffset-10,z+group.zOffset-10,20,10,20))) {
          source.level.variables.set(variable, true);
          if(!state){
            if(!source.levelCreator){
              source.stats.incrementButtonsActivated();
            }
          }
        } else {
          source.level.variables.set(variable, false);
        }
      }
    }
    if (variable!=-1) {
      state=source.level.variables.get(variable);
    }
    source.drawLogicButton((x+group.xOffset), (y+group.yOffset), (z+group.zOffset), 1, state,render);
  }

  boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x >= ((this.x+group.xOffset))-20 && x <= ((this.x+group.xOffset)) + 20 && y >= ((this.y+group.yOffset)) - 10 && y <= (this.y+group.yOffset)) {
        return true;
      }
    }
    return false;
  }

  boolean colide(float x, float y, float z, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x >= (this.x+group.xOffset) - 20 && x <= (this.x+group.xOffset) + 20 && y >= (this.y+group.yOffset) - 10 && y <= (this.y+group.yOffset) && z >= (this.z+group.zOffset) - 20 && z <= (this.z+group.zOffset) + 20) {
        return true;
      }
    }
    return false;
  }

  public void setData(int data) {
    variable=data;
  }

  public int getDataI() {
    return variable;
  }

  /**this instance of this function allows the portal to test if a player is standing on it
   
   @param data the index of the stage the button is in
   */
  public void worldInteractions(int data) {
    if (source.level.multyplayerMode==2) {
      Group group=getGroup();
      if (!group.visable)
        return;
      if (variable!=-1){
        for (int i=0; i<source.currentNumberOfPlayers; i++) {
          if (source.players[i].stage!=data)//test if the player is in the same stage as the button
            continue;
          if (source.players[i].in3D) {//test if the player is in 3d mode
            if (source.players[i].x>=(x+group.xOffset)-10&&source.players[i].x<=(x+group.xOffset)+10&&source.players[i].y >=(y+group.yOffset)-10&&source.players[i].y<= (y+group.yOffset)+2 && source.players[i].z >= (z+group.zOffset)-10 && source.players[i].z <= (z+group.zOffset)+10) {
              source.level.variables.set(variable, true);
              return;
            }
          } else {
            if (source.players[i].x>=(x+group.xOffset)-10&&source.players[i].x<=(x+group.xOffset)+10&&source.players[i].y >=(y+group.yOffset)-10&&source.players[i].y<= (y+group.yOffset)+2) {
              source.level.variables.set(variable, true);
              return;
            }
          }
        }
      source.level.variables.set(variable, false);
      }
    }
  }
  
  public Collider2D getCollider2D(){
    return null;
  }
  public Collider3D getCollider3D(){ 
    return null;
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(variable);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
  
}
