import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
import processing.core.*;

/**Checkpoint stage component
*/
public class CheckPoint extends StageComponent {
  /**The checkpoint id
  */
  public static final Identifier ID = new Identifier("check_point");
  /**Load a checkpoint from saved JOSN data
  @param data The JSON Object containing the check point data
  */
  public CheckPoint(JSONObject data) {
    type="check point";
    x=data.getFloat("x");
    y=data.getFloat("y");
    boolean stage_3D = data.getBoolean("s3d");
    if (stage_3D) {
      z=data.getFloat("z");
    }
    if (!data.isNull("group")) {
      group=data.getInt("group");
    }
  }
  
  /**Place a new checkpoint
  @param context The context for the placement
  */
  public CheckPoint(StageComponentPlacementContext context){
    type="check point";
    x = context.getX();
    y = context.getY();
    if(context.has3D()){
      z = context.getZ();
    }
  }
  
  /**Create a checkpoint from serialized binarry data
  @param iterator The source of the data
  */
  public CheckPoint(SerialIterator iterator){
    deserial(iterator);
  }
  
  /**Get a JSONObject representation of this component that can be saved to a file
  @param stage_3D Wether this stage is a 3D stage
  @return JSONObject representation of this object
  */
  public JSONObject save(boolean stage_3D) {
    JSONObject part=new JSONObject();
    part.setFloat("x", x);
    part.setFloat("y", y);
    if (stage_3D) {
      part.setFloat("z", z);
    }
    part.setString("type", type);
    part.setInt("group", group);
    return part;
  }

  /**Render the 2D representation of this component.<br>
  NOTE: this method may be called more then once per frame
  @param render The surface to draw to
  */
  public void draw(StageComponentRenderContext context) {
  PGraphics render = context.getRender();
    Group group=getGroup();
    //TODO: move this off the render thread
    if (!group.visable)
      return;
    //check if the player is on top of the check point so we can turn the pole yellow and set their spawn point
    Collider2D playerBox=source.players[source.currentPlayer].getHitBox2D(0,0);
    boolean po=false;
    if (CollisionDetection.collide2D(playerBox,new Collider2D(new PVector[]{new PVector(x-3,y-60),new PVector(x+3,y-60),new PVector(x+3,y),new PVector(x-3,y)}))) {
      context.setRespawnPosition(x,y,0);
      context.setRespawnStage(context.getCurrentStageIndex());
      po=true;
      context.setCheckpointIn3D(false);
    }

    float x2=(x+group.xOffset)-context.cameraX();
    float y2=(y+group.yOffset)+context.cameraY();
    if (po) {
      render.fill(-1719293);
    } else {
      render.fill(-4605510);
    }
    float scale = context.scale();
    render.rect((x2-3)*scale, (y2-60)*scale, 5*scale, 60*scale);
    render.fill(-1441277);
    render.triangle(x2*scale, (y2-60)*scale, x2*scale, (y2-40)*scale, (x2+30)*scale, (y2-50)*scale);
  }

  /**Render the 3D representation of this component.<br>
  NOTE: this method may be called more then once per frame
  @param render The surface to draw to
  */
  public void draw3D(StageComponentRenderContext context) {
    PGraphics render = context.getRender();
    Group group=getGroup();
    if (!group.visable)
      return;
    //check if the player is on top of the check point so we can turn the pole yellow and set their spawn point
    Collider3D playerBox = context.get3DPlayerHitbox();
    boolean po=false;
    if (CollisionDetection.collide3D(playerBox,new Collider3D(new PVector[]{ new PVector(x-3,y-60,z-3),new PVector(x+3,y-60,z-3),new PVector(x+3,y,z-3),new PVector(x-3,y,z-3),new PVector(x-3,y-60,z+3),new PVector(x+3,y-60,z+3),new PVector(x+3,y,z+3),new PVector(x-3,y,z+3) } ))) {
      context.setRespawnPosition(x,y,z);
      context.setRespawnStage(context.getCurrentStageIndex());
      context.setCheckpointIn3D(true);
      po=true;
    }


    if (po){
      render.fill(-1719293);
    } else {
      render.fill(-4605510);
    }
    //strokeWeight(0);
    render.translate((x+group.xOffset), (y+group.yOffset)-30, (z+group.zOffset));
    render.box(4, 60, 4);
    render.translate(-(x+group.xOffset), -((y+group.yOffset)-30), -(z+group.zOffset));
    render.fill(-1441277);
    render.translate((x+group.xOffset)+10, (y+group.yOffset)-50, (z+group.zOffset));
    render.box(20, 20, 2);
    render.translate(-((x+group.xOffset)+10), -((y+group.yOffset)-50), -(z+group.zOffset));
  }

  /**used for mouse click detecteion
  @param x The x position of the mouse
  @param y The y position of the mouse
  @param c Check colliding with hitbox reghuardless of if the compoennt normally has collision during gameplay
  @return true if a collision is occoring
  */
  public boolean colide(float x, float y, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x>=(this.x+group.xOffset)-8 && x<= (this.x+group.xOffset)+8 && y >= (this.y+group.yOffset)-50 && y <= (this.y+group.yOffset)) {
        return true;
      }
    }
    return false;
  }

  /**used for mouse click detecteion
  @param x The x position of the mouse
  @param y The y position of the mouse
  @param z The z position of the mouse
  @param c Check colliding with hitbox reghuardless of if the compoennt normally has collision during gameplay
  @return true if a collision is occoring
  */
  public boolean colide(float x, float y, float z, boolean c) {
    Group group=getGroup();
    if (!group.visable)
      return false;
    if (c) {
      if (x>=(this.x+group.xOffset)-8 && x<= (this.x+group.xOffset)+8 && y >= (this.y+group.yOffset)-50 && y <= (this.y+group.yOffset) && z>=(this.z+group.zOffset)-8 && z<= (this.z+group.zOffset)+8 ) {
        return true;
      }
    }
    return false;
  }
  
  /**Get the 2D collision box for entitiy collisions
  @return 2D hitbox for this component or null for none
  */
  public Collider2D getCollider2D(){
    return null;
  }
  /**Get the 3D collision box for entitiy collisions
  @return 3D hitbox for this component or null for none
  */
  public Collider3D getCollider3D(){ 
    return null;
  }
  
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
}
