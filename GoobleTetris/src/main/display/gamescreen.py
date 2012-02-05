'''
Created on 2012-02-04

@author: Gooble


'''


from main.display.tilecolors import BLACK
import pygame
from main.gameplay.game import Game
from main.movement.direction import LEFT, RIGHT
from main.display.taskthread import TaskThread
from main.display.draw import SCREEN_HEIGHT, SCREEN_WIDTH, DrawBoard

def handle_events(pygame, game):
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            return True
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                game.move(LEFT)
            if event.key == pygame.K_RIGHT:
                game.move(RIGHT)
            if event.key == pygame.K_UP:
                game.rotate()
            if event.key == pygame.K_SPACE:
                game.drop()
            if event.key == pygame.K_DOWN:
                game.speed_up()
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                game.slow_down()
    return False

if __name__ == "__main__":
    pygame.init()
    
    size = [SCREEN_WIDTH, SCREEN_HEIGHT]
    
    screen = pygame.display.set_mode(size)
    pygame.display.set_caption("Gooble Tetris")
    clock = pygame.time.Clock()
    
    game = Game()
    drawer = DrawBoard(game, screen)
    done = False
    
    gthread = TaskThread(game)
    gthread.start()
    while not game.is_game_over() and not done:
        clock.tick(30)
        done = handle_events(pygame, game)
        screen.fill(BLACK)
        drawer.draw_board()
        drawer.write_score()
        drawer.draw_next_shape()
        pygame.display.flip()
    
    gthread.shutdown()
                
                