package fortress;
public class ��ũ extends ���� {
	String Ÿ�� = "��ũ";
	int �÷��̾��ȣ = 0;
	int ������� = 5000;
	�� �̷���ġ;
	int �̵����ɰŸ� = 6;

	public void ����(double angle, double power) {
		// �̻��� �� = new �̻���();
		// ��.������ġ = this.������ġ.��.��.��.��;
		// this.������ġ.��.��.��.��.�� = ��;
		// Runnable ���ݾ����� = new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		//
		// long start = System.currentTimeMillis();
		// long check = System.currentTimeMillis();
		// while((check-start)<10000) {
		// ��.�ʵ���(��.������ġ.��ǥ_Y);
		// }
		//
		//
		//
		// long end = System.currentTimeMillis();
		// System.out.println("���� �ð� : " + (end - start) / 1000.0);
		// }
		// };
	}

	public void �̵�(String direction, �� current_pos, �� right, �� left) {
		if (direction.equals("a") || direction.equals("A")
				|| direction.equals("��")) {
			if (left.��.������� != ����.���) {
				// ���� ��ֹ��� ������� ��, ��ֹ��� �ְ� ��ĭ�� ���� ��, �̵�
				if (left.���ع� == null) {
					�̷���ġ = left;
					this.�̵�(current_pos, �̷���ġ);
				} else if (left.���ع� != null && left.��.���ع� == null) {
					�̷���ġ = left.��;
					this.�̵�(current_pos, �̷���ġ);
				}
			}
		}
		if (direction.equals("d") || direction.equals("D")
				|| direction.equals("��")) {
			if (right.��.������� != ����.���) {
				if (right.���ع� == null) {
					�̷���ġ = right;
					this.�̵�(current_pos, �̷���ġ);
				} else if (right.���ع� != null && right.��.���ع� == null) {
					�̷���ġ = right.��;
					this.�̵�(current_pos, �̷���ġ);
				}
			}
		}
	}

	public void �̵�(�� current_pos, �� next_pos) {
		�ʵ���(next_pos);
		�ʳ�����(current_pos);
	}

	public void �ʵ���(�� pos) {
		pos.���� = this;
		this.������ġ = pos;
	}

	public void �ʳ�����(�� pos) {
		pos.���� = null;
	}

	public void ���() {
		System.out.print("=");
	}

	public void ���_��() {
		System.out.print("��");
	}

	public void ���_��() {
		System.out.print("��");
	}

	public void ���_��() {
		System.out.print("��");
	}

	public void ���_����() {
		System.out.print("-");
	}

	public void ���_����() {
		System.out.print(" ");
	}

}
