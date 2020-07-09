int lb=150,hb=50;

class buton {
  float x,y;
  String a;
  
  buton(int x_,int y_, String a_) {
    x=x_; y=y_; a=a_; 
  }
  
  boolean over() {
    if(mouseX>=x&&mouseX<=x+lb&&mouseY>=y&&mouseY<=y+hb) return true;
    else return false;
  }
  
  boolean click() {
    if(over()&&mousePressed) return true;
    else return false;
  }
  
  void show() {
    if(over()) fill(100);
    else fill(150);
    rect(x,y,lb,hb);
    
    fill(0);
    textAlign(CENTER,CENTER);
    textSize(16);
    text(a,x,y,lb,hb);
  }
}