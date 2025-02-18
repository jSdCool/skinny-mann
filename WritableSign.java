import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class WritableSign extends StageComponent {
  
  public static final Identifier ID = new Identifier("WritableSign");
  
  String contents;
  WritableSign(JSONObject data) {
    type="WritableSign";
    x=data.getFloat("x");
    y=data.getFloat("y");
    boolean stage_3D = data.getBoolean("s3d");
    if (stage_3D) {
      z=data.getFloat("z");
    }
    contents=data.getString("contents");
    if (!data.isNull("group")) {
      group=data.getInt("group");
    }
  }
  
  public WritableSign(StageComponentPlacementContext context){
    type="WritableSign";
    x = context.getX();
    y = context.getY();
    if(context.has3D()){
      z = context.getZ();
    }
    contents="";
  }
  
  public WritableSign(SerialIterator iterator){
    deserial(iterator);
    contents = iterator.getString();
  }
  
  StageComponent copy() {
    WritableSign e=new WritableSign(new StageComponentPlacementContext(x, y, z));
    e.contents=contents;
    return  e;
  }
  
  StageComponent copy(float offsetX,float offsetY){
    WritableSign e = new WritableSign(new StageComponentPlacementContext(x+offsetX,y+offsetY));
    e.contents = contents;
    return e;
  }
  
  StageComponent copy(float offsetX,float offsetY,float offsetZ){
    WritableSign e = new WritableSign(new StageComponentPlacementContext(x+offsetX,y+offsetY,z+offsetZ));
    e.contents = contents;
    return e;
  }

  void draw(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    source.drawSign(source.Scale*((x+group.xOffset)-source.drawCamPosX), source.Scale*((y+group.yOffset)+source.drawCamPosY), source.Scale,render);

    Collider2D playerHitBox = source.players[source.currentPlayer].getHitBox2D(0,0);
    if (source.collisionDetection.collide2D(playerHitBox,Collider2D.createRectHitbox(x-35,y-40,70,40))) {//display the press e message to the player
      source.displayText="Press E";
      source.displayTextUntill=source.millis()+100;

      if (source.E_pressed) {
        source.E_pressed=false;
        source.viewingItemContents=true;
        if(!source.levelCreator){
          source.stats.incrementSignsRead();
        }
      }
    }
  }
  void draw3D(PGraphics render) {
    Group group=getGroup();
    if (!group.visable)
      return;
    source.drawSign((x+group.xOffset), (y+group.yOffset), (z+group.zOffset), source.Scale,render);

     Collider3D playerHitBox = source.players[source.currentPlayer].getHitBox3D(0,0,0);
    if (source.collisionDetection.collide3D(playerHitBox,Collider3D.createBoxHitBox(x-35,y-40,z-20,70,40,40))) {
      source.displayText="Press E";
      source.displayTextUntill=source.millis()+100;
      if (source.E_pressed) {
        source.E_pressed=false;
        source.viewingItemContents=true;
        if(!source.levelCreator){
          source.stats.incrementSignsRead();
        }
      }
    }
  }
  boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x >= ((this.x+group.xOffset))-35 && x <= ((this.x+group.xOffset)) + 35 && y >= ((this.y+group.yOffset)) - 65 && y <= (this.y+group.yOffset)) {
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
      if (x >= ((this.x+group.xOffset))-35 && x <= ((this.x+group.xOffset)) + 35 && y >= ((this.y+group.yOffset)) - 65 && y <= (this.y+group.yOffset) && z >= ((this.z+group.yOffset)) - 5 && z <= (this.z+group.zOffset)+5) {
        return true;
      }
    }
    return false;
  }

  JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x", x);
    part.setFloat("y", y);
    if (stage_3D) {
      part.setFloat("z", z);
    }
    part.setString("type", type);
    part.setString("contents", contents);
    part.setInt("group", group);
    return part;
  }

  void setData(String data) {
    contents=data;
  }

  String getData() {
    return contents;
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
    data.addObject(SerializedData.ofString(contents));
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
