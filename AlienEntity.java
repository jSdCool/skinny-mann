import processing.core.*;
import java.util.ArrayList;
import processing.data.*;
/**The alien emeny that can be found in game
*/
public class AlienEntity extends StageEntity{
  
  /**Place a new goon
  @param context The context for the placement
  */
  public AlienEntity(StageEntityPlacementContext context){
    this.x=context.getX();
    this.y=context.getY();
  }
  
  /**Load a goon from saved JSON data
  @param data The saved JSON data
  */
  public AlienEntity(JSONObject data){

  }
  
  /**Recreate a goon from serialized binarry data
  @param iterator The source of the binarry data
  */
  public AlienEntity(SerialIterator iterator){

  }
  
  float x=200,y=200;
  boolean facingRight = true;
  
  public static final Identifier ID = new Identifier("alien");
  
  /**Render the 2D representation of this entity.<br>
  NOTE: this method may be called more then once per frame
  @param context The context of the render
  @param render The surface to draw to
  */
  public void draw(skiny_mann context,PGraphics render){
    float scale = context.Scale;
    float drawX = (x - context.drawCamPosX) * context.Scale;
    float drawY = (y - context.drawCamPosY) * context.Scale;
    int facing = facingRight? -1:1;
    //NOTE: all value offsets will need to be multipled by scale. do not multiply the x or y variblles by scale, they allready are sclaed
    //body
    render.fill(120,152,122);
    render.triangle(drawX,drawY,drawX,drawY-15*scale,drawX+15*scale*facing,drawY-12*scale);
    render.triangle(drawX+10*scale*facing,drawY-20*scale,drawX,drawY-15*scale,drawX+15*scale*facing,drawY-12*scale);
    render.triangle(drawX+10*scale*facing,drawY-20*scale,drawX,drawY-15*scale,drawX+5*scale*facing,drawY-20*scale);
    render.triangle(drawX,drawY-10*scale,drawX+4*scale*facing,drawY+20*scale,drawX-15*scale*facing,drawY+20*scale);
    render.triangle(drawX-8*scale*facing,drawY+25*scale,drawX+4*scale*facing,drawY+20*scale,drawX-15*scale*facing,drawY+20*scale);
    render.triangle(drawX-8*scale*facing,drawY+25*scale,drawX+4*scale*facing,drawY+20*scale,drawX+2*scale*facing,drawY+25*scale);
    //head
    render.fill(25,155,17);
    render.triangle(drawX-12*scale*facing,drawY-20*scale,drawX-12*scale*facing,drawY-28*scale,drawX+30*scale*facing,drawY-20*scale);
    render.triangle(drawX-12*scale*facing,drawY-20*scale,drawX-12*scale*facing,drawY-18*scale,drawX,drawY-20*scale);
    render.triangle(drawX+20*scale*facing,drawY-20*scale,drawX+30*scale*facing,drawY-20*scale,drawX+30*scale*facing,drawY-19*scale);
    //mouth mabby
    
    //legs
    render.fill(21,193,28);
    render.triangle(drawX-8*scale*facing,drawY+25*scale,drawX-5*scale*facing,drawY+25*scale,drawX+2*scale*facing,drawY+32*scale);
    render.triangle(drawX-8*scale*facing,drawY+25*scale,drawX-1*scale*facing,drawY+32*scale,drawX+2*scale*facing,drawY+32*scale);
    render.triangle(drawX-1*scale*facing,drawY+25*scale,drawX+2*scale*facing,drawY+25*scale,drawX+9*scale*facing,drawY+32*scale);
    render.triangle(drawX-1*scale*facing,drawY+25*scale,drawX+6*scale*facing,drawY+32*scale,drawX+9*scale*facing,drawY+32*scale);
    render.triangle(drawX-8*scale*facing,drawY+40*scale,drawX-5*scale*facing,drawY+40*scale,drawX+2*scale*facing,drawY+32*scale);
    render.triangle(drawX-8*scale*facing,drawY+40*scale,drawX-1*scale*facing,drawY+32*scale,drawX+2*scale*facing,drawY+32*scale);
    render.triangle(drawX-1*scale*facing,drawY+40*scale,drawX+2*scale*facing,drawY+40*scale,drawX+9*scale*facing,drawY+32*scale);
    render.triangle(drawX-1*scale*facing,drawY+40*scale,drawX+6*scale*facing,drawY+32*scale,drawX+9*scale*facing,drawY+32*scale);
    
    //arms
    render.triangle(drawX,drawY-10*scale,drawX,drawY-13*scale,drawX-12*scale*facing,drawY-7*scale);
    render.triangle(drawX,drawY-10*scale,drawX-12*scale*facing,drawY-4*scale,drawX-12*scale*facing,drawY-7*scale);
    render.quad(drawX-12*scale*facing,drawY-4*scale, drawX-18*scale*facing,drawY-4*scale, drawX-18*scale*facing,drawY-7*scale, drawX-12*scale*facing,drawY-7*scale);
    
    render.triangle(drawX+8*scale*facing,drawY-7*scale,drawX+8*scale*facing,drawY-10*scale,drawX-12*scale*facing,drawY-2*scale);
    render.triangle(drawX+8*scale*facing,drawY-7*scale,drawX-12*scale*facing,drawY+1*scale,drawX-12*scale*facing,drawY-2*scale);
    render.quad(drawX-12*scale*facing,drawY+1*scale, drawX-16*scale*facing,drawY+1*scale, drawX-16*scale*facing,drawY-2*scale, drawX-12*scale*facing,drawY-2*scale);
  }
  
  public void draw3D(skiny_mann context,PGraphics render){
    
  }
  
  public void update(float mspc, ArrayList<Collider2D> stageCollisions){
    
  }
  
  public MovementManager getMovementmanager(){
   return new NoMovementManager(); 
  }
  
  public float getX(){
    return x;
  }
  
  public float getY(){
    return y;
  }
  
  public float getZ(){
    return 0;
  }
  
  public Entity setX(float newX){
    x=newX;
    return this;
  }
  
  public Entity setY(float newY){
    y=newY;
    return this;
  }
  
  public Entity setZ(float newZ){
    return this;
  }
  
  /**Gets the current vertical velocity of this entity
  @return the current vertical velocity
  */
  public float getVerticalVelocity(){
    return 0;
  }
  /**Set the current vertical velocity of this entity
  @param v The new velocity
  @return this
  */
  public Entity setVerticalVelocity(float newV){
    return this;
  }
  
  public boolean collidesWithEntites(){
    return false;
  }
  
  public Collider3D getHitBox3D(float offsetX, float offsetY, float offsetZ){
    return null;
  }
  
  public Collider2D getHitBox2D(float offsetX, float offsetY){
    float hbx = x+ (facingRight?-30:-18);
    float hby=y-30;
    return Collider2D.createRectHitbox(hbx,hby,48,70);
    
  }
  
  public boolean in3D(boolean playerIn3D){
    return false;
  }
  
  public Identifier id(){
    return ID;
  }
  
  public void kill(){
    
  }
  
  public void respawn(){
    
  }
  
  public boolean isDead(){
    return false;
  }
  
  public SerializedData serialize(){
    return null;
  }
  
  public JSONObject save(){
    return new JSONObject();
  }
  
  /**Get the result of the player interacting with this entity in 2D
  @param playerHitBox The hitbox of the player
  @return The result of the player interaction or null if there is no result
  */
  public PlayerIniteractionResult playerInteraction(Collider2D playerHitBox){
    return null;
  }
  /**Get the result of the player interacting with this entity in 3D
  @param playerHitBox The hitbox of the player
  @return The result of the player interaction or null if there is no result
  */
  public PlayerIniteractionResult playerInteraction(Collider3D playerHitBox){
    return null;
  }
}
