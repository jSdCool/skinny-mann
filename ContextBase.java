/**Base infastructure for context classes
*/
interface ContextBase{
  /**A getter for dynamic content that can change during the render cycle, intended to be implmneted as a lambda
  @param <T> The type of the getter
  */
  interface DynamicProvider<T>{
    /**Get the current value of the dynamic resource
    @return The current value of the resource
    */
    T get();
  }
  
  /**A setter for dynamic content that needs to be set or midified by the render code, intended to be implmented as a lambda
  @param <T> The type of the modifier
  */
  interface DynamicModifier<T>{
    /**Update the dynamic resource with the supplied value
    @param newVal The value to update the resource to 
    */
    void set(T newVal);
  }
  
  /**A function to trigger a dynamic action. intended to be intended as a lambda. Basically its a Runnable
  */
  interface DynamicAction{
    /**Trigger the action
    */
    void go();
  }
  
  /**A funciton to get dyamic content based on dynamic input
  @param <T> The type the function returns
  @param <R> The type the function takes as a parameter
  */
  interface DynamicGetter<T,R>{
    /**Get the data
    @param input The value to use for the data
    @return The dynamic data
    */
    T get(R input);
  }
}
