import java.io.Serializable;
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

/** I have no idea why this class exsists
*/
class GenericStageComponent extends StageComponent {
  void draw(PGraphics render){
    
  }
  
  void draw3D(PGraphics render){
    
  }
  
  StageComponent copy() {
    return this;
  }
  
  StageComponent copy(float offsetX,float offsetY){
    return this;
  }
  
  StageComponent copy(float offsetX,float offsetY,float offsetZ){
    return this;
  }
  
  JSONObject save(boolean e) {
    return null;
  }
  
  public Collider2D getCollider2D(){
    return null;
  }
  public Collider3D getCollider3D(){ 
    return null;
  }
}
