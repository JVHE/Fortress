package fortress;
// 맵의 현재 상태를 나타내는 enum 클래스
// 경계: 맵의 경계선. 발사체가 경계를 넘어갈 경우 발사체는 사라지게 된다.
// 경계 출력형태 = '■'
// *고민중: 발사체의 좌표값을 계속 갖고 있게 하다가 경계 내부에 포함될 경우에만 표시시켜 줄까? 물론 땅 밑으로 갈 경우 사라지게 하고 
// 공백
// 공간= ' '
//미사일 = '!' 데드라인 = '-' 적미사일 = ':'
enum 상태 {
	경계, 공백, 미사일, 미사일잔상1, 미사일잔상2, 미사일잔상3, 데드라인, 레이저, 폭발, 폭발잔상1, 폭발잔상2;
}

public class 맵 {
	public int 좌표_X;
	public int 좌표_Y;
	상태 현재상태, 이전상태;

	탱크 유저 = null;
	// 적군 적 = null;
	벽 방해물 = null;
	미사일 샷 = null;

	맵 상, 하, 좌, 우;

	맵() {
		현재상태 = 상태.공백;
	}

	public 맵(int x, int y) {
		좌표_X = x;
		좌표_Y = y;
		현재상태 = 상태.공백;
	}

	public void 맵설정(상태 status) {
		switch (status) {
		case 경계:
			this.이전상태 = this.현재상태 = 상태.경계;
			break;
		case 공백:
			this.이전상태 = this.현재상태 = 상태.공백;
			break;
		// case "object" : this.status = component.object;
		case 미사일:
			this.이전상태 = this.현재상태 = 상태.미사일;
			break;
		case 미사일잔상1:
			this.이전상태 = this.현재상태 = 상태.미사일잔상1;
			break;
		case 미사일잔상2:
			this.이전상태 = this.현재상태 = 상태.미사일잔상2;
			break;
		case 미사일잔상3:
			this.이전상태 = this.현재상태 = 상태.미사일잔상3;
			break;
		case 데드라인:
			this.이전상태 = this.현재상태 = 상태.데드라인;
			break;
		case 폭발:
			this.이전상태 = this.현재상태 = 상태.폭발;
			break;
		case 폭발잔상1:
			this.이전상태 = this.현재상태 = 상태.폭발잔상1;
			break;
		case 폭발잔상2:
			this.이전상태 = this.현재상태 = 상태.폭발잔상2;
			break;
		case 레이저:
			this.이전상태 = this.현재상태 = 상태.레이저;
			break;
		default:
			System.out.println("경고! 그런 상태는 없습니다!");
		}
	}

	public void 출력() {
		boolean flag = true;

		// 체크 필요
		if (현재상태 == 상태.폭발) {
			System.out.print("#");
			현재상태 = 상태.폭발잔상1;
			return;
		}
		if (this.방해물 != null) {
			방해물.출력();
			return;
		}

		if (this.유저 != null) {
			유저.출력();
			return;
		}
		if (하.유저 != null) {
			하.유저.출력_하();
			return;
		}
		if (우.유저 != null) {
			우.유저.출력_우();
			return;
		}
		if (좌.유저 != null) {
			좌.유저.출력_좌();
			return;
		}
		if (좌.하.유저 != null) {
			좌.하.유저.출력_좌하();
			return;
		}
		if (우.하.유저 != null) {
			우.하.유저.출력_우하();
			return;
		}
		if (우.하.하.하.하.유저 != null) {
			System.out.print("↓");
			return;
		}
		if (좌.하.하.하.하.유저 != null) {
			System.out.print("P");
			return;
		}
		if (하.하.하.하.유저 != null) {
			System.out.print(하.하.하.하.유저.플레이어번호);
			return;
		}

		if (flag) {
			if (현재상태 == 상태.경계) {
				System.out.print("■");
			} else if (현재상태 == 상태.공백) {
				System.out.print(" ");
			} else if (현재상태 == 상태.미사일) {
				System.out.print("●");
				현재상태 = 상태.미사일잔상1;
			} else if (현재상태 == 상태.미사일잔상1) {
				System.out.print("○");
				현재상태 = 상태.미사일잔상2;
			} else if (현재상태 == 상태.미사일잔상2) {
				System.out.print("*");
				현재상태 = 상태.미사일잔상3;
			} else if (현재상태 == 상태.미사일잔상3) {
				System.out.print("'");
				현재상태 = 상태.공백;
			} else if (현재상태 == 상태.데드라인) {
				System.out.print("-");
			} else if (현재상태 == 상태.레이저) {
				System.out.print(":");
				현재상태 = 상태.공백;
			} else if (현재상태 == 상태.폭발) {
				System.out.print("#");
				현재상태 = 상태.폭발잔상1;
			} else if (현재상태 == 상태.폭발잔상1) {
				System.out.print("x");
				현재상태 = 상태.폭발잔상2;
			} else if (현재상태 == 상태.폭발잔상2) {
				System.out.print(".");
				현재상태 = 상태.공백;
			}
		}
		return;
	}
}
