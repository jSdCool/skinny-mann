import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
/**A logic component that can play a stage sound
*/
public class LogicPlaySound extends LogicComponent implements Configurable{
  
  public static final Identifier ID = new Identifier("play_sound");
  
  String soundKey="";
  /**Place a new play sound component 
  @param context The context for the placement
  */
  public LogicPlaySound(LogicCompoentnPlacementContext context) {
    super(context.getX(), context.getY(), "play sound", context.getLogicBoard());
  }
  /**Create a new play sound component from saved json data
  @param data The saved json data
  */
  public LogicPlaySound(JSONObject data) {
    super(data.getFloat("x"), data.getFloat("y"), "play sound", data.getJSONArray("connections"));
    soundKey=data.getString("sound key");
  }
  @Override
  protected void commonInit(float uiScale, PGraphics render){
    super.commonInit(uiScale,render);
    button.setText("  play sound: "+soundKey+" ");
  }
  /**Create a play sound component from serialized binarry data
  @param iterator The source of the data
  */
  public LogicPlaySound(SerialIterator iterator){
    super(iterator);
    soundKey = iterator.getString();
  }
  
  /**The function where the logic/functionality of this component is execuated
  */
  public void tick() {
    try {
      StageSound sound = source.level.sounds.get(soundKey);
      if(sound.isNarration){
        boolean isPlaying = source.soundHandler.isNarrationPlaying(sound.sound);
        if (inputTerminal1) {//if the play terminal is high then  play the sound if it is not playing
          if (!(isPlaying)) {
            source.soundHandler.playNarration(sound.sound);
          }
        }
        if (inputTerminal2) {//if the stop terminal is high then stop the sound if it is playing
          if ((isPlaying)) {
            source.soundHandler.stopNarration(sound.sound);
          }
        }
        
        outputTerminal = isPlaying;
      }else{
        boolean isPlaying = source.soundHandler.isPlaying(sound.sound)||source.soundHandler.isInQueue(sound.sound);
        if (inputTerminal1) {//if the play terminal is high then  play the sound if it is not playing
          if (!(isPlaying)) {
            source.soundHandler.addToQueue(sound.sound);
          }
        }
        if (inputTerminal2) {//if the stop terminal is high then stop the sound if it is playing
  
          if ((isPlaying)) {
            source.soundHandler.cancleSound(sound.sound);
          }
        }
        
        outputTerminal = isPlaying;
      }
    } catch(Exception e) {
    }
  }

  /**Get a JSONObject representation of this component that can be saved to a file
  @return JSONObject representation of this object
  */
  public JSONObject save() {
    JSONObject component=super.save();
    component.setString("sound key", soundKey);
    return component;
  }
  /**renders the logic component a long with its I/O terminals
  */
  @Override
  public void draw(LogicComponentRenderContext context) {
    super.draw(context);
    context.render.fill(0);
    context.render.textSize(context.scale(15));
    context.render.textAlign(PConstants.LEFT, PConstants.CENTER);
    PVector playPos = context.scaleCoord(x+5,y+16);
    context.render.text("play", playPos.x, playPos.y);
    PVector stopPos = context.scaleCoord(x+5,y+56);
    context.render.text("stop", stopPos.x, stopPos.y);
    context.render.textAlign(PConstants.RIGHT, PConstants.CENTER);
    PVector playerPos = context.scaleCoord(x+97, y+16);
    context.render.text("playing", playerPos.x, playerPos.y);
  }
  
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    data.addObject(SerializedData.ofString(soundKey));
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
      new SoundProperty(() -> soundKey, (value) -> {soundKey = value; button.setText("  play sound: "+soundKey+" ");}, "Current Sound")
    };
  }
}
