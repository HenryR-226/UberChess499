package application;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

@Entity
public class GameDataBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private ArrayList<ImageView> whitePieces;
	private ArrayList<ImageView> blackPieces;

	private ListView<ImageView> capturedWhites;

	private ListView<ImageView> capturedBlacks;

	public GameDataBase() {
	}

	GameDataBase(ListView<ImageView> capturedWhites, ListView<ImageView> capturedBlacks) {

		this.capturedWhites = capturedWhites;
		this.capturedBlacks = capturedBlacks;

	}

	public Long getId() {
		return id;
	}

	public ListView<ImageView> getCapturedWhites() {
		return capturedWhites;
	}

	public ListView<ImageView> getCapturedBlacks() {
		return capturedBlacks;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", this.capturedWhites, this.capturedWhites);
	}

}
