package character;

import weapon.불지팡이;

public class 마법사 extends 캐릭터{

    @Override
    public String 직업() {
        return "마법사";
    }

    @Override
    public int 기본공격력() {
        return 5;
    }

    @Override
    public void 공격() {
        if (무기 == null) set무기(new 불지팡이());

        String 무기이름 = 무기.name();
        int 무기공격력 = 무기.power();

        무기.공격시도(직업(), 기본공격력(), 무기이름, 무기공격력);
    }
}
