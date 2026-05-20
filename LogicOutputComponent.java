import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

/**A special varient of the logic component that only has an output
*/
public abstract class LogicOutputComponent extends LogicComponent {
  /**Create a logic component at the provided position with the given type
  @param x the visual x position
  @param y the visual y position
  @param type The type name to display on the component
  @param board The logic board the component is on
  */
  public LogicOutputComponent(float x, float y, String type, LogicBoard board) {
    super(x, y, type, board);
  }
  /**Creates a logic compoennet at the proviede position with the provided connections
  @param x the visual x position
  @param y the visual y position
  @param type The type name to display on the component
  @param cnects JSONArray containing a list of connections consisting of an index and terminal integers
  */
  public LogicOutputComponent(float x, float y, String type, JSONArray cnects) {
    super(x, y, type, cnects);
  }
  /**Creates a logic component from serialized data
  */
  public LogicOutputComponent(SerialIterator iterator){
    super(iterator);
  }
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    button = new Button(render, x, y, 100*uiScale, 80*uiScale, "  "+type+"  ");
  }
  
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    PVector pos = context.scaleCoord(x,y);
    button.x = pos.x;
    button.y = pos.y;
    button.draw();
    context.render.fill(-26416);
    float scaled20 = context.scale(20);
    PVector conNum1 = context.scaleCoord(x-2,y+20);
    PVector conNum2 = context.scaleCoord(x-2,y+60);
    context.render.ellipse(conNum1.x, conNum1.y, scaled20, scaled20);
    context.render.ellipse(conNum2.x, conNum2.y, scaled20, scaled20);
  }
  /**Get the position of a I/O terminal
  @param t The index of the terminal to get
  @return A float array containg 2 elemts represeting the on screen x,y coords of the terminal. NOTE: theese have allready been camera adjusted
  */
  @Override
  public float[] getTerminalPos(int t,float camX, float camY) {
    if (t==0) {
      return new float[]{x-2-camX, y+20-camY};
    }
    if (t==1) {
      return new float[]{x-2-camX, y+60-camY};
    }
    return new float[]{-1000, -1000};
  }
}
