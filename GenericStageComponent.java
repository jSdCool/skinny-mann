import processing.core.*;
import processing.data.*;
import java.util.ArrayList;

/** I have no idea why this class exsists
*/
class GenericStageComponent extends StageComponent {
	
  public static final Identifier ID = new Identifier("GenericStageComposnent");

  /**Not much gogin on here
  @param render not particluarly useful
  */
  @Override
  public void draw(StageComponentRenderContext context){
    
  }
  /**Not particularly useful here
  @param render Not rendering anything here
  */
  @Override
  public void draw3D(StageComponentRenderContext context){
    
  }
  
  /**No placement nessarry
  @param context There is no context here
  */
  public GenericStageComponent(StageComponentPlacementContext context){
    
  }
  
  /**I'm not serial about this
  @param iterator It is not gana be used much
  */
  public GenericStageComponent(SerialIterator iterator){
     deserial(iterator);
  }
  
  /**Too generic
  */
  public GenericStageComponent(){}
  
  /**Not gonna save this
  @param e EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
  @return null
  */
  @Override
  public JSONObject save(boolean e) {
    return null;
  }
  
  /**No collision no conflict
  @return null
  */
  @Override
  public Collider2D getCollider2D(ContextBase.DynamicProvider<ArrayList<Group>> groupProvider){
    return null;
  }
  /**A bit dimentionless
  @return null
  */
  @Override
  public Collider3D getCollider3D(ContextBase.DynamicProvider<ArrayList<Group>> groupProvider){ 
    return null;
  }
  /**Convert this component to a byte representation that can be sent over the network or saved to a file.<br>
  @return This component as a binarry representation
  */
  @Override
  public SerializedData serialize() {
    SerializedData data = new SerializedData(id());
    serialize(data);
    return data;
  }
  /**Get the id of this objet
  @return The Identifier representing this object
  */
  @Override
  public Identifier id() {
    return ID;
  }
}
