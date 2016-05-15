package org.yakindu.scr.chessclocka0ol55;
import org.yakindu.scr.ITimer;

public class ChessClockA0OL55Statemachine implements IChessClockA0OL55Statemachine {

	private final boolean[] timeEvents = new boolean[2];

	private final class SCIButtonsImpl implements SCIButtons {

		private boolean modeButton;

		public void raiseModeButton() {
			modeButton = true;
		}

		private boolean startButton;

		public void raiseStartButton() {
			startButton = true;
		}

		private boolean blackButton;

		public void raiseBlackButton() {
			blackButton = true;
		}

		private boolean whiteButton;

		public void raiseWhiteButton() {
			whiteButton = true;
		}

		public void clearEvents() {
			modeButton = false;
			startButton = false;
			blackButton = false;
			whiteButton = false;
		}

	}

	private SCIButtonsImpl sCIButtons;
	private final class SCIBeeperImpl implements SCIBeeper {

		private SCIBeeperOperationCallback operationCallback;

		public void setSCIBeeperOperationCallback(SCIBeeperOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}

	}

	private SCIBeeperImpl sCIBeeper;
	private final class SCIDisplayImpl implements SCIDisplay {

		private String text;
		public String getText() {
			return text;
		}

		public void setText(String value) {
			this.text = value;
		}

		private long whiteDisplay;
		public long getWhiteDisplay() {
			return whiteDisplay;
		}

		public void setWhiteDisplay(long value) {
			this.whiteDisplay = value;
		}

		private long blackDisplay;
		public long getBlackDisplay() {
			return blackDisplay;
		}

		public void setBlackDisplay(long value) {
			this.blackDisplay = value;
		}

	}

	private SCIDisplayImpl sCIDisplay;

	public enum State {
		t_Ready_to_play, t_White_initial_time, t_Black_Initial_time, t_Increment_time, t_Beginning_player, t_Beginning_player_Selection_White_begins, t_Beginning_player_Selection_Black_begins, t_Game, t_Game_W_White_turn, t_Game_W_Black_turn, t_Game_W_White_fallen, t_Game_W_Black_fallen, t_Game_W_Black_adjourned, t_Game_W_White_adjourned, $NullState$
	};

	private long whiteInit;
	private long blackInit;
	private long incrementTime;
	private boolean whiteBegins;
	private long whiteTime;
	private long blackTime;
	private boolean blackPause;
	private boolean whitePause;

	private State[] historyVector = new State[1];
	private final State[] stateVector = new State[1];

	private int nextStateIndex;

	private ITimer timer;

	static {
	}

	public ChessClockA0OL55Statemachine() {

		sCIButtons = new SCIButtonsImpl();
		sCIBeeper = new SCIBeeperImpl();
		sCIDisplay = new SCIDisplayImpl();
	}

	public void init() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}

		for (int i = 0; i < 1; i++) {
			historyVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();

		sCIDisplay.text = "Chess Clock";

		sCIDisplay.whiteDisplay = -1;

		sCIDisplay.blackDisplay = -1;

		whiteInit = 60;

		blackInit = 60;

		incrementTime = 30;

		whiteBegins = true;

		whiteTime = 0;

		blackTime = 0;

		blackPause = false;

		whitePause = false;
	}

	public void enter() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		entryAction();

		sCIDisplay.text = "Ready to play";

		blackTime = blackInit;

		whiteTime = whiteInit;

		sCIDisplay.whiteDisplay = -1;

		sCIDisplay.blackDisplay = -1;

		nextStateIndex = 0;
		stateVector[0] = State.t_Ready_to_play;
	}

	public void exit() {
		switch (stateVector[0]) {
			case t_Ready_to_play :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_White_initial_time :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Black_Initial_time :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Increment_time :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Beginning_player_Selection_White_begins :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Beginning_player_Selection_Black_begins :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Game_W_White_turn :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 0);
				break;

			case t_Game_W_Black_turn :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 1);
				break;

			case t_Game_W_White_fallen :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Game_W_Black_fallen :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Game_W_Black_adjourned :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case t_Game_W_White_adjourned :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			default :
				break;
		}

		exitAction();
	}

	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCIButtons.clearEvents();

		for (int i = 0; i < timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}

	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}

	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
		switch (state) {
			case t_Ready_to_play :
				return stateVector[0] == State.t_Ready_to_play;
			case t_White_initial_time :
				return stateVector[0] == State.t_White_initial_time;
			case t_Black_Initial_time :
				return stateVector[0] == State.t_Black_Initial_time;
			case t_Increment_time :
				return stateVector[0] == State.t_Increment_time;
			case t_Beginning_player :
				return stateVector[0].ordinal() >= State.t_Beginning_player.ordinal()
						&& stateVector[0].ordinal() <= State.t_Beginning_player_Selection_Black_begins.ordinal();
			case t_Beginning_player_Selection_White_begins :
				return stateVector[0] == State.t_Beginning_player_Selection_White_begins;
			case t_Beginning_player_Selection_Black_begins :
				return stateVector[0] == State.t_Beginning_player_Selection_Black_begins;
			case t_Game :
				return stateVector[0].ordinal() >= State.t_Game.ordinal()
						&& stateVector[0].ordinal() <= State.t_Game_W_White_adjourned.ordinal();
			case t_Game_W_White_turn :
				return stateVector[0] == State.t_Game_W_White_turn;
			case t_Game_W_Black_turn :
				return stateVector[0] == State.t_Game_W_Black_turn;
			case t_Game_W_White_fallen :
				return stateVector[0] == State.t_Game_W_White_fallen;
			case t_Game_W_Black_fallen :
				return stateVector[0] == State.t_Game_W_Black_fallen;
			case t_Game_W_Black_adjourned :
				return stateVector[0] == State.t_Game_W_Black_adjourned;
			case t_Game_W_White_adjourned :
				return stateVector[0] == State.t_Game_W_White_adjourned;
			default :
				return false;
		}
	}

	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correct
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}

	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}

	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}

	public SCIButtons getSCIButtons() {
		return sCIButtons;
	}
	public SCIBeeper getSCIBeeper() {
		return sCIBeeper;
	}
	public SCIDisplay getSCIDisplay() {
		return sCIDisplay;
	}

	/* Entry action for statechart 'Chess Clock A0OL55'. */
	private void entryAction() {
	}

	/* Exit action for state 'Chess Clock A0OL55'. */
	private void exitAction() {
	}

	/* shallow enterSequence with history in child Selection */
	private void shallowEnterSequenceT_Beginning_player_Selection() {
		switch (historyVector[0]) {
			case t_Beginning_player_Selection_White_begins :
				sCIDisplay.text = "White begins";

				whiteBegins = true;

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = -1;

				nextStateIndex = 0;
				stateVector[0] = State.t_Beginning_player_Selection_White_begins;

				historyVector[0] = stateVector[0];
				break;

			case t_Beginning_player_Selection_Black_begins :
				sCIDisplay.text = "Black begins";

				whiteBegins = false;

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = -1;

				nextStateIndex = 0;
				stateVector[0] = State.t_Beginning_player_Selection_Black_begins;

				historyVector[0] = stateVector[0];
				break;

			default :
				break;
		}
	}

	/* The reactions of state Ready to play. */
	private void reactT_Ready_to_play() {
		if (sCIButtons.modeButton) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			sCIDisplay.text = "White initial time";

			sCIDisplay.whiteDisplay = whiteInit;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_White_initial_time;
		} else {
			if (sCIButtons.startButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				if (whiteBegins) {
					sCIBeeper.operationCallback.beep();

					timer.setTimer(this, 0, 1 * 1000, false);

					sCIDisplay.text = "White moves";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_White_turn;
				} else {
					sCIBeeper.operationCallback.beep();

					timer.setTimer(this, 1, 1 * 1000, false);

					sCIDisplay.text = "Black moves";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_Black_turn;
				}
			}
		}
	}

	/* The reactions of state White initial time. */
	private void reactT_White_initial_time() {
		if ((sCIButtons.blackButton) && whiteInit > 60) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			whiteInit -= 30;

			sCIDisplay.text = "White initial time";

			sCIDisplay.whiteDisplay = whiteInit;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_White_initial_time;
		} else {
			if (sCIButtons.modeButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				sCIDisplay.text = "Black initial time";

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = blackInit;

				nextStateIndex = 0;
				stateVector[0] = State.t_Black_Initial_time;
			} else {
				if ((sCIButtons.whiteButton) && whiteInit < 600) {
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					whiteInit += 30;

					sCIDisplay.text = "White initial time";

					sCIDisplay.whiteDisplay = whiteInit;

					sCIDisplay.blackDisplay = -1;

					nextStateIndex = 0;
					stateVector[0] = State.t_White_initial_time;
				} else {
					if (sCIButtons.startButton) {
						nextStateIndex = 0;
						stateVector[0] = State.$NullState$;

						whiteInit = 60;

						sCIDisplay.text = "White initial time";

						sCIDisplay.whiteDisplay = whiteInit;

						sCIDisplay.blackDisplay = -1;

						nextStateIndex = 0;
						stateVector[0] = State.t_White_initial_time;
					}
				}
			}
		}
	}

	/* The reactions of state Black Initial time. */
	private void reactT_Black_Initial_time() {
		if ((sCIButtons.blackButton) && blackInit > 60) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			blackInit -= 30;

			sCIDisplay.text = "Black initial time";

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = blackInit;

			nextStateIndex = 0;
			stateVector[0] = State.t_Black_Initial_time;
		} else {
			if ((sCIButtons.whiteButton) && blackInit < 600) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				blackInit += 30;

				sCIDisplay.text = "Black initial time";

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = blackInit;

				nextStateIndex = 0;
				stateVector[0] = State.t_Black_Initial_time;
			} else {
				if (sCIButtons.modeButton) {
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					sCIDisplay.text = "Increment time";

					sCIDisplay.whiteDisplay = incrementTime;

					sCIDisplay.blackDisplay = -1;

					nextStateIndex = 0;
					stateVector[0] = State.t_Increment_time;
				} else {
					if (sCIButtons.startButton) {
						nextStateIndex = 0;
						stateVector[0] = State.$NullState$;

						blackInit = 60;

						sCIDisplay.text = "Black initial time";

						sCIDisplay.whiteDisplay = -1;

						sCIDisplay.blackDisplay = blackInit;

						nextStateIndex = 0;
						stateVector[0] = State.t_Black_Initial_time;
					}
				}
			}
		}
	}

	/* The reactions of state Increment time. */
	private void reactT_Increment_time() {
		if ((sCIButtons.whiteButton) && incrementTime < 60) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			incrementTime += 5;

			sCIDisplay.text = "Increment time";

			sCIDisplay.whiteDisplay = incrementTime;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Increment_time;
		} else {
			if ((sCIButtons.blackButton) && incrementTime > 0) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				incrementTime -= 5;

				sCIDisplay.text = "Increment time";

				sCIDisplay.whiteDisplay = incrementTime;

				sCIDisplay.blackDisplay = -1;

				nextStateIndex = 0;
				stateVector[0] = State.t_Increment_time;
			} else {
				if (sCIButtons.modeButton) {
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					/* Enter the region with shallow history */
					if (historyVector[0] != State.$NullState$) {
						shallowEnterSequenceT_Beginning_player_Selection();
					} else {
						sCIDisplay.text = "White begins";

						whiteBegins = true;

						sCIDisplay.whiteDisplay = -1;

						sCIDisplay.blackDisplay = -1;

						nextStateIndex = 0;
						stateVector[0] = State.t_Beginning_player_Selection_White_begins;

						historyVector[0] = stateVector[0];
					}
				} else {
					if (sCIButtons.startButton) {
						nextStateIndex = 0;
						stateVector[0] = State.$NullState$;

						incrementTime = 30;

						sCIDisplay.text = "Increment time";

						sCIDisplay.whiteDisplay = incrementTime;

						sCIDisplay.blackDisplay = -1;

						nextStateIndex = 0;
						stateVector[0] = State.t_Increment_time;
					}
				}
			}
		}
	}

	/* The reactions of state White begins. */
	private void reactT_Beginning_player_Selection_White_begins() {
		if (sCIButtons.modeButton) {
			switch (stateVector[0]) {
				case t_Beginning_player_Selection_White_begins :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Beginning_player_Selection_Black_begins :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (sCIButtons.blackButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				sCIDisplay.text = "Black begins";

				whiteBegins = false;

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = -1;

				nextStateIndex = 0;
				stateVector[0] = State.t_Beginning_player_Selection_Black_begins;

				historyVector[0] = stateVector[0];
			}
		}
	}

	/* The reactions of state Black begins. */
	private void reactT_Beginning_player_Selection_Black_begins() {
		if (sCIButtons.modeButton) {
			switch (stateVector[0]) {
				case t_Beginning_player_Selection_White_begins :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Beginning_player_Selection_Black_begins :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (sCIButtons.whiteButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				sCIDisplay.text = "White begins";

				whiteBegins = true;

				sCIDisplay.whiteDisplay = -1;

				sCIDisplay.blackDisplay = -1;

				nextStateIndex = 0;
				stateVector[0] = State.t_Beginning_player_Selection_White_begins;

				historyVector[0] = stateVector[0];
			}
		}
	}

	/* The reactions of state White turn. */
	private void reactT_Game_W_White_turn() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (timeEvents[0]) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 0);

				whiteTime -= 1;

				if (whiteTime > 0) {

					timer.setTimer(this, 0, 1 * 1000, false);

					sCIDisplay.text = "White moves";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_White_turn;
				} else {
					sCIBeeper.operationCallback.beep();

					sCIDisplay.text = "White flag fallen";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					whitePause = false;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_White_fallen;
				}
			} else {
				if (sCIButtons.whiteButton) {
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);

					sCIBeeper.operationCallback.beep();

					if (whiteTime + incrementTime <= 1200) {
						whiteTime += incrementTime;

						if (whitePause) {
							sCIDisplay.text = "White adjourned";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_White_adjourned;
						} else {

							timer.setTimer(this, 1, 1 * 1000, false);

							sCIDisplay.text = "Black moves";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_Black_turn;
						}
					} else {
						whiteTime = 1200;

						if (whitePause) {
							sCIDisplay.text = "White adjourned";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_White_adjourned;
						} else {

							timer.setTimer(this, 1, 1 * 1000, false);

							sCIDisplay.text = "Black moves";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_Black_turn;
						}
					}
				} else {
					if (sCIButtons.modeButton) {
						nextStateIndex = 0;
						stateVector[0] = State.$NullState$;

						timer.unsetTimer(this, 0);

						whitePause = true;

						timer.setTimer(this, 0, 1 * 1000, false);

						sCIDisplay.text = "White moves";

						sCIDisplay.blackDisplay = blackTime;

						sCIDisplay.whiteDisplay = whiteTime;

						nextStateIndex = 0;
						stateVector[0] = State.t_Game_W_White_turn;
					}
				}
			}
		}
	}

	/* The reactions of state Black turn. */
	private void reactT_Game_W_Black_turn() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (timeEvents[1]) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 1);

				blackTime -= 1;

				if (blackTime > 0) {

					timer.setTimer(this, 1, 1 * 1000, false);

					sCIDisplay.text = "Black moves";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_Black_turn;
				} else {
					sCIBeeper.operationCallback.beep();

					sCIDisplay.text = "Black flag fallen";

					sCIDisplay.blackDisplay = blackTime;

					sCIDisplay.whiteDisplay = whiteTime;

					blackPause = false;

					nextStateIndex = 0;
					stateVector[0] = State.t_Game_W_Black_fallen;
				}
			} else {
				if (sCIButtons.blackButton) {
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);

					sCIBeeper.operationCallback.beep();

					if (blackTime + incrementTime <= 1200) {
						blackTime += incrementTime;

						if (blackPause) {
							sCIDisplay.text = "Black adjourned";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_Black_adjourned;
						} else {

							timer.setTimer(this, 0, 1 * 1000, false);

							sCIDisplay.text = "White moves";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_White_turn;
						}
					} else {
						blackTime = 1200;

						if (blackPause) {
							sCIDisplay.text = "Black adjourned";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_Black_adjourned;
						} else {

							timer.setTimer(this, 0, 1 * 1000, false);

							sCIDisplay.text = "White moves";

							sCIDisplay.blackDisplay = blackTime;

							sCIDisplay.whiteDisplay = whiteTime;

							nextStateIndex = 0;
							stateVector[0] = State.t_Game_W_White_turn;
						}
					}
				} else {
					if (sCIButtons.modeButton) {
						nextStateIndex = 0;
						stateVector[0] = State.$NullState$;

						timer.unsetTimer(this, 1);

						blackPause = true;

						timer.setTimer(this, 1, 1 * 1000, false);

						sCIDisplay.text = "Black moves";

						sCIDisplay.blackDisplay = blackTime;

						sCIDisplay.whiteDisplay = whiteTime;

						nextStateIndex = 0;
						stateVector[0] = State.t_Game_W_Black_turn;
					}
				}
			}
		}
	}

	/* The reactions of state White fallen. */
	private void reactT_Game_W_White_fallen() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
		}
	}

	/* The reactions of state Black fallen. */
	private void reactT_Game_W_Black_fallen() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
		}
	}

	/* The reactions of state Black adjourned. */
	private void reactT_Game_W_Black_adjourned() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (sCIButtons.modeButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.setTimer(this, 0, 1 * 1000, false);

				sCIDisplay.text = "White moves";

				sCIDisplay.blackDisplay = blackTime;

				sCIDisplay.whiteDisplay = whiteTime;

				nextStateIndex = 0;
				stateVector[0] = State.t_Game_W_White_turn;
			}
		}
	}

	/* The reactions of state White adjourned. */
	private void reactT_Game_W_White_adjourned() {
		if (sCIButtons.startButton) {
			switch (stateVector[0]) {
				case t_Game_W_White_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 0);
					break;

				case t_Game_W_Black_turn :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;

					timer.unsetTimer(this, 1);
					break;

				case t_Game_W_White_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_fallen :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_Black_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				case t_Game_W_White_adjourned :
					nextStateIndex = 0;
					stateVector[0] = State.$NullState$;
					break;

				default :
					break;
			}

			sCIDisplay.text = "Ready to play";

			blackTime = blackInit;

			whiteTime = whiteInit;

			sCIDisplay.whiteDisplay = -1;

			sCIDisplay.blackDisplay = -1;

			nextStateIndex = 0;
			stateVector[0] = State.t_Ready_to_play;
		} else {
			if (sCIButtons.modeButton) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.setTimer(this, 1, 1 * 1000, false);

				sCIDisplay.text = "Black moves";

				sCIDisplay.blackDisplay = blackTime;

				sCIDisplay.whiteDisplay = whiteTime;

				nextStateIndex = 0;
				stateVector[0] = State.t_Game_W_Black_turn;
			}
		}
	}

	public void runCycle() {

		clearOutEvents();

		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {

			switch (stateVector[nextStateIndex]) {
				case t_Ready_to_play :
					reactT_Ready_to_play();
					break;
				case t_White_initial_time :
					reactT_White_initial_time();
					break;
				case t_Black_Initial_time :
					reactT_Black_Initial_time();
					break;
				case t_Increment_time :
					reactT_Increment_time();
					break;
				case t_Beginning_player_Selection_White_begins :
					reactT_Beginning_player_Selection_White_begins();
					break;
				case t_Beginning_player_Selection_Black_begins :
					reactT_Beginning_player_Selection_Black_begins();
					break;
				case t_Game_W_White_turn :
					reactT_Game_W_White_turn();
					break;
				case t_Game_W_Black_turn :
					reactT_Game_W_Black_turn();
					break;
				case t_Game_W_White_fallen :
					reactT_Game_W_White_fallen();
					break;
				case t_Game_W_Black_fallen :
					reactT_Game_W_Black_fallen();
					break;
				case t_Game_W_Black_adjourned :
					reactT_Game_W_Black_adjourned();
					break;
				case t_Game_W_White_adjourned :
					reactT_Game_W_White_adjourned();
					break;
				default :
					// $NullState$
			}
		}

		clearEvents();
	}
}
