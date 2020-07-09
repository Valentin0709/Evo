//creier-input: 1-ultimul input vizual; 2-actualul input vizual; 3-ultima directie; 4-hp/100; 5-bias
int nr1=5,nr2=10,nr3=5;

class animal {
  int x,y,gen,dir;
  float hp,bi,scor;
  creier c=new creier(nr1,nr2,nr3);
  int[] dx={-1,0,1,0},dy={0,-1,0,1};
  
  void traieste() {
    c.in[1]=c.in[2];
    c.in[2]=m[trans(x+dx[dir])][trans(y+dy[dir])];
    c.in[3]=dir/3;
    c.in[4]=hp/100;
    c.in[5]=bi;
    
    c.output();
    
    hp=hp-0.25;
    
    if(c.out[1]!=1) { //sta
      if(c.out[2]==1) { //rotire dreapta
        dir++;
        if(dir==4) dir=0;
      }
      if(c.out[3]==1) { //rotire stanga
        dir--;
        if(dir==-1) dir=3;
      }
      if(c.out[4]==1) { //merge
        hp-=0.25;
        x+=dx[dir]; y+=dy[dir];
        x=trans(x); y=trans(y);
      }
      if(c.out[5]==1) { //mananca
        actualizare_plante();
        if(m[x][y]>=0) {
          hp+=20;
          if(hp>100) hp=100;
          b.remove(m[x][y]);
          actualizare_plante();
        }
      }
    }
  }
  
  void show() {
    fill(256,0,0);
    rect(x*l,y*l,l,l);
    fill(0);
    line(x*l+l/2,y*l+l/2,(x+dx[dir])*l+l/2,(y+dy[dir])*l+l/2);
  }
  
  
}