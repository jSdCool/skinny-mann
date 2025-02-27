import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class ReadVariable extends LogicInputComponent {
  
  public static final Identifier ID = new Identifier("ReadVariable");
  
  int variableNumber=0;
  ReadVariable(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "read var", context.getLogicBoard());
    button.setText("read var b"+variableNumber+"  ");
  }

  ReadVariable(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "read var", data.getJSONArray("connections"));
    variableNumber=data.getInt("variable number");
    button.setText("read var b"+variableNumber+"  ");
  }
  
  public ReadVariable(SerialIterator iterator){
    super(iterator);
    variableNumber = iterator.getInt();
  }
  
  void tick() {
    outputTerminal=source.level.variables.get(variableNumber);
  }
  JSONObject save() {
    JSONObject component=super.save();
    component.setInt("variable number", variableNumber);
    return component;
  }
  void setData(int data) {
    variableNumber=data;
    button.setText("read var b"+variableNumber+"  ");
  }
  int getData() {
    return variableNumber;
  }
  
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addInt(variableNumber);
    return data; 
  }
  
  @Override
  public Identifier id() {
    return ID;
  }
}
