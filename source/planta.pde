class planta{
  int x,y,id;
  
  planta(int x_, int y_) {
    x=x_; y=y_;
  }
  
  void show() {
    fill(0,256,0);
    rect(x*l,y*l,l,l);
  }
}