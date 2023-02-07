package character;


import weapon.활;

import java.util.HashMap;
import java.util.Map;

public class 캐릭터공장 {

    private static Map<String, 캐릭터> 캐릭터공장;

    public static 캐릭터 get(String 생성) {
        if (캐릭터공장 == null) {
            캐릭터공장 = new HashMap<>();
            캐릭터공장.put("칼전사", new 전사());
            캐릭터공장.put("활전사", new 전사());
            캐릭터공장.put("불마법사", new 마법사());
            캐릭터공장.put("얼음마법사", new 얼음마법사());

            캐릭터공장.get("활전사").set무기(new 활());
        }
        return 캐릭터공장.get(생성);
    }

    private 캐릭터공장() {}

}
