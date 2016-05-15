package example;

import org.yakindu.scr.RuntimeService;
import org.yakindu.scr.TimerService;
import org.yakindu.scr.chessclocka0ol55.ChessClockA0OL55Statemachine;
import org.yakindu.scr.chessclocka0ol55.IChessClockA0OL55Statemachine.SCIBeeperOperationCallback;

public class ChessClock {
	static ChessClockA0OL55Statemachine statemachine = new ChessClockA0OL55Statemachine();
	static boolean started = false;

	public static void init() {
		if (!started) {
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
			started = true;
		}
	}

	public static void startButton() {
		statemachine.getSCIButtons().raiseStartButton();
		statemachine.runCycle();
	}

	public static void whiteButton() {
		statemachine.getSCIButtons().raiseWhiteButton();
		statemachine.runCycle();
	}

	public static void blackButton() {
		statemachine.getSCIButtons().raiseBlackButton();
		statemachine.runCycle();
	}

	public static void modeButton() {
		statemachine.getSCIButtons().raiseModeButton();
		statemachine.runCycle();
	}

	public static String getText() {
		return statemachine.getSCIDisplay().getText();
	}

	public static String getWhiteText() {
		return "" + statemachine.getSCIDisplay().getWhiteDisplay();
	}

	public static String getBlackText() {
		return "" + statemachine.getSCIDisplay().getBlackDisplay();
	}
}
