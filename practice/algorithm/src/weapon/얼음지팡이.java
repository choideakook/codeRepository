package weapon;

//얼음지팡이의 고유한 정보를 갖고있는 객체입니다.
public class 얼음지팡이 extends 무기 {

    @Override
    public String name() {
        return "얼음지팡이";
    }

    @Override
    public int power() {
        return 60;
    }
}
