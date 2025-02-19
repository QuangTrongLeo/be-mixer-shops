package mixer_shops.mixer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int inventory;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	public Size(Long id, String name, int inventory, Color color) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = inventory;
		this.color = color;
	}
	public Size(String name, int inventory, Color color) {
		super();
		this.name = name;
		this.inventory = inventory;
		this.color = color;
	}
	public Size() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
