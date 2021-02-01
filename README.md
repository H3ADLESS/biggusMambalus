# Biggus Mambalus - Reengineering Mamba 
We want to learn rebuild and improve the base game.

## How to  run the game:
chmod +x gradlew  
./gradlew run

## Open ToDo's (open for discussion or feedback) 

#### Game Loop 
Done

#### KeyManager
- Objects can register themselves for certain Keys (Yes. they can).
- At least the Keys: 
  - UP
  - DOWN
  - RIGHT
  - LEFT
  - P for Pause
  - ESC for menu
  - X, C for increase and decrease speed

#### Snake
- Rendering and Display of the Snake
- Movement of the Snake
- SnakeIntelligence i.e. level dependent

#### Spider
- Direction Change on Input
- Movement Logic   
- Spider Image

#### SpiderThread
- Rendering

#### Board Logic
- Spider Territory (Grey Space)  
- SpiderThread Collision  
- Snake bites Spider   

#### Level Logic
- Level dependent stuff

#### Points Calculations
- Spider Territory to points   
- CurrentScore   
- HighScore

