import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

abstract class StageComponent implements Serialization {//the base class for all components that exsist inside a stage
  static transient skiny_mann source;
  public float x, y, z, dx, dy, dz;
  public int ccolor, group=-1;
  public String type;
  
  void draw() {
  }
  
  void draw3D() {
  }
  
  //used for mouse click detecteion
  boolean colide(float x, float y, boolean c) {
    return false;
  }//c= is colideing with click box
  
  boolean colide(float x, float y, float z, boolean c) {
    return false;
  }
  
  boolean colideDethPlane(float x, float Y) {
    return false;
  }
  
  abstract JSONObject save(boolean stage_3D);

  void setData(String data) {
  }
  
  void setData(int data) {
  }

  String getData() {
    return null;
  }
  
  int getDataI() {
    return -1;
  }
  
  abstract StageComponent copy();
  abstract StageComponent copy(float offsetX,float  offsetY);
  abstract StageComponent copy(float offsetX,float  offsetY,float offsetZ);
  
  Group getGroup() {
    if (group==-1)
      return new Group();
    return source.level.groups.get(group);
  }
  
  void setGroup(int grp) {
    group=grp;
  }

  void worldInteractions(int data) {
  }
  
  //used for entity collision detection 
  abstract public Collider2D getCollider2D();
  abstract public Collider3D getCollider3D();
  
  void serialize(SerializedData data){
    data.addFloat(x);
    data.addFloat(y);
    data.addFloat(z);
    data.addFloat(dx);
    data.addFloat(dy);
    data.addFloat(dz);
    data.addInt(ccolor);
    data.addInt(group);
    data.addObject(SerializedData.ofString(type));
  }
  
  void deserial(SerialIterator iterator){
    x = iterator.getFloat();
    y = iterator.getFloat();
    z = iterator.getFloat();
    dx = iterator.getFloat();
    dy = iterator.getFloat();
    dz = iterator.getFloat();
    ccolor = iterator.getInt();
    group = iterator.getInt();
    type = iterator.getString();
  }
  
}
