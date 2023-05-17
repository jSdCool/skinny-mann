import java.io.Serializable;
import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

class Set3DMode extends LogicOutputComponent {
  int groupNumber=0;
  float offset=0;
  Set3DMode(float x, float y, LogicBoard lb) {
    super(x, y, "  set 3D  ", lb);
  }

  Set3DMode(JSONObject data, LogicBoard lb) {
    super(data.getFloat("x"), data.getFloat("y"), "  set 3D  ", lb, data.getJSONArray("connections"));
  }
  void tick() {
    if (inputTerminal1) {
      if (source.level.multyplayerMode!=2)
        source.e3DMode=true;
    }
    if (inputTerminal2) {
      if (source.level.multyplayerMode!=2)
        source.e3DMode=false;
    }
  }

  void draw() {
    super.draw();
    source.fill(0);
    source.textSize(15*source.Scale);
    source.textAlign(source.LEFT, source.CENTER);
    source.text("true", (x+5-source.camPos)*source.Scale, (y+16-source.camPosY)*source.Scale);
    source.text("false", (x+5-source.camPos)*source.Scale, (y+56-source.camPosY)*source.Scale);
  }
}
