import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Wood  {
	Random rnd= new Random();
	//Istakamdaki taslar
	ArrayList<Stone> Stoneders = new ArrayList<Stone>();
	//Atacagim taslar
	ArrayList<Stone> droppedStones =new ArrayList<Stone>();
	//Bitirirken attýgým taslar
	ArrayList<Stone> FinStones = new ArrayList<Stone>();
	//Istakamdaki cizgiler
	ArrayList<Line> woodliner= new ArrayList<Line>();
	//TASLARI attigim rectangle ler
	ArrayList<Rectangle> DropP1 = new ArrayList<Rectangle>();
	ArrayList<Rectangle> DropP2 = new ArrayList<Rectangle>();
	ArrayList<Rectangle> DropP3 = new ArrayList<Rectangle>();
	ArrayList<Rectangle> DropP4 = new ArrayList<Rectangle>();

	ArrayList<Rectangle> FinisherPlace = new ArrayList<Rectangle>();
	int single=0;
	double CorX,CorY,Weight,Heigth,recx;
	private int PlayerID;
	private Color color;
	Color red=Color.RED;
	Color yellow=Color.GOLD;
	Color blue=Color.BLUE;
	Color green=Color.GREEN;
	Color black= Color.BLACK;
	public int getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(int PlayerID) {
		PlayerID = PlayerID;
	}
	public void setcolor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}

	public Wood(int PlayerID,double _CorX,double _CorY,double _Weight,double _Height,Color _color,Group root,ArrayList<Stone> Stoneders
			) {
		this.PlayerID = PlayerID;
		this.CorX=_CorX;
		this.CorY=_CorY;
		this.Weight=_Weight;
		this.Heigth=_Height;
		this.color=_color;
		Initialize(PlayerID,color,Stoneders, root);

	}

	public void Initialize(int PlayerID, Color color,ArrayList<Stone> Stoneders, Group root)
	{


		Rectangle rectangle= new Rectangle(CorX, CorY, Weight, Heigth);
		rectangle.setFill(color);
		rectangle.setStroke(black);

		root.getChildren().add(rectangle);

		//woodlinelari ciz

		//DrawLimits(root);

		//Eger bu kýsým olmazsa herseyden 4 tane basýyor gözükmüyor iþlemler
		if(PlayerID==1) {
			Drawwoodlines(root);
			rectangle.setStrokeWidth(3.0F);
		}

		DrawDropPlaces(root);
		LoadFirstStones(Stoneders,root);
		/* bu kisimda ekleme oluyor ama taslarin içi boyanmýyor birde boyle bakarsýn
		 * if(single==0){
		 * DrawDropPlaces(root);
		 * }
		 * */
	}
//Istakama taslari atýyorum 20 tas oyun baslýyor
	public void StartWood(Group root)
	{

		Stone stone;

		for(int i=0; i<this.Stoneders.size(); i++) {
			stone = this.Stoneders.get(i);

			root.getChildren().add(stone.getTxt());
			//ilk 20 alana 20 tas ilk 20 çizgi kýrmýzý
			woodliner.get(i).setStroke(Color.RED);

			//System.out.println(woodliner.get(i));
			//woodliner.get(i).setFill(Color.RED);

		}
	}
//FX üzerinde gösterme
	private void LoadFirstStones(ArrayList<Stone> Stoneders, Group root)
	{
		int iTasSayisi =0;
		double xcor=CorX + 30 ;
		double ycor=CorY + 60;
		Stone stone;
		iTasSayisi = this.PlayerID==1 ? 20 : 17;
		//System.out.println("Line Output");
		//Sorting Stones on the true Side
		/*Collections.sort(this.Stoneders, new Comparator<Stone>() {
			public int compare(Stone s1, Stone s2) {
				return Integer.valueOf(s1.getValue()).compareTo(s2.getValue());

			}
		})*/;
		for(int i=0; i<iTasSayisi; i++) {
			this.Stoneders.add(Stoneders.get(0));
			Stoneders.remove(0);

			if(this.PlayerID==1)
			{
				stone = this.Stoneders.get(this.Stoneders.size()-1);
				if(this.Stoneders.size()%16==0 && this.Stoneders.size()/16>0) {
					xcor=CorX + 30;
					ycor+=100;
				}else if (this.Stoneders.size()>1){
					xcor+= 80;
				}

				stone.setXcolunm(xcor-(stone.getValue()>9 ? 10 : 0));
				stone.setYline(ycor);


				stone.line = this.woodliner.get(this.Stoneders.size()-1);



			}
		}
	}

	private void Drawwoodlines(Group root)
	{
		double recx=230;
		double recy=720;
		Line woodline;
		for(int l=1; l<31; l++) {


			woodline= new Line();
			if(l!=1 && l%3==1) {
				//woodline.setFill(Color.BLACK);
				//woodline.setFill(Color.YELLOW);

			}
			//1.woodline
			if(l%16==0 && l/16!=0) {
				recx=230;
				recy+=100;
				woodline.setStartX(recx);
				woodline.setStartY(recy+30);
				woodline.setEndX(recx);
				woodline.setEndY(recy+130);

			}else {
				woodline.setStartX(recx);
				woodline.setStartY(recy+30);
				woodline.setEndX(recx);
				woodline.setEndY(recy+130);
			}
			int id=l;
			woodline.setId(Integer.toString(id));

			//woodline.setStroke(Color.RED);
			woodliner.add(woodline);
			root.getChildren().add(woodline);
			recx+=80;
		}

	}

	


	public void DrawDropPlaces(Group root) {
		//p3 number dropped
		recx=230;
		for(int c=1; c<=3; c++ ) {
			Rectangle P3_Stones =new Rectangle(recx, 260,80, 100);
			P3_Stones.setFill(Color.GHOSTWHITE);
			DropP3.add(P3_Stones);
			recx+=90;
			root.getChildren().add(P3_Stones);
		}
		recx=230;
		//p4 number dropped
		for(int c=1; c<=3; c++ ) {
			Rectangle P4_Stones =new Rectangle(recx, 560,80, 100);
			P4_Stones.setFill(Color.GHOSTWHITE);
			DropP4.add(P4_Stones);

			recx+=90;
			root.getChildren().add(P4_Stones);
		}
		recx=230;
		//p1 number dropped

		for(int c=1; c<=3; c++ ) {
			Rectangle P1_Stones =new Rectangle(recx+900, 560,80, 100);
			P1_Stones.setFill(Color.GHOSTWHITE);
			//P1_Drop.add(P1_Stones);
			recx+=90;
			DropP1.add(P1_Stones);
			root.getChildren().add(P1_Stones);
		}
		recx=230;
		//p2 number dropped
		for(int c=1; c<=3; c++ ) {
			Rectangle P2_Stones =new Rectangle(recx+900, 260,80, 100);
			P2_Stones.setFill(Color.GHOSTWHITE);
			DropP2.add(P2_Stones);
			recx+=90;

			root.getChildren().add(P2_Stones);
		}
		//Final number adding
		for(int c=1; c<=3; c++) {
			Rectangle Fin_Stones =new Rectangle(recx+160, 560,80, 100);
			Fin_Stones.setFill(Color.GHOSTWHITE);
			FinisherPlace.add(Fin_Stones);
			//FinDrp.add(Fin_Stones);

			recx+=90;
			root.getChildren().add(Fin_Stones);
		}

	}
	//Bu counterler sag mý yoksa ortayamý atýlacak ona bakýyor
	int counterRight=0;
	int counterFin=0;
	//taslarý sürükledigim yer return ettiði deger buton cýkarýp cýkarmamya bakýyor
	public int StoneDragg(double PointX, double PointY,Stone stone)
	{	

		int buttonOption=0;
		int iStatusID=0;

		/* 0 eski yerine koy
		 * 1 line a yerlestir Istaka uzerýnde
		 * 2 sagdakine koy
		 * 3 ortaya koy
		 */
		Rectangle dropping;
		int iLineWidth=80,iLineHeight=100;
		double rightSideXstart=DropP1.get(0).getX(),rightSideYstart=DropP1.get(0).getY();
		double FinisherSideXstart=FinisherPlace.get(0).getX(),FinisherSideYstart=FinisherPlace.get(0).getY();
		Line CurrentLine=null;
		boolean bIsInWood=false;
		//double StartX=this.CorX;
		//double StartY=this.CorY;
		//double FinX=StartX+this.En;
		//double FinY=StartY+this.Heigth;

		if((PointX > this.CorX) 
				&& (PointX < this.CorX + this.Weight)
				&& (PointY > this.CorY) 
				&& (PointY < this.CorY + this.Heigth)

				)
		{
			System.out.println("You are in the wood");
			bIsInWood=true;
		}
		else if(
				(PointX > rightSideXstart) 
				&& (PointX < rightSideXstart+260)
				&& (PointY > rightSideYstart) 
				&& (PointY < rightSideYstart+100)
				) {
			iStatusID=2;
			System.out.println("You are the right side");
		}else if((PointX > FinisherSideXstart) 
				&& (PointX < FinisherSideXstart+260)
				&& (PointY > FinisherSideYstart) 
				&& (PointY < FinisherSideYstart+100)) {
			System.out.println("You are the middle side");
			iStatusID=3;
		}
		else {
			System.out.println("You are Outside");
			iStatusID=0;
		}

		if(bIsInWood)
		{
			//hangi line icinde
			for(Line line: woodliner) {
				if(PointX>=line.getStartX()
						&& PointX<line.getStartX()+iLineWidth
						&& PointY>=line.getStartY()
						&& PointY<line.getStartY()+iLineHeight){

					CurrentLine = line;
					if(line.getStroke().equals(Color.RED)) {
						iStatusID = 0;
						System.out.println("busy");
					}else {
						iStatusID = 1;
						System.out.println("Empty");
						break;
					}
				}
			}
		}


		switch(iStatusID)
		{
		case 0:
			stone.setXcolunm(stone.getXcolunm());
			stone.setYline(stone.getYline());
			break;
		case 1:
			stone.line.setStroke(Color.BLACK);
			CurrentLine.setStroke(Color.RED);
			if(stone.getValue()>=10) 
			{
				stone.setXcolunm(CurrentLine.getStartX()+ 20);
				stone.setYline(CurrentLine.getStartY()+ 60);
			}else {
				stone.setXcolunm(CurrentLine.getStartX()+ 30);
				stone.setYline(CurrentLine.getStartY()+ 60);
			}
			stone.line=CurrentLine;

			break;
		case 2:

			//Drop.
			// 2 sagdakine koy
			stone.line.setStroke(Color.BLACK);
			for(int ln=0; ln<DropP1.size(); ln++) {
				double wayX=PointX-DropP1.get(ln).getX();
				double wayY=PointY-DropP1.get(ln).getY();
				double msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					//	droping=Drop.get(ln);
					//double pstX=Drop.get(ln).getX()-newTranslateX;
					//double pstY=Drop.get(ln).getY()-newTranslateY;
					if(DropP1.get(ln).getFill().equals(Color.GRAY)) {
						stone.setXcolunm(stone.getXcolunm());
						stone.setYline(stone.getYline());
						stone.line.setStroke(Color.RED);
					}else {
						if(stone.getValue()>=10) {
							stone.setXcolunm(DropP1.get(ln).getX()+ 20);
							stone.setYline(DropP1.get(ln).getY()+ 60);
						}else {
							stone.setXcolunm(DropP1.get(ln).getX()+ 30);
							stone.setYline(DropP1.get(ln).getY()+ 60);
						}
						DropP1.get(ln).setFill(Color.GRAY);
						counterRight++;
						droppedStones.add(stone);
						Stoneders.remove(stone);
					}
				}
			}

			if(counterRight==3) {
				System.out.println("Saga tas attin 3 tane");
				/*for(int a=0; a<droppedStones.size(); a++) {
					System.out.println(droppedStones.get(a).getTxt());

				}*/
				buttonOption=1;
				counterRight=0;

			}
			break;
		case 3:

			stone.line.setStroke(Color.BLACK);
			Rectangle droping;
			for(int ln=0; ln<FinisherPlace.size(); ln++) {
				double wayX=PointX-FinisherPlace.get(ln).getX();
				double wayY=PointY-FinisherPlace.get(ln).getY();
				double msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
				if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
					droping=FinisherPlace.get(ln);
					//double pstX=Drop.get(ln).getX()-newTranslateX;
					//double pstY=Drop.get(ln).getY()-newTranslateY;
					if(FinisherPlace.get(ln).getFill().equals(Color.GRAY)) {
						stone.setXcolunm(stone.getXcolunm());
						stone.setYline(stone.getYline());
						stone.line.setStroke(Color.RED);
					}else {
						if(stone.getValue()>=10) {
							stone.setXcolunm(FinisherPlace.get(ln).getX()+ 20);
							stone.setYline(FinisherPlace.get(ln).getY()+ 60);
						}else {
							stone.setXcolunm(FinisherPlace.get(ln).getX()+ 30);
							stone.setYline(FinisherPlace.get(ln).getY()+ 60);

						}
						FinisherPlace.get(ln).setFill(Color.GRAY);
						FinStones.add(stone);
						Stoneders.remove(stone);
						counterFin++;
					}
				}
			}

			if(counterFin==3) {
				counterFin=0;
				System.out.println("You put 3 stone to the corner");
				buttonOption=2;
			}
			//3 ortaya koy

			break;
		default:
			System.out.println("I don't know");

		}
		stone.getTxt().setTranslateX(0);
		stone.getTxt().setTranslateY(0);

		return buttonOption;
	}
	public Stone FindClosest(double X, double Y) {
		double diff=9999;
		int av=0;
		for(int a=0; a<Stoneders.size(); a++) {
			double DistX=X-Stoneders.get(a).getXcolunm();
			double DistY=Math.abs(Y-Stoneders.get(a).getYline());
			double dist=Math.sqrt(Math.pow(DistX, 2)+ Math.pow(DistY, 2));
			if(dist<diff) {
				av=a;
				diff=dist;
			}
		}
		return Stoneders.get(av);

	}

	public int WhereIsTaken(double PointX, double PointY) {
		double rightSideXmin=DropP1.get(0).getX(),rightSideYmin=DropP1.get(0).getY();
		double FinisherSideXmin=FinisherPlace.get(0).getX(),FinisherSideYmin=FinisherPlace.get(0).getY();
		int position=0;
		if((PointX > this.CorX) 
				&& (PointX < this.CorX + this.Weight)
				&& (PointY > this.CorY) 
				&& (PointY < this.CorY + this.Heigth)

				)
		{
			position=1;
			System.out.println("You are in the wood");
			//System.out.println("Limit X between "+this.CorX+" and "+this.CorX + this.En);
			//System.out.println("Limit Y between "+this.CorY+" and "+this.CorY + this.Heigth);

		}
		else if(
				(PointX > rightSideXmin) 
				&& (PointX < rightSideXmin+260)
				&& (PointY > rightSideYmin) 
				&& (PointY < rightSideYmin+100)
				) {
			position=2;
			System.out.println("You are the right side");
		}else if((PointX > FinisherSideXmin) 
				&& (PointX < FinisherSideXmin+260)
				&& (PointY > FinisherSideYmin) 
				&& (PointY < FinisherSideYmin+100)) {
			System.out.println("You are the middle side");
			position=3;
		}
		else {
			System.out.println("You are Outside");
			position=0;
		}
		return position;
	}


	//Oyuncunun tas atacagý alani seciyorum.
	private ArrayList<Rectangle> TargetArea(int oyuncuIndex) {

		switch(oyuncuIndex) {
		case 0:
			return DropP1;

		case 1:
			return DropP2;

		case 2:		
			return DropP3;

		case 3:
			return DropP4;


		}
		return null;
	}
/*
 * Belirlediðim atýlacak olan taslarý atýyorum burada 
*	Eger animasyonu bulursan burada yapacaðýz yapma yöntemide þöyle olsun
*	iPlayerIndex sana sýra hangi oyuncuda onu döndürecek 
*o oyuncunun ýstakasýnýn ortasýný noktasýný bul oraya doðru sürükle
*Bu method teker teker çalýþýyor içinde bir tas dönüyor for içinde 3 defa tekrarlýyor
*sana ekte attýðým dosyada animasyon örneði var
 * */
	
	public void DroppedStone_Place(int loop, int a, int iPlayerIndex, Group root) {
		int gamer=getPlayerID();
		//System.out.println("suanda "+getPlayerID());
		//System.out.println(getPlayerID());
		//System.out.println(DropP2.get(0));
		//ArrayList<Stone> thrower= droppedStones;

		System.out.println(iPlayerIndex);
		System.out.println(iPlayerIndex-1);
		System.out.println("________________________");




		ArrayList<Rectangle> RightDropSide=TargetArea(iPlayerIndex);
		ArrayList<Rectangle> LeftDropSide=TargetArea(iPlayerIndex-1);


		if(droppedStones.get(droppedStones.size()-1-a).getValue()>=10) {
			droppedStones.get(droppedStones.size()-1-a).setXcolunm(RightDropSide.get(a).getX()+20);
		}else {
			droppedStones.get(droppedStones.size()-1-a).setXcolunm(RightDropSide.get(a).getX()+30);
		}

		droppedStones.get(droppedStones.size()-1-a).setYline(RightDropSide.get(a).getY()+60);
		droppedStones.get(droppedStones.size()-1-a).getTxt().setTranslateX(0);
		droppedStones.get(droppedStones.size()-1-a).getTxt().setTranslateY(0);
		root.getChildren().add(droppedStones.get(droppedStones.size()-1-a).getTxt());
		if(loop>0) {
			if(gamer!=4) {
				root.getChildren().remove(droppedStones.get(droppedStones.size()-4-a).getTxt());
			}
		}
		//RightDropSide.get(a).setFill(Color.GRAY);
		LeftDropSide.get(a).setFill(Color.GHOSTWHITE); 

	}
	/* 
	 * 4. playerden sonra sýra bizde direkt son 3 bölüme tas koyuyor 
	 * */
	public void PutIt(int decision, int a, Stone stone, Group root) {

		//ArrayList<Rectangle> p4drop=TargetArea(3);
		stone.line=this.woodliner.get(woodliner.size()-1-a);
		//Line putterline=woodliner.get(woodliner.size()-1-a);
		if(stone.getValue()>=10){
			stone.setXcolunm(stone.line.getStartX()+20);
		}else {
			stone.setXcolunm(stone.line.getStartX()+30);
		}
		stone.setYline(stone.line.getStartY()+60);
		stone.getTxt().setTranslateX(0);
		stone.getTxt().setTranslateY(0);
		woodliner.get(woodliner.size()-1-a).setStroke(Color.RED);
		DropP4.get(a).setFill(Color.GHOSTWHITE);
		DropP1.get(a).setFill(Color.GHOSTWHITE);

		if(decision==0) {
			root.getChildren().add(stone.getTxt());

		}
		//putterline=null;


	}
	//bitirme kýsmý uðraþýyorum buu da az kaldý
	public void FinalControl() {
		ArrayList<Integer> Lines=new ArrayList<>();
		ArrayList<Integer> Numbers=new ArrayList<>();
		ArrayList<Stone> sortUpWood=new ArrayList<Stone>();
		ArrayList<Stone> sortDownWood=new ArrayList<Stone>();
		for(int a=0; a<Stoneders.size(); a++) {
			if(Stoneders.get(a).getYline()>=850) {
				sortDownWood.add(Stoneders.get(a));
			}else {
				sortUpWood.add(Stoneders.get(a));
			}
		}

		Collections.sort(sortUpWood);
		Collections.sort(sortDownWood);
		ArrayList<Stone> SortedStones= new ArrayList<Stone>();
		SortedStones.addAll(sortUpWood);
		SortedStones.addAll(sortDownWood);

		for(Stone stone: SortedStones) {
			//System.out.println(Coloring(stone.getColor())+" "+stone.getValue()+" is on the  X line");
			//System.out.println(stone.line.getId());
			//System.out.println(stone.getColor()+" "+stone.getValue() );
			for(Line dist: woodliner) {
				double msfX=stone.getXcolunm() - dist.getStartX();
				double msfY=stone.getYline() - dist.getStartY();
				if((msfX<80 && msfX>0) && (msfY<100 && msfY>0)) {
					int LnId=Integer.parseInt(dist.getId());
					Lines.add(LnId);
					Numbers.add(stone.getValue());
					System.out.println(Coloring(stone.getColor())+" "+stone.getValue()+" is on the"+LnId+" line");


				}
			}
		}
		Boolean decision=FinalDecision(Lines, Numbers); 

	}
	private Boolean FinalDecision(ArrayList<Integer> keys, ArrayList<Integer> values) {
		boolean sums = false;
		int a=0;
		while(a<keys.size()-2) {
			int LineFind=keys.get(a);
			int NextLineFind=keys.get(a+1);
			int NextNextLineFind=0;
			if(a+2<=keys.size()) {
			 NextNextLineFind=keys.get(a+2);
			}
			System.out.println("on the "+ LineFind);
			if(LineFind==15){
				a++;
			}
			if(NextLineFind-LineFind==1 && NextNextLineFind-LineFind==2 && NextLineFind!=15) {
				System.out.println(values.get(a)+" "+values.get(a+1)+" "+values.get(a+2)+" are 3 groups");
				a+=3;
			}else if(NextLineFind-LineFind==1 && NextNextLineFind-LineFind!=2) {
				System.out.println(values.get(a)+" "+values.get(a+1)+" are 2 groups");
				a+=2;
			}else if(NextLineFind-LineFind==1) {
				
				System.out.println(NextNextLineFind);
				System.out.println(values.get(a)+" "+values.get(a+1)+" are 2 groups");
				
				a+=2;
			}
			
		}

		return sums;
	}
	private String Coloring(Color fill) {
		if(fill.equals(red)) {
			return "RED";
		}else if(fill.equals(yellow)) {
			return "YELLOW";
		}else if(fill.equals(green)) {
			return "GREEN";
		}else if(fill.equals(blue)) {
			return "BLUE";
		}
		return "BLACK";
	}


}
