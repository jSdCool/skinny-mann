//NAndGate
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class NAndGate extends LogicComponent {
  
  public static final Identifier ID = new Identifier("NandGate");
  
  NAndGate(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "NAND", context.getLogicBoard());
  }

  NAndGate(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "NAND", data.getJSONArray("connections"));
  }
  
  public NAndGate(SerialIterator iterator){
    super(iterator);
  }

  void tick() {
    outputTerminal=!(inputTerminal1&&inputTerminal2);
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
