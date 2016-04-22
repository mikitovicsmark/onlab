package hu.bme.mit.inf.symod.homework.generic.tests;

import org.junit.Test;

public class TestCases {
	@Test(timeout=10000)
	public void base1() {
		TesterAPI c = new TesterAPI("base1: Basic test: Pressing each button.");
	c.pushBlack();
	c.pushWhite();
	c.pushMod();
	c.pushStart();
	System.out.println("base1 Succeeded!");
	}
	
	@Test(timeout=10000)
	public void base2() {
		TesterAPI c = new TesterAPI("base2: Basic test: Waiting for 3 sec.");
	c.clockForward(3000);
	System.out.println("base2 Succeeded!");
	}
	
	@Test(timeout=10000)
	public void optionCycle() {
		TesterAPI c = new TesterAPI("optionCycle: By pushing the MOD button in the menu each options are available, and are set to the default values.");
	c.expectText("Ready to play");
	c.pushMod();
	c.expectBeep(false);
	c.expectText("White initial time");
	c.expectWhiteNumber(60);
	c.pushMod();
	c.expectBeep(false);
	c.expectText("Black initial time");
	c.expectBlackNumber(60);
	c.pushMod();
	c.expectBeep(false);
	c.expectText("Increment time");
	c.expectWhiteNumber(30);
	c.pushMod();
	c.expectBeep(false);
	c.expectText("White begins");
	c.pushMod();
	c.expectBeep(false);
	c.expectText("Ready to play");
	System.out.println("optionCycle Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartTimeForWhite() {
		TesterAPI c = new TesterAPI("checkSetStartTimeForWhite: Checking the upper and lower bounds of the target option for the White player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.expectWhiteNumber(60);
	c.pushWhite();
	c.expectWhiteNumber(90);
	c.pushWhite();
	c.expectWhiteNumber(120);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectWhiteNumber(540);
	c.pushWhite();
	c.expectWhiteNumber(570);
	c.pushWhite();
	c.expectWhiteNumber(600);
	c.pushWhite();
	c.expectBeep(false);
	c.expectWhiteNumber(600);
	c.pushBlack();
	c.expectWhiteNumber(570);
	c.pushBlack();
	c.expectWhiteNumber(540);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectWhiteNumber(120);
	c.pushBlack();
	c.expectWhiteNumber(90);
	c.pushBlack();
	c.expectWhiteNumber(60);
	c.pushBlack();
	c.expectWhiteNumber(60);
	c.expectBeep(false);
	System.out.println("checkSetStartTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartTimeForBlack() {
		TesterAPI c = new TesterAPI("checkSetStartTimeForBlack: Checking the upper and lower bounds of the target optionfor the Black player. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.pushMod();
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectBlackNumber(90);
	c.pushWhite();
	c.expectBlackNumber(120);
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.pushWhite();
	c.expectBlackNumber(540);
	c.pushWhite();
	c.expectBlackNumber(570);
	c.pushWhite();
	c.expectBlackNumber(600);
	c.pushWhite();
	c.expectBeep(false);
	c.expectBlackNumber(600);
	c.pushBlack();
	c.expectBlackNumber(570);
	c.pushBlack();
	c.expectBlackNumber(540);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectBlackNumber(120);
	c.pushBlack();
	c.expectBlackNumber(90);
	c.pushBlack();
	c.expectBlackNumber(60);
	c.pushBlack();
	c.expectBlackNumber(60);
	c.expectBeep(false);
	System.out.println("checkSetStartTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetBonusTime() {
		TesterAPI c = new TesterAPI("checkSetBonusTime: Checking the upper and lower bounds of the target option. First, the value is increased from default to maximal, plus one more time to check if it stops. Then, it decreased to minimal and checked again it it stops.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectWhiteNumber(30);
	c.pushWhite();
	c.expectWhiteNumber(35);
	c.pushWhite();
	c.expectWhiteNumber(40);
	c.pushWhite();
	c.pushWhite();
	c.expectWhiteNumber(50);
	c.pushWhite();
	c.expectWhiteNumber(55);
	c.pushWhite();
	c.expectWhiteNumber(60);
	c.pushWhite();
	c.expectBeep(false);
	c.expectWhiteNumber(60);
	c.pushBlack();
	c.expectWhiteNumber(55);
	c.pushBlack();
	c.expectWhiteNumber(50);
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.pushBlack();
	c.expectWhiteNumber(10);
	c.pushBlack();
	c.expectWhiteNumber(5);
	c.pushBlack();
	c.expectWhiteNumber(0);
	c.pushBlack();
	c.expectWhiteNumber(0);
	c.expectBeep(false);
	System.out.println("checkSetBonusTime Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkSetStartPlayer() {
		TesterAPI c = new TesterAPI("checkSetStartPlayer: Checking whether the initial player can be set.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("White begins");
	c.pushBlack();
	c.expectText("Black begins");
	c.pushBlack();
	c.expectText("Black begins");
	c.pushWhite();
	c.expectText("White begins");
	System.out.println("checkSetStartPlayer Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartTimeForWhite() {
		TesterAPI c = new TesterAPI("checkEffectSetStartTimeForWhite: Checks the result of changeing the initial time for player Whtie.");
	c.pushMod();
	c.expectText("White initial time");
	c.expectWhiteNumber(60);
	c.pushWhite();
	c.expectText("White initial time");
	c.expectWhiteNumber(90);
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(90);
	c.clockForward(3000);
	c.expectWhiteNumber(87);
	System.out.println("checkEffectSetStartTimeForWhite Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartTimeForBlack() {
		TesterAPI c = new TesterAPI("checkEffectSetStartTimeForBlack: Checks the result of changeing the initial time for player Black.");
	c.pushMod();
	c.pushMod();
	c.expectText("Black initial time");
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectText("Black initial time");
	c.expectBlackNumber(90);
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("Ready to play");
	c.pushStart();
	c.pushWhite();
	c.expectBeep(true);
	c.expectText("Black moves");
	c.expectBlackNumber(90);
	c.clockForward(3000);
	c.expectBlackNumber(87);
	System.out.println("checkEffectSetStartTimeForBlack Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetBonusTime() {
		TesterAPI c = new TesterAPI("checkEffectSetBonusTime: Checks the result of changeing the initial time for player Global.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.expectText("Increment time");
	c.expectWhiteNumber(30);
	c.pushWhite();
	c.expectText("Increment time");
	c.expectWhiteNumber(35);
	c.pushMod();
	c.pushMod();
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(60);
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectBeep(true);
	c.expectText("Black moves");
	c.pushBlack();
	c.expectBeep(true);
	c.expectText("White moves");
	c.expectWhiteNumber(95);
	c.expectBlackNumber(95);
	System.out.println("checkEffectSetBonusTime Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkEffectSetStartPlayer() {
		TesterAPI c = new TesterAPI("checkEffectSetStartPlayer: Checks the result of changing the initial player.");
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushMod();
	c.pushBlack();
	c.pushMod();
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("Black moves");
	System.out.println("checkEffectSetStartPlayer Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkResetInOptions() {
		TesterAPI c = new TesterAPI("checkResetInOptions: Checking the effect of pushing the START/RESET button in the options.");
	c.pushMod();
	c.expectText("White initial time");
	c.expectWhiteNumber(60);
	c.pushWhite();
	c.expectText("White initial time");
	c.expectWhiteNumber(90);
	c.pushStart();
	c.expectText("White initial time");
	c.expectWhiteNumber(60);
	System.out.println("checkResetInOptions Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkStartInGame() {
		TesterAPI c = new TesterAPI("checkStartInGame: Checking the START/RESET button in the game");
	c.expectText("Ready to play");
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("White moves");
	c.pushStart();
	c.expectText("Ready to play");
	System.out.println("checkStartInGame Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkModInGame() {
		TesterAPI c = new TesterAPI("checkModInGame: Checking the MOD button in the game");
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(60);
	c.expectBlackNumber(60);
	c.clockForward(3000);
	c.pushMod();
	c.expectWhiteNumber(57);
	c.expectBlackNumber(60);
	c.clockForward(2000);
	c.expectWhiteNumber(55);
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectText("White adjourned");
	c.expectWhiteNumber(85);
	c.expectBlackNumber(60);
	c.clockForward(10000);
	c.expectText("White adjourned");
	c.expectWhiteNumber(85);
	c.expectBlackNumber(60);
	c.pushWhite();
	c.pushBlack();
	c.expectText("White adjourned");
	c.expectWhiteNumber(85);
	c.expectBlackNumber(60);
	c.pushMod();
	c.expectText("Black moves");
	c.expectWhiteNumber(85);
	c.expectBlackNumber(60);
	c.clockForward(3000);
	c.expectText("Black moves");
	c.expectWhiteNumber(85);
	c.expectBlackNumber(57);
	System.out.println("checkModInGame Succeeded!");
	}
	
	@Test(timeout=10000)
	public void checkLastSeconds() {
		TesterAPI c = new TesterAPI("checkLastSeconds: Checking the last seconds of a game button in the game.  First, each moves after 10 sec, the the White player waits until the end of the game.");
	c.expectText("Ready to play");
	c.pushStart();
	c.expectText("White moves");
	c.expectWhiteNumber(60);
	c.expectBlackNumber(60);
	c.clockForward(10000);
	c.expectText("White moves");
	c.expectWhiteNumber(50);
	c.expectBlackNumber(60);
	c.pushWhite();
	c.expectText("Black moves");
	c.expectWhiteNumber(80);
	c.expectBlackNumber(60);
	c.clockForward(10000);
	c.expectText("Black moves");
	c.expectWhiteNumber(80);
	c.expectBlackNumber(50);
	c.pushBlack();
	c.expectText("White moves");
	c.expectWhiteNumber(80);
	c.expectBlackNumber(80);
	c.clockForward(74000);
	c.expectText("White moves");
	c.expectWhiteNumber(6);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(false);
	c.expectText("White moves");
	c.expectWhiteNumber(5);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(false);
	c.expectText("White moves");
	c.expectWhiteNumber(4);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(false);
	c.expectText("White moves");
	c.expectWhiteNumber(3);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(false);
	c.expectText("White moves");
	c.expectWhiteNumber(2);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(false);
	c.expectText("White moves");
	c.expectWhiteNumber(1);
	c.expectBlackNumber(80);
	c.clockForward(1000);
	c.expectBeep(true);
	c.expectText("White flag fallen");
	c.expectWhiteNumber(0);
	c.expectBlackNumber(80);
	c.pushBlack();
	c.pushWhite();
	c.pushMod();
	c.expectText("White flag fallen");
	c.expectWhiteNumber(0);
	c.expectBlackNumber(80);
	c.pushStart();
	c.expectText("Ready to play");
	System.out.println("checkLastSeconds Succeeded!");
	}
	
}
