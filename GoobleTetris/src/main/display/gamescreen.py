'''
Created on 2012-02-04

@author: Gooble


'''


from main.display.tilecolors import BLACK
import pygame
from main.gameplay.game import Game
from main.display.taskthread import TaskThread
from main.display.draw import SCREEN_HEIGHT, SCREEN_WIDTH, DrawBoard
from main.display.gameeventhandler import GameEventHandler
from main.display.gamestate import GameState

if __name__ == "__main__":
    pygame.init()
    
    size = [SCREEN_WIDTH, SCREEN_HEIGHT]
    
    screen = pygame.display.set_mode(size)
    pygame.display.set_caption("Gooble Tetris")
    clock = pygame.time.Clock()
    
    game = Game()
    
    state = GameState(GameEventHandler(game), DrawBoard(game), TaskThread(game))
    
    while True:
        clock.tick(30)
        screen.fill(BLACK)
        try:
            for event in pygame.event.get():
                state.process(event)
            state.display(screen)
        except SystemExit:
            state.kill()
            break
        pygame.display.flip()
                