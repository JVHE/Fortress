package fortress;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class main {

	public static void main(String[] args) {

		

		class ShareArea {
			boolean turn_1P = true;
			int turn = 1;
			int �Ŀ� = 0;
			int ȸ������ȭ_X;
			int ȸ������ȭ_Y;
			int ǳ�� = 0;
			int ���ݷ� = 10;
			boolean ���� = false;
			boolean is_end = false;

		}
		final ShareArea �������� = new ShareArea();
		final int ������� = 47;
		final int �������� = 80;
		final ��[][] �������� = new ��[�������][��������];
		// ������� ���� �Լ�
		// ���� ���۱� ������ ��
				Runnable bgm = new Runnable() {
					Clip clip;

					public void run() {
						try {
//							clip = AudioSystem.getClip();
//							File file = new File("");
//							System.out.println(file.exists());
//							clip.open(AudioSystem.getAudioInputStream(file));
//							clip.start();
//							while(!��������.is_end) {
//								Thread.sleep(200);
//							}
//							clip.stop();
						} catch (Exception e) {
//							clip.stop();

					
						}
					}
				};

				final Thread bgm_main_start = new Thread(bgm);
				bgm_main_start.start();


		// ǳ�� ��ȭ ������. 10�ʸ��� ǳ���� -5~+5���̷� �ٲ��.
		Runnable ǳ�Ӻ�ȭ = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				Random rand = new Random();
				while (i < 50) {
					��������.ǳ�� = rand.nextInt(11) - 5;
					i++;
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		Thread ǳ�Ӻ�ȭ������ = new Thread(ǳ�Ӻ�ȭ);
		ǳ�Ӻ�ȭ������.start();

		for (int i = 0; i < �������; i++) {
			for (int j = 0; j < ��������; j++) {
				��������[i][j] = new ��(i, j);
			}
		}

		// �ʵ��� �����¿� ����
		for (int i = 0; i < �������; i++) {
			for (int j = 0; j < ��������; j++) {
				��������[i][j].�� = ��������[(��������[i][j].��ǥ_X + ������� - 1) % �������][��������[i][j].��ǥ_Y];
				��������[i][j].�� = ��������[(��������[i][j].��ǥ_X + 1) % �������][��������[i][j].��ǥ_Y];
				��������[i][j].�� = ��������[��������[i][j].��ǥ_X][(��������[i][j].��ǥ_Y + �������� - 1)
						% ��������];
				��������[i][j].�� = ��������[��������[i][j].��ǥ_X][(��������[i][j].��ǥ_Y + 1)
						% ��������];
			}
		}

		// ��輱 �� ������� ����
		for (int i = 0; i < ��������; i++) {
			��������[0][i].�ʼ���(����.���);
			��������[������� - 1][i].�ʼ���(����.���);
			��������[������� - 2][i].�ʼ���(����.�������);

		}
		for (int i = 0; i < �������; i++) {
			��������[i][0].�ʼ���(����.���);
			��������[i][�������� - 1].�ʼ���(����.���);
		}

		// ��� �� ����
		for (int i = 0; i < 3; i++) {
			for (int j = 5; j < 29; j++) {
				��������[23 + i][j].���ع� = new ��();
				��������[23 + i][j].���ع�.������ġ = ��������[23 + i][j];
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 26; j < 73; j++) {
				��������[28 + i][j].���ع� = new ��();
				��������[28 + i][j].���ع�.������ġ = ��������[28 + i][j];
			}
		}

		for (int j = 15; j < 60; j++) {
			��������[36][j].���ع� = new ��();
			��������[36][j].���ع�.������ġ = ��������[36][j];
		}

		// �ǹ� ����
		// for (int i = 0; i < 10; i++) {
		// ��������[35 - i][47].���ع� = new ��();
		// ��������[35 - i][47].���ع�.������ġ = ��������[36 - i][47];
		// ��������[35 - i][58].���ع� = new ��();
		// ��������[35 - i][58].���ع�.������ġ = ��������[36 - i][47];
		// }
		//
		// for (int i = 47; i < 58; i++) {
		// ��������[30][i].���ع� = new ��();
		// ��������[30][i].���ع�.������ġ = ��������[40][i];
		// ��������[26][i].���ع� = new ��();
		// ��������[26][i].���ع�.������ġ = ��������[40][i];
		// }

		// ���� ����

		System.out.println("��Ʈ����\n�����Ϸ��� ���͸� �����ּ���!");

		String selection;
		Scanner scan = new Scanner(System.in);

		final ��ũ �÷��̾�1;
		final ��ũ �÷��̾�2;
		System.out.println("��Ʈ������ ���̼� ���� 1:1 ���� �����Դϴ�.");
		System.out.println("       		��ũ�� ������ �ּ���.");
		System.out.println("1. �����ũ	2. ������ũ	3. �̷���ũ");

		System.out.print("1P�� �÷��� �� ��ũ�� ����ּ���!\n����: ");
		selection = scan.nextLine();
		while (!selection.equals("1") && !selection.equals("2")
				&& !selection.equals("3")) {
			System.out.print("�߸��� ���� �Է��Ͽ����ϴ�. 1, 2, 3�� �ϳ��� �Է��� �ּ���.\n����: ");
			selection = scan.nextLine();
		}
		// 1p �����ũ ����
		if (selection.equals("1")) {
			�÷��̾�1 = new �����ũ();
			System.out.println("�÷��̾�1: �����ũ ���� �Ϸ�!");
		}
		// 1p ������ũ ����
		else if (selection.equals("2")) {
			�÷��̾�1 = new ������ũ();
			System.out.println("�÷��̾�1: ������ũ ���� �Ϸ�!");
		}
		// 1p �̷���ũ ����
		else if (selection.equals("3")) {
			�÷��̾�1 = new �̷���ũ();
			System.out.println("�÷��̾�1: �̷���ũ ���� �Ϸ�!");
		} else {
			�÷��̾�1 = new ��ũ();
		}
		System.out.print("2P�� �÷��� �� ��ũ�� ����ּ���!\n����: ");
		selection = scan.nextLine();
		while (!selection.equals("1") && !selection.equals("2")
				&& !selection.equals("3")) {
			System.out.print("�߸��� ���� �Է��Ͽ����ϴ�. 1, 2, 3�� �ϳ��� �Է��� �ּ���.\n����: ");
			selection = scan.nextLine();
		}
		// 2p �����ũ ����
		if (selection.equals("1")) {
			�÷��̾�2 = new �����ũ();
			System.out.println("�÷��̾�2: �����ũ ���� �Ϸ�!");
		}
		// 2p ������ũ ����
		else if (selection.equals("2")) {
			�÷��̾�2 = new ������ũ();
			System.out.println("�÷��̾�2: ������ũ ���� �Ϸ�!");
		}
		// 2p �̷���ũ ����
		else if (selection.equals("3")) {
			�÷��̾�2 = new �̷���ũ();
			System.out.println("�÷��̾�2: �̷���ũ ���� �Ϸ�!");
		} else {
			�÷��̾�2 = new ��ũ();
		}
		System.out.println("ĳ���� ���� �Ϸ�!!!");

		// �÷��̾�1�� ��ġ ���� �� ���� ����
		�÷��̾�1.�÷��̾��ȣ = 1;
		�÷��̾�1.��ǥ_X = 22;
		�÷��̾�1.��ǥ_Y = 10;
		��������[�÷��̾�1.��ǥ_X][�÷��̾�1.��ǥ_Y].���� = �÷��̾�1;
		�÷��̾�1.������ġ = ��������[�÷��̾�1.��ǥ_X][�÷��̾�1.��ǥ_Y];

		// �÷��̾�2�� ��ġ ���� �� ���� ����
		�÷��̾�2.�÷��̾��ȣ = 2;
		�÷��̾�2.��ǥ_X = 27;
		�÷��̾�2.��ǥ_Y = 65;
		��������[�÷��̾�2.��ǥ_X][�÷��̾�2.��ǥ_Y].���� = �÷��̾�2;
		�÷��̾�2.������ġ = ��������[�÷��̾�2.��ǥ_X][�÷��̾�2.��ǥ_Y];

		System.out.println("�����Ͻ÷��� ���͸� �����ּ���!!");
		selection = scan.nextLine();

		Runnable �߷� = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 6000; i++) {
					if (�÷��̾�1.������ġ.��.���ع� == null) {
						if (�÷��̾�1.������ġ.��.������� == ����.�������) {
							�÷��̾�1.is_dead = true;
							return;
						}
						�÷��̾�1.�̵�(�÷��̾�1.������ġ, �÷��̾�1.������ġ.��);

					}
					if (�÷��̾�2.������ġ.��.���ع� == null) {
						if (�÷��̾�2.������ġ.��.������� == ����.�������) {
							�÷��̾�2.is_dead = true;
							return;
						}
						�÷��̾�2.�̵�(�÷��̾�2.������ġ, �÷��̾�2.������ġ.��);
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		Thread �߷¾����� = new Thread(�߷�);
		�߷¾�����.start();

		// �� Ȯ�ο� boolean ����. 1P ���ʸ� true, 2P ���ʸ� false
		// boolean turn_1P = true;
		��������.turn_1P = true;
		// �� Ȯ�ο� int ����. 1P ���ʸ� 1, 2P ���ʸ� 2
		// int turn = 0;
		��������.turn = 1;
		while (true) {
			// turn_1P�� true�� 1P����, false�� 2P����
			��������.turn = ��������.turn_1P ? 1 : 2;

			// �������� ���
			for (int i = 0; i < �������; i++) {
				for (int j = 0; j < ��������; j++) {
					��������[i][j].���();
				}
				System.out.println();
			}
			System.out.println("\t              �÷��̾�1\t\t  �÷��̾�2");
			System.out.println("\t      Ÿ��|" + �÷��̾�1.Ÿ�� + "\t\t|" + �÷��̾�2.Ÿ��);
			System.out.println("\t����ü��|" + �÷��̾�1.HP + "\t\t|" + �÷��̾�2.HP);
			System.out.println("\t�������|" + �÷��̾�1.������� + "\t\t|" + �÷��̾�2.�������);
			if (�÷��̾�1.is_dead || �÷��̾�2.is_dead) {
				��������.is_end = true;
				// �������� ���� �Լ�
				// �Ϻη� �ȳ���
				Runnable bgm_win = new Runnable() {
					Clip clip;

					public void run() {
						try {
//							clip = AudioSystem.getClip();
//							File file = new File(
//									"");
//							System.out.println(file.exists());
//							clip.open(AudioSystem.getAudioInputStream(file));
//							clip.start();
						} catch (Exception e) {
//							clip.stop();
						}
					}
				};

				final Thread bgm_win_start = new Thread(bgm_win);
				bgm_win_start.start();
				System.out.println("*************************************");
				if (�÷��̾�1.is_dead) {
					System.out.println("**********�÷��̾� 2�� �¸��߽��ϴ�!!********");
				} else {
					System.out.println("**********�÷��̾� 1�� �¸��߽��ϴ�!!********");
				}
				System.out.println("*************************************");
				�߷¾�����.interrupt();
				return;
			}
			System.out.println("\t\t!!�÷��̾�" + ��������.turn + "�� �����Դϴ�!!");
			System.out.println("\t\t���� ȿ��: \t\t-  ǳ��: " + ��������.ǳ�� + "M/s  +");
			System.out
					.println("1. �����ϱ�     2. �̵��ϱ�    3. ������ ���� �� ���	4. �� ����		5. �׺�");

			selection = scan.nextLine();
			while (!selection.equals("1") && !selection.equals("2")
					&& !selection.equals("3") && !selection.equals("4")
					&& !selection.equals("5")) {
				System.out.print("�߸��� ���� �Է��Ͽ����ϴ�. 1~5�� �ϳ��� �Է��� �ּ���.\n����: ");
				selection = scan.nextLine();
			}
			if (selection.equals("1")) {
				System.out
						.println("�����ϱ⸦ �����ϼ̽��ϴ�.\n���� ������ ������ �ּ���.(0������ 90������ ����)");
				final double angle = Double.parseDouble(scan.nextLine());
				System.out.println("����: " + angle + "��");
				System.out.println("�Ŀ� �����ϱ�");
				System.out.println("0%\t\t50%\t\t100%");// ������ 27ĭ
				Runnable �Ŀ����� = new Runnable() {

					int count = 27;
					int j = 0;

					public void run() {
						��������.�Ŀ� = 0;
						// TODO Auto-generated method stub
						try {
							while (!Thread.currentThread().isInterrupted()) {

								Thread.sleep(150);

								System.out.print("��");
								��������.�Ŀ�++;
								j++;
								if (��������.�Ŀ� >= count) {
									Thread.sleep(300);
									��������.�Ŀ� = 0;
									System.out.println();
									for (int i = 0; i < �������; i++) {
										for (int j = 0; j < ��������; j++) {
											��������[i][j].���();
										}
										System.out.println();
									}

									System.out
											.println("\t              �÷��̾�1\t\t  �÷��̾�2");
									System.out.println("\t      Ÿ��|" + �÷��̾�1.Ÿ��
											+ "\t\t|" + �÷��̾�2.Ÿ��);
									System.out.println("\t����ü��|" + �÷��̾�1.HP
											+ "\t\t|" + �÷��̾�2.HP);
									System.out.println("\t�������|" + �÷��̾�1.�������
											+ "\t\t|" + �÷��̾�2.�������);
									System.out.println("\t\t!!�÷��̾�" + ��������.turn
											+ "�� �����Դϴ�!!");
									System.out.println("\t\t���� ȿ��: \t\t-  ǳ��: "
											+ ��������.ǳ�� + "M/s  +");
									System.out
											.println("1. �����ϱ�     2. �̵��ϱ�    3. ������ ���� �� ���	4. �� ����		5. �׺�");
									System.out
											.println("������ ����: " + angle + "��");
									System.out.println("�Ŀ� �����ϱ�");
									System.out.println("0%\t\t50%\t\t100%");
								}
								if (j >= 200) {
									System.out.println("Ÿ�ӿ���");
									return;
								}

							}
						} catch (InterruptedException e) {
							// System.out.println("�Է¹���!");
						} finally {
							// System.out.println("������ ����..");
						}
					}
				};
				Thread �Ŀ����������� = new Thread(�Ŀ�����);
				�Ŀ�����������.start();
				scan.nextLine();
				�Ŀ�����������.interrupt();
				final �̻��� �� = new �̻���();
				if (��������.turn_1P) {
					��.������ġ = �÷��̾�1.������ġ.��.��.��.��;
					��.������ġ = �÷��̾�1.������ġ.��.��.��.��;
					�÷��̾�1.������ġ.��.��.��.��.�� = ��;
				} else {
					��.������ġ = �÷��̾�2.������ġ.��.��.��.��;
					��.������ġ = �÷��̾�2.������ġ.��.��.��.��;
					�÷��̾�2.������ġ.��.��.��.��.�� = ��;
				}
				Runnable ���ݾ����� = new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						double �߻簢;
						int ������ = ��������.���ݷ�;
						if (��������.���ݷ� == 15) {
							��������.���ݷ� = 10;
						}
						if (��������.turn_1P) {
							�߻簢 = angle;
						} else {
							�߻簢 = 180 - angle;
						}
						long start = System.currentTimeMillis();
						long check = System.currentTimeMillis();

						int loc_x = ��.������ġ.��ǥ_X
								- (int) ((��������.�Ŀ� * 3
										* Math.sin(Math.toRadians(�߻簢)) * (check - start)) / 10000 - 4.9
										* (check - start)
										* (check - start)
										/ 10000000);
						int loc_y = ��.������ġ.��ǥ_Y
								+ (int) (��������.�Ŀ� * 3
										* Math.cos(Math.toRadians(�߻簢))
										* (check - start) / 10000);

						while (loc_x >= 0 && loc_x < ������� && loc_y >= 0
								&& loc_y < ��������
								&& ��������[loc_x][loc_y].���ع� == null
								&& ��������[loc_x][loc_y].������� != ����.���) {

							��������[loc_x][loc_y].�ʼ���(����.�̻���);

							// ��.�ʵ���(��.������ġ.��ǥ_Y);
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							check = System.currentTimeMillis();
							loc_x = ��.������ġ.��ǥ_X
									- (int) ((��������.�Ŀ� * 3
											* Math.sin(Math.toRadians(�߻簢)) * (check - start)) / 10000 - 4.9
											* (check - start)
											* (check - start)
											/ 10000000);
							loc_y = ��.������ġ.��ǥ_Y
									+ (int) (��������.�Ŀ� * 3
											* Math.cos(Math.toRadians(�߻簢))
											* (check - start) / 10000);

							System.out
									.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							for (int i = 0; i < �������; i++) {
								for (int j = 0; j < ��������; j++) {
									��������[i][j].���();
								}
								System.out.println();
							}
						}

						if (loc_x >= 0 && loc_x < ������� && loc_y >= 0
								&& loc_y < ��������
								&& ��������[loc_x][loc_y].���ع� != null) {
							String Ÿ��;
							if (��������.turn_1P) {
								Ÿ�� = �÷��̾�1.Ÿ��;
							} else {
								Ÿ�� = �÷��̾�2.Ÿ��;
							}
							switch (Ÿ��) {
							case "��ũ":
								for (int i = 0; i < 3; i++) {
									for (int j = 0; j < 9; j++) {
										if (��������[loc_x + i - 1][loc_y - 5 + j].������� != ����.���) {
											��������[loc_x + i - 1][loc_y - 5 + j]
													.�ʼ���(����.����);
											if (��������[loc_x + i - 1][loc_y - 5
													+ j].���ع� != null) {
												��������[loc_x + i - 1][loc_y - 5
														+ j].���ع� = null;
											}
											if (��������[loc_x + i - 1][loc_y - 5
													+ j].���� != null) {
												��������[loc_x + i - 1][loc_y - 5
														+ j].����.HP -= ������;
												if (��������[loc_x + i - 1][loc_y
														- 5 + j].����.HP <= 0) {
													��������[loc_x + i - 1][loc_y
															- 5 + j].����.is_dead = true;
												}

											}
										}
									}
								}
								break;
							case "�����ũ":
								for (int i = 0; i < 3; i++) {
									for (int j = 0; j < 9; j++) {
										if (��������[loc_x + i - 1][loc_y - 5 + j].������� != ����.���) {
											��������[loc_x + i - 1][loc_y - 5 + j]
													.�ʼ���(����.����);
											if (��������[loc_x + i - 1][loc_y - 5
													+ j].���ع� != null) {
												��������[loc_x + i - 1][loc_y - 5
														+ j].���ع� = null;
											}
											if (��������[loc_x + i - 1][loc_y - 5
													+ j].���� != null) {
												��������[loc_x + i - 1][loc_y - 5
														+ j].����.HP -= ������;
												if (��������[loc_x + i - 1][loc_y
														- 5 + j].����.HP <= 0) {
													��������[loc_x + i - 1][loc_y
															- 5 + j].����.is_dead = true;
												}
											}
										}
									}
								}
								break;
							case "������ũ":
								for (int i = 0; i < �������; i++) {
									for (int j = 0; j < ��������; j++) {
										if (5 * (loc_x - i) * (loc_x - i)
												+ (loc_y - j) * (loc_y - j) <= 12) {
											��������[i][j].�ʼ���(����.����);
											if (��������[i][j].���ع� != null) {
												��������[i][j].���ع� = null;
											}
											if (��������[i][j].���� != null) {
												��������[i][j].����.HP -= ������;
												if (��������[i][j].����.HP <= 0) {
													��������[i][j].����.is_dead = true;
												}
											}
										}
									}
								}
								break;
							case "�̷���ũ":
								breakOut: for (int i = 1;; i++) {
									for (int j = 0; j < 5; j++) {
										if (��������[i][loc_y - 3 + j].������� != ����.���) {
											��������[i][loc_y - 3 + j].�ʼ���(����.������);
											if (��������[i][loc_y - 3 + j].���ع� != null) {
												for (int m = 0; m < 3; m++) {
													for (int n = 0; n < 9; n++) {
														if (��������[i + m - 1][loc_y
																- 5 + n].������� != ����.���) {
															��������[i + m - 1][loc_y
																	- 5 + n]
																	.�ʼ���(����.����);
															if (��������[i + m - 1][loc_y
																	- 5 + n].���ع� != null) {
																��������[i + m - 1][loc_y
																		- 5 + n].���ع� = null;
															}
															if (��������[i + m - 1][loc_y
																	- 5 + n].���� != null) {
																��������[i + m - 1][loc_y
																		- 5 + n].����.HP -= ������;
																if (��������[i + m
																		- 1][loc_y
																		- 5 + n].����.HP <= 0) {
																	��������[i + m
																			- 1][loc_y
																			- 5
																			+ n].����.is_dead = true;
																}
															}
														}
													}
												}
												break breakOut;
											}
										}
									}
								}

								break;
							default:
								System.out
										.println("�ùٸ��� ���� ��ũ Ÿ���Դϴ�. ���α׷��� �����մϴ�. "
												+ Ÿ��);
								return;
							}

						}
						for (int n = 0; n < 4; n++) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							for (int i = 0; i < �������; i++) {
								for (int j = 0; j < ��������; j++) {
									��������[i][j].���();
								}
								System.out.println();
							}
						}

						long end = System.currentTimeMillis();

						// System.out.println("���� �ð� : " + (end - start)
						// / 1000.0);
						return;
					}
				};
				Thread �̻��Ͼ����� = new Thread(���ݾ�����);
				if (��������.����) {
					��������.���� = false;
					Thread �̻��Ͼ�����2 = new Thread(���ݾ�����);
					�̻��Ͼ�����2.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				�̻��Ͼ�����.start();

				scan.nextLine();
			} else if (selection.equals("2")) {
				System.out
						.println("�̵��ϱ⸦ �����ϼ̽��ϴ�. ���ϴ� ����(����: a  ������: d)�� �Է� �� �ּ���.\n�̵��� �����Ϸ��� -1�� �Է� �� �ּ���");
				final String direction = scan.nextLine();

				if (selection.equals("-1")) {
					System.out.println("�̵� ����! ���͸� �Է��ϼ���.");
				} else {
					System.out.println("���� ���� �Ϸ�. ���ϴ� �̵� ĭ ���� �Է��� ����");
					final int moves = Integer.parseInt(scan.nextLine());
					final ��ũ ����;
					if (��������.turn_1P) {
						���� = �÷��̾�1;
					} else {
						���� = �÷��̾�2;
					}
					Runnable �����̵� = new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							for (int mv = 0; mv < moves; mv++) {
								// �������� ���
								for (int i = 0; i < �������; i++) {
									for (int j = 0; j < ��������; j++) {
										��������[i][j].���();
									}
									System.out.println();
								}

								����.�̵�(direction, ����.������ġ, ����.������ġ.��, ����.������ġ.��);
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							// �������� ���
							for (int mv = 0; mv < 10; mv++) {
								for (int i = 0; i < �������; i++) {
									for (int j = 0; j < ��������; j++) {
										��������[i][j].���();
									}
									System.out.println();
								}
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							// �� ��ȯ
							��������.turn_1P = !��������.turn_1P;
						}
					};
					Thread �����̵������� = new Thread(�����̵�);
					�����̵�������.start();
					scan.nextLine();
				}
			} else if (selection.equals("3")) {
				System.out.println("���Ͻô� �������� ����ּ���. (���ݷ� ��ȭ�� ������ 1ȸ���Դϴ�.)");
				System.out.println("1. ���ݷ� 50% ��ȭ. ����: 1000��");
				System.out.println("2. ����. ����: 1500��");
				System.out.println("3. ü�� 10 ����. ����: 2500��");
				System.out.println("4. ���ư���.\n����: ");
				selection = scan.nextLine();
				while (!selection.equals("1") && !selection.equals("2")
						&& !selection.equals("3") && !selection.equals("4")) {
					System.out
							.print("�߸��� ���� �Է��Ͽ����ϴ�. 1, 2, 3, 4�� �ϳ��� �Է��� �ּ���.\n����: ");
					selection = scan.nextLine();
				}
				if (selection.equals("1")) {
					if (��������.turn_1P) {
						if (�÷��̾�1.������� >= 1000) {
							�÷��̾�1.������� -= 1000;
							��������.���ݷ� = 15;
							System.out.println("���� �Ϸ�! �����Ͻ� ȿ���� �̹� �Ͽ� ����˴ϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					} else {
						if (�÷��̾�2.������� >= 1000) {
							�÷��̾�2.������� -= 1000;
							��������.���ݷ� = 15;
							System.out.println("���� �Ϸ�! �����Ͻ� ȿ���� �̹� �Ͽ� ����˴ϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					}
				} else if (selection.equals("2")) {
					if (��������.turn_1P) {
						if (�÷��̾�1.������� >= 1500) {
							�÷��̾�1.������� -= 1500;
							��������.���� = true;
							System.out.println("���� �Ϸ�! �����Ͻ� ȿ���� �̹� �Ͽ� ����˴ϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					} else {
						if (�÷��̾�2.������� >= 1500) {
							�÷��̾�2.������� -= 1500;
							��������.���� = true;
							System.out.println("���� �Ϸ�! �����Ͻ� ȿ���� �̹� �Ͽ� ����˴ϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					}

				} else if (selection.equals("3")) {
					if (��������.turn_1P) {
						if (�÷��̾�1.������� >= 2500) {
							�÷��̾�1.������� -= 2500;
							�÷��̾�1.HP += 10;
							System.out.println("���� �Ϸ�! ü���� 10 ����մϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					} else {
						if (�÷��̾�2.������� >= 2500) {
							�÷��̾�2.������� -= 2500;
							�÷��̾�2.HP += 10;
							System.out.println("���� �Ϸ�! ü���� 10 ����մϴ�!");
						} else {
							System.out.println("�ݾ��� �����մϴ�!");
						}
					}

				} else if (selection.equals("4")) {
					System.out.println("���ư��ϴ�..");
				}
				// �� ��ȯ
				��������.turn_1P = !��������.turn_1P;
				scan.nextLine();

			}

			// �� ��ȯ
			��������.turn_1P = !��������.turn_1P;
		}

	}

}
