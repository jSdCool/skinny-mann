import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A logic component to set the z offset of a group
*/
public class SetZOffset extends LogicOutputComponent implements Configurable{
  
  public static final Identifier ID = new Identifier("z-offset");
  
  int groupNumber=0;
  float offset=0;
  boolean reText = false;
  /**Place a new set z offset 
  @param context The context for the placement
  */
  public SetZOffset(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "z-offset", context.getLogicBoard());
  }
  /**Create a new set z offset from saved json data
  @param data The saved json data
  */
  public SetZOffset(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "z-offset", data.getJSONArray("connections"));
    groupNumber=data.getInt("group number");
    offset=data.getFloat("offset");
  }
  /**Create an set z offset from serialized binarry data
  @param iterator The source of the data
  */
  public SetZOffset(SerialIterator iterator){
    super(iterator);
    groupNumber = iterator.getInt();
    offset = iterator.getFloat();
  }
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    super.commonInit(uiScale,render);
    reText = true;
  }
  /**The function where the logic/functionality of this component is execuated
  */
  @Override
  public void tick(LogicComponentTickingContext context) {
    if (inputTerminal1) {
      context.getGroupProvider().get().get(groupNumber).zOffset=offset;
    }
    if (inputTerminal2) {
      context.getGroupProvider().get().get(groupNumber).zOffset=0;
    }
  }
   /**Get a JSONObject representation of this component that can be saved to a file
  @return JSONObject representation of this object
  */
  @Override
  public JSONObject save() {
    JSONObject component=super.save();
    component.setInt("group number", groupNumber);
    component.setFloat("offset", offset);
    return component;
  }
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    if(reText){
      button.setText("z-offset "+context.getGroupNames().get(groupNumber)+" by "+offset);
      reText=false;
    }
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(context.scale(15));
    context.render.textAlign(PConstants.LEFT, PConstants.CENTER);
    PVector setPos = context.scaleCoord(x+5,y+16);
    context.render.text("set", setPos.x, setPos.y);
    PVector resetPos = context.scaleCoord(x+5, y+56);
    context.render.text("reset", resetPos.x, resetPos.y);
  }
  
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(groupNumber);
    data.addFloat(offset);
    return data;
  }
  
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
  
  /**Get the properties that can be configured on this component
  @return An array of the properties that can be configured
  */
  @Override
  public Property[] getProperties(){
    return new Property[]{
      new GroupProperty(() -> groupNumber, (value) -> {groupNumber=value;reText = true;},"Current Group"),
      new IntegerProperty(() -> (int)offset, (value) -> {offset = value; reText=true;}, "Offset")
    };
  }
}
