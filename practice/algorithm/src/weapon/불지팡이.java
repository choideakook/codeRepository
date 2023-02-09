package weapon;

// 불지팡이의 고유한 정보를 갖고있는 객체입니다.
public class 불지팡이 extends 무기{

    @Override
    public String name() {
        return "불지팡이";
    }

    @Override
    public int power() {
        return 50;
    }
}
