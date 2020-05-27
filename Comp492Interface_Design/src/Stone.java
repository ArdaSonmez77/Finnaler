import java.awt.Paint;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Stone implements Comparable<Stone>{
	private Color color;
	private int value;
	private int	id;
	private double Yline;
	private double Xcolunm;
	Text txt = new Text();
	//HOCA EKLEDÝ
	public Line line;
	public Stone(){
		
	}
	public Color getColor() {
		return (Color) txt.getFill();
	}
	public void setColor(Color color) {
		this.color = color;
		txt.setFill(this.color);
	}
	public int getValue() {
		return Integer.parseInt(txt.getText());
		
	}
	public void setValue(int value) {
		this.value = value;
		txt.setText(Integer.toString(value));
	}
	public int getId() {
		return Integer.parseInt(txt.getId());
	}
	public void setId(int id) {
		this.id = id;
		txt.setId(Integer.toString(id));
	}
	public double getYline() {
		return Yline;
	}
	public void setYline(double yline) {
		Yline = yline;
		txt.setY(yline);
	}
	public double getXcolunm() {
		return Xcolunm;
	}
	public void setXcolunm(double xcolunm) {
		Xcolunm = xcolunm;
		txt.setX(xcolunm);
	}
		
	public Text getTxt() {
		return txt;
	}

	public void setTxt(Text _txt) {
		this.txt = _txt;
		txt.setFont(Font.font("Verdana", 30));
	}
	
public boolean Greater10(int value) {
	if(value>=10) {
		return true;
	}
	return false;
}
@Override
public int compareTo(Stone val1) {
		if(this.getXcolunm() >= val1.getXcolunm()) {
			return 1;
		}
			return -1;

}
}
