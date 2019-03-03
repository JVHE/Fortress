package fortress;
/* 벽은 물체를 상속받은 클래스다.
 * 맵 위에 존재할 수 있고, 미사일에 의해 파괴될 수 있다.
 */

public class 벽 extends 물체 {
	// 이 메소드를 통해 벽 클래스는 맵 위에서 사라진다.
	public void 파괴() {
		this.현재위치.방해물 = null;
	}
	public void 출력() {
		System.out.print("▼");
	}
}