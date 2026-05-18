/**Represents something that can be interacted with
*/
public interface Interactable{//what is this non descriptive abomination? I think this has something to do with the logic button
  /**Processes interactions between the world and this compoenent.<br>
  Executed on the logic thread.
  @param context The context for the interaction
  */
  void worldInteractions(WorldInteractionsContext context);
}
