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
		int attacker = 0;
		
		while(attacker<1 || attacker>this.guild.getPartyList().size()) {
			System.out.println("누가 전투에 나갑니까?");
			this.guild.printParty();
			attacker = MainGame.scan.nextInt();			
		}
		
		Unit unit = this.guild.getPartyList().get(attacker-1);
		String[] MonsterName = {"오크", "드래곤", "슬라임", "드라큘라", "늑대인간", "깡패"};
		
		int ranIdx = MainGame.ran.nextInt(MonsterName.length);
		int mLevel = unit.getLevel() + MainGame.ran.nextInt(5) - 2;
		int mHp = MainGame.ran.nextInt(50) + 50;
		int mAtt = MainGame.ran.nextInt(5) + 5;
		int mDef = MainGame.ran.nextInt(5) + 5;
		int mExp = MainGame.ran.nextInt(10) + 10;
		Monster monster = new Monster(MonsterName[ranIdx], mLevel, mHp, mAtt, mDef, mExp);
		
		battle(unit, monster);
	}

	public void battle(Unit unit, Monster monster) {
		
		System.out.printf("%s과(와) %s의 전투!\n", unit.getName(), monster.getName());
		
		while(true) {
			printCombatMenu();
			int sel = MainGame.scan.nextInt();
			
			if(sel==1)		unit.attack(monster);
			else if(sel==2)	unit.defense(unit);
			else if(sel==3)	{
				if(!unit.useSkill(monster)) {
					continue;
				}
				
			} 
			else if(sel==4)	{
				int ranNum = MainGame.ran.nextInt(10);
				
				if(ranNum!=0)
					break;
				else
					System.out.println("도망칠 수 없었다!");
			}
			
			monster.attack(unit);
			unit.setDef(unit.getDef()/2);
			
			System.out.printf("%s의 남은 HP: %d\n", unit.getName(), unit.getHp());
			System.out.printf("%s의 남은 HP: %d\n", monster.getName(), monster.getHp());
			
			if(monster.getHp()==0) {
				int exp = monster.getExp();
				unit.setExp(unit.getExp()+exp);
				System.out.printf("전투에 이겨서 경험치를 %d 얻었다!\n", exp);
				
				int money = MainGame.ran.nextInt(1000) + 2500;
				Player.setMoney(Player.getMoney()+money);
				System.out.printf("전투에 이겨서 %d원을 얻었다!\n", money);
				
				unit.checkLevelUp();
				break;
			} else if(unit.getHp()==0) {
				System.out.println("패배했다...");
				unit.setDead(true);
				this.guild.deleteDeadUnit();
				break;
			}
		}
	}
	
	private void printCombatMenu() {
		System.out.println("[1.공격한다] [2.방어한다] [3.스킬사용] [4.도망간다]");
	}
}
