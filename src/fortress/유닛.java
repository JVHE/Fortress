package fortress;
/*
 * 유닛 클래스는 물체를 상속받는다.
 * 자손 클래스로 탱크, 몬스터를 상속시킨다.
 * 물체는 int 타입의 체력(HP)변수, 그리고 boolean 타입의 죽음 확인(is_dead)변수가 있다.
 */
public class 유닛 extends 물체 {
	int HP = 35;	//체력
	boolean is_dead = false;	//죽음확인0
	
}