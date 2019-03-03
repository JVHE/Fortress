package fortress;
/* 물체 클래스는 맵이라는 클래스로 이루어진 2차원 배열 내에 존재한다.
 * 물체 클래스는 x좌표, y좌표, 그리고 현재위치라는 맵 변수를 통하여 물체가 존재하는 영역에 접근할 수 있다.
 */

public class 물체 {
	int 좌표_X;
	int 좌표_Y;
	//현재위치 변수는 포인터와 비슷하다. 만약 어떤 맵 M위에 물체 O가 존재한다면 O객체는 M을 현재위치로 갖는다.  
	public 맵 현재위치;
}
