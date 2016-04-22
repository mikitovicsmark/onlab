package hu.bme.mit.inf.symod.A0OL55.homework;

import java.util.Scanner;

import org.yakindu.scr.RuntimeService;
import org.yakindu.scr.TimerService;
import org.yakindu.scr.chessclocka0ol55.ChessClockA0OL55Statemachine;
import org.yakindu.scr.chessclocka0ol55.IChessClockA0OL55Statemachine.SCIBeeperOperationCallback;

public class SimpleApplication {

	public static void main(String[] args) {
		ChessClockA0OL55Statemachine statemachine =
			new ChessClockA0OL55Statemachine();
		statemachine.setTimer(new TimerService());
		statemachine.getSCIBeeper().setSCIBeeperOperationCallback(new SCIBeeperOperationCallback() {
			@Override
			public void beep() {
				System.out.println("Beep");
			}
		});
		
		statemachine.init();
		statemachine.enter();
		
		RuntimeService.getInstance().registerStatemachine(statemachine, 100);
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			switch (scanner.next()) {
			case "s":
				statemachine.getSCIButtons().raiseStartButton();
				break;
			case "w":
				statemachine.getSCIButtons().raiseWhiteButton();
				break;
			case "b":
				statemachine.getSCIButtons().raiseBlackButton();
				break;
			}
			statemachine.runCycle();
			
			System.out.println(statemachine.getSCIDisplay().getText());
			System.out.println(statemachine.getSCIDisplay().getWhiteDisplay() + " - " + statemachine.getSCIDisplay().getBlackDisplay());
		}
	}
}
