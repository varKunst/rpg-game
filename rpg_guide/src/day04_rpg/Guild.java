package day04_rpg;

import java.util.ArrayList;

public class Guild {
	private final int PARTY_SIZE = 4;
	private ArrayList<Unit> guildList = new ArrayList<>();
	private Unit[] partyList;

	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		this.guildList.add(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		this.guildList.add(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		this.guildList.add(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		this.guildList.add(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		this.guildList.add(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		this.guildList.add(temp);
		for (int i = 0; i < 4; i++) {
			this.guildList.get(i).setParty(true);
		}
		this.partyList = new Unit[PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < this.guildList.size(); i++) {
			if (this.guildList.get(i).isParty() == true) {
				this.partyList[n] = this.guildList.get(i);
				n += 1;
			}
		}
	}

	public Unit getGuildUnit(int num) {
		return this.guildList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.getMoney() + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < this.guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + this.guildList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + this.guildList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + this.guildList.get(i).getHp());
			System.out.println(" / " + this.guildList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + this.guildList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + this.guildList.get(i).getDef() + "]");
			System.out.println(" [파티중 : " + this.guildList.get(i).isParty() + "]");
			System.out.println("");
		}
		System.out.println("=================================");
	}

	public void printUnitStaus(int num) {
		this.guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		this.guildList.get(num).printEquitedItem();
	}

	private void buyUnit() {
		final int PRICE = 5000;
		if (Player.getMoney() < PRICE) {
			System.out.println("돈이 부족합니다.");
			return;
		}
		
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.guildList.add(temp);
		Player.setMoney(Player.getMoney()-PRICE);
	}
	
	public void addUnit(Unit unit) {
		this.guildList.add(unit);
	}

	private void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		if(sel<1 || sel>this.guildList.size())
			return;
		
		if (this.guildList.get(sel - 1).isParty()) {
			System.out.println("파티에 참여 중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[" + this.guildList.get(sel - 1).getName() + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			this.guildList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Unit> getGuildList() {
		return (ArrayList<Unit>) this.guildList.clone();
	}
	
	public int getListSize() {
		return this.guildList.size();
	}
	
	public void clearList() {
		this.guildList.clear();
	}
	
	public Unit[] getPartyList() {
		return this.partyList.clone();
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < PARTY_SIZE; i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + this.partyList[i].getName() + "]");
			System.out.print(" [레벨 : " + this.partyList[i].getLevel() + "]");
			System.out.print(" [체력 : " + this.partyList[i].getHp());
			System.out.println(" / " + this.partyList[i].getMaxHp() + "]");
			System.out.print("[공격력 : " + this.partyList[i].getAtt() + "]");
			System.out.print(" [방어력 : " + this.partyList[i].getDef() + "]");
			System.out.println(" [파티중 : " + this.partyList[i].isParty() + "]");
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	private void partyChange() {
		printParty();
		System.out.println("교체할 번호를 입력하세요 ");
		int partyNum = MainGame.scan.nextInt();
		if(partyNum<1 || partyNum>this.partyList.length)
			return;
		
		printAllUnitStaus();
		System.out.println("참가할 번호를 입력하세요 ");
		int guildNum = MainGame.scan.nextInt();
		if(guildNum<1 || guildNum>this.guildList.size())
			return;
		
		if(this.guildList.get(guildNum-1).isParty()) {
			System.out.printf("%s은(는) 이미 파티에 참여중입니다.\n",this.guildList.get(guildNum-1).getName());
			return;
		}

		this.partyList[partyNum - 1].setParty(false);
		this.guildList.get(guildNum - 1).setParty(true);

		System.out.println("====================================");
		System.out.print("[이름 : " + this.partyList[partyNum - 1].getName() + "]");
		System.out.print("에서 ");
		System.out.print("[이름 : " + this.guildList.get(guildNum - 1).getName() + "]");
		System.out.println("으로 교체 합니다. ");
		System.out.println("====================================");

		int n = 0;
		for (int i = 0; i < this.guildList.size(); i++) {
			if (this.guildList.get(i).isParty()) {
				this.partyList[n] = this.guildList.get(i);
				n += 1;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sortMembers() {
		System.out.println("정렬 기준을 선택하세요(내림차순).");
		System.out.println("[1.이름] [2.레벨] [3.체력] [4.공격력] [5.방어력] [6.파티참여]\n[0.뒤로가기]");
		int sel = MainGame.scan.nextInt();
		
		if (sel == 1) {
			sortByName();
		} else if (sel == 2) {
			sortByLevel();
		} else if (sel == 3) {
			sortByHp();
		} else if (sel == 4) {
			sortByAtt();
		} else if (sel == 5) {
			sortByDef();
		} else if (sel == 6) {
			sortByParty();
		}  
		else if (sel == 0) {
			return;
		}
	}
	
	private void sortByName() {
		for(int i=0; i<this.getListSize(); i++) {
			for(int j=i; j<this.getListSize(); j++) {
				String prevName = this.guildList.get(i).getName();
				String nextName = this.guildList.get(j).getName(); 
				if(nextName.compareTo(prevName)<0) {
					Unit temp = this.guildList.get(i);
					this.guildList.set(i, this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	private void sortByLevel() {
		for(int i=0; i<this.getListSize(); i++) {
			for(int j=i; j<this.getListSize(); j++) {
				int prevLevel = this.guildList.get(i).getLevel();
				int nextLevel = this.guildList.get(j).getLevel(); 
				if(nextLevel<prevLevel) {
					Unit temp = this.guildList.get(i);
					this.guildList.set(i, this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	private void sortByHp() {
		for(int i=0; i<this.getListSize(); i++) {
			for(int j=i; j<this.getListSize(); j++) {
				int prevMaxHP = this.guildList.get(i).getMaxHp();
				int nextMaxHP = this.guildList.get(j).getMaxHp(); 
				if(nextMaxHP>prevMaxHP) {
					Unit temp = this.guildList.get(i);
					this.guildList.set(i, this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	private void sortByAtt() {
		for(int i=0; i<this.getListSize(); i++) {
			for(int j=i; j<this.getListSize(); j++) {
				int prevAtt = this.guildList.get(i).getAtt();
				int nextAtt = this.guildList.get(j).getAtt(); 
				if(nextAtt>prevAtt) {
					Unit temp = this.guildList.get(i);
					this.guildList.set(i, this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	private void sortByDef() {
		for(int i=0; i<this.getListSize(); i++) {
			for(int j=i; j<this.getListSize(); j++) {
				int prevDef = this.guildList.get(i).getDef();
				int nextDef = this.guildList.get(j).getDef(); 
				if(nextDef>prevDef) {
					Unit temp = this.guildList.get(i);
					this.guildList.set(i, this.guildList.get(j));
					this.guildList.set(j, temp);
				}
			}
		}
	}

	private void sortByParty() {
		ArrayList<Unit> temp = new ArrayList<Unit>();
		
		for(int i=0; i<this.getListSize(); i++) {
			if(this.guildList.get(i).isParty()) {
				temp.add(this.guildList.get(i));
			}
		}
		
		for(int i=0; i<this.getListSize(); i++) {
			if(!this.guildList.get(i).isParty())
				temp.add(this.guildList.get(i));
		}
		
		this.guildList = temp;
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.길드원목록] [2.길드원추가] [3.길드원삭제]\n" + "[4.파티원교체] [5.정렬하기] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				partyChange();
			} else if (sel == 5) {
				sortMembers();
			} 
			else if (sel == 0) {
				break;
			}
		}
	}
}