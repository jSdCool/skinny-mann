import processing.core.PGraphics;
import processing.core.PVector;

/**Context infomration used by srage compnents to render content to the screene
*/
public class StageComponentRenderContext{
  
  /**Create a new component render context
  @param render The buffer that components render to
  @param screenScale The render scale of the screen
  @param camX The 2D camera x position
  @param camY The 2D camera y position
  */
  public StageComponentRenderContext(PGraphics render, float screenScale, int camX, int camY){
    this.render = render;
    scale = screenScale;
    cameraOffset = new PVector(camX,camY);
  }
  
  /**The buffer that components render to
  */
  public final PGraphics render;
  /**The scale objects should be scaled to
  */
  float scale;
  /**The position of the 2D camera. values have integer persision
  */
  PVector cameraOffset;
  
  /*
  multyplayer mode
  varaibles
  stats
  current player hitbox
  display text 
  e pressed (and reset) (name it used pressed)
  stage index (read wright)
  current stage index (read wright)
  reset selected index
  set player position
  glitch effect trigger
  
  
  make assets functions static
  */
  
  /**Get the render buffer;
  @return the graphics instance to render to
  */
  public PGraphics getRender(){
    return render;
  }
  /**Get the current scale of the UI
  @return The current scale of the ui
  */
  public float scale(){
    return scale;
  }
  /**Scale a value by the ui scale, used for 2D rendering
  @param x the value to scale
  @return the input value scaled by the scale value
  */
  public float scale(float x){
    return x*scale;
  }
  /**Get the x offset of teh 2D camera
  @return the 2D camera x value
  */
  public float cameraX(){
    return cameraOffset.x;
  }
  /**Get the y offset of teh 2D camera
  @return the 2D camera y value
  */
  public float cameraY(){
    return cameraOffset.y;
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param x The x coord to scale
  @param y The y coord to scale
  @return The input coordinates scaled to their screen position.
  */
  PVector scaleCoord(float x, float y){
    return new PVector((x-cameraX())*scale(),(y-cameraY())*scale());
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param v the coordinate to scale;
  @return The input coordinates scaled to their screen position.
  */
  PVector scaleCoord(PVector v){
    return scaleCoord(v.x,v.y);
  }
  
}
