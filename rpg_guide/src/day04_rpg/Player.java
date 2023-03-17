package day04_rpg;

import java.util.ArrayList;

public class Player {
	private static int money;
	private static Guild guild = new Guild();
	private static Inventory inven = new Inventory();

	public Player() {
		money = 100000;
		guild.setGuild();
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int money) {
		Player.money = money;
	}

	public static Guild getGuild() {
		return guild;
	}

	public static void setGuild(Guild guild) {
		Player.guild = guild;
	}

	public static Inventory getInven() {
		return inven;
	}

	public static void setInven(Inventory inven) {
		Player.inven = inven;
	}

	public void guildMenu() {
		guild.guildMenu();
	}

	public void inventoryMenu() {
		inven.inventoryMenu();
	}

	public static ArrayList<Unit> getGuildList() {
		return guild.getGuildList();
	}

	public static ArrayList<Item> getItemList() {
		return inven.getItemList();
	}

	public static Unit getGuildUnit(int num) {
		return guild.getGuildUnit(num);
	}

	public static int getGuildSize() {
		return guild.getListSize();
	}

	public static int getItemSize() {
		return inven.getListSize();
	}
	
	public void combat() {
		Unit unit = this.guild.getPartyList()[0];
		Monster monster = new Monster("오크", 2, 15, 9, 9, 10);
		
		battle(unit, monster);
		
	}

	public void battle(Unit unit, Monster monster) {
		System.out.printf("%s와 %s의 전투!\n", unit.getName(), monster.getName());
		
		while(true) {
			printCombatMenu();
			int sel = MainGame.scan.nextInt();
			
			if(sel==1)		unit.attack(monster);
//			else if(sel==2)	unit.defense();
//			else if(sel==3)	unit.substitute();
			else if(sel==4)	{
				int ranNum = MainGame.ran.nextInt(10);
				
				if(ranNum!=0)
					break;
				else
					System.out.println("도망칠 수 없었다!");
			}
			
			if(monster.getHp()==0) {
				System.out.printf("전투에 이겨서 경험치를 %d 얻었다!\n", monster.getExp());
				break;
			}
		}
	}
	
	private void printCombatMenu() {
		System.out.println("[1.공격한다] [2.방어한다] [3.교대한다] [4.도망간다]");
	}
}
