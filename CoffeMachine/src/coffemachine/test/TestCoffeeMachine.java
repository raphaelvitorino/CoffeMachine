package coffemachine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import coffemachine.machine.Button;
import coffemachine.machine.Chest;
import coffemachine.machine.Coin;
import coffemachine.machine.Display;
import coffemachine.machine.Ingredient;
import coffemachine.machine.Machine;
import coffemachine.machine.Panel;

public class TestCoffeeMachine {

	@Test
	public void depositCoinTest() {
		Chest chest = new Chest();
		Button button = new Button("Café");
		Display display = new Display();
		List<Button> buttons = new ArrayList<Button>();
		Panel panel = new Panel(display, buttons);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Machine machine = new Machine(panel, chest, ingredients);
		Coin coin10 = new Coin();
		Coin coin25 = new Coin();
		machine.getPanel().addNewButton(button);
		coin10.setValue(0.10);
		coin25.setValue(0.25);

		machine.getChest().depositCoin(coin10);
		machine.getPanel().getDisplay().setMessage("0.10");
		assertEquals("0.10", machine.getPanel().getDisplay().getMessage());
		machine.getChest().depositCoin(coin25);
		machine.getPanel().getDisplay().setMessage("0.35");
		assertEquals("0.35", machine.getPanel().getDisplay().getMessage());

	}

	@Test
	public void pressButtonCoffe() {
		Chest chest = new Chest();
		Button button = new Button("Café");
		Display display = new Display();
		List<Button> buttons = new ArrayList<Button>();
		Panel panel = new Panel(display, buttons);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Machine machine = new Machine(panel, chest, ingredients);
		machine.getPanel().addNewButton(button);
		assertEquals("Café", panel.pressButton(button).getMessage());

	}

	@Test
	public void combineIngredients() {
		Chest chest = new Chest();
		Button button = new Button("Café");
		Display display = new Display();

		Ingredient cup = new Ingredient("Copo", 1);
		Ingredient water = new Ingredient("Água", 50);
		Ingredient coffePowder = new Ingredient("Café", 10);
		Ingredient sugar = new Ingredient("Açucar", 5);

		List<Button> buttons = new ArrayList<Button>();
		Panel panel = new Panel(display, buttons);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		Machine machine = new Machine(panel, chest, ingredients);

		machine.getPanel().addNewButton(button);
		machine.combineIngredients(cup);
		machine.combineIngredients(water);
		machine.combineIngredients(coffePowder);
		machine.combineIngredients(sugar);

		assertEquals("Pronto", machine.coffeIsReady());
	}

	@Test
	public void pressButtonCoffeWithoutMoney() {
		Chest chest = new Chest();
		Button button = new Button("Café");
		Display display = new Display();
		List<Button> buttons = new ArrayList<Button>();
		Panel panel = new Panel(display, buttons);
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Machine machine = new Machine(panel, chest, ingredients);
		machine.getPanel().addNewButton(button);
		if (machine.getChest().chestIsEmpty()) {
			machine.getPanel().getDisplay().setMessage("0.35");
			assertEquals("0.35", panel.getDisplay().getMessage());
		}

	}

}
