//AndGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class AndGate extends LogicComponent {
  public static final Identifier ID = new Identifier("AND");
  
  AndGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "AND", context.getLogicBoard());
  }

  AndGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "AND", data.getJSONArray("connections"));
  }
  
  public AndGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=inputTerminal1&&inputTerminal2;
  }
  
  //
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
