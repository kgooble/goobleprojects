'''
Created on 2012-02-04

@author: Gooble
'''

import pygame
from main.display.game.tilecolors import WHITE, TILE_COLORS
from main.display.screencoords import ScreenCoords
from main.shapes.shape import Shape
from main.movement.transform.translation import Translation
from main.movement.direction import RIGHT, DOWN

PADDING = 20

class DrawBoard(object):
    def __init__(self, game, predicting=False):
        self.game = game
        self.predicting = predicting

    def display(self, screen):
        self.draw_board(screen)
        self.write_score(screen)
        self.draw_prediction(screen)
        self.draw_next_shape(screen)

    def write_score(self, screen):
        pygame.draw.line(screen, WHITE, [screen.get_height()/2, 0], [screen.get_height()/2, 600], 5)
        font = pygame.font.Font(None, 25)
        text = font.render("Score", True, WHITE)
        screen.blit(text, [screen.get_height()/2 + PADDING, 200])
        text = font.render(str(self.game.get_score()), True, WHITE)
        screen.blit(text, [screen.get_height()/2 + PADDING, 250])
    
    def fade(self, color):
        new_color = [color[0] - 200, color[1] - 200, color[2] - 200]
        for i in range (0, len(new_color)):
            if new_color[i] < 0:
                new_color[i] = 0
        return new_color
    
    def draw_piece(self, screen, coords, piece, faded=False):
        color = TILE_COLORS[piece]
        if faded:
            color = self.fade(color)
        pygame.draw.rect(screen, color, [coords.get_start_x(), 
                                         coords.get_start_y(), 
                                         coords.get_width(), 
                                         coords.get_height()])
        if not piece.is_empty():
            pygame.draw.rect(screen, WHITE, [coords.get_start_x(), 
                                             coords.get_start_y(), 
                                             coords.get_width(), 
                                             coords.get_height()], 1)
    def draw_prediction(self, screen):
        cur_shape = self.game.get_current_shape()
        if cur_shape is None or not self.predicting:
            return
        predicted = Shape(cur_shape.get_tile())
        predicted.set_position(self.game.get_predicted_points())
        self.draw_shape(screen, predicted, faded=True)
                    
    def draw_board(self, screen):
        pieces = self.game.get_pieces()
        for i in range(0, len(pieces)):
            for j in range(0, len(pieces[0])):
                piece = self.game.get_pieces()[i][j]
                coords = ScreenCoords(row=i, col=j, tile_width=(screen.get_height()/20))
                self.draw_piece(screen, coords, piece)
                
    def draw_next_shape(self, screen):
        next_shape = Shape(self.game.get_next_shape())
        for i in range(0, 12):
            next_shape.set_position(Translation().in_direction(next_shape.get_points(), RIGHT))
        for i in range(0, 2):
            next_shape.set_position(Translation().in_direction(next_shape.get_points(), DOWN))
        self.draw_shape(screen, next_shape)
    
    def draw_shape(self, screen, shape, faded=False):
        for point in shape.get_points():
            coords = ScreenCoords(row=point.row, col=point.col, tile_width=(screen.get_height()/20))
            self.draw_piece(screen, coords, shape.get_tile(), faded=faded)
        
        