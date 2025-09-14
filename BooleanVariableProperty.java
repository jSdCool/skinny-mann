/**Represents a configruable boolean variable property of a component<br>
Basically just an int property with a diffrent selection UI that only allows the values that correspond to variables
*/
public class BooleanVariableProperty extends IntegerProperty{
  
  /**Create a new boolean variable property with the given name
  @param getter The getter for this property
  @param setter The setter for this property
  @param name the visable name of the property
  */
  public BooleanVariableProperty(Getter<Integer> getter, Setter<Integer> setter, String name){
    super(getter,setter,name);
  }
}
