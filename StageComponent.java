import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

abstract class StageComponent implements Serialization {//the base class for all components that exsist inside a stage
  static transient skiny_mann source;
  public float x, y, z, dx, dy, dz;
  public int ccolor, group=-1;
  public String type;
  
  public abstract void draw(PGraphics render);
  
  public abstract void draw3D(PGraphics render);
  
  //used for mouse click detecteion
  public boolean colide(float x, float y, boolean c) {
    return false;
  }//c= is colideing with click box
  
  public boolean colide(float x, float y, float z, boolean c) {
    return false;
  }
  
  public boolean colideDethPlane(float x, float Y) {
    return false;
  }
  
  public abstract JSONObject save(boolean stage_3D);

  public void setData(String data) {
  }
  
  public void setData(int data) {
  }

  public String getData() {
    return null;
  }
  
  public int getDataI() {
    return -1;
  }
  
  public abstract StageComponent copy();
  public abstract StageComponent copy(float offsetX,float  offsetY);
  public abstract StageComponent copy(float offsetX,float  offsetY,float offsetZ);
  
  public Group getGroup() {
    if (group==-1)
      return new Group();
    if(source.level == null){
      return new Group();
    }
    return source.level.groups.get(group);
  }
  
  public void setGroup(int grp) {
    group=grp;
  }

  public void worldInteractions(int data) {
  }
  
  //used for entity collision detection 
  abstract public Collider2D getCollider2D();
  abstract public Collider3D getCollider3D();
  
  public void serialize(SerializedData data){
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
  
  public void deserial(SerialIterator iterator){
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
  
  public PVector getCenter(){
     return new PVector(x+dx/2,y+dy/2,z+dz/2);
  }
  
  public float getWidth(){
    return dx;
  }
  
  public float getHeight(){
    return dy;
  }
  
  public float getDepth(){
    return dz;
  }
  
  public void setX(float x){
    this.x=x;
  }
  public void setY(float y){
    this.y=y;
  }
  public void setZ(float z){
    this.z=z;
  }
  
  public void setWidth(float w){
    dx=w;
  }
  
  public void setHeight(float h){
    dy=h;
  }
  
  public void setDepth(float d){
    dz=d;
  }
  
}
