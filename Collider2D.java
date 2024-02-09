import processing.core.*;
class Collider2D {
  PVector[] vertices;
  PVector center;
  private PVector min=new PVector(),max = new PVector();
  public Collider2D(PVector[] vertices) {
    this.vertices=vertices;
    float cx=0, cy=0, cz=0;
    for (int i=0; i<vertices.length; i++) {
      cx+=vertices[i].x;
      cy+=vertices[i].y;
      cx+=vertices[i].z;
      
      updateMin();
      updateMax();
    }
    center=new PVector(cx/vertices.length, cy/vertices.length, cz/vertices.length);
  }
  
  public PVector furthestPoint(PVector d) {
    float largestDP=0;
    int indexof=0;
    for (int i=0; i<vertices.length; i++) {
      PVector np = new PVector();
      PVector.sub(center,vertices[i],np);
      float dp=d.dot(np);
      if (dp>largestDP) {
        largestDP=dp;
        indexof=i;
      }
    }
    return vertices[indexof];
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
