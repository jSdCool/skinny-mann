import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**We all strive to reachn it, but only the best will.<br>
The stage component for the finish line.
*/
class Goal extends StageComponent {//ground component

  public static final Identifier ID = new Identifier("goal");
  /**Load a goal from saved JOSN data
  @param data The JSON Object containing the goal data
  */
  Goal(JSONObject data) {
    type="goal";
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
  /**Place a new goal
  @param context The context for the placement
  */
  public Goal(StageComponentPlacementContext context){
    type="goal";
    x = context.getX();
    y = context.getY();
    if(context.has3D()){
      z = context.getZ();
    }
  }
  /**Create a goal from serialized binarry data
  @param iterator The source of the data
  */
  public Goal(SerialIterator iterator){
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
  @Override
  public void draw(StageComponentRenderContext context) {
    Group group=getGroup(context.getGroupProvider());
    if (!group.visable)
      return;
    //float x2 = (x+group.xOffset)-source.drawCamPosX, y2 = (y+group.yOffset);
    PVector s1Pos = context.scaleCoord(x,y);
    PVector s2Pos = context.scaleCoord(x+100,y);
    PVector s3Pos = context.scaleCoord(x+200,y);
    PVector s4Pos = context.scaleCoord(x+50,y);
    PVector s5Pos = context.scaleCoord(x+150,y);
    float scaled50 = context.scale(50);
    context.render.fill(255);
    context.render.rect(s1Pos.x, s1Pos.y, scaled50, scaled50);
    context.render.rect(s2Pos.x, s2Pos.y, scaled50, scaled50);
    context.render.rect(s3Pos.x, s3Pos.y, scaled50, scaled50);
    context.render.fill(0);
    context.render.rect(s4Pos.x, s4Pos.y, scaled50, scaled50);
    context.render.rect(s5Pos.x, s5Pos.y, scaled50, scaled50);

    Collider2D playerHitBox = context.get2DPlayerHitbox();

    if (CollisionDetection.collide2D(playerHitBox,Collider2D.createRectHitbox(x+group.xOffset,y+group.yOffset-50,250,100))) {
      if (!context.getLevelComplete()) {
        //TODO make this run on the logic thread!!!
        context.getLevelCompleteLogicBoard().superTick(new LogicComponentTickingContext(context.getVariables(),context.getGroupProvider(),false,(d)->context.set3DMode(d), (sound) -> context.getLevelSound(sound)));
      }
      if (context.getMultyplayerMode()!=2) {
        context.completeLevel();
      } else {
        context.setEndReached();
      }
    }
  }
  /**Render the 3D representation of this component.<br>
  NOTE: this method may be called more then once per frame
  @param render The surface to draw to
  */
  @Override
  public void draw3D(StageComponentRenderContext context) {
  }
  /**used for mouse click detecteion
  @param x The x position of the mouse
  @param y The y position of the mouse
  @param c Check colliding with hitbox reghuardless of if the compoennt normally has collision during gameplay
  @return true if a collision is occoring
  */
  @Override
  public boolean colide(float x, float y, boolean c,ContextBase.DynamicProvider<ArrayList<Group>> groupProvider) {
    Group group=getGroup(groupProvider);
    if (!group.visable)
      return false;
    if (c) {
      if (x >= (this.x+group.xOffset) && x <= ((this.x+group.xOffset)) + 250 && y >= ((this.y+group.yOffset)) - 50 && y <= ((this.y+group.yOffset)) + 50) {
        return true;
      }
    }
    return false;
  }
  /**Get the 2D collision box for entitiy collisions
  @return 2D hitbox for this component or null for none
  */
  @Override
  public Collider2D getCollider2D(ContextBase.DynamicProvider<ArrayList<Group>> groupProvider){
    return null;
  }
  /**Get the 3D collision box for entitiy collisions
  @return 3D hitbox for this component or null for none
  */
  @Override
  public Collider3D getCollider3D(ContextBase.DynamicProvider<ArrayList<Group>> groupProvider){ 
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
