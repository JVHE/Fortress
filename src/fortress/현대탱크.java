package fortress;


public class 현대탱크 extends 탱크 {

	현대탱크() {
		타입 = "현대탱크";
	}

	public void 미사일발사(double angle, double power) {

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
		System.out.print("-");
	}

	public void 출력_좌하() {
		System.out.print("-");
	}

	public void 출력_우하() {
		System.out.print("▲");
	}
}
