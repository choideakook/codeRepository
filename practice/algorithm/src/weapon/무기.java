package weapon;

public abstract class 무기 {

    public abstract String name();

    public abstract int power();

    public void 공격시도(String 직업, int 기본공격력, String 무기이름, int 무기공격력) {
        System.out.printf("%s", 직업 + "(이)가 " + 무기이름 + "(으)로 공격합니다.\n");
        System.out.printf("%s", "데미지 : " + 기본공격력 * 무기공격력 + "\n");
    }
}
