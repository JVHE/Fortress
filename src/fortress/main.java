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
			int 파워 = 0;
			int 회오리변화_X;
			int 회오리변화_Y;
			int 풍속 = 0;
			int 공격력 = 10;
			boolean 더블샷 = false;
			boolean is_end = false;

		}
		final ShareArea 공유영역 = new ShareArea();
		final int 행사이즈 = 47;
		final int 열사이즈 = 80;
		final 맵[][] 스테이지 = new 맵[행사이즈][열사이즈];
		// 배경음악 실행 함수
		// 음악 저작권 때문에 뺌
				Runnable bgm = new Runnable() {
					Clip clip;

					public void run() {
						try {
//							clip = AudioSystem.getClip();
//							File file = new File("");
//							System.out.println(file.exists());
//							clip.open(AudioSystem.getAudioInputStream(file));
//							clip.start();
//							while(!공유영역.is_end) {
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


		// 풍속 변화 쓰레드. 10초마다 풍속이 -5~+5사이로 바뀐다.
		Runnable 풍속변화 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				Random rand = new Random();
				while (i < 50) {
					공유영역.풍속 = rand.nextInt(11) - 5;
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

		Thread 풍속변화쓰레드 = new Thread(풍속변화);
		풍속변화쓰레드.start();

		for (int i = 0; i < 행사이즈; i++) {
			for (int j = 0; j < 열사이즈; j++) {
				스테이지[i][j] = new 맵(i, j);
			}
		}

		// 맵들의 상하좌우 연결
		for (int i = 0; i < 행사이즈; i++) {
			for (int j = 0; j < 열사이즈; j++) {
				스테이지[i][j].상 = 스테이지[(스테이지[i][j].좌표_X + 행사이즈 - 1) % 행사이즈][스테이지[i][j].좌표_Y];
				스테이지[i][j].하 = 스테이지[(스테이지[i][j].좌표_X + 1) % 행사이즈][스테이지[i][j].좌표_Y];
				스테이지[i][j].좌 = 스테이지[스테이지[i][j].좌표_X][(스테이지[i][j].좌표_Y + 열사이즈 - 1)
						% 열사이즈];
				스테이지[i][j].우 = 스테이지[스테이지[i][j].좌표_X][(스테이지[i][j].좌표_Y + 1)
						% 열사이즈];
			}
		}

		// 경계선 및 데드라인 생성
		for (int i = 0; i < 열사이즈; i++) {
			스테이지[0][i].맵설정(상태.경계);
			스테이지[행사이즈 - 1][i].맵설정(상태.경계);
			스테이지[행사이즈 - 2][i].맵설정(상태.데드라인);

		}
		for (int i = 0; i < 행사이즈; i++) {
			스테이지[i][0].맵설정(상태.경계);
			스테이지[i][열사이즈 - 1].맵설정(상태.경계);
		}

		// 디딜 땅 생성
		for (int i = 0; i < 3; i++) {
			for (int j = 5; j < 29; j++) {
				스테이지[23 + i][j].방해물 = new 벽();
				스테이지[23 + i][j].방해물.현재위치 = 스테이지[23 + i][j];
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 26; j < 73; j++) {
				스테이지[28 + i][j].방해물 = new 벽();
				스테이지[28 + i][j].방해물.현재위치 = 스테이지[28 + i][j];
			}
		}

		for (int j = 15; j < 60; j++) {
			스테이지[36][j].방해물 = new 벽();
			스테이지[36][j].방해물.현재위치 = 스테이지[36][j];
		}

		// 건물 생성
		// for (int i = 0; i < 10; i++) {
		// 스테이지[35 - i][47].방해물 = new 벽();
		// 스테이지[35 - i][47].방해물.현재위치 = 스테이지[36 - i][47];
		// 스테이지[35 - i][58].방해물 = new 벽();
		// 스테이지[35 - i][58].방해물.현재위치 = 스테이지[36 - i][47];
		// }
		//
		// for (int i = 47; i < 58; i++) {
		// 스테이지[30][i].방해물 = new 벽();
		// 스테이지[30][i].방해물.현재위치 = 스테이지[40][i];
		// 스테이지[26][i].방해물 = new 벽();
		// 스테이지[26][i].방해물.현재위치 = 스테이지[40][i];
		// }

		// 유저 생성

		System.out.println("포트리스\n시작하려면 엔터를 눌러주세요!");

		String selection;
		Scanner scan = new Scanner(System.in);

		final 탱크 플레이어1;
		final 탱크 플레이어2;
		System.out.println("포트리스는 둘이서 즐기는 1:1 대전 게임입니다.");
		System.out.println("       		탱크를 선택해 주세요.");
		System.out.println("1. 고대탱크	2. 현대탱크	3. 미래탱크");

		System.out.print("1P가 플레이 할 탱크를 골라주세요!\n선택: ");
		selection = scan.nextLine();
		while (!selection.equals("1") && !selection.equals("2")
				&& !selection.equals("3")) {
			System.out.print("잘못된 값을 입력하였습니다. 1, 2, 3중 하나를 입력해 주세요.\n선택: ");
			selection = scan.nextLine();
		}
		// 1p 고대탱크 선택
		if (selection.equals("1")) {
			플레이어1 = new 고대탱크();
			System.out.println("플레이어1: 고대탱크 선택 완료!");
		}
		// 1p 현대탱크 선택
		else if (selection.equals("2")) {
			플레이어1 = new 현대탱크();
			System.out.println("플레이어1: 현대탱크 선택 완료!");
		}
		// 1p 미래탱크 선택
		else if (selection.equals("3")) {
			플레이어1 = new 미래탱크();
			System.out.println("플레이어1: 미래탱크 선택 완료!");
		} else {
			플레이어1 = new 탱크();
		}
		System.out.print("2P가 플레이 할 탱크를 골라주세요!\n선택: ");
		selection = scan.nextLine();
		while (!selection.equals("1") && !selection.equals("2")
				&& !selection.equals("3")) {
			System.out.print("잘못된 값을 입력하였습니다. 1, 2, 3중 하나를 입력해 주세요.\n선택: ");
			selection = scan.nextLine();
		}
		// 2p 고대탱크 선택
		if (selection.equals("1")) {
			플레이어2 = new 고대탱크();
			System.out.println("플레이어2: 고대탱크 선택 완료!");
		}
		// 2p 현대탱크 선택
		else if (selection.equals("2")) {
			플레이어2 = new 현대탱크();
			System.out.println("플레이어2: 현대탱크 선택 완료!");
		}
		// 2p 미래탱크 선택
		else if (selection.equals("3")) {
			플레이어2 = new 미래탱크();
			System.out.println("플레이어2: 미래탱크 선택 완료!");
		} else {
			플레이어2 = new 탱크();
		}
		System.out.println("캐릭터 선택 완료!!!");

		// 플레이어1의 위치 배정 및 기초 설정
		플레이어1.플레이어번호 = 1;
		플레이어1.좌표_X = 22;
		플레이어1.좌표_Y = 10;
		스테이지[플레이어1.좌표_X][플레이어1.좌표_Y].유저 = 플레이어1;
		플레이어1.현재위치 = 스테이지[플레이어1.좌표_X][플레이어1.좌표_Y];

		// 플레이어2의 위치 배정 및 기초 설정
		플레이어2.플레이어번호 = 2;
		플레이어2.좌표_X = 27;
		플레이어2.좌표_Y = 65;
		스테이지[플레이어2.좌표_X][플레이어2.좌표_Y].유저 = 플레이어2;
		플레이어2.현재위치 = 스테이지[플레이어2.좌표_X][플레이어2.좌표_Y];

		System.out.println("시작하시려면 엔터를 눌러주세요!!");
		selection = scan.nextLine();

		Runnable 중력 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 6000; i++) {
					if (플레이어1.현재위치.하.방해물 == null) {
						if (플레이어1.현재위치.하.현재상태 == 상태.데드라인) {
							플레이어1.is_dead = true;
							return;
						}
						플레이어1.이동(플레이어1.현재위치, 플레이어1.현재위치.하);

					}
					if (플레이어2.현재위치.하.방해물 == null) {
						if (플레이어2.현재위치.하.현재상태 == 상태.데드라인) {
							플레이어2.is_dead = true;
							return;
						}
						플레이어2.이동(플레이어2.현재위치, 플레이어2.현재위치.하);
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
		Thread 중력쓰레드 = new Thread(중력);
		중력쓰레드.start();

		// 턴 확인용 boolean 변수. 1P 차례면 true, 2P 차례면 false
		// boolean turn_1P = true;
		공유영역.turn_1P = true;
		// 턴 확인용 int 변수. 1P 차례면 1, 2P 차례면 2
		// int turn = 0;
		공유영역.turn = 1;
		while (true) {
			// turn_1P가 true면 1P차례, false면 2P차례
			공유영역.turn = 공유영역.turn_1P ? 1 : 2;

			// 스테이지 출력
			for (int i = 0; i < 행사이즈; i++) {
				for (int j = 0; j < 열사이즈; j++) {
					스테이지[i][j].출력();
				}
				System.out.println();
			}
			System.out.println("\t              플레이어1\t\t  플레이어2");
			System.out.println("\t      타입|" + 플레이어1.타입 + "\t\t|" + 플레이어2.타입);
			System.out.println("\t현재체력|" + 플레이어1.HP + "\t\t|" + 플레이어2.HP);
			System.out.println("\t소지골드|" + 플레이어1.소지골드 + "\t\t|" + 플레이어2.소지골드);
			if (플레이어1.is_dead || 플레이어2.is_dead) {
				공유영역.is_end = true;
				// 종료음악 실행 함수
				// 일부러 안넣음
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
				if (플레이어1.is_dead) {
					System.out.println("**********플레이어 2가 승리했습니다!!********");
				} else {
					System.out.println("**********플레이어 1이 승리했습니다!!********");
				}
				System.out.println("*************************************");
				중력쓰레드.interrupt();
				return;
			}
			System.out.println("\t\t!!플레이어" + 공유영역.turn + "의 차례입니다!!");
			System.out.println("\t\t현재 효과: \t\t-  풍속: " + 공유영역.풍속 + "M/s  +");
			System.out
					.println("1. 공격하기     2. 이동하기    3. 아이템 구매 및 사용	4. 턴 종료		5. 항복");

			selection = scan.nextLine();
			while (!selection.equals("1") && !selection.equals("2")
					&& !selection.equals("3") && !selection.equals("4")
					&& !selection.equals("5")) {
				System.out.print("잘못된 값을 입력하였습니다. 1~5중 하나를 입력해 주세요.\n선택: ");
				selection = scan.nextLine();
			}
			if (selection.equals("1")) {
				System.out
						.println("공격하기를 선택하셨습니다.\n공격 각도를 설정해 주세요.(0도부터 90도까지 가능)");
				final double angle = Double.parseDouble(scan.nextLine());
				System.out.println("각도: " + angle + "도");
				System.out.println("파워 설정하기");
				System.out.println("0%\t\t50%\t\t100%");// ㅁ글자 27칸
				Runnable 파워설정 = new Runnable() {

					int count = 27;
					int j = 0;

					public void run() {
						공유영역.파워 = 0;
						// TODO Auto-generated method stub
						try {
							while (!Thread.currentThread().isInterrupted()) {

								Thread.sleep(150);

								System.out.print("ㅁ");
								공유영역.파워++;
								j++;
								if (공유영역.파워 >= count) {
									Thread.sleep(300);
									공유영역.파워 = 0;
									System.out.println();
									for (int i = 0; i < 행사이즈; i++) {
										for (int j = 0; j < 열사이즈; j++) {
											스테이지[i][j].출력();
										}
										System.out.println();
									}

									System.out
											.println("\t              플레이어1\t\t  플레이어2");
									System.out.println("\t      타입|" + 플레이어1.타입
											+ "\t\t|" + 플레이어2.타입);
									System.out.println("\t현재체력|" + 플레이어1.HP
											+ "\t\t|" + 플레이어2.HP);
									System.out.println("\t소지골드|" + 플레이어1.소지골드
											+ "\t\t|" + 플레이어2.소지골드);
									System.out.println("\t\t!!플레이어" + 공유영역.turn
											+ "의 차례입니다!!");
									System.out.println("\t\t현재 효과: \t\t-  풍속: "
											+ 공유영역.풍속 + "M/s  +");
									System.out
											.println("1. 공격하기     2. 이동하기    3. 아이템 구매 및 사용	4. 턴 종료		5. 항복");
									System.out
											.println("선택한 각도: " + angle + "도");
									System.out.println("파워 설정하기");
									System.out.println("0%\t\t50%\t\t100%");
								}
								if (j >= 200) {
									System.out.println("타임오버");
									return;
								}

							}
						} catch (InterruptedException e) {
							// System.out.println("입력받음!");
						} finally {
							// System.out.println("쓰레드 종료..");
						}
					}
				};
				Thread 파워설정쓰레드 = new Thread(파워설정);
				파워설정쓰레드.start();
				scan.nextLine();
				파워설정쓰레드.interrupt();
				final 미사일 샷 = new 미사일();
				if (공유영역.turn_1P) {
					샷.현재위치 = 플레이어1.현재위치.상.상.우.우;
					샷.시작위치 = 플레이어1.현재위치.상.상.우.우;
					플레이어1.현재위치.상.상.우.우.샷 = 샷;
				} else {
					샷.현재위치 = 플레이어2.현재위치.상.상.좌.좌;
					샷.시작위치 = 플레이어2.현재위치.상.상.좌.좌;
					플레이어2.현재위치.상.상.좌.좌.샷 = 샷;
				}
				Runnable 공격쓰레드 = new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						double 발사각;
						int 데미지 = 공유영역.공격력;
						if (공유영역.공격력 == 15) {
							공유영역.공격력 = 10;
						}
						if (공유영역.turn_1P) {
							발사각 = angle;
						} else {
							발사각 = 180 - angle;
						}
						long start = System.currentTimeMillis();
						long check = System.currentTimeMillis();

						int loc_x = 샷.시작위치.좌표_X
								- (int) ((공유영역.파워 * 3
										* Math.sin(Math.toRadians(발사각)) * (check - start)) / 10000 - 4.9
										* (check - start)
										* (check - start)
										/ 10000000);
						int loc_y = 샷.시작위치.좌표_Y
								+ (int) (공유영역.파워 * 3
										* Math.cos(Math.toRadians(발사각))
										* (check - start) / 10000);

						while (loc_x >= 0 && loc_x < 행사이즈 && loc_y >= 0
								&& loc_y < 열사이즈
								&& 스테이지[loc_x][loc_y].방해물 == null
								&& 스테이지[loc_x][loc_y].현재상태 != 상태.경계) {

							스테이지[loc_x][loc_y].맵설정(상태.미사일);

							// 샷.맵들어가기(샷.시작위치.좌표_Y);
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							check = System.currentTimeMillis();
							loc_x = 샷.시작위치.좌표_X
									- (int) ((공유영역.파워 * 3
											* Math.sin(Math.toRadians(발사각)) * (check - start)) / 10000 - 4.9
											* (check - start)
											* (check - start)
											/ 10000000);
							loc_y = 샷.시작위치.좌표_Y
									+ (int) (공유영역.파워 * 3
											* Math.cos(Math.toRadians(발사각))
											* (check - start) / 10000);

							System.out
									.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							for (int i = 0; i < 행사이즈; i++) {
								for (int j = 0; j < 열사이즈; j++) {
									스테이지[i][j].출력();
								}
								System.out.println();
							}
						}

						if (loc_x >= 0 && loc_x < 행사이즈 && loc_y >= 0
								&& loc_y < 열사이즈
								&& 스테이지[loc_x][loc_y].방해물 != null) {
							String 타입;
							if (공유영역.turn_1P) {
								타입 = 플레이어1.타입;
							} else {
								타입 = 플레이어2.타입;
							}
							switch (타입) {
							case "탱크":
								for (int i = 0; i < 3; i++) {
									for (int j = 0; j < 9; j++) {
										if (스테이지[loc_x + i - 1][loc_y - 5 + j].현재상태 != 상태.경계) {
											스테이지[loc_x + i - 1][loc_y - 5 + j]
													.맵설정(상태.폭발);
											if (스테이지[loc_x + i - 1][loc_y - 5
													+ j].방해물 != null) {
												스테이지[loc_x + i - 1][loc_y - 5
														+ j].방해물 = null;
											}
											if (스테이지[loc_x + i - 1][loc_y - 5
													+ j].유저 != null) {
												스테이지[loc_x + i - 1][loc_y - 5
														+ j].유저.HP -= 데미지;
												if (스테이지[loc_x + i - 1][loc_y
														- 5 + j].유저.HP <= 0) {
													스테이지[loc_x + i - 1][loc_y
															- 5 + j].유저.is_dead = true;
												}

											}
										}
									}
								}
								break;
							case "고대탱크":
								for (int i = 0; i < 3; i++) {
									for (int j = 0; j < 9; j++) {
										if (스테이지[loc_x + i - 1][loc_y - 5 + j].현재상태 != 상태.경계) {
											스테이지[loc_x + i - 1][loc_y - 5 + j]
													.맵설정(상태.폭발);
											if (스테이지[loc_x + i - 1][loc_y - 5
													+ j].방해물 != null) {
												스테이지[loc_x + i - 1][loc_y - 5
														+ j].방해물 = null;
											}
											if (스테이지[loc_x + i - 1][loc_y - 5
													+ j].유저 != null) {
												스테이지[loc_x + i - 1][loc_y - 5
														+ j].유저.HP -= 데미지;
												if (스테이지[loc_x + i - 1][loc_y
														- 5 + j].유저.HP <= 0) {
													스테이지[loc_x + i - 1][loc_y
															- 5 + j].유저.is_dead = true;
												}
											}
										}
									}
								}
								break;
							case "현대탱크":
								for (int i = 0; i < 행사이즈; i++) {
									for (int j = 0; j < 열사이즈; j++) {
										if (5 * (loc_x - i) * (loc_x - i)
												+ (loc_y - j) * (loc_y - j) <= 12) {
											스테이지[i][j].맵설정(상태.폭발);
											if (스테이지[i][j].방해물 != null) {
												스테이지[i][j].방해물 = null;
											}
											if (스테이지[i][j].유저 != null) {
												스테이지[i][j].유저.HP -= 데미지;
												if (스테이지[i][j].유저.HP <= 0) {
													스테이지[i][j].유저.is_dead = true;
												}
											}
										}
									}
								}
								break;
							case "미래탱크":
								breakOut: for (int i = 1;; i++) {
									for (int j = 0; j < 5; j++) {
										if (스테이지[i][loc_y - 3 + j].현재상태 != 상태.경계) {
											스테이지[i][loc_y - 3 + j].맵설정(상태.레이저);
											if (스테이지[i][loc_y - 3 + j].방해물 != null) {
												for (int m = 0; m < 3; m++) {
													for (int n = 0; n < 9; n++) {
														if (스테이지[i + m - 1][loc_y
																- 5 + n].현재상태 != 상태.경계) {
															스테이지[i + m - 1][loc_y
																	- 5 + n]
																	.맵설정(상태.폭발);
															if (스테이지[i + m - 1][loc_y
																	- 5 + n].방해물 != null) {
																스테이지[i + m - 1][loc_y
																		- 5 + n].방해물 = null;
															}
															if (스테이지[i + m - 1][loc_y
																	- 5 + n].유저 != null) {
																스테이지[i + m - 1][loc_y
																		- 5 + n].유저.HP -= 데미지;
																if (스테이지[i + m
																		- 1][loc_y
																		- 5 + n].유저.HP <= 0) {
																	스테이지[i + m
																			- 1][loc_y
																			- 5
																			+ n].유저.is_dead = true;
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
										.println("올바르지 않은 탱크 타입입니다. 프로그램을 종료합니다. "
												+ 타입);
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
							for (int i = 0; i < 행사이즈; i++) {
								for (int j = 0; j < 열사이즈; j++) {
									스테이지[i][j].출력();
								}
								System.out.println();
							}
						}

						long end = System.currentTimeMillis();

						// System.out.println("실행 시간 : " + (end - start)
						// / 1000.0);
						return;
					}
				};
				Thread 미사일쓰레드 = new Thread(공격쓰레드);
				if (공유영역.더블샷) {
					공유영역.더블샷 = false;
					Thread 미사일쓰레드2 = new Thread(공격쓰레드);
					미사일쓰레드2.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				미사일쓰레드.start();

				scan.nextLine();
			} else if (selection.equals("2")) {
				System.out
						.println("이동하기를 선택하셨습니다. 원하는 방향(왼쪽: a  오른쪽: d)을 입력 해 주세요.\n이동을 종료하려면 -1을 입력 해 주세요");
				final String direction = scan.nextLine();

				if (selection.equals("-1")) {
					System.out.println("이동 종료! 엔터를 입력하세요.");
				} else {
					System.out.println("방향 설정 완료. 원하는 이동 칸 수를 입력후 엔터");
					final int moves = Integer.parseInt(scan.nextLine());
					final 탱크 유저;
					if (공유영역.turn_1P) {
						유저 = 플레이어1;
					} else {
						유저 = 플레이어2;
					}
					Runnable 연속이동 = new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							for (int mv = 0; mv < moves; mv++) {
								// 스테이지 출력
								for (int i = 0; i < 행사이즈; i++) {
									for (int j = 0; j < 열사이즈; j++) {
										스테이지[i][j].출력();
									}
									System.out.println();
								}

								유저.이동(direction, 유저.현재위치, 유저.현재위치.우, 유저.현재위치.좌);
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							// 스테이지 출력
							for (int mv = 0; mv < 10; mv++) {
								for (int i = 0; i < 행사이즈; i++) {
									for (int j = 0; j < 열사이즈; j++) {
										스테이지[i][j].출력();
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

							// 턴 전환
							공유영역.turn_1P = !공유영역.turn_1P;
						}
					};
					Thread 연속이동쓰레드 = new Thread(연속이동);
					연속이동쓰레드.start();
					scan.nextLine();
				}
			} else if (selection.equals("3")) {
				System.out.println("원하시는 아이템을 골라주세요. (공격력 강화와 더블샷은 1회용입니다.)");
				System.out.println("1. 공격력 50% 강화. 가격: 1000원");
				System.out.println("2. 더블샷. 가격: 1500원");
				System.out.println("3. 체력 10 증가. 가격: 2500원");
				System.out.println("4. 돌아가기.\n선택: ");
				selection = scan.nextLine();
				while (!selection.equals("1") && !selection.equals("2")
						&& !selection.equals("3") && !selection.equals("4")) {
					System.out
							.print("잘못된 값을 입력하였습니다. 1, 2, 3, 4중 하나를 입력해 주세요.\n선택: ");
					selection = scan.nextLine();
				}
				if (selection.equals("1")) {
					if (공유영역.turn_1P) {
						if (플레이어1.소지골드 >= 1000) {
							플레이어1.소지골드 -= 1000;
							공유영역.공격력 = 15;
							System.out.println("구매 완료! 구매하신 효과는 이번 턴에 적용됩니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					} else {
						if (플레이어2.소지골드 >= 1000) {
							플레이어2.소지골드 -= 1000;
							공유영역.공격력 = 15;
							System.out.println("구매 완료! 구매하신 효과는 이번 턴에 적용됩니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					}
				} else if (selection.equals("2")) {
					if (공유영역.turn_1P) {
						if (플레이어1.소지골드 >= 1500) {
							플레이어1.소지골드 -= 1500;
							공유영역.더블샷 = true;
							System.out.println("구매 완료! 구매하신 효과는 이번 턴에 적용됩니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					} else {
						if (플레이어2.소지골드 >= 1500) {
							플레이어2.소지골드 -= 1500;
							공유영역.더블샷 = true;
							System.out.println("구매 완료! 구매하신 효과는 이번 턴에 적용됩니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					}

				} else if (selection.equals("3")) {
					if (공유영역.turn_1P) {
						if (플레이어1.소지골드 >= 2500) {
							플레이어1.소지골드 -= 2500;
							플레이어1.HP += 10;
							System.out.println("구매 완료! 체력이 10 상승합니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					} else {
						if (플레이어2.소지골드 >= 2500) {
							플레이어2.소지골드 -= 2500;
							플레이어2.HP += 10;
							System.out.println("구매 완료! 체력이 10 상승합니다!");
						} else {
							System.out.println("금액이 부족합니다!");
						}
					}

				} else if (selection.equals("4")) {
					System.out.println("돌아갑니다..");
				}
				// 턴 전환
				공유영역.turn_1P = !공유영역.turn_1P;
				scan.nextLine();

			}

			// 턴 전환
			공유영역.turn_1P = !공유영역.turn_1P;
		}

	}

}
