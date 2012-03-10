'''
Created on 2012-02-04

@author: Gooble


'''


from main.display.draw import SCREEN_HEIGHT, SCREEN_WIDTH
from main.display.tilecolors import BLACK
import pygame
from main.display.splashstate import SplashState

if __name__ == "__main__":
    pygame.init()
    
    size = [SCREEN_WIDTH, SCREEN_HEIGHT]
    
    screen = pygame.display.set_mode(size)
    pygame.display.set_caption("Gooble Tetris")
    clock = pygame.time.Clock()
    
    state = SplashState()
    
    while True:
        clock.tick(30)
        screen.fill(BLACK)
        try:
            next_state = state
            for event in pygame.event.get():
                next_state = state.process(event)
            state.display(screen)
            state = next_state
        except SystemExit:
            state.kill()
            break
        pygame.display.flip()
                