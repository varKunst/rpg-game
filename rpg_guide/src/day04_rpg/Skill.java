package day04_rpg;

public class Skill {
	
	private String name;
	private int effect;
	private int kind;
	
	public Skill(String name, int effect, int kind) {
		this.name = name;
		this.effect = effect;
		this.kind = kind;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEffect() {
		return this.effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}
	
	public int getKind() {
		return this.kind;
	}
	
	public void setKind(int kind) {
		this.kind = kind;
	}

	public void useSkill(Monster monster) {
		int damage = this.effect - monster.getDef();
		int mHp = monster.getHp();
		
		for(int i=0; i<damage; i++) {
			mHp--;
			
			if(mHp==0) {
				System.out.printf("%s is Dead...\n", monster.getName());
				break;
			}
		}
		
		monster.setHp(mHp);
	}

	public void useSkill(Unit unit) {
		
	}

	public void useSkill(Monster monster, Unit unit) {
		int damage = this.effect - monster.getDef();
		
		monster.setHp(monster.getHp() - damage);
		unit.setHp(unit.getHp() + damage);
	}
}
