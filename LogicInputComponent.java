//LogicInputComponent
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A special varient of the logic component that only has inputs
*/
public abstract class LogicInputComponent extends LogicComponent {
  /**Create a logic component at the provided position with the given type
  @param x the visual x position
  @param y the visual y position
  @param type The type name to display on the component
  @param board The logic board the component is on
  */
  public LogicInputComponent(float x, float y, String type, LogicBoard board) {
    super(x, y, type, board);
  }
  /**Creates a logic compoennet at the proviede position with the provided connections
  @param x the visual x position
  @param y the visual y position
  @param type The type name to display on the component
  @param cnects JSONArray containing a list of connections consisting of an index and terminal integers
  */
  public LogicInputComponent(float x, float y, String type, JSONArray cnects) {
    super(x, y, type, cnects);
  }
  /**Creates a logic component from serialized data
  @param iterator The source of the data
  */
  public LogicInputComponent(SerialIterator iterator){
    super(iterator);
  }
  
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    button=new Button(render, x, y, 100*uiScale, 40*uiScale, "  "+type+"  ");
  }
  
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    PVector pos = context.scaleCoord(x,y);
    button.x = pos.x;
    button.y = pos.y;
    button.draw();
    context.render.fill(-369706);
    float scaled20 = context.scale(20);
    PVector conNum = context.scaleCoord(x+102,y+20);
    context.render.ellipse(conNum.x, conNum.y, scaled20, scaled20);
  }
  /**Get the position of a I/O terminal
  @param t The index of the terminal to get
  @return A float array containg 2 elemts represeting the on screen x,y coords of the terminal. NOTE: theese have allready been camera adjusted
  */
  @Override
  public float[] getTerminalPos(int t,float camX, float camY) {
    if (t==2) {
      return new float[]{x+102-camX, y+20-camY};
    }
    return new float[]{-1000, -1000};
  }
}
