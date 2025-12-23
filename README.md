**Space Invaders**

A console-based implementation of the classic arcade game Space Invaders, recreating the iconic alien-shooting experience entirely in text mode.

**How to play**

The objective is to defend your ship against waves of descending aliens. The rules are straightforward:

- Control your UCMShip (marked with "^__^") at the bottom of the 9×8 grid
- Move left/right across the board to dodge enemy fire
- Shoot lasers to eliminate aliens before they reach the final row
- Earn points by destroying aliens and the occasional UFO
- Game ends when you lose all lives, aliens land, or all enemies are defeated

**Key commands:**
- `move <direction>`: Move your ship (left, lleft, right, rright)
- `shoot`: Fire a regular laser
- `shoot superlaser`: Fire a more powerful laser (costs 5 points)
- `shockwave`: Activate a screen-clearing shockwave (if available)
- `none`: Skip your movement turn
- `exit`: Quit the current game
- `list`: Display information about all ship types
- `reset`: Restart with a new game configuration

**Features**

**Classic Arcade Action**: Faithful recreation of Space Invaders mechanics adapted for console play

**Multiple Enemy Types**:
- Regular Aliens: Standard enemies that move in formation
- Destroyer Aliens: Drop bombs downward as they advance
- Explosive Aliens: Deal damage to surrounding ships when destroyed
- UFO: Rare appearance that grants extra points and enables shockwave

**Advanced Weapon System**:
- UCMLaser: Standard projectile
- SuperLaser: Deals double damage (consumes 5 points)
- ShockWave: Clears all enemies from the screen (unlockable via UFO)

**Difficulty Levels**: EASY, HARD, and INSANE modes with varying alien speeds, fire rates, and formation sizes

**Custom Initialization**: Load custom alien formations from text files using InitialConfiguration mode

**Save & Debug Support**: Built-in random seed system allows reproducing specific game scenarios for testing

Debug Mode
To enable reproducible testing, the game accepts a random seed parameter during initialization. Set the seed in the Main.java file to replay identical enemy patterns and test specific situations.