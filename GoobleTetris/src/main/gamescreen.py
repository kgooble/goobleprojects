'''
Created on 2012-02-04

@author: Gooble


'''
import pygame
from main.display.splash.state import SplashState

SCREEN_WIDTH = 500
SCREEN_HEIGHT = 600

if __name__ == "__main__":
    pygame.init()
    
    size = [SCREEN_WIDTH, SCREEN_HEIGHT]
    
    screen = pygame.display.set_mode(size)
    pygame.display.set_caption("Gooble Tetris")
    clock = pygame.time.Clock()
    
    state = SplashState()
    
    while True:
        clock.tick(30)
        screen.fill(pygame.color.THECOLORS['black'])
        try:
            next_state = state
            for event in pygame.event.get():
                possible_next_state = state.process(event)
                if possible_next_state is not None:
                    next_state = possible_next_state
            state.display(screen)
            state = next_state
        except SystemExit:
            state.kill()
            break
        pygame.display.flip()
                