import processing.core.*;
import java.util.ArrayList;
import processing.data.*;
/**The alien emeny that can be found in game
*/
public class AlienEntity extends StageEntity implements Configurable{
  
  /**Place an alien
  @param context The context for the placement
  */
  public AlienEntity(StageEntityPlacementContext context){
    super(context);
  }
  
  /**Load an alien from saved JSON data
  @param data The saved JSON data
  */
  public AlienEntity(JSONObject data){
    super(data);
    maxWanderDistance = data.getInt("maxWanderDistance");
  }
  
  /**Recreate an alien from serialized binarry data
  @param iterator The source of the binarry data
  */
  public AlienEntity(SerialIterator iterator){
    super(iterator);
    maxWanderDistance = iterator.getInt();
    facingRight = iterator.getBoolean();
    dead = iterator.getBoolean();
  }
  
  //if this entity is facing / moving to the right
  boolean facingRight = true;
  
  boolean dead = false;
  
  int maxWanderDistance = 300;
  
  public static final Identifier ID = new Identifier("alien");
  
  /**Render the 2D representation of this entity.<br>
  NOTE: this method may be called more then once per frame
  @param context The context of the render
  @param render The surface to draw to
  */
  public void draw(skiny_mann context,PGraphics render){
    float scale = context.Scale;
    float drawX = (x+ (facingRight?6:-6) - context.drawCamPosX) * context.Scale;
    float drawY = (y-5 + context.drawCamPosY) * context.Scale;
    int facing = facingRight? -1:1;
    renderAlien2D(render,drawX,drawY,scale,facing);
  }
  
  public static void renderAlien2D(PGraphics render, float drawX, float drawY, float scale, int facing){
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
    float eadjust = mspc * 0.4f;
    if(!facingRight){
      eadjust *= -1;
    }
    
    //if in 3D do 3D testing thigns here
    
    //check collide with wall
    Collider2D groudLevelBox = getHitBox2D(eadjust, -10);
    boolean collided = false;
    for(Collider2D box: stageCollisions){
      if(CollisionDetection.collide2D(groudLevelBox,box)){
        collided = true;
        break;
      }
    }
    if(collided){
      facingRight = !facingRight;
      return;
    }
    
    //check collide with ground
    Collider2D underGroundBox = getHitBox2D(eadjust, 15);
    collided = false;
    for(Collider2D box: stageCollisions){
      if(CollisionDetection.collide2D(underGroundBox,box)){
        collided = true;
        break;
      }
    }
    if(!collided){
      facingRight = !facingRight;
      return;
    }
    
    float wanderDist = x - respawnX;
    
    if(facingRight){
      if(wanderDist >= maxWanderDistance){
        facingRight = false;
      }
    } else {
      if(wanderDist <= -maxWanderDistance){
        facingRight = true;
      }
    }
    
  }
  
  public MovementManager getMovementmanager(){
   return new MovementManager(){
     public boolean left(){
       return !facingRight;
     }
     
     public boolean right(){
       return facingRight;
     }
     
     public boolean jump(){
       return false;
     }
     
     public boolean in(){
       return false;
     }
     
     public boolean out(){
       return false;
     }
     
     public void reset(){
       
     }
     
     public Identifier id(){
       return null;
     }
     
     public SerializedData serialize(){
       return null;
     }
     
   }; 
  }
  
  public boolean collidesWithEntites(){
    return false;
  }
  
  public Collider3D getHitBox3D(float offsetX, float offsetY, float offsetZ){
    return null;
  }
  
  public Collider2D getHitBox2D(float offsetX, float offsetY){
    float hbx = x-24 + offsetX;
    float hby=y-35 + offsetY;
    return Collider2D.createRectHitbox(hbx,hby,48,70);
    
  }
  
  public boolean in3D(boolean playerIn3D){
    return false;
  }
  
  public Identifier id(){
    return ID;
  }
  
  public void kill(){
    dead = true;
  }
  
  public void respawn(){
    setX(respawnX);
    setY(respawnY);
    setZ(respawnZ);
    dead = false;
  }
  
  public boolean isDead(){
    return dead;
  }
  
  public SerializedData serialize(){
    SerializedData data = serializeInternal();
    data.addInt(maxWanderDistance);
    data.addBool(facingRight);
    data.addBool(dead);
    return data;
  }
  
  public JSONObject save(){
    JSONObject data = saveInternal();
    data.setInt("maxWanderDistance",maxWanderDistance);
    return data;
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
  
  @Override
  public Property[] getProperties(){
    return new Property[]{new IntegerProperty(() -> maxWanderDistance, (newValue) -> {if(newValue>0) maxWanderDistance = newValue;},"Max Wander Distance")};
  }
}
