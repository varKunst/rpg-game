package day04_rpg;

public class Monster {
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private int exp;

	public Monster(String n, int l, int h, int a, int d, int e) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.att = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
	}
	
	public void attack(Unit unit) {
		int damage = MainGame.ran.nextInt(5) + this.att;
		int unitHp = unit.getHp();
		
		for(int i=0; i<damage; i++) {
			unitHp--;
			if(unitHp==0) {
				System.out.printf("%s DIED...\n", unit.getName());
				break;
			}
		}
		
		unit.setHp(unitHp);
	}

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
}
