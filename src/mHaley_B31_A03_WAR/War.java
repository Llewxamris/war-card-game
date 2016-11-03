package mHaley_B31_A03_WAR;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import adts.queue.EmptyQueueException;
import adts.queue.ListQueue;
import adts.queue.Queue;

public class War
{
	private Deck deckOfCards = new Deck(); // The deck of cards
	private String player1; // The String containing the first players name
	private String player2; // The String containing the second player name
	private String output;
	// The queue containing the first players hand
	private Queue<Card> player1Hand = new ListQueue<Card>(52);
	// The queue containing the second players hand
	private Queue<Card> player2Hand = new ListQueue<Card>(52);
	private Card player1CurrentCard;
	private Card player2CurrentCard;

	War()
	{
		// War constructor. Sets player1 and player2 to Unknown
		player1 = "Unknown";
		player2 = "Unknown";
		start(); // Call start to shuffle the cards and distribute the cards into
							// the players hands.
	}// War()

	protected String getPlayer1()
	{
		// Returns the value of player1
		return player1;
	} // getPlayer1()

	protected void setPlayer1(String player1)
	{
		// Sets player1 to the value of @param player1
		this.player1 = player1;
	} // setPlayer1(...)

	protected String getPlayer2()
	{
		// @return the value of player2
		return player2;
	} // getPlayer2()

	protected void setPlayer2(String player2)
	{
		// Sets player2 to the value of @param player2
		System.out.println("DEBUG: Player 2 name set.");
		this.player2 = player2;
	} // setPlayer2(...)

	protected Card getPlayer1CurrentCard()
	{
		return player1CurrentCard;
	}

	private void setPlayer1CurrentCard(Card player1CurrentCard)
	{
		this.player1CurrentCard = player1CurrentCard;
	}

	protected Card getPlayer2CurrentCard()
	{
		return player2CurrentCard;
	}

	private void setPlayer2CurrentCard(Card player2CurrentCard)
	{
		this.player2CurrentCard = player2CurrentCard;
	}

	protected String getOutput()
	{
		return output;
	}

	private void addToOutput(String output)
	{
		this.output += "\n" + output;
	}

	protected void clearOutput()
	{
		this.output = "";
	}

	private void start()
	{
		/*
		 * Calls the shuffle method in deckOfCards to shuffle the deck. Takes the
		 * Deck object deckOfCards and distributes the Card objects within to the
		 * *Hand queues.
		 */
		boolean cardForPlayer1 = true; // Lets us alternate hands
		deckOfCards.shuffle();
		addToOutput("The deck has been shuffled.");

		while (deckOfCards.size() != 0)
		{
			if (cardForPlayer1)
			{
				player1Hand.enqueue(deckOfCards.deal());
				cardForPlayer1 = false;
			}
			else
			{
				player2Hand.enqueue(deckOfCards.deal());
				cardForPlayer1 = true;
			}
		}
		addToOutput(
				getPlayer1() + " has " + player1Hand.size() + " cards to start.");
		addToOutput(
				getPlayer2() + " has " + player2Hand.size() + " cards to start.");
		addToOutput("\nIT IS TIME, FOR WAR!");
		addToOutput("--------------------");

	} // start()

	protected String play()
	{
		/*
		 * Runs through a round of play, dealing with winning, losing, gaining or
		 * losing cards, and with WAR
		 */

		ArrayList<Card> winnersPot = new ArrayList<Card>();
		Stack<Card> kitty = new Stack<Card>();
		boolean warHasEnded;

		try
		{
			setPlayer1CurrentCard(player1Hand.dequeue());
			addToOutput(
					getPlayer1() + " plays a " + getPlayer1CurrentCard().toString());

			setPlayer2CurrentCard(player2Hand.dequeue());
			addToOutput(
					getPlayer2() + " plays a " + getPlayer2CurrentCard().toString());

			winnersPot.add(getPlayer1CurrentCard());
			winnersPot.add(getPlayer2CurrentCard());

			if (getPlayer1CurrentCard().getRank() > getPlayer2CurrentCard().getRank())
			{
				distributeWinnings(player1Hand, winnersPot);
				addToOutput(getPlayer1() + " wins the hand!");
			}
			else
				if (getPlayer2CurrentCard().getRank() > getPlayer1CurrentCard()
						.getRank())
				{
					distributeWinnings(player2Hand, winnersPot);
					addToOutput(getPlayer2() + " wins the hand!");
				}
				else
				{
					warHasEnded = false;
					addToOutput("It's a tie! IT IS TIME FOR WAR!");
					addToOutput("Each player puts 3 cards in the trench!");
					while (!warHasEnded)
					{
						kitty.push(getPlayer1CurrentCard());
						kitty.push(getPlayer2CurrentCard());
						for (int x = 0; x < 3; x++)
						{
							kitty.push(player1Hand.dequeue());
							kitty.push(player2Hand.dequeue());
						}
						addToOutput("The trench contains " + kitty.size() + " cards.");

						setPlayer1CurrentCard(player1Hand.dequeue());
						addToOutput(getPlayer1() + playVerbs()
								+ getPlayer1CurrentCard().toString() + "!");

						setPlayer2CurrentCard(player2Hand.dequeue());
						addToOutput(getPlayer2() + playVerbs()
								+ getPlayer2CurrentCard().toString() + "!");

						winnersPot.add(getPlayer1CurrentCard());
						winnersPot.add(getPlayer2CurrentCard());

						if (getPlayer1CurrentCard().getRank() > getPlayer2CurrentCard()
								.getRank())
						{
							for (int o = 0; o < kitty.size(); o++)
							{
								winnersPot.add(kitty.pop());
							}
							distributeWinnings(player1Hand, winnersPot);
							addToOutput(getPlayer1() + victoryVerbs() + getPlayer2() + "!");
							warHasEnded = true;
						}
						else
							if (getPlayer2CurrentCard().getRank() > getPlayer1CurrentCard()
									.getRank())
							{
								for (int o = 0; o < kitty.size(); o++)
								{
									winnersPot.add(kitty.pop());
								}
								distributeWinnings(player2Hand, winnersPot);
								addToOutput(getPlayer2() + victoryVerbs() + getPlayer1() + "!");
								warHasEnded = true;
							}
					}
				}
		}
		// WAR!!!
		catch (EmptyQueueException e)
		{
			warHasEnded = true;
		}

		addToOutput(getPlayer1() + " has " + player1Hand.size() + " cards left.");
		addToOutput(getPlayer2() + " has " + player2Hand.size() + " cards left.");
		winnersPot.clear();

		if (player1Hand.isEmpty())
		{
			return getPlayer2();
		}
		else
			if (player2Hand.isEmpty())
			{
				return getPlayer1();
			}
		return "No Current Winner";
	} // play()

	private void distributeWinnings(Queue<Card> winningHand,
			ArrayList<Card> winnings)
	{
		for (int i = 0; i < winnings.size(); i++)
		{
			winningHand.enqueue(winnings.get(i));
		}
	}

	private String victoryVerbs()
	{
		String[] verbs = { "crushes", "destroys", "annihilates", "slaughters",
				"demolishes", "exterminates", "obliterates", "massacres", "wrecks",
				"rekts", "owns", "pwns", "abrogates", "humilates", "kills", "slayes",
				"scalps", "immolates", "crucifies", "sacrifices", "smothers", "ruins",
				"buries", "lynches", "eradicates", "executes", "beheads",
				"draw and quarters", "eviscerate", "butchers" };

		Random rng = new Random();

		return " " + verbs[rng.nextInt(verbs.length)] + " ";
	}

	private String playVerbs()
	{
		String[] plays = { "fires", "launches", "attacks", "shells", "bombs",
				"bombards" };

		Random rng = new Random();
		return " " + plays[rng.nextInt(plays.length)] + " with a(n) ";
	}
}
