class LogicCompoentnPlacementContext{
  
  private float x, y;
  private LogicBoard lb;
  
  public LogicCompoentnPlacementContext(float x, float y, LogicBoard lb){
    this.x=x;
    this.y=y;
    this.lb=lb;
  }
  
  public float getX(){
    return x;
  }
  
  public float getY(){
    return y;
  }
  
  public LogicBoard getLogicBoard(){
    return lb;
  }
  
}
