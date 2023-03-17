package day04_rpg;

import java.util.*;

public class Unit {
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private int exp;
	private boolean party;
	private boolean dead;
	private Item weapon;
	private Item armor;
	private Item ring;
	
	private ArrayList<Skill> skillList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isParty() {
		return party;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getRing() {
		return ring;
	}

	public void setRing(Item ring) {
		this.ring = ring;
	}
	
	public ArrayList<Skill> getSkillList() {
		return (ArrayList<Skill>) this.skillList.clone();
	}
	
	public void addSkill(Skill skill) {
		this.skillList.add(skill);
	}
	
	private void printSkillList() {
		int num = 1;
		for(Skill skill: this.skillList) {
			System.out.printf(" [%d. %s] ", num++, skill.getName());
		}
		System.out.println();
	}

	public Unit(String n, int l, int h, int a, int d, int e) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.att = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
		this.party = false;
		this.dead = false;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
		this.skillList = new ArrayList<Skill>();
	}

	public Unit(String n, int l, int h, int x, int a, int d, int e, boolean p) {
		this.name = n;
		this.level = l;
		this.hp = h;
		this.maxHp = x;
		this.att = a;
		this.def = d;
		this.exp = e;
		this.hp = maxHp;
		this.party = p;
		this.dead = false;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
		this.skillList = new ArrayList<Skill>();
	}

	public void setItem(Item w, Item a, Item r) {
		this.weapon = w;
		this.armor = a;
		this.ring = r;
	}

	public void printStatus() {
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + level + "]");
		if (ring != null) {
			System.out.print(" [체력 : " + hp + " + " + ring.getPower());
		} else {
			System.out.print(" [체력 : " + hp);
		}
		if (ring != null) {
			System.out.println(" / " + maxHp + " + " + ring.getPower() + "]");
		} else {
			System.out.println(" / " + maxHp + "]");
		}
		if (weapon != null) {
			System.out.print("[공격력 : " + att + " + " + weapon.getPower() + "]");
		} else {
			System.out.print("[공격력 : " + att + "]");
		}
		if (armor != null) {
			System.out.print(" [방어력 : " + def + " + " + armor.getPower() + "]");
		} else {
			System.out.print(" [방어력 : " + def + "]");
		}
		System.out.println(" [파티중 : " + party + "]");
	}

	public void printEquitedItem() {
		if (weapon == null) {
			System.out.println("[무기 : 없음 ]");
		} else {
			System.out.println("[무기 : " + weapon.getName() + "]");
		}
		if (armor == null) {
			System.out.println("[방어구 : 없음 ]");
		} else {
			System.out.println("[방어구 : " + armor.getName()+ "]");
		}
		if (ring == null) {
			System.out.println("[반지 : 없음 ]");
		} else {
			System.out.println("[반지 : " + ring.getName() + "]");
		}
	}

	public void attack(Monster monster) {
		int damage = MainGame.ran.nextInt(5) + this.att;
		int monsterHp = monster.getHp();
		
		for(int i=0; i<damage; i++) {
			monsterHp--;
			if(monsterHp==0) {
				System.out.printf("%s DIED...\n", monster.getName());
				break;
			}
		}
				
		monster.setHp(monsterHp);
	}
	
	public void defense(Unit unit) {
		System.out.printf("%s는 방어 태세에 들어갔다!\n", unit.getName());
		unit.setDef(unit.getDef()*2);
	}
	
	public void checkLevelUp() {
		if(this.exp>=this.level*50) {
			this.level++;
			this.exp = 0;
			System.out.printf("%s는 레벨 %d로 올랐습니다!\n", this.name, this.level);
		}
	}
	
	public boolean useSkill(Monster monster) {
		if(this.skillList.size()==0) {
			System.out.println("보유한 스킬이 없다.");
			return false;
		}
		
		printSkillList();
		int sel = MainGame.scan.nextInt();
		
		if(sel<1 || sel>this.skillList.size())
			return false;
		
		Skill skill = skillList.get(sel-1);
		
		if(skill.getKind()==1)		skill.useSkill(monster);
		else if(skill.getKind()==2)	skill.useSkill(this);
		else if(skill.getKind()==3)	skill.useSkill(monster, this);
		
		return true;
	}
}
