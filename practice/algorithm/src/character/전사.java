package character;

import weapon.칼;

public class 전사 extends 캐릭터 {

    @Override
    public String 직업() {
        return "전사";
    }

    @Override
    public int 기본공격력() {
        return 10;
    }

    @Override
    public void 공격() {
        if (무기 == null) set무기(new 칼());

        String 무기이름 = 무기.name();
        int 무기공격력 = 무기.power();

        무기.공격시도(직업(), 기본공격력(), 무기이름, 무기공격력);
    }
}
