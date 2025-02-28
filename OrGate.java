//OrGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class OrGate extends LogicComponent {
  
  public static final Identifier ID = new Identifier("OR");
  
  OrGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "OR", context.getLogicBoard());
  }
  OrGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "OR", data.getJSONArray("connections"));
  }
  
  public OrGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=inputTerminal1||inputTerminal2;
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
