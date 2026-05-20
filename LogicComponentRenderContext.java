import processing.core.*;
import java.util.ArrayList;
/**Context for rendering logic components
*/
public class LogicComponentRenderContext implements ContextBase{
  /**Create render context for logic components
  @param render The buffer that components render to
  @param variables The current boolean variables for the current level
  @param scale The render scale of the screen
  @param camX The 2D camera x position
  @param camY The 2D camera y position
  @param groupProvider A refrence to the levels group provider
  @param groupNames The current names of a level's groups
  */
  public LogicComponentRenderContext(PGraphics render,ArrayList<Boolean> variables,float scale,int camX, int camY,DynamicProvider<ArrayList<Group>> groupProvider, ArrayList<String> groupNames){
    this.render = render;
    this.variables = variables;
    this.scale = scale;
    camera = new PVector(camX,camY);
    this.groupProvider = groupProvider;
    this.groupNames = groupNames;
  }
  
  /**The buffer that components render to
  */
  public final PGraphics render;
  /**The level's boolean varibles
  */
  private ArrayList<Boolean> variables;
  /**The scale objects should be scaled to
  */
  private float scale;
  /**The 2D camera
  */
  private PVector camera;
  /**Proivdes access to the levels groups
  */
  private DynamicProvider<ArrayList<Group>> groupProvider;
  /**The string names of the level's groups
  */
  private ArrayList<String> groupNames;
  
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
  /**Get the logic boolean varibales for the current level.
  @return The list of boolean varaibles associated with the current level
  */
  public ArrayList<Boolean> getVariables(){
    return variables;
  }
  /**Get the x offset of teh 2D camera
  @return the 2D camera x value
  */
  public float cameraX(){
    return camera.x;
  }
  /**Get the y offset of teh 2D camera
  @return the 2D camera y value
  */
  public float cameraY(){
    return camera.y;
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param x The x coord to scale
  @param y The y coord to scale
  @return The input coordinates scaled to their screen position.
  */
  public PVector scaleCoord(float x, float y){
    return new PVector((x-cameraX())*scale(),(y-cameraY())*scale());
  }
  
  /**Scale a screen coordinate using both the camera offset and ui scale.
  Coodinate passed in will be transformed into the correct onscreen posiiton with the 2D camera taken into account.<br>
  The result of this funcion can directly be used in 2D rendering.
  @param v the coordinate to scale;
  @return The input coordinates scaled to their screen position.
  */
  public PVector scaleCoord(PVector v){
    return scaleCoord(v.x,v.y);
  }
  
  /**Get the group provider for the current level
  @return The group provider provided by the current level
  */
  public DynamicProvider<ArrayList<Group>> getGroupProvider(){
    return groupProvider;
  }
  /**Get the current names of the levels groups
  @return the levels group names
  */
  public ArrayList<String> getGroupNames(){
    return groupNames;
  }
}
