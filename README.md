# Overview
Java based virtual card game with an implemented complex strategic AI opponent and peer-to-peer multiplayer. Players can play with built-in decks or create, save, and import decks of their own to use. [GAME PAGE WITH DOWNLOAD](https://joey101937.itch.io/cardgame1)
![Screenshot](http://i68.tinypic.com/rr59n8.jpg)
# Strategic AI
The AI opponent for the single player of this game is dynamic and able to assess a nearly infinite number of board states. It does this by analyzing the value of each card on a dynamic basis, taking into consideration other related cards- both friend and foe- and the resources available as well as how close it is to a failure or victory state.
### AI.GetValue
When the AI takes a turn, it evaluates all cards in its hand individually. Each card has a purpose field which helps the AI assess the card's intended use case. The AI will first assess the raw stat points of a card in different ways depending on the card's use case, but generally the baseline is derived by summing the stat points of the card or creature that it would summon. It then factors in the player's future possible actions. Could the player cost-effectively deal with this card on his/her next turn? That would reset the value to the value penalty the player would take dealing with it rather than damage potential + staying power. Would playing this card impact the AI's ability to play another card? If that card is more valuable than this, we will penalize the value of this card. 

Another variable in getting a card's value is its intrinsic value. This is a dynamic, sometimes volatile value that can change at any time and deflects the impact of modifiers, special abilities, or handicaps related to a given card. The higher intrinsic value means the AI will value that card more than others while negative values will make the AI want to kill off that Card/Creature. The AI also sees intrinsic value on any opposing card revealed to it by the player and uses this information to determine ideal targets for removal.
### AI.TakeTurn
The take turn is where the AI puts the above GetValue into use. The AI takes its turn in 3 steps: Trade, Play, Trade. At the start of the turn, the AI will iterate through all of its available creatures active on the board and assess their ability to generate value based on what the player has put into play against them. If any cost-effective trades are readily available, or if the victory state is achievable, the AI will act upon them accordingly.

The meat of the method is the recursively implemented play stage where each iteration while the AI has available cards, it will implement the GetValue system outlined previously. Each time, the AI plays a card, it will reassess the situation and reassign worth and cost values accordingly as well as update all intrinsic values.

Once the AI is done playing its cards, it will enter trade state #2. This stage reassesses the board state with updated values and looks for newly created opportunities as well as checking victory and failure state progress. Nearing failure state will trigger a defensive playstyle prioritizing eliminating incoming threats while an achievable victory state will be perused immediately.

Once the AI evaluates all known cards, it assigns a value or "worth" to each, reflecting how good of a play that option would be. The AI then divides by the cost of each card to get the value per cost of each. These worth and value assignments are dynamic in that they may change mid-turn as the player reacts to its moves and makes counter plays.

The AI also has an end goal which is killing the player. It may choose to pursue this over protecting itself if there are no cost-effective plays it can do otherwise. The AI understands when either itself or the player is vulnerable to a game-over and will always prioritize ending the game in a victory state while playing defensively when its failure state is nearing.
# Multiplayer
### How to use multiplayer
This game allows for peer to peer multiplayer. One player will select the multiplayer option and choose "server". Their friend will then select multiplayer and choose "client". The client will then be prompted for the address of the server player. Once entered, the two users will communicate over port 444 and initialize a multiplayer game. 
### How it works
The game when in multiplayer mode uses a "Phantom" to communicate and replicate user actions. The Phantom is created and assigned to the non-user hero like an AI would be, however instead of using the AI algorithms, it communicates with the remote user and follows orders based on what the remote user sends.
A user, when making plays in an online game, will send instructions to the phantom of his opponent to replicate these actions. For example, if user1 plays the first card in his hand, the phantom opponent of user2 will detect this and also play the first card in its hand. Instructions are formatted in the following way: actor-actorindex-target-targetindex. actors can be either 'c' or 'm' for playing a card or issuing an attack with a minion. actor index can be 0-5 for cards (place in hand) or 0-3 for minions (place on board). Target can be fh,eh,em,fmn for friendly hero, enemy hero, enemy minion, friendly minion, or null. Target index will reflect the place on the board of the owning hero if targeting a minion, or 0 if null or targeting hero

# Gameplay
The game has a class system and 30+ cards as of this update. Cards are of three types: Minion, Spell, and Trap. Minions summon a creature or "minion" onto the field which can attack for you, spells do some listed effect, and traps go dormant and wait for their trigger condition to be met. Every card has a price that you must pay to play. This is the yellow value at the top left of the card. You start out with one crystal and gain an additional one each turn. Expended crystals are returned at the end of each turn. There are also different classes that pertain to the overall deck, meaning theme cards go together. A deck can contain that class's cards as well as neutral cards. A deck may not, however, contain cards belonging to a different class. Players can build their own decks and load them into the game to play against the AI or give them to the AI to use against players.

Additionally, at the start of a game, the server will generate a random seed for use in both user's Random object, which will synchronize random outcomes across both user's games. Each player's deck will also be sent to the other user's phantom at the beginning of the game, and appropriate asset changes will be made by each instance.

![Screenshot](https://img.itch.zone/aW1hZ2UvMjUzMzI1LzEyMTI1NDgucG5n/original/irFtlX.png)
![Screenshot](https://img.itch.zone/aW1hZ2UvMjUzMzI1LzEyMTI1NDYucG5n/original/Z1HyHN.png)
![Screenshot](http://i68.tinypic.com/15s0yue.png)
