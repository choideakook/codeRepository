package character;

import weapon.무기;

// 캐릭터의 필수 정보를 갖고있는 추상 객체입니다.
public abstract class 캐릭터 {

    public abstract String 직업();

    public abstract int 기본공격력();

    public abstract void 공격();

    public 무기 무기;

    public void set무기(weapon.무기 무기) {
        this.무기 = 무기;
    }
}
