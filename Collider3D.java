import processing.core.*;
import java.util.ArrayList;
class Collider3D{
  
  Collider3D(PVector[] verticies){
    for(PVector p: verticies){
      vertices.add(p);
    }
    updateMin();
    updateMax();
  }
  
  private ArrayList<PVector> vertices = new ArrayList<>();
  private PVector min=new PVector(),max = new PVector();
  
  public PVector findFurthestPoint(PVector direction){
    PVector  maxPoint = null;
    float maxDistance = Float.NEGATIVE_INFINITY;
 
    for (PVector vertex : vertices) {
      float distance = PVector.dot(vertex, direction);
      if (distance > maxDistance) {
        maxDistance = distance;
        maxPoint = vertex;
      }
    }
    return maxPoint;
  }
  
  public PVector getMin(){
    return min;
  }
  
  public PVector getMax(){
    return max;
  }
  
  void updateMin(){
    for(PVector p:vertices){
      min.x = Math.min(p.x,min.x);
      min.y = Math.min(p.y,min.y);
    }
  }
  
  void updateMax(){
    for(PVector p:vertices){
      max.x = Math.max(p.x,max.x);
      max.y = Math.max(p.y,max.y);
    }
  }
}