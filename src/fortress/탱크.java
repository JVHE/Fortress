package fortress;
public class 탱크 extends 유닛 {
	String 타입 = "탱크";
	int 플레이어번호 = 0;
	int 소지골드 = 5000;
	맵 미래위치;
	int 이동가능거리 = 6;

	public void 공격(double angle, double power) {
		// 미사일 샷 = new 미사일();
		// 샷.현재위치 = this.현재위치.상.상.우.우;
		// this.현재위치.상.상.우.우.샷 = 샷;
		// Runnable 공격쓰레드 = new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		//
		// long start = System.currentTimeMillis();
		// long check = System.currentTimeMillis();
		// while((check-start)<10000) {
		// 샷.맵들어가기(샷.시작위치.좌표_Y);
		// }
		//
		//
		//
		// long end = System.currentTimeMillis();
		// System.out.println("실행 시간 : " + (end - start) / 1000.0);
		// }
		// };
	}

	public void 이동(String direction, 맵 current_pos, 맵 right, 맵 left) {
		if (direction.equals("a") || direction.equals("A")
				|| direction.equals("ㅁ")) {
			if (left.좌.현재상태 != 상태.경계) {
				// 옆의 장애물이 비어있을 때, 장애물이 있고 위칸은 없을 때, 이동
				if (left.방해물 == null) {
					미래위치 = left;
					this.이동(current_pos, 미래위치);
				} else if (left.방해물 != null && left.상.방해물 == null) {
					미래위치 = left.상;
					this.이동(current_pos, 미래위치);
				}
			}
		}
		if (direction.equals("d") || direction.equals("D")
				|| direction.equals("ㅇ")) {
			if (right.우.현재상태 != 상태.경계) {
				if (right.방해물 == null) {
					미래위치 = right;
					this.이동(current_pos, 미래위치);
				} else if (right.방해물 != null && right.상.방해물 == null) {
					미래위치 = right.상;
					this.이동(current_pos, 미래위치);
				}
			}
		}
	}

	public void 이동(맵 current_pos, 맵 next_pos) {
		맵들어가기(next_pos);
		맵나가기(current_pos);
	}

	public void 맵들어가기(맵 pos) {
		pos.유저 = this;
		this.현재위치 = pos;
	}

	public void 맵나가기(맵 pos) {
		pos.유저 = null;
	}

	public void 출력() {
		System.out.print("=");
	}

	public void 출력_좌() {
		System.out.print("●");
	}

	public void 출력_우() {
		System.out.print("●");
	}

	public void 출력_하() {
		System.out.print("▲");
	}

	public void 출력_좌하() {
		System.out.print("-");
	}

	public void 출력_우하() {
		System.out.print(" ");
	}

}
