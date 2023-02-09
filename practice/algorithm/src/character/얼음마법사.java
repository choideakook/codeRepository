package character;

import weapon.얼음지팡이;

// 얼음마법사의 고유한 정보를 갖고있는 객체입니다.
// 기록되지 않은 정보는 마법사와 동일합니다.
public class 얼음마법사 extends 마법사 {

    @Override
    public void 공격() {
        if (무기 == null) set무기(new 얼음지팡이());

        String 무기이름 = 무기.name();
        int 무기공격력 = 무기.power();

        무기.공격시도(직업(), 기본공격력(), 무기이름, 무기공격력);
    }
}
